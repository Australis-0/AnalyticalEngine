//Initialise functions
{
	function getBooleanOperatorFromString (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Return statement
		if (string.startsWith("and_") || string == "and") return "and";
		if (string.startsWith("not_") || string == "not") return "not";
		if (string.startsWith("or_") || string == "or") return "or";
		if (string.startsWith("xor_") || string == "xor") return "xor";
	}

	/**
	 * parseLimit() - Parses a limit scope and returns true/false.
	 * @param {Object} arg0_scope
	 * @param {Object} [arg1_options]
	 *  @param {number} [arg1_options.depth=0] - Optimisation parameter. The current recursive depth.
	 *  @param {Array<Object>} [arg1_options.scopes]
	 *
	 * @returns {Object<{boolean: boolean, scopes: Array<Object> }>}
	 */
	function parseLimit (arg0_scope, arg1_options) { //[WIP] - Finish function body
		//Convert from parameters
		var scope = arg0_scope;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (options.depth == undefined) options.depth = 0;
			options.depth++;
		if (!options.options) options.options = {}; //Stores all local variables
		if (!options.scopes) options.scopes = [{ scope_type: "global" }];

		//Declare local instance variables
		var all_recursive_scope_keys = getAllObjectKeys(scope, { include_parent_keys: true });
		var all_scope_keys = Object.keys(scope);
		var last_scope_obj = JSON.parse(JSON.stringify(options.scopes[options.scopes.length - 1]));
		var local_checks = 0;

		for (var i = 0; i < all_recursive_scope_keys.length; i++) {
			var local_value = getList(getObjectKey(scope, all_recursive_scope_keys[i]));

			//Test to make sure that local_value[0] is indeed a string; and that local_value is 1 long
			if (local_value.length == 1)
				if (typeof local_value[0] == "string") {
					//Direct variable substitution if detected as valid
					if (getObjectKey(options.options, local_value[0]) != undefined)
						setObjectKey(scope, all_recursive_scope_keys[i], getObjectKey(options.options, local_value[0]));
					for (var x = 0; x < options.scopes.length; x++)
						if (getObjectKey(options.scopes[x], local_value[0]) != undefined)
							setObjectKey(scope, all_recursive_scope_keys[i], getObjectKey(options.scopes[x], local_value[0]));

					//If the string is more complex; attempt to use parseVariableString() on it
					try {
						options.options.ignore_errors = true;
						setObjectKey(scope, all_recursive_scope_keys[i], parseVariableString(local_value[0], options.options));
						delete options.options.ignore_errors;
					} catch (e) {
						console.log(scope, all_recursive_scope_keys[i], local_value[0], options.options);
						console.log(e.message);
					}
				}
		}
		var suboptions = options.options;

		//Current .limit parser
		//Iterate over all_scope_keys, recursively parsing the scope whenever a subscope is encountered.
		for (var i = 0; i < all_scope_keys.length; i++) {
			var local_value = getList(scope[all_scope_keys[i]]);

			//Recursive parsers/scoping - [WIP] - Rework to handle object returns
			{
				if (all_scope_keys[i] == "limit" || all_scope_keys[i].startsWith("limit_")) {
					var new_options = JSON.parse(JSON.stringify(options));
					var parsed_limit = parseLimit(local_value[0], new_options);

					if (parsed_limit)
						if (parsed_limit.boolean)
							local_checks++;
				}

				//And/Not/Or/Xor parser
				var local_boolean_type = getBooleanOperatorFromString(all_scope_keys[i]);
				if (local_boolean_type) {
					var new_options = JSON.parse(JSON.stringify(options));
						new_options.operator = local_boolean_type;
					var parsed_limit = parseLimit(local_value[0], new_options);

					if (parsed_limit)
						if (parsed_limit.boolean)
							local_checks++;
				}

				//1. Global-Scope Conditions.
				{
					if (all_scope_keys[i] == "global" || all_scope_keys[i].startsWith("global_")) {
						var new_options = JSON.parse(JSON.stringify(options));
							new_options.operator = "and";
							new_options.scopes.push({ scope_type: "global" });
						var parsed_limit = parseLimit(local_value[0], new_options);

						if (parsed_limit)
							if (parsed_limit.boolean)
								local_checks++;
					}

					if (all_scope_keys[i] == "all_resources" || all_scope_keys[i].startsWith("all_resources_")) {
						var new_options = JSON.parse(JSON.stringify(options));
							new_options.operator = "and";
							new_options.scopes.push({ scope_type: "resource", resource_types: getAllResources({ return_keys: true }) });
						var parsed_limit = parseLimit(local_value[0], new_options);

						if (parsed_limit)
							if (parsed_limit.boolean)
								local_checks++;
					}
				}
			}

			//Same-scope conditions
			{
				//1. Global-Scope Conditions.
				{
					if (all_scope_keys[i] == "any_civilisation_does_not_exist")
						if (anyCivilisationDoesNotExist(local_value))
							local_checks++;
					if (all_scope_keys[i] == "any_civilisation_exists")
						if (anyCivilisationExists(local_value))
							local_checks++;
					if (all_scope_keys[i] == "civilisation_does_not_exist")
						if (civilisationDoesNotExist(local_value))
							local_checks++;
					if (all_scope_keys[i] == "civilisation_exists")
						if (civilisationExists(local_value))
							local_checks++;

					if (all_scope_keys[i] == "is_date")
						if (isDate(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_date_greater_than")
						if (isDateGreaterThan(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_date_geq")
						if (isDateGEQ(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_date_less_than")
						if (isDateLessThan(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_date_leq")
						if (isDateLEQ(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_hour")
						if (isHour(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_hour_greater_than")
						if (isHourGreaterThan(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_hour_geq")
						if (isHourGEQ(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_hour_leq")
						if (isHourLEQ(local_value[0]))
							local_checks++;
					if (all_scope_keys[i] == "is_hour_less_than")
						if (isHourLessThan(local_value[0]))
							local_checks++;
				}
			}
		}

		//1.1. International Organisation Scope Conditions
		//1.2. Resource Scope Conditions; check for all last_scope_obj.resource_types
		if (last_scope_obj.scope_type == "resource") {
			var resource_checks = [];
			var total_resource_checks = 0;
			var valid_resources = [];

			for (var i = 0; i < last_scope_obj.resource_types.length; i++)
				resource_checks.push(0);

			//1. Iterate over all_scope_keys and fetch resource_checks
			for (var i = 0; i < all_scope_keys.length; i++) {
				var local_value = getList(scope[all_scope_keys[i]]);

				//price_is
				if (all_scope_keys[i] == "price_is" || all_scope_keys[i] == "price_is_equal_to") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (priceIs(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "price_is_geq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (priceIsGEQ(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "price_is_greater_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (priceIsGreaterThan(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "price_is_leq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (priceIsLEQ(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "price_is_less_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (priceIsLessThan(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}

				//civilisations_have_market_share
				if (all_scope_keys[i] == "civilisations_have_market_share" || all_scope_keys[i] == "civilisations_have_market_share_equal_to") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (civilisationsHaveMarketShare(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "civilisations_have_market_share_geq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (civilisationsHaveMarketShareGEQ(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "civilisations_have_market_share_greater_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (civilisationsHaveMarketShareGreaterThan(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "civilisations_have_market_share_leq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (civilisationsHaveMarketShareLEQ(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "civilisations_have_market_share_less_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (civilisationsHaveMarketShareLessThan(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				//global_production_is
				if (all_scope_keys[i] == "global_production_is" || all_scope_keys[i] == "global_production_is_equal_to") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (globalProductionIs(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "global_production_is_geq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (globalProductionIsGEQ(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "global_production_is_greater_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (globalProductionIsGreaterThan(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "global_production_is_leq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (globalProductionIsLEQ(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "global_production_is_less_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (globalProductionIsLessThan(last_scope_obj.resource_types[x], local_value[0]))
							resource_checks[x]++;
				}
				//production_is
				if (all_scope_keys[i] == "production_is" || all_scope_keys[i] == "production_is_equal_to") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (productionIs(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "production_is_geq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (productionIsGEQ(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "production_is_greater_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (productionIsGreaterThan(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "production_is_leq") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (productionIsLEQ(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
				if (all_scope_keys[i] == "production_is_less_than") {
					total_resource_checks++;
					for (var x = 0; x < last_scope_obj.resource_types.length; x++)
						if (productionIsLessThan(local_value[0].civilisations, last_scope_obj.resource_types[x], local_value[0].value))
							resource_checks[x]++;
				}
			}

			//KEEP AT BOTTOM OF LOCAL SCOPE!
			//2. Iterate over all resource_checks to append valid checks to valid_resources
			for (var i = 0; i < resource_checks.length; i++) {
				var local_resource_name = last_scope_obj.resource_types[i];
				var local_return_true = false;

				if (options.operator == "and")
					if (resource_checks[i] >= total_resource_checks) local_return_true = true;
				if (options.operator == "not")
					if (resource_checks[i] == 0) local_return_true = true;
				if (options.operator == "or")
					if (resource_checks[i] > 0) local_return_true = true;
				if (options.operator == "xor")
					if (resource_checks[i] == 1) local_return_true = true;

				if (local_return_true)
					valid_resources.push(local_resource_name);
			}

			//3. Append to options.scopes
			options.scopes.push({
				scope_type: "resource",
				resource_types: valid_resources
			});
		}

		//2. Civilisation Scope Conditions; check for all last_scope_obj.civ_tags
		if (last_scope_obj.scope_type == "civilisation") { //[WIP] - Finish function body
			var civilisation_checks = [];
			var total_civilisation_checks = 0;
			var valid_civ_tags = [];

			for (var i = 0; i < last_scope_obj.civ_tags.length; i++)
				civilisation_checks.push(0);

			//1. Iterate over all_scope_keys and fetch civilisation_checks
			for (var i = 0; i < all_scope_keys.length; i++) { //[WIP] - Add List<String, CivTag> parsing; check typeof local_value[0]
				var local_value = getList(scope[all_scope_keys[i]]);

				//<building_category_key>_buildings_constructed
				if (all_scope_keys[i].endsWith("_buildings_constructed") || all_scope_keys[i].endsWith("_constructed_is_equal_to")) {
					var local_building_category_key = all_scope_keys[i].replace("_buildings_constructed", "")
						.replace("_constructed_is_equal_to", "");
					var old_local_value;

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") { //This logic needs to be changed because if an array of CivTags is already provided, it will be local_value that is an array
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingCategoryValue(local_value, local_building_category_key);
					}
					if (civilisationBuildingCategoryConstructedIs(last_scope_obj.civ_tags, local_building_category_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_geq")) {
					var local_building_category_key = all_scope_keys[i].replace("_constructed_is_geq", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingCategoryValue(local_value, local_building_category_key);
					}
					if (civilisationBuildingCategoryConstructedIsGEQ(last_scope_obj.civ_tags, local_building_category_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_greater_than")) {
					var local_building_category_key = all_scope_keys[i].replace("_constructed_is_greater_than", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingCategoryValue(local_value, local_building_category_key);
					}
					if (civilisationBuildingCategoryConstructedIsGreaterThan(last_scope_obj.civ_tags, local_building_category_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;

				}
				if (all_scope_keys[i].endsWith("_constructed_is_leq")) {
					var local_building_category_key = all_scope_keys[i].replace("_constructed_is_leq", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingCategoryValue(local_value, local_building_category_key);
					}
					if (civilisationBuildingCategoryConstructedIsLEQ(last_scope_obj.civ_tags, local_building_category_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;

				}
				if (all_scope_keys[i].endsWith("_constructed_is_less_than")) {
					var local_building_category_key = all_scope_keys[i].replace("_constructed_is_less_than", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingCategoryValue(local_value, local_building_category_key);
					}
					if (civilisationBuildingCategoryConstructedIsLessThan(last_scope_obj.civ_tags, local_building_category_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;

				}

				//<building_key>_constructed
				if (all_scope_keys[i].endsWith("_constructed") || all_scope_keys[i].endsWith("_constructed_is_equal_to")) {
					var local_building_key = all_scope_keys[i].replace("_constructed", "")
						.replace("_constructed_is_equal_to", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingValue(local_values, local_building_key);
					}
					if (civilisationBuildingConstructedIs(last_scope_obj.civ_tags, local_building_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_geq")) {
					var local_building_key = all_scope_keys[i].replace("_constructed_is_geq", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingValue(local_value, local_building_key);
					}
					if (civilisationBuildingConstructedIsGEQ(last_scope_obj.civ_tags, local_building_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_greater_than")) {
					var local_building_key = all_scope_keys[i].replace("_constructed_is_greater_than", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingValue(local_value, local_building_key);
					}
					if (civilisationBuildingConstructedIsGreaterThan(last_scope_obj.civ_tags, local_building_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_leq")) {
					var local_building_key = all_scope_keys[i].replace("_constructed_is_leq", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingValue(local_value, local_building_key);
					}
					if (civilisationBuildingConstructedIsLEQ(last_scope_obj.civ_tags, local_building_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i].endsWith("_constructed_is_less_than")) {
					var local_building_key = all_scope_keys[i].replace("_constructed_is_less_than", "");

					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsBuildingValue(local_value, local_building_key);
					}
					if (civilisationBuildingConstructedIsLessThan(last_scope_obj.civ_tags, local_building_key, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//unique_capital_buildings_constructed
				if (all_scope_keys[i] == "unique_capital_buildings_constructed" || all_scope_keys[i] == "unique_capital_buildings_constructed_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsUniqueCapitalBuildingsValue(local_value);
					}
					if (uniqueCapitalBuildingsConstructedIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "unique_capital_buildings_constructed_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsUniqueCapitalBuildingsValue(local_value);
					}
					if (uniqueCapitalBuildingsConstructedIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "unique_capital_buildings_constructed_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsUniqueCapitalBuildingsValue(local_value);
					}
					if (uniqueCapitalBuildingsConstructedIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "unique_capital_buildings_constructed_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsUniqueCapitalBuildingsValue(local_value);
					}
					if (uniqueCapitalBuildingsConstructedIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "unique_capital_buildings_constructed_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsUniqueCapitalBuildingsValue(local_value);
					}
					if (uniqueCapitalBuildingsConstructedIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//economy_income_is
				if (all_scope_keys[i] == "economy_income_is" || all_scope_keys[i] == "economy_income_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIncomeIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_income_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIncomeIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_income_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIncomeIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_income_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIncomeIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_income_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIncomeIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//economy_is
				if (all_scope_keys[i] == "economy_is" || all_scope_keys[i] == "economy_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsEconomyIncomeValue(local_value);
					}
					if (civilisationEconomyIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//economy_production_is
				if (all_scope_keys[i] == "economy_production_is" || all_scope_keys[i] == "economy_production_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsProductionValue(local_value);
					}
					if (civilisationEconomyProductionIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_production_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsProductionValue(local_value);
					}
					if (civilisationEconomyProductionIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_production_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsProductionValue(local_value);
					}
					if (civilisationEconomyProductionIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_production_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsProductionValue(local_value);
					}
					if (civilisationEconomyProductionIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_production_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsProductionValue(local_value);
					}
					if (civilisationEconomyProductionIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//economy_taxation_is
				if (all_scope_keys[i] == "economy_taxation_is" || all_scope_keys[i] == "economy_taxation_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTaxationValue(local_value);
					}
					if (civilisationEconomyTaxationIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_taxation_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTaxationValue(local_value);
					}
					if (civilisationEconomyTaxationIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_taxation_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTaxationValue(local_value);
					}
					if (civilisationEconomyTaxationIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_taxation_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTaxationValue(local_value);
					}
					if (civilisationEconomyTaxationIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_taxation_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTaxationValue(local_value);
					}
					if (civilisationEconomyTaxationIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//economy_total_income_is
				if (all_scope_keys[i] == "economy_total_income_is" || all_scope_keys[i] == "economy_total_income__is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTotalIncomeValue(local_value);
					}
					if (civilisationEconomyTotalIncomeIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_total_income__is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTotalIncomeValue(local_value);
					}
					if (civilisationEconomyTotalIncomeIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_total_income__is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTotalIncomeValue(local_value);
					}
					if (civilisationEconomyTotalIncomeIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_total_income__is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTotalIncomeValue(local_value);
					}
					if (civilisationEconomyTotalIncomeIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "economy_total_income__is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsTotalIncomeValue(local_value);
					}
					if (civilisationEconomyTotalIncomeIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}

				//money_is
				if (all_scope_keys[i] == "money_is" || all_scope_keys[i] == "money_is_equal_to") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsMoneyValue(local_value);
					}
					if (civilisationMoneyIs(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "money_is_geq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsMoneyValue(local_value);
					}
					if (civilisationMoneyIsGEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "money_is_greater_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsMoneyValue(local_value);
					}
					if (civilisationMoneyIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "money_is_leq") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsMoneyValue(local_value);
					}
					if (civilisationMoneyIsLEQ(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
				if (all_scope_keys[i] == "money_is_less_than") {
					total_civilisation_checks++;

					if (typeof local_value[0] == "string") {
						old_local_value = JSON.parse(JSON.stringify(local_value));
						local_value[0] = getCivilisationsMoneyValue(local_value);
					}
					if (civilisationMoneyIsLessThan(last_scope_obj.civ_tags, local_value[0]))
						civilisation_checks[x]++;
					if (old_local_value != undefined)
						local_value = old_local_value;
				}
 			}

			//KEEP AT BOTTOM OF LOCAL SCOPE!
			//2. Iterate over all civilisation_checks to append valid checks to valid_civ_tags
			for (var i = 0; i < civilisation_checks.length; i++) {
				var local_civ_tag = last_scope_obj.civ_tags[i];
				var local_return_true = false;

				if (options.operator == "and")
					if (civilisation_checks[i] >= total_civilisation_checks) local_return_true = true;
				if (options.operator == "not")
					if (civilisation_checks[i] == 0) local_return_true = true;
				if (options.operator == "or")
					if (civilisation_checks[i] > 0) local_return_true = true;
				if (options.operator == "xor")
					if (civilisation_checks[i] == 1) local_return_true = true;

				if (local_return_true)
					valid_civ_tags.push(local_civ_tag);
			}

			//3. Append to options.scopes
			options.scopes.push({
				scope_type: "civilisation",
				civ_tags: valid_civ_tags
			});
		}

		//Return statement; AND/NOT/OR/XOR handler
		var return_true = false;

		if (options.operator == "and")
			if (local_checks >= all_scope_keys.length) return_true = true;
		if (options.operator == "not")
			if (local_checks == 0) return_true = true;
		if (options.operator == "or")
			if (local_checks > 0) return_true = true;
		if (options.operator == "xor")
			if (local_checks == 1) return_true = true;

		if (return_true)
			return {
				boolean: true,
				scopes: options.scopes
			};
	}
}