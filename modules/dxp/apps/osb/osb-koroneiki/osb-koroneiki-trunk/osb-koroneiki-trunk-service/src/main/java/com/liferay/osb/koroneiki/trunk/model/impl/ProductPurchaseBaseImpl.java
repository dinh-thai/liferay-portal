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

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalServiceUtil;

/**
 * The extended model base implementation for the ProductPurchase service. Represents a row in the &quot;Koroneiki_ProductPurchase&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductPurchaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseImpl
 * @see ProductPurchase
 * @generated
 */
public abstract class ProductPurchaseBaseImpl
	extends ProductPurchaseModelImpl implements ProductPurchase {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product purchase model instance should use the <code>ProductPurchase</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ProductPurchaseLocalServiceUtil.addProductPurchase(this);
		}
		else {
			ProductPurchaseLocalServiceUtil.updateProductPurchase(this);
		}
	}

}