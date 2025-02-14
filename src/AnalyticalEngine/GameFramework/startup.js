//Import classes - Dynamic Imports
importDynamicClasses();

//Load functions
setTimeout(function(){
	//Initialise global
	initGlobal();

	//Initialise optimisation
	initOptimisation();

	initialiseConsole();

	setTimeout(function(){
		//Initialise individual EventHandlers
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