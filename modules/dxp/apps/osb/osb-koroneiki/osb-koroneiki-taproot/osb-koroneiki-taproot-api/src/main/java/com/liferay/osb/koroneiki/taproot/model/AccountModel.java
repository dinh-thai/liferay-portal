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

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Account service. Represents a row in the &quot;Koroneiki_Account&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Account
 * @generated
 */
@ProviderType
public interface AccountModel
	extends BaseModel<Account>, ShardedModel, StagedModel, WorkflowedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a account model instance should use the {@link Account} interface instead.
	 */

	/**
	 * Returns the primary key of this account.
	 *
	 * @return the primary key of this account
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this account.
	 *
	 * @param primaryKey the primary key of this account
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this account.
	 *
	 * @return the uuid of this account
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this account.
	 *
	 * @param uuid the uuid of this account
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the account ID of this account.
	 *
	 * @return the account ID of this account
	 */
	public long getAccountId();

	/**
	 * Sets the account ID of this account.
	 *
	 * @param accountId the account ID of this account
	 */
	public void setAccountId(long accountId);

	/**
	 * Returns the company ID of this account.
	 *
	 * @return the company ID of this account
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this account.
	 *
	 * @param companyId the company ID of this account
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this account.
	 *
	 * @return the user ID of this account
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this account.
	 *
	 * @param userId the user ID of this account
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this account.
	 *
	 * @return the user uuid of this account
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this account.
	 *
	 * @param userUuid the user uuid of this account
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this account.
	 *
	 * @return the create date of this account
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this account.
	 *
	 * @param createDate the create date of this account
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this account.
	 *
	 * @return the modified date of this account
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this account.
	 *
	 * @param modifiedDate the modified date of this account
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the account key of this account.
	 *
	 * @return the account key of this account
	 */
	@AutoEscape
	public String getAccountKey();

	/**
	 * Sets the account key of this account.
	 *
	 * @param accountKey the account key of this account
	 */
	public void setAccountKey(String accountKey);

	/**
	 * Returns the parent account ID of this account.
	 *
	 * @return the parent account ID of this account
	 */
	public long getParentAccountId();

	/**
	 * Sets the parent account ID of this account.
	 *
	 * @param parentAccountId the parent account ID of this account
	 */
	public void setParentAccountId(long parentAccountId);

	/**
	 * Returns the name of this account.
	 *
	 * @return the name of this account
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this account.
	 *
	 * @param name the name of this account
	 */
	public void setName(String name);

	/**
	 * Returns the code of this account.
	 *
	 * @return the code of this account
	 */
	@AutoEscape
	public String getCode();

	/**
	 * Sets the code of this account.
	 *
	 * @param code the code of this account
	 */
	public void setCode(String code);

	/**
	 * Returns the description of this account.
	 *
	 * @return the description of this account
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this account.
	 *
	 * @param description the description of this account
	 */
	public void setDescription(String description);

	/**
	 * Returns the notes of this account.
	 *
	 * @return the notes of this account
	 */
	@AutoEscape
	public String getNotes();

	/**
	 * Sets the notes of this account.
	 *
	 * @param notes the notes of this account
	 */
	public void setNotes(String notes);

	/**
	 * Returns the logo ID of this account.
	 *
	 * @return the logo ID of this account
	 */
	public long getLogoId();

	/**
	 * Sets the logo ID of this account.
	 *
	 * @param logoId the logo ID of this account
	 */
	public void setLogoId(long logoId);

	/**
	 * Returns the contact email address of this account.
	 *
	 * @return the contact email address of this account
	 */
	@AutoEscape
	public String getContactEmailAddress();

	/**
	 * Sets the contact email address of this account.
	 *
	 * @param contactEmailAddress the contact email address of this account
	 */
	public void setContactEmailAddress(String contactEmailAddress);

	/**
	 * Returns the profile email address of this account.
	 *
	 * @return the profile email address of this account
	 */
	@AutoEscape
	public String getProfileEmailAddress();

	/**
	 * Sets the profile email address of this account.
	 *
	 * @param profileEmailAddress the profile email address of this account
	 */
	public void setProfileEmailAddress(String profileEmailAddress);

	/**
	 * Returns the phone number of this account.
	 *
	 * @return the phone number of this account
	 */
	@AutoEscape
	public String getPhoneNumber();

	/**
	 * Sets the phone number of this account.
	 *
	 * @param phoneNumber the phone number of this account
	 */
	public void setPhoneNumber(String phoneNumber);

	/**
	 * Returns the fax number of this account.
	 *
	 * @return the fax number of this account
	 */
	@AutoEscape
	public String getFaxNumber();

	/**
	 * Sets the fax number of this account.
	 *
	 * @param faxNumber the fax number of this account
	 */
	public void setFaxNumber(String faxNumber);

	/**
	 * Returns the website of this account.
	 *
	 * @return the website of this account
	 */
	@AutoEscape
	public String getWebsite();

	/**
	 * Sets the website of this account.
	 *
	 * @param website the website of this account
	 */
	public void setWebsite(String website);

	/**
	 * Returns the industry of this account.
	 *
	 * @return the industry of this account
	 */
	@AutoEscape
	public String getIndustry();

	/**
	 * Sets the industry of this account.
	 *
	 * @param industry the industry of this account
	 */
	public void setIndustry(String industry);

	/**
	 * Returns the tier of this account.
	 *
	 * @return the tier of this account
	 */
	@AutoEscape
	public String getTier();

	/**
	 * Sets the tier of this account.
	 *
	 * @param tier the tier of this account
	 */
	public void setTier(String tier);

	/**
	 * Returns the sold by of this account.
	 *
	 * @return the sold by of this account
	 */
	@AutoEscape
	public String getSoldBy();

	/**
	 * Sets the sold by of this account.
	 *
	 * @param soldBy the sold by of this account
	 */
	public void setSoldBy(String soldBy);

	/**
	 * Returns the internal of this account.
	 *
	 * @return the internal of this account
	 */
	public boolean getInternal();

	/**
	 * Returns <code>true</code> if this account is internal.
	 *
	 * @return <code>true</code> if this account is internal; <code>false</code> otherwise
	 */
	public boolean isInternal();

	/**
	 * Sets whether this account is internal.
	 *
	 * @param internal the internal of this account
	 */
	public void setInternal(boolean internal);

	/**
	 * Returns the status of this account.
	 *
	 * @return the status of this account
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this account.
	 *
	 * @param status the status of this account
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this account.
	 *
	 * @return the status by user ID of this account
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this account.
	 *
	 * @param statusByUserId the status by user ID of this account
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this account.
	 *
	 * @return the status by user uuid of this account
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this account.
	 *
	 * @param statusByUserUuid the status by user uuid of this account
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this account.
	 *
	 * @return the status by user name of this account
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this account.
	 *
	 * @param statusByUserName the status by user name of this account
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this account.
	 *
	 * @return the status date of this account
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this account.
	 *
	 * @param statusDate the status date of this account
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the status message of this account.
	 *
	 * @return the status message of this account
	 */
	@AutoEscape
	public String getStatusMessage();

	/**
	 * Sets the status message of this account.
	 *
	 * @param statusMessage the status message of this account
	 */
	public void setStatusMessage(String statusMessage);

	/**
	 * Returns <code>true</code> if this account is approved.
	 *
	 * @return <code>true</code> if this account is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this account is denied.
	 *
	 * @return <code>true</code> if this account is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this account is a draft.
	 *
	 * @return <code>true</code> if this account is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this account is expired.
	 *
	 * @return <code>true</code> if this account is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this account is inactive.
	 *
	 * @return <code>true</code> if this account is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this account is incomplete.
	 *
	 * @return <code>true</code> if this account is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this account is pending.
	 *
	 * @return <code>true</code> if this account is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this account is scheduled.
	 *
	 * @return <code>true</code> if this account is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

}