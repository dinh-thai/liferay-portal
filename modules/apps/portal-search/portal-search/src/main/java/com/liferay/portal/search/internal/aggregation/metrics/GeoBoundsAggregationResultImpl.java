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

package com.liferay.portal.search.internal.aggregation.metrics;

import com.liferay.portal.search.aggregation.metrics.GeoBoundsAggregationResult;
import com.liferay.portal.search.geolocation.GeoLocationPoint;
import com.liferay.portal.search.internal.aggregation.BaseAggregationResult;

/**
 * @author Michael C. Han
 */
public class GeoBoundsAggregationResultImpl
	extends BaseAggregationResult implements GeoBoundsAggregationResult {

	public GeoBoundsAggregationResultImpl(
		String name, GeoLocationPoint topLeft, GeoLocationPoint bottomRight) {

		super(name);

		_topLeft = topLeft;
		_bottomRight = bottomRight;
	}

	@Override
	public GeoLocationPoint getBottomRight() {
		return _bottomRight;
	}

	@Override
	public GeoLocationPoint getTopLeft() {
		return _topLeft;
	}

	@Override
	public void setBottomRight(GeoLocationPoint bottomRight) {
		_bottomRight = bottomRight;
	}

	@Override
	public void setTopLeft(GeoLocationPoint topLeft) {
		_topLeft = topLeft;
	}

	private GeoLocationPoint _bottomRight;
	private GeoLocationPoint _topLeft;

}