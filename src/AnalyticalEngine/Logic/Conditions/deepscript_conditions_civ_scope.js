//Intialise functions
{
	function civilisationBuildingCategoryConstructedIs (arg0_civ_tags, arg1_building_category_key, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);

		//Return statement
		return civilisationBuildingCategoryConstructed(civ_tags, building_category_key, value, function (local_civ_buildings_value) {
			if (local_civ_buildings_value != value)
				return true;
		});
	}

	function civilisationBuildingCategoryConstructedIsGEQ (arg0_civ_tags, arg1_building_category_key, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);

		//Return statement
		return civilisationBuildingCategoryConstructed(civ_tags, building_category_key, value, function (local_civ_buildings_value) {
			if (local_civ_buildings_value < value)
				return true;
		});
	}

	function civilisationBuildingCategoryConstructedIsGreaterThan (arg0_civ_tags, arg1_building_category_key, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);

		//Return statement
		return civilisationBuildingCategoryConstructed(civ_tags, building_category_key, value, function (local_civ_buildings_value) {
			if (local_civ_buildings_value <= value)
				return true;
		});
	}

	function civilisationBuildingCategoryConstructedIsLEQ (arg0_civ_tags, arg1_building_category_key, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);

		//Return statement
		return civilisationBuildingCategoryConstructed(civ_tags, building_category_key, value, function (local_civ_buildings_value) {
			if (local_civ_buildings_value > value)
				return true;
		});
	}

	function civilisationBuildingCategoryConstructedIsLessThan (arg0_civ_tags, arg1_building_category_key, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);

		//Return statement
		return civilisationBuildingCategoryConstructed(civ_tags, building_category_key, value, function (local_civ_buildings_value) {
			if (local_civ_buildings_value >= value)
				return true;
		});
	}
}

//Initialise internal helper functions
{
	function civilisationBuildingCategoryConstructed (arg0_civ_tags, arg1_building_category_key, arg2_value, arg3_conditional_function) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var value = getList(arg2_value);
		var conditional_function = arg3_conditional_function;

		//Declare local instance variables
		var building_keys = [];
		var building_obj = getBuilding(building_category_key, { return_object: true });
		var comparison_value = 0;

		//Iterate over building_obj.Name; populate building_keys
		for (var i = 0; i < building_obj.building_obj.Name.length; i++)
			building_keys.push(building_obj.building_obj.Name[i]);

		if (typeof value[0] == "number") {
			comparison_value = value;
		} else if (typeof value[0] == "string") {
			comparison_value = getCivilisationsBuildingCategoryValue(value, building_category_key, { building_keys: building_keys });
		}

		//Iterate over all civ_tags to check if their value is equal to the comparison_value
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_buildings_value = getCivilisationsBuildingCategoryValue(civ_tags[i], building_category_key);

			if (local_civ_buildings_value)
				if (conditional_function(local_civ_buildings_value))
					return false;
		}

		//Return statement
		return true;
	}

	function getCivilisationsBuildingCategoryValue (arg0_civ_tags, arg1_building_category_key, arg2_options) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_category_key = arg1_building_category_key;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var building_keys = (options.building_keys) ? options.building_keys : [];

		var building_obj = getBuilding(building_category_key, { return_object: true });
		var comparison_value = 0;

		try {
			//Populate building_keys
			if (building_keys)
				//Iterate over building_obj.Name; populate building_keys
				for (var i = 0; i < building_obj.building_obj.Name.length; i++)
					building_keys.push(building_obj.building_obj.Name[i]);

			//Iterate over civ_tags to fetch comparison_value
			for (var i = 0; i < civ_tags.length; i++) {
				var civilisation_buildings_obj = getTotalBuildings(civ_tags[i]);
				var local_building_category_amount = 0;

				for (var x = 0; x < building_keys.length; x++)
					local_building_category_amount += returnSafeNumber(civilisation_buildings_obj[building_keys[x]]);
				comparison_value = Math.max(comparison_value, local_building_category_amount);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}
}