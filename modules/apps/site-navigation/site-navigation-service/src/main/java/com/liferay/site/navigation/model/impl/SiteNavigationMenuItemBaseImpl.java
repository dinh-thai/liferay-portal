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

package com.liferay.site.navigation.model.impl;

import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;

/**
 * The extended model base implementation for the SiteNavigationMenuItem service. Represents a row in the &quot;SiteNavigationMenuItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SiteNavigationMenuItemImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuItemImpl
 * @see SiteNavigationMenuItem
 * @generated
 */
public abstract class SiteNavigationMenuItemBaseImpl
	extends SiteNavigationMenuItemModelImpl implements SiteNavigationMenuItem {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a site navigation menu item model instance should use the <code>SiteNavigationMenuItem</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SiteNavigationMenuItemLocalServiceUtil.addSiteNavigationMenuItem(
				this);
		}
		else {
			SiteNavigationMenuItemLocalServiceUtil.updateSiteNavigationMenuItem(
				this);
		}
	}

}