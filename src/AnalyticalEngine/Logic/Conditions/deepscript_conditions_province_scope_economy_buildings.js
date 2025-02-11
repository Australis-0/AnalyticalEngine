//Initialise functions
{
	//Economy (Buildings - Checks).
	{
		function provinceDoesNotHaveBuilding (arg0_provinces, arg1_building_names) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);

			//Declare local instance variables
			var all_buildings = getAllBuildings();

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_buildings = getProvinceBuildings(local_province, { all_buildings: all_buildings });

				for (var x = 0; x < building_names.length; x++)
					if (returnSafeNumber(local_province_buildings[building_names[x]]) > 0)
						return false;
			}

			//Return statement
			return true;
		}

		function provinceHasBuilding (arg0_provinces, arg1_building_names) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);

			//Declare local instance variables
			var all_buildings = getAllBuildings();

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_buildings = getProvinceBuildings(local_province, { all_buildings: all_buildings });

				for (var x = 0; x < building_names.length; x++)
					if (returnSafeNumber(local_province_buildings[building_names[x]]) == 0)
						return false;
			}

			//Return statement
			return true;
		}
	}

	//Economy (Buildings).
	{
		function provinceBuildingConstructedIs (arg0_provinces, arg1_building_names, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_sum = getProvincesBuildingsValue(provinces, building_names);

			//Return statement
			return (comparison_sum == value);
		}

		function provinceBuildingConstructedIsGEQ (arg0_provinces, arg1_building_names, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_sum = getProvincesBuildingsValue(provinces, building_names);

			//Return statement
			return (comparison_sum >= value);
		}

		function provinceBuildingConstructedIsGreaterThan (arg0_provinces, arg1_building_names, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_sum = getProvincesBuildingsValue(provinces, building_names);

			//Return statement
			return (comparison_sum > value);
		}

		function provinceBuildingConstructedIsLEQ (arg0_provinces, arg1_building_names, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_sum = getProvincesBuildingsValue(provinces, building_names);

			//Return statement
			return (comparison_sum <= value);
		}

		function provinceBuildingConstructedIsLessThan (arg0_provinces, arg1_building_names, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_sum = getProvincesBuildingsValue(provinces, building_names);

			//Return statement
			return (comparison_sum < value);
		}

		function provinceBuildingsIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildings(provinces);

			//Return statement
			return (comparison_sum == value);
		}

		function provinceBuildingsIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildings(provinces);

			//Return statement
			return (comparison_sum >= value);
		}

		function provinceBuildingsIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildings(provinces);

			//Return statement
			return (comparison_sum > value);
		}

		function provinceBuildingsIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildings(provinces);

			//Return statement
			return (comparison_sum <= value);
		}

		function provinceBuildingsIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildings(provinces);

			//Return statement
			return (comparison_sum < value);
		}

		function provinceBuildingLimitIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildingLimit(provinces);

			//Return statement
			return (comparison_sum == value);
		}

		function provinceBuildingLimitIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildingLimit(provinces);

			//Return statement
			return (comparison_sum >= value);
		}

		function provinceBuildingLimitIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildingLimit(provinces);

			//Return statement
			return (comparison_sum > value);
		}

		function provinceBuildingLimitIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildingLimit(provinces);

			//Return statement
			return (comparison_sum <= value);
		}

		function provinceBuildingLimitIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_sum = getProvincesTotalBuildingLimit(provinces);

			//Return statement
			return (comparison_sum < value);
		}
	}
}

//Initialise internal helper functions
{
	function getProvincesBuildingsValue (arg0_provinces, arg1_building_names) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var building_names = getList(arg1_building_names);

		//Declare local instance variables
		var all_buildings = getAllBuildings();
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++) {
			var local_province = getProvince(provinces[i]);
			var local_province_buildings = getProvinceBuildings(local_province, { all_buildings: all_buildings });

			for (var x = 0; x < building_names.length; x++)
				comparison_sum += local_province_buildings[building_names[x]];
		}

		//Return statement
		return comparison_sum;
	}

	function getProvincesTotalBuildings (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceTotalBuildings(provinces[i]);

		//Return statement
		return comparison_sum;
	}

	function getProvincesTotalBuildingLimit (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceBuildingLimit(provinces[i]);

		//Return statement
		return comparison_sum;
	}
}