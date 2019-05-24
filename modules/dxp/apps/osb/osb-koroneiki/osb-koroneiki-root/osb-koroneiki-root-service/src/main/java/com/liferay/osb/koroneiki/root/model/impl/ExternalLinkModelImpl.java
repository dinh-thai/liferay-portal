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
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.model.ExternalLinkModel;
import com.liferay.osb.koroneiki.root.model.ExternalLinkSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the ExternalLink service. Represents a row in the &quot;Koroneiki_ExternalLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ExternalLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExternalLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ExternalLinkModelImpl
	extends BaseModelImpl<ExternalLink> implements ExternalLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a external link model instance should use the <code>ExternalLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ExternalLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"externalLinkId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"domain", Types.VARCHAR}, {"entityName", Types.VARCHAR},
		{"entityId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("domain", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("entityName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("entityId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ExternalLink (externalLinkId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,domain VARCHAR(75) null,entityName VARCHAR(75) null,entityId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ExternalLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY externalLink.externalLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ExternalLink.externalLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long DOMAIN_COLUMN_BITMASK = 4L;

	public static final long ENTITYID_COLUMN_BITMASK = 8L;

	public static final long ENTITYNAME_COLUMN_BITMASK = 16L;

	public static final long EXTERNALLINKID_COLUMN_BITMASK = 32L;

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
	public static ExternalLink toModel(ExternalLinkSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ExternalLink model = new ExternalLinkImpl();

		model.setExternalLinkId(soapModel.getExternalLinkId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setDomain(soapModel.getDomain());
		model.setEntityName(soapModel.getEntityName());
		model.setEntityId(soapModel.getEntityId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ExternalLink> toModels(ExternalLinkSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ExternalLink> models = new ArrayList<ExternalLink>(
			soapModels.length);

		for (ExternalLinkSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ExternalLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _externalLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setExternalLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _externalLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ExternalLink.class;
	}

	@Override
	public String getModelClassName() {
		return ExternalLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ExternalLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ExternalLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExternalLink, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ExternalLink)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ExternalLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ExternalLink, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ExternalLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ExternalLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ExternalLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ExternalLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ExternalLink.class.getClassLoader(), ExternalLink.class,
			ModelWrapper.class);

		try {
			Constructor<ExternalLink> constructor =
				(Constructor<ExternalLink>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ExternalLink, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ExternalLink, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ExternalLink, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ExternalLink, Object>>();
		Map<String, BiConsumer<ExternalLink, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ExternalLink, ?>>();

		attributeGetterFunctions.put(
			"externalLinkId", ExternalLink::getExternalLinkId);
		attributeSetterBiConsumers.put(
			"externalLinkId",
			(BiConsumer<ExternalLink, Long>)ExternalLink::setExternalLinkId);
		attributeGetterFunctions.put("companyId", ExternalLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ExternalLink, Long>)ExternalLink::setCompanyId);
		attributeGetterFunctions.put("createDate", ExternalLink::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ExternalLink, Date>)ExternalLink::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ExternalLink::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ExternalLink, Date>)ExternalLink::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", ExternalLink::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<ExternalLink, Long>)ExternalLink::setClassNameId);
		attributeGetterFunctions.put("classPK", ExternalLink::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<ExternalLink, Long>)ExternalLink::setClassPK);
		attributeGetterFunctions.put("domain", ExternalLink::getDomain);
		attributeSetterBiConsumers.put(
			"domain",
			(BiConsumer<ExternalLink, String>)ExternalLink::setDomain);
		attributeGetterFunctions.put("entityName", ExternalLink::getEntityName);
		attributeSetterBiConsumers.put(
			"entityName",
			(BiConsumer<ExternalLink, String>)ExternalLink::setEntityName);
		attributeGetterFunctions.put("entityId", ExternalLink::getEntityId);
		attributeSetterBiConsumers.put(
			"entityId",
			(BiConsumer<ExternalLink, String>)ExternalLink::setEntityId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getExternalLinkId() {
		return _externalLinkId;
	}

	@Override
	public void setExternalLinkId(long externalLinkId) {
		_externalLinkId = externalLinkId;
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
	public String getDomain() {
		if (_domain == null) {
			return "";
		}
		else {
			return _domain;
		}
	}

	@Override
	public void setDomain(String domain) {
		_columnBitmask |= DOMAIN_COLUMN_BITMASK;

		if (_originalDomain == null) {
			_originalDomain = _domain;
		}

		_domain = domain;
	}

	public String getOriginalDomain() {
		return GetterUtil.getString(_originalDomain);
	}

	@JSON
	@Override
	public String getEntityName() {
		if (_entityName == null) {
			return "";
		}
		else {
			return _entityName;
		}
	}

	@Override
	public void setEntityName(String entityName) {
		_columnBitmask |= ENTITYNAME_COLUMN_BITMASK;

		if (_originalEntityName == null) {
			_originalEntityName = _entityName;
		}

		_entityName = entityName;
	}

	public String getOriginalEntityName() {
		return GetterUtil.getString(_originalEntityName);
	}

	@JSON
	@Override
	public String getEntityId() {
		if (_entityId == null) {
			return "";
		}
		else {
			return _entityId;
		}
	}

	@Override
	public void setEntityId(String entityId) {
		_columnBitmask |= ENTITYID_COLUMN_BITMASK;

		if (_originalEntityId == null) {
			_originalEntityId = _entityId;
		}

		_entityId = entityId;
	}

	public String getOriginalEntityId() {
		return GetterUtil.getString(_originalEntityId);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ExternalLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ExternalLink toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ExternalLinkImpl externalLinkImpl = new ExternalLinkImpl();

		externalLinkImpl.setExternalLinkId(getExternalLinkId());
		externalLinkImpl.setCompanyId(getCompanyId());
		externalLinkImpl.setCreateDate(getCreateDate());
		externalLinkImpl.setModifiedDate(getModifiedDate());
		externalLinkImpl.setClassNameId(getClassNameId());
		externalLinkImpl.setClassPK(getClassPK());
		externalLinkImpl.setDomain(getDomain());
		externalLinkImpl.setEntityName(getEntityName());
		externalLinkImpl.setEntityId(getEntityId());

		externalLinkImpl.resetOriginalValues();

		return externalLinkImpl;
	}

	@Override
	public int compareTo(ExternalLink externalLink) {
		long primaryKey = externalLink.getPrimaryKey();

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

		if (!(obj instanceof ExternalLink)) {
			return false;
		}

		ExternalLink externalLink = (ExternalLink)obj;

		long primaryKey = externalLink.getPrimaryKey();

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
		ExternalLinkModelImpl externalLinkModelImpl = this;

		externalLinkModelImpl._setModifiedDate = false;

		externalLinkModelImpl._originalClassNameId =
			externalLinkModelImpl._classNameId;

		externalLinkModelImpl._setOriginalClassNameId = false;

		externalLinkModelImpl._originalClassPK = externalLinkModelImpl._classPK;

		externalLinkModelImpl._setOriginalClassPK = false;

		externalLinkModelImpl._originalDomain = externalLinkModelImpl._domain;

		externalLinkModelImpl._originalEntityName =
			externalLinkModelImpl._entityName;

		externalLinkModelImpl._originalEntityId =
			externalLinkModelImpl._entityId;

		externalLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ExternalLink> toCacheModel() {
		ExternalLinkCacheModel externalLinkCacheModel =
			new ExternalLinkCacheModel();

		externalLinkCacheModel.externalLinkId = getExternalLinkId();

		externalLinkCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			externalLinkCacheModel.createDate = createDate.getTime();
		}
		else {
			externalLinkCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			externalLinkCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			externalLinkCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		externalLinkCacheModel.classNameId = getClassNameId();

		externalLinkCacheModel.classPK = getClassPK();

		externalLinkCacheModel.domain = getDomain();

		String domain = externalLinkCacheModel.domain;

		if ((domain != null) && (domain.length() == 0)) {
			externalLinkCacheModel.domain = null;
		}

		externalLinkCacheModel.entityName = getEntityName();

		String entityName = externalLinkCacheModel.entityName;

		if ((entityName != null) && (entityName.length() == 0)) {
			externalLinkCacheModel.entityName = null;
		}

		externalLinkCacheModel.entityId = getEntityId();

		String entityId = externalLinkCacheModel.entityId;

		if ((entityId != null) && (entityId.length() == 0)) {
			externalLinkCacheModel.entityId = null;
		}

		return externalLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ExternalLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ExternalLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExternalLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ExternalLink)this));
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
		Map<String, Function<ExternalLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ExternalLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExternalLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ExternalLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, ExternalLink>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _externalLinkId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _domain;
	private String _originalDomain;
	private String _entityName;
	private String _originalEntityName;
	private String _entityId;
	private String _originalEntityId;
	private long _columnBitmask;
	private ExternalLink _escapedModel;

}