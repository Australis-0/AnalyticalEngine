//Initialise functions
{
	//Economy (Goods/Resources).
	{
		function civilisationHasResource (arg0_civ_tags, arg1_resource_names) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);

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
		 * @param {Array<String>} arg1_resource_names
		 *
		 * @returns {boolean}
		 */
		function civilisationIsLargestProducer (arg0_civ_tags, arg1_resource_names) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);

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

		function civilisationIsLargestProducerOfNumberOfResources (arg0_civ_tags, arg1_value) {
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

		function civilisationLargestResourceProducedIs (arg0_civ_tags, arg1_resource_names) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);

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

		function civilisationResourceProductionIs (arg0_civ_tags, arg1_resource_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);
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

		function resourceProductionIsGEQ (arg0_civ_tags, arg1_resource_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);
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

		function resourceProductionIsGreaterThan (arg0_civ_tags, arg1_resource_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);
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

		function resourceProductionIsLEQ (arg0_civ_tags, arg1_resource_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);
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

		function resourceProductionIsLessThan (arg0_civ_tags, arg1_resource_names, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var resource_types = getList(arg1_resource_names);
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
	}
}