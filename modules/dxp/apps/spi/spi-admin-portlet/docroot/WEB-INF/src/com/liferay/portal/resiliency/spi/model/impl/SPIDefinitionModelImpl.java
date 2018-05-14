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

package com.liferay.portal.resiliency.spi.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.model.SPIDefinitionModel;
import com.liferay.portal.resiliency.spi.model.SPIDefinitionSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the SPIDefinition service. Represents a row in the &quot;SPIDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SPIDefinitionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SPIDefinitionImpl}.
 * </p>
 *
 * @author Michael C. Han
 * @see SPIDefinitionImpl
 * @see SPIDefinition
 * @see SPIDefinitionModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SPIDefinitionModelImpl extends BaseModelImpl<SPIDefinition>
	implements SPIDefinitionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a spi definition model instance should use the {@link SPIDefinition} interface instead.
	 */
	public static final String TABLE_NAME = "SPIDefinition";
	public static final Object[][] TABLE_COLUMNS = {
			{ "spiDefinitionId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "connectorAddress", Types.VARCHAR },
			{ "connectorPort", Types.INTEGER },
			{ "description", Types.VARCHAR },
			{ "jvmArguments", Types.VARCHAR },
			{ "portletIds", Types.VARCHAR },
			{ "servletContextNames", Types.VARCHAR },
			{ "typeSettings", Types.CLOB },
			{ "status", Types.INTEGER },
			{ "statusMessage", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("spiDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("connectorAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("connectorPort", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("jvmArguments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("servletContextNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table SPIDefinition (spiDefinitionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(200) null,connectorAddress VARCHAR(200) null,connectorPort INTEGER,description STRING null,jvmArguments STRING null,portletIds STRING null,servletContextNames STRING null,typeSettings TEXT null,status INTEGER,statusMessage STRING null)";
	public static final String TABLE_SQL_DROP = "drop table SPIDefinition";
	public static final String ORDER_BY_JPQL = " ORDER BY spiDefinition.spiDefinitionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SPIDefinition.spiDefinitionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long CONNECTORADDRESS_COLUMN_BITMASK = 2L;
	public static final long CONNECTORPORT_COLUMN_BITMASK = 4L;
	public static final long NAME_COLUMN_BITMASK = 8L;
	public static final long STATUS_COLUMN_BITMASK = 16L;
	public static final long SPIDEFINITIONID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SPIDefinition toModel(SPIDefinitionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SPIDefinition model = new SPIDefinitionImpl();

		model.setSpiDefinitionId(soapModel.getSpiDefinitionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setConnectorAddress(soapModel.getConnectorAddress());
		model.setConnectorPort(soapModel.getConnectorPort());
		model.setDescription(soapModel.getDescription());
		model.setJvmArguments(soapModel.getJvmArguments());
		model.setPortletIds(soapModel.getPortletIds());
		model.setServletContextNames(soapModel.getServletContextNames());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setStatus(soapModel.getStatus());
		model.setStatusMessage(soapModel.getStatusMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SPIDefinition> toModels(SPIDefinitionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SPIDefinition> models = new ArrayList<SPIDefinition>(soapModels.length);

		for (SPIDefinitionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.resiliency.spi.model.SPIDefinition"));

	public SPIDefinitionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSpiDefinitionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SPIDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return SPIDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("spiDefinitionId", getSpiDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("connectorAddress", getConnectorAddress());
		attributes.put("connectorPort", getConnectorPort());
		attributes.put("description", getDescription());
		attributes.put("jvmArguments", getJvmArguments());
		attributes.put("portletIds", getPortletIds());
		attributes.put("servletContextNames", getServletContextNames());
		attributes.put("typeSettings", getTypeSettings());
		attributes.put("status", getStatus());
		attributes.put("statusMessage", getStatusMessage());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long spiDefinitionId = (Long)attributes.get("spiDefinitionId");

		if (spiDefinitionId != null) {
			setSpiDefinitionId(spiDefinitionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String connectorAddress = (String)attributes.get("connectorAddress");

		if (connectorAddress != null) {
			setConnectorAddress(connectorAddress);
		}

		Integer connectorPort = (Integer)attributes.get("connectorPort");

		if (connectorPort != null) {
			setConnectorPort(connectorPort);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String jvmArguments = (String)attributes.get("jvmArguments");

		if (jvmArguments != null) {
			setJvmArguments(jvmArguments);
		}

		String portletIds = (String)attributes.get("portletIds");

		if (portletIds != null) {
			setPortletIds(portletIds);
		}

		String servletContextNames = (String)attributes.get(
				"servletContextNames");

		if (servletContextNames != null) {
			setServletContextNames(servletContextNames);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}
	}

	@JSON
	@Override
	public long getSpiDefinitionId() {
		return _spiDefinitionId;
	}

	@Override
	public void setSpiDefinitionId(long spiDefinitionId) {
		_columnBitmask = -1L;

		_spiDefinitionId = spiDefinitionId;
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
	public String getConnectorAddress() {
		if (_connectorAddress == null) {
			return "";
		}
		else {
			return _connectorAddress;
		}
	}

	@Override
	public void setConnectorAddress(String connectorAddress) {
		_columnBitmask |= CONNECTORADDRESS_COLUMN_BITMASK;

		if (_originalConnectorAddress == null) {
			_originalConnectorAddress = _connectorAddress;
		}

		_connectorAddress = connectorAddress;
	}

	public String getOriginalConnectorAddress() {
		return GetterUtil.getString(_originalConnectorAddress);
	}

	@JSON
	@Override
	public int getConnectorPort() {
		return _connectorPort;
	}

	@Override
	public void setConnectorPort(int connectorPort) {
		_columnBitmask |= CONNECTORPORT_COLUMN_BITMASK;

		if (!_setOriginalConnectorPort) {
			_setOriginalConnectorPort = true;

			_originalConnectorPort = _connectorPort;
		}

		_connectorPort = connectorPort;
	}

	public int getOriginalConnectorPort() {
		return _originalConnectorPort;
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
	public String getJvmArguments() {
		if (_jvmArguments == null) {
			return "";
		}
		else {
			return _jvmArguments;
		}
	}

	@Override
	public void setJvmArguments(String jvmArguments) {
		_jvmArguments = jvmArguments;
	}

	@JSON
	@Override
	public String getPortletIds() {
		if (_portletIds == null) {
			return "";
		}
		else {
			return _portletIds;
		}
	}

	@Override
	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;
	}

	@JSON
	@Override
	public String getServletContextNames() {
		if (_servletContextNames == null) {
			return "";
		}
		else {
			return _servletContextNames;
		}
	}

	@Override
	public void setServletContextNames(String servletContextNames) {
		_servletContextNames = servletContextNames;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SPIDefinition.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SPIDefinition toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SPIDefinition)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SPIDefinitionImpl spiDefinitionImpl = new SPIDefinitionImpl();

		spiDefinitionImpl.setSpiDefinitionId(getSpiDefinitionId());
		spiDefinitionImpl.setCompanyId(getCompanyId());
		spiDefinitionImpl.setUserId(getUserId());
		spiDefinitionImpl.setUserName(getUserName());
		spiDefinitionImpl.setCreateDate(getCreateDate());
		spiDefinitionImpl.setModifiedDate(getModifiedDate());
		spiDefinitionImpl.setName(getName());
		spiDefinitionImpl.setConnectorAddress(getConnectorAddress());
		spiDefinitionImpl.setConnectorPort(getConnectorPort());
		spiDefinitionImpl.setDescription(getDescription());
		spiDefinitionImpl.setJvmArguments(getJvmArguments());
		spiDefinitionImpl.setPortletIds(getPortletIds());
		spiDefinitionImpl.setServletContextNames(getServletContextNames());
		spiDefinitionImpl.setTypeSettings(getTypeSettings());
		spiDefinitionImpl.setStatus(getStatus());
		spiDefinitionImpl.setStatusMessage(getStatusMessage());

		spiDefinitionImpl.resetOriginalValues();

		return spiDefinitionImpl;
	}

	@Override
	public int compareTo(SPIDefinition spiDefinition) {
		int value = 0;

		if (getSpiDefinitionId() < spiDefinition.getSpiDefinitionId()) {
			value = -1;
		}
		else if (getSpiDefinitionId() > spiDefinition.getSpiDefinitionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SPIDefinition)) {
			return false;
		}

		SPIDefinition spiDefinition = (SPIDefinition)obj;

		long primaryKey = spiDefinition.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SPIDefinitionModelImpl spiDefinitionModelImpl = this;

		spiDefinitionModelImpl._originalCompanyId = spiDefinitionModelImpl._companyId;

		spiDefinitionModelImpl._setOriginalCompanyId = false;

		spiDefinitionModelImpl._setModifiedDate = false;

		spiDefinitionModelImpl._originalName = spiDefinitionModelImpl._name;

		spiDefinitionModelImpl._originalConnectorAddress = spiDefinitionModelImpl._connectorAddress;

		spiDefinitionModelImpl._originalConnectorPort = spiDefinitionModelImpl._connectorPort;

		spiDefinitionModelImpl._setOriginalConnectorPort = false;

		spiDefinitionModelImpl._originalStatus = spiDefinitionModelImpl._status;

		spiDefinitionModelImpl._setOriginalStatus = false;

		spiDefinitionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SPIDefinition> toCacheModel() {
		SPIDefinitionCacheModel spiDefinitionCacheModel = new SPIDefinitionCacheModel();

		spiDefinitionCacheModel.spiDefinitionId = getSpiDefinitionId();

		spiDefinitionCacheModel.companyId = getCompanyId();

		spiDefinitionCacheModel.userId = getUserId();

		spiDefinitionCacheModel.userName = getUserName();

		String userName = spiDefinitionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			spiDefinitionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			spiDefinitionCacheModel.createDate = createDate.getTime();
		}
		else {
			spiDefinitionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			spiDefinitionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			spiDefinitionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		spiDefinitionCacheModel.name = getName();

		String name = spiDefinitionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			spiDefinitionCacheModel.name = null;
		}

		spiDefinitionCacheModel.connectorAddress = getConnectorAddress();

		String connectorAddress = spiDefinitionCacheModel.connectorAddress;

		if ((connectorAddress != null) && (connectorAddress.length() == 0)) {
			spiDefinitionCacheModel.connectorAddress = null;
		}

		spiDefinitionCacheModel.connectorPort = getConnectorPort();

		spiDefinitionCacheModel.description = getDescription();

		String description = spiDefinitionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			spiDefinitionCacheModel.description = null;
		}

		spiDefinitionCacheModel.jvmArguments = getJvmArguments();

		String jvmArguments = spiDefinitionCacheModel.jvmArguments;

		if ((jvmArguments != null) && (jvmArguments.length() == 0)) {
			spiDefinitionCacheModel.jvmArguments = null;
		}

		spiDefinitionCacheModel.portletIds = getPortletIds();

		String portletIds = spiDefinitionCacheModel.portletIds;

		if ((portletIds != null) && (portletIds.length() == 0)) {
			spiDefinitionCacheModel.portletIds = null;
		}

		spiDefinitionCacheModel.servletContextNames = getServletContextNames();

		String servletContextNames = spiDefinitionCacheModel.servletContextNames;

		if ((servletContextNames != null) &&
				(servletContextNames.length() == 0)) {
			spiDefinitionCacheModel.servletContextNames = null;
		}

		spiDefinitionCacheModel.typeSettings = getTypeSettings();

		String typeSettings = spiDefinitionCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			spiDefinitionCacheModel.typeSettings = null;
		}

		spiDefinitionCacheModel.status = getStatus();

		spiDefinitionCacheModel.statusMessage = getStatusMessage();

		String statusMessage = spiDefinitionCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			spiDefinitionCacheModel.statusMessage = null;
		}

		return spiDefinitionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{spiDefinitionId=");
		sb.append(getSpiDefinitionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", connectorAddress=");
		sb.append(getConnectorAddress());
		sb.append(", connectorPort=");
		sb.append(getConnectorPort());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", jvmArguments=");
		sb.append(getJvmArguments());
		sb.append(", portletIds=");
		sb.append(getPortletIds());
		sb.append(", servletContextNames=");
		sb.append(getServletContextNames());
		sb.append(", typeSettings=");
		sb.append(getTypeSettings());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusMessage=");
		sb.append(getStatusMessage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.resiliency.spi.model.SPIDefinition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>spiDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getSpiDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>connectorAddress</column-name><column-value><![CDATA[");
		sb.append(getConnectorAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>connectorPort</column-name><column-value><![CDATA[");
		sb.append(getConnectorPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jvmArguments</column-name><column-value><![CDATA[");
		sb.append(getJvmArguments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletIds</column-name><column-value><![CDATA[");
		sb.append(getPortletIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>servletContextNames</column-name><column-value><![CDATA[");
		sb.append(getServletContextNames());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeSettings</column-name><column-value><![CDATA[");
		sb.append(getTypeSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusMessage</column-name><column-value><![CDATA[");
		sb.append(getStatusMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SPIDefinition.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SPIDefinition.class, ModelWrapper.class
		};
	private long _spiDefinitionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private String _connectorAddress;
	private String _originalConnectorAddress;
	private int _connectorPort;
	private int _originalConnectorPort;
	private boolean _setOriginalConnectorPort;
	private String _description;
	private String _jvmArguments;
	private String _portletIds;
	private String _servletContextNames;
	private String _typeSettings;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private String _statusMessage;
	private long _columnBitmask;
	private SPIDefinition _escapedModel;
}