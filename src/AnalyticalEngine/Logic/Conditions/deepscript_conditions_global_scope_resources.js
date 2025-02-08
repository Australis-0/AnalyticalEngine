//Initialise functions
{
	function resourceCivilisationsHaveMarketShare (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value, 1);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ = getCivilisation(options.civilisations[x]);

				if (local_civ.getGoodsProduced(local_resource_id) != options.value)
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceCivilisationsHaveMarketShareGEQ (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value, 1);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ = getCivilisation(options.civilisations[x]);

				if (local_civ.getGoodsProduced(local_resource_id) < options.value)
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceCivilisationsHaveMarketShareGreaterThan (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value, 1);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ = getCivilisation(options.civilisations[x]);

				if (local_civ.getGoodsProduced(local_resource_id) <= options.value)
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceCivilisationsHaveMarketShareLEQ (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value, 1);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ = getCivilisation(options.civilisations[x]);

				if (local_civ.getGoodsProduced(local_resource_id) > options.value)
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceCivilisationsHaveMarketShareLessThan (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value, 1);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ = getCivilisation(options.civilisations[x]);

				if (local_civ.getGoodsProduced(local_resource_id) >= options.value)
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceGlobalProductionIs (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_global_resource_production = getGlobalResourceProduction(resource_names[i]);

			if (local_global_resource_production != value)
				return false;
		}

		//Return statement
		return true;
	}

	function resourceGlobalProductionIsGEQ (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_global_resource_production = getGlobalResourceProduction(resource_names[i]);

			if (local_global_resource_production < value)
				return false;
		}

		//Return statement
		return true;
	}

	function resourceGlobalProductionIsGreaterThan (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_global_resource_production = getGlobalResourceProduction(resource_names[i]);

			if (local_global_resource_production <= value)
				return false;
		}

		//Return statement
		return true;
	}

	function resourceGlobalProductionIsLEQ (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_global_resource_production = getGlobalResourceProduction(resource_names[i]);

			if (local_global_resource_production > value)
				return false;
		}

		//Return statement
		return true;
	}

	function resourceGlobalProductionIsLessThan (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_global_resource_production = getGlobalResourceProduction(resource_names[i]);

			if (local_global_resource_production >= value)
				return false;
		}

		//Return statement
		return true;
	}

	function resourceIs (arg0_resource_names, arg1_resource_names) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var ot_resource_names = getList(arg1_resource_names);

		//Declare local instance variables
		var ot_resource_ids = [];
		var resource_ids = [];

		for (var i = 0; i < resource_names.length; i++)
			resource_ids.push(getResourceID(resource_names[i]));
		for (var i = 0; i < ot_resource_names.length; i++)
			ot_resource_ids.push(getResourceID(ot_resource_names[i]));

		//Check to see if all of resource_ids are contained in ot_resource_ids
		for (var i = 0; i < resource_ids.length; i++)
			if (!ot_resource_ids.includes(resource_ids[i]))
				//Return statement
				return false;
		return true;
	}

	function resourceIsNot (arg0_resource_names, arg1_resource_names) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var ot_resource_names = getList(arg1_resource_names);

		//Return statement
		return (!resourceIs(resource_names, ot_resource_names));
	}

	function resourcePriceIs (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++)
			if (ResourcesManager.getPrice(getResourceID(resource_names[i])) != value)
				//Return statement
				return false;
		return true;
	}

	function resourcePriceIsGEQ (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++)
			if (ResourcesManager.getPrice(getResourceID(resource_names[i])) < value)
				//Return statement
				return false;
		return true;
	}

	function resourcePriceIsGreaterThan (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++)
			if (ResourcesManager.getPrice(getResourceID(resource_names[i])) <= value)
				//Return statement
				return false;
		return true;
	}

	function resourcePriceIsLEQ (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++)
			if (ResourcesManager.getPrice(getResourceID(resource_names[i])) > value)
				//Return statement
				return false;
		return true;
	}

	function resourcePriceIsLessThan (arg0_resource_names, arg1_value) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var value = returnSafeNumber(arg1_value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++)
			if (ResourcesManager.getPrice(getResourceID(resource_names[i])) >= value)
				//Return statement
				return false;
		return true;
	}

	function resourceProductionIs (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			//Iterate over all options.civilisations
			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ_id = getCivilisationID(options.civilisations[x]);

				if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, local_resource_id) != value)
					//Return statement
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceProductionIsGEQ (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			//Iterate over all options.civilisations
			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ_id = getCivilisationID(options.civilisations[x]);

				if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, local_resource_id) < value)
					//Return statement
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceProductionIsGreaterThan (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			//Iterate over all options.civilisations
			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ_id = getCivilisationID(options.civilisations[x]);

				if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, local_resource_id) <= value)
					//Return statement
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceProductionIsLEQ (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			//Iterate over all options.civilisations
			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ_id = getCivilisationID(options.civilisations[x]);

				if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, local_resource_id) > value)
					//Return statement
					return false;
			}
		}

		//Return statement
		return true;
	}

	function resourceProductionIsLessThan (arg0_resource_names, arg1_options) {
		//Convert from parameters
		var resource_names = getList(arg0_resource_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.civilisations = (options.civilisations) ? getList(options.civilisations) : [];
		options.value = returnSafeNumber(options.value);

		//Iterate over all resource_names
		for (var i = 0; i < resource_names.length; i++) {
			var local_resource_id = getResourceID(resource_names[i]);

			//Iterate over all options.civilisations
			for (var x = 0; x < options.civilisations.length; x++) {
				var local_civ_id = getCivilisationID(options.civilisations[x]);

				if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, local_resource_id) >= value)
					//Return statement
					return false;
			}
		}

		//Return statement
		return true;
	}
}