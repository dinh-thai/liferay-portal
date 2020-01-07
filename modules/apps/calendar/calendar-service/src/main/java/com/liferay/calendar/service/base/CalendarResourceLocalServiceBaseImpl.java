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

package com.liferay.calendar.service.base;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalService;
import com.liferay.calendar.service.persistence.CalendarBookingFinder;
import com.liferay.calendar.service.persistence.CalendarBookingPersistence;
import com.liferay.calendar.service.persistence.CalendarFinder;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplatePersistence;
import com.liferay.calendar.service.persistence.CalendarPersistence;
import com.liferay.calendar.service.persistence.CalendarResourceFinder;
import com.liferay.calendar.service.persistence.CalendarResourcePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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
 * Provides the base implementation for the calendar resource local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl
 * @generated
 */
public abstract class CalendarResourceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CalendarResourceLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CalendarResourceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.calendar.service.CalendarResourceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CalendarResource addCalendarResource(
		CalendarResource calendarResource) {

		calendarResource.setNew(true);

		return calendarResourcePersistence.update(calendarResource);
	}

	/**
	 * Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	 *
	 * @param calendarResourceId the primary key for the new calendar resource
	 * @return the new calendar resource
	 */
	@Override
	@Transactional(enabled = false)
	public CalendarResource createCalendarResource(long calendarResourceId) {
		return calendarResourcePersistence.create(calendarResourceId);
	}

	/**
	 * Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource that was removed
	 * @throws PortalException if a calendar resource with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CalendarResource deleteCalendarResource(long calendarResourceId)
		throws PortalException {

		return calendarResourcePersistence.remove(calendarResourceId);
	}

	/**
	 * Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CalendarResource deleteCalendarResource(
			CalendarResource calendarResource)
		throws PortalException {

		return calendarResourcePersistence.remove(calendarResource);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CalendarResource.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return calendarResourcePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
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

		return calendarResourcePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
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

		return calendarResourcePersistence.findWithDynamicQuery(
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
		return calendarResourcePersistence.countWithDynamicQuery(dynamicQuery);
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

		return calendarResourcePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CalendarResource fetchCalendarResource(long calendarResourceId) {
		return calendarResourcePersistence.fetchByPrimaryKey(
			calendarResourceId);
	}

	/**
	 * Returns the calendar resource matching the UUID and group.
	 *
	 * @param uuid the calendar resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 */
	@Override
	public CalendarResource fetchCalendarResourceByUuidAndGroupId(
		String uuid, long groupId) {

		return calendarResourcePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the calendar resource with the primary key.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource
	 * @throws PortalException if a calendar resource with the primary key could not be found
	 */
	@Override
	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException {

		return calendarResourcePersistence.findByPrimaryKey(calendarResourceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			calendarResourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CalendarResource.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("calendarResourceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			calendarResourceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CalendarResource.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"calendarResourceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			calendarResourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CalendarResource.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("calendarResourceId");
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

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CalendarResource>() {

				@Override
				public void performAction(CalendarResource calendarResource)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, calendarResource);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CalendarResource.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return calendarResourceLocalService.deleteCalendarResource(
			(CalendarResource)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return calendarResourcePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the calendar resources matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar resources
	 * @param companyId the primary key of the company
	 * @return the matching calendar resources, or an empty list if no matches were found
	 */
	@Override
	public List<CalendarResource> getCalendarResourcesByUuidAndCompanyId(
		String uuid, long companyId) {

		return calendarResourcePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of calendar resources matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar resources
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching calendar resources, or an empty list if no matches were found
	 */
	@Override
	public List<CalendarResource> getCalendarResourcesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return calendarResourcePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the calendar resource matching the UUID and group.
	 *
	 * @param uuid the calendar resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar resource
	 * @throws PortalException if a matching calendar resource could not be found
	 */
	@Override
	public CalendarResource getCalendarResourceByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return calendarResourcePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the calendar resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of calendar resources
	 */
	@Override
	public List<CalendarResource> getCalendarResources(int start, int end) {
		return calendarResourcePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of calendar resources.
	 *
	 * @return the number of calendar resources
	 */
	@Override
	public int getCalendarResourcesCount() {
		return calendarResourcePersistence.countAll();
	}

	/**
	 * Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CalendarResource updateCalendarResource(
		CalendarResource calendarResource) {

		return calendarResourcePersistence.update(calendarResource);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CalendarResourceLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		calendarResourceLocalService = (CalendarResourceLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CalendarResourceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CalendarResource.class;
	}

	protected String getModelClassName() {
		return CalendarResource.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = calendarResourcePersistence.getDataSource();

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

	@Reference
	protected CalendarPersistence calendarPersistence;

	@Reference
	protected CalendarFinder calendarFinder;

	@Reference
	protected CalendarBookingPersistence calendarBookingPersistence;

	@Reference
	protected CalendarBookingFinder calendarBookingFinder;

	@Reference
	protected CalendarNotificationTemplatePersistence
		calendarNotificationTemplatePersistence;

	protected CalendarResourceLocalService calendarResourceLocalService;

	@Reference
	protected CalendarResourcePersistence calendarResourcePersistence;

	@Reference
	protected CalendarResourceFinder calendarResourceFinder;

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

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

}