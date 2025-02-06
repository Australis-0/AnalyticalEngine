//Initialise functions
{
	function getJavaFloat (arg0_java_number) {
		//Convert from parameters
		var java_number = arg0_java_number;

		//Return statement
		return Float.valueOf(java_number);
	}

	function getJavaInteger (arg0_java_number) {
		//Convert from parameters
		var java_number = arg0_java_number;

		//Return statement
		return Integer.valueOf(java_number);
	}

	function setJavaFloat (arg0_js_number) {
		//Convert from parameters
		var js_number = arg0_js_number;

		//Return statement
		return js_number.floatValue();
	}

	function setJavaInteger (arg0_js_number) {
		//Convert from parameters
		var js_number = arg0_js_number;

		//Return statement
		return js_number.intValue();
	}
	/**
	 * createMenuTitle() - Creates a MenuTitle for an open menu.
	 * @param {Object} [arg0_options]
	 * @param {String} [arg0_options.name]
	 *
	 * @param {boolean} [arg0_options.draggable=true]
	 * @param {number} [arg0_options.font_size=1.0]
	 * @param {number} [arg0_options.height=CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT/2]
	 * @param {boolean} [arg0_options.resizeable=true]
	 *
	 * @returns {MenuTitle}
	 */
	/*
	function createDummyMenu () {
		//Dummy code
		var menu_elements = new ArrayList();
		var menu_obj = new Menu();

		var button_obj = createButton({ name: "Test", width: 5 });
		var current_view_id = Game.menuManager.viewID;
		var init_menu_method = menu_obj.getClass().getDeclaredMethod(
			"initMenu",
			MenuTitle.class,
			java.lang.Integer.TYPE,
			java.lang.Integer.TYPE,
			java.lang.Integer.TYPE,
			java.lang.Integer.TYPE,
			java.util.List.class,
			java.lang.Boolean.TYPE,
			java.lang.Boolean.TYPE,
			java.lang.Boolean.TYPE,
			java.lang.Boolean.TYPE
		);
			init_menu_method.setAccessible(true);
		var menu_title_obj = createMenuTitle({ name: "Test" });

		//Add dummy UI elements
		menu_elements.add(button_obj);

		//Initialise menu and add to view
		//init_menu_method.invoke(menu_obj, menu_title_obj, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menu_elements, true, false, true, false);
		var menu_height = (getJavaInteger(CFG.GAME_HEIGHT)/4);
		console.log("Menu height: " + menu_height);
		init_menu_method.invoke(menu_obj, menu_title_obj, 500, 400, CFG.LEFT_MENU_WIDTH, setJavaInteger(menu_height), menu_elements, true, false, true, false);
		Game.menuManager.addNextMenuToView(current_view_id, menu_obj);

		//Return statement
		return menu_obj;
	}
	 */
}