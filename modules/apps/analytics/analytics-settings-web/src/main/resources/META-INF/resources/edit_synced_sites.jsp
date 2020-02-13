<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ChannelDisplayContext channelDisplayContext = new ChannelDisplayContext(renderRequest, renderResponse);

ChannelSearch channelSearch = channelDisplayContext.getChannelSearch();
%>

<portlet:actionURL name="/analytics/edit_synced_sites" var="editSyncedSitesURL" />

<div class="pb-2 portlet-analytics-settings sheet sheet-lg">
	<h2>
		<liferay-ui:message key="sync-sites-to-property" />
	</h2>

	<p class="mt-3 text-secondary">
		<liferay-ui:message key="select-or-create-a-property-to-manage-synced-sites" />
	</p>

	<c:choose>
		<c:when test="<%= channelSearch == null %>">
			<liferay-ui:message key="failed-to-fetch-properties" />
		</c:when>
		<c:when test="<%= (channelSearch != null) && (channelSearch.getTotal() == 0) %>">
			<div class="mb-5 mt-5">
				<div class="empty-state-icon mb-4 mt-4"></div>

				<div class="text-center">
					<h2>
						<liferay-ui:message key="no-properties-found" />
					</h2>

					<p class="text-secondary">
						<liferay-ui:message key="create-a-new-property-to-get-started" />
					</p>

					<button class="btn btn-primary">
						<liferay-ui:message key="new-property" />
					</button>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<clay:management-toolbar
				displayContext="<%= new ChannelManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, channelDisplayContext) %>"
				elementClasses="custom-management-toolbar"
			/>

			<liferay-ui:search-container
				id="selectChannels"
				searchContainer="<%= channelSearch %>"
				var="groupSearchContainer"
			>
				<liferay-ui:search-container-row
					className="com.liferay.analytics.settings.web.internal.model.Channel"
					escapedModel="<%= true %>"
					keyProperty="id"
					modelVar="channel"
				>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand"
						name="available-properties"
						value="<%= HtmlUtil.escape(channel.getName()) %>"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					searchResultCssClass="show-quick-actions-on-hover table table-autofit"
				/>
			</liferay-ui:search-container>
		</c:otherwise>
	</c:choose>
</div>