//Import classes
{
	this.AA_Game = Java.type("aoc.kingdoms.lukasz.jakowski.AA_Game");
	this.ArrayList = Java.type("java.util.ArrayList");
	this.ButtonMain = Java.type("aoc.kingdoms.lukasz.menu_element.button.ButtonMain");
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
	this.DesktopLauncher = Java.type("aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher");
	//this.Menu = Java.type("aoc.kingdoms.lukasz.menu.Menu");
	//this.MenuTitle = Java.type("aoc.kingdoms.lukasz.menu.menuTitle");
	this.Method = Java.type("java.lang.reflect.Method");

	this.boolean_class = Java.type("java.lang.Boolean").TYPE;
	this.integer_class = Java.type("java.lang.Integer").TYPE;
	this.list_class = Java.type("java.util.List");
	this.Renderer = Java.type("aoc.kingdoms.lukasz.jakowski.Renderer.Renderer");
	this.Touch = Java.type("aoc.kingdoms.lukasz.jakowski.Touch");
}

//Initialise functions
{
	/**
	 * createButton() - Creates a button and returns it as an Object for adding to menuElements in createContextMenu().
	 * @param {Object} [arg0_options]
	 * @param {String} [arg0_options.name]
	 * @param {number} [arg0_options.width=2] - The width as multiplied by CFG.BUTTON_WIDTH.
	 * @param {number} [arg0_options.x=0]
	 * @param {number} [arg0_options.y=0]
	 *
	 * @param {String} [arg0_options.align="centre"] - Optional. Either 'left', 'centre', or 'right'.
	 * @param {boolean} [arg0_options.is_clickable=true]
	 * @param {Function} [arg0_options.special_function]
	 * @param {String} [arg0_options.tooltip]
	 *
	 * @returns {ButtonMain}
	 */
	function createButton (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise functions
		options.align = (options.align) ? options.align : "centre";
		options.is_clickable = (options.is_clickable != false) ? true : false;
		options.name = (options.name) ? options.name : "";
		options.width = returnSafeNumber(options.width, 2);
		options.x = returnSafeNumber(options.x);
		options.y = returnSafeNumber(options.y);

		console.log("Options", options);

		//Declare local instance variables
		var actual_text_position = -1;
			//Adjust actual_text_position
			if (options.align == "left") actual_text_position = 0;

		//Construct new_button_obj
		var new_button_obj = new ButtonMain(
			null, //(sText) Initial string
			0, //(fontID)
			actual_text_position, //(itextPositionX)
			parseInt(options.x), //(iPosX)
			parseInt(options.y), //(iPosY)
			parseInt(CFG.BUTTON_WIDTH*options.width), //(nWidth)
			options.is_clickable //(isClickable)
		);
		/*var new_button_obj_delegate = {
			actionElement: function () {
				if (options.special_function)
					options.special_function(this);
			},
			updateLanguage: function () {
				new_button_obj.setText(options.name);
			}
		};*/
		//new_button_obj.delegate = new_button_obj_delegate;
		new_button_obj.setText(options.name);
		new_button_obj.actionElement = function () {
			console.log("Hello!");
			if (options.special_function)
				options.special_function(this);
		}

		//Return statement
		return new_button_obj;
	}

	/**
	 * createContextMenu() - Creates a new context menu to add for the current in-game screen.
	 * @param [arg0_options]
	 * @param {boolean} [arg0_options.can_close=true]
	 * @param {boolean} [arg0_options.draggable=true]
	 * @param {boolean} [arg0_options.has_back_button=false]
	 * @param {boolean} [arg0_options.lock_hover=false]
	 * @param {boolean} [arg0_options.resizeable=true]
	 * @param {String} [arg0_options.title]
	 *
	 * @param {number} [arg0_options.height]
	 * @param {number} [arg0_options.width]
	 * @param {number} [arg0_options.x]
	 * @param {number} [arg0_options.y]
	 *
	 * @param {Object} [arg0_options."input_key"]
	 *  @param {String} [arg0_options."input_key".name]
	 * 	@param {String} [arg0_options."input_key".type="button"]
	 * 	@param {number} [arg0_options."input_key".width=2] - The width as multiplied by CFG.BUTTON_WIDTH.
	 * 	@param {number} [arg0_options."input_key".x]
	 * 	@param {number} [arg0_options."input_key".y]
	 *
	 *  @param {String} [arg0_options."input_key".align="centre"] - Optional. Either 'left', 'centre', or 'right'.
	 *  @param {boolean} [arg0_options.is_clickable=true]
	 *  @param {Function} [arg0_options."input_key".special_function]
	 *  @param {String} [arg0_options."input_key".tooltip]
	 */
	function createContextMenu (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables

		//Construct MenuTitle

		//Dummy code
	}

	function createDummyMenu () {
		//Dummy code
		var menu_elements = new ArrayList();
		var menu_obj = new Menu();

		var button_obj = createButton({ name: "Test" });
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

		//Add dummy UI elements
		menu_elements.add(button_obj);

		//Initialise menu and add to view
		init_menu_method.invoke(menu_obj, null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menu_elements, true, false, true, false);
		Game.menuManager.addNextMenuToView(current_view_id, menu_obj);

		//Return statement
		return menu_obj;
	}

	function menuHandler () {
		//Declare local instance variables
		var dummy_menu_obj = global.dummy_menu_obj; //Temp synchronisation

		var current_view_id = Game.menuManager.viewID;
		var dummy_menu_elements_length = dummy_menu_obj.getMenuElementsSize();
		var dummy_menu_elements = [];

		//Populate dummy_menu_elements
		for (var i = 0; i < dummy_menu_elements_length; i++)
			dummy_menu_elements.push(dummy_menu_obj.getMenuElement(i));

		Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order

		for (var i = 0; i < dummy_menu_elements.length; i++)
			if (dummy_menu_elements[i].getIsHovered()) {
				console.log("Hovering! Touch: " + Touch.buttonTouch);
			}
	}

	function initialiseUI () {
		global.dummy_menu_obj = createDummyMenu(); //Temp synchronisation
	}
}