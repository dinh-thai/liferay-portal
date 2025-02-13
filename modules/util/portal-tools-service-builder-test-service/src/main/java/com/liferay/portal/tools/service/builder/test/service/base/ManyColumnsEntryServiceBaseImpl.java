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

package com.liferay.portal.tools.service.builder.test.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.ManyColumnsEntry;
import com.liferay.portal.tools.service.builder.test.service.ManyColumnsEntryService;
import com.liferay.portal.tools.service.builder.test.service.persistence.ManyColumnsEntryPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the many columns entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.ManyColumnsEntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.ManyColumnsEntryServiceImpl
 * @generated
 */
public abstract class ManyColumnsEntryServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, ManyColumnsEntryService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ManyColumnsEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.tools.service.builder.test.service.ManyColumnsEntryServiceUtil</code>.
	 */

	/**
	 * Returns the many columns entry local service.
	 *
	 * @return the many columns entry local service
	 */
	public com.liferay.portal.tools.service.builder.test.service.
		ManyColumnsEntryLocalService getManyColumnsEntryLocalService() {

		return manyColumnsEntryLocalService;
	}

	/**
	 * Sets the many columns entry local service.
	 *
	 * @param manyColumnsEntryLocalService the many columns entry local service
	 */
	public void setManyColumnsEntryLocalService(
		com.liferay.portal.tools.service.builder.test.service.
			ManyColumnsEntryLocalService manyColumnsEntryLocalService) {

		this.manyColumnsEntryLocalService = manyColumnsEntryLocalService;
	}

	/**
	 * Returns the many columns entry remote service.
	 *
	 * @return the many columns entry remote service
	 */
	public ManyColumnsEntryService getManyColumnsEntryService() {
		return manyColumnsEntryService;
	}

	/**
	 * Sets the many columns entry remote service.
	 *
	 * @param manyColumnsEntryService the many columns entry remote service
	 */
	public void setManyColumnsEntryService(
		ManyColumnsEntryService manyColumnsEntryService) {

		this.manyColumnsEntryService = manyColumnsEntryService;
	}

	/**
	 * Returns the many columns entry persistence.
	 *
	 * @return the many columns entry persistence
	 */
	public ManyColumnsEntryPersistence getManyColumnsEntryPersistence() {
		return manyColumnsEntryPersistence;
	}

	/**
	 * Sets the many columns entry persistence.
	 *
	 * @param manyColumnsEntryPersistence the many columns entry persistence
	 */
	public void setManyColumnsEntryPersistence(
		ManyColumnsEntryPersistence manyColumnsEntryPersistence) {

		this.manyColumnsEntryPersistence = manyColumnsEntryPersistence;
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
		return ManyColumnsEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ManyColumnsEntry.class;
	}

	protected String getModelClassName() {
		return ManyColumnsEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = manyColumnsEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(
		type = com.liferay.portal.tools.service.builder.test.service.ManyColumnsEntryLocalService.class
	)
	protected com.liferay.portal.tools.service.builder.test.service.
		ManyColumnsEntryLocalService manyColumnsEntryLocalService;

	@BeanReference(type = ManyColumnsEntryService.class)
	protected ManyColumnsEntryService manyColumnsEntryService;

	@BeanReference(type = ManyColumnsEntryPersistence.class)
	protected ManyColumnsEntryPersistence manyColumnsEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}