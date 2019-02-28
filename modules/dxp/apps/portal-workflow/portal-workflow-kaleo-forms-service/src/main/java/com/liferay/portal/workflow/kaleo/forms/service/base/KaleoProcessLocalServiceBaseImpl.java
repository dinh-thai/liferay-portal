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

package com.liferay.portal.workflow.kaleo.forms.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.lists.service.persistence.DDLRecordPersistence;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordSetPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowDefinitionLinkPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessFinder;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessLinkPersistence;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the kaleo process local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLocalServiceImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class KaleoProcessLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements KaleoProcessLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KaleoProcessLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalServiceUtil</code>.
	 */

	/**
	 * Adds the kaleo process to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcess the kaleo process
	 * @return the kaleo process that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoProcess addKaleoProcess(KaleoProcess kaleoProcess) {
		kaleoProcess.setNew(true);

		return kaleoProcessPersistence.update(kaleoProcess);
	}

	/**
	 * Creates a new kaleo process with the primary key. Does not add the kaleo process to the database.
	 *
	 * @param kaleoProcessId the primary key for the new kaleo process
	 * @return the new kaleo process
	 */
	@Override
	@Transactional(enabled = false)
	public KaleoProcess createKaleoProcess(long kaleoProcessId) {
		return kaleoProcessPersistence.create(kaleoProcessId);
	}

	/**
	 * Deletes the kaleo process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process that was removed
	 * @throws PortalException if a kaleo process with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException {

		return kaleoProcessPersistence.remove(kaleoProcessId);
	}

	/**
	 * Deletes the kaleo process from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcess the kaleo process
	 * @return the kaleo process that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoProcess deleteKaleoProcess(KaleoProcess kaleoProcess)
		throws PortalException {

		return kaleoProcessPersistence.remove(kaleoProcess);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			KaleoProcess.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return kaleoProcessPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return kaleoProcessPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return kaleoProcessPersistence.findWithDynamicQuery(
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
		return kaleoProcessPersistence.countWithDynamicQuery(dynamicQuery);
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

		return kaleoProcessPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public KaleoProcess fetchKaleoProcess(long kaleoProcessId) {
		return kaleoProcessPersistence.fetchByPrimaryKey(kaleoProcessId);
	}

	/**
	 * Returns the kaleo process matching the UUID and group.
	 *
	 * @param uuid the kaleo process's UUID
	 * @param groupId the primary key of the group
	 * @return the matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	 */
	@Override
	public KaleoProcess fetchKaleoProcessByUuidAndGroupId(
		String uuid, long groupId) {

		return kaleoProcessPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the kaleo process with the primary key.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process
	 * @throws PortalException if a kaleo process with the primary key could not be found
	 */
	@Override
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException {

		return kaleoProcessPersistence.findByPrimaryKey(kaleoProcessId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(kaleoProcessLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoProcess.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kaleoProcessId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			kaleoProcessLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(KaleoProcess.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"kaleoProcessId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(kaleoProcessLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoProcess.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kaleoProcessId");
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
			new ActionableDynamicQuery.PerformActionMethod<KaleoProcess>() {

				@Override
				public void performAction(KaleoProcess kaleoProcess)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, kaleoProcess);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(KaleoProcess.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return kaleoProcessLocalService.deleteKaleoProcess(
			(KaleoProcess)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kaleoProcessPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the kaleo processes matching the UUID and company.
	 *
	 * @param uuid the UUID of the kaleo processes
	 * @param companyId the primary key of the company
	 * @return the matching kaleo processes, or an empty list if no matches were found
	 */
	@Override
	public List<KaleoProcess> getKaleoProcessesByUuidAndCompanyId(
		String uuid, long companyId) {

		return kaleoProcessPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of kaleo processes matching the UUID and company.
	 *
	 * @param uuid the UUID of the kaleo processes
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching kaleo processes, or an empty list if no matches were found
	 */
	@Override
	public List<KaleoProcess> getKaleoProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		return kaleoProcessPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the kaleo process matching the UUID and group.
	 *
	 * @param uuid the kaleo process's UUID
	 * @param groupId the primary key of the group
	 * @return the matching kaleo process
	 * @throws PortalException if a matching kaleo process could not be found
	 */
	@Override
	public KaleoProcess getKaleoProcessByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return kaleoProcessPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the kaleo processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @return the range of kaleo processes
	 */
	@Override
	public List<KaleoProcess> getKaleoProcesses(int start, int end) {
		return kaleoProcessPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of kaleo processes.
	 *
	 * @return the number of kaleo processes
	 */
	@Override
	public int getKaleoProcessesCount() {
		return kaleoProcessPersistence.countAll();
	}

	/**
	 * Updates the kaleo process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcess the kaleo process
	 * @return the kaleo process that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoProcess updateKaleoProcess(KaleoProcess kaleoProcess) {
		return kaleoProcessPersistence.update(kaleoProcess);
	}

	/**
	 * Returns the kaleo process local service.
	 *
	 * @return the kaleo process local service
	 */
	public KaleoProcessLocalService getKaleoProcessLocalService() {
		return kaleoProcessLocalService;
	}

	/**
	 * Sets the kaleo process local service.
	 *
	 * @param kaleoProcessLocalService the kaleo process local service
	 */
	public void setKaleoProcessLocalService(
		KaleoProcessLocalService kaleoProcessLocalService) {

		this.kaleoProcessLocalService = kaleoProcessLocalService;
	}

	/**
	 * Returns the kaleo process persistence.
	 *
	 * @return the kaleo process persistence
	 */
	public KaleoProcessPersistence getKaleoProcessPersistence() {
		return kaleoProcessPersistence;
	}

	/**
	 * Sets the kaleo process persistence.
	 *
	 * @param kaleoProcessPersistence the kaleo process persistence
	 */
	public void setKaleoProcessPersistence(
		KaleoProcessPersistence kaleoProcessPersistence) {

		this.kaleoProcessPersistence = kaleoProcessPersistence;
	}

	/**
	 * Returns the kaleo process finder.
	 *
	 * @return the kaleo process finder
	 */
	public KaleoProcessFinder getKaleoProcessFinder() {
		return kaleoProcessFinder;
	}

	/**
	 * Sets the kaleo process finder.
	 *
	 * @param kaleoProcessFinder the kaleo process finder
	 */
	public void setKaleoProcessFinder(KaleoProcessFinder kaleoProcessFinder) {
		this.kaleoProcessFinder = kaleoProcessFinder;
	}

	/**
	 * Returns the kaleo process link local service.
	 *
	 * @return the kaleo process link local service
	 */
	public
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService getKaleoProcessLinkLocalService() {

		return kaleoProcessLinkLocalService;
	}

	/**
	 * Sets the kaleo process link local service.
	 *
	 * @param kaleoProcessLinkLocalService the kaleo process link local service
	 */
	public void setKaleoProcessLinkLocalService(
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {

		this.kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	/**
	 * Returns the kaleo process link persistence.
	 *
	 * @return the kaleo process link persistence
	 */
	public KaleoProcessLinkPersistence getKaleoProcessLinkPersistence() {
		return kaleoProcessLinkPersistence;
	}

	/**
	 * Sets the kaleo process link persistence.
	 *
	 * @param kaleoProcessLinkPersistence the kaleo process link persistence
	 */
	public void setKaleoProcessLinkPersistence(
		KaleoProcessLinkPersistence kaleoProcessLinkPersistence) {

		this.kaleoProcessLinkPersistence = kaleoProcessLinkPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the ddl record local service.
	 *
	 * @return the ddl record local service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordLocalService
		getDDLRecordLocalService() {

		return ddlRecordLocalService;
	}

	/**
	 * Sets the ddl record local service.
	 *
	 * @param ddlRecordLocalService the ddl record local service
	 */
	public void setDDLRecordLocalService(
		com.liferay.dynamic.data.lists.service.DDLRecordLocalService
			ddlRecordLocalService) {

		this.ddlRecordLocalService = ddlRecordLocalService;
	}

	/**
	 * Returns the ddl record persistence.
	 *
	 * @return the ddl record persistence
	 */
	public DDLRecordPersistence getDDLRecordPersistence() {
		return ddlRecordPersistence;
	}

	/**
	 * Sets the ddl record persistence.
	 *
	 * @param ddlRecordPersistence the ddl record persistence
	 */
	public void setDDLRecordPersistence(
		DDLRecordPersistence ddlRecordPersistence) {

		this.ddlRecordPersistence = ddlRecordPersistence;
	}

	/**
	 * Returns the ddl record set local service.
	 *
	 * @return the ddl record set local service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
		getDDLRecordSetLocalService() {

		return ddlRecordSetLocalService;
	}

	/**
	 * Sets the ddl record set local service.
	 *
	 * @param ddlRecordSetLocalService the ddl record set local service
	 */
	public void setDDLRecordSetLocalService(
		com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
			ddlRecordSetLocalService) {

		this.ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	/**
	 * Returns the ddl record set persistence.
	 *
	 * @return the ddl record set persistence
	 */
	public DDLRecordSetPersistence getDDLRecordSetPersistence() {
		return ddlRecordSetPersistence;
	}

	/**
	 * Sets the ddl record set persistence.
	 *
	 * @param ddlRecordSetPersistence the ddl record set persistence
	 */
	public void setDDLRecordSetPersistence(
		DDLRecordSetPersistence ddlRecordSetPersistence) {

		this.ddlRecordSetPersistence = ddlRecordSetPersistence;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the workflow definition link local service.
	 *
	 * @return the workflow definition link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
		getWorkflowDefinitionLinkLocalService() {

		return workflowDefinitionLinkLocalService;
	}

	/**
	 * Sets the workflow definition link local service.
	 *
	 * @param workflowDefinitionLinkLocalService the workflow definition link local service
	 */
	public void setWorkflowDefinitionLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
			workflowDefinitionLinkLocalService) {

		this.workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	/**
	 * Returns the workflow definition link persistence.
	 *
	 * @return the workflow definition link persistence
	 */
	public WorkflowDefinitionLinkPersistence
		getWorkflowDefinitionLinkPersistence() {

		return workflowDefinitionLinkPersistence;
	}

	/**
	 * Sets the workflow definition link persistence.
	 *
	 * @param workflowDefinitionLinkPersistence the workflow definition link persistence
	 */
	public void setWorkflowDefinitionLinkPersistence(
		WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence) {

		this.workflowDefinitionLinkPersistence =
			workflowDefinitionLinkPersistence;
	}

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			kaleoProcessLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KaleoProcessLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	protected String getModelClassName() {
		return KaleoProcess.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = kaleoProcessPersistence.getDataSource();

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

	@BeanReference(type = KaleoProcessLocalService.class)
	protected KaleoProcessLocalService kaleoProcessLocalService;

	@BeanReference(type = KaleoProcessPersistence.class)
	protected KaleoProcessPersistence kaleoProcessPersistence;

	@BeanReference(type = KaleoProcessFinder.class)
	protected KaleoProcessFinder kaleoProcessFinder;

	@BeanReference(
		type = com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalService.class
	)
	protected
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService kaleoProcessLinkLocalService;

	@BeanReference(type = KaleoProcessLinkPersistence.class)
	protected KaleoProcessLinkPersistence kaleoProcessLinkPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordLocalService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordLocalService
		ddlRecordLocalService;

	@ServiceReference(type = DDLRecordPersistence.class)
	protected DDLRecordPersistence ddlRecordPersistence;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
		ddlRecordSetLocalService;

	@ServiceReference(type = DDLRecordSetPersistence.class)
	protected DDLRecordSetPersistence ddlRecordSetPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService.class
	)
	protected
		com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
			workflowDefinitionLinkLocalService;

	@ServiceReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence
		workflowDefinitionLinkPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@ServiceReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}