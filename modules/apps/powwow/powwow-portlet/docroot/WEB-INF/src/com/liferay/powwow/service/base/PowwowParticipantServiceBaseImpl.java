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

package com.liferay.powwow.service.base;

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
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.PowwowParticipantService;
import com.liferay.powwow.service.persistence.PowwowMeetingFinder;
import com.liferay.powwow.service.persistence.PowwowMeetingPersistence;
import com.liferay.powwow.service.persistence.PowwowParticipantPersistence;
import com.liferay.powwow.service.persistence.PowwowServerPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the powwow participant remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.powwow.service.impl.PowwowParticipantServiceImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see com.liferay.powwow.service.impl.PowwowParticipantServiceImpl
 * @generated
 */
public abstract class PowwowParticipantServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, PowwowParticipantService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PowwowParticipantService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.powwow.service.PowwowParticipantServiceUtil</code>.
	 */

	/**
	 * Returns the powwow meeting local service.
	 *
	 * @return the powwow meeting local service
	 */
	public com.liferay.powwow.service.PowwowMeetingLocalService
		getPowwowMeetingLocalService() {

		return powwowMeetingLocalService;
	}

	/**
	 * Sets the powwow meeting local service.
	 *
	 * @param powwowMeetingLocalService the powwow meeting local service
	 */
	public void setPowwowMeetingLocalService(
		com.liferay.powwow.service.PowwowMeetingLocalService
			powwowMeetingLocalService) {

		this.powwowMeetingLocalService = powwowMeetingLocalService;
	}

	/**
	 * Returns the powwow meeting remote service.
	 *
	 * @return the powwow meeting remote service
	 */
	public com.liferay.powwow.service.PowwowMeetingService
		getPowwowMeetingService() {

		return powwowMeetingService;
	}

	/**
	 * Sets the powwow meeting remote service.
	 *
	 * @param powwowMeetingService the powwow meeting remote service
	 */
	public void setPowwowMeetingService(
		com.liferay.powwow.service.PowwowMeetingService powwowMeetingService) {

		this.powwowMeetingService = powwowMeetingService;
	}

	/**
	 * Returns the powwow meeting persistence.
	 *
	 * @return the powwow meeting persistence
	 */
	public PowwowMeetingPersistence getPowwowMeetingPersistence() {
		return powwowMeetingPersistence;
	}

	/**
	 * Sets the powwow meeting persistence.
	 *
	 * @param powwowMeetingPersistence the powwow meeting persistence
	 */
	public void setPowwowMeetingPersistence(
		PowwowMeetingPersistence powwowMeetingPersistence) {

		this.powwowMeetingPersistence = powwowMeetingPersistence;
	}

	/**
	 * Returns the powwow meeting finder.
	 *
	 * @return the powwow meeting finder
	 */
	public PowwowMeetingFinder getPowwowMeetingFinder() {
		return powwowMeetingFinder;
	}

	/**
	 * Sets the powwow meeting finder.
	 *
	 * @param powwowMeetingFinder the powwow meeting finder
	 */
	public void setPowwowMeetingFinder(
		PowwowMeetingFinder powwowMeetingFinder) {

		this.powwowMeetingFinder = powwowMeetingFinder;
	}

	/**
	 * Returns the powwow participant local service.
	 *
	 * @return the powwow participant local service
	 */
	public com.liferay.powwow.service.PowwowParticipantLocalService
		getPowwowParticipantLocalService() {

		return powwowParticipantLocalService;
	}

	/**
	 * Sets the powwow participant local service.
	 *
	 * @param powwowParticipantLocalService the powwow participant local service
	 */
	public void setPowwowParticipantLocalService(
		com.liferay.powwow.service.PowwowParticipantLocalService
			powwowParticipantLocalService) {

		this.powwowParticipantLocalService = powwowParticipantLocalService;
	}

	/**
	 * Returns the powwow participant remote service.
	 *
	 * @return the powwow participant remote service
	 */
	public PowwowParticipantService getPowwowParticipantService() {
		return powwowParticipantService;
	}

	/**
	 * Sets the powwow participant remote service.
	 *
	 * @param powwowParticipantService the powwow participant remote service
	 */
	public void setPowwowParticipantService(
		PowwowParticipantService powwowParticipantService) {

		this.powwowParticipantService = powwowParticipantService;
	}

	/**
	 * Returns the powwow participant persistence.
	 *
	 * @return the powwow participant persistence
	 */
	public PowwowParticipantPersistence getPowwowParticipantPersistence() {
		return powwowParticipantPersistence;
	}

	/**
	 * Sets the powwow participant persistence.
	 *
	 * @param powwowParticipantPersistence the powwow participant persistence
	 */
	public void setPowwowParticipantPersistence(
		PowwowParticipantPersistence powwowParticipantPersistence) {

		this.powwowParticipantPersistence = powwowParticipantPersistence;
	}

	/**
	 * Returns the powwow server local service.
	 *
	 * @return the powwow server local service
	 */
	public com.liferay.powwow.service.PowwowServerLocalService
		getPowwowServerLocalService() {

		return powwowServerLocalService;
	}

	/**
	 * Sets the powwow server local service.
	 *
	 * @param powwowServerLocalService the powwow server local service
	 */
	public void setPowwowServerLocalService(
		com.liferay.powwow.service.PowwowServerLocalService
			powwowServerLocalService) {

		this.powwowServerLocalService = powwowServerLocalService;
	}

	/**
	 * Returns the powwow server persistence.
	 *
	 * @return the powwow server persistence
	 */
	public PowwowServerPersistence getPowwowServerPersistence() {
		return powwowServerPersistence;
	}

	/**
	 * Sets the powwow server persistence.
	 *
	 * @param powwowServerPersistence the powwow server persistence
	 */
	public void setPowwowServerPersistence(
		PowwowServerPersistence powwowServerPersistence) {

		this.powwowServerPersistence = powwowServerPersistence;
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
		return PowwowParticipantService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PowwowParticipant.class;
	}

	protected String getModelClassName() {
		return PowwowParticipant.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				powwowParticipantPersistence.getDataSource();

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
		type = com.liferay.powwow.service.PowwowMeetingLocalService.class
	)
	protected com.liferay.powwow.service.PowwowMeetingLocalService
		powwowMeetingLocalService;

	@BeanReference(type = com.liferay.powwow.service.PowwowMeetingService.class)
	protected com.liferay.powwow.service.PowwowMeetingService
		powwowMeetingService;

	@BeanReference(type = PowwowMeetingPersistence.class)
	protected PowwowMeetingPersistence powwowMeetingPersistence;

	@BeanReference(type = PowwowMeetingFinder.class)
	protected PowwowMeetingFinder powwowMeetingFinder;

	@BeanReference(
		type = com.liferay.powwow.service.PowwowParticipantLocalService.class
	)
	protected com.liferay.powwow.service.PowwowParticipantLocalService
		powwowParticipantLocalService;

	@BeanReference(type = PowwowParticipantService.class)
	protected PowwowParticipantService powwowParticipantService;

	@BeanReference(type = PowwowParticipantPersistence.class)
	protected PowwowParticipantPersistence powwowParticipantPersistence;

	@BeanReference(
		type = com.liferay.powwow.service.PowwowServerLocalService.class
	)
	protected com.liferay.powwow.service.PowwowServerLocalService
		powwowServerLocalService;

	@BeanReference(type = PowwowServerPersistence.class)
	protected PowwowServerPersistence powwowServerPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}