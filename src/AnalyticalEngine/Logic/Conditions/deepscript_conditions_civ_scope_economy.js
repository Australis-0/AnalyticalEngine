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

		function civilisationBuildingGroupConstructedIs (arg0_civ_tags, arg1_building_group_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_key = arg1_building_group_key;
			var value = getList(arg2_value);

			//Return statement
			return civilisationBuildingGroupConstructed(civ_tags, building_group_key, value, function (local_civ_buildings_value) {
				if (local_civ_buildings_value != value)
					return true;
			});
		}

		function civilisationBuildingGroupConstructedIsGEQ (arg0_civ_tags, arg1_building_group_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_key = arg1_building_group_key;
			var value = getList(arg2_value);

			//Return statement
			return civilisationBuildingGroupConstructed(civ_tags, building_group_key, value, function (local_civ_buildings_value) {
				if (local_civ_buildings_value < value)
					return true;
			});
		}

		function civilisationBuildingGroupConstructedIsGreaterThan (arg0_civ_tags, arg1_building_group_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_key = arg1_building_group_key;
			var value = getList(arg2_value);

			//Return statement
			return civilisationBuildingGroupConstructed(civ_tags, building_group_key, value, function (local_civ_buildings_value) {
				if (local_civ_buildings_value <= value)
					return true;
			});
		}

		function civilisationBuildingGroupConstructedIsLEQ (arg0_civ_tags, arg1_building_group_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_key = arg1_building_group_key;
			var value = getList(arg2_value);

			//Return statement
			return civilisationBuildingGroupConstructed(civ_tags, building_group_key, value, function (local_civ_buildings_value) {
				if (local_civ_buildings_value > value)
					return true;
			});
		}

		function civilisationBuildingGroupConstructedIsLessThan (arg0_civ_tags, arg1_building_group_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var building_group_key = arg1_building_group_key;
			var value = getList(arg2_value);

			//Return statement
			return civilisationBuildingGroupConstructed(civ_tags, building_group_key, value, function (local_civ_buildings_value) {
				if (local_civ_buildings_value >= value)
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
			if (comparison_value == value)
				return true;
		}

		function civilisationEconomyIncomeIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationEconomyIncomeIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationEconomyIncomeIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationEconomyIncomeIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyIncomeValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationEconomyIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationEconomyIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationEconomyIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationEconomyIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationEconomyIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsEconomyValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationEconomyProductionIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProductionValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationEconomyProductionIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProductionValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationEconomyProductionIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProductionValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationEconomyProductionIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProductionValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationEconomyProductionIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsProductionValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationEconomyTaxationIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTaxationValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationEconomyTaxationIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTaxationValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationEconomyTaxationIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTaxationValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationEconomyTaxationIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTaxationValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationEconomyTaxationIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTaxationValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationEconomyTotalIncomeIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTotalIncomeValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationEconomyTotalIncomeIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTotalIncomeValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationEconomyTotalIncomeIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTotalIncomeValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationEconomyTotalIncomeIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTotalIncomeValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationEconomyTotalIncomeIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsTotalIncomeValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationMoneyIs (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsMoneyValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationMoneyIsGEQ (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsMoneyValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationMoneyIsGreaterThan (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsMoneyValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationMoneyIsLEQ (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsMoneyValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationMoneyIsLessThan (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsMoneyValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}
	}

	//Economy (Goods/Resources).
	{
		function civilisationHasResource (arg0_civ_tags, arg1_resource_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);

			//Declare local instance variables
			var civ_resources_obj = getCivilisationResources(civ_tags);

			//Iterate over civ_tags; resource_types
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_resources_obj = getCivilisationResources(civ_tags[i]);

				for (var x = 0; x < resource_types.length; x++)
					if (returnSafeNumber(civ_resources_obj[resource_types[x]]) <= 0)
						//Return statement
						return false;
			}
			return true;
		}

		function civilisationHasResourceProvinces (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.resources) options.resources = [];
			options.value = returnSafeNumber(options.value, 1);

			//Declare local instance variables
			var civ_resources_obj = getCivilisationResources(civ_tags);

			//Iterate over civ_tags; options.resources
			for (var i = 0; i < civ_tags.length; i++) {
				if (!options.provinces) {
					var local_civ_resources_obj = getCivilisationResources(civ_tags[i]);

					for (var x = 0; x < options.resources.length; x++)
						if (returnSafeNumber(civ_resources_obj[options.resources[x]]) < options.value)
							//Return statement
							return false;
				} else {
					options.provinces = getList(options.provinces);

					var local_civ_resources_obj = getCivilisationProvincesResources(civ_tags[i], options.provinces, {
						exclude_occupied_provinces: true
					});

					for (var x = 0; x < options.resources.length; x++)
						if (returnSafeNumber(civ_resources_obj[options.resources[x]]) < options.value)
							//Return statement
							return false;
				}
			}
			return true;
		}

		/**
		 * isLargestProducer() = Checks to see if each civ_type n is also the largest producer of resource_type n.
		 * @param {Array<String>} arg0_civ_tags
		 * @param {Array<String>} arg1_resource_types
		 *
		 * @returns {boolean}
		 */
		function isLargestProducer (arg0_civ_tags, arg1_resource_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);

			//Declare local instance variables
			var all_civ_tags = getAllCurrentCivTags();
			var global_resource_production_obj = getGlobalResourceProductionObject({ all_civ_tags: all_civ_tags });

			//Iterate over all_civ_tags to determine largest producer of each good
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var current_largest_producer = ["", 0];
				var local_civ_tag = getCurrentTag(civ_tags[i]);

				//Iterate over all_civ_tags in global_resource_production_obj
				for (var x = 0; x < all_civ_tags.length; x++)
					if (global_resource_production_obj[all_civ_tags[x]]) {
						var local_resource_value = returnSafeNumber(global_resource_production_obj[all_civ_tags[x]][local_resource]);

						if (local_resource_value > global_resource_production_obj[1])
							current_largest_producer = [all_civ_tags[x], local_resource_value];
					}

				//Return statement
				if (current_largest_producer != local_civ_tag)
					return false;
			}

			//Return statement
			return true;
		}

		function isLargestProducerOfNumberOfResources (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var largest_producers_obj = getLargestProducersObject();

			//Iterate over civ_tags; check against largest_producers_obj
			for (var i = 0; i < civ_tags.length; i++)
				if (largest_producers_obj[civ_tags[i]] < value)
					//Return statement
					return false;
			return true;
		}

		function largestResourceProducedIs (arg0_civ_tags, arg1_resource_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);

			//Iterate over civ_tags and fetch their respective largest resource type produced
			for (var i = 0; i < civ_tags.length; i++) {
				var largest_resource_type_produced = getCivilisationLargestProducedResource(civ_tags[i], { return_key: true });
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				if (largest_resource_type_produced != local_resource_type)
					//Return statement
					return false;
			}
			return true;
		}

		function resourceProductionIs (arg0_civ_tags, arg1_resource_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var global_resource_production_obj = getGlobalResourceProductionObject();

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var local_civ_resource_production = returnSafeNumber(global_resource_production_obj[civ_tags[i]][local_resource_type]);

				if (local_civ_resource_production != value)
					//Return statement
					return false;
			}
			return true;
		}

		function resourceProductionIsLEQ (arg0_civ_tags, arg1_resource_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var global_resource_production_obj = getGlobalResourceProductionObject();

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var local_civ_resource_production = returnSafeNumber(global_resource_production_obj[civ_tags[i]][local_resource_type]);

				if (local_civ_resource_production > value)
					//Return statement
					return false;
			}
			return true;
		}

		function resourceProductionIsLessThan (arg0_civ_tags, arg1_resource_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var global_resource_production_obj = getGlobalResourceProductionObject();

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var local_civ_resource_production = returnSafeNumber(global_resource_production_obj[civ_tags[i]][local_resource_type]);

				if (local_civ_resource_production >= value)
					//Return statement
					return false;
			}
			return true;
		}

		function resourceProductionIsGEQ (arg0_civ_tags, arg1_resource_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var global_resource_production_obj = getGlobalResourceProductionObject();

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var local_civ_resource_production = returnSafeNumber(global_resource_production_obj[civ_tags[i]][local_resource_type]);

				if (local_civ_resource_production < value)
					//Return statement
					return false;
			}
			return true;
		}

		function resourceProductionIsGreaterThan (arg0_civ_tags, arg1_resource_types, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_types);
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var global_resource_production_obj = getGlobalResourceProductionObject();

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_resource_type = (resource_types[i]) ? resource_types[i] : resource_types[0];

				var local_civ_resource_production = returnSafeNumber(global_resource_production_obj[civ_tags[i]][local_resource_type]);

				if (local_civ_resource_production <= value)
					//Return statement
					return false;
			}
			return true;
		}
	}

	//Economy (Pops).
	{
		function civilisationAverageGrowthRateIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) == value)
				return true;
		}

		function civilisationAverageGrowthRateIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) >= value)
				return true;
		}

		function civilisationAverageGrowthRateIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) > value)
				return true;
		}

		function civilisationAverageGrowthRateIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) <= value)
				return true;
		}

		function civilisationAverageGrowthRateIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) < value)
				return true;
		}

		function civilisationIncreasedGrowthRateIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) == value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) >= value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) > value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) <= value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) < value)
				return true;
		}

		function civilisationPopulationIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) == value)
				return true;
		}

		function civilisationPopulationIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) >= value)
				return true;
		}

		function civilisationPopulationIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) > value)
				return true;
		}

		function civilisationPopulationIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) <= value)
				return true;
		}

		function civilisationPopulationIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) < value)
				return true;
		}
	}

	//Economy (Province).
	{
		function civilisationDevelopedInfrastructureIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsDevelopedInfrastructureValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationDevelopedInfrastructureIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsDevelopedInfrastructureValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationDevelopedInfrastructureIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsDevelopedInfrastructureValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationDevelopedInfrastructureIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsDevelopedInfrastructureValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationDevelopedInfrastructureIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsDevelopedInfrastructureValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationIncreasedManpowerIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedManpowerValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationIncreasedManpowerIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedManpowerValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationIncreasedManpowerIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedManpowerValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationIncreasedManpowerIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedManpowerValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationIncreasedManpowerIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedManpowerValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationIncreasedTaxEfficiencyIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedTaxEfficiencyValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationIncreasedTaxEfficiencyIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedTaxEfficiencyValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationIncreasedTaxEfficiencyIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedTaxEfficiencyValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationIncreasedTaxEfficiencyIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedTaxEfficiencyValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationIncreasedTaxEfficiencyIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsIncreasedTaxEfficiencyValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationInvestedInEconomyIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsInvestedInEconomyValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationInvestedInEconomyIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsInvestedInEconomyValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationInvestedInEconomyIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsInvestedInEconomyValue(civ_tags);

			//Return statement
			if (value > comparison_value)
				return true;
		}

		function civilisationInvestedInEconomyIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsInvestedInEconomyValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationInvestedInEconomyIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getCivilisationsInvestedInEconomyValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}
	}

	//Economy (Technology).
	{
		function civilisationResearchedTechnologiesIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies <- value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationTechnologyIsResearched (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_technologies_researched = getCivilisationResearchedTechnologies(local_civ);

				for (var x = 0; x < value.length; x++) {
					var local_technology = getTechnology(value[x]);

					if (!local_technologies_researched.includes(local_technology.Name))
						//Return statement
						return false;
				}
			}

			//Return statement
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
			comparison_value = value[0];
		} else if (typeof value[0] == "string") {
			comparison_value = getCivilisationsBuildingCategoryValue(value, building_category_key, { building_keys: building_keys });
		}

		//Iterate over all civ_tags to check if their value is equal to the comparison_value
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_buildings_value = getCivilisationsBuildingCategoryValue(civ_tags[i], building_category_key);

			if (local_civ_buildings_value)
				if (conditional_function(local_civ_buildings_value, comparison_value))
					return false;
		}

		//Return statement
		return true;
	}

	function civilisationBuildingGroupConstructed (arg0_civ_tags, arg1_building_group_key, arg2_value, arg3_conditional_function) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_group_key = arg1_building_group_key;
		var value = getList(arg2_value);

		//Declare local instance variables
		var comparison_value = 0;

		if (typeof value[0] == "number") {
			comparison_value = value[0];
		} else if (typeof value[0] == "string") {
			comparison_value = getCivilisationsBuildingGroupValue(value, building_group_key);
		}

		//Iterate over all civ_tags to check if their value is equal to the comparison value
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_buildings_value = getCivilisationsBuildingGroupValue(civ_tags[i], building_group_key);

			if (local_civ_buildings_value)
				if (conditional_function(local_civ_buildings_value, comparison_value))
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

	function getCivilisationsAverageGrowthRateValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += getCivilisationAverageGrowthRate(civ_tags[i]);

		//Return statement
		return comparison_sum/civ_tags.length;
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

	function getCivilisationsBuildingGroupValue (arg0_civ_tags, arg1_building_group_key, arg2_options) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var building_group_key = returnSafeNumber(arg1_building_group_key);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var building_group_keys = getAllBuildingsInGroup(building_group_key, { return_keys: true });
		var comparison_value = 0;

		try {
			//Iterate over civ_tags to fetch comparison_value
			for (var i = 0; i < civ_tags.length; i++) {
				var civilisation_buildings_obj = getTotalBuildings(civ_tags[i]);
				var local_building_group_amount = 0;

				for (var x = 0; x < building_group_keys.length; x++)
					local_building_group_amount += returnSafeNumber(civilisation_buildings_obj[building_group_keys[x]]);
				comparison_value = Math.max(comparison_value, local_building_group_amount);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsDevelopedInfrastructureValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, getCivilisationDevelopedInfrastructure(civ_tags[i]));
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

	function getCivilisationsCombinedResourceProductionValuesObject (arg0_civ_tags, arg1_options) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.global_resource_production_obj) options.global_resource_production_obj = getGlobalResourceProductionObject();

		//Declare local instance variables
		var combined_resource_production_obj = {};

		for (var i = 0; i < civ_tags.length; i++)
			combined_resource_production_obj = mergeObjects(combined_resource_production_obj, options.global_resource_production_obj[civ_tags[i]]);

		//Return statement
		return combined_resource_production_obj;
	}

	function getCivilisationsIncreasedGrowthRateValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += getCivilisationIncreasedGrowthRate(civ_tags[i]);

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsIncreasedManpowerValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, getCivilisationIncreasedManpower(civ_tags[i]));
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsIncreasedTaxEfficiencyValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, getCivilisationIncreasedTaxEfficiency(civ_tags[i]));
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsInvestedInEconomyValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, getCivilisationInvestedInEconomy(civ_tags[i]));
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

	function getCivilisationsPopulationValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += getCivilisationPopulation(civ_tags[i]);

		//Return statement
		return comparison_sum;
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

	function getCivilisationsResearchPerMonthValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationResearchPerMonth(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsResearchedTechnologiesValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationResearchedTechnologies(civ_obj).length;
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
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