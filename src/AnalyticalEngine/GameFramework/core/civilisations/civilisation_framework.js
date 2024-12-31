//Import classes
{
	this.Civilization = Java.type("aoc.kingdoms.lukasz.map.civilization.Civilization");
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
}

//Initialise functions
{
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

	function getCurrentTag (arg0_civ_name) {
		//Convert from parameters
		var civ_name = arg0_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Return statement
		if (civ_obj)
			return civ_obj.civData.t;
	}

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