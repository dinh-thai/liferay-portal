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

package com.liferay.fragment.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.persistence.FragmentCollectionPersistence;
import com.liferay.fragment.service.persistence.FragmentEntryPersistence;
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
 * Provides the base implementation for the fragment collection local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.fragment.service.impl.FragmentCollectionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.fragment.service.impl.FragmentCollectionLocalServiceImpl
 * @generated
 */
public abstract class FragmentCollectionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FragmentCollectionLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FragmentCollectionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.fragment.service.FragmentCollectionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the fragment collection to the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentCollection the fragment collection
	 * @return the fragment collection that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentCollection addFragmentCollection(
		FragmentCollection fragmentCollection) {

		fragmentCollection.setNew(true);

		return fragmentCollectionPersistence.update(fragmentCollection);
	}

	/**
	 * Creates a new fragment collection with the primary key. Does not add the fragment collection to the database.
	 *
	 * @param fragmentCollectionId the primary key for the new fragment collection
	 * @return the new fragment collection
	 */
	@Override
	@Transactional(enabled = false)
	public FragmentCollection createFragmentCollection(
		long fragmentCollectionId) {

		return fragmentCollectionPersistence.create(fragmentCollectionId);
	}

	/**
	 * Deletes the fragment collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentCollectionId the primary key of the fragment collection
	 * @return the fragment collection that was removed
	 * @throws PortalException if a fragment collection with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentCollection deleteFragmentCollection(
			long fragmentCollectionId)
		throws PortalException {

		return fragmentCollectionPersistence.remove(fragmentCollectionId);
	}

	/**
	 * Deletes the fragment collection from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentCollection the fragment collection
	 * @return the fragment collection that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentCollection deleteFragmentCollection(
			FragmentCollection fragmentCollection)
		throws PortalException {

		return fragmentCollectionPersistence.remove(fragmentCollection);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			FragmentCollection.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return fragmentCollectionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentCollectionModelImpl</code>.
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

		return fragmentCollectionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentCollectionModelImpl</code>.
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

		return fragmentCollectionPersistence.findWithDynamicQuery(
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
		return fragmentCollectionPersistence.countWithDynamicQuery(
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

		return fragmentCollectionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FragmentCollection fetchFragmentCollection(
		long fragmentCollectionId) {

		return fragmentCollectionPersistence.fetchByPrimaryKey(
			fragmentCollectionId);
	}

	/**
	 * Returns the fragment collection matching the UUID and group.
	 *
	 * @param uuid the fragment collection's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fragment collection, or <code>null</code> if a matching fragment collection could not be found
	 */
	@Override
	public FragmentCollection fetchFragmentCollectionByUuidAndGroupId(
		String uuid, long groupId) {

		return fragmentCollectionPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fragment collection with the primary key.
	 *
	 * @param fragmentCollectionId the primary key of the fragment collection
	 * @return the fragment collection
	 * @throws PortalException if a fragment collection with the primary key could not be found
	 */
	@Override
	public FragmentCollection getFragmentCollection(long fragmentCollectionId)
		throws PortalException {

		return fragmentCollectionPersistence.findByPrimaryKey(
			fragmentCollectionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			fragmentCollectionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentCollection.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentCollectionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			fragmentCollectionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FragmentCollection.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentCollectionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			fragmentCollectionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentCollection.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentCollectionId");
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

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<FragmentCollection>() {

				@Override
				public void performAction(FragmentCollection fragmentCollection)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, fragmentCollection);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(FragmentCollection.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return fragmentCollectionLocalService.deleteFragmentCollection(
			(FragmentCollection)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fragmentCollectionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the fragment collections matching the UUID and company.
	 *
	 * @param uuid the UUID of the fragment collections
	 * @param companyId the primary key of the company
	 * @return the matching fragment collections, or an empty list if no matches were found
	 */
	@Override
	public List<FragmentCollection> getFragmentCollectionsByUuidAndCompanyId(
		String uuid, long companyId) {

		return fragmentCollectionPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of fragment collections matching the UUID and company.
	 *
	 * @param uuid the UUID of the fragment collections
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of fragment collections
	 * @param end the upper bound of the range of fragment collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching fragment collections, or an empty list if no matches were found
	 */
	@Override
	public List<FragmentCollection> getFragmentCollectionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FragmentCollection> orderByComparator) {

		return fragmentCollectionPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the fragment collection matching the UUID and group.
	 *
	 * @param uuid the fragment collection's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fragment collection
	 * @throws PortalException if a matching fragment collection could not be found
	 */
	@Override
	public FragmentCollection getFragmentCollectionByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return fragmentCollectionPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the fragment collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment collections
	 * @param end the upper bound of the range of fragment collections (not inclusive)
	 * @return the range of fragment collections
	 */
	@Override
	public List<FragmentCollection> getFragmentCollections(int start, int end) {
		return fragmentCollectionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of fragment collections.
	 *
	 * @return the number of fragment collections
	 */
	@Override
	public int getFragmentCollectionsCount() {
		return fragmentCollectionPersistence.countAll();
	}

	/**
	 * Updates the fragment collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentCollection the fragment collection
	 * @return the fragment collection that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentCollection updateFragmentCollection(
		FragmentCollection fragmentCollection) {

		return fragmentCollectionPersistence.update(fragmentCollection);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FragmentCollectionLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		fragmentCollectionLocalService =
			(FragmentCollectionLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FragmentCollectionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FragmentCollection.class;
	}

	protected String getModelClassName() {
		return FragmentCollection.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				fragmentCollectionPersistence.getDataSource();

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

	protected FragmentCollectionLocalService fragmentCollectionLocalService;

	@Reference
	protected FragmentCollectionPersistence fragmentCollectionPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected FragmentEntryPersistence fragmentEntryPersistence;

}