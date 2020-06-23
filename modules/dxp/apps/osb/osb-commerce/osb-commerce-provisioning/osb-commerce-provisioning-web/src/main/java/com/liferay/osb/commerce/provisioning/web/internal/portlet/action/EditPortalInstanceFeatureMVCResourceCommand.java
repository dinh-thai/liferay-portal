/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.commerce.provisioning.web.internal.portlet.action;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.osb.commerce.provisioning.web.internal.constants.OSBCommerceProvisioningPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + OSBCommerceProvisioningPortletKeys.TRIAL_REGISTRATION,
		"mvc.command.name=portalInstanceStatus"
	},
	service = MVCResourceCommand.class
)
public class EditPortalInstanceFeatureMVCResourceCommand extends BaseMVCResourceCommand {
	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long commerceSubscriptionEntryId = ParamUtil.getLong(
			resourceRequest, "commerceSubscriptionEntryId");
		String featureName = ParamUtil.getString(
			resourceRequest, "featureName");
		boolean active = ParamUtil.getBoolean(resourceRequest, "active");

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_getCommerceSubscriptionEntry(commerceSubscriptionEntryId);

		UnicodeProperties properties = commerceSubscriptionEntry.
			getSubscriptionTypeSettingsProperties();

		if (active) {
			properties.put(featureName, String.valueOf(featureName));
		}
		else {
			properties.remove(featureName);
		}

		commerceSubscriptionEntry.setSubscriptionTypeSettingsProperties(
			properties);

		_commerceSubscriptionEntryLocalService.updateCommerceSubscriptionEntry(
			commerceSubscriptionEntry);
	}

	private CommerceSubscriptionEntry _getCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId) {

		return _commerceSubscriptionEntryLocalService.
			fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;
}
