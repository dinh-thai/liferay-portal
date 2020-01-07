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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMTemplate service. Represents a row in the &quot;DDMTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplate
 * @generated
 */
@ProviderType
public interface DDMTemplateModel
	extends AttachedModel, BaseModel<DDMTemplate>, LocalizedModel, MVCCModel,
			ShardedModel, StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm template model instance should use the {@link DDMTemplate} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm template.
	 *
	 * @return the primary key of this ddm template
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm template.
	 *
	 * @param primaryKey the primary key of this ddm template
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm template.
	 *
	 * @return the mvcc version of this ddm template
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm template.
	 *
	 * @param mvccVersion the mvcc version of this ddm template
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ddm template.
	 *
	 * @return the uuid of this ddm template
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ddm template.
	 *
	 * @param uuid the uuid of this ddm template
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the template ID of this ddm template.
	 *
	 * @return the template ID of this ddm template
	 */
	public long getTemplateId();

	/**
	 * Sets the template ID of this ddm template.
	 *
	 * @param templateId the template ID of this ddm template
	 */
	public void setTemplateId(long templateId);

	/**
	 * Returns the group ID of this ddm template.
	 *
	 * @return the group ID of this ddm template
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm template.
	 *
	 * @param groupId the group ID of this ddm template
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm template.
	 *
	 * @return the company ID of this ddm template
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm template.
	 *
	 * @param companyId the company ID of this ddm template
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm template.
	 *
	 * @return the user ID of this ddm template
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this ddm template.
	 *
	 * @param userId the user ID of this ddm template
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm template.
	 *
	 * @return the user uuid of this ddm template
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm template.
	 *
	 * @param userUuid the user uuid of this ddm template
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm template.
	 *
	 * @return the user name of this ddm template
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this ddm template.
	 *
	 * @param userName the user name of this ddm template
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this ddm template.
	 *
	 * @return the version user ID of this ddm template
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ddm template.
	 *
	 * @param versionUserId the version user ID of this ddm template
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ddm template.
	 *
	 * @return the version user uuid of this ddm template
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ddm template.
	 *
	 * @param versionUserUuid the version user uuid of this ddm template
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this ddm template.
	 *
	 * @return the version user name of this ddm template
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this ddm template.
	 *
	 * @param versionUserName the version user name of this ddm template
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this ddm template.
	 *
	 * @return the create date of this ddm template
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm template.
	 *
	 * @param createDate the create date of this ddm template
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddm template.
	 *
	 * @return the modified date of this ddm template
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddm template.
	 *
	 * @param modifiedDate the modified date of this ddm template
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this ddm template.
	 *
	 * @return the fully qualified class name of this ddm template
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ddm template.
	 *
	 * @return the class name ID of this ddm template
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ddm template.
	 *
	 * @param classNameId the class name ID of this ddm template
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this ddm template.
	 *
	 * @return the class pk of this ddm template
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this ddm template.
	 *
	 * @param classPK the class pk of this ddm template
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the resource class name ID of this ddm template.
	 *
	 * @return the resource class name ID of this ddm template
	 */
	public long getResourceClassNameId();

	/**
	 * Sets the resource class name ID of this ddm template.
	 *
	 * @param resourceClassNameId the resource class name ID of this ddm template
	 */
	public void setResourceClassNameId(long resourceClassNameId);

	/**
	 * Returns the template key of this ddm template.
	 *
	 * @return the template key of this ddm template
	 */
	public String getTemplateKey();

	/**
	 * Sets the template key of this ddm template.
	 *
	 * @param templateKey the template key of this ddm template
	 */
	public void setTemplateKey(String templateKey);

	/**
	 * Returns the version of this ddm template.
	 *
	 * @return the version of this ddm template
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm template.
	 *
	 * @param version the version of this ddm template
	 */
	public void setVersion(String version);

	/**
	 * Returns the name of this ddm template.
	 *
	 * @return the name of this ddm template
	 */
	public String getName();

	/**
	 * Returns the localized name of this ddm template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this ddm template
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this ddm template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this ddm template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this ddm template
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this ddm template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm template
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this ddm template.
	 *
	 * @return the locales and localized names of this ddm template
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this ddm template.
	 *
	 * @param name the name of this ddm template
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this ddm template in the language.
	 *
	 * @param name the localized name of this ddm template
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this ddm template in the language, and sets the default locale.
	 *
	 * @param name the localized name of this ddm template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this ddm template from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this ddm template
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this ddm template from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this ddm template
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this ddm template.
	 *
	 * @return the description of this ddm template
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this ddm template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this ddm template
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this ddm template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this ddm template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this ddm template
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this ddm template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm template
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this ddm template.
	 *
	 * @return the locales and localized descriptions of this ddm template
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this ddm template.
	 *
	 * @param description the description of this ddm template
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this ddm template in the language.
	 *
	 * @param description the localized description of this ddm template
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this ddm template in the language, and sets the default locale.
	 *
	 * @param description the localized description of this ddm template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this ddm template from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm template
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this ddm template from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm template
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the type of this ddm template.
	 *
	 * @return the type of this ddm template
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this ddm template.
	 *
	 * @param type the type of this ddm template
	 */
	public void setType(String type);

	/**
	 * Returns the mode of this ddm template.
	 *
	 * @return the mode of this ddm template
	 */
	@AutoEscape
	public String getMode();

	/**
	 * Sets the mode of this ddm template.
	 *
	 * @param mode the mode of this ddm template
	 */
	public void setMode(String mode);

	/**
	 * Returns the language of this ddm template.
	 *
	 * @return the language of this ddm template
	 */
	@AutoEscape
	public String getLanguage();

	/**
	 * Sets the language of this ddm template.
	 *
	 * @param language the language of this ddm template
	 */
	public void setLanguage(String language);

	/**
	 * Returns the script of this ddm template.
	 *
	 * @return the script of this ddm template
	 */
	@AutoEscape
	public String getScript();

	/**
	 * Sets the script of this ddm template.
	 *
	 * @param script the script of this ddm template
	 */
	public void setScript(String script);

	/**
	 * Returns the cacheable of this ddm template.
	 *
	 * @return the cacheable of this ddm template
	 */
	public boolean getCacheable();

	/**
	 * Returns <code>true</code> if this ddm template is cacheable.
	 *
	 * @return <code>true</code> if this ddm template is cacheable; <code>false</code> otherwise
	 */
	public boolean isCacheable();

	/**
	 * Sets whether this ddm template is cacheable.
	 *
	 * @param cacheable the cacheable of this ddm template
	 */
	public void setCacheable(boolean cacheable);

	/**
	 * Returns the small image of this ddm template.
	 *
	 * @return the small image of this ddm template
	 */
	public boolean getSmallImage();

	/**
	 * Returns <code>true</code> if this ddm template is small image.
	 *
	 * @return <code>true</code> if this ddm template is small image; <code>false</code> otherwise
	 */
	public boolean isSmallImage();

	/**
	 * Sets whether this ddm template is small image.
	 *
	 * @param smallImage the small image of this ddm template
	 */
	public void setSmallImage(boolean smallImage);

	/**
	 * Returns the small image ID of this ddm template.
	 *
	 * @return the small image ID of this ddm template
	 */
	public long getSmallImageId();

	/**
	 * Sets the small image ID of this ddm template.
	 *
	 * @param smallImageId the small image ID of this ddm template
	 */
	public void setSmallImageId(long smallImageId);

	/**
	 * Returns the small image url of this ddm template.
	 *
	 * @return the small image url of this ddm template
	 */
	@AutoEscape
	public String getSmallImageURL();

	/**
	 * Sets the small image url of this ddm template.
	 *
	 * @param smallImageURL the small image url of this ddm template
	 */
	public void setSmallImageURL(String smallImageURL);

	/**
	 * Returns the last publish date of this ddm template.
	 *
	 * @return the last publish date of this ddm template
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this ddm template.
	 *
	 * @param lastPublishDate the last publish date of this ddm template
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

}