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

package com.liferay.osb.koroneiki.root.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AuditEntry service. Represents a row in the &quot;Koroneiki_AuditEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.root.model.impl.AuditEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.root.model.impl.AuditEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
@ProviderType
public interface AuditEntryModel
	extends AttachedModel, BaseModel<AuditEntry>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a audit entry model instance should use the {@link AuditEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this audit entry.
	 *
	 * @return the primary key of this audit entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this audit entry.
	 *
	 * @param primaryKey the primary key of this audit entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this audit entry.
	 *
	 * @return the mvcc version of this audit entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this audit entry.
	 *
	 * @param mvccVersion the mvcc version of this audit entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the audit entry ID of this audit entry.
	 *
	 * @return the audit entry ID of this audit entry
	 */
	public long getAuditEntryId();

	/**
	 * Sets the audit entry ID of this audit entry.
	 *
	 * @param auditEntryId the audit entry ID of this audit entry
	 */
	public void setAuditEntryId(long auditEntryId);

	/**
	 * Returns the company ID of this audit entry.
	 *
	 * @return the company ID of this audit entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this audit entry.
	 *
	 * @param companyId the company ID of this audit entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this audit entry.
	 *
	 * @return the user ID of this audit entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this audit entry.
	 *
	 * @param userId the user ID of this audit entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this audit entry.
	 *
	 * @return the user uuid of this audit entry
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this audit entry.
	 *
	 * @param userUuid the user uuid of this audit entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this audit entry.
	 *
	 * @return the create date of this audit entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this audit entry.
	 *
	 * @param createDate the create date of this audit entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this audit entry.
	 *
	 * @return the modified date of this audit entry
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this audit entry.
	 *
	 * @param modifiedDate the modified date of this audit entry
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the audit entry key of this audit entry.
	 *
	 * @return the audit entry key of this audit entry
	 */
	@AutoEscape
	public String getAuditEntryKey();

	/**
	 * Sets the audit entry key of this audit entry.
	 *
	 * @param auditEntryKey the audit entry key of this audit entry
	 */
	public void setAuditEntryKey(String auditEntryKey);

	/**
	 * Returns the agent name of this audit entry.
	 *
	 * @return the agent name of this audit entry
	 */
	@AutoEscape
	public String getAgentName();

	/**
	 * Sets the agent name of this audit entry.
	 *
	 * @param agentName the agent name of this audit entry
	 */
	public void setAgentName(String agentName);

	/**
	 * Returns the agent okta ID of this audit entry.
	 *
	 * @return the agent okta ID of this audit entry
	 */
	@AutoEscape
	public String getAgentOktaId();

	/**
	 * Sets the agent okta ID of this audit entry.
	 *
	 * @param agentOktaId the agent okta ID of this audit entry
	 */
	public void setAgentOktaId(String agentOktaId);

	/**
	 * Returns the fully qualified class name of this audit entry.
	 *
	 * @return the fully qualified class name of this audit entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this audit entry.
	 *
	 * @return the class name ID of this audit entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this audit entry.
	 *
	 * @param classNameId the class name ID of this audit entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this audit entry.
	 *
	 * @return the class pk of this audit entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this audit entry.
	 *
	 * @param classPK the class pk of this audit entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the audit set ID of this audit entry.
	 *
	 * @return the audit set ID of this audit entry
	 */
	public long getAuditSetId();

	/**
	 * Sets the audit set ID of this audit entry.
	 *
	 * @param auditSetId the audit set ID of this audit entry
	 */
	public void setAuditSetId(long auditSetId);

	/**
	 * Returns the field class name ID of this audit entry.
	 *
	 * @return the field class name ID of this audit entry
	 */
	public long getFieldClassNameId();

	/**
	 * Sets the field class name ID of this audit entry.
	 *
	 * @param fieldClassNameId the field class name ID of this audit entry
	 */
	public void setFieldClassNameId(long fieldClassNameId);

	/**
	 * Returns the field class pk of this audit entry.
	 *
	 * @return the field class pk of this audit entry
	 */
	public long getFieldClassPK();

	/**
	 * Sets the field class pk of this audit entry.
	 *
	 * @param fieldClassPK the field class pk of this audit entry
	 */
	public void setFieldClassPK(long fieldClassPK);

	/**
	 * Returns the action of this audit entry.
	 *
	 * @return the action of this audit entry
	 */
	@AutoEscape
	public String getAction();

	/**
	 * Sets the action of this audit entry.
	 *
	 * @param action the action of this audit entry
	 */
	public void setAction(String action);

	/**
	 * Returns the field of this audit entry.
	 *
	 * @return the field of this audit entry
	 */
	@AutoEscape
	public String getField();

	/**
	 * Sets the field of this audit entry.
	 *
	 * @param field the field of this audit entry
	 */
	public void setField(String field);

	/**
	 * Returns the old label of this audit entry.
	 *
	 * @return the old label of this audit entry
	 */
	@AutoEscape
	public String getOldLabel();

	/**
	 * Sets the old label of this audit entry.
	 *
	 * @param oldLabel the old label of this audit entry
	 */
	public void setOldLabel(String oldLabel);

	/**
	 * Returns the old value of this audit entry.
	 *
	 * @return the old value of this audit entry
	 */
	@AutoEscape
	public String getOldValue();

	/**
	 * Sets the old value of this audit entry.
	 *
	 * @param oldValue the old value of this audit entry
	 */
	public void setOldValue(String oldValue);

	/**
	 * Returns the new label of this audit entry.
	 *
	 * @return the new label of this audit entry
	 */
	@AutoEscape
	public String getNewLabel();

	/**
	 * Sets the new label of this audit entry.
	 *
	 * @param newLabel the new label of this audit entry
	 */
	public void setNewLabel(String newLabel);

	/**
	 * Returns the new value of this audit entry.
	 *
	 * @return the new value of this audit entry
	 */
	@AutoEscape
	public String getNewValue();

	/**
	 * Sets the new value of this audit entry.
	 *
	 * @param newValue the new value of this audit entry
	 */
	public void setNewValue(String newValue);

	/**
	 * Returns the description of this audit entry.
	 *
	 * @return the description of this audit entry
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this audit entry.
	 *
	 * @param description the description of this audit entry
	 */
	public void setDescription(String description);

}