//Initialise functions
{
	function civilisationsHaveMarketShare (arg0_civ_tags, arg1_value) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);
		var value = returnSafeNumber(arg1_value);

		
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
}