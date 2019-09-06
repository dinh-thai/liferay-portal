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

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalServiceUtil;

/**
 * The extended model base implementation for the ProductConsumption service. Represents a row in the &quot;Koroneiki_ProductConsumption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductConsumptionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionImpl
 * @see ProductConsumption
 * @generated
 */
public abstract class ProductConsumptionBaseImpl
	extends ProductConsumptionModelImpl implements ProductConsumption {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product consumption model instance should use the <code>ProductConsumption</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ProductConsumptionLocalServiceUtil.addProductConsumption(this);
		}
		else {
			ProductConsumptionLocalServiceUtil.updateProductConsumption(this);
		}
	}

}