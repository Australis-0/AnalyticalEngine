//Initialise functions
{
	function initialiseProvinceNamesEditorEventHandlers () {
		if (!global.on_page_change) global.on_page_change = {};
		if (!global.on_province_click) global.on_province_click = {};

		global.on_page_change.EDITOR_MAPS_EDIT_PROVINCE_NAMES = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Declare local instance variables

			//Initialise EDITOR_MAPS_EDIT_PROVINCE_NAMES UI if it doesn't already exist
			//console.log("Page changed to " + current_page);
			if (current_page == "EDITOR_MAPS_EDIT_PROVINCE_NAMES")
				if (!main.interfaces.province_names_editor) {
					//Set initial interface settings
					if (!main.interfaces.province_names_editor)
						main.interfaces.province_names_editor = {};
					var interface_obj = main.interfaces.province_names_editor;
					interface_obj.current_province_name = "";
					interface_obj.edit_province_names = false;

					var province_names_editor_menu = createContextMenu({
						id: "province_names_editor",
						name: "AnalyticalEngine\nProvince Names Editor:",
						no_title: false,

						anchor: "top_right",
						can_close: false,
						height: 250,
						width: 3*CFG.BUTTON_WIDTH,
						x: 500,
						y: 150,

						province_names_editor_text: {
							type: "text",
							name: "Placeholder",

							align: "left",
							font_weight: 400,
							height: 0.75,
							width: 3
						},
						edit_province_names_button: {
							type: "button",
							name: "Edit province names",
							width: 3,

							special_function: function (e) {
								//Convert from parameters
								var button_el = e.element;
								var interface_obj = e.interface_obj;

								interface_obj.edit_province_names = (!interface_obj.edit_province_names);
								(!interface_obj.edit_province_names) ?
									button_el.setText("Edit province names") : button_el.setText("Edit province name positions");
							}
						},
						view_unnamed_provinces_button: {
							type: "button",
							name: "View unnamed provinces",
							width: 3,

							special_function: function (e) {
								//Convert from parameters
								var button_el = e.element;
								var interface_obj = e.interface_obj;

								//Declare local instance variables
								var current_mapmode = getCurrentMapmode();

								if (current_mapmode != "undefined_province_names") {
									switchMapmode("undefined_province_names");
									button_el.setText("Hide unnamed provinces");
								} else {
									clearMapmode();
									button_el.setText("View unnamed provinces");
								}
							}
						},
						save_province_names_button: {
							type: "button",
							name: "Save province names",
							width: 3,

							special_function: function (e) {
								saveCities();

								Game.menuManager.addToast("[AnalyticalEngine] Saved province names.");
								console.log("[Editor] Province Names Editor: Saved province names.");
							}
						}
					});

					if (!interface_obj.logic_loop)
						interface_obj.logic_loop = updateProvinceNamesEditor();
				}
		};
		global.on_province_click.EDITOR_MAPS_EDIT_PROVINCE_NAMES = function (arg0_province_id) {
			//Convert from parameters
			var province_id = arg0_province_id;

			//Declare local instance variables
			var current_page = getCurrentPage();
			var interface_obj = main.interfaces.province_names_editor;

			//Check to see if the current page is EDITOR_MAPS_EDIT_PROVINCE_NAMES
			if (current_page == "EDITOR_MAPS_EDIT_PROVINCE_NAMES")
				if (interface_obj != undefined) {
					var province_obj = getProvince(province_id);

					var current_province_name = province_obj.getProvinceName();
					interface_obj.current_province_name = current_province_name;

					if (interface_obj.edit_province_names) {
						var new_province_name = openKeyboardPrompt("new_province_name", {
							placeholder: current_province_name,

							special_function: function (e) {
							},
							update_function: function (e) {
								//Declare local instance variables
								var city_index = getCity(province_id, { return_index: true });
								var province_centre = getProvinceCentre(province_id);

								//Set province name; update city object
								interface_obj.current_province_name = e;
								province_obj.setProvinceName(interface_obj.current_province_name);

								if (city_index)
									console.log("City exists in index " + city_index);

								//Modify city
								modifyCity(province_id, interface_obj.current_province_name, province_centre[0], province_centre[1]);
							}
						});
					}
				}
		}
	}

	function updateProvinceNamesEditor () {
		//Return statement
		return setInterval(function(){
			//Declare local instance variables
			var interface_obj = main.interfaces.province_names_editor;

			var current_province_name = interface_obj.current_province_name;
			var display_text_el = getContextMenuElement(interface_obj, "province_names_editor_text");

			//Refresh province names editor display
			if (display_text_el.elements[0]) {
				display_text_el.elements[0].setText(
					"Province Name: " + current_province_name + "|" +
					"\nEditing province names: " + interface_obj.edit_province_names + "");
			}
		}, 8); //120FPS
	}
}