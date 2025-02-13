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

package com.liferay.account.model.impl;

import com.liferay.account.model.AccountGroupAccountEntryRel;
import com.liferay.account.model.AccountGroupAccountEntryRelModel;
import com.liferay.account.model.AccountGroupAccountEntryRelSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the AccountGroupAccountEntryRel service. Represents a row in the &quot;AccountGroupAccountEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AccountGroupAccountEntryRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountGroupAccountEntryRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountGroupAccountEntryRelImpl
 * @generated
 */
@JSON(strict = true)
public class AccountGroupAccountEntryRelModelImpl
	extends BaseModelImpl<AccountGroupAccountEntryRel>
	implements AccountGroupAccountEntryRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account group account entry rel model instance should use the <code>AccountGroupAccountEntryRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "AccountGroupAccountEntryRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"AccountGroupAccountEntryRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"accountGroupId", Types.BIGINT},
		{"accountEntryId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("AccountGroupAccountEntryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AccountGroupAccountEntryRel (mvccVersion LONG default 0 not null,AccountGroupAccountEntryRelId LONG not null primary key,companyId LONG,accountGroupId LONG,accountEntryId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table AccountGroupAccountEntryRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY accountGroupAccountEntryRel.AccountGroupAccountEntryRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AccountGroupAccountEntryRel.AccountGroupAccountEntryRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ACCOUNTENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ACCOUNTGROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ACCOUNTGROUPACCOUNTENTRYRELID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static AccountGroupAccountEntryRel toModel(
		AccountGroupAccountEntryRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		AccountGroupAccountEntryRel model =
			new AccountGroupAccountEntryRelImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setAccountGroupAccountEntryRelId(
			soapModel.getAccountGroupAccountEntryRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setAccountGroupId(soapModel.getAccountGroupId());
		model.setAccountEntryId(soapModel.getAccountEntryId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<AccountGroupAccountEntryRel> toModels(
		AccountGroupAccountEntryRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<AccountGroupAccountEntryRel> models =
			new ArrayList<AccountGroupAccountEntryRel>(soapModels.length);

		for (AccountGroupAccountEntryRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AccountGroupAccountEntryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _AccountGroupAccountEntryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountGroupAccountEntryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _AccountGroupAccountEntryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AccountGroupAccountEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return AccountGroupAccountEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AccountGroupAccountEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AccountGroupAccountEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountGroupAccountEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(AccountGroupAccountEntryRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AccountGroupAccountEntryRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AccountGroupAccountEntryRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AccountGroupAccountEntryRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AccountGroupAccountEntryRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AccountGroupAccountEntryRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AccountGroupAccountEntryRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AccountGroupAccountEntryRel.class.getClassLoader(),
			AccountGroupAccountEntryRel.class, ModelWrapper.class);

		try {
			Constructor<AccountGroupAccountEntryRel> constructor =
				(Constructor<AccountGroupAccountEntryRel>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<AccountGroupAccountEntryRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<AccountGroupAccountEntryRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<AccountGroupAccountEntryRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AccountGroupAccountEntryRel, Object>>();
		Map<String, BiConsumer<AccountGroupAccountEntryRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<AccountGroupAccountEntryRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AccountGroupAccountEntryRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AccountGroupAccountEntryRel, Long>)
				AccountGroupAccountEntryRel::setMvccVersion);
		attributeGetterFunctions.put(
			"AccountGroupAccountEntryRelId",
			AccountGroupAccountEntryRel::getAccountGroupAccountEntryRelId);
		attributeSetterBiConsumers.put(
			"AccountGroupAccountEntryRelId",
			(BiConsumer<AccountGroupAccountEntryRel, Long>)
				AccountGroupAccountEntryRel::setAccountGroupAccountEntryRelId);
		attributeGetterFunctions.put(
			"companyId", AccountGroupAccountEntryRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AccountGroupAccountEntryRel, Long>)
				AccountGroupAccountEntryRel::setCompanyId);
		attributeGetterFunctions.put(
			"accountGroupId", AccountGroupAccountEntryRel::getAccountGroupId);
		attributeSetterBiConsumers.put(
			"accountGroupId",
			(BiConsumer<AccountGroupAccountEntryRel, Long>)
				AccountGroupAccountEntryRel::setAccountGroupId);
		attributeGetterFunctions.put(
			"accountEntryId", AccountGroupAccountEntryRel::getAccountEntryId);
		attributeSetterBiConsumers.put(
			"accountEntryId",
			(BiConsumer<AccountGroupAccountEntryRel, Long>)
				AccountGroupAccountEntryRel::setAccountEntryId);

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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getAccountGroupAccountEntryRelId() {
		return _AccountGroupAccountEntryRelId;
	}

	@Override
	public void setAccountGroupAccountEntryRelId(
		long AccountGroupAccountEntryRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_AccountGroupAccountEntryRelId = AccountGroupAccountEntryRelId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@JSON
	@Override
	public long getAccountGroupId() {
		return _accountGroupId;
	}

	@Override
	public void setAccountGroupId(long accountGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountGroupId = accountGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAccountGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("accountGroupId"));
	}

	@JSON
	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryId = accountEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAccountEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("accountEntryId"));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AccountGroupAccountEntryRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AccountGroupAccountEntryRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AccountGroupAccountEntryRel>
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
		AccountGroupAccountEntryRelImpl accountGroupAccountEntryRelImpl =
			new AccountGroupAccountEntryRelImpl();

		accountGroupAccountEntryRelImpl.setMvccVersion(getMvccVersion());
		accountGroupAccountEntryRelImpl.setAccountGroupAccountEntryRelId(
			getAccountGroupAccountEntryRelId());
		accountGroupAccountEntryRelImpl.setCompanyId(getCompanyId());
		accountGroupAccountEntryRelImpl.setAccountGroupId(getAccountGroupId());
		accountGroupAccountEntryRelImpl.setAccountEntryId(getAccountEntryId());

		accountGroupAccountEntryRelImpl.resetOriginalValues();

		return accountGroupAccountEntryRelImpl;
	}

	@Override
	public int compareTo(
		AccountGroupAccountEntryRel accountGroupAccountEntryRel) {

		long primaryKey = accountGroupAccountEntryRel.getPrimaryKey();

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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountGroupAccountEntryRel)) {
			return false;
		}

		AccountGroupAccountEntryRel accountGroupAccountEntryRel =
			(AccountGroupAccountEntryRel)object;

		long primaryKey = accountGroupAccountEntryRel.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AccountGroupAccountEntryRel> toCacheModel() {
		AccountGroupAccountEntryRelCacheModel
			accountGroupAccountEntryRelCacheModel =
				new AccountGroupAccountEntryRelCacheModel();

		accountGroupAccountEntryRelCacheModel.mvccVersion = getMvccVersion();

		accountGroupAccountEntryRelCacheModel.AccountGroupAccountEntryRelId =
			getAccountGroupAccountEntryRelId();

		accountGroupAccountEntryRelCacheModel.companyId = getCompanyId();

		accountGroupAccountEntryRelCacheModel.accountGroupId =
			getAccountGroupId();

		accountGroupAccountEntryRelCacheModel.accountEntryId =
			getAccountEntryId();

		return accountGroupAccountEntryRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AccountGroupAccountEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AccountGroupAccountEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountGroupAccountEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(AccountGroupAccountEntryRel)this));
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
		Map<String, Function<AccountGroupAccountEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AccountGroupAccountEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountGroupAccountEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(AccountGroupAccountEntryRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, AccountGroupAccountEntryRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _AccountGroupAccountEntryRelId;
	private long _companyId;
	private long _accountGroupId;
	private long _accountEntryId;

	public <T> T getColumnValue(String columnName) {
		Function<AccountGroupAccountEntryRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AccountGroupAccountEntryRel)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put(
			"AccountGroupAccountEntryRelId", _AccountGroupAccountEntryRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("accountGroupId", _accountGroupId);
		_columnOriginalValues.put("accountEntryId", _accountEntryId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("AccountGroupAccountEntryRelId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("accountGroupId", 8L);

		columnBitmasks.put("accountEntryId", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AccountGroupAccountEntryRel _escapedModel;

}