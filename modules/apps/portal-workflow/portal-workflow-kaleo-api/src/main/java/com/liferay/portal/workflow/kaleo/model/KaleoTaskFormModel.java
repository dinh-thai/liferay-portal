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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KaleoTaskForm service. Represents a row in the &quot;KaleoTaskForm&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskFormModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskFormImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskForm
 * @generated
 */
@ProviderType
public interface KaleoTaskFormModel
	extends BaseModel<KaleoTaskForm>, GroupedModel, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo task form model instance should use the {@link KaleoTaskForm} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo task form.
	 *
	 * @return the primary key of this kaleo task form
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo task form.
	 *
	 * @param primaryKey the primary key of this kaleo task form
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kaleo task form.
	 *
	 * @return the mvcc version of this kaleo task form
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kaleo task form.
	 *
	 * @param mvccVersion the mvcc version of this kaleo task form
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the kaleo task form ID of this kaleo task form.
	 *
	 * @return the kaleo task form ID of this kaleo task form
	 */
	public long getKaleoTaskFormId();

	/**
	 * Sets the kaleo task form ID of this kaleo task form.
	 *
	 * @param kaleoTaskFormId the kaleo task form ID of this kaleo task form
	 */
	public void setKaleoTaskFormId(long kaleoTaskFormId);

	/**
	 * Returns the group ID of this kaleo task form.
	 *
	 * @return the group ID of this kaleo task form
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo task form.
	 *
	 * @param groupId the group ID of this kaleo task form
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo task form.
	 *
	 * @return the company ID of this kaleo task form
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo task form.
	 *
	 * @param companyId the company ID of this kaleo task form
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo task form.
	 *
	 * @return the user ID of this kaleo task form
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo task form.
	 *
	 * @param userId the user ID of this kaleo task form
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo task form.
	 *
	 * @return the user uuid of this kaleo task form
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo task form.
	 *
	 * @param userUuid the user uuid of this kaleo task form
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo task form.
	 *
	 * @return the user name of this kaleo task form
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo task form.
	 *
	 * @param userName the user name of this kaleo task form
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo task form.
	 *
	 * @return the create date of this kaleo task form
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo task form.
	 *
	 * @param createDate the create date of this kaleo task form
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo task form.
	 *
	 * @return the modified date of this kaleo task form
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo task form.
	 *
	 * @param modifiedDate the modified date of this kaleo task form
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo definition version ID of this kaleo task form.
	 *
	 * @return the kaleo definition version ID of this kaleo task form
	 */
	public long getKaleoDefinitionVersionId();

	/**
	 * Sets the kaleo definition version ID of this kaleo task form.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID of this kaleo task form
	 */
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId);

	/**
	 * Returns the kaleo node ID of this kaleo task form.
	 *
	 * @return the kaleo node ID of this kaleo task form
	 */
	public long getKaleoNodeId();

	/**
	 * Sets the kaleo node ID of this kaleo task form.
	 *
	 * @param kaleoNodeId the kaleo node ID of this kaleo task form
	 */
	public void setKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns the kaleo task ID of this kaleo task form.
	 *
	 * @return the kaleo task ID of this kaleo task form
	 */
	public long getKaleoTaskId();

	/**
	 * Sets the kaleo task ID of this kaleo task form.
	 *
	 * @param kaleoTaskId the kaleo task ID of this kaleo task form
	 */
	public void setKaleoTaskId(long kaleoTaskId);

	/**
	 * Returns the kaleo task name of this kaleo task form.
	 *
	 * @return the kaleo task name of this kaleo task form
	 */
	@AutoEscape
	public String getKaleoTaskName();

	/**
	 * Sets the kaleo task name of this kaleo task form.
	 *
	 * @param kaleoTaskName the kaleo task name of this kaleo task form
	 */
	public void setKaleoTaskName(String kaleoTaskName);

	/**
	 * Returns the name of this kaleo task form.
	 *
	 * @return the name of this kaleo task form
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kaleo task form.
	 *
	 * @param name the name of this kaleo task form
	 */
	public void setName(String name);

	/**
	 * Returns the description of this kaleo task form.
	 *
	 * @return the description of this kaleo task form
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kaleo task form.
	 *
	 * @param description the description of this kaleo task form
	 */
	public void setDescription(String description);

	/**
	 * Returns the form company ID of this kaleo task form.
	 *
	 * @return the form company ID of this kaleo task form
	 */
	public long getFormCompanyId();

	/**
	 * Sets the form company ID of this kaleo task form.
	 *
	 * @param formCompanyId the form company ID of this kaleo task form
	 */
	public void setFormCompanyId(long formCompanyId);

	/**
	 * Returns the form definition of this kaleo task form.
	 *
	 * @return the form definition of this kaleo task form
	 */
	@AutoEscape
	public String getFormDefinition();

	/**
	 * Sets the form definition of this kaleo task form.
	 *
	 * @param formDefinition the form definition of this kaleo task form
	 */
	public void setFormDefinition(String formDefinition);

	/**
	 * Returns the form group ID of this kaleo task form.
	 *
	 * @return the form group ID of this kaleo task form
	 */
	public long getFormGroupId();

	/**
	 * Sets the form group ID of this kaleo task form.
	 *
	 * @param formGroupId the form group ID of this kaleo task form
	 */
	public void setFormGroupId(long formGroupId);

	/**
	 * Returns the form ID of this kaleo task form.
	 *
	 * @return the form ID of this kaleo task form
	 */
	public long getFormId();

	/**
	 * Sets the form ID of this kaleo task form.
	 *
	 * @param formId the form ID of this kaleo task form
	 */
	public void setFormId(long formId);

	/**
	 * Returns the form uuid of this kaleo task form.
	 *
	 * @return the form uuid of this kaleo task form
	 */
	@AutoEscape
	public String getFormUuid();

	/**
	 * Sets the form uuid of this kaleo task form.
	 *
	 * @param formUuid the form uuid of this kaleo task form
	 */
	public void setFormUuid(String formUuid);

	/**
	 * Returns the metadata of this kaleo task form.
	 *
	 * @return the metadata of this kaleo task form
	 */
	@AutoEscape
	public String getMetadata();

	/**
	 * Sets the metadata of this kaleo task form.
	 *
	 * @param metadata the metadata of this kaleo task form
	 */
	public void setMetadata(String metadata);

	/**
	 * Returns the priority of this kaleo task form.
	 *
	 * @return the priority of this kaleo task form
	 */
	public int getPriority();

	/**
	 * Sets the priority of this kaleo task form.
	 *
	 * @param priority the priority of this kaleo task form
	 */
	public void setPriority(int priority);

}