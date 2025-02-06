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

				//Economy (Buildings).
				{
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

					//building_group_<building_group_key>_constructed
					if (all_scope_keys[i].startsWith("building_group_") && (all_scope_keys[i].endsWith("_constructed_is") || all_scope_keys[i].endsWith("_constructed_is_equal_to"))) {
						var local_building_group_key = all_scope_keys[i].replace("_constructed_is_equal_to", "")
							.replace("_constructed_is", "")
							.replace("building_group_");

						total_civilisation_checks++;

						if (civilisationBuildingGroupConstructedIs(last_scope_obj.civ_tags, local_building_group_key, local_value))
							civilisation_checks[x]++;
					}
					if (all_scope_keys[i].startsWith("building_group_") && all_scope_keys[i].endsWith("_constructed_is_geq")) {
						var local_building_group_key = all_scope_keys[i].replace("_constructed_is_geq", "")
						.replace("building_group_");

						total_civilisation_checks++;

						if (civilisationBuildingGroupConstructedIsGEQ(last_scope_obj.civ_tags, local_building_group_key, local_value))
							civilisation_checks[x]++;
					}
					if (all_scope_keys[i].startsWith("building_group_") && all_scope_keys[i].endsWith("_constructed_is_greater_than")) {
						var local_building_group_key = all_scope_keys[i].replace("_constructed_is_greater_than", "")
						.replace("building_group_");

						total_civilisation_checks++;

						if (civilisationBuildingGroupConstructedIsGreaterThan(last_scope_obj.civ_tags, local_building_group_key, local_value))
							civilisation_checks[x]++;
					}
					if (all_scope_keys[i].startsWith("building_group_") && all_scope_keys[i].endsWith("_constructed_is_leq")) {
						var local_building_group_key = all_scope_keys[i].replace("_constructed_is_leq", "")
						.replace("building_group_");

						total_civilisation_checks++;

						if (civilisationBuildingGroupConstructedIsLEQ(last_scope_obj.civ_tags, local_building_group_key, local_value))
							civilisation_checks[x]++;
					}
					if (all_scope_keys[i].startsWith("building_group_") && all_scope_keys[i].endsWith("_constructed_is_less_than")) {
						var local_building_group_key = all_scope_keys[i].replace("_constructed_is_less_than", "")
						.replace("building_group_");

						total_civilisation_checks++;

						if (civilisationBuildingGroupConstructedIsLessThan(last_scope_obj.civ_tags, local_building_group_key, local_value))
							civilisation_checks[x]++;
					}

					//<building_key>_constructed
					if (all_scope_keys[i].endsWith("_constructed") || all_scope_keys[i].endsWith("_constructed_is_equal_to")) {
						var local_building_key = all_scope_keys[i].replace("_constructed", "")
						.replace("_constructed_is_equal_to", "");

						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBuildingValue(local_value, local_building_key);
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
				}

				//Economy (Civilisation).
				{
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
					if (all_scope_keys[i] == "economy_total_income_is" || all_scope_keys[i] == "economy_total_income_is_equal_to") {
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
					if (all_scope_keys[i] == "economy_total_income_is_geq") {
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
					if (all_scope_keys[i] == "economy_total_income_is_greater_than") {
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
					if (all_scope_keys[i] == "economy_total_income_is_leq") {
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
					if (all_scope_keys[i] == "economy_total_income_is_less_than") {
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

				//Economy (Goods/Resources).
				{
					//has_resource
					if (all_scope_keys[i] == "has_resource") {
						total_civilisation_checks++;

						if (civilisationHasResource(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}

					//has_<resource_key>_provinces
					if (all_scope_keys[i].startsWith("has_") && all_scope_keys[i].endsWith("_provinces")) {
						var resource_name = all_scope_keys[i].replace("has_", "")
						.replace("_provinces", "");
						var resource_type = getResource(resource_name, { return_key: true });

						if (resource_type) {
							total_civilisation_checks++;
							if (civilisationHasResourceProvinces(last_scope_obj.civ_tags, {
								resources: resource_type,
								value: local_value[0]
							}))
								civilisation_checks[x]++;
						}
					}

					//has_resource_provinces
					if (all_scope_keys[i] == "has_resource_provinces")
						if (typeof local_value == "object") {
							total_civilisation_checks++;
							if (civilisationHasResourceProvinces(last_scope_obj.civ_tags, {
								provinces: local_value[0].provinces,
								resources: local_value[0].resources,
								value: local_value[0].value
							}))
								civilisation_checks[x]++;
						}

					//is_largest_producer
					if (all_scope_keys[i] == "is_largest_producer") {
						total_civilisation_checks++;
						if (isLargestProducer(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}

					//is_largest_producer_of_number_of_resources
					if (all_scope_keys[i] == "is_largest_producer_of_number_of_resources") {
						total_civilisation_checks++;
						if (isLargestProducerOfNumberOfResources(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
					}

					//largest_resource_produced_is
					if (all_scope_keys[i] == "largest_resource_produced_is") {
						total_civilisation_checks++;
						if (largestResourceProducedIs(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}

					//resource_production_is
					if (all_scope_keys[i] == "resource_production_is" || all_scope_keys[i] == "resource_production_is_equal_to")
						if (typeof local_value[0] == "object") {
							civilisation_checks[x]++;

							if (resourceProductionIs(local_value[0].tag, last_scope_obj.resource_types, local_value[0].value))
								civilisation_checks[x]++;
						}
					if (all_scope_keys[i] == "resource_production_is_geq")
						if (typeof local_value[0] == "object") {
							civilisation_checks[x]++;

							if (resourceProductionIsGEQ(local_value[0].tag, last_scope_obj.resource_types, local_value[0].value))
								civilisation_checks[x]++;
						}
					if (all_scope_keys[i] == "resource_production_is_greater_than")
						if (typeof local_value[0] == "object") {
							civilisation_checks[x]++;

							if (resourceProductionIsGreaterThan(local_value[0].tag, last_scope_obj.resource_types, local_value[0].value))
								civilisation_checks[x]++;
						}
					if (all_scope_keys[i] == "resource_production_is_leq")
						if (typeof local_value[0] == "object") {
							civilisation_checks[x]++;

							if (resourceProductionIsLEQ(local_value[0].tag, last_scope_obj.resource_types, local_value[0].value))
								civilisation_checks[x]++;
						}
					if (all_scope_keys[i] == "resource_production_is_less_than")
						if (typeof local_value[0] == "object") {
							civilisation_checks[x]++;

							if (resourceProductionIsLessThan(local_value[0].tag, last_scope_obj.resource_types, local_value[0].value))
								civilisation_checks[x]++;
						}
				}

				//Economy (Pops).
				{
					//average_growth_rate_is
					if (all_scope_keys[i] == "average_growth_rate_is" || all_scope_keys[i] == "average_growth_rate_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAverageGrowthRateValue(local_value);
						}
						if (civilisationAverageGrowthRateIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "average_growth_rate_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAverageGrowthRateValue(local_value);
						}
						if (civilisationAverageGrowthRateIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "average_growth_rate_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAverageGrowthRateValue(local_value);
						}
						if (civilisationAverageGrowthRateIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "average_growth_rate_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAverageGrowthRateValue(local_value);
						}
						if (civilisationAverageGrowthRateIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "average_growth_rate_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAverageGrowthRateValue(local_value);
						}
						if (civilisationAverageGrowthRateIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//increased_growth_rate_is
					if (all_scope_keys[i] == "increased_growth_rate_is" || all_scope_keys[i] == "increased_growth_rate_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedGrowthRateValue(local_value);
						}
						if (civilisationIncreasedGrowthRateIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_growth_rate_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedGrowthRateValue(local_value);
						}
						if (civilisationIncreasedGrowthRateIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_growth_rate_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedGrowthRateValue(local_value);
						}
						if (civilisationIncreasedGrowthRateIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_growth_rate_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedGrowthRateValue(local_value);
						}
						if (civilisationIncreasedGrowthRateIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_growth_rate_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedGrowthRateValue(local_value);
						}
						if (civilisationIncreasedGrowthRateIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//population_is
					if (all_scope_keys[i] == "population_is" || all_scope_keys[i] == "population_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPopulationValue(local_value);
						}
						if (civilisationPopulationIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "population_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPopulationValue(local_value);
						}
						if (civilisationPopulationIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "population_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPopulationValue(local_value);
						}
						if (civilisationPopulationIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "population_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPopulationValue(local_value);
						}
						if (civilisationPopulationIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "population_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPopulationValue(local_value);
						}
						if (civilisationPopulationIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
				}

				//Economy (Provinces).
				{
					//developed_infrastructure_is
					if (all_scope_keys[i] == "developed_infrastructure_is" || all_scope_keys[i] == "developed_infrastructure_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsDevelopedInfrastructureValue(local_value);
						}
						if (civilisationDevelopedInfrastructureIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "developed_infrastructure_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsDevelopedInfrastructureValue(local_value);
						}
						if (civilisationDevelopedInfrastructureIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "developed_infrastructure_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsDevelopedInfrastructureValue(local_value);
						}
						if (civilisationDevelopedInfrastructureIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "developed_infrastructure_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsDevelopedInfrastructureValue(local_value);
						}
						if (civilisationDevelopedInfrastructureIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "developed_infrastructure_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsDevelopedInfrastructureValue(local_value);
						}
						if (civilisationDevelopedInfrastructureIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//increased_manpower_is
					if (all_scope_keys[i] == "increased_manpower_is" || all_scope_keys[i] == "increased_manpower_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedManpowerValue(local_value);
						}
						if (civilisationIncreasedManpowerIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_manpower_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedManpowerValue(local_value);
						}
						if (civilisationIncreasedManpowerIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_manpower_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedManpowerValue(local_value);
						}
						if (civilisationIncreasedManpowerIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_manpower_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedManpowerValue(local_value);
						}
						if (civilisationIncreasedManpowerIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_manpower_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedManpowerValue(local_value);
						}
						if (civilisationIncreasedManpowerIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//increased_tax_efficiency_is
					if (all_scope_keys[i] == "increased_tax_efficiency_is" || all_scope_keys[i] == "increased_tax_efficiency_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedTaxEfficiencyValue(local_value);
						}
						if (civilisationIncreasedTaxEfficiencyIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_tax_efficiency_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedTaxEfficiencyValue(local_value);
						}
						if (civilisationIncreasedTaxEfficiencyIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_tax_efficiency_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedTaxEfficiencyValue(local_value);
						}
						if (civilisationIncreasedTaxEfficiencyIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_tax_efficiency_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedTaxEfficiencyValue(local_value);
						}
						if (civilisationIncreasedTaxEfficiencyIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "increased_tax_efficiency_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsIncreasedTaxEfficiencyValue(local_value);
						}
						if (civilisationIncreasedTaxEfficiencyIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//invested_in_economy_is
					if (all_scope_keys[i] == "invested_in_economy_is" || all_scope_keys[i] == "invested_in_economy_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsInvestedInEconomyValue(local_value);
						}
						if (civilisationInvestedInEconomyIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "invested_in_economy_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsInvestedInEconomyValue(local_value);
						}
						if (civilisationInvestedInEconomyIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "invested_in_economy_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsInvestedInEconomyValue(local_value);
						}
						if (civilisationInvestedInEconomyIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "invested_in_economy_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsInvestedInEconomyValue(local_value);
						}
						if (civilisationInvestedInEconomyIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "invested_in_economy_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsInvestedInEconomyValue(local_value);
						}
						if (civilisationInvestedInEconomyIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
				}

				//Economy (Technology).
				{
					//researched_technologies_is
					if (all_scope_keys[i] == "researched_technologies_is" || all_scope_keys[i] == "researched_technologies_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchedTechnologiesValue(local_value);
						}
						if (civilisationResearchedTechnologiesIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "researched_technologies_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchedTechnologiesValue(local_value);
						}
						if (civilisationResearchedTechnologiesIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "researched_technologies_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchedTechnologiesValue(local_value);
						}
						if (civilisationResearchedTechnologiesIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "researched_technologies_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchedTechnologiesValue(local_value);
						}
						if (civilisationResearchedTechnologiesIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "researched_technologies_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchedTechnologiesValue(local_value);
						}
						if (civilisationResearchedTechnologiesIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//research_per_month_is
					if (all_scope_keys[i] == "research_per_month_is" || all_scope_keys[i] == "research_per_month_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchPerMonthValue(local_value);
						}
						if (civilisationResearchPerMonthIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "research_per_month_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchPerMonthValue(local_value);
						}
						if (civilisationResearchPerMonthIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "research_per_month_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchPerMonthValue(local_value);
						}
						if (civilisationResearchPerMonthIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "research_per_month_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchPerMonthValue(local_value);
						}
						if (civilisationResearchPerMonthIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "research_per_month_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsResearchPerMonthValue(local_value);
						}
						if (civilisationResearchPerMonthIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//technology_is_researched
					if (all_scope_keys[i] == "technology_is_researched") {
						total_civilisation_checks++;

						if (civilisationTechnologyIsResearched(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
					}
				}

				//Military.
				{
					//battle_width_is
					if (all_scope_keys[i] == "battle_width_is" || all_scope_keys[i] == "battle_width_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBattleWidthValue(local_value);
						}
						if (civilisationBattleWidthIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "battle_width_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBattleWidthValue(local_value);
						}
						if (civilisationBattleWidthIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "battle_width_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBattleWidthValue(local_value);
						}
						if (civilisationBattleWidthIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "battle_width_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBattleWidthValue(local_value);
						}
						if (civilisationBattleWidthIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "battle_width_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsBattleWidthValue(local_value);
						}
						if (civilisationBattleWidthIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//manpower_is
					if (all_scope_keys[i] == "manpower_is" || all_scope_keys[i] == "manpower_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerValue(local_value);
						}
						if (civilisationManpowerIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerValue(local_value);
						}
						if (civilisationManpowerIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerValue(local_value);
						}
						if (civilisationManpowerIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerValue(local_value);
						}
						if (civilisationManpowerIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerValue(local_value);
						}
						if (civilisationManpowerIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//manpower_percentage_is
					if (all_scope_keys[i] == "manpower_percentage_is" || all_scope_keys[i] == "manpower_percentage_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerPercentageValue(local_value);
						}
						if (civilisationManpowerPercentageIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_percentage_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerPercentageValue(local_value);
						}
						if (civilisationManpowerPercentageIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_percentage_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerPercentageValue(local_value);
						}
						if (civilisationManpowerPercentageIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_percentage_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerPercentageValue(local_value);
						}
						if (civilisationManpowerPercentageIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "manpower_percentage_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsManpowerPercentageValue(local_value);
						}
						if (civilisationManpowerPercentageIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//max_manpower_is
					if (all_scope_keys[i] == "max_manpower_is" || all_scope_keys[i] == "max_manpower_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsMaximumManpowerValue(local_value);
						}
						if (civilisationMaxManpowerIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "max_manpower_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsMaximumManpowerValue(local_value);
						}
						if (civilisationMaxManpowerIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "max_manpower_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsMaximumManpowerValue(local_value);
						}
						if (civilisationMaxManpowerIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "max_manpower_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsMaximumManpowerValue(local_value);
						}
						if (civilisationMaxManpowerIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "max_manpower_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsMaximumManpowerValue(local_value);
						}
						if (civilisationMaxManpowerIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//regiments_is
					if (all_scope_keys[i] == "regiments_is" || all_scope_keys[i] == "regiments_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsValue(local_value);
						}
						if (civilisationRegimentsIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsValue(local_value);
						}
						if (civilisationRegimentsIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsValue(local_value);
						}
						if (civilisationRegimentsIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsValue(local_value);
						}
						if (civilisationRegimentsIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsValue(local_value);
						}
						if (civilisationRegimentsIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//regiments_limit_is
					if (all_scope_keys[i] == "regiments_limit_is" || all_scope_keys[i] == "regiments_limit_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsLimitValue(local_value);
						}
						if (civilisationRegimentsLimitIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_limit_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsLimitValue(local_value);
						}
						if (civilisationRegimentsLimitIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_limit_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsLimitValue(local_value);
						}
						if (civilisationRegimentsLimitIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_limit_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsLimitValue(local_value);
						}
						if (civilisationRegimentsLimitIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_limit_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsLimitValue(local_value);
						}
						if (civilisationRegimentsLimitIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//regiments_over_limit_is
					if (all_scope_keys[i] == "regiments_over_limit_is" || all_scope_keys[i] == "regiments_over_limit_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsOverLimitValue(local_value);
						}
						if (civilisationRegimentsOverLimitIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_over_limit_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsOverLimitValue(local_value);
						}
						if (civilisationRegimentsOverLimitIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_over_limit_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsOverLimitValue(local_value);
						}
						if (civilisationRegimentsOverLimitIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_over_limit_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsOverLimitValue(local_value);
						}
						if (civilisationRegimentsOverLimitIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "regiments_over_limit_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRegimentsOverLimitValue(local_value);
						}
						if (civilisationRegimentsOverLimitIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
				}

				//Politics (Internal).
				{
					//has_government_type
					if (all_scope_keys[i] == "has_government_type") {
						total_civilisation_checks++;

						if (hasGovernmentType(last_scope_obj.civ_tags, local_value))
							civilisation_checks++;
					}
					//has_religion
					if (all_scope_keys[i] == "has_religion") {
						total_civilisation_checks++;

						if (hasReligion(last_scope_obj.civ_tags, local_value))
							civilisation_checks++;
					}
					//is_not_government_type
					if (all_scope_keys[i] == "is_not_government_type") {
						total_civilisation_checks++;

						if (isNotGovernmentType(last_scope_obj.civ_tags, local_value))
							civilisation_checks++;
					}
					//is_not_religion
					if (all_scope_keys[i] == "is_not_religion") {
						total_civilisation_checks++;

						if (isNotReligion(last_scope_obj.civ_tags, local_value))
							civilisation_checks++;
					}

					//advisor_skill_is
					if (all_scope_keys[i] == "advisor_skill_is" || all_scope_keys[i] == "advisor_skill_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAdvisorSkillValue(local_value);
						}
						if (civilisationAdvisorSkillIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "advisor_skill_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAdvisorSkillValue(local_value);
						}
						if (civilisationAdvisorSkillIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "advisor_skill_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAdvisorSkillValue(local_value);
						}
						if (civilisationAdvisorSkillIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "advisor_skill_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAdvisorSkillValue(local_value);
						}
						if (civilisationAdvisorSkillIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "advisor_skill_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAdvisorSkillValue(local_value);
						}
						if (civilisationAdvisorSkillIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//legacy_is
					if (all_scope_keys[i] == "legacy_is" || all_scope_keys[i] == "legacy_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyValue(local_value);
						}
						if (civilisationLegacyIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyValue(local_value);
						}
						if (civilisationLegacyIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyValue(local_value);
						}
						if (civilisationLegacyIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyValue(local_value);
						}
						if (civilisationLegacyIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyValue(local_value);
						}
						if (civilisationLegacyIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//legacy_per_month_is
					if (all_scope_keys[i] == "legacy_per_month_is" || all_scope_keys[i] == "legacy_per_month_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyPerMonthValue(local_value);
						}
						if (civilisationLegacyPerMonthIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_per_month_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyPerMonthValue(local_value);
						}
						if (civilisationLegacyPerMonthIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_per_month_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyPerMonthValue(local_value);
						}
						if (civilisationLegacyPerMonthIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_per_month_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyPerMonthValue(local_value);
						}
						if (civilisationLegacyPerMonthIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "legacy_per_month_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsLegacyPerMonthValue(local_value);
						}
						if (civilisationLegacyPerMonthIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//prestige_is
					if (all_scope_keys[i] == "prestige_is" || all_scope_keys[i] == "prestige_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationPrestigeIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "prestige_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationPrestigeIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "prestige_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationPrestigeIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "prestige_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationPrestigeIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "prestige_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationPrestigeIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//recruited_advisors_is
					if (all_scope_keys[i] == "recruited_advisors_is" || all_scope_keys[i] == "recruited_advisors_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationRecruitedAdvisorsIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "recruited_advisors_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationRecruitedAdvisorsIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "recruited_advisors_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationRecruitedAdvisorsIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "recruited_advisors_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationRecruitedAdvisorsIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "recruited_advisors_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsPrestigeValue(local_value);
						}
						if (civilisationRecruitedAdvisorsIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//unlocked_advantages_is
					if (all_scope_keys[i] == "unlocked_advantages_is" || all_scope_keys[i] == "unlocked_advantages_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedAdvantagesValue(local_value);
						}
						if (civilisationUnlockedAdvantagesIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_advantages_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedAdvantagesValue(local_value);
						}
						if (civilisationUnlockedAdvantagesIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_advantages_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedAdvantagesValue(local_value);
						}
						if (civilisationUnlockedAdvantagesIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_advantages_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedAdvantagesValue(local_value);
						}
						if (civilisationUnlockedAdvantagesIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_advantages_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedAdvantagesValue(local_value);
						}
						if (civilisationUnlockedAdvantagesIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//unlocked_legacies_is
					if (all_scope_keys[i] == "unlocked_legacies_is" || all_scope_keys[i] == "unlocked_legacies_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedLegaciesValue(local_value);
						}
						if (civilisationUnlockedLegaciesIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_legacies_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedLegaciesValue(local_value);
						}
						if (civilisationUnlockedLegaciesIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_legacies_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedLegaciesValue(local_value);
						}
						if (civilisationUnlockedLegaciesIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_legacies_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedLegaciesValue(local_value);
						}
						if (civilisationUnlockedLegaciesIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "unlocked_legacies_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsUnlockedLegaciesValue(local_value);
						}
						if (civilisationUnlockedLegaciesIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
				}

				//Politics (External [Diplomacy]).
				{
					//is_ai
					if (all_scope_keys[i] == "is_ai") {
						total_civilisation_checks++;

						if (isAI(local_value))
							civilisation_checks[x]++;
					}
					//is_civ
					if (all_scope_keys[i] == "is_civ") {
						total_civilisation_checks++;

						if (isCiv(local_value))
							civilisation_checks[x]++;
					}
					//is_player
					if (all_scope_keys[i] == "is_player") {
						total_civilisation_checks++;

						if (isPlayer(local_value))
							civilisation_checks[x]++;
					}

					//aggressive_expansion_is
					if (all_scope_keys[i] == "aggressive_expansion_is" || all_scope_keys[i] == "aggressive_expansion_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAggressiveExpansionValue(local_value);
						}
						if (civilisationAggressiveExpansionIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "aggressive_expansion_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAggressiveExpansionValue(local_value);
						}
						if (civilisationAggressiveExpansionIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "aggressive_expansion_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAggressiveExpansionValue(local_value);
						}
						if (civilisationAggressiveExpansionIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "aggressive_expansion_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAggressiveExpansionValue(local_value);
						}
						if (civilisationAggressiveExpansionIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "aggressive_expansion_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAggressiveExpansionValue(local_value);
						}
						if (civilisationAggressiveExpansionIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//at_war
					if (all_scope_keys[i] == "at_war") {
						total_civilisation_checks++;

						if (civilisationAtWar(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
					}

					//at_war_days_is
					if (all_scope_keys[i] == "at_war_days_is" || all_scope_keys[i] == "at_war_days_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAtWarDaysValue(local_value);
						}
						if (civilisationAtWarDaysIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "at_war_days_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAtWarDaysValue(local_value);
						}
						if (civilisationAtWarDaysIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "at_war_days_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAtWarDaysValue(local_value);
						}
						if (civilisationAtWarDaysIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "at_war_days_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAtWarDaysValue(local_value);
						}
						if (civilisationAtWarDaysIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "at_war_days_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsAtWarDaysValue(local_value);
						}
						if (civilisationAtWarDaysIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_alliance
					if (all_scope_keys[i] == "has_alliance") {
						total_civilisation_checks++;

						if (hasAlliance(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//has_defensive_pact
					if (all_scope_keys[i] == "has_defensive_pact") {
						total_civilisation_checks++;

						if (hasDefensivePact(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//has_guarantee
					if (all_scope_keys[i] == "has_guarantee") {
						total_civilisation_checks++;

						if (hasGuarantee(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//has_military_access
					if (all_scope_keys[i] == "has_military_access") {
						total_civilisation_checks++;

						if (hasMilitaryAccess(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//has_no_allies
					if (all_scope_keys[i] == "has_no_allies") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (hasNoAllies(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!hasNoAllies(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//has_no_defensive_pacts
					if (all_scope_keys[i] == "has_no_defensive_pacts") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (hasNoDefensivePact(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!hasNoDefensivePact(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//has_no_guarantors
					if (all_scope_keys[i] == "has_no_guarantors") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (hasNoGuarantors(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!hasNoGuarantors(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//has_no_non_aggression_pacts
					if (all_scope_keys[i] == "has_no_non_aggression_pacts") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (hasNoNonAggressionPacts(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!hasNoNonAggressionPacts(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//has_no_truces
					if (all_scope_keys[i] == "has_no_truces") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (hasNoTruces(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!hasNoTruces(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//has_truce
					if (all_scope_keys[i] == "has_truce") {
						total_civilisation_checks++;

						if (hasTruce(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//is_at_war_with
					if (all_scope_keys[i] == "is_at_war_with") {
						total_civilisation_checks++;

						if (isAtWarWith(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//is_guaranteeing
					if (all_scope_keys[i] == "is_guaranteeing") {
						total_civilisation_checks++;

						if (isGuaranteeing(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}
					//is_not_allied
					if (all_scope_keys[i] == "is_not_allied") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (isNotAllied(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!isNotAllied(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//is_not_at_war
					if (all_scope_keys[i] == "is_not_at_war") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (isNotAtWar(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!isNotAtWar(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//is_not_rival
					if (all_scope_keys[i] == "is_not_rival") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (isNotRival(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!isNotRival(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//is_rival
					if (all_scope_keys[i] == "is_rival") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (isNotAtWar(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!isNotAtWar(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//is_vassal
					if (all_scope_keys[i] == "is_vassal") {
						total_civilisation_checks++;

						if (local_value[0]) {
							if (isVassal(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						} else {
							if (!isVassal(last_scope_obj.civ_tags))
								civilisation_checks[x]++;
						}
					}
					//is_vassal_of
					if (all_scope_keys[i] == "is_vassal_of") {
						total_civilisation_checks++;

						if (isVassalOf(last_scope_obj.civ_tags, local_value))
							civilisation_checks[x]++;
					}

					//has_allies_equal_to
					if (all_scope_keys[i] == "has_allies_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasAlliesValue(local_value);
						}
						if (hasAlliesEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_allies_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasAlliesValue(local_value);
						}
						if (hasAlliesGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_allies_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasAlliesValue(local_value);
						}
						if (hasAlliesGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_allies_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasAlliesValue(local_value);
						}
						if (hasAlliesLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_allies_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasAlliesValue(local_value);
						}
						if (hasAlliesLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_defensive_pacts_equal_to
					if (all_scope_keys[i] == "has_defensive_pacts_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasDefensivePactsValue(local_value);
						}
						if (hasDefensivePactsEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_defensive_pacts_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasDefensivePactsValue(local_value);
						}
						if (hasDefensivePactsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_defensive_pacts_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasDefensivePactsValue(local_value);
						}
						if (hasDefensivePactsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_defensive_pacts_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasDefensivePactsValue(local_value);
						}
						if (hasDefensivePactsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_defensive_pacts_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasDefensivePactsValue(local_value);
						}
						if (hasDefensivePactsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_neighbours_equal_to
					if (all_scope_keys[i] == "has_neighbours_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNeighboursValue(local_value);
						}
						if (hasNeighboursEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_neighbours_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNeighboursValue(local_value);
						}
						if (hasNeighboursGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_neighbours_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNeighboursValue(local_value);
						}
						if (hasNeighboursGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_neighbours_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNeighboursValue(local_value);
						}
						if (hasNeighboursLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_neighbours_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNeighboursValue(local_value);
						}
						if (hasNeighboursLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_non_aggression_pacts_equal_to
					if (all_scope_keys[i] == "has_non_aggression_pacts_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNonAggressionPactsValue(local_value);
						}
						if (hasNonAggressionPactsEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_non_aggression_pacts_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNonAggressionPactsValue(local_value);
						}
						if (hasNonAggressionPactsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_non_aggression_pacts_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNonAggressionPactsValue(local_value);
						}
						if (hasNonAggressionPactsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_non_aggression_pacts_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNonAggressionPactsValue(local_value);
						}
						if (hasNonAggressionPactsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_non_aggression_pacts_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasNonAggressionPactsValue(local_value);
						}
						if (hasNonAggressionPactsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_total_wars_equal_to
					if (all_scope_keys[i] == "has_total_wars_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasTotalWars(local_value);
						}
						if (hasTotalWarsEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_total_wars_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasTotalWars(local_value);
						}
						if (hasTotalWarsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_total_wars_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasTotalWars(local_value);
						}
						if (hasTotalWarsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_total_wars_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasTotalWars(local_value);
						}
						if (hasTotalWarsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_total_wars_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasTotalWars(local_value);
						}
						if (hasTotalWarsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//has_vassals_equal_to
					if (all_scope_keys[i] == "has_vassals_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasVassals(local_value);
						}
						if (hasVassalsEqualTo(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_vassals_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasVassals(local_value);
						}
						if (hasVassalsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_vassals_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasVassals(local_value);
						}
						if (hasVassalsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_vassals_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasVassals(local_value);
						}
						if (hasVassalsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "has_vassals_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsHasVassals(local_value);
						}
						if (hasVassalsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}

					//opinion_is
					if (all_scope_keys[i] == "opinion_is" || all_scope_keys[i] == "opinion_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "object")
							if (opinionIs(last_scope_obj.civ_tags, local_value[0].tag, local_value[0].value))
								civilisation_checks[x]++;
					}
					if (all_scope_keys[i] == "opinion_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "object")
							if (opinionIsGEQ(last_scope_obj.civ_tags, local_value[0].tag, local_value[0].value))
								civilisation_checks[x]++;
					}
					if (all_scope_keys[i] == "opinion_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "object")
							if (opinionIsGreaterThan(last_scope_obj.civ_tags, local_value[0].tag, local_value[0].value))
								civilisation_checks[x]++;
					}
					if (all_scope_keys[i] == "opinion_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "object")
							if (opinionIsLEQ(last_scope_obj.civ_tags, local_value[0].tag, local_value[0].value))
								civilisation_checks[x]++;
					}
					if (all_scope_keys[i] == "opinion_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "object")
							if (opinionIsLessThan(last_scope_obj.civ_tags, local_value[0].tag, local_value[0].value))
								civilisation_checks[x]++;
					}

					//ranking_is
					if (all_scope_keys[i] == "ranking_is" || all_scope_keys[i] == "ranking_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "ranking_is" || all_scope_keys[i] == "ranking_is_equal_to") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIs(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "ranking_is_geq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIsGEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "ranking_is_greater_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIsGreaterThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "ranking_is_leq") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIsLEQ(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
					if (all_scope_keys[i] == "ranking_is_less_than") {
						total_civilisation_checks++;

						if (typeof local_value[0] == "string") {
							old_local_value = JSON.parse(JSON.stringify(local_value));
							local_value[0] = getCivilisationsRanking(local_value);
						}
						if (rankingIsLessThan(last_scope_obj.civ_tags, local_value[0]))
							civilisation_checks[x]++;
						if (old_local_value != undefined)
							local_value = old_local_value;
					}
				}

				//Provinces.
				{
					//provinces_is

					//<capital_building_key>_is
					//conquered_provinces_is
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