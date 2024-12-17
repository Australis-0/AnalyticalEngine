//Initialise functions
{
	function getAllMapContextMenus (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var flattened_map_context_menus = config.flattened_map_context_menus;
		var return_context_menus = [];
		var return_keys = [];

		//Iterate over all_flattened_map_context_menus
		var all_flattened_map_context_menus = Object.keys(flattened_map_context_menus);

		for (var i = 0; i < all_flattened_map_context_menus.length; i++)
			if (!config.editor_map_context_menu_reserved_keys.includes(all_flattened_map_context_menus[i])) {
				return_context_menus.push(flattened_map_context_menus[all_flattened_map_context_menus[i]]);
				return_keys.push(all_flattened_map_context_menus[i]);
			}

		//Return statement
		return (!options.return_context_menus) ? return_context_menus : return_keys;
	}

	function getMapContextMenu (arg0_context_menu_id, arg1_options) {
		//Convert from parameters
		var context_menu_id = arg0_context_menu_id;
		var options = (arg1_options) ? arg1_options : {};

		//Guard clause for objects; direct keys
		if (typeof context_menu_id == "object") return context_menu_id;
		if (config.flattened_map_context_menus[context_menu_id]) return (!options.return_key) ?
			config.flattened_map_context_menus[context_menu_id] : context_menu_id;

		//Declare local instance variables
		var context_menu_exists = [false, ""]; //[context_menu_exists, context_menu_key];
		var search_name = context_menu_id.toLowerCase().trim();

		//ID search - safe search 1st, hard search 2nd
		{
			//Iterate over config.all_map_context_menus
			for (var i = 0; i < config.all_map_context_menus.length; i++) {
				var local_value = config.all_map_context_menus[i];

				if (local_value.id.toLowerCase().includes(search_name))
					context_menu_exists = [true, local_value.key];
			}
			for (var i = 0; i < config.all_map_context_menus.length; i++) {
				var local_value = config.all_map_context_menus[i];

				if (local_value.id.toLowerCase() == search_name)
					context_menu_exists = [true, local_value.key];
			}
		}

		//Name search - safe search 1st, hard search 2nd
		{
			//Iterate over config.all_map_context_menus
			for (var i = 0; i < config.all_map_context_menus.length; i++) {
				var local_value = config.all_map_context_menus[i];

				if (local_value.name.toLowerCase().includes(search_name))
					context_menu_exists = [true, local_value.key];
			}
			for (var i = 0; i < config.all_map_context_menus.length; i++) {
				var local_value = config.all_map_context_menus[i];

				if (local_value.name.toLowerCase() == search_name)
					context_menu_exists = [true, local_value.key];
			}
		}

		//Return statement
		if (context_menu_exists[0])
			return (!options.return_key) ?
				config.flattened_map_context_menus[context_menu_exists[1]] : context_menu_exists[1];
	}

	function getMapContextMenusAtOrder (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var flattened_map_context_menus = config.flattened_map_context_menus;
		var order = (options.order != undefined) ? options.order : 1;
		var return_context_menus = [];
		var return_obj = {};
		var return_keys = [];

		//Iterate over all_flattened_map_context_menus
		var all_flattened_map_context_menus = Object.keys(config.flattened_map_context_menus);

		for (var i = 0; i < all_flattened_map_context_menus.length; i++) {
			var local_map_context_menu = flattened_map_context_menus[all_flattened_map_context_menus[i]];

			if (local_map_context_menu.order == options.order) {
				return_context_menus.push(local_map_context_menu);
				return_keys.push(all_flattened_map_context_menus[i]);
			}
		}

		//options.return_object handler
		if (options.return_object) {
			for (var i = 0; i < return_context_menus.length; i++)
				return_obj[return_keys[i]] = return_context_menus[i];
			//Return statement
			return return_obj;
		}

		//Return statement
		return (!options.return_key) ? return_context_menus : return_keys;
	}

	function getMapContextMenuInput (arg0_context_menu_id, arg1_input_id) {
		//Convert from parameters
		var context_menu_id = arg0_context_menu_id;
		var input_id = arg1_input_id;

		//Declare local instance variables
		var map_context_menu = getMapContextMenu(context_menu_id);

		if (map_context_menu) {
			//Guard clause if citing indirect key
			if (map_context_menu[input_id]) return map_context_menu[input_id];

			//Iterate over all_inputs
			var all_inputs = Object.keys(map_context_menu);

			for (var i = 0; i < all_inputs.length; i++) {
				var local_input = map_context_menu[all_inputs[i]];

				if (!Array.isArray(local_input) && typeof local_input == "object")
					if (local_input.id == input_id)
						//Return statement
						return local_input;
			}
		}
	}

	function getMapContextMenusLowestOrder () {
		//Declare local instance variables;
		var flattened_map_context_menus = config.flattened_map_context_menus;
		var min_order = Infinity;

		//Iterate over all_flattened_map_context_menus
		var all_flattened_map_context_menus = Object.keys(flattened_map_context_menus);

		for (var i = 0; i < all_flattened_map_context_menus.length; i++) {
			var local_map_context_menu = flattened_map_context_menus[all_flattened_map_context_menus[i]];

			if (local_map_context_menu.order != undefined)
				min_order = Math.min(min_order, local_map_context_menu.order);
		}

		//Return statement
		return min_order;
	}

	function getMapContextMenuNavigationObject () {
		//Declare local instance variables
		var flattened_map_context_menus = config.flattened_map_context_menus;
		var lowest_order = getMapContextMenusLowestOrder();

		var actions_at_order = getMapContextMenusAtOrder({ order: lowest_order, return_object: true });
		var return_obj;

		//console.log("Actions at order: ", actions_at_order);

		//Iterate over all_actions_at_order_keys keys
		if (lowest_order != undefined) {
			var all_actions_at_order_keys = Object.keys(actions_at_order);

			for (var i = 0; i < all_actions_at_order_keys.length; i++) {
				var local_context_menu_obj = actions_at_order[all_actions_at_order_keys[i]];

				if (!Array.isArray(local_context_menu_obj) && typeof local_context_menu_obj == "object")
					return_obj = local_context_menu_obj;
			}
		}


		//Return statement
		return return_obj;
	}
}