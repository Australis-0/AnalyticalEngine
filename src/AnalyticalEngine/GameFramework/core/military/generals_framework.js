//Initialise functions
{
	function getGeneral (arg0_general_name) {
		//Convert from parameters
		var general_name = arg0_general_name;

		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var general_obj;

		var actual_general_name;

		//Guard clauses if general_name is already an object
		if (typeof general_name == "object") return general_name;

		//Check to see if general_name is of type JSON or simply an object
		if (general_name.toLowerCase().endsWith(".json")) {
			var general_file_path = general_name.split(".json")[0];

			//Load general
			var general_obj = loadFileAsObject(general_file_path);

			if (general_obj)
				actual_general_name = general_obj[0].Name;
		}

		//Iterate over all_general_keys if actual_general_name exists and return it
		if (actual_general_name) {
			var all_general_keys = Object.keys(main.generals);

			//Iterate over all_general_keys
			for (var i = 0; i < all_general_keys.length; i++) {
				var local_general = main.generals[all_general_keys[i]];

				if (local_general.n)
					return local_general;
			}
		}
	}

	function loadGenerals () {
		//Clear main.generals
		main.generals = {};

		//Fetch current scenario civs
		var all_civilisations = getAllCurrentCivTags();
		var all_provinces = getAllProvinces();

		//Iterate over all_civilisations
		for (var i = 0; i < all_civilisations.length; i++) {
			var local_civ = getCivilisation(all_civilisations[i]);

			for (var x = 0; x < local_civ.getGeneralsNotAssignedSize(); x++) {
				var local_general = local_civ.getGeneralNotAssigned(x);
				main.generals[local_general.n] = local_general;
			}
		}

		//Iterate over all_provinces
		for (var i = 0; i < all_provinces.length; i++) {
			var local_province = getProvince(all_provinces[i]);
			var local_province_armies = getProvinceArmies(all_provinces[i]);

			//Iterate over local_province_armies
			for (var x = 0; x < local_province_armies.length; x++) {
				var local_army_general = local_province_armies[x].armyGeneral;

				if (local_army_general)
					main.generals[local_army_general.n] = local_army_general;
			}
		}
	}

	function isGeneralEqualTo (arg0_general_name, arg1_general_name) {
		//Convert from parameters
		var general_name = arg0_general_name;
		var ot_general_name = arg1_general_name;

		//Return statement
		return (getGeneral(general_name).n == getGeneral(ot_general_name).n);
	}
}