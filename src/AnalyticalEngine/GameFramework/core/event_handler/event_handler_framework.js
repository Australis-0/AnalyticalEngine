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
		if (options.interval == undefined) options.interval = 100;

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
	 * initialisePostLoadingEventHandlers() - Initialises event handlers after the game is loaded.
	 */
	function initialisePostLoadingEventHandlers () {
		//Date handler
		declareEventHandler("on_date_change", {
			conditional_function: function () {
				//Declare local instance variables
				var current_date_obj = getCurrentDate();

				var current_date = JSON.stringify(current_date_obj);

				if (current_date)
					try {
						var date_change = false;

						if (!global.cache.current_date) global.cache.current_date = current_date;
						if (global.cache.current_date != current_date) {
							var old_cache_date = JSON.parse(global.cache.current_date);
							date_change = true;
						}
						global.cache.current_date = current_date;

						//Return statement
						if (date_change)
							return {
								old_date_obj: old_cache_date,
								new_date_obj: current_date_obj
							};
					} catch (e) {
						console.error(e.message);
					}
			},
			interval: 1
		});
		//Map handler
		declareEventHandler("on_civilisation_click", {
			conditional_function: function () {
				//Declare local instance variables
				var current_civ_tag = getCurrentTag(InGame_Civ.iActiveCivID);

				if (current_civ_tag) {
					var civ_tag_change = false;
					var is_diplomacy_menu_open = isDiplomacyMenuOpen();

					if (global.cache.is_diplomacy_menu_open != is_diplomacy_menu_open)
						if (is_diplomacy_menu_open)
							civ_tag_change = true;
					if (global.cache.selected_civ_tag != current_civ_tag)
						civ_tag_change = true;
					global.cache.is_diplomacy_menu_open = is_diplomacy_menu_open;
					global.cache.selected_civ_tag = current_civ_tag;

					//Return statement
					if (civ_tag_change)
						return current_civ_tag;
				}
			}
		})
		declareEventHandler("on_mapmode_change", {
			conditional_function: function () {
				//Declare local instance variables
				var current_base_mapmode = getCurrentBaseMapmode();
				var current_mapmode = getCurrentMapmode();

				if (current_mapmode) {
					var base_mapmode_change = false;
					var mapmode_change = false;

					if (global.cache.current_base_mapmode != current_base_mapmode)
						base_mapmode_change = true;
					global.cache.current_base_mapmode = current_base_mapmode;
					if (global.cache.current_mapmode != current_mapmode)
						mapmode_change = true;
					global.cache.current_mapmode = current_mapmode;

					//Return statement
					if (base_mapmode_change)
						return current_base_mapmode;
					if (mapmode_change)
						return current_mapmode;
				}
			}
		});
		declareEventHandler("on_province_click", {
			conditional_function: function () {
				//Declare local instance variables
				var current_province = Game.iActiveProvince;

				if (current_province >= 0) {
					var province_change = false;

					if (global.cache.selected_province_id != current_province)
						province_change = true;
					//Disable right-click interactions when diplomacy menu is already open
					global.cache.selected_province_id = current_province;

					//Return statement
					if (province_change)
						return current_province;
				}
			}
		});
		declareEventHandler("on_province_rename", {
			conditional_function: function () {
				//Declare local instance variables
				var current_province = Game.iActiveProvince;

				if (current_province >= 0) try {
					var current_province_obj = getSelectedProvince();
					var province_is_undefined = false;
					var province_name_change = false;

					if (!global.cache.selected_province_name) {
						global.cache.selected_province_name = [current_province, current_province_obj.getProvinceName()];
						console.log("Attempting to define global.cache.selected_province_name!");
						province_is_undefined = true;
					}
					if (!province_is_undefined) {
						if (
							global.cache.selected_province_name[0] == current_province &&
							global.cache.selected_province_name[1] != current_province_obj.getProvinceName()
						)
							province_name_change = true;
						var province_old_name = global.cache.selected_province_name[1];
						global.cache.selected_province_name = [current_province, current_province_obj.getProvinceName()];

						//Return statement
						if (province_name_change)
							return {
								province_id: current_province,

								old_name: province_old_name,
								new_name: global.cache.selected_province_name[1]
							};
					}
				} catch (e) {
					console.log("Error in on_province_rename:");
					console.log(e.stack);
				}
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