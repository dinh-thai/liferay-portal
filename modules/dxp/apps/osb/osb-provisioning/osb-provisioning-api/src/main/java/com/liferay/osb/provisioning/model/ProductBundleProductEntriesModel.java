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

package com.liferay.osb.provisioning.model;

import com.liferay.osb.provisioning.service.persistence.ProductBundleProductEntriesPK;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ProductBundleProductEntries service. Represents a row in the &quot;Provisioning_ProductBundleProductEntries&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductEntriesModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductEntriesImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductEntries
 * @generated
 */
@ProviderType
public interface ProductBundleProductEntriesModel
	extends BaseModel<ProductBundleProductEntries>, MVCCModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a product bundle product entries model instance should use the {@link ProductBundleProductEntries} interface instead.
	 */

	/**
	 * Returns the primary key of this product bundle product entries.
	 *
	 * @return the primary key of this product bundle product entries
	 */
	public ProductBundleProductEntriesPK getPrimaryKey();

	/**
	 * Sets the primary key of this product bundle product entries.
	 *
	 * @param primaryKey the primary key of this product bundle product entries
	 */
	public void setPrimaryKey(ProductBundleProductEntriesPK primaryKey);

	/**
	 * Returns the mvcc version of this product bundle product entries.
	 *
	 * @return the mvcc version of this product bundle product entries
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this product bundle product entries.
	 *
	 * @param mvccVersion the mvcc version of this product bundle product entries
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the product bundle ID of this product bundle product entries.
	 *
	 * @return the product bundle ID of this product bundle product entries
	 */
	public long getProductBundleId();

	/**
	 * Sets the product bundle ID of this product bundle product entries.
	 *
	 * @param productBundleId the product bundle ID of this product bundle product entries
	 */
	public void setProductBundleId(long productBundleId);

	/**
	 * Returns the product entry ID of this product bundle product entries.
	 *
	 * @return the product entry ID of this product bundle product entries
	 */
	public long getProductEntryId();

	/**
	 * Sets the product entry ID of this product bundle product entries.
	 *
	 * @param productEntryId the product entry ID of this product bundle product entries
	 */
	public void setProductEntryId(long productEntryId);

}