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
	 * @param {boolean} [arg0_options.raw_coords=false] - Whether to skip auto-formatting and manually supply all coords. For use in non-vertical menus.
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
	 * 	@param {number} [arg0_options."input_key".raw_coords=false] - Whether to use raw specified coords instead of autoformatting.
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
		if (options.raw_coords != true) options.raw_coords = false;
		if (options.resizeable != false) options.resizeable = true;

		options.height = (options.height) ?
			returnSafeNumber(options.height, 100) : CFG.GAME_HEIGHT;
		options.width = (options.width) ?
			returnSafeNumber(options.width, 100) : CFG.GAME_WIDTH;
		options.x = returnSafeNumber(options.x);
		options.y = returnSafeNumber(options.y);

		//Declare local instance variables
		var all_options_keys = Object.keys(options);
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
		var menu_properties = [];

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

		//1. Iterate over all_options_keys that are subobjects; fix their .x/.y coordinates if unspecified
		var column_one_current_rows = getRowsInColumn(options, 0);

		for (var i = 0; i < all_options_keys.length; i++) {
			var local_value = options[all_options_keys[i]];

			if (typeof local_value == "object" && local_value.type) {
				if (local_value.x == undefined)
					local_value.x = 0;
				if (local_value.y == undefined) {
					local_value.y = JSON.parse(JSON.stringify(column_one_current_rows));
					column_one_current_rows++;
				}
			}
		}

		//2. Iterate over all_options_keys that are subobjects; add them to the menu
		for (var i = 0; i < all_options_keys.length; i++) {
			var local_value = options[all_options_keys[i]];

			if (typeof local_value == "object" && local_value.type) {
				var new_menu_element_obj;
				var new_properties_obj = {};

				//Parse type
				if (local_value.type == "button") {
					new_menu_element_obj = createButton(local_value);
				}

				if (new_menu_element_obj) {
					menu_elements.push(new_menu_element_obj);

					//Push new_properties_obj
					new_properties_obj.x = local_value.x;
					new_properties_obj.y = local_value.y;

					if (local_value.special_function)
						new_properties_obj.onclick = local_value.special_function;
					menu_properties.push(new_properties_obj);
				}
			}
		}

		//3. Set .interface_obj
		interface_obj.menu_elements = menu_elements;
		interface_obj.menu_properties = menu_properties;

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

		//Add trackers to main.interfaces[<interface_id>]
		interface_obj.menu_obj = menu_obj;

		//3. Autoformat interface_obj.menu_obj with table layout
		if (!options.raw_coords) {
			var current_x_width = 0;
			var current_y_height = 0;
			var menu_dimensions = getContextMenuDimensions(options);
			//console.log("Menu dimensions: " + menu_dimensions);

			//Set .x
			for (var i = 0; i < menu_dimensions[0] + 1; i++) {
				for (var x = 0; x < interface_obj.menu_properties.length; x++)
					if (!interface_obj.menu_properties[x].raw_coords)
						if (interface_obj.menu_properties[x].x == i)
							interface_obj.menu_elements[x].setPosX(current_x_width);

				current_x_width += getMaxColumnWidth(interface_obj, i);
			}

			//Set .y
			//Iterate over all_options_keys and set local y height per row
			for (var i = 0; i < menu_dimensions[1] + 1; i++) {
				//console.log("Iterating over Y: " + i);
				for (var x = 0; x < interface_obj.menu_properties.length; x++) {
					//console.log(interface_obj.menu_properties[x]);
					if (!interface_obj.menu_properties[x].raw_coords)
						if (interface_obj.menu_properties[x].y == i) {
							interface_obj.menu_elements[x].setPosY(current_y_height);
							//console.log("Set " + ordinalise(x) + " element to Y: " + current_y_height);
						}
				}

				current_y_height += getMaxRowHeight(interface_obj, i);
			}
		}

		//KEEP AT BOTTOM! Update Game menu draw so that UI appears on screen
		Game.menuManager.addNextMenuToView(current_view_id, menu_obj);
		Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order

		menu_obj.setWidth_Resize(options.width); //Update scrollable
		menu_obj.updateScrollable();

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
	 * getColumnsInRow() - Fetches the total number of columns in a row.
	 * @param {Object} arg0_context_menu_obj - The context menu to input.
	 * @param {number} arg1_y - The row to target.
	 *
	 * @returns {number}
	 */
	function getColumnsInRow (arg0_context_menu_obj, arg1_y) {
		//Convert from parameters
		var context_menu_obj = arg0_context_menu_obj;
		var row = parseInt(arg1_y);

		//Declare local instance variables
		var all_context_menu_keys = Object.keys(context_menu_obj);
		var max_column = 0;

		//Iterate over all_context_menu_keys
		for (var i = 0; i < all_context_menu_keys.length; i++) {
			var local_value = context_menu_obj[all_context_menu_keys[i]];

			if (local_value.y == row)
				max_column = Math.max(max_row, local_value.x + 1);
		}

		//Return statement
		return max_column;
	}

	/**
	 * getContextMenuDimensions() - Returns the [x, y] dimensions of the current context menu.
	 * @param {Object} arg0_context_menu_obj - The context menu to input.
	 *
	 * @returns {Array<number, number>}
	 */
	function getContextMenuDimensions (arg0_context_menu_obj) {
		//Convert from parameters
		var context_menu_obj = arg0_context_menu_obj;

		//Declare local instance variables
		var all_context_menu_keys = Object.keys(context_menu_obj);
		var max_x = 0;
		var max_y = 0;

		//Iterate over all_context_menu_keys
		for (var i = 0; i < all_context_menu_keys.length; i++) {
			var local_value = context_menu_obj[all_context_menu_keys[i]];

			if (typeof local_value == "object") {
				if (local_value.x) max_x = Math.max(max_x, local_value.x);
				if (local_value.y) max_y = Math.max(max_y, local_value.y);
			}
		}

		//Return statement
		return [max_x, max_y];
	}

	/**
	 * getMaxColumnWidth() - Fetches the maximal width of a given column needed for rendering.
	 * @param {Object} arg0_interface_obj - The interface object to input.
	 * @param {number} arg1_x - The column to target.
	 *
	 * @returns {number}
	 */
	function getMaxColumnWidth (arg0_interface_obj, arg1_x) {
		//Convert from parameters
		var interface_obj = arg0_interface_obj;
		var x_column = parseInt(arg1_x);

		//Declare local instance variables
		var max_column_width = 0;

		//Iterate over interface_obj.menu_properties
		if (interface_obj)
			if (interface_obj.menu_properties)
				for (var i = 0; i < interface_obj.menu_properties.length; i++)
					if (interface_obj.menu_properties[i].x == x_column)
						max_column_width = Math.max(max_column_width, interface_obj.menu_elements[i].getWidth());

		//Return statement
		return max_column_width;
	}

	/**
	 * getMaxRowHeight() - Fetches the maximal height of a given row needed for rendering.
	 * @param {Object} arg0_interface_obj - The interface object to input.
	 * @param {number} arg1_y - The row to target.
	 *
	 * @returns {number}
	 */
	function getMaxRowHeight (arg0_interface_obj, arg1_y) {
		//Convert from parameters
		var interface_obj = arg0_interface_obj;
		var y_row = parseInt(arg1_y);

		//Declare local instance variables
		var max_row_height = 0;

		//Iterate over interface_obj.menu_properties
		if (interface_obj)
			if (interface_obj.menu_properties)
				for (var i = 0; i < interface_obj.menu_properties.length; i++)
					if (interface_obj.menu_properties[i].y == y_row) {
						max_row_height = Math.max(max_row_height, interface_obj.menu_elements[i].getHeight());
					}

		//Return statement
		return max_row_height;
	}

	/**
	 * getRowsInColumn() - Fetches the total number of rows in a column.
	 * @param {Object} arg0_context_menu_obj - The context menu to input.
	 * @param {number} arg1_x - The column to target.
	 *
	 * @returns {number}
	 */
	function getRowsInColumn (arg0_context_menu_obj, arg1_x) {
		//Convert from parameters
		var context_menu_obj = arg0_context_menu_obj;
		var column = parseInt(arg1_x);

		//Declare local instance variables
		var all_context_menu_keys = Object.keys(context_menu_obj);
		var max_row = 0;

		//Iterate over all_context_menu_keys
		for (var i = 0; i < all_context_menu_keys.length; i++) {
			var local_value = context_menu_obj[all_context_menu_keys[i]];

			if (local_value.x == column)
				max_row = Math.max(max_row, local_value.y + 1);
		}

		//Return statement
		return max_row;
	}

	/**
	 * getTotalColumnHeight() - Fetches the total display column height for all elements  with no padding.
	 * @param {Object} arg0_interface_obj - The interface object to input.
	 * @param {number} arg1_x - The column to target.
	 *
	 * @returns {number}
	 */
	//[WIP] - Adjust for padding options
	function getTotalColumnHeight (arg0_interface_obj, arg1_x) {
		//Convert from parameters
		var interface_obj = arg0_interface_obj;
		var x_column = parseInt(arg1_x);

		//Declare local instance variables
		var total_column_height = 0;

		//Iterate over interface_obj.menu_properties
		if (interface_obj)
			if (interface_obj.menu_properties)
				for (var i = 0; i < interface_obj.menu_properties.length; i++)
					if (interface_obj.menu_properties[i].x == x_column)
						total_column_height += interface_obj.menu_elements[i].getHeight();

		//Return statement
		return total_column_height;
	}

	/**
	 * getTotalRowWidth() - Fetches the total display row width for all elements with no padding.
	 * @param {Object} arg0_interface_obj - The interface object to input.
	 * @param {number} arg1_y - The row to target.
	 *
	 * @returns {number}
	 */
	function getTotalRowWidth (arg0_interface_obj, arg1_y) {
		//Convert from parameters
		var interface_obj = arg0_interface_obj;
		var y_row = parseInt(arg1_y);

		//Declare local instance variables
		var total_row_width = 0;

		//Iterate over interface_obj.menu_properties
		if (interface_obj)
			if (interface_obj.menu_properties)
				for (var i = 0; i < interface_obj.menu_properties.length; i++)
					if (interface_obj.menu_properties[i].y == y_row)
						total_row_width += interface_obj.menu_elements[i].getWidth();

		//Return statement
		return total_row_width;
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

	/**
	 * menuHandler() - Handles onclick events for menus in-game.
	 */
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
					var local_properties = local_interface.menu_properties[x];

					if (local_element.getIsHovered()) {
						console.log("Clicked on button: " + local_element.getText());
						if (local_properties.onclick)
							local_properties.onclick();
					}
				}
		}

		//Draw current menus
		Game.menuManager.update();
		Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order
	}
}