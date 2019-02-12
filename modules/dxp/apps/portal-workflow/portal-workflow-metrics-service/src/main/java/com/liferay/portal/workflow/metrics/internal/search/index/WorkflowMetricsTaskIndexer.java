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

package com.liferay.portal.workflow.metrics.internal.search.index;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	service = {WorkflowMetricsIndexer.class, WorkflowMetricsTaskIndexer.class}
)
public class WorkflowMetricsTaskIndexer extends BaseWorkflowMetricsIndexer {

	@Override
	public String getIndexName() {
		return "workflow-metrics-tokens";
	}

	@Override
	public String getIndexType() {
		return "WorkflowMetricsTokenType";
	}

}