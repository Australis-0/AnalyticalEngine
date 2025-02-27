//Initialise functions
{
	function getAllUnitCategories (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_unit_category_keys = [];
		var all_unit_categories = [];
		var unit_category_list = ArmyManager.lUnitsTypes;
		var return_obj = {};

		//Iterate over unit_category_list
		for (var i = 0; i < unit_category_list.size(); i++) {
			var local_unit_category = unit_category_list.get(i);

			all_unit_category_keys.push(local_unit_category.ID);
			all_unit_categories.push(local_unit_category);
			return_obj[i] = local_unit_category;
		}

		//Return statement
		if (options.return_keys) return all_unit_category_keys;
		return (!options.return_object) ?
			all_unit_categories : return_obj;
	}

	function getAllUnits (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_unit_keys = [];
		var all_units = [];
		var unit_list = ArmyManager.lArmy;
		var return_obj = {};

		//Iterate over unit_list
		for (var i = 0; i < unit_list.size(); i++) {
			var local_unit_list = unit_list.get(i);

			for (var x = 0; x < local_unit_list.size(); x++) {
				var local_unit = local_unit_list.get(x);

				all_unit_keys.push(local_unit.Name);
				all_units.push(local_unit);
				return_obj[i] = local_unit;
			}
		}

		//Return statement
		if (options.return_keys) return all_unit_keys;
		return (!options.return_object) ?
			all_units : return_obj;
	}

	function getAllUnitsInCategory (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_unit_categories =  getAllUnitCategories();
		var all_unit_keys = [];
		var all_units = [];
		var return_obj = {};

		//Iterate over all_unit_categories
		for (var i = 0; i < all_unit_categories.length; i++) {
			var local_file_path = all_unit_categories[i].File;

			var local_file_list = FileManager.loadFile("game/units/" + local_file_path);
			var local_file_content = local_file_list.readString();
			var local_json = new Json();
				local_json.setElementType(ArmyManager.ConfigUnitsData.class, "Army", ArmyManager.Data_Army.class);
			var local_data = local_json.fromJson(ArmyManager.ConfigUnitsData.class, local_file_content);

			for (var x = 0; x < local_data.Army.size(); x++) {
				var local_unit = local_data.Army.get(x);

				all_unit_keys.push(local_unit.Name);
				all_units.push(local_unit);
				return_obj[i] = local_unit;
			}
		}

		//Return statement
		if (options.return_keys) return all_unit_keys;
		return (!options.return_object) ?
			all_units : return_obj;
	}

	function getUnit (arg0_unit_name, arg1_options) {
		//Convert from parameters
		var name = arg0_unit_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_units = getAllUnits();
		var unit_exists = [false, ""];
		var unit_index = 0;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return all_units[name];

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < all_units.length; i++)
			if (all_units[i].Name.trim().toLowerCase().indexOf(search_name) != -1) {
				unit_index = i;
				unit_exists = [true, all_units[i]];
			}
		for (var i = 0; i < all_units.length; i++)
			if (all_units[i].Name.trim().toLowerCase() == search_name) {
				unit_index = i;
				unit_exists = [true, all_units[i]];
			}

		//Return statement
		if (options.return_key)
			return (unit_exists[0]) ? unit_exists[1].Name : undefined;
		if (options.return_object)
			return (unit_exists[0]) ? { unit_obj: unit_exists[1], index: unit_index } : undefined;
		return (unit_exists[0]) ? unit_exists[1].Name : undefined;
	}

	function getUnitCategory (arg0_unit_category_name, arg1_options) {
		//Convert from parameters
		var name = arg0_unit_category_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_unit_categories = getAllUnitCategories();
		var unit_category_exists = [false, ""];
		var unit_category_index = 0;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return all_units[name];

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < all_unit_categories.length; i++)
			if (all_unit_categories[i].File.trim().toLowerCase().indexOf(search_name) != -1) {
				unit_category_index = i;
				unit_category_exists = [true, all_unit_categories[i]];
			}
		for (var i = 0; i < all_unit_categories.length; i++)
			if (all_unit_categories[i].File.trim().toLowerCase() == search_name) {
				unit_category_index = i;
				unit_category_exists = [true, all_unit_categories[i]];
			}

		//Return statement
		if (options.return_key)
			return (unit_category_exists[0]) ? unit_category_exists[1].File : undefined;
		if (options.return_object)
			return (unit_category_exists[0]) ? { unit_obj: unit_category_exists[1], index: unit_category_index } : undefined;
		return (unit_category_exists[0]) ? unit_category_exists[1].File : undefined;
	}

	function getUnitsObject () {
		//Declare local instance variables
		var unit_list = ArmyManager.lArmy;
		var return_obj = {};

		//Iterate over unit_list
		for (var i = 0; i < unit_list.size(); i++) {
			var local_unit_list = unit_list.get(i);

			for (var x = 0; x < local_unit_list.size(); x++) {
				var local_unit = local_unit_list.get(x);

				return_obj[local_unit.Name] = [i, x];
			}
		}

		//Return statement
		return return_obj;
	}
}