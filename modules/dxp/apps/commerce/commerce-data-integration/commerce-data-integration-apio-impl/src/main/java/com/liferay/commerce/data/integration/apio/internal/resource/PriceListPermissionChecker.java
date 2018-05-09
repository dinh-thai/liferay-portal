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

package com.liferay.commerce.data.integration.apio.internal.resource;

import static com.liferay.commerce.price.list.constants.CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS;
import static com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory.openSingleValueMap;

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.data.integration.apio.resource.CollectionPermissionChecker;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.UserService;

import java.util.function.BiFunction;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = PriceListPermissionChecker.class)
public class PriceListPermissionChecker implements CollectionPermissionChecker {

	@Override
	public BiFunction<Credentials, Long, Boolean> forAdding(
		String resourceName) {

		return (credentials, groupId) -> {
			Try<PermissionChecker> permissionCheckerTry =
				_getPermissionCheckerTry(credentials);

			return Try.success(
				resourceName
			).map(
				_portletResourcePermissions::getService
			).flatMap(
				portletResourcePermission -> permissionCheckerTry.map(
					permissionChecker -> portletResourcePermission.contains(
						permissionChecker, groupId,
						MANAGE_COMMERCE_PRICE_LISTS))
			).orElse(
				false
			);
		};
	}

	@Override
	public BiFunction<Credentials, Long, Boolean> forDeleting(
		String resourceName) {

		return (credentials, identifier) -> {
			Try<PermissionChecker> permissionCheckerTry =
				_getPermissionCheckerTry(credentials);

			CommercePriceList commercePriceList =
				_commercePriceListService.fetchCommercePriceList(identifier);

			return Try.success(
				resourceName
			).map(
				_portletResourcePermissions::getService
			).flatMap(
				portletResourcePermission -> permissionCheckerTry.map(
					permissionChecker -> portletResourcePermission.contains(
						permissionChecker, commercePriceList.getGroupId(),
						MANAGE_COMMERCE_PRICE_LISTS))
			).orElse(
				false
			);
		};
	}

	@Override
	public BiFunction<Credentials, Long, Boolean> forUpdating(
		String resourceName) {

		return (credentials, identifier) -> {
			Try<PermissionChecker> permissionCheckerTry =
				_getPermissionCheckerTry(credentials);

			CommercePriceList commercePriceList =
				_commercePriceListService.fetchCommercePriceList(identifier);

			return Try.success(
				resourceName
			).map(
				_portletResourcePermissions::getService
			).flatMap(
				portletResourcePermission -> permissionCheckerTry.map(
					permissionChecker -> portletResourcePermission.contains(
						permissionChecker, commercePriceList.getGroupId(),
						MANAGE_COMMERCE_PRICE_LISTS))
			).orElse(
				false
			);
		};
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_portletResourcePermissions = openSingleValueMap(
			bundleContext, PortletResourcePermission.class, "resource.name");
	}

	private Try<PermissionChecker> _getPermissionCheckerTry(
		Credentials credentials) {

		return Try.success(
			credentials.get()
		).map(
			Long::valueOf
		).map(
			_userService::getUserById
		).map(
			PermissionCheckerFactoryUtil::create
		).recoverWith(
			__ -> Try.fromFallible(PermissionThreadLocal::getPermissionChecker)
		);
	}

	@Reference
	private CommercePriceListService _commercePriceListService;

	private ServiceTrackerMap<String, PortletResourcePermission>
		_portletResourcePermissions;

	@Reference
	private UserService _userService;

}