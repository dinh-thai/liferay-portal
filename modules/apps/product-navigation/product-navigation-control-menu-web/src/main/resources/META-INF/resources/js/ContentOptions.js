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

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayForm, {ClaySelectWithOption} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import React, {useState} from 'react';

import 'product-navigation-control-menu/css/ContentOptions.scss';

const OPTIONS = [
	{
		label: Liferay.Util.sub(Liferay.Language.get('x-items'), 4),
		value: 4,
	},
	{
		label: Liferay.Util.sub(Liferay.Language.get('x-items'), 8),
		value: 8,
	},
	{
		label: Liferay.Util.sub(Liferay.Language.get('x-items'), 10),
		selected: true,
		value: 10,
	},
	{
		label: Liferay.Util.sub(Liferay.Language.get('x-items'), 20),
		value: 20,
	},
];
const TYPES = [
	'$Basic Document',
	'$Basic Web Content',
	'$Blog Entry',
	'$Bookmarks Entry',
];

const ContentOptions = ({
	grid,
	onChangeListMode,
	onChangeSelect,
	portletNamespace,
}) => {
	const [active, setActive] = useState(false);

	return (
		<div className="sidebar-content__panel__content-options">
			<ClayForm.Group small>
				<ClaySelectWithOption
					aria-label="Select Label"
					className="btn-monospaced sidebar-content__panel__content-options-select"
					id={`${portletNamespace}_contentDropdown`}
					onChange={(event) => onChangeSelect(event.target.value)}
					options={OPTIONS}
					sizing="sm"
				/>
			</ClayForm.Group>
			<ClayButton
				className="btn-monospaced sidebar-content__panel__content-options-list"
				displayType="unstyled"
				onClick={() => onChangeListMode(!grid)}
				small
				title={Liferay.Language.get('display-style')}
			>
				<ClayIcon symbol={grid ? 'cards2' : 'list'} />
				<span className="sr-only">
					{Liferay.Language.get('display-style')}
				</span>
			</ClayButton>

			<ClayDropDown
				active={active}
				onActiveChange={setActive}
				trigger={
					<ClayButton
						className="btn-monospaced sidebar-content__panel__content-options-add"
						displayType="unstyled"
						small
						title={Liferay.Language.get('add-new')}
					>
						<ClayIcon symbol="plus" />
						<span className="sr-only">
							{Liferay.Language.get('add-new')}
						</span>
					</ClayButton>
				}
			>
				<ClayDropDown.ItemList>
					{TYPES.map((type) => (
						<ClayDropDown.Item
							key={type}
							onClick={() => setActive(false)}
						>
							{type}
						</ClayDropDown.Item>
					))}
				</ClayDropDown.ItemList>
			</ClayDropDown>
		</div>
	);
};

export default ContentOptions;
