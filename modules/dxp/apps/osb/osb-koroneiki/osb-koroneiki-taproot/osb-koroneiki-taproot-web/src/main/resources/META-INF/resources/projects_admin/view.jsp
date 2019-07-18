<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ViewProjectsManagementToolbarDisplayContext viewProjectsManagementToolbarDisplayContext = new ViewProjectsManagementToolbarDisplayContext(request, renderRequest, renderResponse);

SearchContainer searchContainer = viewProjectsManagementToolbarDisplayContext.getSearchContainer();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewProjectsManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	clearResultsURL="<%= viewProjectsManagementToolbarDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= viewProjectsManagementToolbarDisplayContext.getCreationMenu() %>"
	filterDropdownItems="<%= viewProjectsManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= viewProjectsManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="accountSearch"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= viewProjectsManagementToolbarDisplayContext.getSortingURL() %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= searchContainer %>"
		var="projectSearch"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Project"
			escapedModel="<%= true %>"
			keyProperty="projectId"
			modelVar="project"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/projects_admin/edit_project" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="projectId" value="<%= String.valueOf(project.getProjectId()) %>" />
			</portlet:renderURL>

			<%
			Account koroneikiAccount = project.getAccount();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="project" />">
					<aui:icon cssClass="icon-monospaced" image="organizations" markupView="lexicon" />
				</span>

				<%= project.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account"
				value="<%= HtmlUtil.escape(koroneikiAccount.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="code"
				value="<%= project.getCode() %>"
			/>

			<liferay-ui:search-container-column-status
				href="<%= rowURL %>"
				name="status"
				status="<%= project.getStatus() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/projects_admin/project_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>