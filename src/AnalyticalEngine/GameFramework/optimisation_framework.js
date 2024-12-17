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

		//UI
		config.all_page_keys = undefined;
	}
}