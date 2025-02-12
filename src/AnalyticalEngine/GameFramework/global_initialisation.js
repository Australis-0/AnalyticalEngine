//Define global namespace for non-imports and event handlers
var global = {
	cache: {},
	debug: {}
};

//Initialise functions
{
	function setGlobalVariable (arg0_key, arg1_value) {
		//Convert from parameters
		var key = arg0_key;
		var value = arg1_value;

		//Set global[key]
		global[key] = value;
	}

	function initGlobal () {
		this.main = {
			events: {}, //Contains all Event interfaces instantiated via Nashorn in-game.
			gamestate: {
				global: {} //Contains all Global-scope variables declared for this particular savegame.
			}, //Contains the current Gamestate, i.e. all variables for this particular savegame.
			interfaces: {}, //Contains all Menus instantiated via Nashorn in-game.
			scopes: {
				//1. Event Scope.
				events: {},

				//2. Gamestate Scopes (External).
				on_game_start: {},

				//3. OnAction Scopes.
				on_army_disband: {},
				on_army_recruitment: {},

				//4. Gamestate Loops.

				//5. Menu Interactions.
			},

			//Config
			generals: {},

			//Map (Provinces and associated data structures)
			map: {
				//Mapmode renderer
				custom_mapmode: undefined,
				mapmodes: getAllMapmodes(),

				//Map data structures
				cities: []
			},
			mapmodes: {
				all_mapmodes: getAllMapmodes(),
				custom_mapmode: undefined,
				province_colours: {}
			},
			/*
			provinces: (Object)
				<province_key>: (Object)
					growth_rate: (Number, Percentage)
			 */
			provinces: {}
		};
	}
}