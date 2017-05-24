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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinitionLocalization;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CPDefinitionLocalization in entity cache.
 *
 * @author Marco Leo
 * @see CPDefinitionLocalization
 * @generated
 */
@ProviderType
public class CPDefinitionLocalizationCacheModel implements CacheModel<CPDefinitionLocalization>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionLocalizationCacheModel)) {
			return false;
		}

		CPDefinitionLocalizationCacheModel cpDefinitionLocalizationCacheModel = (CPDefinitionLocalizationCacheModel)obj;

		if ((cpDefinitionLocalizationId == cpDefinitionLocalizationCacheModel.cpDefinitionLocalizationId) &&
				(mvccVersion == cpDefinitionLocalizationCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, cpDefinitionLocalizationId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", cpDefinitionLocalizationId=");
		sb.append(cpDefinitionLocalizationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", urlTitle=");
		sb.append(urlTitle);
		sb.append(", shortDescription=");
		sb.append(shortDescription);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinitionLocalization toEntityModel() {
		CPDefinitionLocalizationImpl cpDefinitionLocalizationImpl = new CPDefinitionLocalizationImpl();

		cpDefinitionLocalizationImpl.setMvccVersion(mvccVersion);
		cpDefinitionLocalizationImpl.setCpDefinitionLocalizationId(cpDefinitionLocalizationId);
		cpDefinitionLocalizationImpl.setCompanyId(companyId);
		cpDefinitionLocalizationImpl.setCPDefinitionId(CPDefinitionId);

		if (languageId == null) {
			cpDefinitionLocalizationImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			cpDefinitionLocalizationImpl.setLanguageId(languageId);
		}

		if (title == null) {
			cpDefinitionLocalizationImpl.setTitle(StringPool.BLANK);
		}
		else {
			cpDefinitionLocalizationImpl.setTitle(title);
		}

		if (urlTitle == null) {
			cpDefinitionLocalizationImpl.setUrlTitle(StringPool.BLANK);
		}
		else {
			cpDefinitionLocalizationImpl.setUrlTitle(urlTitle);
		}

		if (shortDescription == null) {
			cpDefinitionLocalizationImpl.setShortDescription(StringPool.BLANK);
		}
		else {
			cpDefinitionLocalizationImpl.setShortDescription(shortDescription);
		}

		if (description == null) {
			cpDefinitionLocalizationImpl.setDescription(StringPool.BLANK);
		}
		else {
			cpDefinitionLocalizationImpl.setDescription(description);
		}

		cpDefinitionLocalizationImpl.resetOriginalValues();

		return cpDefinitionLocalizationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		cpDefinitionLocalizationId = objectInput.readLong();

		companyId = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();
		languageId = objectInput.readUTF();
		title = objectInput.readUTF();
		urlTitle = objectInput.readUTF();
		shortDescription = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(cpDefinitionLocalizationId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(CPDefinitionId);

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (urlTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(urlTitle);
		}

		if (shortDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortDescription);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long mvccVersion;
	public long cpDefinitionLocalizationId;
	public long companyId;
	public long CPDefinitionId;
	public String languageId;
	public String title;
	public String urlTitle;
	public String shortDescription;
	public String description;
}