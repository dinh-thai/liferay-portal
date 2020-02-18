/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.analytics.settings.web.internal.portlet.action;

import com.liferay.analytics.settings.web.internal.util.AnalyticsSettingsUtil;
import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.ActionRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André Miranda
 */
@Component(
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/analytics/add_channel"
	},
	service = MVCActionCommand.class
)
public class AddChannelMVCActionCommand extends BaseAnalyticsMVCActionCommand {

	@Override
	protected void updateConfigurationProperties(
			ActionRequest actionRequest,
			Dictionary<String, Object> configurationProperties)
		throws Exception {

		String[] syncedGroupIds = ParamUtil.getStringValues(
			actionRequest, "rowIds");

		if (ArrayUtil.isEmpty(syncedGroupIds)) {
			return;
		}

		Set<String> liferayAnalyticsGroupIds = _mergeSyncedGroupIds(
			actionRequest, syncedGroupIds);

		_updateCompanyPreferences(actionRequest, liferayAnalyticsGroupIds);

		configurationProperties.put(
			"syncedGroupIds", liferayAnalyticsGroupIds.toArray(new String[0]));

		_notifyAnalyticsCloud(
			actionRequest, ParamUtil.getString(actionRequest, "channelType"),
			liferayAnalyticsGroupIds, syncedGroupIds);
	}

	private JSONObject _buildGroupJSONObject(
		Group group, ThemeDisplay themeDisplay) {

		JSONObject groupJSONObject = JSONUtil.put(
			"id", String.valueOf(group.getGroupId()));

		try {
			return groupJSONObject.put(
				"name", group.getDescriptiveName(themeDisplay.getLocale()));
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", themeDisplay.getLocale(), getClass());

			return groupJSONObject.put(
				"name", LanguageUtil.get(resourceBundle, "unknown"));
		}
	}

	private Set<String> _mergeSyncedGroupIds(
		ActionRequest actionRequest, String[] syncedGroupIds) {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Set<String> liferayAnalyticsGroupIds = SetUtil.fromArray(
			PrefsPropsUtil.getStringArray(
				themeDisplay.getCompanyId(), "liferayAnalyticsGroupIds",
				StringPool.COMMA));

		Collections.addAll(liferayAnalyticsGroupIds, syncedGroupIds);

		return liferayAnalyticsGroupIds;
	}

	private void _notifyAnalyticsCloud(
			ActionRequest actionRequest, String channelType,
			Set<String> liferayAnalyticsGroupIds, String[] syncedGroupIds)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!AnalyticsSettingsUtil.isAnalyticsEnabled(
				themeDisplay.getCompanyId())) {

			return;
		}

		// Update sitesSelected flag

		boolean sitesSelected = true;

		if (liferayAnalyticsGroupIds.isEmpty()) {
			sitesSelected = false;
		}

		HttpResponse httpResponse = AnalyticsSettingsUtil.doPut(
			JSONUtil.put("sitesSelected", sitesSelected),
			themeDisplay.getCompanyId(),
			String.format(
				"api/1.0/data-sources/%s/details",
				AnalyticsSettingsUtil.getAsahFaroBackendDataSourceId(
					themeDisplay.getCompanyId())));

		StatusLine statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
			disconnectDataSource(themeDisplay.getCompanyId(), httpResponse);

			return;
		}

		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
			throw new PortalException("Invalid token");
		}

		// Create channels

		Stream<String> stream = Arrays.stream(syncedGroupIds);

		List<Group> groups = stream.map(
			Long::valueOf
		).map(
			_groupLocalService::fetchGroup
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);

		httpResponse = AnalyticsSettingsUtil.doPost(
			JSONUtil.put(
				"channelType", channelType
			).put(
				"dataSourceId",
				AnalyticsSettingsUtil.getAsahFaroBackendDataSourceId(
					themeDisplay.getCompanyId())
			).put(
				"groups",
				JSONUtil.toJSONArray(
					groups, group -> _buildGroupJSONObject(group, themeDisplay))
			),
			themeDisplay.getCompanyId(), "api/1.0/channels");

		statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
			disconnectDataSource(themeDisplay.getCompanyId(), httpResponse);

			return;
		}

		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
			throw new PortalException("Invalid token");
		}
	}

	private Set<String> _updateCompanyPreferences(
			ActionRequest actionRequest, Set<String> liferayAnalyticsGroupIds)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UnicodeProperties unicodeProperties = new UnicodeProperties(true);

		unicodeProperties.setProperty(
			"liferayAnalyticsGroupIds",
			StringUtil.merge(liferayAnalyticsGroupIds, StringPool.COMMA));

		_companyService.updatePreferences(
			themeDisplay.getCompanyId(), unicodeProperties);

		return liferayAnalyticsGroupIds;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddChannelMVCActionCommand.class);

	@Reference
	private CompanyService _companyService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}