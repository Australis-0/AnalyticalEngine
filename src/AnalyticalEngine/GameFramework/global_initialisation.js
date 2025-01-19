//Import classes
Object.defineProperty(this, "Game", {
	get: function () {
		return Java.type('aoc.kingdoms.lukasz.jakowski.Game');
	}
});
Object.defineProperty(this, "Menu", {
	get: function () {
		return Java.type('aoc.kingdoms.lukasz.menu.Menu');
	}
});
Object.defineProperty(this, "MenuTitle", {
	get: function () {
		return Java.type('aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle');
	}
});

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
			events: {}, //Contains all Events instantiated via Nashorn in-game.
			interfaces: {}, //Contains all Menus instantiated via Nashorn in-game.

			//Map (Provinces and assoiated data structures)
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