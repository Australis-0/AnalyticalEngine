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
		initialiseOnProvinceClickHandler();

		//Initialise EventHandler logic
		initialiseEventHandlers();
		initialiseUI();
	}, 3000);
}, 1);