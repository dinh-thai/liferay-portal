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

package com.liferay.osb.provisioning.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.model.ProductBundleModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ProductBundle service. Represents a row in the &quot;Provisioning_ProductBundle&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ProductBundleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductBundleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleImpl
 * @generated
 */
public class ProductBundleModelImpl
	extends BaseModelImpl<ProductBundle> implements ProductBundleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product bundle model instance should use the <code>ProductBundle</code> interface instead.
	 */
	public static final String TABLE_NAME = "Provisioning_ProductBundle";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"productBundleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("productBundleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Provisioning_ProductBundle (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,productBundleId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Provisioning_ProductBundle";

	public static final String ORDER_BY_JPQL =
		" ORDER BY productBundle.productBundleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Provisioning_ProductBundle.productBundleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long PRODUCTBUNDLEID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public ProductBundleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _productBundleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProductBundleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _productBundleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ProductBundle.class;
	}

	@Override
	public String getModelClassName() {
		return ProductBundle.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ProductBundle, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ProductBundle, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductBundle, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ProductBundle)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ProductBundle, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ProductBundle, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ProductBundle)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ProductBundle, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ProductBundle, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ProductBundle>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ProductBundle.class.getClassLoader(), ProductBundle.class,
			ModelWrapper.class);

		try {
			Constructor<ProductBundle> constructor =
				(Constructor<ProductBundle>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ProductBundle, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ProductBundle, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ProductBundle, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ProductBundle, Object>>();
		Map<String, BiConsumer<ProductBundle, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ProductBundle, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ProductBundle::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ProductBundle, Long>)ProductBundle::setMvccVersion);
		attributeGetterFunctions.put("uuid", ProductBundle::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<ProductBundle, String>)ProductBundle::setUuid);
		attributeGetterFunctions.put(
			"productBundleId", ProductBundle::getProductBundleId);
		attributeSetterBiConsumers.put(
			"productBundleId",
			(BiConsumer<ProductBundle, Long>)ProductBundle::setProductBundleId);
		attributeGetterFunctions.put("companyId", ProductBundle::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ProductBundle, Long>)ProductBundle::setCompanyId);
		attributeGetterFunctions.put("userId", ProductBundle::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<ProductBundle, Long>)ProductBundle::setUserId);
		attributeGetterFunctions.put(
			"createDate", ProductBundle::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ProductBundle, Date>)ProductBundle::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ProductBundle::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ProductBundle, Date>)ProductBundle::setModifiedDate);
		attributeGetterFunctions.put("name", ProductBundle::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ProductBundle, String>)ProductBundle::setName);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

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

	@Override
	public long getProductBundleId() {
		return _productBundleId;
	}

	@Override
	public void setProductBundleId(long productBundleId) {
		_columnBitmask |= PRODUCTBUNDLEID_COLUMN_BITMASK;

		if (!_setOriginalProductBundleId) {
			_setOriginalProductBundleId = true;

			_originalProductBundleId = _productBundleId;
		}

		_productBundleId = productBundleId;
	}

	public long getOriginalProductBundleId() {
		return _originalProductBundleId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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
		_name = name;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ProductBundle.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ProductBundle.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ProductBundle toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ProductBundle>
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
		ProductBundleImpl productBundleImpl = new ProductBundleImpl();

		productBundleImpl.setMvccVersion(getMvccVersion());
		productBundleImpl.setUuid(getUuid());
		productBundleImpl.setProductBundleId(getProductBundleId());
		productBundleImpl.setCompanyId(getCompanyId());
		productBundleImpl.setUserId(getUserId());
		productBundleImpl.setCreateDate(getCreateDate());
		productBundleImpl.setModifiedDate(getModifiedDate());
		productBundleImpl.setName(getName());

		productBundleImpl.resetOriginalValues();

		return productBundleImpl;
	}

	@Override
	public int compareTo(ProductBundle productBundle) {
		long primaryKey = productBundle.getPrimaryKey();

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

		if (!(object instanceof ProductBundle)) {
			return false;
		}

		ProductBundle productBundle = (ProductBundle)object;

		long primaryKey = productBundle.getPrimaryKey();

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
		ProductBundleModelImpl productBundleModelImpl = this;

		productBundleModelImpl._originalUuid = productBundleModelImpl._uuid;

		productBundleModelImpl._originalProductBundleId =
			productBundleModelImpl._productBundleId;

		productBundleModelImpl._setOriginalProductBundleId = false;

		productBundleModelImpl._originalCompanyId =
			productBundleModelImpl._companyId;

		productBundleModelImpl._setOriginalCompanyId = false;

		productBundleModelImpl._setModifiedDate = false;

		productBundleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ProductBundle> toCacheModel() {
		ProductBundleCacheModel productBundleCacheModel =
			new ProductBundleCacheModel();

		productBundleCacheModel.mvccVersion = getMvccVersion();

		productBundleCacheModel.uuid = getUuid();

		String uuid = productBundleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			productBundleCacheModel.uuid = null;
		}

		productBundleCacheModel.productBundleId = getProductBundleId();

		productBundleCacheModel.companyId = getCompanyId();

		productBundleCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			productBundleCacheModel.createDate = createDate.getTime();
		}
		else {
			productBundleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			productBundleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			productBundleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		productBundleCacheModel.name = getName();

		String name = productBundleCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			productBundleCacheModel.name = null;
		}

		return productBundleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ProductBundle, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ProductBundle, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductBundle, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ProductBundle)this));
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
		Map<String, Function<ProductBundle, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ProductBundle, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductBundle, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ProductBundle)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ProductBundle>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _productBundleId;
	private long _originalProductBundleId;
	private boolean _setOriginalProductBundleId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private long _columnBitmask;
	private ProductBundle _escapedModel;

}