//Declare imports
{
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
}

//Initialise functions
{
	/**
	 * declareEventHandler() - Declares an event handler and runs all functions recursively on global[<key>]; flattened.
	 * @param {String} arg0_key - The key to which the event handler is bound.
	 * @param {Object} [arg1_options]
	 * @param {Function} [arg1_options.conditional_function] - The conditional function that is executed to check whether to invoke all functions in the event handler. If it does not return undefined, fires event.
	 * @param {number} [arg1_options.interval=100] - The interval at which checks are done.
	 */
	function declareEventHandler (arg0_key, arg1_options) {
		//Convert from parameters
		var key = arg0_key;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.interval) options.interval = 100;

		//Declare global event handler namespace
		global[key] = (global[key]) ? global[key] : {};

		//options.conditional_function handler on setInterval(..., options.interval).
		if (options.conditional_function)
			global[key + "_logic_loop"] = setInterval(function(){
				var boolean_check = options.conditional_function();

				if (boolean_check) {
					var all_event_handler_keys = Object.keys(global[key]);

					for (var i = 0; i < all_event_handler_keys.length; i++) {
						var local_value = global[key][all_event_handler_keys[i]];

						if (typeof local_value == "function")
							local_value(boolean_check);
					}
				}
			}, options.interval);
	}

	/**
	 * initialiseEventHandlers() - Initialises all active event handlers wherever needed.
	 */
	function initialiseEventHandlers () {
		//Interactions
		declareEventHandler("onmousedown", {
			conditional_function: function () {
				//Declare local instance variables
				var e;

				if (global.left_mouse_click) e = "left_click";
				if (global.right_mouse_click) e = "right_click";

				delete global.left_mouse_click;
				delete global.right_mouse_click;

				//Return statement
				return e;
			},
			interval: 8 //120FPS
		});
		declareEventHandler("onclick", {
			conditional_function: function () {
				//Declare local instance variables
				var e;

				if (global.left_mouse_release) e = "left_click";
				if (global.right_mouse_release) e = "right_click";

				delete global.left_mouse_release;
				delete global.right_mouse_release;

				//Return statement
				return e;
			},
			interval: 8
		});

		//Page handler
		declareEventHandler("on_page_change", {
			conditional_function: function () {
				//Declare local instance variables
				var current_page = getCurrentPage();
				var page_change = false;

				//Check if global.cache.previous_page was different
				if (global.cache.previous_page != current_page)
					page_change = true;
				global.cache.previous_page = current_page;

				//Return statement
				if (page_change)
					return current_page;
			}
		});
	}

	/**
	 * invokeEventHandler() - Manually invokes the functions associated with a particular event handler.
	 * @param {String} arg0_key - The key to which the event handler is bound.
	 * @param {Object} [arg1_options] - The options to pass to the functions associated with that event handler.
	 */
	function invokeEventHandler (arg0_key, arg1_options) {
		//Convert from parameters
		var key = arg0_key;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var event_handler_obj = global[key];

		var all_event_handler_keys = Object.keys(event_handler_obj);

		//Iterate over all_event_handler_keys
		for (var i = 0; i < all_event_handler_keys.length; i++) {
			var local_value = event_handler_obj[all_event_handler_keys[i]];

			if (typeof local_value == "function")
				local_value(options);
		}
	}
}