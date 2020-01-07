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

package com.liferay.segments.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.model.SegmentsEntryRel;
import com.liferay.segments.model.SegmentsEntryRelModel;
import com.liferay.segments.model.SegmentsEntryRelSoap;

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
 * The base model implementation for the SegmentsEntryRel service. Represents a row in the &quot;SegmentsEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>SegmentsEntryRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SegmentsEntryRelImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryRelImpl
 * @generated
 */
@JSON(strict = true)
public class SegmentsEntryRelModelImpl
	extends BaseModelImpl<SegmentsEntryRel> implements SegmentsEntryRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a segments entry rel model instance should use the <code>SegmentsEntryRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "SegmentsEntryRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"segmentsEntryRelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"segmentsEntryId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("segmentsEntryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SegmentsEntryRel (mvccVersion LONG default 0 not null,segmentsEntryRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,segmentsEntryId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP = "drop table SegmentsEntryRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY segmentsEntryRel.segmentsEntryRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SegmentsEntryRel.segmentsEntryRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long SEGMENTSENTRYID_COLUMN_BITMASK = 8L;

	public static final long SEGMENTSENTRYRELID_COLUMN_BITMASK = 16L;

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
	public static SegmentsEntryRel toModel(SegmentsEntryRelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SegmentsEntryRel model = new SegmentsEntryRelImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setSegmentsEntryRelId(soapModel.getSegmentsEntryRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSegmentsEntryId(soapModel.getSegmentsEntryId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SegmentsEntryRel> toModels(
		SegmentsEntryRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SegmentsEntryRel> models = new ArrayList<SegmentsEntryRel>(
			soapModels.length);

		for (SegmentsEntryRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SegmentsEntryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _segmentsEntryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSegmentsEntryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _segmentsEntryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SegmentsEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return SegmentsEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SegmentsEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SegmentsEntryRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsEntryRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SegmentsEntryRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SegmentsEntryRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SegmentsEntryRel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SegmentsEntryRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SegmentsEntryRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SegmentsEntryRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SegmentsEntryRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SegmentsEntryRel.class.getClassLoader(), SegmentsEntryRel.class,
			ModelWrapper.class);

		try {
			Constructor<SegmentsEntryRel> constructor =
				(Constructor<SegmentsEntryRel>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SegmentsEntryRel, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SegmentsEntryRel, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SegmentsEntryRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<SegmentsEntryRel, Object>>();
		Map<String, BiConsumer<SegmentsEntryRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SegmentsEntryRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", SegmentsEntryRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<SegmentsEntryRel, Long>)
				SegmentsEntryRel::setMvccVersion);
		attributeGetterFunctions.put(
			"segmentsEntryRelId", SegmentsEntryRel::getSegmentsEntryRelId);
		attributeSetterBiConsumers.put(
			"segmentsEntryRelId",
			(BiConsumer<SegmentsEntryRel, Long>)
				SegmentsEntryRel::setSegmentsEntryRelId);
		attributeGetterFunctions.put("groupId", SegmentsEntryRel::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SegmentsEntryRel, Long>)SegmentsEntryRel::setGroupId);
		attributeGetterFunctions.put(
			"companyId", SegmentsEntryRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SegmentsEntryRel, Long>)SegmentsEntryRel::setCompanyId);
		attributeGetterFunctions.put("userId", SegmentsEntryRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SegmentsEntryRel, Long>)SegmentsEntryRel::setUserId);
		attributeGetterFunctions.put("userName", SegmentsEntryRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SegmentsEntryRel, String>)
				SegmentsEntryRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", SegmentsEntryRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SegmentsEntryRel, Date>)
				SegmentsEntryRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SegmentsEntryRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SegmentsEntryRel, Date>)
				SegmentsEntryRel::setModifiedDate);
		attributeGetterFunctions.put(
			"segmentsEntryId", SegmentsEntryRel::getSegmentsEntryId);
		attributeSetterBiConsumers.put(
			"segmentsEntryId",
			(BiConsumer<SegmentsEntryRel, Long>)
				SegmentsEntryRel::setSegmentsEntryId);
		attributeGetterFunctions.put(
			"classNameId", SegmentsEntryRel::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SegmentsEntryRel, Long>)
				SegmentsEntryRel::setClassNameId);
		attributeGetterFunctions.put("classPK", SegmentsEntryRel::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<SegmentsEntryRel, Long>)SegmentsEntryRel::setClassPK);

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
	public long getSegmentsEntryRelId() {
		return _segmentsEntryRelId;
	}

	@Override
	public void setSegmentsEntryRelId(long segmentsEntryRelId) {
		_segmentsEntryRelId = segmentsEntryRelId;
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
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		_columnBitmask |= SEGMENTSENTRYID_COLUMN_BITMASK;

		if (!_setOriginalSegmentsEntryId) {
			_setOriginalSegmentsEntryId = true;

			_originalSegmentsEntryId = _segmentsEntryId;
		}

		_segmentsEntryId = segmentsEntryId;
	}

	public long getOriginalSegmentsEntryId() {
		return _originalSegmentsEntryId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SegmentsEntryRel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SegmentsEntryRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SegmentsEntryRel>
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
		SegmentsEntryRelImpl segmentsEntryRelImpl = new SegmentsEntryRelImpl();

		segmentsEntryRelImpl.setMvccVersion(getMvccVersion());
		segmentsEntryRelImpl.setSegmentsEntryRelId(getSegmentsEntryRelId());
		segmentsEntryRelImpl.setGroupId(getGroupId());
		segmentsEntryRelImpl.setCompanyId(getCompanyId());
		segmentsEntryRelImpl.setUserId(getUserId());
		segmentsEntryRelImpl.setUserName(getUserName());
		segmentsEntryRelImpl.setCreateDate(getCreateDate());
		segmentsEntryRelImpl.setModifiedDate(getModifiedDate());
		segmentsEntryRelImpl.setSegmentsEntryId(getSegmentsEntryId());
		segmentsEntryRelImpl.setClassNameId(getClassNameId());
		segmentsEntryRelImpl.setClassPK(getClassPK());

		segmentsEntryRelImpl.resetOriginalValues();

		return segmentsEntryRelImpl;
	}

	@Override
	public int compareTo(SegmentsEntryRel segmentsEntryRel) {
		long primaryKey = segmentsEntryRel.getPrimaryKey();

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

		if (!(obj instanceof SegmentsEntryRel)) {
			return false;
		}

		SegmentsEntryRel segmentsEntryRel = (SegmentsEntryRel)obj;

		long primaryKey = segmentsEntryRel.getPrimaryKey();

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
		SegmentsEntryRelModelImpl segmentsEntryRelModelImpl = this;

		segmentsEntryRelModelImpl._originalGroupId =
			segmentsEntryRelModelImpl._groupId;

		segmentsEntryRelModelImpl._setOriginalGroupId = false;

		segmentsEntryRelModelImpl._setModifiedDate = false;

		segmentsEntryRelModelImpl._originalSegmentsEntryId =
			segmentsEntryRelModelImpl._segmentsEntryId;

		segmentsEntryRelModelImpl._setOriginalSegmentsEntryId = false;

		segmentsEntryRelModelImpl._originalClassNameId =
			segmentsEntryRelModelImpl._classNameId;

		segmentsEntryRelModelImpl._setOriginalClassNameId = false;

		segmentsEntryRelModelImpl._originalClassPK =
			segmentsEntryRelModelImpl._classPK;

		segmentsEntryRelModelImpl._setOriginalClassPK = false;

		segmentsEntryRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SegmentsEntryRel> toCacheModel() {
		SegmentsEntryRelCacheModel segmentsEntryRelCacheModel =
			new SegmentsEntryRelCacheModel();

		segmentsEntryRelCacheModel.mvccVersion = getMvccVersion();

		segmentsEntryRelCacheModel.segmentsEntryRelId = getSegmentsEntryRelId();

		segmentsEntryRelCacheModel.groupId = getGroupId();

		segmentsEntryRelCacheModel.companyId = getCompanyId();

		segmentsEntryRelCacheModel.userId = getUserId();

		segmentsEntryRelCacheModel.userName = getUserName();

		String userName = segmentsEntryRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			segmentsEntryRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			segmentsEntryRelCacheModel.createDate = createDate.getTime();
		}
		else {
			segmentsEntryRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			segmentsEntryRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			segmentsEntryRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		segmentsEntryRelCacheModel.segmentsEntryId = getSegmentsEntryId();

		segmentsEntryRelCacheModel.classNameId = getClassNameId();

		segmentsEntryRelCacheModel.classPK = getClassPK();

		return segmentsEntryRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SegmentsEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SegmentsEntryRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsEntryRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SegmentsEntryRel)this));
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
		Map<String, Function<SegmentsEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SegmentsEntryRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsEntryRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SegmentsEntryRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SegmentsEntryRel>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _segmentsEntryRelId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _segmentsEntryId;
	private long _originalSegmentsEntryId;
	private boolean _setOriginalSegmentsEntryId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _columnBitmask;
	private SegmentsEntryRel _escapedModel;

}