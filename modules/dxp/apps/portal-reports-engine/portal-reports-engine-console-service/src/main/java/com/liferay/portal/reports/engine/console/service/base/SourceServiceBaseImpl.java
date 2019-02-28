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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceService;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionFinder;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.EntryFinder;
import com.liferay.portal.reports.engine.console.service.persistence.EntryPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.SourceFinder;
import com.liferay.portal.reports.engine.console.service.persistence.SourcePersistence;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the source remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.reports.engine.console.service.impl.SourceServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.reports.engine.console.service.impl.SourceServiceImpl
 * @generated
 */
public abstract class SourceServiceBaseImpl
	extends BaseServiceImpl implements SourceService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SourceService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.reports.engine.console.service.SourceServiceUtil</code>.
	 */

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
	 * Returns the definition remote service.
	 *
	 * @return the definition remote service
	 */
	public com.liferay.portal.reports.engine.console.service.DefinitionService
		getDefinitionService() {

		return definitionService;
	}

	/**
	 * Sets the definition remote service.
	 *
	 * @param definitionService the definition remote service
	 */
	public void setDefinitionService(
		com.liferay.portal.reports.engine.console.service.DefinitionService
			definitionService) {

		this.definitionService = definitionService;
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
	 * Returns the entry remote service.
	 *
	 * @return the entry remote service
	 */
	public com.liferay.portal.reports.engine.console.service.EntryService
		getEntryService() {

		return entryService;
	}

	/**
	 * Sets the entry remote service.
	 *
	 * @param entryService the entry remote service
	 */
	public void setEntryService(
		com.liferay.portal.reports.engine.console.service.EntryService
			entryService) {

		this.entryService = entryService;
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
	public com.liferay.portal.reports.engine.console.service.SourceLocalService
		getSourceLocalService() {

		return sourceLocalService;
	}

	/**
	 * Sets the source local service.
	 *
	 * @param sourceLocalService the source local service
	 */
	public void setSourceLocalService(
		com.liferay.portal.reports.engine.console.service.SourceLocalService
			sourceLocalService) {

		this.sourceLocalService = sourceLocalService;
	}

	/**
	 * Returns the source remote service.
	 *
	 * @return the source remote service
	 */
	public SourceService getSourceService() {
		return sourceService;
	}

	/**
	 * Sets the source remote service.
	 *
	 * @param sourceService the source remote service
	 */
	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
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
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SourceService.class.getName();
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

	@BeanReference(
		type = com.liferay.portal.reports.engine.console.service.DefinitionService.class
	)
	protected
		com.liferay.portal.reports.engine.console.service.DefinitionService
			definitionService;

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

	@BeanReference(
		type = com.liferay.portal.reports.engine.console.service.EntryService.class
	)
	protected com.liferay.portal.reports.engine.console.service.EntryService
		entryService;

	@BeanReference(type = EntryPersistence.class)
	protected EntryPersistence entryPersistence;

	@BeanReference(type = EntryFinder.class)
	protected EntryFinder entryFinder;

	@BeanReference(
		type = com.liferay.portal.reports.engine.console.service.SourceLocalService.class
	)
	protected
		com.liferay.portal.reports.engine.console.service.SourceLocalService
			sourceLocalService;

	@BeanReference(type = SourceService.class)
	protected SourceService sourceService;

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

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

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

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}