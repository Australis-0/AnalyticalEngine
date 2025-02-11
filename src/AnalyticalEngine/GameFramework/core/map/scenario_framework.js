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

		//Fix scenario-related bugs; override provinces using AnalyticalEngine structures
		fixSeaProvinces();

		//Initialise Scenario logic loop
		if (main[logic_loop_key])
			clearInterval(main[logic_loop_key]);

		//Set main[logic_loop_key]
		main[logic_loop_key] = setInterval(function(){
			//Refresh scenario data
			loadGenerals(); //Load generals

			//Scenario thread editor loop
		}, 100);
	}
}