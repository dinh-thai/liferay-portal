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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRoleModel;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRoleSoap;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
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
 * The base model implementation for the ContactTeamRole service. Represents a row in the &quot;Koroneiki_ContactTeamRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ContactTeamRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContactTeamRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleImpl
 * @generated
 */
@JSON(strict = true)
public class ContactTeamRoleModelImpl
	extends BaseModelImpl<ContactTeamRole> implements ContactTeamRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a contact team role model instance should use the <code>ContactTeamRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ContactTeamRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"contactId", Types.BIGINT}, {"teamId", Types.BIGINT},
		{"contactRoleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("contactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teamId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contactRoleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ContactTeamRole (contactId LONG not null,teamId LONG not null,contactRoleId LONG not null,primary key (contactId, teamId, contactRoleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ContactTeamRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY contactTeamRole.id.contactId ASC, contactTeamRole.id.teamId ASC, contactTeamRole.id.contactRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ContactTeamRole.contactId ASC, Koroneiki_ContactTeamRole.teamId ASC, Koroneiki_ContactTeamRole.contactRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CONTACTID_COLUMN_BITMASK = 1L;

	public static final long CONTACTROLEID_COLUMN_BITMASK = 2L;

	public static final long TEAMID_COLUMN_BITMASK = 4L;

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
	public static ContactTeamRole toModel(ContactTeamRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ContactTeamRole model = new ContactTeamRoleImpl();

		model.setContactId(soapModel.getContactId());
		model.setTeamId(soapModel.getTeamId());
		model.setContactRoleId(soapModel.getContactRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ContactTeamRole> toModels(
		ContactTeamRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ContactTeamRole> models = new ArrayList<ContactTeamRole>(
			soapModels.length);

		for (ContactTeamRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ContactTeamRoleModelImpl() {
	}

	@Override
	public ContactTeamRolePK getPrimaryKey() {
		return new ContactTeamRolePK(_contactId, _teamId, _contactRoleId);
	}

	@Override
	public void setPrimaryKey(ContactTeamRolePK primaryKey) {
		setContactId(primaryKey.contactId);
		setTeamId(primaryKey.teamId);
		setContactRoleId(primaryKey.contactRoleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ContactTeamRolePK(_contactId, _teamId, _contactRoleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ContactTeamRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return ContactTeamRole.class;
	}

	@Override
	public String getModelClassName() {
		return ContactTeamRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ContactTeamRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ContactTeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactTeamRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ContactTeamRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ContactTeamRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ContactTeamRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ContactTeamRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ContactTeamRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ContactTeamRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ContactTeamRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ContactTeamRole.class.getClassLoader(), ContactTeamRole.class,
			ModelWrapper.class);

		try {
			Constructor<ContactTeamRole> constructor =
				(Constructor<ContactTeamRole>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ContactTeamRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ContactTeamRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ContactTeamRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<ContactTeamRole, Object>>();
		Map<String, BiConsumer<ContactTeamRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ContactTeamRole, ?>>();

		attributeGetterFunctions.put(
			"contactId", ContactTeamRole::getContactId);
		attributeSetterBiConsumers.put(
			"contactId",
			(BiConsumer<ContactTeamRole, Long>)ContactTeamRole::setContactId);
		attributeGetterFunctions.put("teamId", ContactTeamRole::getTeamId);
		attributeSetterBiConsumers.put(
			"teamId",
			(BiConsumer<ContactTeamRole, Long>)ContactTeamRole::setTeamId);
		attributeGetterFunctions.put(
			"contactRoleId", ContactTeamRole::getContactRoleId);
		attributeSetterBiConsumers.put(
			"contactRoleId",
			(BiConsumer<ContactTeamRole, Long>)
				ContactTeamRole::setContactRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getContactId() {
		return _contactId;
	}

	@Override
	public void setContactId(long contactId) {
		_columnBitmask |= CONTACTID_COLUMN_BITMASK;

		if (!_setOriginalContactId) {
			_setOriginalContactId = true;

			_originalContactId = _contactId;
		}

		_contactId = contactId;
	}

	public long getOriginalContactId() {
		return _originalContactId;
	}

	@JSON
	@Override
	public long getTeamId() {
		return _teamId;
	}

	@Override
	public void setTeamId(long teamId) {
		_columnBitmask |= TEAMID_COLUMN_BITMASK;

		if (!_setOriginalTeamId) {
			_setOriginalTeamId = true;

			_originalTeamId = _teamId;
		}

		_teamId = teamId;
	}

	public long getOriginalTeamId() {
		return _originalTeamId;
	}

	@JSON
	@Override
	public long getContactRoleId() {
		return _contactRoleId;
	}

	@Override
	public void setContactRoleId(long contactRoleId) {
		_columnBitmask |= CONTACTROLEID_COLUMN_BITMASK;

		if (!_setOriginalContactRoleId) {
			_setOriginalContactRoleId = true;

			_originalContactRoleId = _contactRoleId;
		}

		_contactRoleId = contactRoleId;
	}

	public long getOriginalContactRoleId() {
		return _originalContactRoleId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ContactTeamRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ContactTeamRole>
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
		ContactTeamRoleImpl contactTeamRoleImpl = new ContactTeamRoleImpl();

		contactTeamRoleImpl.setContactId(getContactId());
		contactTeamRoleImpl.setTeamId(getTeamId());
		contactTeamRoleImpl.setContactRoleId(getContactRoleId());

		contactTeamRoleImpl.resetOriginalValues();

		return contactTeamRoleImpl;
	}

	@Override
	public int compareTo(ContactTeamRole contactTeamRole) {
		ContactTeamRolePK primaryKey = contactTeamRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactTeamRole)) {
			return false;
		}

		ContactTeamRole contactTeamRole = (ContactTeamRole)obj;

		ContactTeamRolePK primaryKey = contactTeamRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		ContactTeamRoleModelImpl contactTeamRoleModelImpl = this;

		contactTeamRoleModelImpl._originalContactId =
			contactTeamRoleModelImpl._contactId;

		contactTeamRoleModelImpl._setOriginalContactId = false;

		contactTeamRoleModelImpl._originalTeamId =
			contactTeamRoleModelImpl._teamId;

		contactTeamRoleModelImpl._setOriginalTeamId = false;

		contactTeamRoleModelImpl._originalContactRoleId =
			contactTeamRoleModelImpl._contactRoleId;

		contactTeamRoleModelImpl._setOriginalContactRoleId = false;

		contactTeamRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ContactTeamRole> toCacheModel() {
		ContactTeamRoleCacheModel contactTeamRoleCacheModel =
			new ContactTeamRoleCacheModel();

		contactTeamRoleCacheModel.contactTeamRolePK = getPrimaryKey();

		contactTeamRoleCacheModel.contactId = getContactId();

		contactTeamRoleCacheModel.teamId = getTeamId();

		contactTeamRoleCacheModel.contactRoleId = getContactRoleId();

		return contactTeamRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ContactTeamRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ContactTeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactTeamRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ContactTeamRole)this));
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
		Map<String, Function<ContactTeamRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ContactTeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactTeamRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ContactTeamRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ContactTeamRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _contactId;
	private long _originalContactId;
	private boolean _setOriginalContactId;
	private long _teamId;
	private long _originalTeamId;
	private boolean _setOriginalTeamId;
	private long _contactRoleId;
	private long _originalContactRoleId;
	private boolean _setOriginalContactRoleId;
	private long _columnBitmask;
	private ContactTeamRole _escapedModel;

}