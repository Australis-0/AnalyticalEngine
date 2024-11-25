//Load function
initGlobal();
setTimeout(function(){
	//Initialise optimisation
	initOptimisation();

	initialiseConsole();

	setTimeout(function(){
		//Initialise individual EventHandlers
		initialiseOnPageChangeHandler();

		//Initialise EventHandler logic
		initialiseEventHandlers();
	}, 3000);
}, 1);