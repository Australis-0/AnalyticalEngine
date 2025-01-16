//Import classes
{
	this.GameCivsEdit = Java.type("aoc.kingdoms.lukasz.menusEditor.GameCivsEdit");
}

//Initialise functions
{
	//[WIP] - Map context menu is closed with left-click on map
	function closeMapContextMenu () {
		if (main.interfaces.map_context_menu)
			main.interfaces.map_context_menu.menu_obj.setVisible(false);
	}

	function openMapContextMenu (arg0_province_id, arg1_context_menu_id) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var context_menu_id = arg1_context_menu_id;

		//Declare local instance variables
		var current_page = getCurrentPage();

	}

	function openMapNavigationMenu (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var current_page = getCurrentPage();
		var editor_map_navigation_context_menu;
		var mouse_coords = getMouseCoords();
		var navigation_context_menu_obj = getMapContextMenuNavigationObject();
		var province_obj = getProvince(province_id);

		var civilisation_obj = getProvinceOwner(province_id, { return_object: true });

		var civilisation_name = getCivilisationName(civilisation_obj);
		var civilisation_tag = civilisation_obj.getCivTag();

		//1. Open context menu if not already open
		if (!main.interfaces[navigation_context_menu_obj.id]) {
			navigation_context_menu_obj.is_config_menu = true; //Set .is_config_menu so .special_function is processed properly

			navigation_context_menu_obj.x = mouse_coords[0];
			navigation_context_menu_obj.y = mouse_coords[1];
			createContextMenu(navigation_context_menu_obj);
		}

		//2. Transfer variables to interface_obj
		var interface_obj = main.interfaces[navigation_context_menu_obj.id];

		interface_obj.civilisation_obj = civilisation_obj;
		interface_obj.civilisation_name = civilisation_name;
		interface_obj.civilisation_tag = civilisation_tag;
		interface_obj.current_page = current_page;
		interface_obj.mouse_coords = mouse_coords;
		interface_obj.province_obj = province_obj;

		//3. Update interface_obj.menu_obj
		interface_obj.menu_obj.setPosX(mouse_coords[0]);
		interface_obj.menu_obj.setPosY(mouse_coords[1]);
		interface_obj.menu_obj.setVisible(true);

		//4. Iterate over all navigation_context_menu_obj_inputs
		var all_inputs = Object.keys(navigation_context_menu_obj);

		for (var i = 0; i < all_inputs.length; i++) {
			var local_element = getElement(navigation_context_menu_obj.id, all_inputs[i]);
			var local_input = navigation_context_menu_obj[all_inputs[i]];

			if (!Array.isArray(local_input) && typeof local_input == "object")
				if (local_element) {
					//Call .setText() on any element encountered with .name
					if (local_input.name)
						local_element.elements[0].setText(parseLocalisation(local_input.name, { scopes: {
							interface_obj: interface_obj
						} }));

				}
		}
	}

	function refreshMapContextMenus () {
		//Declare local instance variables
	}

	function refreshMapContextMenuInputs () {
		
	}
}