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

package com.liferay.message.boards.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.service.MBThreadLocalServiceUtil;
import com.liferay.message.boards.test.util.MBTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Michael C. Han
 */
@RunWith(Arquillian.class)
public class MBThreadLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
				new LiferayIntegrationTestRule(),
				PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddThreadTitleWhenAddingRootMessage() throws Exception {
		MBMessage rootMessage = _addMessage(null, true);

		MBThread thread = MBThreadLocalServiceUtil.getThread(
			rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());

		MBMessage childMessage = _addMessage(rootMessage, true);

		Assert.assertNotEquals(childMessage.getSubject(), thread.getTitle());
	}

	@Test
	public void testAttachmentsWhenSplittingThread() throws Exception {
		MBMessage rootMessage = _addMessage(null, true);

		MBMessage splitMessage = _addMessage(rootMessage, true);

		MBMessage childMessage = _addMessage(splitMessage, true);

		Assert.assertEquals(
			rootMessage.getThreadId(), splitMessage.getThreadId());

		Assert.assertEquals(1, rootMessage.getAttachmentsFileEntriesCount());
		Assert.assertEquals(1, splitMessage.getAttachmentsFileEntriesCount());
		Assert.assertEquals(1, childMessage.getAttachmentsFileEntriesCount());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		MBThreadLocalServiceUtil.splitThread(
			TestPropsValues.getUserId(), splitMessage.getMessageId(),
			RandomTestUtil.randomString(), serviceContext);

		rootMessage = MBMessageLocalServiceUtil.getMBMessage(
			rootMessage.getMessageId());

		splitMessage = MBMessageLocalServiceUtil.getMBMessage(
			splitMessage.getMessageId());

		childMessage = MBMessageLocalServiceUtil.getMBMessage(
			childMessage.getMessageId());

		Assert.assertNotEquals(
			rootMessage.getThreadId(), splitMessage.getThreadId());
		Assert.assertEquals(1, rootMessage.getAttachmentsFileEntriesCount());
		Assert.assertEquals(1, splitMessage.getAttachmentsFileEntriesCount());
		Assert.assertEquals(1, childMessage.getAttachmentsFileEntriesCount());
	}

	@Test
	public void testDeleteThreadWithExpandoMessages() throws Exception {

		int expandoCount = ExpandoValueLocalServiceUtil.getExpandoValuesCount();

		Assert.assertEquals(0, expandoCount);

		MBMessage message = _addMessageWithExpando();

		ExpandoBridge expandoBridge = message.getExpandoBridge();

		String attributeValue = GetterUtil.getString(
			expandoBridge.getAttribute("testExpandoName"));

		Assert.assertEquals("testExpandoValue", attributeValue);

		expandoCount = ExpandoValueLocalServiceUtil.getExpandoValuesCount();

		Assert.assertEquals(1, expandoCount);

		MBThreadLocalServiceUtil.deleteThread(message.getThreadId());

		expandoCount = ExpandoValueLocalServiceUtil.getExpandoValuesCount();

		Assert.assertEquals(0, expandoCount);

		ExpandoTableLocalServiceUtil.deleteTables(
			PortalUtil.getDefaultCompanyId(), MBMessage.class.getName());
	}

	@Test
	public void testNotUpdateThreadTitleWhenUpdatingChildMessage()
		throws Exception {

		MBMessage rootMessage = _addMessage(null, true);

		MBMessage childMessage = _addMessage(rootMessage, true);

		MBThread thread = MBThreadLocalServiceUtil.getThread(
			rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		String newSubject = RandomTestUtil.randomString();

		MBMessageLocalServiceUtil.updateMessage(
			TestPropsValues.getUserId(), childMessage.getMessageId(),
			newSubject, RandomTestUtil.randomString(), inputStreamOVPs,
			new ArrayList<String>(), 0.0, false, serviceContext);

		thread = MBThreadLocalServiceUtil.getThread(rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());
		Assert.assertNotEquals(newSubject, thread.getTitle());
	}

	@Test
	public void testUpdateThreadTitleWhenSplittingMessage() throws Exception {
		MBMessage rootMessage = _addMessage(null, true);

		MBMessage splitMessage = _addMessage(rootMessage, true);

		MBThread thread = MBThreadLocalServiceUtil.getThread(
			rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());

		Assert.assertNotEquals(splitMessage.getSubject(), thread.getTitle());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		MBThreadLocalServiceUtil.splitThread(
			TestPropsValues.getUserId(), splitMessage.getMessageId(),
			RandomTestUtil.randomString(), serviceContext);

		rootMessage = MBMessageLocalServiceUtil.getMBMessage(
			rootMessage.getMessageId());

		thread = MBThreadLocalServiceUtil.getThread(rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());

		splitMessage = MBMessageLocalServiceUtil.getMBMessage(
			splitMessage.getMessageId());

		MBThread splitThread = MBThreadLocalServiceUtil.getThread(
			splitMessage.getThreadId());

		Assert.assertEquals(splitMessage.getSubject(), splitThread.getTitle());
	}

	@Test
	public void testUpdateThreadTitleWhenUpdatingRootMessage()
		throws Exception {

		MBMessage rootMessage = _addMessage(null, true);

		MBThread thread = MBThreadLocalServiceUtil.getThread(
			rootMessage.getThreadId());

		Assert.assertEquals(rootMessage.getSubject(), thread.getTitle());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		String newSubject = RandomTestUtil.randomString();

		MBMessageLocalServiceUtil.updateMessage(
			TestPropsValues.getUserId(), rootMessage.getMessageId(), newSubject,
			RandomTestUtil.randomString(), inputStreamOVPs,
			new ArrayList<String>(), 0.0, false, serviceContext);

		thread = MBThreadLocalServiceUtil.getThread(rootMessage.getThreadId());

		Assert.assertEquals(newSubject, thread.getTitle());
	}

	private MBMessage _addMessage(
			MBMessage parentMessage, boolean addAttachments)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		long categoryId = MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID;
		long threadId = 0;
		long parentMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;

		if (parentMessage != null) {
			categoryId = parentMessage.getCategoryId();
			threadId = parentMessage.getThreadId();
			parentMessageId = parentMessage.getMessageId();
		}

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		if (addAttachments) {
			inputStreamOVPs = MBTestUtil.getInputStreamOVPs(
				"attachment.txt", getClass(), StringPool.BLANK);
		}

		return MBMessageLocalServiceUtil.addMessage(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			_group.getGroupId(), categoryId, threadId, parentMessageId,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			MBMessageConstants.DEFAULT_FORMAT, inputStreamOVPs, false, 0.0,
			false, serviceContext);
	}

	private MBMessage _addMessageWithExpando()
		throws Exception {

		ExpandoTable expandoTable =
			ExpandoTableLocalServiceUtil.addDefaultTable(
				PortalUtil.getDefaultCompanyId(), MBMessage.class.getName());

		ExpandoColumnLocalServiceUtil.addColumn(
			expandoTable.getTableId(), "testExpandoName",
			ExpandoColumnConstants.STRING, StringPool.BLANK);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		long categoryId = MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID;
		long threadId = 0;
		long parentMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;

		Map<String, Serializable> expandoBridgeAttributes =
			HashMapBuilder.<String, Serializable>put(
				"testExpandoName", "testExpandoValue"
			).build();

		serviceContext.setExpandoBridgeAttributes(expandoBridgeAttributes);

		return MBMessageLocalServiceUtil.addMessage(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			_group.getGroupId(), categoryId, threadId, parentMessageId,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			MBMessageConstants.DEFAULT_FORMAT, null, false, 0.0,
			false, serviceContext);
	}

	@DeleteAfterTestRun
	private Group _group;

}