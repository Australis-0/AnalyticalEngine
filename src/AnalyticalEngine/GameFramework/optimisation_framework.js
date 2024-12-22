{
	function initOptimisation () {
		//CONSOLE
		//Set .flattened_console_commands
		config.flattened_console_commands = dumbFlattenObject(config.console);

		config.all_console_commands = getAllConsoleCommands();
		config.all_console_commands_keys = getAllConsoleCommands({ return_keys: true });
		config.console_commands_lowest_order = getConsoleCommandsLowestOrder();

		//EDITOR - MAP CONTEXT MENU
		config.flattened_map_context_menus = dumbFlattenObject(config.editor_map_context_menu);

		config.all_map_context_menus = getAllMapContextMenus();
		config.all_map_context_menus_keys = getAllMapContextMenus({ return_keys: true });
		config.map_context_menus_lowest_order = getMapContextMenusLowestOrder();

			//Fix config.editor_map_context_menu such that all UIs have a .id
			{
				var all_map_context_menu_categories = Object.keys(config.editor_map_context_menu);

				for (var i = 0; i < all_map_context_menu_categories.length; i++) {
					var local_category = config.editor_map_context_menu[all_map_context_menu_categories[i]];

					//Iterate over all_local_category_keys
					var all_local_category_keys = Object.keys(local_category);

					for (var x = 0; x < all_local_category_keys.length; x++) {
						var local_value = local_category[all_local_category_keys[x]];

						if (!Array.isArray(local_value) && typeof local_value == "object")
							if (!local_value.id)
								local_value.id = all_local_category_keys[x];
					}
				}
			}

		//UI
		config.all_page_keys = undefined;
	}
}