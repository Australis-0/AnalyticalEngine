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
	 * @param [arg1_options.return_key=false] - Whether to return the Civ ID instead.
	 * @param [arg1_options.return_tag=false] - Whether to return the CivTag.
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

				if (local_civ.civData.realTag == civ_name) {
					civ_id = local_civ.iCivID;
					civ_obj = local_civ;
				}
			}

			//Name search
			if (!civ_obj && typeof search_name == "string") {
				var search_name = civ_name.trim().toLowerCase();

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
		if (civ_obj && options.return_tag)
			return civ_obj.realTag;

		//Return statement
		return (!options.return_key) ? civ_obj : civ_id;
	}
}