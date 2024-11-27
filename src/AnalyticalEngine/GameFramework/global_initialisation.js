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
	function initGlobal () {
		this.main = {
			//Map (Provinces and associated data structures)
			map: {
				cities: []
			}
		};
	}
}