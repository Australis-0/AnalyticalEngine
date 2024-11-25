{
	function initOptimisation () {
		//CONSOLE
		//Set .flattened_console_commands
		config.flattened_console_commands = dumbFlattenObject(config.console);

		config.all_console_commands = getAllConsoleCommands();
		config.all_console_commands_keys = getAllConsoleCommands({ return_keys: true });
		config.console_commands_lowest_order = getConsoleCommandsLowestOrder();

		//UI
		config.all_page_keys = undefined;
	}
}