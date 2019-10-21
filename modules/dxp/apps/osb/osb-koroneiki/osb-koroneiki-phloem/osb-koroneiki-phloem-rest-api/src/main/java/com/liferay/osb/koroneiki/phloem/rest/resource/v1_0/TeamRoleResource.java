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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRolePermission;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
@ProviderType
public interface TeamRoleResource {

	public Page<TeamRole> getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
			String accountKey, String teamKey, Pagination pagination)
		throws Exception;

	public Page<TeamRole> getTeamRolesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public TeamRole postTeamRole(TeamRole teamRole) throws Exception;

	public void deleteTeamRole(String teamRoleKey) throws Exception;

	public TeamRole getTeamRole(String teamRoleKey) throws Exception;

	public TeamRole putTeamRole(String teamRoleKey, TeamRole teamRole)
		throws Exception;

	public void deleteTeamRoleTeamRolePermission(
			String teamRoleKey, TeamRolePermission teamRolePermission)
		throws Exception;

	public void putTeamRoleTeamRolePermission(
			String teamRoleKey, TeamRolePermission teamRolePermission)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public void setContextUser(User contextUser);

}