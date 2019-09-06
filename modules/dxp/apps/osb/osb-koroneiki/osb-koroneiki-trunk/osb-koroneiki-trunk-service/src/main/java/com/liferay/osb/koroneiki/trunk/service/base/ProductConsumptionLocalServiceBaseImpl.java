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

package com.liferay.osb.koroneiki.trunk.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductConsumptionPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductEntryPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductFieldPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductPurchasePersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the product consumption local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionLocalServiceImpl
 * @generated
 */
public abstract class ProductConsumptionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements ProductConsumptionLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ProductConsumptionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the product consumption to the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ProductConsumption addProductConsumption(
		ProductConsumption productConsumption) {

		productConsumption.setNew(true);

		return productConsumptionPersistence.update(productConsumption);
	}

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	@Override
	@Transactional(enabled = false)
	public ProductConsumption createProductConsumption(
		long productConsumptionId) {

		return productConsumptionPersistence.create(productConsumptionId);
	}

	/**
	 * Deletes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws PortalException if a product consumption with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return productConsumptionPersistence.remove(productConsumptionId);
	}

	/**
	 * Deletes the product consumption from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ProductConsumption deleteProductConsumption(
			ProductConsumption productConsumption)
		throws PortalException {

		return productConsumptionPersistence.remove(productConsumption);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			ProductConsumption.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return productConsumptionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return productConsumptionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return productConsumptionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return productConsumptionPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return productConsumptionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public ProductConsumption fetchProductConsumption(
		long productConsumptionId) {

		return productConsumptionPersistence.fetchByPrimaryKey(
			productConsumptionId);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchProductConsumptionByUuidAndCompanyId(
		String uuid, long companyId) {

		return productConsumptionPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the product consumption with the primary key.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws PortalException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption getProductConsumption(long productConsumptionId)
		throws PortalException {

		return productConsumptionPersistence.findByPrimaryKey(
			productConsumptionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			productConsumptionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ProductConsumption.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"productConsumptionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			productConsumptionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ProductConsumption.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"productConsumptionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			productConsumptionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ProductConsumption.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"productConsumptionId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<ProductConsumption>() {

				@Override
				public void performAction(ProductConsumption productConsumption)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, productConsumption);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(ProductConsumption.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return productConsumptionLocalService.deleteProductConsumption(
			(ProductConsumption)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return productConsumptionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption
	 * @throws PortalException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption getProductConsumptionByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return productConsumptionPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of product consumptions
	 */
	@Override
	public List<ProductConsumption> getProductConsumptions(int start, int end) {
		return productConsumptionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
	 */
	@Override
	public int getProductConsumptionsCount() {
		return productConsumptionPersistence.countAll();
	}

	/**
	 * Updates the product consumption in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ProductConsumption updateProductConsumption(
		ProductConsumption productConsumption) {

		return productConsumptionPersistence.update(productConsumption);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ProductConsumptionLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		productConsumptionLocalService =
			(ProductConsumptionLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ProductConsumptionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ProductConsumption.class;
	}

	protected String getModelClassName() {
		return ProductConsumption.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				productConsumptionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected ProductConsumptionLocalService productConsumptionLocalService;

	@Reference
	protected ProductConsumptionPersistence productConsumptionPersistence;

	@Reference
	protected ProductEntryPersistence productEntryPersistence;

	@Reference
	protected ProductFieldPersistence productFieldPersistence;

	@Reference
	protected ProductPurchasePersistence productPurchasePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}