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

package com.liferay.commerce.data.integration.apio.internal.resource;

import static com.liferay.portal.apio.idempotent.Idempotent.idempotent;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifiers.ProductDefinitionIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.ProductCreatorForm;
import com.liferay.commerce.data.integration.apio.internal.util.ProductDefinitionHelper;
import com.liferay.commerce.data.integration.apio.internal.util.ProductIndexerHelper;
import com.liferay.commerce.product.exception.CPDefinitionProductTypeNameException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.search.CPDefinitionIndexer;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.media.object.apio.architect.identifier.FileEntryIdentifier;
import com.liferay.person.apio.architect.identifier.PersonIdentifier;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Provides the information necessary to expose <a
 * href="http://schema.org/Product">Product</a> resources through a web API. The
 * resources are mapped from the internal model {@code CPDefinition}.
 *
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class ProductNestedCollectionResource
	implements
		NestedCollectionResource<Document, Long,
				ProductDefinitionIdentifier, Long, WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<Document, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<Document, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCPDefinition,
			_hasPermission.forAddingEntries(CPDefinition.class)::apply,
			ProductCreatorForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "products";
	}

	@Override
	public ItemRoutes<Document, Long> itemRoutes(
		ItemRoutes.Builder<Document, Long> builder) {

		return builder.addGetter(
			this::_getCPDefinition
		).addRemover(
			idempotent(_cpDefinitionService::deleteCPDefinition),
			_hasPermission.forDeleting(CPDefinition.class)::apply
		).build();
	}

	@Override
	public Representor<Document> representor(
		Representor.Builder<Document, Long> builder) {

		return builder.types(
			"Product"
		).identifier(
			document -> GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))
		).addBidirectionalModel(
			"webSite", "products", WebSiteIdentifier.class,
			document -> GetterUtil.getLong(document.get(Field.GROUP_ID))
		).addDate(
			"dateCreated",
			document -> Try.fromFallible(
				() -> document.getDate(Field.CREATE_DATE)
			).getUnchecked()
		).addDate(
			"dateModified",
			document -> Try.fromFallible(
				() -> document.getDate(Field.MODIFIED_DATE)
			).getUnchecked()
		).addLinkedModel(
			"author", PersonIdentifier.class,
			document -> GetterUtil.getLong(document.get(Field.USER_ID))
		).addLinkedModel(
			"defaultImage", FileEntryIdentifier.class,
			document -> GetterUtil.getLong(
				document.get(
					CPDefinitionIndexer.FIELD_DEFAULT_IMAGE_FILE_ENTRY_ID))
		).addString(
			"description", this::_getSafeDescription
		).addString(
			"productType",
			document -> document.get(
				CPDefinitionIndexer.FIELD_PRODUCT_TYPE_NAME)
		).addString(
			"name", document -> document.get(Field.TITLE)
		).addString(
			"externalReferenceCode",
			document -> document.get(
				CPDefinitionIndexer.FIELD_EXTERNAL_REFERENCE_CODE)
		).addStringList(
			"skus",
			document -> Arrays.asList(
				document.getValues(CPDefinitionIndexer.FIELD_SKUS))
		).build();
	}

	private Document _addCPDefinition(
		Long webSiteId, ProductCreatorForm productCreatorForm) {

		try {
			CPDefinition cpDefinition =
				_productDefinitionHelper.createCPDefinition(
					webSiteId, productCreatorForm.getTitleMap(),
					productCreatorForm.getDescriptionMap(),
					productCreatorForm.getShortDescriptionMap(),
					productCreatorForm.getProductTypeName(),
					ArrayUtil.toLongArray(
						productCreatorForm.getAssetCategoryIds()),
					productCreatorForm.getExternalReferenceCode(),
					productCreatorForm.getDefaultSku());

			Indexer<CPDefinition> indexer = _productIndexerHelper.getIndexer(
				CPDefinition.class);

			return indexer.getDocument(cpDefinition);
		}
		catch (CPDefinitionProductTypeNameException cpdptne) {
			throw new NotFoundException(
				"Product type not available: " +
					productCreatorForm.getProductTypeName(),
				cpdptne);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	private Document _getCPDefinition(Long cpDefinitionId) {
		try {
			ServiceContext serviceContext =
				_productIndexerHelper.getServiceContext();

			SearchContext searchContext =
				_productDefinitionHelper.buildSearchContext(
					String.valueOf(cpDefinitionId),
					String.valueOf(cpDefinitionId), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null, serviceContext);

			Indexer<CPDefinition> indexer = _productIndexerHelper.getIndexer(
				CPDefinition.class);

			Hits hits = indexer.search(searchContext);

			if (hits.getLength() == 0) {
				throw new NotFoundException(
					"Unable to find product with ID " + cpDefinitionId);
			}

			if (hits.getLength() > 1) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"More than one document found for product with ID " +
							cpDefinitionId);
				}

				CPDefinition cpDefinition =
					_cpDefinitionService.getCPDefinition(cpDefinitionId);

				return indexer.getDocument(cpDefinition);
			}

			List<Document> documents = hits.toList();

			return documents.get(0);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	private PageItems<Document> _getPageItems(
		Pagination pagination, Long webSiteId) {

		try {
			ServiceContext serviceContext =
				_productIndexerHelper.getServiceContext(webSiteId, new long[0]);

			SearchContext searchContext =
				_productDefinitionHelper.buildSearchContext(
					null, null, pagination.getStartPosition(),
					pagination.getEndPosition(), null, serviceContext);

			Indexer<CPDefinition> indexer = _productIndexerHelper.getIndexer(
				CPDefinition.class);

			Hits hits = indexer.search(searchContext);

			List<Document> documents = Collections.<Document>emptyList();

			if (hits.getLength() > 0) {
				documents = hits.toList();
			}

			return new PageItems<>(documents, hits.getLength());
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	private String _getSafeDescription(Document document) {
		String description = document.get(Field.DESCRIPTION);

		if (Validator.isNull(description)) {
			description = StringPool.DASH;
		}

		return description;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductNestedCollectionResource.class);

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private HasPermission _hasPermission;

	@Reference
	private ProductDefinitionHelper _productDefinitionHelper;

	@Reference
	private ProductIndexerHelper _productIndexerHelper;

}