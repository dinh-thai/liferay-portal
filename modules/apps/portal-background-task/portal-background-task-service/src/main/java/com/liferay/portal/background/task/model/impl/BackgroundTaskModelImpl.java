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

package com.liferay.portal.background.task.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.model.BackgroundTaskModel;
import com.liferay.portal.background.task.model.BackgroundTaskSoap;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the BackgroundTask service. Represents a row in the &quot;BackgroundTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BackgroundTaskModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BackgroundTaskImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskImpl
 * @generated
 */
@JSON(strict = true)
public class BackgroundTaskModelImpl
	extends BaseModelImpl<BackgroundTask> implements BackgroundTaskModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a background task model instance should use the <code>BackgroundTask</code> interface instead.
	 */
	public static final String TABLE_NAME = "BackgroundTask";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"backgroundTaskId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"servletContextNames", Types.VARCHAR},
		{"taskExecutorClassName", Types.VARCHAR},
		{"taskContextMap", Types.CLOB}, {"completed", Types.BOOLEAN},
		{"completionDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusMessage", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("backgroundTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("servletContextNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("taskExecutorClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("taskContextMap", Types.CLOB);
		TABLE_COLUMNS_MAP.put("completed", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("completionDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BackgroundTask (mvccVersion LONG default 0 not null,backgroundTaskId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(255) null,servletContextNames VARCHAR(255) null,taskExecutorClassName VARCHAR(200) null,taskContextMap TEXT null,completed BOOLEAN,completionDate DATE null,status INTEGER,statusMessage TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table BackgroundTask";

	public static final String ORDER_BY_JPQL =
		" ORDER BY backgroundTask.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BackgroundTask.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long COMPLETED_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long STATUS_COLUMN_BITMASK = 16L;

	public static final long TASKEXECUTORCLASSNAME_COLUMN_BITMASK = 32L;

	public static final long CREATEDATE_COLUMN_BITMASK = 64L;

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
	public static BackgroundTask toModel(BackgroundTaskSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		BackgroundTask model = new BackgroundTaskImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setBackgroundTaskId(soapModel.getBackgroundTaskId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setServletContextNames(soapModel.getServletContextNames());
		model.setTaskExecutorClassName(soapModel.getTaskExecutorClassName());
		model.setTaskContextMap(soapModel.getTaskContextMap());
		model.setCompleted(soapModel.isCompleted());
		model.setCompletionDate(soapModel.getCompletionDate());
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
	public static List<BackgroundTask> toModels(
		BackgroundTaskSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<BackgroundTask> models = new ArrayList<BackgroundTask>(
			soapModels.length);

		for (BackgroundTaskSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public BackgroundTaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _backgroundTaskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBackgroundTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _backgroundTaskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BackgroundTask.class;
	}

	@Override
	public String getModelClassName() {
		return BackgroundTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BackgroundTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BackgroundTask, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BackgroundTask)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BackgroundTask, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BackgroundTask, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BackgroundTask)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BackgroundTask, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BackgroundTask, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, BackgroundTask>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			BackgroundTask.class.getClassLoader(), BackgroundTask.class,
			ModelWrapper.class);

		try {
			Constructor<BackgroundTask> constructor =
				(Constructor<BackgroundTask>)proxyClass.getConstructor(
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

	private static final Map<String, Function<BackgroundTask, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BackgroundTask, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<BackgroundTask, Object>>();
		Map<String, BiConsumer<BackgroundTask, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<BackgroundTask, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", BackgroundTask::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setMvccVersion);
		attributeGetterFunctions.put(
			"backgroundTaskId", BackgroundTask::getBackgroundTaskId);
		attributeSetterBiConsumers.put(
			"backgroundTaskId",
			(BiConsumer<BackgroundTask, Long>)
				BackgroundTask::setBackgroundTaskId);
		attributeGetterFunctions.put("groupId", BackgroundTask::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setGroupId);
		attributeGetterFunctions.put("companyId", BackgroundTask::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setCompanyId);
		attributeGetterFunctions.put("userId", BackgroundTask::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setUserId);
		attributeGetterFunctions.put("userName", BackgroundTask::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<BackgroundTask, String>)BackgroundTask::setUserName);
		attributeGetterFunctions.put(
			"createDate", BackgroundTask::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<BackgroundTask, Date>)BackgroundTask::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", BackgroundTask::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<BackgroundTask, Date>)BackgroundTask::setModifiedDate);
		attributeGetterFunctions.put("name", BackgroundTask::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<BackgroundTask, String>)BackgroundTask::setName);
		attributeGetterFunctions.put(
			"servletContextNames", BackgroundTask::getServletContextNames);
		attributeSetterBiConsumers.put(
			"servletContextNames",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setServletContextNames);
		attributeGetterFunctions.put(
			"taskExecutorClassName", BackgroundTask::getTaskExecutorClassName);
		attributeSetterBiConsumers.put(
			"taskExecutorClassName",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setTaskExecutorClassName);
		attributeGetterFunctions.put(
			"taskContextMap", BackgroundTask::getTaskContextMap);
		attributeSetterBiConsumers.put(
			"taskContextMap",
			(BiConsumer<BackgroundTask, Map<String, Serializable>>)
				BackgroundTask::setTaskContextMap);
		attributeGetterFunctions.put("completed", BackgroundTask::getCompleted);
		attributeSetterBiConsumers.put(
			"completed",
			(BiConsumer<BackgroundTask, Boolean>)BackgroundTask::setCompleted);
		attributeGetterFunctions.put(
			"completionDate", BackgroundTask::getCompletionDate);
		attributeSetterBiConsumers.put(
			"completionDate",
			(BiConsumer<BackgroundTask, Date>)
				BackgroundTask::setCompletionDate);
		attributeGetterFunctions.put("status", BackgroundTask::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<BackgroundTask, Integer>)BackgroundTask::setStatus);
		attributeGetterFunctions.put(
			"statusMessage", BackgroundTask::getStatusMessage);
		attributeSetterBiConsumers.put(
			"statusMessage",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setStatusMessage);

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
	public long getBackgroundTaskId() {
		return _backgroundTaskId;
	}

	@Override
	public void setBackgroundTaskId(long backgroundTaskId) {
		_backgroundTaskId = backgroundTaskId;
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
		_columnBitmask = -1L;

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
	public String getTaskExecutorClassName() {
		if (_taskExecutorClassName == null) {
			return "";
		}
		else {
			return _taskExecutorClassName;
		}
	}

	@Override
	public void setTaskExecutorClassName(String taskExecutorClassName) {
		_columnBitmask |= TASKEXECUTORCLASSNAME_COLUMN_BITMASK;

		if (_originalTaskExecutorClassName == null) {
			_originalTaskExecutorClassName = _taskExecutorClassName;
		}

		_taskExecutorClassName = taskExecutorClassName;
	}

	public String getOriginalTaskExecutorClassName() {
		return GetterUtil.getString(_originalTaskExecutorClassName);
	}

	@JSON
	@Override
	public Map<String, Serializable> getTaskContextMap() {
		return _taskContextMap;
	}

	@Override
	public void setTaskContextMap(Map<String, Serializable> taskContextMap) {
		_taskContextMap = taskContextMap;
	}

	@JSON
	@Override
	public boolean getCompleted() {
		return _completed;
	}

	@JSON
	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void setCompleted(boolean completed) {
		_columnBitmask |= COMPLETED_COLUMN_BITMASK;

		if (!_setOriginalCompleted) {
			_setOriginalCompleted = true;

			_originalCompleted = _completed;
		}

		_completed = completed;
	}

	public boolean getOriginalCompleted() {
		return _originalCompleted;
	}

	@JSON
	@Override
	public Date getCompletionDate() {
		return _completionDate;
	}

	@Override
	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
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
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), BackgroundTask.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BackgroundTask toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BackgroundTask>
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
		BackgroundTaskImpl backgroundTaskImpl = new BackgroundTaskImpl();

		backgroundTaskImpl.setMvccVersion(getMvccVersion());
		backgroundTaskImpl.setBackgroundTaskId(getBackgroundTaskId());
		backgroundTaskImpl.setGroupId(getGroupId());
		backgroundTaskImpl.setCompanyId(getCompanyId());
		backgroundTaskImpl.setUserId(getUserId());
		backgroundTaskImpl.setUserName(getUserName());
		backgroundTaskImpl.setCreateDate(getCreateDate());
		backgroundTaskImpl.setModifiedDate(getModifiedDate());
		backgroundTaskImpl.setName(getName());
		backgroundTaskImpl.setServletContextNames(getServletContextNames());
		backgroundTaskImpl.setTaskExecutorClassName(getTaskExecutorClassName());
		backgroundTaskImpl.setTaskContextMap(getTaskContextMap());
		backgroundTaskImpl.setCompleted(isCompleted());
		backgroundTaskImpl.setCompletionDate(getCompletionDate());
		backgroundTaskImpl.setStatus(getStatus());
		backgroundTaskImpl.setStatusMessage(getStatusMessage());

		backgroundTaskImpl.resetOriginalValues();

		return backgroundTaskImpl;
	}

	@Override
	public int compareTo(BackgroundTask backgroundTask) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), backgroundTask.getCreateDate());

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

		if (!(obj instanceof BackgroundTask)) {
			return false;
		}

		BackgroundTask backgroundTask = (BackgroundTask)obj;

		long primaryKey = backgroundTask.getPrimaryKey();

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
		BackgroundTaskModelImpl backgroundTaskModelImpl = this;

		backgroundTaskModelImpl._originalGroupId =
			backgroundTaskModelImpl._groupId;

		backgroundTaskModelImpl._setOriginalGroupId = false;

		backgroundTaskModelImpl._originalCompanyId =
			backgroundTaskModelImpl._companyId;

		backgroundTaskModelImpl._setOriginalCompanyId = false;

		backgroundTaskModelImpl._setModifiedDate = false;

		backgroundTaskModelImpl._originalName = backgroundTaskModelImpl._name;

		backgroundTaskModelImpl._originalTaskExecutorClassName =
			backgroundTaskModelImpl._taskExecutorClassName;

		backgroundTaskModelImpl._originalCompleted =
			backgroundTaskModelImpl._completed;

		backgroundTaskModelImpl._setOriginalCompleted = false;

		backgroundTaskModelImpl._originalStatus =
			backgroundTaskModelImpl._status;

		backgroundTaskModelImpl._setOriginalStatus = false;

		backgroundTaskModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BackgroundTask> toCacheModel() {
		BackgroundTaskCacheModel backgroundTaskCacheModel =
			new BackgroundTaskCacheModel();

		backgroundTaskCacheModel.mvccVersion = getMvccVersion();

		backgroundTaskCacheModel.backgroundTaskId = getBackgroundTaskId();

		backgroundTaskCacheModel.groupId = getGroupId();

		backgroundTaskCacheModel.companyId = getCompanyId();

		backgroundTaskCacheModel.userId = getUserId();

		backgroundTaskCacheModel.userName = getUserName();

		String userName = backgroundTaskCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			backgroundTaskCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			backgroundTaskCacheModel.createDate = createDate.getTime();
		}
		else {
			backgroundTaskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			backgroundTaskCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			backgroundTaskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		backgroundTaskCacheModel.name = getName();

		String name = backgroundTaskCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			backgroundTaskCacheModel.name = null;
		}

		backgroundTaskCacheModel.servletContextNames = getServletContextNames();

		String servletContextNames =
			backgroundTaskCacheModel.servletContextNames;

		if ((servletContextNames != null) &&
			(servletContextNames.length() == 0)) {

			backgroundTaskCacheModel.servletContextNames = null;
		}

		backgroundTaskCacheModel.taskExecutorClassName =
			getTaskExecutorClassName();

		String taskExecutorClassName =
			backgroundTaskCacheModel.taskExecutorClassName;

		if ((taskExecutorClassName != null) &&
			(taskExecutorClassName.length() == 0)) {

			backgroundTaskCacheModel.taskExecutorClassName = null;
		}

		backgroundTaskCacheModel.taskContextMap = getTaskContextMap();

		backgroundTaskCacheModel.completed = isCompleted();

		Date completionDate = getCompletionDate();

		if (completionDate != null) {
			backgroundTaskCacheModel.completionDate = completionDate.getTime();
		}
		else {
			backgroundTaskCacheModel.completionDate = Long.MIN_VALUE;
		}

		backgroundTaskCacheModel.status = getStatus();

		backgroundTaskCacheModel.statusMessage = getStatusMessage();

		String statusMessage = backgroundTaskCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			backgroundTaskCacheModel.statusMessage = null;
		}

		return backgroundTaskCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BackgroundTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BackgroundTask, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((BackgroundTask)this));
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
		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<BackgroundTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BackgroundTask, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((BackgroundTask)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, BackgroundTask>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _backgroundTaskId;
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
	private String _name;
	private String _originalName;
	private String _servletContextNames;
	private String _taskExecutorClassName;
	private String _originalTaskExecutorClassName;
	private Map<String, Serializable> _taskContextMap;
	private boolean _completed;
	private boolean _originalCompleted;
	private boolean _setOriginalCompleted;
	private Date _completionDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private String _statusMessage;
	private long _columnBitmask;
	private BackgroundTask _escapedModel;

}