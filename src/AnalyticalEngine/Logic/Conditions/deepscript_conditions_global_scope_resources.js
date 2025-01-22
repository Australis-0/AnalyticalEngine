//Initialise functions
{
	function civilisationsHaveMarketShare (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var civ_obj = getCivilisation(civ_tags[i]);

			if (civ_obj.getGoodsProduced(resource_id) != value)
				return false;
		}
		return true;
	}

	function civilisationsHaveMarketShareGEQ (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var civ_obj = getCivilisation(civ_tags[i]);

			if (civ_obj.getGoodsProduced(resource_id) < value)
				return false;
		}
		return true;
	}

	function civilisationsHaveMarketShareGreaterThan (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var civ_obj = getCivilisation(civ_tags[i]);

			if (civ_obj.getGoodsProduced(resource_id) <= value)
				return false;
		}
		return true;
	}

	function civilisationsHaveMarketShareLEQ (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var civ_obj = getCivilisation(civ_tags[i]);

			if (civ_obj.getGoodsProduced(resource_id) > value)
				return false;
		}
		return true;
	}

	function civilisationsHaveMarketShareLessThan (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var civ_obj = getCivilisation(civ_tags[i]);

			if (civ_obj.getGoodsProduced(resource_id) >= value)
				return false;
		}
		return true;
	}

	function globalProductionIs (arg0_resource_name, arg1_value, arg2_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var global_resource_production  = getGlobalResourceProduction(resource_name, options);

		//Return statement
		if (global_resource_production == value)
			return true;
	}

	function globalProductionIsGEQ (arg0_resource_name, arg1_value, arg2_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var global_resource_production  = getGlobalResourceProduction(resource_name, options);

		//Return statement
		if (global_resource_production >= value)
			return true;
	}

	function globalProductionIsGreaterThan (arg0_resource_name, arg1_value, arg2_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var global_resource_production  = getGlobalResourceProduction(resource_name, options);

		//Return statement
		if (global_resource_production > value)
			return true;
	}

	function globalProductionIsLEQ (arg0_resource_name, arg1_value, arg2_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var global_resource_production  = getGlobalResourceProduction(resource_name, options);

		//Return statement
		if (global_resource_production <= value)
			return true;
	}

	function globalProductionIsLessThan (arg0_resource_name, arg1_value, arg2_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var global_resource_production  = getGlobalResourceProduction(resource_name, options);

		//Return statement
		if (global_resource_production < value)
			return true;
	}

	function priceIs (arg0_resource_name, arg1_value) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);

		//Return statement
		if (resource_obj)
			if (ResourcesManager.getPrice(getResourceID(resource_name)) == value)
				return true;
	}

	function priceIsGEQ (arg0_resource_name, arg1_value) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);

		//Return statement
		if (resource_obj)
			if (ResourcesManager.getPrice(getResourceID(resource_name)) >= value)
				return true;
	}

	function priceIsGreaterThan (arg0_resource_name, arg1_value) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);

		//Return statement
		if (resource_obj)
			if (ResourcesManager.getPrice(getResourceID(resource_name)) > value)
				return true;
	}

	function priceIsLEQ (arg0_resource_name, arg1_value) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);

		//Return statement
		if (resource_obj)
			if (ResourcesManager.getPrice(getResourceID(resource_name)) <= value)
				return true;
	}

	function priceIsLessThan (arg0_resource_name, arg1_value) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var value = returnSafeNumber(arg1_value);

		//Return statement
		if (resource_obj)
			if (ResourcesManager.getPrice(getResourceID(resource_name)) < value)
				return true;
	}

	function productionIs (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_id = getCivilisationID(civ_tags[i]);

			if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, resource_id) != value)
				return false;
		}
		return true;
	}

	function productionIsGEQ (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_id = getCivilisationID(civ_tags[i]);

			if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, resource_id) < value)
				return false;
		}
		return true;
	}

	function productionIsGreaterThan (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_id = getCivilisationID(civ_tags[i]);

			if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, resource_id) <= value)
				return false;
		}
		return true;
	}

	function productionIsLEQ (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_id = getCivilisationID(civ_tags[i]);

			if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, resource_id) > value)
				return false;
		}
		return true;
	}

	function productionIsLessThan (arg0_civ_tags, arg1_resource_name, arg2_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var resource_name = arg1_resource_name;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);

		//Return statement
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_id = getCivilisationID(civ_tags[i]);

			if (ResourcesManager.getProducedGoods_ResourceCiv(local_civ_id, resource_id) >= value)
				return false;
		}
		return true;
	}
}