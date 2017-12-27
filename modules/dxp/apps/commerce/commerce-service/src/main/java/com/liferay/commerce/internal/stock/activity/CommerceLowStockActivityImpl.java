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

package com.liferay.commerce.internal.stock.activity;

import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngineRegistry;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.low.stock.activity.key=" + CommerceLowStockActivityImpl.KEY,
		"commerce.low.stock.activity.priority:Integer=10"
	},
	service = com.liferay.commerce.stock.activity.CommerceLowStockActivity.class
)
public class CommerceLowStockActivityImpl
	implements com.liferay.commerce.stock.activity.CommerceLowStockActivity {

	public static final String KEY = "default";

	@Override
	public void check(CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					commerceOrderItem.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		int stockQuantity = cpDefinitionInventoryEngine.updateStockQuantity(
			commerceOrderItem);

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		if (stockQuantity <=
				cpDefinitionInventoryEngine.getMinStockQuantity(cpInstance)) {

			_updateCPInstance(cpInstance);
		}
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "set-as-unpublish");
	}

	private void _updateCPInstance(CPInstance cpInstance) {
		if (cpInstance.getPublished()) {
			cpInstance.setPublished(false);

			_cpInstanceLocalService.updateCPInstance(cpInstance);
		}
	}

	@Reference
	private CPDefinitionInventoryEngineRegistry
		_cpDefinitionInventoryEngineRegistry;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}