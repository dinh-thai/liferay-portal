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

package com.liferay.osb.provisioning.web.internal.util;

import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class ProvisioningWebComponentProvider {

	public static AccountSearchDisplayContext getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getAccountSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ProvisioningWebComponentProvider
		getProvisioningWebComponentProvider() {

		return _provisioningWebComponentProvider;
	}

	public static ViewAccountDisplayContext getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			renderRequest, renderResponse, httpServletRequest);
	}

	@Activate
	protected void activate() {
		_provisioningWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_provisioningWebComponentProvider = null;
	}

	private AccountSearchDisplayContext _getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new AccountSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService);
	}

	private ViewAccountDisplayContext _getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		ViewAccountDisplayContext viewAccountDisplayContext =
			(ViewAccountDisplayContext)httpServletRequest.getAttribute(
				ViewAccountDisplayContext.class.getName());

		if (viewAccountDisplayContext != null) {
			return viewAccountDisplayContext;
		}

		viewAccountDisplayContext = new ViewAccountDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_contactRoleWebService, _contactWebService, _externalLinkWebService,
			_noteWebService, _productPurchaseViewWebService);

		httpServletRequest.setAttribute(
			ViewAccountDisplayContext.class.getName(),
			viewAccountDisplayContext);

		return viewAccountDisplayContext;
	}

	private static ProvisioningWebComponentProvider
		_provisioningWebComponentProvider;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

}