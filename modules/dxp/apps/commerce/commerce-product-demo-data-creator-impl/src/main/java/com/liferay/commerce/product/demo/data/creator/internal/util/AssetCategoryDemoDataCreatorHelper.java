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

package com.liferay.commerce.product.demo.data.creator.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = AssetCategoryDemoDataCreatorHelper.class)
public class AssetCategoryDemoDataCreatorHelper
	extends BaseCPDemoDataCreatorHelper {

	public AssetCategory createAssetCategory(
			long userId, long groupId, long vocabularyId, String title)
		throws PortalException {

		ServiceContext serviceContext = getServiceContext(userId, groupId);

		AssetCategory assetCategory = _assetCategoryLocalService.addCategory(
			userId, groupId, title, vocabularyId, serviceContext);

		return assetCategory;
	}

	public AssetCategory getAssetCategory(
			long userId, long groupId, long vocabularyId,
			JSONObject assetCategoryJSONObject)
		throws PortalException {

		String title = assetCategoryJSONObject.getString("title");

		AssetCategory assetCategory = _assetCategories.get(title);

		if (assetCategory != null) {
			return assetCategory;
		}

		assetCategory = createAssetCategory(
			userId, groupId, vocabularyId, title);

		_assetCategories.put(title, assetCategory);

		return assetCategory;
	}

	public long[] getAssetCategoryIds(
			long userId, long groupId, long vocabularyId,
			JSONArray assetCategoriesJSONArray)
		throws PortalException {

		List<Long> assetCategoryIds = new ArrayList<>();

		for (int i = 0; i < assetCategoriesJSONArray.length(); i++) {
			JSONObject assetCategoryJSONObject =
				assetCategoriesJSONArray.getJSONObject(i);

			AssetCategory assetCategory = getAssetCategory(
				userId, groupId, vocabularyId, assetCategoryJSONObject);

			assetCategoryIds.add(i, assetCategory.getCategoryId());
		}

		return ArrayUtil.toLongArray(assetCategoryIds);
	}

	public void init() {
		_assetCategories = new ConcurrentHashMap<>();
	}

	@Activate
	protected void activate() {
		init();
	}

	private Map<String, AssetCategory> _assetCategories;

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

}