//Import classes - Dynamic Imports
{
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
}

//Load functions
setTimeout(function(){
	//Initialise global
	initGlobal();

	//Initialise optimisation
	initOptimisation();

	initialiseConsole();

	setTimeout(function(){
		//Initialise individual EventHandlers
		initialiseOnDateChangeHandler();
		initialiseOnPageChangeHandler();
		initialiseOnclickHandler();
		initialiseOnMapmodeChangeHandler();
		initialiseOnProvinceClickHandler();

		//Initialise EventHandler logic
		initialiseEventHandlers();
		initialiseMenuLogic();

		//Initialise Editor handlers
		initialiseProvinceNamesEditorEventHandlers();
		initialiseProvinceResourceEditorEventHandlers();
		initialiseProvinceTerrainEditorEventHandlers();

		//Load Mods
		loadMods();
	}, 3000);
}, 1);