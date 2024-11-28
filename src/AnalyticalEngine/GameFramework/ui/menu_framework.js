//Import classes
{
	this.AA_Game = Java.type("aoc.kingdoms.lukasz.jakowski.AA_Game");
	this.ArrayList = Java.type("java.util.ArrayList");
	this.ButtonMain = Java.type("aoc.kingdoms.lukasz.menu_element.button.ButtonMain");
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
	this.DesktopLauncher = Java.type("aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher");
	this.Float = Java.type("java.lang.Float");
	this.Integer = Java.type("java.lang.Integer");
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
	 * @param {boolean} [arg0_options.raw_width=false] - Whether the width is not multiplied by CFG.BUTTON_WIDTH.
	 * @param {number} [arg0_options.width=2] - The width as multiplied by CFG.BUTTON_WIDTH.
	 * @param {number} [arg0_options.x=0]
	 * @param {number} [arg0_options.y=0]
	 *
	 * @param {String} [arg0_options.align="centre"] - Optional. Either 'left', 'centre', or 'right'.
	 * @param {boolean} [arg0_options.clickable=true]
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
		options.clickable = (options.clickable != false) ? true : false;
		options.name = (options.name) ? options.name : "";
		options.width = returnSafeNumber(options.width, 2);
		options.x = returnSafeNumber(options.x);
		options.y = returnSafeNumber(options.y);

		//Declare local instance variables
		var actual_width = parseInt((!options.raw_width) ?
			CFG.BUTTON_WIDTH*options.width : options.width);

		var actual_text_position = -1;
			//Adjust actual_text_position
			if (options.align == "left")
				actual_text_position = 25;
			if (options.align == "right") {
				//Create phantom dummy_button_obj to calculate displayed text width from
				var dummy_button_obj = new ButtonMain(
					null, //(sText) Initial string
					0, //(fontID)
					actual_text_position, //(itextPositionX)
					parseInt(options.x), //(iPosX)
					parseInt(options.y), //(iPosY)
					actual_width, //(nWidth)
					options.clickable //(isClickable)
				);
				dummy_button_obj.setText(options.name);
				var dummy_button_width = dummy_button_obj.getTextWidth();

				actual_text_position = actual_width - 25 - dummy_button_width;
			}

		//Construct new_button_obj
		var new_button_obj = new ButtonMain(
			null, //(sText) Initial string
			0, //(fontID)
			actual_text_position, //(itextPositionX)
			parseInt(options.x), //(iPosX)
			parseInt(options.y), //(iPosY)
			actual_width, //(nWidth)
			options.clickable //(isClickable)
		);

		//Post-process handling
		//options.name
		new_button_obj.setText(options.name);

		//Return statement
		return new_button_obj;
	}

	/**
	 * createContextMenu() - Creates a new context menu to add for the current in-game screen.
	 * @param [arg0_options]
	 * @param {String} [arg0_options.anchor="top_left"] - Either 'bottom_left', 'bottom_right', 'top_left', or 'top_right'.
	 * @param {boolean} [arg0_options.can_close=true]
	 * @param {boolean} [arg0_options.draggable=true]
	 * @param {boolean} [arg0_options.has_back_button=false]
	 * @param {boolean} [arg0_options.lock_hover=false]
	 * @param {String} [arg0_options.name] - If undefined, the MenuTitle is null instead.
	 * @param {boolean} [arg0_options.no_title=true] - Whether there is a title or not.
	 * @param {boolean} [arg0_options.resizeable=true]
	 *
	 * @param {number} [arg0_options.height]
	 * @param {number} [arg0_options.width]
	 * @param {number} [arg0_options.x]
	 * @param {number} [arg0_options.y]
	 *
	 * @param {Object} [arg0_options."input_key"]
	 *  @param {String} [arg0_options."input_key".name]
	 * 	@param {String} [arg0_options."input_key".type="button"]
	 * 	@param {number} [arg0_options."input_key".raw_width=false] - Whether to override default multiplication for .width.
	 * 	@param {number} [arg0_options."input_key".width=2] - The width as multiplied by CFG.BUTTON_WIDTH.
	 * 	@param {number} [arg0_options."input_key".x]
	 * 	@param {number} [arg0_options."input_key".y]
	 *
	 *  @param {String} [arg0_options."input_key".align="centre"] - Optional. Either 'left', 'centre', or 'right'.
	 *  @param {boolean} [arg0_options.clickable=true]
	 *  @param {Function} [arg0_options."input_key".special_function]
	 *  @param {String} [arg0_options."input_key".tooltip]
	 */
	//[WIP] - Finish function body
	function createContextMenu (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.anchor) options.anchor = "top_left";
		if (options.can_close != false) options.can_close = true;
		if (options.draggable != false) options.draggable = true;
		if (options.has_back_button != true) options.has_back_button = false;
		if (options.lock_hover != true) options.lock_hover = false;
		if (options.no_title != false) options.no_title = true;
		if (!options.name) options.name = "";
		if (options.resizeable != false) options.resizeable = true;

		options.height = (options.height) ?
			returnSafeNumber(options.height, 100) : CFG.GAME_HEIGHT;
		options.width = (options.width) ?
			returnSafeNumber(options.width, 100) : CFG.GAME_WIDTH;
		options.x = returnSafeNumber(options.x);
		options.y = returnSafeNumber(options.y);

		//Declare local instance variables
		var menu_obj = new Menu();

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
		var interface_obj = initInterface();
		var menu_elements_array_list = new ArrayList();
		var menu_elements = [];
		var onclick = [];

		//Construct MenuTitle
		var menu_title_obj = (options.no_title) ? null : createMenuTitle({
			name: options.name,
			draggable: options.draggable,
			resizeable: options.resizeable
		});

		//Construct Menu dimensions; positioning
		if (options.anchor == "top_right") {
			options.x = getJavaInteger(CFG.GAME_WIDTH) - options.x;
		} else if (options.anchor == "bottom_left") {
			options.y = getJavaInteger(CFG.GAME_HEIGHT) - options.y;
		} else if (options.anchor == "bottom_right") {
			options.x = getJavaInteger(CFG.GAME_WIDTH) - options.x;
			options.y = getJavaInteger(CFG.GAME_HEIGHT) - options.y;
		}

		//Iterate over all_options_keys that are subobjects
		var all_options_keys = Object.keys(options);

		for (var i = 0; i < all_options_keys.length; i++) {
			var local_value = options[all_options_keys[i]];

			if (typeof local_value == "object" && local_value.type) {
				var new_menu_element_obj;

				//Parse type
				if (local_value.type == "button") {
					new_menu_element_obj = createButton(local_value);
				}

				if (new_menu_element_obj) {
					menu_elements.push(new_menu_element_obj);
					onclick.push((local_value.special_function) ? local_value.special_function : undefined);
				}
			}
		}

		//Set interface_obj
		interface_obj.menu_elements = menu_elements;
		interface_obj.onclick = onclick;

		//Initialise menu and add to view
		for (var i = 0; i < interface_obj.menu_elements.length; i++)
			menu_elements_array_list.add(interface_obj.menu_elements[i]);

		//console.log("Invoking init_menu_method with parameters: ", menu_obj + " ", menu_title_obj + " ", options.x + " ", options.y + " ", options.width + " ", options.height + " ", menu_elements_array_list + " ", true + " ", false + " ", true + " ", false + " ");
		init_menu_method.invoke(
			menu_obj,
			menu_title_obj,
			setJavaInteger(options.x),
			setJavaInteger(options.y),
			setJavaInteger(options.width),
			setJavaInteger(options.height),
			menu_elements_array_list,
			true,
			false,
			true,
			false);
		Game.menuManager.addNextMenuToView(current_view_id, menu_obj);
		Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order

		//KEEP AT BOTTOM! Add trackers to main.interfaces[<interface_id>]
		interface_obj.menu_obj = menu_obj;

		//Return statement
		return interface_obj;
	}

	/**
	 * createDummyMenu() - Creates a dummy testing menu.
	 * @param [arg0_options]
	 * @param [arg0_options.name="Test Window"]
	 *
	 * @returns {Object}
	 */
	function createDummyMenu (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Intialise options
		if (!options.name) options.name = "Test Window";

		//Dummy code
		var dummy_interface_obj = createContextMenu({
			name: options.name,
			no_title: false,
			height: 200,
			width: 400,
			x: 500,
			y: 400,

			left_align_button: {
				type: "button",
				name: "Left-aligned button",
				raw_width: true,
				width: 400,

				align: "left",
				special_function: function () {
					console.log("Left-aligned dummy menu test button reporting for duty!");
				}
			},
			centre_align_button: {
				type: "button",
				name: "Centre-aligned button",
				raw_width: true,
				width: 400,
				y: CFG.BUTTON_HEIGHT,

				align: "centre",
				special_function: function () {
					console.log("Centre-aligned dummy menu test button reporting for duty!");
				}
			},
			right_align_button: {
				type: "button",
				name: "Right-aligned button",
				raw_width: true,
				width: 400,
				y: CFG.BUTTON_HEIGHT*2,

				align: "right",
				special_function: function () {
					console.log("Right-aligned dummy menu test button reporting for duty!");
				}
			},
		});

		//Return statement
		return dummy_interface_obj.menu_obj;
	}

	/**
	 * initInterface() - Creates a new interface at main.interfaces.
	 * @param {number} [arg0_interface_id] - The interface ID to create this in. Random untaken number by default.
	 *
	 * @returns {Object}
	 */
	//[WIP] - Finish function body
	function initInterface (arg0_interface_id) {
		//Convert from parameters
		var interface_id = (arg0_interface_id) ? arg0_interface_id : generateRandomID(main.interfaces);

		//Declare local instance variables
		main.interfaces[interface_id] = { id: interface_id };
		var interface_obj = main.interfaces[interface_id];

		interface_obj.menu_elements = []; //Stores all elements added to the current Menu (except MenuTitle)
		interface_obj.onclick = []; //Handles onclick elements (except MenuTitle)

		//Return statement
		return interface_obj;
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
	function createMenuTitle (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.name) options.name = "";
		if (options.draggable != false) options.draggable = true;
		if (!options.font_size) options.font_size = (options.font_size) ?
			new Float(options.font_size) : new Float(1.0);
		if (!options.height) options.height = (options.height) ?
			returnSafeNumber(options.height) : CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT/2;
		if (options.resizeable != false) options.resizeable = true;

		//Declare local instance variables
		var new_menu_title_obj = new MenuTitle(
			"", //(sText)
			options.font_size, //(fontScale)
			options.height, //(titleHeight)
			options.draggable, //(moveable)
			options.resizeable //(resizable)
		);
		new_menu_title_obj.setText(options.name);

		//Return statement
		return new_menu_title_obj;
	}

	function menuHandler () {
		//Declare local instance variables
		var all_interface_keys = Object.keys(main.interfaces);
		var current_view_id = Game.menuManager.viewID;

		//Iterate over all_interface_keys
		for (var i = 0; i < all_interface_keys.length; i++) {
			var local_interface = main.interfaces[all_interface_keys[i]];
			var local_menu_obj = local_interface.menu_obj;

			if (local_menu_obj)
				//Handle local_interface.menu_elements
				for (var x = 0; x < local_interface.menu_elements.length; x++) {
					var local_element = local_interface.menu_elements[x];

					if (local_element.getIsHovered() && global.left_mouse_release)
						//console.log("Clicked button: " + local_element.getText());
						if (local_interface.onclick[x])
							local_interface.onclick[x]();
				}
		}

		//Draw current menus
		Game.menuManager.update();
		Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order
	}

	function initialiseUI () {
		//global.dummy_menu_obj = createDummyMenu(); //Temp synchronisation
	}
}