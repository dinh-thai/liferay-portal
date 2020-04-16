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

package com.liferay.akismet.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AkismetEntry service. Represents a row in the &quot;OSBCommunity_AkismetEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jamie Sammons
 * @see AkismetEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.akismet.model.impl.AkismetEntryImpl")
@ProviderType
public interface AkismetEntry extends AkismetEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.akismet.model.impl.AkismetEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AkismetEntry, Long> AKISMET_ENTRY_ID_ACCESSOR =
		new Accessor<AkismetEntry, Long>() {

			@Override
			public Long get(AkismetEntry akismetEntry) {
				return akismetEntry.getAkismetEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AkismetEntry> getTypeClass() {
				return AkismetEntry.class;
			}

		};

}