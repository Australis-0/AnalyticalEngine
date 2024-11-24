//Import classes
Object.defineProperty(this, "Game", {
	get: function () {
		return Java.type('aoc.kingdoms.lukasz.jakowski.Game');
	}
});

//Define global namespace for non-imports and event handlers
var global = {};

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