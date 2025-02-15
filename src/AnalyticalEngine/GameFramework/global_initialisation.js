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
				//Game Interactions - Game.
				on_army_disband: {},
				on_army_recruitment: {},
				on_building_construction: {},
				on_rival: {},
				on_siege_end: {},
				on_war: {},
				on_war_end: {},

				//Game Interactions - Map.
				on_civilisation_click: {},
				on_province_click: {},

				//Game Interactions - Province.
				on_province_rename: {},

				on_province_build_click: {},
				on_province_building_click: {},
				on_province_bonus_modifiers_click: {},
				on_province_cores_click: {},
				on_province_defence_level_click: {},
				on_province_devastation_click: {},
				on_province_economy_click: {},
				on_province_infrastructure_click: {},
				on_province_loot_click: {},
				on_province_manpower_click: {},
				on_province_monthly_income_click: {},
				on_province_population_chart_click: {},
				on_province_religion_click: {},
				on_province_resource_click: {},
				on_province_tax_efficiency_click: {},
				on_province_terrain_picture_click: {},
				on_province_unrest_click: {},
				on_province_value_click: {},

				//4. Gamestate Loops.
				on_game_tick: {},

				on_game_daily_interval: {},
				on_game_monthly_interval: {},
				on_game_yearly_interval: {},

				//5. Menu Interactions.
				on_editor_click: {},
				on_game_exit: {},
				on_new_scenario: {}
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