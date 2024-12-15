//Load function
initGlobal();
setTimeout(function(){
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
	}, 3000);
}, 1);