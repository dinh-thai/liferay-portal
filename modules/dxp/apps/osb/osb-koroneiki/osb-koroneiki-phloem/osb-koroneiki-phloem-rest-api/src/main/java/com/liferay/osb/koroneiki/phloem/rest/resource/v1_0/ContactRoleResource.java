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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface ContactRoleResource {

	public ContactRole postContactRole(ContactRole contactRole)
		throws Exception;

	public void deleteContactRole(Long contactRoleId) throws Exception;

	public ContactRole getContactRole(Long contactRoleId) throws Exception;

	public ContactRole putContactRole(
			Long contactRoleId, ContactRole contactRole)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}