//Initialise functions
{
	//Provinces (Number of Provinces).
	{
		function civilisationNumberOfProvincesIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProvincesAmountValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationNumberOfProvincesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProvincesAmountValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationNumberOfProvincesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProvincesAmountValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationNumberOfProvincesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProvincesAmountValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationNumberOfProvincesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProvincesAmountValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}
	}

	//Provinces (Other).
	{
		function civilisationCapitalBuildingTypeIs (arg0_civ_tags, arg1_capital_building_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_types = getList(arg1_capital_building_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsUniqueCapitalBuildingValue(civ_tags, capital_building_types, value);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationCapitalBuildingTypeIsGEQ (arg0_civ_tags, arg1_capital_building_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_types = getList(arg1_capital_building_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsUniqueCapitalBuildingValue(civ_tags, capital_building_types, value);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationCapitalBuildingTypeIsGreaterThan (arg0_civ_tags, arg1_capital_building_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_types = getList(arg1_capital_building_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsUniqueCapitalBuildingValue(civ_tags, capital_building_types, value);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationCapitalBuildingTypeIsLEQ (arg0_civ_tags, arg1_capital_building_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_types = getList(arg1_capital_building_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsUniqueCapitalBuildingValue(civ_tags, capital_building_types, value);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationCapitalBuildingTypeIsLessThan (arg0_civ_tags, arg1_capital_building_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_types = getList(arg1_capital_building_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsUniqueCapitalBuildingValue(civ_tags, capital_building_types, value);

			//Return statement
			if (comparison_value < value)
				return true;
		}
	}

	//Provinces (Statistics).
	{
		function civilisationConqueredProvincesIs (arg0_civ_tags, arg1_value) {
			//Convert from parametersd
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsConqueredProvincesValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationConqueredProvincesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parametersd
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsConqueredProvincesValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationConqueredProvincesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parametersd
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsConqueredProvincesValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationConqueredProvincesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parametersd
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsConqueredProvincesValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationConqueredProvincesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parametersd
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsConqueredProvincesValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

	}
}

//Initialise internal helper functions
{
	function getCivilisationsConqueredProvincesValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += returnSafeNumber(getCivilisationConqueredProvincesAmount(civ_tags[i]));

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsProvincesAmountValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += returnSafeNumber(getCivilisationProvincesAmount(civ_tags[i]));

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsUniqueCapitalBuildingValue (arg0_civ_tags, arg1_capital_building_names) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var capital_building_names = getList(arg1_capital_building_names);

		//Declare local instance variables
		var all_buildings = getAllBuildings();
		var comparison_sum = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			for (var x = 0; x < capital_building_names.length; x++)
				comparison_sum += getCivilisationUniqueCapitalBuildingAmount(civ_tags[i], capital_building_names[x], {
					all_buildings: all_buildings
				});
	}
}