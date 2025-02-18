//Initialise functions
{
	function initialiseProvinceRegionEditorEventHandlers () {
		if (!global.on_page_change) global.on_page_change = {};
		if (!global.on_province_click) global.on_province_click = {};

		global.on_page_change.EDITOR_MAPS_EDIT_GEO_REGION = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Initialise EDITOR_MAPS_EDIT_GEO_REGION UI if it doesn't already exist
			if (current_page == "EDITOR_MAPS_EDIT_GEO_REGION")
				if (!main.interfaces.province_region_editor) {
					//Set initial interface settings
					if (!main.interfaces.province_region_editor)
						main.interfaces.province_region_editor = {};
					var interface_obj = main.interfaces.province_region_editor;
					interface_obj.current_region_colour = [255, 255, 255];
					interface_obj.current_region_index = 1;
					interface_obj.current_region_name = "";
					interface_obj.total_session_offset = 0;

					//Input variables
					interface_obj.region_move_value = 1;
					interface_obj.region_shift_value = 1;

					var province_region_editor_menu = createContextMenu({
						id: "province_region_editor",
						name: "AnalyticalEngine - Province Region Editor",
						no_title: false,

						anchor: "top_right",
						can_close: false,
						height: 400,
						width: 4*CFG.BUTTON_WIDTH,
						x: 800,
						y: 150,

						province_region_editor_text: {
							type: "text",
							name: "",

							align: "left",
							font_weight: 400,
							height: 1.25,
							width: 4
						},
						create_region_button: {
							type: "button",
							name: "Create Region",
							width: 4,

							special_function: function (e) {
								createRegion({ index: current_region_index }); //[WIP] - This needs to actually insert the region at the caret
								EditorMapGeoRegions.currentGeoRegionID++;
							}
						},
						open_colour_picker_button: {
							type: "button",
							name: "Open Colour Picker",
							width: 4,

							special_function: function (e) {
								var region_obj = getAllRegions()[current_region_index];

								openColourPicker(current_region_colour);
							}
						},
						set_region_colour_button: {
							type: "button",
							name: "Set Region Colour",
							width: 4,

							special_function: function (e) {
								var region_colour = getColourPickerValue();

								modifyRegion(current_region_index, {
									colour: region_colour
								});
							}
						},
						delete_region_button: {
							type: "button",
							name: "Delete Region",
							width: 4,

							special_function: function (e) {
								deleteRegion(interface_obj.current_region_index);
							}
						}
					});
				}

			//NOTE - LOGIC LOOP needs to detect EditorMapGeoRegions.currentGeoRegionID changes.
			//When a change is detected, the colour picker should be refreshed - hideColourPicker(); openColourPicker([...]); and interface_obj.current_region_colour = [255, 255, 255] changed.
			//interface_obj.current_region_index = 1; also needs such updating
		};
	}
}