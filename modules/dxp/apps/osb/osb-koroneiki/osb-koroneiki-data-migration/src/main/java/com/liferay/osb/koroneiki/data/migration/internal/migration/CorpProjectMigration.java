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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Industry;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Tier;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.AuditEntryLocalService;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = CorpProjectMigration.class)
public class CorpProjectMigration {

	public void migrate(long userId) throws Exception {
		User user = _userLocalService.getUser(userId);

		StringBundler sb = new StringBundler(7);

		sb.append("select OSB_CorpProject.*, ");
		sb.append("OSB_AccountEntry.dossieraAccountKey, ");
		sb.append("OSB_AccountEntry.code_, OSB_AccountEntry.type_, ");
		sb.append("OSB_AccountEntry.industry, OSB_AccountEntry.tier, ");
		sb.append("OSB_AccountEntry.notes, OSB_AccountEntry.status from ");
		sb.append("OSB_CorpProject inner join OSB_AccountEntry on ");
		sb.append("OSB_AccountEntry.corpProjectUuid = OSB_CorpProject.uuid_");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int status = resultSet.getInt("status");

				if (status == 500) {
					continue;
				}

				Account account = _accountLocalService.createAccount(
					resultSet.getLong("corpProjectId"));

				account.setCompanyId(user.getCompanyId());
				account.setUserId(userId);
				account.setCreateDate(resultSet.getTimestamp("createDate"));
				account.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
				account.setAccountKey(resultSet.getString("uuid_"));

				long parentAccountId = _getParentAccountId(
					resultSet.getString("dossieraAccountKey"));

				account.setParentAccountId(parentAccountId);

				account.setName(resultSet.getString("name"));
				account.setCode(resultSet.getString("code_"));
				account.setNotes(resultSet.getString("notes"));
				account.setIndustry(_getIndustry(resultSet.getInt("industry")));
				account.setTier(_getTier(resultSet.getInt("tier")));
				account.setInternal(_getInternal(resultSet.getInt("type_")));
				account.setStatus(WorkflowConstants.STATUS_APPROVED);
				account.setStatusByUserId(userId);
				account.setStatusByUserName(user.getFullName());
				account.setStatusDate(new Date());
				account.setStatusMessage(StringPool.BLANK);

				_accountLocalService.addAccount(account);

				_migrateAuditEntries(
					connection, userId, account.getAccountId());

				if (Validator.isNotNull(
						resultSet.getString("dossieraProjectKey"))) {

					_externalLinkLocalService.addExternalLink(
						userId, Account.class.getName(), account.getAccountId(),
						"dossiera", "project",
						resultSet.getString("dossieraProjectKey"));
				}

				if (Validator.isNotNull(
						resultSet.getString("salesforceProjectKey"))) {

					_externalLinkLocalService.addExternalLink(
						userId, Account.class.getName(), account.getAccountId(),
						"salesforce", "project",
						resultSet.getString("salesforceProjectKey"));
				}

				if (_log.isInfoEnabled()) {
					_log.info("Migrated CorpProject " + account.getAccountId());
				}
			}
		}
	}

	private String _getAction(int action) {
		if (action == 1) {
			return "Add";
		}
		else if (action == 2) {
			return "Assign";
		}
		else if (action == 3) {
			return "Delete";
		}
		else if (action == 5) {
			return "Unassign";
		}
		else if (action == 6) {
			return "Update";
		}
		else if (action == 7) {
			return "Renew";
		}
		else if (action == 11) {
			return "Audit";
		}

		return StringPool.BLANK;
	}

	private String _getField(int field) {
		if (field == 34015) {
			return "Role";
		}
		else if (field == 34017) {
			return "Status";
		}
		else if (field == 34021) {
			return "User";
		}
		else if (field == 34025) {
			return "Renew Count";
		}
		else if (field == 34054) {
			return "Tier";
		}
		else if (field == 34055) {
			return "Partner";
		}
		else if (field == 34058) {
			return "Corp Project";
		}
		else if (field == 34059) {
			return "Corp Entry Name";
		}
		else if (field == 34060) {
			return "Name";
		}
		else if (field == 34061) {
			return "Code";
		}
		else if (field == 34062) {
			return "Type";
		}
		else if (field == 34063) {
			return "Industry";
		}
		else if (field == 34064) {
			return "Partner Managed Support";
		}
		else if (field == 34065) {
			return "Instructions";
		}
		else if (field == 34066) {
			return "Notes";
		}
		else if (field == 34067) {
			return "Renewal Contact User";
		}
		else if (field == 34069) {
			return "Address";
		}
		else if (field == 34070) {
			return "Languages";
		}
		else if (field == 34071) {
			return "Support Regions";
		}
		else if (field == 34072) {
			return "N/A";
		}
		else if (field == 34078) {
			return "Dossiera Account Key";
		}

		return StringPool.BLANK;
	}

	private long _getFieldClassNameId(long fieldClassNameId) {
		if ((fieldClassNameId == 1400962) || (fieldClassNameId == 1400964)) {
			return _classNameLocalService.getClassNameId(Account.class);
		}
		else if (fieldClassNameId == 1400963) {
			return _classNameLocalService.getClassNameId(Account.class);
		}
		else if (fieldClassNameId == 1400969) {
			return _classNameLocalService.getClassNameId(ProductPurchase.class);
		}

		return 0;
	}

	private String _getIndustry(int industry) {
		if (industry == 35000) {
			return Industry.AEROSPACE_AND_DEFENSE.toString();
		}
		else if (industry == 35001) {
			return Industry.AGRICULTURE.toString();
		}
		else if (industry == 35002) {
			return Industry.AUTOMOTIVE.toString();
		}
		else if (industry == 35003) {
			return Industry.CONSULTING_MARKET_RESEARCH.toString();
		}
		else if (industry == 35004) {
			return Industry.RETAIL_CONSUMER_PRODUCTS.toString();
		}
		else if (industry == 35005) {
			return Industry.EDUCATION.toString();
		}
		else if (industry == 35006) {
			return Industry.ENERGY.toString();
		}
		else if (industry == 35007) {
			return Industry.FINANCIAL_SERVICES.toString();
		}
		else if (industry == 35009) {
			return Industry.HEALTHCARE.toString();
		}
		else if (industry == 35010) {
			return Industry.HOSPITALITY_LEISURE.toString();
		}
		else if (industry == 35011) {
			return Industry.INSURANCE.toString();
		}
		else if (industry == 35012) {
			return Industry.MANUFACTURING.toString();
		}
		else if (industry == 35013) {
			return Industry.MEDIA_ENTERTAINMENT.toString();
		}
		else if (industry == 35014) {
			return Industry.NOT_FOR_PROFIT_NGO.toString();
		}
		else if (industry == 35015) {
			return Industry.OTHER.toString();
		}
		else if (industry == 35016) {
			return Industry.PHARMACEUTICALS.toString();
		}
		else if (industry == 35018) {
			return Industry.TECHNOLOGY.toString();
		}
		else if (industry == 35019) {
			return Industry.TELECOMMUNICATIONS.toString();
		}
		else if (industry == 35020) {
			return Industry.TRANSPORTAION.toString();
		}
		else if (industry == 35022) {
			return Industry.UTILITIES.toString();
		}
		else if (industry == 35023) {
			return Industry.ENGINEERING.toString();
		}
		else if (industry == 35024) {
			return Industry.GOVERNMENT_FEDERAL.toString();
		}
		else if (industry == 35025) {
			return Industry.GOVERNMENT_STATE_LOCAL.toString();
		}
		else if (industry == 35026) {
			return Industry.PROFESSIONAL_SERVICES_AGENCY_BUSINESS.toString();
		}
		else if (industry == 35027) {
			return Industry.PROFESSIONAL_SERVICES_TECHNICAL_WEB_IT.toString();
		}
		else if (industry == 35028) {
			return Industry.FOOD_SERVICES.toString();
		}

		return StringPool.BLANK;
	}

	private boolean _getInternal(int type) {
		if (type == 3) {
			return true;
		}

		return false;
	}

	private long _getParentAccountId(String dossieraAccountKey)
		throws Exception {

		if (Validator.isNull(dossieraAccountKey)) {
			return 0;
		}

		long classNameId = _classNameLocalService.getClassNameId(Account.class);

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameId, "dossiera", "account", dossieraAccountKey, 0, 1);

		if (!externalLinks.isEmpty()) {
			ExternalLink externalLink = externalLinks.get(0);

			return externalLink.getClassPK();
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to find account with dossiera account key " +
					dossieraAccountKey);
		}

		return 0;
	}

	private String _getTier(int tier) {
		if (tier == 1) {
			return Tier.REGULAR.toString();
		}
		else if (tier == 2) {
			return Tier.OEM.toString();
		}
		else if (tier == 3) {
			return Tier.PREMIER.toString();
		}
		else if (tier == 4) {
			return Tier.STRATEGIC.toString();
		}

		return StringPool.BLANK;
	}

	private void _migrateAuditEntries(
			Connection connection, long userId, long corpProjectId)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("select OSB_AuditEntry.* from OSB_AuditEntry inner join ");
		sb.append("OSB_AccountEntry on OSB_AccountEntry.accountEntryId = ");
		sb.append("OSB_AuditEntry.classPK where ");
		sb.append("OSB_AccountEntry.corpProjectId = ");
		sb.append(corpProjectId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Date createDate = resultSet.getDate("createDate");
				long auditSetId = resultSet.getLong("auditSetId");
				long fieldClassNameId = resultSet.getLong("fieldClassNameId");
				int action = resultSet.getInt("action");
				int field = resultSet.getInt("field");
				String oldLabel = resultSet.getString("oldLabel");
				String oldValue = resultSet.getString("oldValue");
				String newLabel = resultSet.getString("newLabel");
				String newValue = resultSet.getString("newValue");
				String description = resultSet.getString("description");

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCreateDate(createDate);

				_auditEntryLocalService.addAuditEntry(
					userId,
					_classNameLocalService.getClassNameId(Account.class),
					corpProjectId, auditSetId,
					_getFieldClassNameId(fieldClassNameId), 0,
					_getAction(action), _getField(field), oldLabel, oldValue,
					newLabel, newValue, description, serviceContext);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CorpProjectMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private UserLocalService _userLocalService;

}