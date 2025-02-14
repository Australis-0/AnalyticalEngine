//Initialise functions
{
	function getCurrentScenario () {
		//Return statement
		return Game.mapScenarios.sActiveScenarioTag;
	}

	function loadScenario (arg0_scenario) {
		//Convert from parameters
		var scenario = arg0_scenario;

		//Declare local instance variables
		var logic_loop_key = "scenario_" + scenario + "_logic_loop";

		//1. Fix scenario-related bugs; override provinces using AnalyticalEngine structures
		fixSeaProvinces();

		//2. Set global.cache
		global.cache.old_date_obj = getCurrentDate();

		//3. Initialise Scenario logic loop
		if (main[logic_loop_key])
			clearInterval(main[logic_loop_key]);

		//Set main[logic_loop_key]
		main[logic_loop_key] = setInterval(function(){
			//Refresh scenario data
			loadGenerals(); //Load generals

			//Scenario thread editor loop
		}, 100);
	}

	function loadScenarioStart (arg0_scenario) {
		//Convert from parameters
		var scenario = arg0_scenario;

		//1. Call startup events
		try {
			setTimeout(function(){
				//1. Startup functions
				parseEvents();

				//2. onGameStart() scope parsing
				var all_on_game_start_keys = Object.keys(main.scopes.on_game_start);

				for (var i = 0; i < all_on_game_start_keys.length; i++) {
					var local_function = main.scopes.on_game_start[all_on_game_start_keys[i]];

					local_function();
				}
			}, 3000);
		} catch (e) {
			console.log(e.stack);
		}
	}
}