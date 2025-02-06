//Initialise functions
{
	function initialiseProvinceResourceEditorEventHandlers () {
		if (!global.on_page_change) global.on_page_change = {};
		if (!global.on_province_click) global.on_province_click = {};

		global.on_page_change.EDITOR_MAPS_EDIT_RESOURCE = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Initialise EDITOR_MAPS_EDIT_RESOURCE UI if it doesn't already exist
			if (current_page == "EDITOR_MAPS_EDIT_RESOURCE")
				if (!main.interfaces.province_resource_editor) {
					//Set initial interface settings
					if (!main.interfaces.province_resource_editor)
						main.interfaces.province_resource_editor = {};
					var interface_obj = main.interfaces.province_resource_editor;
					interface_obj.all_resources = getAllResources();
					interface_obj.current_province_name = "";
					interface_obj.current_resource_name = "";
					interface_obj.total_session_offset = 0;

					//Input variables
					interface_obj.resource_shift_value = 1;

					var province_resource_editor_menu = createContextMenu({
						id: "province_resource_editor",
						name: "AnalyticalEngine - Province Resource Editor",
						no_title: false,

						anchor: "top_right",
						can_close: false,
						height: 400,
						width: 4*CFG.BUTTON_WIDTH,
						x: 800,
						y: 150,

						province_resource_editor_text: {
							type: "text",
							name: "",

							align: "left",
							font_weight: 400,
							height: 1.25,
							width: 4
						},
						resource_shift_value: {
							type: "slider",
							name: "Resource Shift Value: ",
							height: 0.5,
							width: 4,

							min: 0,
							max: interface_obj.all_resources.length,
							placeholder: 1,

							special_function: function (e) {
								var slider_el = e.element;
								var interface_obj = e.interface_obj;
								var local_value = slider_el.getCurrent();

								interface_obj.resource_shift_value = local_value;
								printEditorAlert("Province Resource Editor: Resource Shift Value set to " + local_value);
							}
						},
						shift_province_resource_up_button:  {
							type: "button",
							name: "Shift all resource types up",
							width: 4,

							special_function: function  (e) {
								var button_el = e.element;
								var interface_obj = e.interface_obj;

								interface_obj.total_session_offset += interface_obj.resource_shift_value;
								interface_obj.total_session_offset = interface_obj.total_session_offset % interface_obj.all_resources.length;
								shiftMapResources(interface_obj.resource_shift_value, { excluded_ids: [] });
								printEditorAlert("Province Resource Editor: Shifted all province resources up by " + interface_obj.resource_shift_value + ".");
							}
						},
						shift_province_resource_down_button:  {
							type: "button",
							name: "Shift all resource types down",
							width: 4,

							special_function: function  (e) {
								var button_el = e.element;
								var interface_obj = e.interface_obj;

								interface_obj.total_session_offset -= interface_obj.resource_shift_value;
								interface_obj.total_session_offset = interface_obj.total_session_offset % interface_obj.all_resources.length;
								shiftMapResources(interface_obj.resource_shift_value*-1, { excluded_ids: [] });
								printEditorAlert("Province Resource Editor: Shifted all province resources down by " + interface_obj.resource_shift_value + ".");
							}
						}
					});

					if (!interface_obj.logic_loop)
						interface_obj.logic_loop = updateProvinceResourceEditor();
				}
		};
		global.on_province_click.EDITOR_MAPS_EDIT_RESOURCE = function (arg0_province_id) {
			//Convert from parameters
			var province_id = arg0_province_id;

			//Declare local instance variables
			var current_page = getCurrentPage();
			var interface_obj = main.interfaces.province_resource_editor;

			//Check to see if the current page is EDITOR_MAPS_EDIT_RESOURCE
			if (current_page == "EDITOR_MAPS_EDIT_RESOURCE")
				if (interface_obj != undefined) {
					var province_obj = getProvince(province_id);
					var province_resource_obj = getProvinceResource(province_id);

					var current_province_name = province_obj.getProvinceName();
					interface_obj.current_province_name = current_province_name;
					interface_obj.current_resource_name = province_resource_obj;
				}
		}
	}

	function updateProvinceResourceEditor () {
		//Return statement
		return setInterval(function(){
			try {
				//Declare local instance variables
				var interface_obj = main.interfaces.province_resource_editor;

				var current_province_name = interface_obj.current_province_name;
				var current_resource_name = interface_obj.current_resource_name;
				var display_text_el = getElement(interface_obj, "province_resource_editor_text");

				//Refresh province resource editor display
				if (display_text_el.elements[0])
					display_text_el.elements[0].setText(
						"Province Name: " + current_province_name +
						"\nProvince Resource: " + current_resource_name +
						"\nTotal Session Resource Offset: " + interface_obj.total_session_offset + "/" + interface_obj.all_resources.length
					);
			} catch (e) {
				console.error(e.message);
			}
		}, 100);
	}
}