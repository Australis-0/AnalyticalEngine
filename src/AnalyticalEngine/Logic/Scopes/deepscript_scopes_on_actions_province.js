//Initialise functions
{
	//1. Province-wide Buttons
	{
		function onProvinceRename (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_rename[generateRandomID(main.scopes.on_province_rename)] = local_function;
		}
	}


	//2. Province Menu Buttons
	{
		function onProvinceBuildClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_build_click[generateRandomID(main.scopes.on_province_build_click)] = local_function;
		}

		function onProvinceBuildingClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_building_click[generateRandomID(main.scopes.on_province_building_click)] = local_function;
		}

		function onProvinceBonusModifiersClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_bonus_modifiers_click[generateRandomID(main.scopes.on_province_bonus_modifiers_click)] = local_function;
		}

		function onProvinceCoresClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_cores_click[generateRandomID(main.scopes.on_province_cores_click)] = local_function;
		}

		function onProvinceDefenceLevelClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_defence_level_click[generateRandomID(main.scopes.on_province_defence_level_click)] = local_function;
		}

		function onProvinceDevastationClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_devastation_click[generateRandomID(main.scopes.on_province_devastation_click)] = local_function;
		}

		function onProvinceEconomyClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_economy_click[generateRandomID(main.scopes.on_province_economy_click)] = local_function;
		}

		function onProvinceInfrastructureClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_infrastructure_click[generateRandomID(main.scopes.on_province_infrastructure_click)] = local_function;
		}

		function onProvinceLootClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_loot_click[generateRandomID(main.scopes.on_province_loot_click)] = local_function;
		}

		function onProvinceManpowerClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_manpower_click[generateRandomID(main.scopes.on_province_manpower_click)] = local_function;
		}

		function onProvinceMonthlyIncomeClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_monthly_income_click[generateRandomID(main.scopes.on_province_monthly_income_click)] = local_function;
		}

		function onProvincePopulationChartClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_population_chart_click[generateRandomID(main.scopes.on_province_population_chart_click)] = local_function;
		}

		function onProvinceReligionClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_religion_click[generateRandomID(main.scopes.on_province_religion_click)] = local_function;
		}

		function onProvinceResourceClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_resource_click[generateRandomID(main.scopes.on_province_resource_click)] = local_function;
		}

		function onProvinceTaxEfficiencyClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_tax_efficiency_click[generateRandomID(main.scopes.on_province_tax_efficiency_click)] = local_function;
		}

		function onProvinceTerrainPictureClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_terrain_picture_click[generateRandomID(main.scopes.on_province_terrain_picture_click)] = local_function;
		}

		function onProvinceUnrestClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_unrest_click[generateRandomID(main.scopes.on_province_unrest_click)] = local_function;
		}

		function onProvinceValueClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_value_click[generateRandomID(main.scopes.on_province_value_click)] = local_function;
		}
	}
}

//Initialise internal helper functions
{
	function getInternalProvinceOptionsObject (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var province_obj = getProvince(province_id);

		var actual_province_id = province_obj.getProvinceID();
		var civ_obj = getProvinceOwner(province_obj, { return_object: true });
		var civ_tag = getCurrentTag(civ_obj);

		//Return statement
		return {
			civ_obj: civ_obj,
			civ_tag: civ_tag,
			province_id: actual_province_id,
			province_obj: province_obj
		};
	}

	function parseOnProvinceBuildClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_build_click_keys = Object.keys(main.scopes.on_province_build_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceBuildClick() fired.");

		//Iterate over all_on_province_build_click_keys keys
		for (var i = 0; i < all_on_province_build_click_keys.length; i++) {
			var local_function = main.scopes.on_province_build_click[all_on_province_build_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceBuildingClick (arg0_province_id, arg1_building_category_index, arg2_building_index) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var building_category_index = arg1_building_category_index;
		var building_index = arg2_building_index;

		//Declare local instance variables
		var province_obj = getProvince(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceBuildingClick() fired.");

		try {
			var all_on_province_building_click_keys = Object.keys(main.scopes.on_province_building_click);
			var building_key = getBuilding(building_category_index).Name[building_index];

			var building_obj = getBuilding(building_key);

			//Iterate over all_on_province_building_click_keys
			for (var i = 0; i < all_on_province_building_click_keys.length; i++) {
				var local_function = main.scopes.on_province_building_click[all_on_province_building_click_keys[i]];

				local_function({
					province_id: province_obj.getProvinceID(),
					province_obj: province_obj,

					building_key: building_key,
					building_obj: building_obj
				});
			}
		} catch (e) {
			console.log(e.stack);
		}
	}

	//[WIP] - Actually implement these Invocations on the Java end of interaction
	function parseOnProvinceBonusModifiersClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_bonus_modifiers_click_keys = Object.keys(main.scopes.on_province_bonus_modifiers_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceBonusModifiers() fired.");

		//Iterate over all_on_province_bonus_modifiers_click_keys
		for (var i = 0; i < all_on_province_bonus_modifiers_click_keys.length; i++) {
			var local_function = main.scopes.on_province_bonus_modifiers_click[all_on_province_bonus_modifiers_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceCoresClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_cores_click_keys = Object.keys(main.scopes.on_province_cores_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceCoresClick() fired.");

		//Iterate over all_on_province_cores_click_keys
		for (var i = 0; i < all_on_province_cores_click_keys.length; i++) {
			var local_function = main.scopes.on_province_cores_click[all_on_province_cores_click_keys[i]];

			local_function(local_options);
		}
	}

	//[WIP] - Stopped implementing Java-end action handlers here.
	function parseOnProvinceDefenceLevelClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_defence_level_click_keys = Object.keys(main.scopes.on_province_defence_level_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceDefenceLevelClick() fired.");

		//Iterate over all_on_province_defence_level_click_keys
		for (var i = 0; i < all_on_province_defence_level_click_keys.length; i++) {
			var local_function = main.scopes.on_province_defence_level_click[all_on_province_defence_level_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceDevastationClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_devastation_click_keys = Object.keys(main.scopes.on_province_devastation_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceDevastationClick() fired.");

		//Iterate over all_on_province_devastation_click_keys
		for (var i = 0; i < all_on_province_devastation_click_keys.length; i++) {
			var local_function = main.scopes.on_province_devastation_click[all_on_province_devastation_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceEconomyClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_economy_click_keys = Object.keys(main.scopes.on_province_economy_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceEconomyClick() fired.");

		//Iterate over all_on_province_economy_click_keys
		for (var i = 0; i < all_on_province_economy_click_keys.length; i++) {
			var local_function = main.scopes.on_province_economy_click[all_on_province_economy_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceInfrastructureClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_infrastructure_click_keys = Object.keys(main.scopes.on_province_infrastructure_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceInfrastructureClick() fired.");

		//Iterate over all_on_province_infrastructure_click_keys
		for (var i = 0; i < all_on_province_infrastructure_click_keys.length; i++) {
			var local_function = main.scopes.on_province_infrastructure_click[all_on_province_infrastructure_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceLootClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_loot_click_keys = Object.keys(main.scopes.on_province_loot_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceLootClick() fired.");

		//Iterate over all_on_province_loot_click_keys
		for (var i = 0; i < all_on_province_loot_click_keys.length; i++) {
			var local_function = main.scopes.on_province_loot_click[all_on_province_loot_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceManpowerClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_manpower_click_keys = Object.keys(main.scopes.on_province_manpower_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceManpowerClick() fired.");

		//Iterate over all_on_province_manpower_click_keys
		for (var i = 0; i < all_on_province_manpower_click_keys.length; i++) {
			var local_function = main.scopes.on_province_manpower_click[all_on_province_manpower_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceMonthlyIncomeClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_monthly_income_click_keys = Object.keys(main.scopes.on_province_monthly_income_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceMonthlyIncomeClick() fired.");

		//Iterate over all_on_province_monthly_income_click_keys
		for (var i = 0; i < all_on_province_monthly_income_click_keys.length; i++) {
			var local_function = main.scopes.on_province_monthly_income_click[all_on_province_monthly_income_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvincePopulationChartClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_population_chart_click_keys = Object.keys(main.scopes.on_province_population_chart_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvincePopulationChartClick() fired.");

		//Iterate over all_on_province_population_chart_click_keys
		for (var i = 0; i < all_on_province_population_chart_click_keys.length; i++) {
			var local_function = main.scopes.on_province_population_chart_click[all_on_province_population_chart_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceReligionClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_religion_click_keys = Object.keys(main.scopes.on_province_religion_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceReligionClick() fired.");

		//Iterate over all_on_province_religion_click_keys
		for (var i = 0; i < all_on_province_religion_click_keys.length; i++) {
			var local_function = main.scopes.on_province_religion_click[all_on_province_religion_click_keys[i]];

			local_function(local_options);
		}
	}

	function parseOnProvinceRename (arg0_province_id, arg1_old_name, arg2_new_name) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var old_name = arg1_old_name;
		var new_name = arg2_new_name;

		//Declare local instance variables
		var all_on_province_rename_keys = Object.keys(main.scopes.on_province_rename);
		var province_obj = getProvince(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceRename() fired. Old Name: " + old_name + ", New Name: " + new_name);

		//Iterate over all_on_province_rename_keys
		for (var i = 0; i < all_on_province_rename_keys.length; i++) {
			var local_function = main.scopes.on_province_rename[all_on_province_rename_keys[i]];

			local_function({
				province_id: province_id,
				province_obj: province_obj,

				old_province_name: old_name,
				new_province_name: new_name
			});
		}
	}

	function parseOnProvinceTerrainPictureClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_terrain_picture_click_keys = Object.keys(main.scopes.on_province_terrain_picture_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceTerrainPictureClick() fired.");

		//Iterate over all_on_province_terrain_picture_click_keys
		for (var i = 0; i < all_on_province_terrain_picture_click_keys.length; i++) {
			var local_function = main.scopes.on_province_terrain_picture_click[all_on_province_terrain_picture_click_keys[i]];
			local_function(local_options);
		}
	}

	function parseOnProvinceUnrestClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_unrest_click_keys = Object.keys(main.scopes.on_province_unrest_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceUnrestClick() fired.");

		//Iterate over all_on_province_unrest_click_keys
		for (var i = 0; i < all_on_province_unrest_click_keys.length; i++) {
			var local_function = main.scopes.on_province_unrest_click[all_on_province_unrest_click_keys[i]];
			local_function(local_options);
		}
	}

	function parseOnProvinceValueClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var all_on_province_value_click_keys = Object.keys(main.scopes.on_province_value_click);
		var local_options = getInternalProvinceOptionsObject(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceValueClick() fired.");

		//Iterate over all_on_province_value_click_keys
		for (var i = 0; i < all_on_province_value_click_keys.length; i++) {
			var local_function = main.scopes.on_province_value_click[all_on_province_value_click_keys[i]];

			local_function(local_options);
		}
	}
}