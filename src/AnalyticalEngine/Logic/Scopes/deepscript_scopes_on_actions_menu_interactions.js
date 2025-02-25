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

	function onGameLoad (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_load[generateRandomID(main.scopes.on_game_load)] = local_function;
	}

	function onGameSave (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_save[generateRandomID(main.scopes.on_game_save)] = local_function;
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

	function parseOnGameLoad (arg0_load_key) {
		//Convert from parameters
		var load_key = arg0_load_key;

		//Declare local instance variables
		var all_on_game_load_keys = Object.keys(main.scopes.on_game_load);
		var local_options = {};

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameLoad() fired. Loading save: " + load_key);

		//Iterate over all_on_game_load_keys
		for (var i = 0; i < all_on_game_load_keys.length; i++) {
			var local_function = main.scopes.on_game_load[all_on_game_load_keys[i]];

			local_function(load_key);
		}
	}

	function parseOnGameSave (arg0_save_key) {
		//Convert from parameters
		var save_key = arg0_save_key;

		//Declare local instance variables
		var all_on_game_save_keys = Object.keys(main.scopes.on_game_save);
		var local_options = {};

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameSave() fired. Saving game: " + save_key);

		//Iterate over all_on_game_save_keys
		for (var i = 0; i < all_on_game_save_keys.length; i++) {
			var local_function = main.scopes.on_game_save[all_on_game_save_keys[i]];

			local_function(save_key);
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