//Initialise functions
{
	function onEditorClick (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_editor_click[generateRandomID(main.scopes.on_editor_click)] = local_function;
	}

	function onGameExit (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_exit[generateRandomID(main.scopes.on_game_exit)] = local_function;
	}

	function onNewScenario (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_new_scenario[generateRandomID(main.scopes.on_new_scenario)] = local_function;
	}
}

//Initialise internal helper functions
{
	function parseOnEditorClick () {
		//Declare local instance variables
		var all_on_editor_click_keys = Object.keys(main.scopes.on_editor_click);
		var local_options = {}; // No province-specific options assumed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onEditorClick() fired.");

		//Iterate over all_on_editor_click_keys
		for (var i = 0; i < all_on_editor_click_keys.length; i++) {
			var local_function = main.scopes.on_editor_click[all_on_editor_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnGameExit () {
		//Declare local instance variables
		var all_on_game_exit_keys = Object.keys(main.scopes.on_game_exit);
		var local_options = {}; // No province-specific options assumed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameExit() fired.");

		//Iterate over all_on_game_exit_keys
		for (var i = 0; i < all_on_game_exit_keys.length; i++) {
			var local_function = main.scopes.on_game_exit[all_on_game_exit_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnNewScenario () {
		//Declare local instance variables
		var all_on_new_scenario_keys = Object.keys(main.scopes.on_new_scenario);
		var local_options = {}; // No province-specific options assumed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onNewScenario() fired.");

		//Iterate over all_on_new_scenario_keys
		for (var i = 0; i < all_on_new_scenario_keys.length; i++) {
			var local_function = main.scopes.on_new_scenario[all_on_new_scenario_keys[i]];

			local_function(local_options);
		}
	}

}