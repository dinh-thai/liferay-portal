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

package com.liferay.osb.koroneiki.scion.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.model.AuthenticationTokenModel;
import com.liferay.osb.koroneiki.scion.model.AuthenticationTokenSoap;
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
 * The base model implementation for the AuthenticationToken service. Represents a row in the &quot;Koroneiki_AuthenticationToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>AuthenticationTokenModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AuthenticationTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenImpl
 * @generated
 */
@JSON(strict = true)
public class AuthenticationTokenModelImpl
	extends BaseModelImpl<AuthenticationToken>
	implements AuthenticationTokenModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a authentication token model instance should use the <code>AuthenticationToken</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_AuthenticationToken";

	public static final Object[][] TABLE_COLUMNS = {
		{"authenticationTokenId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"serviceProducerId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"prefix", Types.VARCHAR},
		{"digest", Types.VARCHAR}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("authenticationTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serviceProducerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("prefix", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("digest", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_AuthenticationToken (authenticationTokenId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,serviceProducerId LONG,name VARCHAR(75) null,prefix VARCHAR(75) null,digest VARCHAR(75) null,status INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_AuthenticationToken";

	public static final String ORDER_BY_JPQL =
		" ORDER BY authenticationToken.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_AuthenticationToken.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long DIGEST_COLUMN_BITMASK = 1L;

	public static final long SERVICEPRODUCERID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

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
	public static AuthenticationToken toModel(
		AuthenticationTokenSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		AuthenticationToken model = new AuthenticationTokenImpl();

		model.setAuthenticationTokenId(soapModel.getAuthenticationTokenId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setServiceProducerId(soapModel.getServiceProducerId());
		model.setName(soapModel.getName());
		model.setPrefix(soapModel.getPrefix());
		model.setDigest(soapModel.getDigest());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AuthenticationToken> toModels(
		AuthenticationTokenSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<AuthenticationToken> models = new ArrayList<AuthenticationToken>(
			soapModels.length);

		for (AuthenticationTokenSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AuthenticationTokenModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _authenticationTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAuthenticationTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _authenticationTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AuthenticationToken.class;
	}

	@Override
	public String getModelClassName() {
		return AuthenticationToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AuthenticationToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AuthenticationToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuthenticationToken, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AuthenticationToken)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AuthenticationToken, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AuthenticationToken, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AuthenticationToken)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AuthenticationToken, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AuthenticationToken, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AuthenticationToken>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AuthenticationToken.class.getClassLoader(),
			AuthenticationToken.class, ModelWrapper.class);

		try {
			Constructor<AuthenticationToken> constructor =
				(Constructor<AuthenticationToken>)proxyClass.getConstructor(
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

	private static final Map<String, Function<AuthenticationToken, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AuthenticationToken, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AuthenticationToken, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AuthenticationToken, Object>>();
		Map<String, BiConsumer<AuthenticationToken, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<AuthenticationToken, ?>>();

		attributeGetterFunctions.put(
			"authenticationTokenId",
			AuthenticationToken::getAuthenticationTokenId);
		attributeSetterBiConsumers.put(
			"authenticationTokenId",
			(BiConsumer<AuthenticationToken, Long>)
				AuthenticationToken::setAuthenticationTokenId);
		attributeGetterFunctions.put(
			"companyId", AuthenticationToken::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AuthenticationToken, Long>)
				AuthenticationToken::setCompanyId);
		attributeGetterFunctions.put("userId", AuthenticationToken::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AuthenticationToken, Long>)
				AuthenticationToken::setUserId);
		attributeGetterFunctions.put(
			"createDate", AuthenticationToken::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AuthenticationToken, Date>)
				AuthenticationToken::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AuthenticationToken::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AuthenticationToken, Date>)
				AuthenticationToken::setModifiedDate);
		attributeGetterFunctions.put(
			"serviceProducerId", AuthenticationToken::getServiceProducerId);
		attributeSetterBiConsumers.put(
			"serviceProducerId",
			(BiConsumer<AuthenticationToken, Long>)
				AuthenticationToken::setServiceProducerId);
		attributeGetterFunctions.put("name", AuthenticationToken::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<AuthenticationToken, String>)
				AuthenticationToken::setName);
		attributeGetterFunctions.put("prefix", AuthenticationToken::getPrefix);
		attributeSetterBiConsumers.put(
			"prefix",
			(BiConsumer<AuthenticationToken, String>)
				AuthenticationToken::setPrefix);
		attributeGetterFunctions.put("digest", AuthenticationToken::getDigest);
		attributeSetterBiConsumers.put(
			"digest",
			(BiConsumer<AuthenticationToken, String>)
				AuthenticationToken::setDigest);
		attributeGetterFunctions.put("status", AuthenticationToken::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<AuthenticationToken, Integer>)
				AuthenticationToken::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getAuthenticationTokenId() {
		return _authenticationTokenId;
	}

	@Override
	public void setAuthenticationTokenId(long authenticationTokenId) {
		_authenticationTokenId = authenticationTokenId;
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
	public long getServiceProducerId() {
		return _serviceProducerId;
	}

	@Override
	public void setServiceProducerId(long serviceProducerId) {
		_columnBitmask |= SERVICEPRODUCERID_COLUMN_BITMASK;

		if (!_setOriginalServiceProducerId) {
			_setOriginalServiceProducerId = true;

			_originalServiceProducerId = _serviceProducerId;
		}

		_serviceProducerId = serviceProducerId;
	}

	public long getOriginalServiceProducerId() {
		return _originalServiceProducerId;
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
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getPrefix() {
		if (_prefix == null) {
			return "";
		}
		else {
			return _prefix;
		}
	}

	@Override
	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	@JSON
	@Override
	public String getDigest() {
		if (_digest == null) {
			return "";
		}
		else {
			return _digest;
		}
	}

	@Override
	public void setDigest(String digest) {
		_columnBitmask |= DIGEST_COLUMN_BITMASK;

		if (_originalDigest == null) {
			_originalDigest = _digest;
		}

		_digest = digest;
	}

	public String getOriginalDigest() {
		return GetterUtil.getString(_originalDigest);
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AuthenticationToken.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AuthenticationToken toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AuthenticationToken>
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
		AuthenticationTokenImpl authenticationTokenImpl =
			new AuthenticationTokenImpl();

		authenticationTokenImpl.setAuthenticationTokenId(
			getAuthenticationTokenId());
		authenticationTokenImpl.setCompanyId(getCompanyId());
		authenticationTokenImpl.setUserId(getUserId());
		authenticationTokenImpl.setCreateDate(getCreateDate());
		authenticationTokenImpl.setModifiedDate(getModifiedDate());
		authenticationTokenImpl.setServiceProducerId(getServiceProducerId());
		authenticationTokenImpl.setName(getName());
		authenticationTokenImpl.setPrefix(getPrefix());
		authenticationTokenImpl.setDigest(getDigest());
		authenticationTokenImpl.setStatus(getStatus());

		authenticationTokenImpl.resetOriginalValues();

		return authenticationTokenImpl;
	}

	@Override
	public int compareTo(AuthenticationToken authenticationToken) {
		int value = 0;

		value = getName().compareTo(authenticationToken.getName());

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

		if (!(obj instanceof AuthenticationToken)) {
			return false;
		}

		AuthenticationToken authenticationToken = (AuthenticationToken)obj;

		long primaryKey = authenticationToken.getPrimaryKey();

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
		AuthenticationTokenModelImpl authenticationTokenModelImpl = this;

		authenticationTokenModelImpl._setModifiedDate = false;

		authenticationTokenModelImpl._originalServiceProducerId =
			authenticationTokenModelImpl._serviceProducerId;

		authenticationTokenModelImpl._setOriginalServiceProducerId = false;

		authenticationTokenModelImpl._originalDigest =
			authenticationTokenModelImpl._digest;

		authenticationTokenModelImpl._originalStatus =
			authenticationTokenModelImpl._status;

		authenticationTokenModelImpl._setOriginalStatus = false;

		authenticationTokenModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AuthenticationToken> toCacheModel() {
		AuthenticationTokenCacheModel authenticationTokenCacheModel =
			new AuthenticationTokenCacheModel();

		authenticationTokenCacheModel.authenticationTokenId =
			getAuthenticationTokenId();

		authenticationTokenCacheModel.companyId = getCompanyId();

		authenticationTokenCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			authenticationTokenCacheModel.createDate = createDate.getTime();
		}
		else {
			authenticationTokenCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			authenticationTokenCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			authenticationTokenCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		authenticationTokenCacheModel.serviceProducerId =
			getServiceProducerId();

		authenticationTokenCacheModel.name = getName();

		String name = authenticationTokenCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			authenticationTokenCacheModel.name = null;
		}

		authenticationTokenCacheModel.prefix = getPrefix();

		String prefix = authenticationTokenCacheModel.prefix;

		if ((prefix != null) && (prefix.length() == 0)) {
			authenticationTokenCacheModel.prefix = null;
		}

		authenticationTokenCacheModel.digest = getDigest();

		String digest = authenticationTokenCacheModel.digest;

		if ((digest != null) && (digest.length() == 0)) {
			authenticationTokenCacheModel.digest = null;
		}

		authenticationTokenCacheModel.status = getStatus();

		return authenticationTokenCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AuthenticationToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AuthenticationToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuthenticationToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AuthenticationToken)this));
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
		Map<String, Function<AuthenticationToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AuthenticationToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AuthenticationToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AuthenticationToken)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AuthenticationToken>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _authenticationTokenId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _serviceProducerId;
	private long _originalServiceProducerId;
	private boolean _setOriginalServiceProducerId;
	private String _name;
	private String _prefix;
	private String _digest;
	private String _originalDigest;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _columnBitmask;
	private AuthenticationToken _escapedModel;

}