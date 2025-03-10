//Initialise functions
{
	/**
	 * getAllCivilisations() - Returns an array housing all civilisations.
	 *
	 * @returns {Array<Object>}
	 */
	function getAllCivilisations () {
		//Declare local instance variables
		var all_civilisations = [];

		//Iterate over Game.getCivilizationsInGame()
		for (var i = 0; i < Game.getCivilizationsInGame(); i++)
			all_civilisations.push(getCivilisation(i));

		//Return statement
		return all_civilisations;
	}

	/**
	 * getAllCurrentCivTags() - Returns an Array<String> housing all current CivTags.
	 *
	 * @returns {Array<String>}
	 */
	function getAllCurrentCivTags () {
		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var unique_tags = [];

		//Iterate over all_provinces and push unique civ tags
		for (var i = 0; i < all_provinces.length; i++) {
			var province_owner = Game.getCiv(all_provinces[i].getCivID());
			var province_owner_tag = province_owner.getCivTag();

			if (!unique_tags.includes(province_owner_tag))
				unique_tags.push(province_owner_tag);
		}

		//Return statement
		return sortArray(unique_tags, { mode: "alphabetical" });
	}

	/**
	 * getCivilisation() - Fetches a Game Civilisation based on its ID/tag/name.
	 * @param {String|number} arg0_civ_name
	 * @param [arg1_options]
	 * @param [arg1_options.return_current_tag=false] - Whether to return the current CivTag.
	 * @param [arg1_options.return_key=false] - Whether to return the Civ ID instead.
	 * @param [arg1_options.return_tag=false] - Whether to return the base CivTag.
	 *
	 * @returns {Object<Civilisation>|number|String}
	 */
	function getCivilisation (arg0_civ_name, arg1_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_id;
		var civ_obj;

		//Guard clauses
		if (typeof civ_name == "object") return civ_name;

		//Check for CivInt; Iterate over Game.lCivs
		if (!isNaN(civ_name)) {
			for (var i = 0; i < Game.lCivs.size(); i++) {
				var local_civ = Game.lCivs.get(i);

				if (local_civ.iCivID == civ_name) {
					civ_id = local_civ.iCivID;
					civ_obj = local_civ;
				}
			}
		} else {
			//Check for tag - no ideology 1st
			for (var i = 0; i < Game.lCivs.size(); i++) {
				var local_civ = Game.lCivs.get(i);

				if (local_civ.civData.t == civ_name) {
					civ_id = local_civ.iCivID;
					civ_obj = local_civ;
				}
			}
			//Check for tag - ideology 2nd
			for (var i = 0; i < Game.lCivs.size(); i++) {
				var local_civ = Game.lCivs.get(i);

				if (local_civ.realTag == civ_name) {
					civ_id = local_civ.iCivID;
					civ_obj = local_civ;
				}
			}

			//Name search
			if (!civ_obj && typeof civ_name == "string") {
				var search_name = civ_name.trim().toLowerCase();
				//console.log("Searching for " + search_name);

				//Name - Soft search 1st
				for (var i = 0; i < Game.lCivs.size(); i++) {
					var local_civ = Game.lCivs.get(i);

					if (local_civ.sCivName.trim().toLowerCase().indexOf(search_name) != -1) {
						civ_id = local_civ.iCivID;
						civ_obj = local_civ;
					}
				}

				//Name - Hard search 2nd
				for (var i = 0; i < Game.lCivs.size(); i++) {
					var local_civ = Game.lCivs.get(i);

					if (local_civ.sCivName.trim().toLowerCase() == search_name) {
						civ_id = local_civ.iCivID;
						civ_obj = local_civ;
					}
				}
			}
		}

		//Return statement
		if (civ_obj && options.return_current_tag)
			return civ_obj.civData.t;
		if (civ_obj && options.return_tag)
			return civ_obj.realTag;

		//Return statement
		return (!options.return_key) ? civ_obj : civ_id;
	}

	/**
	 * getCivilisationActualName() - Fetches the display localisation name of a given civilisation.
	 * @param {String} arg0_civ_name - The civilisation to fetch the name for.
	 *
	 * @returns {String}
	 */
	function getCivilisationActualName (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var civilisation_tag = getCurrentTag(civ_name);
		var split_tag = civilisation_tag.split("_");
		var localisation_value;

		//Fetch localisation_value name for civ
		localisation_value = Game.lang.getCiv(split_tag[0]);
		if (split_tag.length > 1)
			if (Game.lang.getCiv(civilisation_tag) != civilisation_tag)
				localisation_value = Game.lang.getCiv(civilisation_tag);

		//Return statement
		return localisation_value;
	}

	/**
	 * getCivilisationCapital() - Fetches the capital Province of a given civilisation. Returns the province object.
	 * @param {String} arg0_civ_name - The civilisation to fetch the name for.
	 *
	 * @returns {Object}
	 */
	function getCivilisationCapital (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var capital_obj;
		var civ_obj = getCivilisation(civ_name);

		//Fetch capital_obj if possible
		if (civ_obj)
			if (civ_obj.civData.c >= 0) {
				var capital_obj = getProvince(civ_obj.civData.c);

				//Return statement
				return capital_obj;
			}
	}

	/**
	 * getCivilisationConqueredProvincesAmount - Returns the number of provinces conquered by a civilisation since the beginning of the game.
	 * @param {String} arg0_civ_name
	 *
	 * @returns {number}
	 */
	function getCivilisationConqueredProvincesAmount (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		return civ_obj.CivData3.getConqueredProvinces();
	}

	/**
	 * getCivilisationFromExactTag() - Fetches a civilisation only from its exact tag.
	 * @param {String} arg0_civ_tag
	 * @param {Object} [arg1_options]
	 *  @param {boolean} [arg1_options.return_key=false]
	 *
	 * @returns {Object|String}
	 */
	function getCivilisationFromExactTag (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var split_civ_tag = civ_tag.split("_");
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		if (split_civ_tag.length > 1) {
			if (civ_tag == civ_obj.civData.t)
				return (!options.return_key) ? civ_obj : civ_obj.civData.t;
		} else {
			if (civ_tag == civ_obj.realTag)
				return (!options.return_key) ? civ_obj : civ_obj.realTag;
		}
	}

	/**
	 * getCivilisationID() - Returns the current civilisation ID for a given CivTag. Keep in mind that assigned CivIDs are dynamically generated and not static.
	 * @param {String} arg0_civ_name - The civilisation to fetch the ID for.
	 *
	 * @returns {number}
	 */
	function getCivilisationID (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Guard clause if civ_name is already integer
		if (typeof civ_name == "number") return civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		return civ_obj.iCivID;
	}

	/**
	 * getCivilisationName() - Returns the present civilisation name.
	 * @param {number|Object|String} arg0_civ_name - The civilisation to pass to the function.
	 * @param [arg1_options]
	 * @param [arg1_options.display_debug=false] - Whether to display full debug information.
	 *
	 * @returns {String}
	 */
	function getCivilisationName (arg0_civ_name, arg1_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement; guard clause if debug information is not required
		if (civ_obj && !options.display_debug)
			return civ_obj.sCivName;

		//If options.display_debug is true, print full string
		if (civ_obj && options.display_debug) {
			var actual_civ_name = getCivilisationName(civ_obj);
			var capital_obj = getCivilisationCapital(civ_obj);
			var civ_tag = getCurrentTag(civ_obj);

			var capital_name = (capital_obj) ?
				getProvinceName(capital_obj, { display_province_id: true }) : "None";

			//Return statement
			return civ_tag + " - (" + actual_civ_name + ") - Capital: " + capital_name;
		}
	}

	/**
	 * getCivilisationProvinces() - Returns all provinces belonging to a given civilisation.
	 * @param {String} arg0_civ_name
	 * @param {Object} [arg1_options]
	 *  @param {Array<Object>} [arg1_options.all_provinces=getAllProvinces()] - Optimisation parameter.
	 *  @param {boolean} [arg1_options.exclude_occupied_provinces=false]
	 *  @param {boolean} [arg1_options.return_keys=false]
	 *
	 * @returns {Array<Object>|Array<String>}
	 */
	function getCivilisationProvinces (arg0_civ_name, arg1_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_civ_province_ids = [];
		var all_civ_provinces = [];
		var all_provinces = (options.all_provinces) ? options.all_provinces : getAllProvinces();
		var civ_tag = getCurrentTag(civ_name);

		//Iterate over all_provinces
		for (var i = 0; i < all_provinces.length; i++)
			if (getProvinceOwner(all_provinces[i]) == civ_tag) {
				var include_province = true;

				if (options.exclude_occupied_provinces)
					if (all_provinces[i].isOccupied())
						include_province = false;

				if (include_province) {
					all_civ_province_ids.push(all_provinces[i].getProvinceID());
					all_civ_provinces.push(all_provinces[i]);
				}
			}

		//Return statement
		return (!options.return_keys) ? all_civ_provinces : all_civ_province_ids;
	}

	/**
	 * getCivilisationProvincesAmount() - Returns the number of provinces held by a civilisation.
	 * @param {String} arg0_civ_name
	 *
	 * @returns {number}
	 */
	function getCivilisationProvincesAmount (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		return civ_obj.getProvinces().size();
	}

	/**
	 * getCivilisationUniqueCapitalBuildingAmount() - Returns the current designated capital building level for a civilisation.
	 * @param {String} arg0_civ_name
	 * @param {String} arg1_capital_building_type
	 * @param {Object} [arg2_options]
	 *  @param {Array<Object>} [arg2_options.all_buildings=getAllBuiildings()] - Optimisation parameter.
	 *
	 * @returns {number}
	 */
	function getCivilisationUniqueCapitalBuildingAmount (arg0_civ_name, arg1_capital_building_type, arg2_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var capital_building_type = arg1_capital_building_type;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		if (capital_building_type == "capital") {
			//Return statement
			return civ_obj.getCapitalLevel();
		} else if (capital_building_type == "military_academy") {
			//Return statement
			return civ_obj.getMilitaryAcademyLevel();
		} else if (capital_building_type == "military_academy_for_generals") {
			//Return statement
			return civ_obj.getMilitaryAcademyForGeneralsLevel();
		} else if (capital_building_type == "supreme_court") {
			//Return statement
			return civ_obj.getSupremeCourtLevel();
		} else if (capital_building_type == "nuclear_reactor") {
			//Return statement
			return civ_obj.getNuclearReactorLevel();
		} else {
			var all_buildings = (!options.all_buildings) ? getAllBuildings() : options.all_buildings;
			var capital_province_buildings = getProvinceBuildings(capital_province_obj);
			var capital_province_obj = getCivilisationCapital(civ_obj);

			for (var i = 0; i < all_buildings.length; i++)
				for (var x = 0; x < all_buildings[i].Name.length; x++)
					if (all_buildings[i].Name[x].toLowerCase() == capital_building_type)
						//Return statement
						return returnSafeNumber(capital_province_obj[all_buildings[i].Name[x]]);
		}
	}

	/**
	 * getCurrentTag() - Fetches the current CivTag of a given civilisation.
	 * @param {String} arg0_civ_name
	 *
	 * @returns {string}
	 */
	function getCurrentTag (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		if (civ_obj)
			return civ_obj.civData.t;
	}

	/**
	 * setCivilisationName() - Changes the name of a civilisation within a given savegame.
	 * @param {String} arg0_civ_name
	 * @param {String} arg1_new_civ_name
	 * @param {Object} [arg2_options]
	 *  @param {boolean} [arg2_options.do_not_reload=false]
	 */
	function setCivilisationName (arg0_civ_name, arg1_new_civ_name, arg2_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var new_civ_name = arg1_new_civ_name;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var civilisation_obj = getCivilisation(civ_name);

		var civilisation_id = civilisation_obj.getCivID();

		//Rename civilisation only if valid new_civ_name is provided
		if (new_civ_name)
			if (typeof new_civ_name == "string" && new_civ_name.length > 0) {
				civilisation_obj.setCivName(new_civ_name);
				if (!options.do_not_reload)
					CivilizationRegionsManager.buildCivilizationsRegions_TextOver(civilisation_id);
			}
	}
}