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

package com.liferay.polls.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.PollsVoteLocalService;
import com.liferay.polls.service.persistence.PollsChoicePersistence;
import com.liferay.polls.service.persistence.PollsQuestionFinder;
import com.liferay.polls.service.persistence.PollsQuestionPersistence;
import com.liferay.polls.service.persistence.PollsVotePersistence;
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
 * Provides the base implementation for the polls vote local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.polls.service.impl.PollsVoteLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.polls.service.impl.PollsVoteLocalServiceImpl
 * @generated
 */
public abstract class PollsVoteLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, PollsVoteLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PollsVoteLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.polls.service.PollsVoteLocalServiceUtil</code>.
	 */

	/**
	 * Adds the polls vote to the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsVote the polls vote
	 * @return the polls vote that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PollsVote addPollsVote(PollsVote pollsVote) {
		pollsVote.setNew(true);

		return pollsVotePersistence.update(pollsVote);
	}

	/**
	 * Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	 *
	 * @param voteId the primary key for the new polls vote
	 * @return the new polls vote
	 */
	@Override
	@Transactional(enabled = false)
	public PollsVote createPollsVote(long voteId) {
		return pollsVotePersistence.create(voteId);
	}

	/**
	 * Deletes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param voteId the primary key of the polls vote
	 * @return the polls vote that was removed
	 * @throws PortalException if a polls vote with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PollsVote deletePollsVote(long voteId) throws PortalException {
		return pollsVotePersistence.remove(voteId);
	}

	/**
	 * Deletes the polls vote from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsVote the polls vote
	 * @return the polls vote that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PollsVote deletePollsVote(PollsVote pollsVote) {
		return pollsVotePersistence.remove(pollsVote);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PollsVote.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return pollsVotePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsVoteModelImpl</code>.
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

		return pollsVotePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsVoteModelImpl</code>.
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

		return pollsVotePersistence.findWithDynamicQuery(
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
		return pollsVotePersistence.countWithDynamicQuery(dynamicQuery);
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

		return pollsVotePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PollsVote fetchPollsVote(long voteId) {
		return pollsVotePersistence.fetchByPrimaryKey(voteId);
	}

	/**
	 * Returns the polls vote matching the UUID and group.
	 *
	 * @param uuid the polls vote's UUID
	 * @param groupId the primary key of the group
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 */
	@Override
	public PollsVote fetchPollsVoteByUuidAndGroupId(String uuid, long groupId) {
		return pollsVotePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the polls vote with the primary key.
	 *
	 * @param voteId the primary key of the polls vote
	 * @return the polls vote
	 * @throws PortalException if a polls vote with the primary key could not be found
	 */
	@Override
	public PollsVote getPollsVote(long voteId) throws PortalException {
		return pollsVotePersistence.findByPrimaryKey(voteId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(pollsVoteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PollsVote.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("voteId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			pollsVoteLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PollsVote.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("voteId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(pollsVoteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PollsVote.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("voteId");
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
			new ActionableDynamicQuery.PerformActionMethod<PollsVote>() {

				@Override
				public void performAction(PollsVote pollsVote)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, pollsVote);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(PollsVote.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return pollsVoteLocalService.deletePollsVote((PollsVote)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return pollsVotePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the polls votes matching the UUID and company.
	 *
	 * @param uuid the UUID of the polls votes
	 * @param companyId the primary key of the company
	 * @return the matching polls votes, or an empty list if no matches were found
	 */
	@Override
	public List<PollsVote> getPollsVotesByUuidAndCompanyId(
		String uuid, long companyId) {

		return pollsVotePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of polls votes matching the UUID and company.
	 *
	 * @param uuid the UUID of the polls votes
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching polls votes, or an empty list if no matches were found
	 */
	@Override
	public List<PollsVote> getPollsVotesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<PollsVote> orderByComparator) {

		return pollsVotePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the polls vote matching the UUID and group.
	 *
	 * @param uuid the polls vote's UUID
	 * @param groupId the primary key of the group
	 * @return the matching polls vote
	 * @throws PortalException if a matching polls vote could not be found
	 */
	@Override
	public PollsVote getPollsVoteByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return pollsVotePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the polls votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsVoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of polls votes
	 */
	@Override
	public List<PollsVote> getPollsVotes(int start, int end) {
		return pollsVotePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of polls votes.
	 *
	 * @return the number of polls votes
	 */
	@Override
	public int getPollsVotesCount() {
		return pollsVotePersistence.countAll();
	}

	/**
	 * Updates the polls vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param pollsVote the polls vote
	 * @return the polls vote that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PollsVote updatePollsVote(PollsVote pollsVote) {
		return pollsVotePersistence.update(pollsVote);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PollsVoteLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		pollsVoteLocalService = (PollsVoteLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PollsVoteLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PollsVote.class;
	}

	protected String getModelClassName() {
		return PollsVote.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = pollsVotePersistence.getDataSource();

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

	protected PollsVoteLocalService pollsVoteLocalService;

	@Reference
	protected PollsVotePersistence pollsVotePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected PollsChoicePersistence pollsChoicePersistence;

	@Reference
	protected PollsQuestionPersistence pollsQuestionPersistence;

	@Reference
	protected PollsQuestionFinder pollsQuestionFinder;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}