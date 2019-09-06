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

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchaseModel;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap;
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
 * The base model implementation for the ProductPurchase service. Represents a row in the &quot;Koroneiki_ProductPurchase&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ProductPurchaseModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductPurchaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseImpl
 * @generated
 */
@JSON(strict = true)
public class ProductPurchaseModelImpl
	extends BaseModelImpl<ProductPurchase> implements ProductPurchaseModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product purchase model instance should use the <code>ProductPurchase</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ProductPurchase";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"productPurchaseId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"productPurchaseKey", Types.VARCHAR}, {"accountId", Types.BIGINT},
		{"productEntryId", Types.BIGINT}, {"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP}, {"quantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("productPurchaseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("productPurchaseKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("productEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ProductPurchase (uuid_ VARCHAR(75) null,productPurchaseId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,productPurchaseKey VARCHAR(75) null,accountId LONG,productEntryId LONG,startDate DATE null,endDate DATE null,quantity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ProductPurchase";

	public static final String ORDER_BY_JPQL =
		" ORDER BY productPurchase.productPurchaseId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ProductPurchase.productPurchaseId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long PRODUCTPURCHASEKEY_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long PRODUCTPURCHASEID_COLUMN_BITMASK = 16L;

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
	public static ProductPurchase toModel(ProductPurchaseSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ProductPurchase model = new ProductPurchaseImpl();

		model.setUuid(soapModel.getUuid());
		model.setProductPurchaseId(soapModel.getProductPurchaseId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setProductPurchaseKey(soapModel.getProductPurchaseKey());
		model.setAccountId(soapModel.getAccountId());
		model.setProductEntryId(soapModel.getProductEntryId());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setQuantity(soapModel.getQuantity());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ProductPurchase> toModels(
		ProductPurchaseSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ProductPurchase> models = new ArrayList<ProductPurchase>(
			soapModels.length);

		for (ProductPurchaseSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ProductPurchaseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _productPurchaseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProductPurchaseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _productPurchaseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ProductPurchase.class;
	}

	@Override
	public String getModelClassName() {
		return ProductPurchase.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ProductPurchase, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ProductPurchase, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductPurchase, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ProductPurchase)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ProductPurchase, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ProductPurchase, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ProductPurchase)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ProductPurchase, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ProductPurchase, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ProductPurchase>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ProductPurchase.class.getClassLoader(), ProductPurchase.class,
			ModelWrapper.class);

		try {
			Constructor<ProductPurchase> constructor =
				(Constructor<ProductPurchase>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ProductPurchase, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ProductPurchase, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ProductPurchase, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<ProductPurchase, Object>>();
		Map<String, BiConsumer<ProductPurchase, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ProductPurchase, ?>>();

		attributeGetterFunctions.put("uuid", ProductPurchase::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<ProductPurchase, String>)ProductPurchase::setUuid);
		attributeGetterFunctions.put(
			"productPurchaseId", ProductPurchase::getProductPurchaseId);
		attributeSetterBiConsumers.put(
			"productPurchaseId",
			(BiConsumer<ProductPurchase, Long>)
				ProductPurchase::setProductPurchaseId);
		attributeGetterFunctions.put(
			"companyId", ProductPurchase::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ProductPurchase, Long>)ProductPurchase::setCompanyId);
		attributeGetterFunctions.put("userId", ProductPurchase::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<ProductPurchase, Long>)ProductPurchase::setUserId);
		attributeGetterFunctions.put(
			"createDate", ProductPurchase::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ProductPurchase, Date>)ProductPurchase::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ProductPurchase::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ProductPurchase, Date>)
				ProductPurchase::setModifiedDate);
		attributeGetterFunctions.put(
			"productPurchaseKey", ProductPurchase::getProductPurchaseKey);
		attributeSetterBiConsumers.put(
			"productPurchaseKey",
			(BiConsumer<ProductPurchase, String>)
				ProductPurchase::setProductPurchaseKey);
		attributeGetterFunctions.put(
			"accountId", ProductPurchase::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId",
			(BiConsumer<ProductPurchase, Long>)ProductPurchase::setAccountId);
		attributeGetterFunctions.put(
			"productEntryId", ProductPurchase::getProductEntryId);
		attributeSetterBiConsumers.put(
			"productEntryId",
			(BiConsumer<ProductPurchase, Long>)
				ProductPurchase::setProductEntryId);
		attributeGetterFunctions.put(
			"startDate", ProductPurchase::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate",
			(BiConsumer<ProductPurchase, Date>)ProductPurchase::setStartDate);
		attributeGetterFunctions.put("endDate", ProductPurchase::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate",
			(BiConsumer<ProductPurchase, Date>)ProductPurchase::setEndDate);
		attributeGetterFunctions.put("quantity", ProductPurchase::getQuantity);
		attributeSetterBiConsumers.put(
			"quantity",
			(BiConsumer<ProductPurchase, Integer>)ProductPurchase::setQuantity);

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
	public long getProductPurchaseId() {
		return _productPurchaseId;
	}

	@Override
	public void setProductPurchaseId(long productPurchaseId) {
		_productPurchaseId = productPurchaseId;
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
	public String getProductPurchaseKey() {
		if (_productPurchaseKey == null) {
			return "";
		}
		else {
			return _productPurchaseKey;
		}
	}

	@Override
	public void setProductPurchaseKey(String productPurchaseKey) {
		_columnBitmask |= PRODUCTPURCHASEKEY_COLUMN_BITMASK;

		if (_originalProductPurchaseKey == null) {
			_originalProductPurchaseKey = _productPurchaseKey;
		}

		_productPurchaseKey = productPurchaseKey;
	}

	public String getOriginalProductPurchaseKey() {
		return GetterUtil.getString(_originalProductPurchaseKey);
	}

	@JSON
	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_columnBitmask |= ACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalAccountId) {
			_setOriginalAccountId = true;

			_originalAccountId = _accountId;
		}

		_accountId = accountId;
	}

	public long getOriginalAccountId() {
		return _originalAccountId;
	}

	@JSON
	@Override
	public long getProductEntryId() {
		return _productEntryId;
	}

	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ProductPurchase.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ProductPurchase.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ProductPurchase toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ProductPurchase>
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
		ProductPurchaseImpl productPurchaseImpl = new ProductPurchaseImpl();

		productPurchaseImpl.setUuid(getUuid());
		productPurchaseImpl.setProductPurchaseId(getProductPurchaseId());
		productPurchaseImpl.setCompanyId(getCompanyId());
		productPurchaseImpl.setUserId(getUserId());
		productPurchaseImpl.setCreateDate(getCreateDate());
		productPurchaseImpl.setModifiedDate(getModifiedDate());
		productPurchaseImpl.setProductPurchaseKey(getProductPurchaseKey());
		productPurchaseImpl.setAccountId(getAccountId());
		productPurchaseImpl.setProductEntryId(getProductEntryId());
		productPurchaseImpl.setStartDate(getStartDate());
		productPurchaseImpl.setEndDate(getEndDate());
		productPurchaseImpl.setQuantity(getQuantity());

		productPurchaseImpl.resetOriginalValues();

		return productPurchaseImpl;
	}

	@Override
	public int compareTo(ProductPurchase productPurchase) {
		long primaryKey = productPurchase.getPrimaryKey();

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

		if (!(obj instanceof ProductPurchase)) {
			return false;
		}

		ProductPurchase productPurchase = (ProductPurchase)obj;

		long primaryKey = productPurchase.getPrimaryKey();

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
		ProductPurchaseModelImpl productPurchaseModelImpl = this;

		productPurchaseModelImpl._originalUuid = productPurchaseModelImpl._uuid;

		productPurchaseModelImpl._originalCompanyId =
			productPurchaseModelImpl._companyId;

		productPurchaseModelImpl._setOriginalCompanyId = false;

		productPurchaseModelImpl._setModifiedDate = false;

		productPurchaseModelImpl._originalProductPurchaseKey =
			productPurchaseModelImpl._productPurchaseKey;

		productPurchaseModelImpl._originalAccountId =
			productPurchaseModelImpl._accountId;

		productPurchaseModelImpl._setOriginalAccountId = false;

		productPurchaseModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ProductPurchase> toCacheModel() {
		ProductPurchaseCacheModel productPurchaseCacheModel =
			new ProductPurchaseCacheModel();

		productPurchaseCacheModel.uuid = getUuid();

		String uuid = productPurchaseCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			productPurchaseCacheModel.uuid = null;
		}

		productPurchaseCacheModel.productPurchaseId = getProductPurchaseId();

		productPurchaseCacheModel.companyId = getCompanyId();

		productPurchaseCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			productPurchaseCacheModel.createDate = createDate.getTime();
		}
		else {
			productPurchaseCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			productPurchaseCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			productPurchaseCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		productPurchaseCacheModel.productPurchaseKey = getProductPurchaseKey();

		String productPurchaseKey =
			productPurchaseCacheModel.productPurchaseKey;

		if ((productPurchaseKey != null) &&
			(productPurchaseKey.length() == 0)) {

			productPurchaseCacheModel.productPurchaseKey = null;
		}

		productPurchaseCacheModel.accountId = getAccountId();

		productPurchaseCacheModel.productEntryId = getProductEntryId();

		Date startDate = getStartDate();

		if (startDate != null) {
			productPurchaseCacheModel.startDate = startDate.getTime();
		}
		else {
			productPurchaseCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			productPurchaseCacheModel.endDate = endDate.getTime();
		}
		else {
			productPurchaseCacheModel.endDate = Long.MIN_VALUE;
		}

		productPurchaseCacheModel.quantity = getQuantity();

		return productPurchaseCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ProductPurchase, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ProductPurchase, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductPurchase, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ProductPurchase)this));
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
		Map<String, Function<ProductPurchase, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ProductPurchase, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductPurchase, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ProductPurchase)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ProductPurchase>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _productPurchaseId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _productPurchaseKey;
	private String _originalProductPurchaseKey;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private long _productEntryId;
	private Date _startDate;
	private Date _endDate;
	private int _quantity;
	private long _columnBitmask;
	private ProductPurchase _escapedModel;

}