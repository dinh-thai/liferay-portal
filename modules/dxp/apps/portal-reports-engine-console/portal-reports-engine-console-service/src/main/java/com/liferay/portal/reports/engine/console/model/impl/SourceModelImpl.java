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

package com.liferay.portal.reports.engine.console.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.model.SourceModel;
import com.liferay.portal.reports.engine.console.model.SourceSoap;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Source service. Represents a row in the &quot;Reports_Source&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SourceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SourceImpl
 * @generated
 */
@JSON(strict = true)
public class SourceModelImpl
	extends BaseModelImpl<Source> implements SourceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a source model instance should use the <code>Source</code> interface instead.
	 */
	public static final String TABLE_NAME = "Reports_Source";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"sourceId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"lastPublishDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"driverClassName", Types.VARCHAR}, {"driverUrl", Types.VARCHAR},
		{"driverUserName", Types.VARCHAR}, {"driverPassword", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sourceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverPassword", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Reports_Source (uuid_ VARCHAR(75) null,sourceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,name STRING null,driverClassName VARCHAR(75) null,driverUrl STRING null,driverUserName VARCHAR(75) null,driverPassword VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Reports_Source";

	public static final String ORDER_BY_JPQL = " ORDER BY source.sourceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Reports_Source.sourceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long SOURCEID_COLUMN_BITMASK = 8L;

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
	public static Source toModel(SourceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Source model = new SourceImpl();

		model.setUuid(soapModel.getUuid());
		model.setSourceId(soapModel.getSourceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setName(soapModel.getName());
		model.setDriverClassName(soapModel.getDriverClassName());
		model.setDriverUrl(soapModel.getDriverUrl());
		model.setDriverUserName(soapModel.getDriverUserName());
		model.setDriverPassword(soapModel.getDriverPassword());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Source> toModels(SourceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Source> models = new ArrayList<Source>(soapModels.length);

		for (SourceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SourceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sourceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSourceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sourceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Source.class;
	}

	@Override
	public String getModelClassName() {
		return Source.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Source, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Source, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Source, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Source)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Source, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Source, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Source)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Source, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Source, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Source>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Source.class.getClassLoader(), Source.class, ModelWrapper.class);

		try {
			Constructor<Source> constructor =
				(Constructor<Source>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Source, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Source, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Source, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Source, Object>>();
		Map<String, BiConsumer<Source, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Source, ?>>();

		attributeGetterFunctions.put("uuid", Source::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Source, String>)Source::setUuid);
		attributeGetterFunctions.put("sourceId", Source::getSourceId);
		attributeSetterBiConsumers.put(
			"sourceId", (BiConsumer<Source, Long>)Source::setSourceId);
		attributeGetterFunctions.put("groupId", Source::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<Source, Long>)Source::setGroupId);
		attributeGetterFunctions.put("companyId", Source::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Source, Long>)Source::setCompanyId);
		attributeGetterFunctions.put("userId", Source::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Source, Long>)Source::setUserId);
		attributeGetterFunctions.put("userName", Source::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Source, String>)Source::setUserName);
		attributeGetterFunctions.put("createDate", Source::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Source, Date>)Source::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Source::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Source, Date>)Source::setModifiedDate);
		attributeGetterFunctions.put(
			"lastPublishDate", Source::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<Source, Date>)Source::setLastPublishDate);
		attributeGetterFunctions.put("name", Source::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Source, String>)Source::setName);
		attributeGetterFunctions.put(
			"driverClassName", Source::getDriverClassName);
		attributeSetterBiConsumers.put(
			"driverClassName",
			(BiConsumer<Source, String>)Source::setDriverClassName);
		attributeGetterFunctions.put("driverUrl", Source::getDriverUrl);
		attributeSetterBiConsumers.put(
			"driverUrl", (BiConsumer<Source, String>)Source::setDriverUrl);
		attributeGetterFunctions.put(
			"driverUserName", Source::getDriverUserName);
		attributeSetterBiConsumers.put(
			"driverUserName",
			(BiConsumer<Source, String>)Source::setDriverUserName);
		attributeGetterFunctions.put(
			"driverPassword", Source::getDriverPassword);
		attributeSetterBiConsumers.put(
			"driverPassword",
			(BiConsumer<Source, String>)Source::setDriverPassword);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getSourceId() {
		return _sourceId;
	}

	@Override
	public void setSourceId(long sourceId) {
		_sourceId = sourceId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
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
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDriverClassName() {
		if (_driverClassName == null) {
			return "";
		}
		else {
			return _driverClassName;
		}
	}

	@Override
	public void setDriverClassName(String driverClassName) {
		_driverClassName = driverClassName;
	}

	@JSON
	@Override
	public String getDriverUrl() {
		if (_driverUrl == null) {
			return "";
		}
		else {
			return _driverUrl;
		}
	}

	@Override
	public void setDriverUrl(String driverUrl) {
		_driverUrl = driverUrl;
	}

	@JSON
	@Override
	public String getDriverUserName() {
		if (_driverUserName == null) {
			return "";
		}
		else {
			return _driverUserName;
		}
	}

	@Override
	public void setDriverUserName(String driverUserName) {
		_driverUserName = driverUserName;
	}

	@JSON
	@Override
	public String getDriverPassword() {
		if (_driverPassword == null) {
			return "";
		}
		else {
			return _driverPassword;
		}
	}

	@Override
	public void setDriverPassword(String driverPassword) {
		_driverPassword = driverPassword;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Source.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Source.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			Source.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Source toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Source>
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
		SourceImpl sourceImpl = new SourceImpl();

		sourceImpl.setUuid(getUuid());
		sourceImpl.setSourceId(getSourceId());
		sourceImpl.setGroupId(getGroupId());
		sourceImpl.setCompanyId(getCompanyId());
		sourceImpl.setUserId(getUserId());
		sourceImpl.setUserName(getUserName());
		sourceImpl.setCreateDate(getCreateDate());
		sourceImpl.setModifiedDate(getModifiedDate());
		sourceImpl.setLastPublishDate(getLastPublishDate());
		sourceImpl.setName(getName());
		sourceImpl.setDriverClassName(getDriverClassName());
		sourceImpl.setDriverUrl(getDriverUrl());
		sourceImpl.setDriverUserName(getDriverUserName());
		sourceImpl.setDriverPassword(getDriverPassword());

		sourceImpl.resetOriginalValues();

		return sourceImpl;
	}

	@Override
	public int compareTo(Source source) {
		long primaryKey = source.getPrimaryKey();

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

		if (!(obj instanceof Source)) {
			return false;
		}

		Source source = (Source)obj;

		long primaryKey = source.getPrimaryKey();

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
		SourceModelImpl sourceModelImpl = this;

		sourceModelImpl._originalUuid = sourceModelImpl._uuid;

		sourceModelImpl._originalGroupId = sourceModelImpl._groupId;

		sourceModelImpl._setOriginalGroupId = false;

		sourceModelImpl._originalCompanyId = sourceModelImpl._companyId;

		sourceModelImpl._setOriginalCompanyId = false;

		sourceModelImpl._setModifiedDate = false;

		sourceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Source> toCacheModel() {
		SourceCacheModel sourceCacheModel = new SourceCacheModel();

		sourceCacheModel.uuid = getUuid();

		String uuid = sourceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			sourceCacheModel.uuid = null;
		}

		sourceCacheModel.sourceId = getSourceId();

		sourceCacheModel.groupId = getGroupId();

		sourceCacheModel.companyId = getCompanyId();

		sourceCacheModel.userId = getUserId();

		sourceCacheModel.userName = getUserName();

		String userName = sourceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			sourceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			sourceCacheModel.createDate = createDate.getTime();
		}
		else {
			sourceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			sourceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			sourceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			sourceCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			sourceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		sourceCacheModel.name = getName();

		String name = sourceCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			sourceCacheModel.name = null;
		}

		sourceCacheModel.driverClassName = getDriverClassName();

		String driverClassName = sourceCacheModel.driverClassName;

		if ((driverClassName != null) && (driverClassName.length() == 0)) {
			sourceCacheModel.driverClassName = null;
		}

		sourceCacheModel.driverUrl = getDriverUrl();

		String driverUrl = sourceCacheModel.driverUrl;

		if ((driverUrl != null) && (driverUrl.length() == 0)) {
			sourceCacheModel.driverUrl = null;
		}

		sourceCacheModel.driverUserName = getDriverUserName();

		String driverUserName = sourceCacheModel.driverUserName;

		if ((driverUserName != null) && (driverUserName.length() == 0)) {
			sourceCacheModel.driverUserName = null;
		}

		sourceCacheModel.driverPassword = getDriverPassword();

		String driverPassword = sourceCacheModel.driverPassword;

		if ((driverPassword != null) && (driverPassword.length() == 0)) {
			sourceCacheModel.driverPassword = null;
		}

		return sourceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Source, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Source, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Source, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Source)this));
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
		Map<String, Function<Source, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Source, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Source, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Source)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Source>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _sourceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private Date _lastPublishDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _driverClassName;
	private String _driverUrl;
	private String _driverUserName;
	private String _driverPassword;
	private long _columnBitmask;
	private Source _escapedModel;

}