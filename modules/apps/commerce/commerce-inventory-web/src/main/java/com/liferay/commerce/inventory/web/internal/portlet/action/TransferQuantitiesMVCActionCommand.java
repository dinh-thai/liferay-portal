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

package com.liferay.commerce.inventory.web.internal.portlet.action;

import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemService;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_INVENTORY,
		"mvc.command.name=transferQuantities"
	},
	service = MVCActionCommand.class
)
public class TransferQuantitiesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.MOVE)) {
			moveQuantities(actionRequest);
		}
	}

	protected void moveQuantities(ActionRequest actionRequest)
		throws PortalException {

		String sku = ParamUtil.getString(actionRequest, "sku");

		long fromCommerceInventoryWarehouseId = ParamUtil.getLong(
			actionRequest, "fromCommerceInventoryWarehouseId");

		long toCommerceInventoryWarehouseId = ParamUtil.getLong(
			actionRequest, "toCommerceInventoryWarehouseId");

		int quantity = ParamUtil.getInteger(actionRequest, "quantity");

		_commerceInventoryWarehouseItemService.moveQuantitiesBetweenWarehouses(
			fromCommerceInventoryWarehouseId, toCommerceInventoryWarehouseId,
			sku, quantity);
	}

	@Reference
	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

}