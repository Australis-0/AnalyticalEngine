//Import classes
{
	this.BufferedReader = Java.type("java.io.BufferedReader");
	this.BufferedWriter = Java.type("java.io.BufferedWriter");
	this.File = Java.type("java.io.File");
	this.FileReader = Java.type("java.io.FileReader");
	this.FileWriter = Java.type("java.io.FileWriter");
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
	this.ProvinceDraw = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceDraw");
	this.StringBuilder = Java.type("java.lang.StringBuilder");
}

//Initialise functions
{
	/**
	 * clearMap() - Clears the current main.mapmode.provinces Object such that it can be reused by new custom mapmodes.
	 * @param {boolean} [arg0_do_not_clear_mapmode=true] - Whether to clear the mapmode.
	 */
	function clearMap (arg0_do_not_clear_mapmode) {
		//Convert from parameters
		var do_not_clear_mapmode = (arg0_do_not_clear_mapmode != undefined) ?
			arg0_do_not_clear_mapmode : true;

		//Clear provinces and current mapmode
		main.mapmodes.province_colours = {};
		if (!do_not_clear_mapmode)
			clearMapmode();
	}

	/**
	 * clearMapmode() - Clears the current mapmode currently being rendered in-game, including custom mapmodes, and restores it to its effective default.
	 */
	function clearMapmode () {
		//Declare local instance variable
		var current_page = getCurrentPage();

		//Switch mapmode
		switchMapmode("MODE_DEFAULT_TERRAIN");
		ProvinceDraw.updateDrawProvinces_Standard();
		Game.mapModes.updateViews();

		main.mapmodes.province_colours = {};
		delete main.mapmodes.custom_mapmode;
	}

	/**
	 * getAllMapmodes() - Returns all base-game mapmodes as an Array<String>.
	 *
	 * @returns {Array<String>}
	 */
	function getAllMapmodes () {
		//Return statement
		return ["MODE_DEFAULT", "MODE_DEFAULT_TERRAIN", "MODE_POPULATION", "MODE_ECONOMY", "MODE_PROVINCE_INCOM", "MODE_PROVINCE_INCOME_HOVER", "MODE_PROVINCE_EXPENSES_HOVER", "MODE_CIV_POPULATION_HOVER", "MODE_CIV_ECONOMY_HOVER", "MODE_PROVINCE_MANPOWER_HOVER_PLAYER", "MODE_PROVINCE_GROWTH_RATE_HOVER", "MODE_PROVINCE_MANPOWER_HOVER", "MODE_PROVINCE_ECONOMY_HOVER", "MODE_PROVINCE_TAX_EFFICIENCY_HOVER", "MODE_PROVINCE_POPULATION_HOVER", "MODE_PROVINCE_RELIGION_HOVER", "MODE_PROVINCE_FORTS_HOVER", "MODE_PROVINCE_INCOME_HOVER", "MODE_PROVINCE_INFRASTRUCTURE_HOVER", "MODE_PROVINCE_LOOT_HOVER", "MODE_PROVINCE_PROVINCE_VALUE_HOVER", "MODE_PROVINCE_DEVASTATION_HOVER", "MODE_PROVINCE_UNREST_HOVER", "MODE_PROVINCE_TAX", "MODE_TERRAIN", "MODE_GOODS", "MODE_INFRASTRUCTURE", "MODE_WONDERS", "MODE_RELIGION", "MODE_GOVERNMENT", "MODE_DEVASTATION", "MODE_LOOT", "MODE_DEFENSE_LEVEL", "MODE_UNREST", "MODE_DISEASES", "MODE_WARS", "MODE_ALLIANCES", "MODE_DEFENSIVE_PACTS", "MODE_NON_AGGRESSION_PACTS", "MODE_RECRUIT_ARMY", "MODE_NEW_ARMY_CHOOSE_PROVINCE", "MODE_NUKE_CHOOSE_PROVINCE", "MODE_MERCENARIES_CHOOSE_PROVINCE", "MODE_INVEST_IN_ECONOMY", "MODE_INCREASE_TAX_EFFICIENCY", "MODE_INCREASE_MANPOWER", "MODE_MOVE_CAPITAL", "MODE_COLONIZE_CHOOSE_PROVINCE", "MODE_DEVELOP_INFRASTRUCTURE", "MODE_INCREASE_GROWTH_RATE", "MODE_CIV_STABILITY_HOVER", "MODE_BUILDING", "MODE_CONVERT_RELIGION", "MODE_CORE", "MODE_DIPLOMACY", "MODE_DIPLOMACY_IMPROVE_RELATIONS", "MODE_DIPLOMACY_DAMAGE_RELATIONS", "MODE_DECLARE_WAR", "MODE_FORM_CIV", "MODE_SPECIAL_ALLIANCE_VIEW", "MODE_WAR_VIEW", "MODE_PEACE_VIEW", "MODE_RELEASE_VASSAL"];
	}

	/**
	 * getCurrentBaseMapmode() - Returns the current base mapmode as a string.
	 *
	 * @returns {String}
	 */
	function getCurrentBaseMapmode() {
		//Return statement
		return getCurrentMapmode({ base_mapmode: true });
	}

	/**
	 * getCurrentMapmode() - Returns the current mapmode as a string.
	 * @param {Object} [arg0_options]
	 * @param {boolean} [arg0_options.base_mapmode=false] - Whether to return the base mapmode and ignore custom mapmodes.
	 *
	 * @returns {String}
	 */
	function getCurrentMapmode (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Return statement
		if (!options.base_mapmode)
			if (main.mapmodes.custom_mapmode)
				return main.mapmodes.custom_mapmode;

		//Iterate over main.mapmodes.all_mapmodes if custom mapmode is not active
		for (var i = 0; i < main.mapmodes.all_mapmodes.length; i++)
			if (Game.mapModes[main.mapmodes.all_mapmodes[i]])
				if (Game.mapModes.iActiveMapModeID == Game.mapModes[main.mapmodes.all_mapmodes[i]])
					//Return statement
					return main.mapmodes.all_mapmodes[i];

		//Return statement
		return "MODE_DEFAULT_TERRAIN";
	}

	/**
	 * getMapmodeID() - Returns the current base in-game ID of the present base mapmode.
	 * @param {String} arg0_mapmode - The mapmode to fetch the ID of.
	 *
	 * @returns {number}
	 */
	function getMapmodeID (arg0_mapmode) {
		//Convert from parameters
		var mapmode = arg0_mapmode;

		//Return statement
		return Game.mapModes[mapmode];
	}

	/**
	 * loadCustomMapmode() - Loads a custom mapmode into the current registry.
	 * @param {Object} arg0_custom_mapmode_obj - The custom mapmode object to load.
	 * @param {Object} [arg1_options]
	 * @param {Object} [arg1_options.live_update=false] - Optional. Whether the change being induced is a live_update.
	 * @param {Array<Object>} [arg1_options.all_provinces] - Optional. Optimisation parameter.
	 */
	function loadCustomMapmode (arg0_custom_mapmode_obj, arg1_options) {
		//Convert from parameters
		var custom_mapmode_obj = arg0_custom_mapmode_obj;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.all_provinces) options.all_provinces = getAllProvinces();

		//Render custom_mapmode_obj only if it exists
		if (custom_mapmode_obj) {
			//.separate_mapmode handling
			if (custom_mapmode_obj.separate_mapmode && !options.live_update)
				main.mapmodes.province_colours = {};
			//.special_function handling
			if (custom_mapmode_obj.special_function)
				//Iterate over all provinces
				for (var i = 0 ; i < options.all_provinces.length; i++) {
					var local_colour = custom_mapmode_obj.special_function(options.all_provinces[i]);
					var local_province_colour = main.mapmodes.province_colours[local_province_id];
					var local_province_id = options.all_provinces[i].getProvinceID();

					if (local_colour != undefined) {
						main.mapmodes.province_colours[local_province_id] = local_colour;
					} else {
						if (custom_mapmode_obj.separate_mapmode)
							main.mapmodes.province_colours[local_province_id] = [0, 0, 0, 0];
					}
				}

			//KEEP AT BOTTOM! .overlays handling
			if (custom_mapmode_obj.overlays) {
				var overlays = getList(custom_mapmode_obj.overlays);

				for (var i = 0; i < overlays.length; i++) {
					var new_options = { all_provinces: options.all_provinces };

					//Recursively call loadCustomMapmode() for sub-overlays
					loadCustomMapmode(config.mapmodes[overlays[i]], new_options);
				}
			}
		} else {
			console.error("map_framework.js: " + overlays[i] + " is not defined as a valid mapmode in config.mapmodes!");
		}
	}

	function panToProvince (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var province_obj = getProvince(province_id);

		//Pan to province
		if (province_obj)
			Game.mapCoords.centerToProvinceID(province_obj.getProvinceID());
	}

	/**
	 * switchMapmode() - Switches between the current mapmode and another one. Accepts custom mapmodes.
	 * @param {String} arg0_mapmode - The mapmode to switch to.
	 */
	function switchMapmode (arg0_mapmode) {
		//Convert from parameters
		var mapmode = arg0_mapmode;

		//Declare local instance variables
		var mapmode_id = getMapmodeID(mapmode);

		//Custom mapmode handler
		if (getCurrentMapmode() != mapmode)
			if (mapmode_id != undefined) {
				delete main.mapmodes.custom_mapmode;
				Game.mapModes.setActiveViewID(mapmode_id);
			} else {
				clearMapmode();
				main.mapmodes.custom_mapmode = mapmode;
			}
	}
}