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

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface ContactResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyContactsPageHttpResponse(
				String accountKey, Pagination pagination)
		throws Exception;

	public Page<Contact> getContactsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getContactsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Contact postContact(Contact contact) throws Exception;

	public HttpInvoker.HttpResponse postContactHttpResponse(Contact contact)
		throws Exception;

	public void deleteContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteContactByEmailAddresEmailAddressHttpResponse(
				String emailAddress)
		throws Exception;

	public Contact getContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactByEmailAddresEmailAddressHttpResponse(String emailAddress)
		throws Exception;

	public Contact putContactByEmailAddresEmailAddress(
			String emailAddress, Contact contact)
		throws Exception;

	public HttpInvoker.HttpResponse
			putContactByEmailAddresEmailAddressHttpResponse(
				String emailAddress, Contact contact)
		throws Exception;

	public void deleteContactByOkta(String oktaId) throws Exception;

	public HttpInvoker.HttpResponse deleteContactByOktaHttpResponse(
			String oktaId)
		throws Exception;

	public Contact getContactByOkta(String oktaId) throws Exception;

	public HttpInvoker.HttpResponse getContactByOktaHttpResponse(String oktaId)
		throws Exception;

	public Contact putContactByOkta(String oktaId, Contact contact)
		throws Exception;

	public HttpInvoker.HttpResponse putContactByOktaHttpResponse(
			String oktaId, Contact contact)
		throws Exception;

	public void deleteContactByOktaContactPermission(
			String oktaId,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ContactPermission contactPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteContactByOktaContactPermissionHttpResponse(
				String oktaId,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
		throws Exception;

	public void putContactByOktaContactPermission(
			String oktaId,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ContactPermission contactPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			putContactByOktaContactPermissionHttpResponse(
				String oktaId,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
		throws Exception;

	public void deleteContactByUuidContactUuid(String contactUuid)
		throws Exception;

	public HttpInvoker.HttpResponse deleteContactByUuidContactUuidHttpResponse(
			String contactUuid)
		throws Exception;

	public Contact getContactByUuidContactUuid(String contactUuid)
		throws Exception;

	public HttpInvoker.HttpResponse getContactByUuidContactUuidHttpResponse(
			String contactUuid)
		throws Exception;

	public Contact putContactByUuidContactUuid(
			String contactUuid, Contact contact)
		throws Exception;

	public HttpInvoker.HttpResponse putContactByUuidContactUuidHttpResponse(
			String contactUuid, Contact contact)
		throws Exception;

	public void deleteContactByUuidContactUuidContactPermission(
			String contactUuid,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ContactPermission contactPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteContactByUuidContactUuidContactPermissionHttpResponse(
				String contactUuid,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
		throws Exception;

	public void putContactByUuidContactUuidContactPermission(
			String contactUuid,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ContactPermission contactPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			putContactByUuidContactUuidContactPermissionHttpResponse(
				String contactUuid,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public ContactResource build() {
			return new ContactResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		private Builder() {
		}

		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class ContactResourceImpl implements ContactResource {

		public Page<Contact> getAccountAccountKeyContactsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyContactsPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, ContactSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyContactsPageHttpResponse(
					String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Contact> getContactsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getContactsPageHttpResponse(
				search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, ContactSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getContactsPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/contacts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact postContact(Contact contact) throws Exception {
			HttpInvoker.HttpResponse httpResponse = postContactHttpResponse(
				contact);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postContactHttpResponse(Contact contact)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contact.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/contacts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactByEmailAddresEmailAddress(String emailAddress)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactByEmailAddresEmailAddressHttpResponse(
					emailAddress);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteContactByEmailAddresEmailAddressHttpResponse(
					String emailAddress)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-email-address/{emailAddress}",
				emailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact getContactByEmailAddresEmailAddress(String emailAddress)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByEmailAddresEmailAddressHttpResponse(emailAddress);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse
				getContactByEmailAddresEmailAddressHttpResponse(
					String emailAddress)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-email-address/{emailAddress}",
				emailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact putContactByEmailAddresEmailAddress(
				String emailAddress, Contact contact)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactByEmailAddresEmailAddressHttpResponse(
					emailAddress, contact);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse
				putContactByEmailAddresEmailAddressHttpResponse(
					String emailAddress, Contact contact)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contact.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-email-address/{emailAddress}",
				emailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactByOkta(String oktaId) throws Exception {
			HttpInvoker.HttpResponse httpResponse =
				deleteContactByOktaHttpResponse(oktaId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteContactByOktaHttpResponse(
				String oktaId)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact getContactByOkta(String oktaId) throws Exception {
			HttpInvoker.HttpResponse httpResponse =
				getContactByOktaHttpResponse(oktaId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse getContactByOktaHttpResponse(
				String oktaId)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact putContactByOkta(String oktaId, Contact contact)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactByOktaHttpResponse(oktaId, contact);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse putContactByOktaHttpResponse(
				String oktaId, Contact contact)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contact.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactByOktaContactPermission(
				String oktaId,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactByOktaContactPermissionHttpResponse(
					oktaId, contactPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteContactByOktaContactPermissionHttpResponse(
					String oktaId,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ContactPermission contactPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}/contact-permissions",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putContactByOktaContactPermission(
				String oktaId,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactByOktaContactPermissionHttpResponse(
					oktaId, contactPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putContactByOktaContactPermissionHttpResponse(
					String oktaId,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ContactPermission contactPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}/contact-permissions",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactByUuidContactUuid(String contactUuid)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactByUuidContactUuidHttpResponse(contactUuid);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteContactByUuidContactUuidHttpResponse(String contactUuid)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact getContactByUuidContactUuid(String contactUuid)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByUuidContactUuidHttpResponse(contactUuid);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse getContactByUuidContactUuidHttpResponse(
				String contactUuid)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Contact putContactByUuidContactUuid(
				String contactUuid, Contact contact)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactByUuidContactUuidHttpResponse(contactUuid, contact);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse putContactByUuidContactUuidHttpResponse(
				String contactUuid, Contact contact)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contact.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactByUuidContactUuidContactPermission(
				String contactUuid,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactByUuidContactUuidContactPermissionHttpResponse(
					contactUuid, contactPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteContactByUuidContactUuidContactPermissionHttpResponse(
					String contactUuid,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ContactPermission contactPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/contact-permissions",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putContactByUuidContactUuidContactPermission(
				String contactUuid,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ContactPermission contactPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactByUuidContactUuidContactPermissionHttpResponse(
					contactUuid, contactPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putContactByUuidContactUuidContactPermissionHttpResponse(
					String contactUuid,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ContactPermission contactPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/contact-permissions",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private ContactResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			ContactResource.class.getName());

		private Builder _builder;

	}

}