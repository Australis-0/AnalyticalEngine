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
		initialiseUI();

		//The main loop used for game interactions; runs at 60FPS
		var interaction_loop = setInterval(function(){
			menuHandler();

			//KEEP AT BOTTOM! Set interaction flags to false after handling
		}, 16);
	}, 3000);
}, 1);