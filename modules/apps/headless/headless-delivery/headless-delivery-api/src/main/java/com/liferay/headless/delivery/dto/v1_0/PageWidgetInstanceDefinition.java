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

package com.liferay.headless.delivery.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName("PageWidgetInstanceDefinition")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PageWidgetInstanceDefinition")
public class PageWidgetInstanceDefinition {

	@Schema
	@Valid
	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	@JsonIgnore
	public void setWidget(
		UnsafeSupplier<Widget, Exception> widgetUnsafeSupplier) {

		try {
			widget = widgetUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Widget widget;

	@Schema
	@Valid
	public Map<String, Object> getWidgetConfig() {
		return widgetConfig;
	}

	public void setWidgetConfig(Map<String, Object> widgetConfig) {
		this.widgetConfig = widgetConfig;
	}

	@JsonIgnore
	public void setWidgetConfig(
		UnsafeSupplier<Map<String, Object>, Exception>
			widgetConfigUnsafeSupplier) {

		try {
			widgetConfig = widgetConfigUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> widgetConfig;

	@Schema
	@Valid
	public Map<String, Object> getWidgetPermissions() {
		return widgetPermissions;
	}

	public void setWidgetPermissions(Map<String, Object> widgetPermissions) {
		this.widgetPermissions = widgetPermissions;
	}

	@JsonIgnore
	public void setWidgetPermissions(
		UnsafeSupplier<Map<String, Object>, Exception>
			widgetPermissionsUnsafeSupplier) {

		try {
			widgetPermissions = widgetPermissionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> widgetPermissions;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PageWidgetInstanceDefinition)) {
			return false;
		}

		PageWidgetInstanceDefinition pageWidgetInstanceDefinition =
			(PageWidgetInstanceDefinition)object;

		return Objects.equals(
			toString(), pageWidgetInstanceDefinition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (widget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"widget\": ");

			sb.append(String.valueOf(widget));
		}

		if (widgetConfig != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"widgetConfig\": ");

			sb.append(_toJSON(widgetConfig));
		}

		if (widgetPermissions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"widgetPermissions\": ");

			sb.append(_toJSON(widgetPermissions));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.PageWidgetInstanceDefinition",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}