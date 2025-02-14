//Initialise functions
{
	function onGameDailyInterval (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_daily_interval[generateRandomID(main.scopes.on_game_daily_interval)] = local_function;
	}

	function onGameMonthlyInterval (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_monthly_interval[generateRandomID(main.scopes.on_game_monthly_interval)] = local_function;
	}

	function onGameTick (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_tick[generateRandomID(main.scopes.on_game_tick)] = local_function;
	}

	function onGameYearlyInterval (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_game_yearly_interval[generateRandomID(main.scopes.on_game_yearly_interval)] = local_function;
	}
}

//Initialise internal helper functions
{
	function parseOnGameDailyInterval () {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_game_daily_interval_keys = Object.keys(main.scopes.on_game_daily_interval);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameDailyInterval() fired.");

		//Iterate over all_on_game_daily_interval_keys
		for (var i = 0; i < all_on_game_daily_interval_keys.length; i++) {
			var local_function = main.scopes.on_game_daily_interval[all_on_game_daily_interval_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnGameTick () {
		//Declare local instance variables
		var all_on_game_tick_keys = Object.keys(main.scopes.on_game_tick);
		var local_options = {}; // Assuming no province-specific options are needed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameTick() fired.");

		//Iterate over all_on_game_tick_keys
		for (var i = 0; i < all_on_game_tick_keys.length; i++) {
			var local_function = main.scopes.on_game_tick[all_on_game_tick_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnGameMonthlyInterval () {
		//Declare local instance variables
		var all_on_game_monthly_interval_keys = Object.keys(main.scopes.on_game_monthly_interval);
		var local_options = {}; // No province-specific options assumed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameMonthlyInterval() fired.");

		//Iterate over all_on_game_monthly_interval_keys
		for (var i = 0; i < all_on_game_monthly_interval_keys.length; i++) {
			var local_function = main.scopes.on_game_monthly_interval[all_on_game_monthly_interval_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnGameYearlyInterval () {
		//Declare local instance variables
		var all_on_game_yearly_interval_keys = Object.keys(main.scopes.on_game_yearly_interval);
		var local_options = {}; // No province-specific options assumed

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onGameYearlyInterval() fired.");

		//Iterate over all_on_game_yearly_interval_keys
		for (var i = 0; i < all_on_game_yearly_interval_keys.length; i++) {
			var local_function = main.scopes.on_game_yearly_interval[all_on_game_yearly_interval_keys[i]];

			local_function(local_options);
		}
	}
}