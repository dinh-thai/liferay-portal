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

package com.liferay.portal.reports.engine.console.service.base;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceLocalService;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionFinder;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.EntryFinder;
import com.liferay.portal.reports.engine.console.service.persistence.EntryPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.SourceFinder;
import com.liferay.portal.reports.engine.console.service.persistence.SourcePersistence;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the source local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.reports.engine.console.service.impl.SourceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.reports.engine.console.service.impl.SourceLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class SourceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements SourceLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SourceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.reports.engine.console.service.SourceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the source to the database. Also notifies the appropriate model listeners.
	 *
	 * @param source the source
	 * @return the source that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Source addSource(Source source) {
		source.setNew(true);

		return sourcePersistence.update(source);
	}

	/**
	 * Creates a new source with the primary key. Does not add the source to the database.
	 *
	 * @param sourceId the primary key for the new source
	 * @return the new source
	 */
	@Override
	@Transactional(enabled = false)
	public Source createSource(long sourceId) {
		return sourcePersistence.create(sourceId);
	}

	/**
	 * Deletes the source with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sourceId the primary key of the source
	 * @return the source that was removed
	 * @throws PortalException if a source with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Source deleteSource(long sourceId) throws PortalException {
		return sourcePersistence.remove(sourceId);
	}

	/**
	 * Deletes the source from the database. Also notifies the appropriate model listeners.
	 *
	 * @param source the source
	 * @return the source that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Source deleteSource(Source source) throws PortalException {
		return sourcePersistence.remove(source);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Source.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return sourcePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.reports.engine.console.model.impl.SourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return sourcePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.reports.engine.console.model.impl.SourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return sourcePersistence.findWithDynamicQuery(
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
		return sourcePersistence.countWithDynamicQuery(dynamicQuery);
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

		return sourcePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Source fetchSource(long sourceId) {
		return sourcePersistence.fetchByPrimaryKey(sourceId);
	}

	/**
	 * Returns the source matching the UUID and group.
	 *
	 * @param uuid the source's UUID
	 * @param groupId the primary key of the group
	 * @return the matching source, or <code>null</code> if a matching source could not be found
	 */
	@Override
	public Source fetchSourceByUuidAndGroupId(String uuid, long groupId) {
		return sourcePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the source with the primary key.
	 *
	 * @param sourceId the primary key of the source
	 * @return the source
	 * @throws PortalException if a source with the primary key could not be found
	 */
	@Override
	public Source getSource(long sourceId) throws PortalException {
		return sourcePersistence.findByPrimaryKey(sourceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(sourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Source.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sourceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(sourceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Source.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("sourceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(sourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Source.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sourceId");
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
			new ActionableDynamicQuery.PerformActionMethod<Source>() {

				@Override
				public void performAction(Source source)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, source);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Source.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return sourceLocalService.deleteSource((Source)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return sourcePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the sources matching the UUID and company.
	 *
	 * @param uuid the UUID of the sources
	 * @param companyId the primary key of the company
	 * @return the matching sources, or an empty list if no matches were found
	 */
	@Override
	public List<Source> getSourcesByUuidAndCompanyId(
		String uuid, long companyId) {

		return sourcePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of sources matching the UUID and company.
	 *
	 * @param uuid the UUID of the sources
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of sources
	 * @param end the upper bound of the range of sources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching sources, or an empty list if no matches were found
	 */
	@Override
	public List<Source> getSourcesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Source> orderByComparator) {

		return sourcePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the source matching the UUID and group.
	 *
	 * @param uuid the source's UUID
	 * @param groupId the primary key of the group
	 * @return the matching source
	 * @throws PortalException if a matching source could not be found
	 */
	@Override
	public Source getSourceByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return sourcePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the sources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.reports.engine.console.model.impl.SourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sources
	 * @param end the upper bound of the range of sources (not inclusive)
	 * @return the range of sources
	 */
	@Override
	public List<Source> getSources(int start, int end) {
		return sourcePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sources.
	 *
	 * @return the number of sources
	 */
	@Override
	public int getSourcesCount() {
		return sourcePersistence.countAll();
	}

	/**
	 * Updates the source in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param source the source
	 * @return the source that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Source updateSource(Source source) {
		return sourcePersistence.update(source);
	}

	/**
	 * Returns the definition local service.
	 *
	 * @return the definition local service
	 */
	public
		com.liferay.portal.reports.engine.console.service.DefinitionLocalService
			getDefinitionLocalService() {

		return definitionLocalService;
	}

	/**
	 * Sets the definition local service.
	 *
	 * @param definitionLocalService the definition local service
	 */
	public void setDefinitionLocalService(
		com.liferay.portal.reports.engine.console.service.DefinitionLocalService
			definitionLocalService) {

		this.definitionLocalService = definitionLocalService;
	}

	/**
	 * Returns the definition persistence.
	 *
	 * @return the definition persistence
	 */
	public DefinitionPersistence getDefinitionPersistence() {
		return definitionPersistence;
	}

	/**
	 * Sets the definition persistence.
	 *
	 * @param definitionPersistence the definition persistence
	 */
	public void setDefinitionPersistence(
		DefinitionPersistence definitionPersistence) {

		this.definitionPersistence = definitionPersistence;
	}

	/**
	 * Returns the definition finder.
	 *
	 * @return the definition finder
	 */
	public DefinitionFinder getDefinitionFinder() {
		return definitionFinder;
	}

	/**
	 * Sets the definition finder.
	 *
	 * @param definitionFinder the definition finder
	 */
	public void setDefinitionFinder(DefinitionFinder definitionFinder) {
		this.definitionFinder = definitionFinder;
	}

	/**
	 * Returns the entry local service.
	 *
	 * @return the entry local service
	 */
	public com.liferay.portal.reports.engine.console.service.EntryLocalService
		getEntryLocalService() {

		return entryLocalService;
	}

	/**
	 * Sets the entry local service.
	 *
	 * @param entryLocalService the entry local service
	 */
	public void setEntryLocalService(
		com.liferay.portal.reports.engine.console.service.EntryLocalService
			entryLocalService) {

		this.entryLocalService = entryLocalService;
	}

	/**
	 * Returns the entry persistence.
	 *
	 * @return the entry persistence
	 */
	public EntryPersistence getEntryPersistence() {
		return entryPersistence;
	}

	/**
	 * Sets the entry persistence.
	 *
	 * @param entryPersistence the entry persistence
	 */
	public void setEntryPersistence(EntryPersistence entryPersistence) {
		this.entryPersistence = entryPersistence;
	}

	/**
	 * Returns the entry finder.
	 *
	 * @return the entry finder
	 */
	public EntryFinder getEntryFinder() {
		return entryFinder;
	}

	/**
	 * Sets the entry finder.
	 *
	 * @param entryFinder the entry finder
	 */
	public void setEntryFinder(EntryFinder entryFinder) {
		this.entryFinder = entryFinder;
	}

	/**
	 * Returns the source local service.
	 *
	 * @return the source local service
	 */
	public SourceLocalService getSourceLocalService() {
		return sourceLocalService;
	}

	/**
	 * Sets the source local service.
	 *
	 * @param sourceLocalService the source local service
	 */
	public void setSourceLocalService(SourceLocalService sourceLocalService) {
		this.sourceLocalService = sourceLocalService;
	}

	/**
	 * Returns the source persistence.
	 *
	 * @return the source persistence
	 */
	public SourcePersistence getSourcePersistence() {
		return sourcePersistence;
	}

	/**
	 * Sets the source persistence.
	 *
	 * @param sourcePersistence the source persistence
	 */
	public void setSourcePersistence(SourcePersistence sourcePersistence) {
		this.sourcePersistence = sourcePersistence;
	}

	/**
	 * Returns the source finder.
	 *
	 * @return the source finder
	 */
	public SourceFinder getSourceFinder() {
		return sourceFinder;
	}

	/**
	 * Sets the source finder.
	 *
	 * @param sourceFinder the source finder
	 */
	public void setSourceFinder(SourceFinder sourceFinder) {
		this.sourceFinder = sourceFinder;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.reports.engine.console.model.Source",
			sourceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.reports.engine.console.model.Source");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SourceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Source.class;
	}

	protected String getModelClassName() {
		return Source.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = sourcePersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.portal.reports.engine.console.service.DefinitionLocalService.class
	)
	protected
		com.liferay.portal.reports.engine.console.service.DefinitionLocalService
			definitionLocalService;

	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;

	@BeanReference(type = DefinitionFinder.class)
	protected DefinitionFinder definitionFinder;

	@BeanReference(
		type = com.liferay.portal.reports.engine.console.service.EntryLocalService.class
	)
	protected
		com.liferay.portal.reports.engine.console.service.EntryLocalService
			entryLocalService;

	@BeanReference(type = EntryPersistence.class)
	protected EntryPersistence entryPersistence;

	@BeanReference(type = EntryFinder.class)
	protected EntryFinder entryFinder;

	@BeanReference(type = SourceLocalService.class)
	protected SourceLocalService sourceLocalService;

	@BeanReference(type = SourcePersistence.class)
	protected SourcePersistence sourcePersistence;

	@BeanReference(type = SourceFinder.class)
	protected SourceFinder sourceFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

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

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}