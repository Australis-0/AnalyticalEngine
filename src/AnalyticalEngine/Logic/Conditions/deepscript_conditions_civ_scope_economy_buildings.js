//Initialise functions
{
	//Economy (Buildings).
	{
		function civilisationBuildingCategoryConstructedIs (arg0_civ_tags, arg1_building_category_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_category_names = getList(arg1_building_category_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_category_names
			for (var i = 0; i < building_category_names.length; i++)
				checks_passed += (civilisationBuildingCategoryConstructed(civ_tags, building_category_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value == value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_category_names.length);
		}

		function civilisationBuildingCategoryConstructedIsGEQ (arg0_civ_tags, arg1_building_category_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_category_names = getList(arg1_building_category_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_category_names
			for (var i = 0; i < building_category_names.length; i++)
				checks_passed += (civilisationBuildingCategoryConstructed(civ_tags, building_category_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value >= value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_category_names.length);
		}

		function civilisationBuildingCategoryConstructedIsGreaterThan (arg0_civ_tags, arg1_building_category_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_category_names = getList(arg1_building_category_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_category_names
			for (var i = 0; i < building_category_names.length; i++)
				checks_passed += (civilisationBuildingCategoryConstructed(civ_tags, building_category_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value > value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_category_names.length);
		}

		function civilisationBuildingCategoryConstructedIsLEQ (arg0_civ_tags, arg1_building_category_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_category_names = getList(arg1_building_category_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_category_names
			for (var i = 0; i < building_category_names.length; i++)
				checks_passed += (civilisationBuildingCategoryConstructed(civ_tags, building_category_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value <= value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_category_names.length);
		}

		function civilisationBuildingCategoryConstructedIsLessThan (arg0_civ_tags, arg1_building_category_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_category_names = getList(arg1_building_category_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_category_names
			for (var i = 0; i < building_category_names.length; i++)
				checks_passed += (civilisationBuildingCategoryConstructed(civ_tags, building_category_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value < value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_category_names.length);
		}

		function civilisationBuildingConstructedIs (arg0_civ_tags, arg1_building_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_names
			for (var i = 0; i < building_names.length; i++)
				checks_passed += (civilisationBuildingConstructed(civ_tags, building_names[i], value, function (local_value, local_comparison_value) {
					if (local_value == local_comparison_value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_names.length);
		}

		function civilisationBuildingConstructedIsGEQ (arg0_civ_tags, arg1_building_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_names
			for (var i = 0; i < building_names.length; i++)
				checks_passed += (civilisationBuildingConstructed(civ_tags, building_names[i], value, function (local_value, local_comparison_value) {
					if (local_value >= local_comparison_value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_names.length);
		}

		function civilisationBuildingConstructedIsGreaterThan (arg0_civ_tags, arg1_building_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_names
			for (var i = 0; i < building_names.length; i++)
				checks_passed += (civilisationBuildingConstructed(civ_tags, building_names[i], value, function (local_value, local_comparison_value) {
					if (local_value > local_comparison_value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_names.length);
		}

		function civilisationBuildingConstructedIsLEQ (arg0_civ_tags, arg1_building_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_names
			for (var i = 0; i < building_names.length; i++)
				checks_passed += (civilisationBuildingConstructed(civ_tags, building_names[i], value, function (local_value, local_comparison_value) {
					if (local_value <= local_comparison_value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_names.length);
		}

		function civilisationBuildingConstructedIsLessThan (arg0_civ_tags, arg1_building_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_names = getList(arg1_building_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_names
			for (var i = 0; i < building_names.length; i++)
				checks_passed += (civilisationBuildingConstructed(civ_tags, building_names[i], value, function (local_value, local_comparison_value) {
					if (local_value < local_comparison_value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_names.length);
		}

		function civilisationBuildingGroupConstructedIs (arg0_civ_tags, arg1_building_group_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_names = getList(arg1_building_group_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_group_names
			for (var i = 0; i < building_group_names.length; i++)
				checks_passed += (civilisationBuildingGroupConstructed(civ_tags, building_group_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value != value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_group_names.length);
		}

		function civilisationBuildingGroupConstructedIsGEQ (arg0_civ_tags, arg1_building_group_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_names = getList(arg1_building_group_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_group_names
			for (var i = 0; i < building_group_names.length; i++)
				checks_passed += (civilisationBuildingGroupConstructed(civ_tags, building_group_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value < value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_group_names.length);
		}

		function civilisationBuildingGroupConstructedIsGreaterThan (arg0_civ_tags, arg1_building_group_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_names = getList(arg1_building_group_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_group_names
			for (var i = 0; i < building_group_names.length; i++)
				checks_passed += (civilisationBuildingGroupConstructed(civ_tags, building_group_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value <= value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_group_names.length);
		}

		function civilisationBuildingGroupConstructedIsLEQ (arg0_civ_tags, arg1_building_group_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_names = getList(arg1_building_group_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_group_names
			for (var i = 0; i < building_group_names.length; i++)
				checks_passed += (civilisationBuildingGroupConstructed(civ_tags, building_group_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value > value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_group_names.length);
		}

		function civilisationBuildingGroupConstructedIsLessThan (arg0_civ_tags, arg1_building_group_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_names = getList(arg1_building_group_names);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var checks_passed = 0;

			//Iterate over building_group_names
			for (var i = 0; i < building_group_names.length; i++)
				checks_passed += (civilisationBuildingGroupConstructed(civ_tags, building_group_names[i], value, function (local_civ_buildings_value) {
					if (local_civ_buildings_value >= value)
						return true;
				})) ? 1 : 0;

			//Return statement
			return (checks_passed >= building_group_names.length);
		}
	}

	//Economy (Buildings - Unique Capital Buildings).
	{
		function civilisationUniqueCapitalBuildingsConstructedIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Return statement
			return civilisationUniqueCapitalBuildingsConstructed(civ_tags, value, function (local_value, local_comparison_value) {
				if (local_value == local_comparison_value)
					return true;
			});
		}

		function civilisationUniqueCapitalBuildingsConstructedIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Return statement
			return civilisationUniqueCapitalBuildingsConstructed(civ_tags, value, function (local_value, local_comparison_value) {
				if (local_value >= local_comparison_value)
					return true;
			});
		}

		function civilisationUniqueCapitalBuildingsConstructedIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Return statement
			return civilisationUniqueCapitalBuildingsConstructed(civ_tags, value, function (local_value, local_comparison_value) {
				if (local_value > local_comparison_value)
					return true;
			});
		}

		function civilisationUniqueCapitalBuildingsConstructedIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Return statement
			return civilisationUniqueCapitalBuildingsConstructed(civ_tags, value, function (local_value, local_comparison_value) {
				if (local_value <= local_comparison_value)
					return true;
			});
		}

		function civilisationUniqueCapitalBuildingsConstructedIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Return statement
			return civilisationUniqueCapitalBuildingsConstructed(civ_tags, value, function (local_value, local_comparison_value) {
				if (local_value < local_comparison_value)
					return true;
			});
		}
	}
}