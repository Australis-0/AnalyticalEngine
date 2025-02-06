//Initialise functions
{
	function getAllLegacies (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_legacies = [];
		var all_legacy_keys = [];
		var legacies_list = LegacyManager.legacies;
		var return_obj = {};

		//Iterate over legacies_list
		for (var i = 0; i < legacies_list.size(); i++) {
			var local_legacy = legacies_list.get(i);

			all_legacy_keys.push(local_legacy.Name);
			all_legacies.push(local_legacy);
			return_obj[i] = local_legacy;
		}

		//Return statement
		if (options.return_keys) return all_legacy_keys;
		return (!options.return_object) ?
			all_legacies : return_obj;
	}

	function getCivilisationLegacies (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_legacies = (options.all_legacies) ? options.all_legacies : getAllLegacies({ return_keys: true });
		var civ_legacies = [];
		var civ_obj = getCivilisation(civ_tag);

		//Iterate over all_legacies
		for (var i = 0; i < all_legacies.length; i++) {
			var local_legacy = getLegacy(all_legacies[i]);
			var local_legacy_level = civ_obj.getLegacyLevel(i);

			//Push to civ_legacies
			if (local_legacy_level > -1)
				civ_legacies.push({
					id: i,
					name: local_legacy.Name,

					level: local_legacy_level + 1
				});
		}

		//Return statement
		return civ_legacies;
	}

	function getCivilisationLegaciesValue (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.getLegaciesUnlocked();
	}

	function getLegacy (arg0_legacy_name, arg1_options) {
		//Convert from parameters
		var name = arg0_legacy_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var legacy_exists = [false, ""]; //[legacy_exists, legacy_obj];
		var legacy_index = 0;
		var legacy_list = LegacyManager.legacies;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return legacy_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < legacy_list.size(); i++) {
			var local_legacy = legacy_list.get(i);

			if (local_legacy.Name.trim().toLowerCase().indexOf(search_name) != -1) {
				legacy_exists = [true, local_legacy];
				legacy_index = i;
			}
		}
		for (var i = 0; i < legacy_list.size(); i++) {
			var local_legacy = legacy_list.get(i);

			if (local_legacy.Name.trim().toLowerCase() == search_name) {
				legacy_exists = [true, local_legacy];
				legacy_index = i;
			}
		}

		//Return statement
		if (options.return_object)
			return (legacy_exists[0]) ? { index: legacy_index, legacy_obj: legacy_exists[1] } : undefined;
		return (legacy_exists[0]) ? legacy_exists[1] : undefined;
	}
}