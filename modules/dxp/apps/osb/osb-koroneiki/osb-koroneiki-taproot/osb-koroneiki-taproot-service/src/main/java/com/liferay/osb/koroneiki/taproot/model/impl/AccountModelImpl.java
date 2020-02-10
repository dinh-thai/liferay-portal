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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountModel;
import com.liferay.osb.koroneiki.taproot.model.AccountSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Account service. Represents a row in the &quot;Koroneiki_Account&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>AccountModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountImpl
 * @generated
 */
@JSON(strict = true)
public class AccountModelImpl
	extends BaseModelImpl<Account> implements AccountModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account model instance should use the <code>Account</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_Account";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"accountId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"accountKey", Types.VARCHAR},
		{"parentAccountId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"code_", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"notes", Types.VARCHAR}, {"logoId", Types.BIGINT},
		{"contactEmailAddress", Types.VARCHAR},
		{"profileEmailAddress", Types.VARCHAR}, {"phoneNumber", Types.VARCHAR},
		{"faxNumber", Types.VARCHAR}, {"website", Types.VARCHAR},
		{"tier", Types.VARCHAR}, {"soldBy", Types.VARCHAR},
		{"internal_", Types.BOOLEAN}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}, {"statusMessage", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accountKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("parentAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notes", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contactEmailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("profileEmailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("phoneNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("faxNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("website", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tier", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("soldBy", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("internal_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_Account (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,accountId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,accountKey VARCHAR(75) null,parentAccountId LONG,name VARCHAR(150) null,code_ VARCHAR(75) null,description STRING null,notes STRING null,logoId LONG,contactEmailAddress VARCHAR(75) null,profileEmailAddress VARCHAR(75) null,phoneNumber VARCHAR(75) null,faxNumber VARCHAR(75) null,website VARCHAR(75) null,tier VARCHAR(75) null,soldBy VARCHAR(75) null,internal_ BOOLEAN,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,statusMessage VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Koroneiki_Account";

	public static final String ORDER_BY_JPQL =
		" ORDER BY account.accountId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_Account.accountId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTKEY_COLUMN_BITMASK = 1L;

	public static final long CODE_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long PARENTACCOUNTID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long ACCOUNTID_COLUMN_BITMASK = 64L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Account toModel(AccountSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Account model = new AccountImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setAccountId(soapModel.getAccountId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAccountKey(soapModel.getAccountKey());
		model.setParentAccountId(soapModel.getParentAccountId());
		model.setName(soapModel.getName());
		model.setCode(soapModel.getCode());
		model.setDescription(soapModel.getDescription());
		model.setNotes(soapModel.getNotes());
		model.setLogoId(soapModel.getLogoId());
		model.setContactEmailAddress(soapModel.getContactEmailAddress());
		model.setProfileEmailAddress(soapModel.getProfileEmailAddress());
		model.setPhoneNumber(soapModel.getPhoneNumber());
		model.setFaxNumber(soapModel.getFaxNumber());
		model.setWebsite(soapModel.getWebsite());
		model.setTier(soapModel.getTier());
		model.setSoldBy(soapModel.getSoldBy());
		model.setInternal(soapModel.isInternal());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setStatusMessage(soapModel.getStatusMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Account> toModels(AccountSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Account> models = new ArrayList<Account>(soapModels.length);

		for (AccountSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AccountModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Account.class;
	}

	@Override
	public String getModelClassName() {
		return Account.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Account)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Account, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Account, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Account)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Account, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Account, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Account>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Account.class.getClassLoader(), Account.class, ModelWrapper.class);

		try {
			Constructor<Account> constructor =
				(Constructor<Account>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Account, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Account, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Account, Object>>();
		Map<String, BiConsumer<Account, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Account, ?>>();

		attributeGetterFunctions.put("mvccVersion", Account::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<Account, Long>)Account::setMvccVersion);
		attributeGetterFunctions.put("uuid", Account::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Account, String>)Account::setUuid);
		attributeGetterFunctions.put("accountId", Account::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId", (BiConsumer<Account, Long>)Account::setAccountId);
		attributeGetterFunctions.put("companyId", Account::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Account, Long>)Account::setCompanyId);
		attributeGetterFunctions.put("userId", Account::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Account, Long>)Account::setUserId);
		attributeGetterFunctions.put("createDate", Account::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Account, Date>)Account::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Account::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Account, Date>)Account::setModifiedDate);
		attributeGetterFunctions.put("accountKey", Account::getAccountKey);
		attributeSetterBiConsumers.put(
			"accountKey", (BiConsumer<Account, String>)Account::setAccountKey);
		attributeGetterFunctions.put(
			"parentAccountId", Account::getParentAccountId);
		attributeSetterBiConsumers.put(
			"parentAccountId",
			(BiConsumer<Account, Long>)Account::setParentAccountId);
		attributeGetterFunctions.put("name", Account::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Account, String>)Account::setName);
		attributeGetterFunctions.put("code", Account::getCode);
		attributeSetterBiConsumers.put(
			"code", (BiConsumer<Account, String>)Account::setCode);
		attributeGetterFunctions.put("description", Account::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<Account, String>)Account::setDescription);
		attributeGetterFunctions.put("notes", Account::getNotes);
		attributeSetterBiConsumers.put(
			"notes", (BiConsumer<Account, String>)Account::setNotes);
		attributeGetterFunctions.put("logoId", Account::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId", (BiConsumer<Account, Long>)Account::setLogoId);
		attributeGetterFunctions.put(
			"contactEmailAddress", Account::getContactEmailAddress);
		attributeSetterBiConsumers.put(
			"contactEmailAddress",
			(BiConsumer<Account, String>)Account::setContactEmailAddress);
		attributeGetterFunctions.put(
			"profileEmailAddress", Account::getProfileEmailAddress);
		attributeSetterBiConsumers.put(
			"profileEmailAddress",
			(BiConsumer<Account, String>)Account::setProfileEmailAddress);
		attributeGetterFunctions.put("phoneNumber", Account::getPhoneNumber);
		attributeSetterBiConsumers.put(
			"phoneNumber",
			(BiConsumer<Account, String>)Account::setPhoneNumber);
		attributeGetterFunctions.put("faxNumber", Account::getFaxNumber);
		attributeSetterBiConsumers.put(
			"faxNumber", (BiConsumer<Account, String>)Account::setFaxNumber);
		attributeGetterFunctions.put("website", Account::getWebsite);
		attributeSetterBiConsumers.put(
			"website", (BiConsumer<Account, String>)Account::setWebsite);
		attributeGetterFunctions.put("tier", Account::getTier);
		attributeSetterBiConsumers.put(
			"tier", (BiConsumer<Account, String>)Account::setTier);
		attributeGetterFunctions.put("soldBy", Account::getSoldBy);
		attributeSetterBiConsumers.put(
			"soldBy", (BiConsumer<Account, String>)Account::setSoldBy);
		attributeGetterFunctions.put("internal", Account::getInternal);
		attributeSetterBiConsumers.put(
			"internal", (BiConsumer<Account, Boolean>)Account::setInternal);
		attributeGetterFunctions.put("status", Account::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<Account, Integer>)Account::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", Account::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<Account, Long>)Account::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", Account::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<Account, String>)Account::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", Account::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate", (BiConsumer<Account, Date>)Account::setStatusDate);
		attributeGetterFunctions.put(
			"statusMessage", Account::getStatusMessage);
		attributeSetterBiConsumers.put(
			"statusMessage",
			(BiConsumer<Account, String>)Account::setStatusMessage);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getAccountKey() {
		if (_accountKey == null) {
			return "";
		}
		else {
			return _accountKey;
		}
	}

	@Override
	public void setAccountKey(String accountKey) {
		_columnBitmask |= ACCOUNTKEY_COLUMN_BITMASK;

		if (_originalAccountKey == null) {
			_originalAccountKey = _accountKey;
		}

		_accountKey = accountKey;
	}

	public String getOriginalAccountKey() {
		return GetterUtil.getString(_originalAccountKey);
	}

	@JSON
	@Override
	public long getParentAccountId() {
		return _parentAccountId;
	}

	@Override
	public void setParentAccountId(long parentAccountId) {
		_columnBitmask |= PARENTACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalParentAccountId) {
			_setOriginalParentAccountId = true;

			_originalParentAccountId = _parentAccountId;
		}

		_parentAccountId = parentAccountId;
	}

	public long getOriginalParentAccountId() {
		return _originalParentAccountId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		_columnBitmask |= CODE_COLUMN_BITMASK;

		if (_originalCode == null) {
			_originalCode = _code;
		}

		_code = code;
	}

	public String getOriginalCode() {
		return GetterUtil.getString(_originalCode);
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getNotes() {
		if (_notes == null) {
			return "";
		}
		else {
			return _notes;
		}
	}

	@Override
	public void setNotes(String notes) {
		_notes = notes;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	@JSON
	@Override
	public String getContactEmailAddress() {
		if (_contactEmailAddress == null) {
			return "";
		}
		else {
			return _contactEmailAddress;
		}
	}

	@Override
	public void setContactEmailAddress(String contactEmailAddress) {
		_contactEmailAddress = contactEmailAddress;
	}

	@JSON
	@Override
	public String getProfileEmailAddress() {
		if (_profileEmailAddress == null) {
			return "";
		}
		else {
			return _profileEmailAddress;
		}
	}

	@Override
	public void setProfileEmailAddress(String profileEmailAddress) {
		_profileEmailAddress = profileEmailAddress;
	}

	@JSON
	@Override
	public String getPhoneNumber() {
		if (_phoneNumber == null) {
			return "";
		}
		else {
			return _phoneNumber;
		}
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	@JSON
	@Override
	public String getFaxNumber() {
		if (_faxNumber == null) {
			return "";
		}
		else {
			return _faxNumber;
		}
	}

	@Override
	public void setFaxNumber(String faxNumber) {
		_faxNumber = faxNumber;
	}

	@JSON
	@Override
	public String getWebsite() {
		if (_website == null) {
			return "";
		}
		else {
			return _website;
		}
	}

	@Override
	public void setWebsite(String website) {
		_website = website;
	}

	@JSON
	@Override
	public String getTier() {
		if (_tier == null) {
			return "";
		}
		else {
			return _tier;
		}
	}

	@Override
	public void setTier(String tier) {
		_tier = tier;
	}

	@JSON
	@Override
	public String getSoldBy() {
		if (_soldBy == null) {
			return "";
		}
		else {
			return _soldBy;
		}
	}

	@Override
	public void setSoldBy(String soldBy) {
		_soldBy = soldBy;
	}

	@JSON
	@Override
	public boolean getInternal() {
		return _internal;
	}

	@JSON
	@Override
	public boolean isInternal() {
		return _internal;
	}

	@Override
	public void setInternal(boolean internal) {
		_internal = internal;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getStatusMessage() {
		if (_statusMessage == null) {
			return "";
		}
		else {
			return _statusMessage;
		}
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Account.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Account.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Account toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Account>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AccountImpl accountImpl = new AccountImpl();

		accountImpl.setMvccVersion(getMvccVersion());
		accountImpl.setUuid(getUuid());
		accountImpl.setAccountId(getAccountId());
		accountImpl.setCompanyId(getCompanyId());
		accountImpl.setUserId(getUserId());
		accountImpl.setCreateDate(getCreateDate());
		accountImpl.setModifiedDate(getModifiedDate());
		accountImpl.setAccountKey(getAccountKey());
		accountImpl.setParentAccountId(getParentAccountId());
		accountImpl.setName(getName());
		accountImpl.setCode(getCode());
		accountImpl.setDescription(getDescription());
		accountImpl.setNotes(getNotes());
		accountImpl.setLogoId(getLogoId());
		accountImpl.setContactEmailAddress(getContactEmailAddress());
		accountImpl.setProfileEmailAddress(getProfileEmailAddress());
		accountImpl.setPhoneNumber(getPhoneNumber());
		accountImpl.setFaxNumber(getFaxNumber());
		accountImpl.setWebsite(getWebsite());
		accountImpl.setTier(getTier());
		accountImpl.setSoldBy(getSoldBy());
		accountImpl.setInternal(isInternal());
		accountImpl.setStatus(getStatus());
		accountImpl.setStatusByUserId(getStatusByUserId());
		accountImpl.setStatusByUserName(getStatusByUserName());
		accountImpl.setStatusDate(getStatusDate());
		accountImpl.setStatusMessage(getStatusMessage());

		accountImpl.resetOriginalValues();

		return accountImpl;
	}

	@Override
	public int compareTo(Account account) {
		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Account)) {
			return false;
		}

		Account account = (Account)obj;

		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		AccountModelImpl accountModelImpl = this;

		accountModelImpl._originalUuid = accountModelImpl._uuid;

		accountModelImpl._originalCompanyId = accountModelImpl._companyId;

		accountModelImpl._setOriginalCompanyId = false;

		accountModelImpl._setModifiedDate = false;

		accountModelImpl._originalAccountKey = accountModelImpl._accountKey;

		accountModelImpl._originalParentAccountId =
			accountModelImpl._parentAccountId;

		accountModelImpl._setOriginalParentAccountId = false;

		accountModelImpl._originalName = accountModelImpl._name;

		accountModelImpl._originalCode = accountModelImpl._code;

		accountModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Account> toCacheModel() {
		AccountCacheModel accountCacheModel = new AccountCacheModel();

		accountCacheModel.mvccVersion = getMvccVersion();

		accountCacheModel.uuid = getUuid();

		String uuid = accountCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			accountCacheModel.uuid = null;
		}

		accountCacheModel.accountId = getAccountId();

		accountCacheModel.companyId = getCompanyId();

		accountCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			accountCacheModel.createDate = createDate.getTime();
		}
		else {
			accountCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			accountCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			accountCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		accountCacheModel.accountKey = getAccountKey();

		String accountKey = accountCacheModel.accountKey;

		if ((accountKey != null) && (accountKey.length() == 0)) {
			accountCacheModel.accountKey = null;
		}

		accountCacheModel.parentAccountId = getParentAccountId();

		accountCacheModel.name = getName();

		String name = accountCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			accountCacheModel.name = null;
		}

		accountCacheModel.code = getCode();

		String code = accountCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			accountCacheModel.code = null;
		}

		accountCacheModel.description = getDescription();

		String description = accountCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			accountCacheModel.description = null;
		}

		accountCacheModel.notes = getNotes();

		String notes = accountCacheModel.notes;

		if ((notes != null) && (notes.length() == 0)) {
			accountCacheModel.notes = null;
		}

		accountCacheModel.logoId = getLogoId();

		accountCacheModel.contactEmailAddress = getContactEmailAddress();

		String contactEmailAddress = accountCacheModel.contactEmailAddress;

		if ((contactEmailAddress != null) &&
			(contactEmailAddress.length() == 0)) {

			accountCacheModel.contactEmailAddress = null;
		}

		accountCacheModel.profileEmailAddress = getProfileEmailAddress();

		String profileEmailAddress = accountCacheModel.profileEmailAddress;

		if ((profileEmailAddress != null) &&
			(profileEmailAddress.length() == 0)) {

			accountCacheModel.profileEmailAddress = null;
		}

		accountCacheModel.phoneNumber = getPhoneNumber();

		String phoneNumber = accountCacheModel.phoneNumber;

		if ((phoneNumber != null) && (phoneNumber.length() == 0)) {
			accountCacheModel.phoneNumber = null;
		}

		accountCacheModel.faxNumber = getFaxNumber();

		String faxNumber = accountCacheModel.faxNumber;

		if ((faxNumber != null) && (faxNumber.length() == 0)) {
			accountCacheModel.faxNumber = null;
		}

		accountCacheModel.website = getWebsite();

		String website = accountCacheModel.website;

		if ((website != null) && (website.length() == 0)) {
			accountCacheModel.website = null;
		}

		accountCacheModel.tier = getTier();

		String tier = accountCacheModel.tier;

		if ((tier != null) && (tier.length() == 0)) {
			accountCacheModel.tier = null;
		}

		accountCacheModel.soldBy = getSoldBy();

		String soldBy = accountCacheModel.soldBy;

		if ((soldBy != null) && (soldBy.length() == 0)) {
			accountCacheModel.soldBy = null;
		}

		accountCacheModel.internal = isInternal();

		accountCacheModel.status = getStatus();

		accountCacheModel.statusByUserId = getStatusByUserId();

		accountCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = accountCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			accountCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			accountCacheModel.statusDate = statusDate.getTime();
		}
		else {
			accountCacheModel.statusDate = Long.MIN_VALUE;
		}

		accountCacheModel.statusMessage = getStatusMessage();

		String statusMessage = accountCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			accountCacheModel.statusMessage = null;
		}

		return accountCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Account)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Account)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Account>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _accountId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _accountKey;
	private String _originalAccountKey;
	private long _parentAccountId;
	private long _originalParentAccountId;
	private boolean _setOriginalParentAccountId;
	private String _name;
	private String _originalName;
	private String _code;
	private String _originalCode;
	private String _description;
	private String _notes;
	private long _logoId;
	private String _contactEmailAddress;
	private String _profileEmailAddress;
	private String _phoneNumber;
	private String _faxNumber;
	private String _website;
	private String _tier;
	private String _soldBy;
	private boolean _internal;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private long _columnBitmask;
	private Account _escapedModel;

}