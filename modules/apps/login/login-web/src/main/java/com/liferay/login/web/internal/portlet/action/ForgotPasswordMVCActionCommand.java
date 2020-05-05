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

package com.liferay.login.web.internal.portlet.action;

import com.liferay.captcha.configuration.CaptchaConfiguration;
import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.login.web.internal.constants.LoginPortletKeys;
import com.liferay.login.web.internal.portlet.util.LoginUtil;
import com.liferay.portal.kernel.captcha.CaptchaConfigurationException;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.RequiredReminderQueryException;
import com.liferay.portal.kernel.exception.SendPasswordException;
import com.liferay.portal.kernel.exception.UserActiveException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Tibor Kovacs
 * @author Peter Fellwock
 */
@Component(
	property = {
		"javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN,
		"mvc.command.name=/login/forgot_password"
	},
	service = MVCActionCommand.class
)
public class ForgotPasswordMVCActionCommand extends BaseMVCActionCommand {

	protected void checkCaptcha(ActionRequest actionRequest)
		throws CaptchaConfigurationException, CaptchaException {

		CaptchaConfiguration captchaConfiguration = getCaptchaConfiguration();

		if (captchaConfiguration.sendPasswordCaptchaEnabled()) {
			CaptchaUtil.check(actionRequest);
		}
	}

	protected void checkReminderQueries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletSession portletSession = actionRequest.getPortletSession();

		int step = ParamUtil.getInteger(actionRequest, "step");

		if (step == 1) {
			checkCaptcha(actionRequest);

			portletSession.removeAttribute(
				WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS);
			portletSession.removeAttribute(
				WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);
		}

		User user = getUser(actionRequest);

		String login = (String)portletSession.getAttribute(
			WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

		if (Validator.isNull(login)) {
			login = ParamUtil.getString(actionRequest, "login");

			portletSession.setAttribute(
				WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS, login);
		}

		actionRequest.setAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER, user);

		if (step == 2) {
			Integer reminderAttempts = (Integer)portletSession.getAttribute(
				WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS);

			if (reminderAttempts == null) {
				reminderAttempts = 0;
			}
			else if (reminderAttempts > 2) {
				checkCaptcha(actionRequest);
			}

			reminderAttempts++;

			portletSession.setAttribute(
				WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS, reminderAttempts);

			sendPassword(actionRequest, actionResponse);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Company company = themeDisplay.getCompany();

		if (!company.isSendPasswordResetLink()) {
			throw new PrincipalException.MustBeEnabled(
				company.getCompanyId(),
				PropsKeys.COMPANY_SECURITY_SEND_PASSWORD_RESET_LINK);
		}

		try {
			if (PropsValues.USERS_REMINDER_QUERIES_ENABLED) {
				checkReminderQueries(actionRequest, actionResponse);
			}
			else {
				checkCaptcha(actionRequest);

				sendPassword(actionRequest, actionResponse);
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to send password: " + exception.getMessage(),
					exception);
			}

			if (exception instanceof CaptchaException ||
				exception instanceof UserEmailAddressException ||
				exception instanceof UserReminderQueryException) {

				SessionErrors.add(actionRequest, exception.getClass());
			}
			else if (exception instanceof NoSuchUserException ||
					 exception instanceof RequiredReminderQueryException ||
					 exception instanceof SendPasswordException ||
					 exception instanceof UserActiveException ||
					 exception instanceof UserLockoutException) {

				if (PropsValues.LOGIN_SECURE_FORGOT_PASSWORD) {
					SessionMessages.add(
						_portal.getHttpServletRequest(actionRequest),
						"forgotPasswordSent");

					sendRedirect(actionRequest, actionResponse, null);
				}
				else {
					SessionErrors.add(
						actionRequest, exception.getClass(), exception);
				}
			}
			else {
				_portal.sendError(exception, actionRequest, actionResponse);
			}
		}
	}

	protected CaptchaConfiguration getCaptchaConfiguration()
		throws CaptchaConfigurationException {

		try {
			return _configurationProvider.getSystemConfiguration(
				CaptchaConfiguration.class);
		}
		catch (Exception exception) {
			throw new CaptchaConfigurationException(exception);
		}
	}

	protected User getUser(ActionRequest actionRequest) throws Exception {
		if (!PropsValues.LOGIN_SECURE_FORGOT_PASSWORD) {
			return _getUser(actionRequest);
		}

		try {
			return _getUser(actionRequest);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get user: " + e.getMessage(), e);
			}
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User defaultUser = _userLocalService.getDefaultUser(
			themeDisplay.getCompanyId());

		Set<String> reminderQueryQuestions =
			defaultUser.getReminderQueryQuestions();

		if (reminderQueryQuestions.size() > 0) {
			Iterator<String> it = reminderQueryQuestions.iterator();

			defaultUser.setReminderQueryQuestion(it.next());
		}
		else {
			defaultUser.setReminderQueryQuestion(
				"what-is-your-library-card-number");
		}

		defaultUser.setReminderQueryAnswer(
			defaultUser.getReminderQueryQuestion());

		return defaultUser;
	}

	private User _getUser(ActionRequest actionRequest) throws Exception {
		PortletSession portletSession = actionRequest.getPortletSession();

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String login = (String)portletSession.getAttribute(
			WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

		if (Validator.isNull(login)) {
			login = ParamUtil.getString(actionRequest, "login");
		}

		User user = null;

		Company company = themeDisplay.getCompany();

		String authType = company.getAuthType();

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			user = _userLocalService.getUserByEmailAddress(
				themeDisplay.getCompanyId(), login);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			user = _userLocalService.getUserByScreenName(
				themeDisplay.getCompanyId(), login);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			user = _userLocalService.getUserById(GetterUtil.getLong(login));
		}
		else {
			throw new NoSuchUserException("User does not exist");
		}

		if (!user.isActive()) {
			throw new UserActiveException("Inactive user " + user.getUuid());
		}

		_userLocalService.checkLockout(user);

		return user;
	}

	protected void sendPassword(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Company company = themeDisplay.getCompany();

		User user = getUser(actionRequest);

		if (PropsValues.USERS_REMINDER_QUERIES_ENABLED) {
			if (user.isDefaultUser()) {
				throw new UserReminderQueryException(
					"Reminder query answer does not match answer");
			}

			if (PropsValues.USERS_REMINDER_QUERIES_REQUIRED &&
				!user.hasReminderQuery()) {

				throw new RequiredReminderQueryException(
					"No reminder query or answer is defined for user " +
						user.getUserId());
			}

			String answer = ParamUtil.getString(actionRequest, "answer");

			String reminderQueryAnswer = user.getReminderQueryAnswer();

			if (!reminderQueryAnswer.equals(answer)) {
				throw new UserReminderQueryException(
					"Reminder query answer does not match answer");
			}
		}

		if (user.isDefaultUser()) {
			SessionMessages.add(
				_portal.getHttpServletRequest(actionRequest),
				"forgotPasswordSent");

			return;
		}

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String emailFromName = portletPreferences.getValue(
			"emailFromName", null);
		String emailFromAddress = portletPreferences.getValue(
			"emailFromAddress", null);
		String emailToAddress = user.getEmailAddress();

		String emailParam = "emailPasswordSent";

		if (company.isSendPasswordResetLink()) {
			emailParam = "emailPasswordReset";
		}

		String subject = portletPreferences.getValue(
			emailParam + "Subject_" + languageId, null);
		String body = portletPreferences.getValue(
			emailParam + "Body_" + languageId, null);

		LoginUtil.sendPassword(
			actionRequest, emailFromName, emailFromAddress, emailToAddress,
			subject, body);

		SessionMessages.add(
			_portal.getHttpServletRequest(actionRequest), "forgotPasswordSent");

		sendRedirect(actionRequest, actionResponse, null);
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;
	private static final Log _log = LogFactoryUtil.getLog(
		ForgotPasswordMVCActionCommand.class);

}