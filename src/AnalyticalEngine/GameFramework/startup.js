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
			delete global.left_mouse_click; //mouse_down
			delete global.right_mouse_click; //mouse_down

			delete global.left_mouse_release; //mouse_up
			delete global.right_mouse_release; //mouse_up
		}, 8); //120FPS
	}, 3000);
}, 1);