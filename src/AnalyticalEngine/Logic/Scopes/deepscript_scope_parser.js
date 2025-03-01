//Initialise functions
{
	/**
	 * addEvent() - [WIP] - Documentation.
	 * @param {Object} [arg0_options]
	 *  @param {String} [arg0_options.id=generateRandomID(main.scopes.events)] - The ID of the event.
	 *  @param {String} [arg0_options.image] - The filepath to the event image to display.
	 *  @param {String} [arg0_options.type] - Either 'civ_event'/'mission_event'/'province_event'.
	 *
	 *  @param {String} [arg0_options.name=""] - The name of the event.
	 *  @param {String} [arg0_options.description=""] - The description for the event to display.
	 *
	 *  @param {boolean} [arg0_options.display_in_missions=false]
	 *  @param {number} [arg0_options.mission_id=undefined] - If defined, this will display in missions unless told not to explicitly.
	 *  @param {number} [arg0_options.only_once=false] - Whether to fire the event only once.
	 *
	 *  @param {Function} [arg0_options.effect]
	 *  @param {Function} [arg0_options.immediate]
	 *  @param {Function} [arg0_options.limit] - Event triggers if this function returns true.
	 *  @param {Function} [arg0_options.trigger] - Event triggers if this function returns true. Alias for limit.
	 *
	 *  @param {Object} [arg0_options."btn_<key>"]
	 *   @param {String} [arg0_options."btn_<key>".name]
	 *   @param {String} [arg0_options."<btn_key>".description]
	 *
	 *   @param {number|Function} [arg0_options."<btn_key>".ai_chance=1] - Proportional to other options by default.
	 *   @param {boolean} [arg0_options."<btn_key>".disabled=false] - Whether the button is disabled for clicking. Useful for tooltips.
	 *   @param {Function} [arg0_options."<btn_key>".effect]
	 *   @param {Function} [arg0_options."<btn_key>".limit] - Whether the option displays to begin with.
	 */
	function addEvent (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.id) options.id = generateRandomID(main.scopes.events);
		if (!options.type) options.type = "civ_event";

		if (options.name == undefined) options.name = "Event Name";
		if (options.description == undefined) options.description = "";

		if (options.trigger) options.limit = options.trigger;

		//Add options to main.scopes.events
		main.scopes.events[options.id] = options;
	}

	function allCivilisationsScope (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		var all_civilisations = getAllCivilisations();

		//Iterate over all_civilisations
		for (var i = 0; i < all_civilisations.length; i++)
			local_function(all_civilisations[i]);
	}

	function allProvincesScope (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		var all_provinces = getAllProvinces();

		//Iterate over all_provinces
		for (var i = 0; i < all_provinces.length; i++)
			local_function(all_provinces[i]);
	}

	function anyCivilisationScope (arg0_limit_function, arg1_function) {
		//Convert from parameters
		var limit_function = arg0_limit_function;
		var local_function = arg1_function;

		//Declare local instance variables
		var all_civilisations = getAllCivilisations();

		//Iterate over all_civilisations
		for (var i = 0; i < all_civilisations.length; i++)
			if (limit_function(all_civilisations[i]))
				local_function(all_civilisations[i]);
	}

	function anyProvinceScope (arg0_limit_function, arg1_function) {
		//Convert from parameters
		var limit_function = arg0_limit_function;
		var local_function = arg1_function;

		//Declare local instance variables
		var all_provinces = getAllProvinces();

		//Iterate over all_provinces
		for (var i = 0; i < all_provinces.length; i++)
			if (limit_function(all_provinces[i]))
				local_function(all_provinces[i]);
	}

	function civilisationScope (arg0_civ_tags, arg1_function) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var local_function = arg1_function;

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			local_function(getCivilisation(civ_tags[i]));
	}

	function globalScope (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Immediately invoke function
		local_function();
	}

	function onGameStart (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Add local_function to main.scopes.on_game_start
		main.scopes.on_game_start[generateRandomID(main.scopes.on_game_start)] = local_function;
	}

	function onGameStartup (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Add local_function to main.scopes.on_game_startup
		main.scopes.on_game_startup[generateRandomID(main.scopes.on_game_startup)] = local_function;
	}

	function provinceScope (arg0_provinces, arg1_function) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var local_function = arg1_function;

		//Iterate over all provinces
		for (var i = 0; i < provinces.length; i++)
			local_function(getProvince(provinces[i]));
	}
}