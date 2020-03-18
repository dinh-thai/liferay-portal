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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.product.create",
		"remove.topic=koroneiki.product.delete",
		"update.topic=koroneiki.product.update"
	},
	service = ModelListener.class
)
public class ProductEntryModelListener
	extends BaseXylemModelListener<ProductEntry> {

	@Override
	protected Callable<Message> getCallable(ProductEntry productEntry)
		throws Exception {

		return () -> messageFactory.create(productEntry);
	}

}