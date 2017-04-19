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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CommerceProductDefinition service. Represents a row in the &quot;CommerceProductDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CommerceProductDefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CommerceProductDefinitionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceProductDefinition
 * @see com.liferay.commerce.product.model.impl.CommerceProductDefinitionImpl
 * @see com.liferay.commerce.product.model.impl.CommerceProductDefinitionModelImpl
 * @generated
 */
@ProviderType
public interface CommerceProductDefinitionModel extends BaseModel<CommerceProductDefinition>,
	LocalizedModel, ShardedModel, StagedGroupedModel, TrashedModel,
	WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce product definition model instance should use the {@link CommerceProductDefinition} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce product definition.
	 *
	 * @return the primary key of this commerce product definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce product definition.
	 *
	 * @param primaryKey the primary key of this commerce product definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this commerce product definition.
	 *
	 * @return the uuid of this commerce product definition
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce product definition.
	 *
	 * @param uuid the uuid of this commerce product definition
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce product definition ID of this commerce product definition.
	 *
	 * @return the commerce product definition ID of this commerce product definition
	 */
	public long getCommerceProductDefinitionId();

	/**
	 * Sets the commerce product definition ID of this commerce product definition.
	 *
	 * @param commerceProductDefinitionId the commerce product definition ID of this commerce product definition
	 */
	public void setCommerceProductDefinitionId(long commerceProductDefinitionId);

	/**
	 * Returns the group ID of this commerce product definition.
	 *
	 * @return the group ID of this commerce product definition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce product definition.
	 *
	 * @param groupId the group ID of this commerce product definition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce product definition.
	 *
	 * @return the company ID of this commerce product definition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce product definition.
	 *
	 * @param companyId the company ID of this commerce product definition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce product definition.
	 *
	 * @return the user ID of this commerce product definition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce product definition.
	 *
	 * @param userId the user ID of this commerce product definition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce product definition.
	 *
	 * @return the user uuid of this commerce product definition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce product definition.
	 *
	 * @param userUuid the user uuid of this commerce product definition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce product definition.
	 *
	 * @return the user name of this commerce product definition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce product definition.
	 *
	 * @param userName the user name of this commerce product definition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce product definition.
	 *
	 * @return the create date of this commerce product definition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce product definition.
	 *
	 * @param createDate the create date of this commerce product definition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce product definition.
	 *
	 * @return the modified date of this commerce product definition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce product definition.
	 *
	 * @param modifiedDate the modified date of this commerce product definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this commerce product definition.
	 *
	 * @return the title of this commerce product definition
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this commerce product definition
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this commerce product definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this commerce product definition
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this commerce product definition
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this commerce product definition.
	 *
	 * @return the locales and localized titles of this commerce product definition
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this commerce product definition.
	 *
	 * @param title the title of this commerce product definition
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this commerce product definition in the language.
	 *
	 * @param title the localized title of this commerce product definition
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this commerce product definition in the language, and sets the default locale.
	 *
	 * @param title the localized title of this commerce product definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this commerce product definition from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this commerce product definition
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this commerce product definition from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this commerce product definition
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the url title of this commerce product definition.
	 *
	 * @return the url title of this commerce product definition
	 */
	public String getUrlTitle();

	/**
	 * Returns the localized url title of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized url title of this commerce product definition
	 */
	@AutoEscape
	public String getUrlTitle(Locale locale);

	/**
	 * Returns the localized url title of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url title of this commerce product definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getUrlTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized url title of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized url title of this commerce product definition
	 */
	@AutoEscape
	public String getUrlTitle(String languageId);

	/**
	 * Returns the localized url title of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url title of this commerce product definition
	 */
	@AutoEscape
	public String getUrlTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getUrlTitleCurrentLanguageId();

	@AutoEscape
	public String getUrlTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized url titles of this commerce product definition.
	 *
	 * @return the locales and localized url titles of this commerce product definition
	 */
	public Map<Locale, String> getUrlTitleMap();

	/**
	 * Sets the url title of this commerce product definition.
	 *
	 * @param urlTitle the url title of this commerce product definition
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Sets the localized url title of this commerce product definition in the language.
	 *
	 * @param urlTitle the localized url title of this commerce product definition
	 * @param locale the locale of the language
	 */
	public void setUrlTitle(String urlTitle, Locale locale);

	/**
	 * Sets the localized url title of this commerce product definition in the language, and sets the default locale.
	 *
	 * @param urlTitle the localized url title of this commerce product definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setUrlTitle(String urlTitle, Locale locale, Locale defaultLocale);

	public void setUrlTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized url titles of this commerce product definition from the map of locales and localized url titles.
	 *
	 * @param urlTitleMap the locales and localized url titles of this commerce product definition
	 */
	public void setUrlTitleMap(Map<Locale, String> urlTitleMap);

	/**
	 * Sets the localized url titles of this commerce product definition from the map of locales and localized url titles, and sets the default locale.
	 *
	 * @param urlTitleMap the locales and localized url titles of this commerce product definition
	 * @param defaultLocale the default locale
	 */
	public void setUrlTitleMap(Map<Locale, String> urlTitleMap,
		Locale defaultLocale);

	/**
	 * Returns the description of this commerce product definition.
	 *
	 * @return the description of this commerce product definition
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this commerce product definition
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce product definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this commerce product definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this commerce product definition
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this commerce product definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce product definition
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this commerce product definition.
	 *
	 * @return the locales and localized descriptions of this commerce product definition
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this commerce product definition.
	 *
	 * @param description the description of this commerce product definition
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this commerce product definition in the language.
	 *
	 * @param description the localized description of this commerce product definition
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this commerce product definition in the language, and sets the default locale.
	 *
	 * @param description the localized description of this commerce product definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this commerce product definition from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce product definition
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this commerce product definition from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce product definition
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the product type name of this commerce product definition.
	 *
	 * @return the product type name of this commerce product definition
	 */
	@AutoEscape
	public String getProductTypeName();

	/**
	 * Sets the product type name of this commerce product definition.
	 *
	 * @param productTypeName the product type name of this commerce product definition
	 */
	public void setProductTypeName(String productTypeName);

	/**
	 * Returns the available individually of this commerce product definition.
	 *
	 * @return the available individually of this commerce product definition
	 */
	public boolean getAvailableIndividually();

	/**
	 * Returns <code>true</code> if this commerce product definition is available individually.
	 *
	 * @return <code>true</code> if this commerce product definition is available individually; <code>false</code> otherwise
	 */
	public boolean isAvailableIndividually();

	/**
	 * Sets whether this commerce product definition is available individually.
	 *
	 * @param availableIndividually the available individually of this commerce product definition
	 */
	public void setAvailableIndividually(boolean availableIndividually);

	/**
	 * Returns the ddm structure key of this commerce product definition.
	 *
	 * @return the ddm structure key of this commerce product definition
	 */
	@AutoEscape
	public String getDDMStructureKey();

	/**
	 * Sets the ddm structure key of this commerce product definition.
	 *
	 * @param DDMStructureKey the ddm structure key of this commerce product definition
	 */
	public void setDDMStructureKey(String DDMStructureKey);

	/**
	 * Returns the base sku of this commerce product definition.
	 *
	 * @return the base sku of this commerce product definition
	 */
	@AutoEscape
	public String getBaseSKU();

	/**
	 * Sets the base sku of this commerce product definition.
	 *
	 * @param baseSKU the base sku of this commerce product definition
	 */
	public void setBaseSKU(String baseSKU);

	/**
	 * Returns the display date of this commerce product definition.
	 *
	 * @return the display date of this commerce product definition
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this commerce product definition.
	 *
	 * @param displayDate the display date of this commerce product definition
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this commerce product definition.
	 *
	 * @return the expiration date of this commerce product definition
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this commerce product definition.
	 *
	 * @param expirationDate the expiration date of this commerce product definition
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the last publish date of this commerce product definition.
	 *
	 * @return the last publish date of this commerce product definition
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce product definition.
	 *
	 * @param lastPublishDate the last publish date of this commerce product definition
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this commerce product definition.
	 *
	 * @return the status of this commerce product definition
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this commerce product definition.
	 *
	 * @param status the status of this commerce product definition
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this commerce product definition.
	 *
	 * @return the status by user ID of this commerce product definition
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this commerce product definition.
	 *
	 * @param statusByUserId the status by user ID of this commerce product definition
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this commerce product definition.
	 *
	 * @return the status by user uuid of this commerce product definition
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this commerce product definition.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce product definition
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this commerce product definition.
	 *
	 * @return the status by user name of this commerce product definition
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this commerce product definition.
	 *
	 * @param statusByUserName the status by user name of this commerce product definition
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this commerce product definition.
	 *
	 * @return the status date of this commerce product definition
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this commerce product definition.
	 *
	 * @param statusDate the status date of this commerce product definition
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this commerce product definition was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this commerce product definition.
	 *
	 * @return the trash entry created when this commerce product definition was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this commerce product definition.
	 *
	 * @return the class primary key of the trash entry for this commerce product definition
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this commerce product definition.
	 *
	 * @return the trash handler for this commerce product definition
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this commerce product definition is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this commerce product definition is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this commerce product definition is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this commerce product definition is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * Returns <code>true</code> if this commerce product definition is approved.
	 *
	 * @return <code>true</code> if this commerce product definition is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this commerce product definition is denied.
	 *
	 * @return <code>true</code> if this commerce product definition is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this commerce product definition is a draft.
	 *
	 * @return <code>true</code> if this commerce product definition is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this commerce product definition is expired.
	 *
	 * @return <code>true</code> if this commerce product definition is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this commerce product definition is inactive.
	 *
	 * @return <code>true</code> if this commerce product definition is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this commerce product definition is incomplete.
	 *
	 * @return <code>true</code> if this commerce product definition is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this commerce product definition is pending.
	 *
	 * @return <code>true</code> if this commerce product definition is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this commerce product definition is scheduled.
	 *
	 * @return <code>true</code> if this commerce product definition is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceProductDefinition commerceProductDefinition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceProductDefinition> toCacheModel();

	@Override
	public CommerceProductDefinition toEscapedModel();

	@Override
	public CommerceProductDefinition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}