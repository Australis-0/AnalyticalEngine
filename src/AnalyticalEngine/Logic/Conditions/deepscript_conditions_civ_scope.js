//Intialise functions
{
	//Economy (Buildings).
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

		function civilisationBuildingConstructedIs (arg0_civ_tags, arg1_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_key = arg1_building_key;
			var value = arg2_value;

			//Return statement
			return civilisationBuildingCategoryConstructed(civ_tags, building_key, value, function (local_value, local_comparison_value) {
				if (local_value == local_comparison_value)
					return true;
			});
		}

		function civilisationBuildingConstructedIsGEQ (arg0_civ_tags, arg1_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_key = arg1_building_key;
			var value = arg2_value;

			//Return statement
			return civilisationBuildingCategoryConstructed(civ_tags, building_key, value, function (local_value, local_comparison_value) {
				if (local_value >= local_comparison_value)
					return true;
			});
		}

		function civilisationBuildingConstructedIsGreaterThan (arg0_civ_tags, arg1_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_key = arg1_building_key;
			var value = arg2_value;

			//Return statement
			return civilisationBuildingCategoryConstructed(civ_tags, building_key, value, function (local_value, local_comparison_value) {
				if (local_value > local_comparison_value)
					return true;
			});
		}

		function civilisationBuildingConstructedIsLEQ (arg0_civ_tags, arg1_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_key = arg1_building_key;
			var value = arg2_value;

			//Return statement
			return civilisationBuildingCategoryConstructed(civ_tags, building_key, value, function (local_value, local_comparison_value) {
				if (local_value <= local_comparison_value)
					return true;
			});
		}

		function civilisationBuildingConstructedIsLessThan (arg0_civ_tags, arg1_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_key = arg1_building_key;
			var value = arg2_value;

			//Return statement
			return civilisationBuildingCategoryConstructed(civ_tags, building_key, value, function (local_value, local_comparison_value) {
				if (local_value < local_comparison_value)
					return true;
			});
		}

		function uniqueCapitalBuildingsConstructedIs (arg0_civ_tags, arg1_value) {
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

		function uniqueCapitalBuildingsConstructedIsGEQ (arg0_civ_tags, arg1_value) {
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

		function uniqueCapitalBuildingsConstructedIsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function uniqueCapitalBuildingsConstructedIsLEQ (arg0_civ_tags, arg1_value) {
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

		function uniqueCapitalBuildingsConstructedIsLessThan (arg0_civ_tags, arg1_value) {
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

	//Economy (Civilisation).
	{
		function civilisationEconomyIncomeIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (value == comparison_value)
				return true;
		}

		function civilisationEconomyIncomeIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (value >= comparison_value)
				return true;
		}

		function civilisationEconomyIncomeIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (value > comparison_value)
				return true;
		}

		function civilisationEconomyIncomeIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (value <= comparison_value)
				return true;
		}

		function civilisationEconomyIncomeIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (value < comparison_value)
				return true;
		}

		function civilisationEconomyIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (value == comparison_value)
				return true;
		}

		function civilisationEconomyIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (value >= comparison_value)
				return true;
		}

		function civilisationEconomyIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (value > comparison_value)
				return true;
		}

		function civilisationEconomyIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (value <= comparison_value)
				return true;
		}

		function civilisationEconomyIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (value < comparison_value)
				return true;
		}
	}
}

//Initialise internal helper functions
{
	function civilisationBuildingConstructed (arg0_civ_tags, arg1_building_key, arg2_value, arg3_conditional_function) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_key = getBuildingKey(arg1_building_key);
		var value = getList(arg2_value);
		var conditional_function = arg3_conditional_function;

		//Declare local instance variables
		var comparison_value = getCivilisationsBuildingValue(civ_tags, building_key);

		//Iterate over all civ_tags to check if their value is equal to the comparison value
		if (conditional_function(value, comparison_value))
			return true;
	}

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

	function civilisationUniqueCapitalBuildingsConstructed (arg0_civ_tags, arg1_value, arg2_conditional_function, arg3_options) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var value = returnSafeNumber(arg1_value);
		var conditional_function = arg2_conditional_function;
		var options = (arg3_options) ? arg3_options : {};

		//Declare local instance variables
		var comparison_value = getCivilisationsUniqueCapitalBuildingsValue(civ_tags);

		//Return statement
		if (conditional_function(value, comparison_value))
			return true;
	}

	function getCivilisationsBuildingValue (arg0_civ_tags, arg1_building_name, arg2_options) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_name = arg1_building_name;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var building_key = getBuildingKey(building_name);
		var comparison_value = 0;

		try {
			//Iterate over civ_tags to fetch comparison_value
			for (var i = 0; i < civ_tags.length; i++) {
				var civilisation_buildings_obj = getTotalBuildings(civ_tags[i]);

				comparison_value = Math.max(comparison_value, returnSafeNumber(civilisation_buildings_obj[building_key]));
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
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

	function getCivilisationsEconomyIncomeValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.getIncomeEconomy());
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsEconomyValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.getEconomyTotal());
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsMoneyValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.fGold);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsProductionValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.getIncomeProduction());
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsTaxationValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.getIncomeTaxation());
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;

	}

	function getCivilisationsTotalIncomeValue (arg0_civ_tags) {
		//Convert frrom parameters
		var civ_tags = arg0_civ_tags;

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, civ_obj.getTotalIncome());
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsUniqueCapitalBuildingsValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			//Iterate over civ_tags to fetch comparison_value
			for (var i = 0; i < civ_tags.length; i++) {
				var civilisation_unique_buildings = getUniqueCapitalBuildingsConstructed(civ_tags[i], options);

				comparison_value = Math.max(comparison_value, returnSafeNumber(civilisation_unique_buildings));
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}
}