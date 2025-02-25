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

		initialiseOnCivilisationClickHandler();
		initialiseOnclickHandler();
		initialiseOnMapmodeChangeHandler();
		initialiseOnProvinceClickHandler();
		initialiseOnProvinceRenameHandler();

		//Initialise EventHandler logic
		initialiseEventHandlers();
		initialiseMenuLogic();

		//Initialise Editor handlers
		initialiseProvinceNamesEditorEventHandlers();
		initialiseProvinceRegionEditorEventHandlers();
		initialiseProvinceResourceEditorEventHandlers();
		initialiseProvinceTerrainEditorEventHandlers();

		//Initialise Save/Load functionality
		initialiseGameSaveLoad();

		//Load Mods
		loadMods();
	}, 3000);
}, 1);