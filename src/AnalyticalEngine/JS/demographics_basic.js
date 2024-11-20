//Initialise functions
{
	/**
	 * generateDemographicStructure() - Generates demographic population totals from the above Demographia data structure. Note that this function is recursive.
	 * @param [arg0_options]
	 * @param {Object} [arg0_options."object_key"]
	 * @param {number} [arg0_options."object_key".population=undefined] - The absolute number to calculate for at this object depth.
	 * @param {number} [arg0_options."object_key".population_percentage=undefined] - The percentage value to calculate for at this object depth.
	 * @param {number} [arg0_options.population] - The total population to feed into the data structure.
	 *
	 * @returns {{"object_key": { population: number, population_percentage: number }, population: number}}
	 */
	function generateDemographicStructure (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (options.population == undefined) options.population = 0;

		//Declare local instance variables
		var abs_population_obj = {}; //For standardising absolute #
		var abs_total = JSON.parse(JSON.stringify(options.population));
		var all_object_keys = Object.keys(options);
		var rel_population_obj = {}; //For standardising relative %
		var rel_total = options.population;
		var population_obj = {}; //Merged population object

		//Absolute # handler - Subtract all .population fields from the relative total when percentages are standardised. Calculate the immediate .population_percentage for these fields relative to the absolute total.
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = JSON.parse(JSON.stringify(options[all_object_keys[i]]));

			if (local_value)
				if (typeof local_value == "object") {
					if (local_value.population) {
						rel_total -= local_value.population;
						local_value.population_percentage = local_value.population/abs_total;

						//Set to abs_population_obj
						abs_population_obj[all_object_keys[i]] = local_value;
					}
				} else if (typeof local_value != "object") {
					//Set to abs_population_obj
					abs_population_obj[all_object_keys[i]] = local_value;
				}
		}

		//Relative % handler - Standardise percentages amongst .population_percentage fields remaining in the relative total; then calculate the absolute figures.
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = JSON.parse(JSON.stringify(options[all_object_keys[i]]));

			if (local_value)
				if (typeof local_value == "object")
					if (local_value.population_percentage && !local_value.population)
						rel_population_obj[all_object_keys[i]] = local_value.population_percentage;
		}

		rel_population_obj = standardisePercentage(rel_population_obj, rel_total);

		//Iterate over all_relative_keys to calculate new absolute .population_percentage
		var all_relative_keys = Object.keys(rel_population_obj);

		for (var i = 0; i < all_relative_keys.length; i++) {
			var local_obj = JSON.parse(JSON.stringify(options[all_relative_keys[i]]));
			var local_value = rel_population_obj[all_relative_keys[i]];

			local_obj.population = local_value;
			local_obj.population_percentage = local_value/abs_total;

			//Set to rel_population_obj
			rel_population_obj[all_relative_keys[i]] = local_obj;
		}

		//Merge rel_population_obj into abs_population_obj; set to population_obj
		population_obj = mergeObjects(abs_population_obj, rel_population_obj);

		//Calculate new .population
		var all_pop_keys = Object.keys(population_obj);
		var final_total = 0;

		for (var i = 0; i < all_pop_keys.length; i++) {
			var local_value = population_obj[all_pop_keys[i]];

			if (local_value)
				if (typeof local_value == "object")
					if (local_value.population)
						final_total += local_value.population;
		}

		population_obj.population = final_total;

		//Recursively calculate for each object in the current merged object
		for (var i = 0; i < all_pop_keys.length; i++) {
			var has_subobject = false;
			var local_value = population_obj[all_pop_keys[i]];

			//Check if local_value has subobject
			var all_local_keys = Object.keys(local_value);

			for (var x = 0; x < all_local_keys.length; x++) {
				var local_subvalue = local_value[all_local_keys[x]];

				if (local_subvalue)
					if (typeof local_subvalue == "object")
						has_subobject = true;
			}

			if (has_subobject)
				population_obj[all_pop_keys[i]] = generateDemographicStructure(local_value);
		}

		//Return statement
		return population_obj;
	}
}