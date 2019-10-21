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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductConsumptionSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseProductConsumptionResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_productConsumptionResource.setContextCompany(testCompany);

		ProductConsumptionResource.Builder builder =
			ProductConsumptionResource.builder();

		productConsumptionResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductConsumption productConsumption1 = randomProductConsumption();

		String json = objectMapper.writeValueAsString(productConsumption1);

		ProductConsumption productConsumption2 = ProductConsumptionSerDes.toDTO(
			json);

		Assert.assertTrue(equals(productConsumption1, productConsumption2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductConsumption productConsumption = randomProductConsumption();

		String json1 = objectMapper.writeValueAsString(productConsumption);
		String json2 = ProductConsumptionSerDes.toJSON(productConsumption);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProductConsumption productConsumption = randomProductConsumption();

		productConsumption.setAccountKey(regex);
		productConsumption.setKey(regex);
		productConsumption.setProductKey(regex);

		String json = ProductConsumptionSerDes.toJSON(productConsumption);

		Assert.assertFalse(json.contains(regex));

		productConsumption = ProductConsumptionSerDes.toDTO(json);

		Assert.assertEquals(regex, productConsumption.getAccountKey());
		Assert.assertEquals(regex, productConsumption.getKey());
		Assert.assertEquals(regex, productConsumption.getProductKey());
	}

	@Test
	public void testGetAccountAccountKeyProductConsumptionsPage()
		throws Exception {

		Page<ProductConsumption> page =
			productConsumptionResource.
				getAccountAccountKeyProductConsumptionsPage(
					testGetAccountAccountKeyProductConsumptionsPage_getAccountKey(),
					Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String accountKey =
			testGetAccountAccountKeyProductConsumptionsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyProductConsumptionsPage_getIrrelevantAccountKey();

		if ((irrelevantAccountKey != null)) {
			ProductConsumption irrelevantProductConsumption =
				testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
					irrelevantAccountKey, randomIrrelevantProductConsumption());

			page =
				productConsumptionResource.
					getAccountAccountKeyProductConsumptionsPage(
						irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductConsumption),
				(List<ProductConsumption>)page.getItems());
			assertValid(page);
		}

		ProductConsumption productConsumption1 =
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				accountKey, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				accountKey, randomProductConsumption());

		page =
			productConsumptionResource.
				getAccountAccountKeyProductConsumptionsPage(
					accountKey, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productConsumption1, productConsumption2),
			(List<ProductConsumption>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyProductConsumptionsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyProductConsumptionsPage_getAccountKey();

		ProductConsumption productConsumption1 =
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				accountKey, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				accountKey, randomProductConsumption());

		ProductConsumption productConsumption3 =
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				accountKey, randomProductConsumption());

		Page<ProductConsumption> page1 =
			productConsumptionResource.
				getAccountAccountKeyProductConsumptionsPage(
					accountKey, Pagination.of(1, 2));

		List<ProductConsumption> productConsumptions1 =
			(List<ProductConsumption>)page1.getItems();

		Assert.assertEquals(
			productConsumptions1.toString(), 2, productConsumptions1.size());

		Page<ProductConsumption> page2 =
			productConsumptionResource.
				getAccountAccountKeyProductConsumptionsPage(
					accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductConsumption> productConsumptions2 =
			(List<ProductConsumption>)page2.getItems();

		Assert.assertEquals(
			productConsumptions2.toString(), 1, productConsumptions2.size());

		Page<ProductConsumption> page3 =
			productConsumptionResource.
				getAccountAccountKeyProductConsumptionsPage(
					accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productConsumption1, productConsumption2, productConsumption3),
			(List<ProductConsumption>)page3.getItems());
	}

	protected ProductConsumption
			testGetAccountAccountKeyProductConsumptionsPage_addProductConsumption(
				String accountKey, ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductConsumptionsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductConsumptionsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyProductConsumption() throws Exception {
		ProductConsumption randomProductConsumption =
			randomProductConsumption();

		ProductConsumption postProductConsumption =
			testPostAccountAccountKeyProductConsumption_addProductConsumption(
				randomProductConsumption);

		assertEquals(randomProductConsumption, postProductConsumption);
		assertValid(postProductConsumption);
	}

	protected ProductConsumption
			testPostAccountAccountKeyProductConsumption_addProductConsumption(
				ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactByOktaProductConsumptionsPage() throws Exception {
		Page<ProductConsumption> page =
			productConsumptionResource.getContactByOktaProductConsumptionsPage(
				testGetContactByOktaProductConsumptionsPage_getOktaId(),
				Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String oktaId = testGetContactByOktaProductConsumptionsPage_getOktaId();
		String irrelevantOktaId =
			testGetContactByOktaProductConsumptionsPage_getIrrelevantOktaId();

		if ((irrelevantOktaId != null)) {
			ProductConsumption irrelevantProductConsumption =
				testGetContactByOktaProductConsumptionsPage_addProductConsumption(
					irrelevantOktaId, randomIrrelevantProductConsumption());

			page =
				productConsumptionResource.
					getContactByOktaProductConsumptionsPage(
						irrelevantOktaId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductConsumption),
				(List<ProductConsumption>)page.getItems());
			assertValid(page);
		}

		ProductConsumption productConsumption1 =
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				oktaId, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				oktaId, randomProductConsumption());

		page =
			productConsumptionResource.getContactByOktaProductConsumptionsPage(
				oktaId, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productConsumption1, productConsumption2),
			(List<ProductConsumption>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactByOktaProductConsumptionsPageWithPagination()
		throws Exception {

		String oktaId = testGetContactByOktaProductConsumptionsPage_getOktaId();

		ProductConsumption productConsumption1 =
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				oktaId, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				oktaId, randomProductConsumption());

		ProductConsumption productConsumption3 =
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				oktaId, randomProductConsumption());

		Page<ProductConsumption> page1 =
			productConsumptionResource.getContactByOktaProductConsumptionsPage(
				oktaId, Pagination.of(1, 2));

		List<ProductConsumption> productConsumptions1 =
			(List<ProductConsumption>)page1.getItems();

		Assert.assertEquals(
			productConsumptions1.toString(), 2, productConsumptions1.size());

		Page<ProductConsumption> page2 =
			productConsumptionResource.getContactByOktaProductConsumptionsPage(
				oktaId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductConsumption> productConsumptions2 =
			(List<ProductConsumption>)page2.getItems();

		Assert.assertEquals(
			productConsumptions2.toString(), 1, productConsumptions2.size());

		Page<ProductConsumption> page3 =
			productConsumptionResource.getContactByOktaProductConsumptionsPage(
				oktaId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productConsumption1, productConsumption2, productConsumption3),
			(List<ProductConsumption>)page3.getItems());
	}

	protected ProductConsumption
			testGetContactByOktaProductConsumptionsPage_addProductConsumption(
				String oktaId, ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetContactByOktaProductConsumptionsPage_getOktaId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByOktaProductConsumptionsPage_getIrrelevantOktaId()
		throws Exception {

		return null;
	}

	@Test
	public void testGetContactByUuidContactUuidProductConsumptionsPage()
		throws Exception {

		Page<ProductConsumption> page =
			productConsumptionResource.
				getContactByUuidContactUuidProductConsumptionsPage(
					testGetContactByUuidContactUuidProductConsumptionsPage_getContactUuid(),
					Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String contactUuid =
			testGetContactByUuidContactUuidProductConsumptionsPage_getContactUuid();
		String irrelevantContactUuid =
			testGetContactByUuidContactUuidProductConsumptionsPage_getIrrelevantContactUuid();

		if ((irrelevantContactUuid != null)) {
			ProductConsumption irrelevantProductConsumption =
				testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
					irrelevantContactUuid,
					randomIrrelevantProductConsumption());

			page =
				productConsumptionResource.
					getContactByUuidContactUuidProductConsumptionsPage(
						irrelevantContactUuid, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductConsumption),
				(List<ProductConsumption>)page.getItems());
			assertValid(page);
		}

		ProductConsumption productConsumption1 =
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				contactUuid, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				contactUuid, randomProductConsumption());

		page =
			productConsumptionResource.
				getContactByUuidContactUuidProductConsumptionsPage(
					contactUuid, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productConsumption1, productConsumption2),
			(List<ProductConsumption>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactByUuidContactUuidProductConsumptionsPageWithPagination()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidProductConsumptionsPage_getContactUuid();

		ProductConsumption productConsumption1 =
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				contactUuid, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				contactUuid, randomProductConsumption());

		ProductConsumption productConsumption3 =
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				contactUuid, randomProductConsumption());

		Page<ProductConsumption> page1 =
			productConsumptionResource.
				getContactByUuidContactUuidProductConsumptionsPage(
					contactUuid, Pagination.of(1, 2));

		List<ProductConsumption> productConsumptions1 =
			(List<ProductConsumption>)page1.getItems();

		Assert.assertEquals(
			productConsumptions1.toString(), 2, productConsumptions1.size());

		Page<ProductConsumption> page2 =
			productConsumptionResource.
				getContactByUuidContactUuidProductConsumptionsPage(
					contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductConsumption> productConsumptions2 =
			(List<ProductConsumption>)page2.getItems();

		Assert.assertEquals(
			productConsumptions2.toString(), 1, productConsumptions2.size());

		Page<ProductConsumption> page3 =
			productConsumptionResource.
				getContactByUuidContactUuidProductConsumptionsPage(
					contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productConsumption1, productConsumption2, productConsumption3),
			(List<ProductConsumption>)page3.getItems());
	}

	protected ProductConsumption
			testGetContactByUuidContactUuidProductConsumptionsPage_addProductConsumption(
				String contactUuid, ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidProductConsumptionsPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidProductConsumptionsPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testGetProductConsumptionsPage() throws Exception {
		Page<ProductConsumption> page =
			productConsumptionResource.getProductConsumptionsPage(
				RandomTestUtil.randomString(), null, Pagination.of(1, 2), null);

		Assert.assertEquals(0, page.getTotalCount());

		ProductConsumption productConsumption1 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		page = productConsumptionResource.getProductConsumptionsPage(
			null, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productConsumption1, productConsumption2),
			(List<ProductConsumption>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductConsumptionsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductConsumption productConsumption1 = randomProductConsumption();

		productConsumption1 =
			testGetProductConsumptionsPage_addProductConsumption(
				productConsumption1);

		for (EntityField entityField : entityFields) {
			Page<ProductConsumption> page =
				productConsumptionResource.getProductConsumptionsPage(
					null,
					getFilterString(
						entityField, "between", productConsumption1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productConsumption1),
				(List<ProductConsumption>)page.getItems());
		}
	}

	@Test
	public void testGetProductConsumptionsPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductConsumption productConsumption1 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductConsumption productConsumption2 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		for (EntityField entityField : entityFields) {
			Page<ProductConsumption> page =
				productConsumptionResource.getProductConsumptionsPage(
					null,
					getFilterString(entityField, "eq", productConsumption1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productConsumption1),
				(List<ProductConsumption>)page.getItems());
		}
	}

	@Test
	public void testGetProductConsumptionsPageWithPagination()
		throws Exception {

		ProductConsumption productConsumption1 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		ProductConsumption productConsumption3 =
			testGetProductConsumptionsPage_addProductConsumption(
				randomProductConsumption());

		Page<ProductConsumption> page1 =
			productConsumptionResource.getProductConsumptionsPage(
				null, null, Pagination.of(1, 2), null);

		List<ProductConsumption> productConsumptions1 =
			(List<ProductConsumption>)page1.getItems();

		Assert.assertEquals(
			productConsumptions1.toString(), 2, productConsumptions1.size());

		Page<ProductConsumption> page2 =
			productConsumptionResource.getProductConsumptionsPage(
				null, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductConsumption> productConsumptions2 =
			(List<ProductConsumption>)page2.getItems();

		Assert.assertEquals(
			productConsumptions2.toString(), 1, productConsumptions2.size());

		Page<ProductConsumption> page3 =
			productConsumptionResource.getProductConsumptionsPage(
				null, null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productConsumption1, productConsumption2, productConsumption3),
			(List<ProductConsumption>)page3.getItems());
	}

	@Test
	public void testGetProductConsumptionsPageWithSortDateTime()
		throws Exception {

		testGetProductConsumptionsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, productConsumption1, productConsumption2) -> {
				BeanUtils.setProperty(
					productConsumption1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProductConsumptionsPageWithSortInteger()
		throws Exception {

		testGetProductConsumptionsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, productConsumption1, productConsumption2) -> {
				BeanUtils.setProperty(
					productConsumption1, entityField.getName(), 0);
				BeanUtils.setProperty(
					productConsumption2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProductConsumptionsPageWithSortString()
		throws Exception {

		testGetProductConsumptionsPageWithSort(
			EntityField.Type.STRING,
			(entityField, productConsumption1, productConsumption2) -> {
				Class clazz = productConsumption1.getClass();

				Method method = clazz.getMethod(
					"get" +
						StringUtil.upperCaseFirstLetter(entityField.getName()));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						productConsumption1, entityField.getName(),
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						productConsumption2, entityField.getName(),
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else {
					BeanUtils.setProperty(
						productConsumption1, entityField.getName(), "Aaa");
					BeanUtils.setProperty(
						productConsumption2, entityField.getName(), "Bbb");
				}
			});
	}

	protected void testGetProductConsumptionsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, ProductConsumption, ProductConsumption, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductConsumption productConsumption1 = randomProductConsumption();
		ProductConsumption productConsumption2 = randomProductConsumption();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, productConsumption1, productConsumption2);
		}

		productConsumption1 =
			testGetProductConsumptionsPage_addProductConsumption(
				productConsumption1);

		productConsumption2 =
			testGetProductConsumptionsPage_addProductConsumption(
				productConsumption2);

		for (EntityField entityField : entityFields) {
			Page<ProductConsumption> ascPage =
				productConsumptionResource.getProductConsumptionsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(productConsumption1, productConsumption2),
				(List<ProductConsumption>)ascPage.getItems());

			Page<ProductConsumption> descPage =
				productConsumptionResource.getProductConsumptionsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(productConsumption2, productConsumption1),
				(List<ProductConsumption>)descPage.getItems());
		}
	}

	protected ProductConsumption
			testGetProductConsumptionsPage_addProductConsumption(
				ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductConsumptionByExternalLinkDomainEntityNameEntity()
		throws Exception {

		Page<ProductConsumption> page =
			productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntity(
					testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getDomain(),
					testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityName(),
					testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityId(),
					Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String domain =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getDomain();
		String irrelevantDomain =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantDomain();
		String entityName =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityName();
		String irrelevantEntityName =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantEntityName();
		String entityId =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityId();
		String irrelevantEntityId =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantEntityId();

		if ((irrelevantDomain != null) && (irrelevantEntityName != null) &&
			(irrelevantEntityId != null)) {

			ProductConsumption irrelevantProductConsumption =
				testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
					irrelevantDomain, irrelevantEntityName, irrelevantEntityId,
					randomIrrelevantProductConsumption());

			page =
				productConsumptionResource.
					getProductConsumptionByExternalLinkDomainEntityNameEntity(
						irrelevantDomain, irrelevantEntityName,
						irrelevantEntityId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductConsumption),
				(List<ProductConsumption>)page.getItems());
			assertValid(page);
		}

		ProductConsumption productConsumption1 =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				domain, entityName, entityId, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				domain, entityName, entityId, randomProductConsumption());

		page =
			productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntity(
					domain, entityName, entityId, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productConsumption1, productConsumption2),
			(List<ProductConsumption>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductConsumptionByExternalLinkDomainEntityNameEntityWithPagination()
		throws Exception {

		String domain =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getDomain();
		String entityName =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityName();
		String entityId =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityId();

		ProductConsumption productConsumption1 =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				domain, entityName, entityId, randomProductConsumption());

		ProductConsumption productConsumption2 =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				domain, entityName, entityId, randomProductConsumption());

		ProductConsumption productConsumption3 =
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				domain, entityName, entityId, randomProductConsumption());

		Page<ProductConsumption> page1 =
			productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntity(
					domain, entityName, entityId, Pagination.of(1, 2));

		List<ProductConsumption> productConsumptions1 =
			(List<ProductConsumption>)page1.getItems();

		Assert.assertEquals(
			productConsumptions1.toString(), 2, productConsumptions1.size());

		Page<ProductConsumption> page2 =
			productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntity(
					domain, entityName, entityId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductConsumption> productConsumptions2 =
			(List<ProductConsumption>)page2.getItems();

		Assert.assertEquals(
			productConsumptions2.toString(), 1, productConsumptions2.size());

		Page<ProductConsumption> page3 =
			productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntity(
					domain, entityName, entityId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productConsumption1, productConsumption2, productConsumption3),
			(List<ProductConsumption>)page3.getItems());
	}

	protected ProductConsumption
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_addProductConsumption(
				String domain, String entityName, String entityId,
				ProductConsumption productConsumption)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getDomain()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantDomain()
		throws Exception {

		return null;
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityName()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantEntityName()
		throws Exception {

		return null;
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getEntityId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionByExternalLinkDomainEntityNameEntity_getIrrelevantEntityId()
		throws Exception {

		return null;
	}

	@Test
	public void testDeleteProductConsumption() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProductConsumption() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteProductConsumptionProductConsumptionPermission()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutProductConsumptionProductConsumptionPermission()
		throws Exception {

		Assert.assertTrue(false);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProductConsumption productConsumption1,
		ProductConsumption productConsumption2) {

		Assert.assertTrue(
			productConsumption1 + " does not equal " + productConsumption2,
			equals(productConsumption1, productConsumption2));
	}

	protected void assertEquals(
		List<ProductConsumption> productConsumptions1,
		List<ProductConsumption> productConsumptions2) {

		Assert.assertEquals(
			productConsumptions1.size(), productConsumptions2.size());

		for (int i = 0; i < productConsumptions1.size(); i++) {
			ProductConsumption productConsumption1 = productConsumptions1.get(
				i);
			ProductConsumption productConsumption2 = productConsumptions2.get(
				i);

			assertEquals(productConsumption1, productConsumption2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProductConsumption> productConsumptions1,
		List<ProductConsumption> productConsumptions2) {

		Assert.assertEquals(
			productConsumptions1.size(), productConsumptions2.size());

		for (ProductConsumption productConsumption1 : productConsumptions1) {
			boolean contains = false;

			for (ProductConsumption productConsumption2 :
					productConsumptions2) {

				if (equals(productConsumption1, productConsumption2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				productConsumptions2 + " does not contain " +
					productConsumption1,
				contains);
		}
	}

	protected void assertValid(ProductConsumption productConsumption) {
		boolean valid = true;

		if (productConsumption.getDateCreated() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (productConsumption.getAccountKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (productConsumption.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (productConsumption.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (productConsumption.getProductKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (productConsumption.getProperties() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<ProductConsumption> page) {
		boolean valid = false;

		java.util.Collection<ProductConsumption> productConsumptions =
			page.getItems();

		int size = productConsumptions.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		ProductConsumption productConsumption1,
		ProductConsumption productConsumption2) {

		if (productConsumption1 == productConsumption2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getAccountKey(),
						productConsumption2.getAccountKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getDateCreated(),
						productConsumption2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getExternalLinks(),
						productConsumption2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getKey(),
						productConsumption2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getProductKey(),
						productConsumption2.getProductKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productConsumption1.getProperties(),
						productConsumption2.getProperties())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_productConsumptionResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productConsumptionResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		ProductConsumption productConsumption) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accountKey")) {
			sb.append("'");
			sb.append(String.valueOf(productConsumption.getAccountKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productConsumption.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productConsumption.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(productConsumption.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(productConsumption.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productKey")) {
			sb.append("'");
			sb.append(String.valueOf(productConsumption.getProductKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("properties")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected ProductConsumption randomProductConsumption() throws Exception {
		return new ProductConsumption() {
			{
				accountKey = RandomTestUtil.randomString();
				dateCreated = RandomTestUtil.nextDate();
				key = RandomTestUtil.randomString();
				productKey = RandomTestUtil.randomString();
			}
		};
	}

	protected ProductConsumption randomIrrelevantProductConsumption()
		throws Exception {

		ProductConsumption randomIrrelevantProductConsumption =
			randomProductConsumption();

		return randomIrrelevantProductConsumption;
	}

	protected ProductConsumption randomPatchProductConsumption()
		throws Exception {

		return randomProductConsumption();
	}

	protected ProductConsumptionResource productConsumptionResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProductConsumptionResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		ProductConsumptionResource _productConsumptionResource;

}