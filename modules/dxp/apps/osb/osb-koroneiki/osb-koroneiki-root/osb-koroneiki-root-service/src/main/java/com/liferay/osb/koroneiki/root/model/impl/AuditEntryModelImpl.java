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

package com.liferay.osb.koroneiki.root.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.osb.koroneiki.root.model.AuditEntryModel;
import com.liferay.osb.koroneiki.root.model.AuditEntrySoap;
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
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the AuditEntry service. Represents a row in the &quot;Koroneiki_AuditEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AuditEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AuditEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryImpl
 * @generated
 */
@JSON(strict = true)
public class AuditEntryModelImpl
	extends BaseModelImpl<AuditEntry> implements AuditEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a audit entry model instance should use the <code>AuditEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_AuditEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"auditEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"auditEntryKey", Types.VARCHAR}, {"agentName", Types.VARCHAR},
		{"agentOktaId", Types.VARCHAR}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"auditSetId", Types.BIGINT},
		{"fieldClassNameId", Types.BIGINT}, {"fieldClassPK", Types.BIGINT},
		{"action", Types.VARCHAR}, {"field", Types.VARCHAR},
		{"oldLabel", Types.VARCHAR}, {"oldValue", Types.VARCHAR},
		{"newLabel", Types.VARCHAR}, {"newValue", Types.VARCHAR},
		{"description", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("auditEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("auditEntryKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("agentName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("agentOktaId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("auditSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fieldClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fieldClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("action", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("field", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("oldLabel", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("oldValue", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newLabel", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newValue", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_AuditEntry (mvccVersion LONG default 0 not null,auditEntryId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,auditEntryKey VARCHAR(75) null,agentName VARCHAR(75) null,agentOktaId VARCHAR(75) null,classNameId LONG,classPK LONG,auditSetId LONG,fieldClassNameId LONG,fieldClassPK LONG,action VARCHAR(75) null,field VARCHAR(75) null,oldLabel VARCHAR(255) null,oldValue STRING null,newLabel VARCHAR(255) null,newValue STRING null,description STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_AuditEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY auditEntry.auditEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_AuditEntry.auditEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long AUDITENTRYKEY_COLUMN_BITMASK = 1L;

	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	public static final long CLASSPK_COLUMN_BITMASK = 4L;

	public static final long FIELDCLASSNAMEID_COLUMN_BITMASK = 8L;

	public static final long FIELDCLASSPK_COLUMN_BITMASK = 16L;

	public static final long AUDITENTRYID_COLUMN_BITMASK = 32L;

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
	public static AuditEntry toModel(AuditEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AuditEntry model = new AuditEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setAuditEntryId(soapModel.getAuditEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAuditEntryKey(soapModel.getAuditEntryKey());
		model.setAgentName(soapModel.getAgentName());
		model.setAgentOktaId(soapModel.getAgentOktaId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setAuditSetId(soapModel.getAuditSetId());
		model.setFieldClassNameId(soapModel.getFieldClassNameId());
		model.setFieldClassPK(soapModel.getFieldClassPK());
		model.setAction(soapModel.getAction());
		model.setField(soapModel.getField());
		model.setOldLabel(soapModel.getOldLabel());
		model.setOldValue(soapModel.getOldValue());
		model.setNewLabel(soapModel.getNewLabel());
		model.setNewValue(soapModel.getNewValue());
		model.setDescription(soapModel.getDescription());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AuditEntry> toModels(AuditEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AuditEntry> models = new ArrayList<AuditEntry>(soapModels.length);

		for (AuditEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AuditEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _auditEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAuditEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AuditEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AuditEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AuditEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AuditEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuditEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((AuditEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AuditEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AuditEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AuditEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AuditEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AuditEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AuditEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AuditEntry.class.getClassLoader(), AuditEntry.class,
			ModelWrapper.class);

		try {
			Constructor<AuditEntry> constructor =
				(Constructor<AuditEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<AuditEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AuditEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AuditEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AuditEntry, Object>>();
		Map<String, BiConsumer<AuditEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AuditEntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", AuditEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"auditEntryId", AuditEntry::getAuditEntryId);
		attributeSetterBiConsumers.put(
			"auditEntryId",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setAuditEntryId);
		attributeGetterFunctions.put("companyId", AuditEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setCompanyId);
		attributeGetterFunctions.put("userId", AuditEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<AuditEntry, Long>)AuditEntry::setUserId);
		attributeGetterFunctions.put("createDate", AuditEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AuditEntry, Date>)AuditEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AuditEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AuditEntry, Date>)AuditEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"auditEntryKey", AuditEntry::getAuditEntryKey);
		attributeSetterBiConsumers.put(
			"auditEntryKey",
			(BiConsumer<AuditEntry, String>)AuditEntry::setAuditEntryKey);
		attributeGetterFunctions.put("agentName", AuditEntry::getAgentName);
		attributeSetterBiConsumers.put(
			"agentName",
			(BiConsumer<AuditEntry, String>)AuditEntry::setAgentName);
		attributeGetterFunctions.put("agentOktaId", AuditEntry::getAgentOktaId);
		attributeSetterBiConsumers.put(
			"agentOktaId",
			(BiConsumer<AuditEntry, String>)AuditEntry::setAgentOktaId);
		attributeGetterFunctions.put("classNameId", AuditEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", AuditEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<AuditEntry, Long>)AuditEntry::setClassPK);
		attributeGetterFunctions.put("auditSetId", AuditEntry::getAuditSetId);
		attributeSetterBiConsumers.put(
			"auditSetId",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setAuditSetId);
		attributeGetterFunctions.put(
			"fieldClassNameId", AuditEntry::getFieldClassNameId);
		attributeSetterBiConsumers.put(
			"fieldClassNameId",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setFieldClassNameId);
		attributeGetterFunctions.put(
			"fieldClassPK", AuditEntry::getFieldClassPK);
		attributeSetterBiConsumers.put(
			"fieldClassPK",
			(BiConsumer<AuditEntry, Long>)AuditEntry::setFieldClassPK);
		attributeGetterFunctions.put("action", AuditEntry::getAction);
		attributeSetterBiConsumers.put(
			"action", (BiConsumer<AuditEntry, String>)AuditEntry::setAction);
		attributeGetterFunctions.put("field", AuditEntry::getField);
		attributeSetterBiConsumers.put(
			"field", (BiConsumer<AuditEntry, String>)AuditEntry::setField);
		attributeGetterFunctions.put("oldLabel", AuditEntry::getOldLabel);
		attributeSetterBiConsumers.put(
			"oldLabel",
			(BiConsumer<AuditEntry, String>)AuditEntry::setOldLabel);
		attributeGetterFunctions.put("oldValue", AuditEntry::getOldValue);
		attributeSetterBiConsumers.put(
			"oldValue",
			(BiConsumer<AuditEntry, String>)AuditEntry::setOldValue);
		attributeGetterFunctions.put("newLabel", AuditEntry::getNewLabel);
		attributeSetterBiConsumers.put(
			"newLabel",
			(BiConsumer<AuditEntry, String>)AuditEntry::setNewLabel);
		attributeGetterFunctions.put("newValue", AuditEntry::getNewValue);
		attributeSetterBiConsumers.put(
			"newValue",
			(BiConsumer<AuditEntry, String>)AuditEntry::setNewValue);
		attributeGetterFunctions.put("description", AuditEntry::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<AuditEntry, String>)AuditEntry::setDescription);

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
	public long getAuditEntryId() {
		return _auditEntryId;
	}

	@Override
	public void setAuditEntryId(long auditEntryId) {
		_auditEntryId = auditEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
		catch (PortalException portalException) {
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
	public String getAuditEntryKey() {
		if (_auditEntryKey == null) {
			return "";
		}
		else {
			return _auditEntryKey;
		}
	}

	@Override
	public void setAuditEntryKey(String auditEntryKey) {
		_columnBitmask |= AUDITENTRYKEY_COLUMN_BITMASK;

		if (_originalAuditEntryKey == null) {
			_originalAuditEntryKey = _auditEntryKey;
		}

		_auditEntryKey = auditEntryKey;
	}

	public String getOriginalAuditEntryKey() {
		return GetterUtil.getString(_originalAuditEntryKey);
	}

	@JSON
	@Override
	public String getAgentName() {
		if (_agentName == null) {
			return "";
		}
		else {
			return _agentName;
		}
	}

	@Override
	public void setAgentName(String agentName) {
		_agentName = agentName;
	}

	@JSON
	@Override
	public String getAgentOktaId() {
		if (_agentOktaId == null) {
			return "";
		}
		else {
			return _agentOktaId;
		}
	}

	@Override
	public void setAgentOktaId(String agentOktaId) {
		_agentOktaId = agentOktaId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@JSON
	@Override
	public long getAuditSetId() {
		return _auditSetId;
	}

	@Override
	public void setAuditSetId(long auditSetId) {
		_auditSetId = auditSetId;
	}

	@JSON
	@Override
	public long getFieldClassNameId() {
		return _fieldClassNameId;
	}

	@Override
	public void setFieldClassNameId(long fieldClassNameId) {
		_columnBitmask |= FIELDCLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalFieldClassNameId) {
			_setOriginalFieldClassNameId = true;

			_originalFieldClassNameId = _fieldClassNameId;
		}

		_fieldClassNameId = fieldClassNameId;
	}

	public long getOriginalFieldClassNameId() {
		return _originalFieldClassNameId;
	}

	@JSON
	@Override
	public long getFieldClassPK() {
		return _fieldClassPK;
	}

	@Override
	public void setFieldClassPK(long fieldClassPK) {
		_columnBitmask |= FIELDCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalFieldClassPK) {
			_setOriginalFieldClassPK = true;

			_originalFieldClassPK = _fieldClassPK;
		}

		_fieldClassPK = fieldClassPK;
	}

	public long getOriginalFieldClassPK() {
		return _originalFieldClassPK;
	}

	@JSON
	@Override
	public String getAction() {
		if (_action == null) {
			return "";
		}
		else {
			return _action;
		}
	}

	@Override
	public void setAction(String action) {
		_action = action;
	}

	@JSON
	@Override
	public String getField() {
		if (_field == null) {
			return "";
		}
		else {
			return _field;
		}
	}

	@Override
	public void setField(String field) {
		_field = field;
	}

	@JSON
	@Override
	public String getOldLabel() {
		if (_oldLabel == null) {
			return "";
		}
		else {
			return _oldLabel;
		}
	}

	@Override
	public void setOldLabel(String oldLabel) {
		_oldLabel = oldLabel;
	}

	@JSON
	@Override
	public String getOldValue() {
		if (_oldValue == null) {
			return "";
		}
		else {
			return _oldValue;
		}
	}

	@Override
	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	@JSON
	@Override
	public String getNewLabel() {
		if (_newLabel == null) {
			return "";
		}
		else {
			return _newLabel;
		}
	}

	@Override
	public void setNewLabel(String newLabel) {
		_newLabel = newLabel;
	}

	@JSON
	@Override
	public String getNewValue() {
		if (_newValue == null) {
			return "";
		}
		else {
			return _newValue;
		}
	}

	@Override
	public void setNewValue(String newValue) {
		_newValue = newValue;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AuditEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AuditEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AuditEntry>
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
		AuditEntryImpl auditEntryImpl = new AuditEntryImpl();

		auditEntryImpl.setMvccVersion(getMvccVersion());
		auditEntryImpl.setAuditEntryId(getAuditEntryId());
		auditEntryImpl.setCompanyId(getCompanyId());
		auditEntryImpl.setUserId(getUserId());
		auditEntryImpl.setCreateDate(getCreateDate());
		auditEntryImpl.setModifiedDate(getModifiedDate());
		auditEntryImpl.setAuditEntryKey(getAuditEntryKey());
		auditEntryImpl.setAgentName(getAgentName());
		auditEntryImpl.setAgentOktaId(getAgentOktaId());
		auditEntryImpl.setClassNameId(getClassNameId());
		auditEntryImpl.setClassPK(getClassPK());
		auditEntryImpl.setAuditSetId(getAuditSetId());
		auditEntryImpl.setFieldClassNameId(getFieldClassNameId());
		auditEntryImpl.setFieldClassPK(getFieldClassPK());
		auditEntryImpl.setAction(getAction());
		auditEntryImpl.setField(getField());
		auditEntryImpl.setOldLabel(getOldLabel());
		auditEntryImpl.setOldValue(getOldValue());
		auditEntryImpl.setNewLabel(getNewLabel());
		auditEntryImpl.setNewValue(getNewValue());
		auditEntryImpl.setDescription(getDescription());

		auditEntryImpl.resetOriginalValues();

		return auditEntryImpl;
	}

	@Override
	public int compareTo(AuditEntry auditEntry) {
		long primaryKey = auditEntry.getPrimaryKey();

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

		if (!(obj instanceof AuditEntry)) {
			return false;
		}

		AuditEntry auditEntry = (AuditEntry)obj;

		long primaryKey = auditEntry.getPrimaryKey();

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
		AuditEntryModelImpl auditEntryModelImpl = this;

		auditEntryModelImpl._setModifiedDate = false;

		auditEntryModelImpl._originalAuditEntryKey =
			auditEntryModelImpl._auditEntryKey;

		auditEntryModelImpl._originalClassNameId =
			auditEntryModelImpl._classNameId;

		auditEntryModelImpl._setOriginalClassNameId = false;

		auditEntryModelImpl._originalClassPK = auditEntryModelImpl._classPK;

		auditEntryModelImpl._setOriginalClassPK = false;

		auditEntryModelImpl._originalFieldClassNameId =
			auditEntryModelImpl._fieldClassNameId;

		auditEntryModelImpl._setOriginalFieldClassNameId = false;

		auditEntryModelImpl._originalFieldClassPK =
			auditEntryModelImpl._fieldClassPK;

		auditEntryModelImpl._setOriginalFieldClassPK = false;

		auditEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AuditEntry> toCacheModel() {
		AuditEntryCacheModel auditEntryCacheModel = new AuditEntryCacheModel();

		auditEntryCacheModel.mvccVersion = getMvccVersion();

		auditEntryCacheModel.auditEntryId = getAuditEntryId();

		auditEntryCacheModel.companyId = getCompanyId();

		auditEntryCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			auditEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			auditEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			auditEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			auditEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		auditEntryCacheModel.auditEntryKey = getAuditEntryKey();

		String auditEntryKey = auditEntryCacheModel.auditEntryKey;

		if ((auditEntryKey != null) && (auditEntryKey.length() == 0)) {
			auditEntryCacheModel.auditEntryKey = null;
		}

		auditEntryCacheModel.agentName = getAgentName();

		String agentName = auditEntryCacheModel.agentName;

		if ((agentName != null) && (agentName.length() == 0)) {
			auditEntryCacheModel.agentName = null;
		}

		auditEntryCacheModel.agentOktaId = getAgentOktaId();

		String agentOktaId = auditEntryCacheModel.agentOktaId;

		if ((agentOktaId != null) && (agentOktaId.length() == 0)) {
			auditEntryCacheModel.agentOktaId = null;
		}

		auditEntryCacheModel.classNameId = getClassNameId();

		auditEntryCacheModel.classPK = getClassPK();

		auditEntryCacheModel.auditSetId = getAuditSetId();

		auditEntryCacheModel.fieldClassNameId = getFieldClassNameId();

		auditEntryCacheModel.fieldClassPK = getFieldClassPK();

		auditEntryCacheModel.action = getAction();

		String action = auditEntryCacheModel.action;

		if ((action != null) && (action.length() == 0)) {
			auditEntryCacheModel.action = null;
		}

		auditEntryCacheModel.field = getField();

		String field = auditEntryCacheModel.field;

		if ((field != null) && (field.length() == 0)) {
			auditEntryCacheModel.field = null;
		}

		auditEntryCacheModel.oldLabel = getOldLabel();

		String oldLabel = auditEntryCacheModel.oldLabel;

		if ((oldLabel != null) && (oldLabel.length() == 0)) {
			auditEntryCacheModel.oldLabel = null;
		}

		auditEntryCacheModel.oldValue = getOldValue();

		String oldValue = auditEntryCacheModel.oldValue;

		if ((oldValue != null) && (oldValue.length() == 0)) {
			auditEntryCacheModel.oldValue = null;
		}

		auditEntryCacheModel.newLabel = getNewLabel();

		String newLabel = auditEntryCacheModel.newLabel;

		if ((newLabel != null) && (newLabel.length() == 0)) {
			auditEntryCacheModel.newLabel = null;
		}

		auditEntryCacheModel.newValue = getNewValue();

		String newValue = auditEntryCacheModel.newValue;

		if ((newValue != null) && (newValue.length() == 0)) {
			auditEntryCacheModel.newValue = null;
		}

		auditEntryCacheModel.description = getDescription();

		String description = auditEntryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			auditEntryCacheModel.description = null;
		}

		return auditEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AuditEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AuditEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuditEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AuditEntry)this));
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
		Map<String, Function<AuditEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AuditEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuditEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AuditEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AuditEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _auditEntryId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _auditEntryKey;
	private String _originalAuditEntryKey;
	private String _agentName;
	private String _agentOktaId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _auditSetId;
	private long _fieldClassNameId;
	private long _originalFieldClassNameId;
	private boolean _setOriginalFieldClassNameId;
	private long _fieldClassPK;
	private long _originalFieldClassPK;
	private boolean _setOriginalFieldClassPK;
	private String _action;
	private String _field;
	private String _oldLabel;
	private String _oldValue;
	private String _newLabel;
	private String _newValue;
	private String _description;
	private long _columnBitmask;
	private AuditEntry _escapedModel;

}