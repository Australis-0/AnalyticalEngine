//Import classes
{
	this.Civilization = Java.type("aoc.kingdoms.lukasz.map.civilization.Civilization");
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
}

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

		//Initialise Scenario logic loop
		if (main[logic_loop_key])
			clearInterval(main[logic_loop_key]);

		//Load Scenario logic
		loadScenarioCivilisationNames(scenario);
	}

	function loadScenarioCivilisationNames (arg0_scenario) {
		//Convert from parameters
		var scenario = arg0_scenario;

		//Declare local instance variables
		var all_civ_tags = getAllCurrentCivTags();

		//Iterate over all_civ_tags an set their real names
		/*for (var i = 0; i < all_civ_tags.length; i++) {
			var local_actual_civ_name = getCivilisationActualName(all_civ_tags[i]);
			var local_civilisation = getCivilisation(all_civ_tags[i]);

			//Set local_civilisation
			if (local_actual_civ_name)
				setCivilisationName(local_civilisation, local_actual_civ_name, { do_not_reload: true });
		}

		//Rebuild Civilisation names using a Monte-Carlo approach
		for (var i = 0; i < 100; i++) {
			var local_delay = i*randomNumber(0, 3000);

			if (local_delay < 25000)
				setTimeout(function(){
					try {
						CivilizationRegionsManager.buildCivilizationsRegions_TextOver();
					} catch (e) {}
				}, local_delay);
		}*/
	}
}