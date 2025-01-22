//Import classes
{
	this.ResourcesManager = Java.type("aoc.kingdoms.lukasz.map.ResourcesManager");
}

//Initialise functions
{
	/**
	 * getAllResources() - Returns all resource types.
	 * @param {Object} [arg0_options]
	 *  @param {boolean} [arg0_options.return_object=false]
	 *  @param {boolean} [arg0_options.return_keys=false]
	 *
	 * @returns {Array<Object>|Object}
	 */
	function getAllResources (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_resource_keys = [];
		var all_resources = [];
		var return_obj = {};

		//Iterate over ResourcesManager.lResources
		for (var i = 0; i < ResourcesManager.lResources.size(); i++) {
			var local_resource = ResourcesManager.lResources.get(i);

			all_resource_keys.push(local_resource.Name);
			all_resources.push(local_resource);
			return_obj[i] = local_resource;
		}

		//Return statement
		if (options.return_keys) return all_resource_keys;
		return (!options.return_object) ?
			all_resources : return_obj;
	}

	function getGlobalResourceProduction (arg0_resource_name, arg1_options) {
		//Convert from parameters
		var resource_name = arg0_resource_name;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.all_provinces) options.all_provinces = getAllProvinces();

		//Declare local instance variables
		var resource_id = getResourceID(resource_name);
		var sum_goods_production = 0;

		for (var i = 0; i < options.all_provinces.length; i++)
			if (options.all_provinces[i].getResourceID() == resource_id)
				sum_goods_production += returnSafeNumber(
					ResourcesManager.getProducedGoods(options.all_provinces[i].getProvinceID())
				);

		//Return statement
		return sum_goods_production;
	}

	function getResource (arg0_resource_name) {
		//Convert from parameters
		var name = arg0_resource_name;

		//Declare local instance variables
		var resource_exists = [false, ""]; //[resource_exists, resource_obj];
		var resources_list = ResourcesManager.lResources;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return resources_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < resources_list.size(); i++) {
			var local_resource = resources_list.get(i);

			if (local_resource.Name)
				if (local_resource.Name.trim().toLowerCase().indexOf(search_name) != -1)
					resource_exists = [true, local_resource];
		}
		for (var i = 0; i < resources_list.size(); i++) {
			var local_resource = resources_list.get(i);

			if (local_resource.Name)
				if (local_resource.Name.trim().toLowerCase() == search_name)
					resource_exists = [true, local_resource];
		}

		//Return statement
		return (resource_exists[0]) ? resource_exists[1] : undefined;
	}

	function getResourceColour (arg0_resource_name) {
		//Convert from parameters
		var resource_name = arg0_resource_name;

		//Declare local instance variables
		var resource_obj = getResource(resource_name);

		//Return statement
		return [Math.round(resource_obj.Color[0]*255),
			Math.round(resource_obj.Color[1]*255),
			Math.round(resource_obj.Color[2]*255)];
	}

	function getResourceID (arg0_resource_name) {
		//Convert from parameters
		var resource_name = arg0_resource_name;

		//Declare local instance variables
		var all_resources = getAllResources();
		var resource_obj = getResource(resource_name);

		//Iterate over all_resources
		for (var i = 0; i < all_resources.length; i++)
			if (all_resources[i].Name == resource_obj.Name)
				//Return statement
				return  i;
	}

	function setProvinceResource (arg0_province_id, arg1_resource_name) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var resource_name = arg1_resource_name;

		//Declare local instance variables
		var all_resources = getAllResources();
		var province_obj = getProvince(province_id);
		var resource_obj  = getResource(resource_name);

		if (resource_obj)
			if (!isSeaProvince(province_obj))
				province_obj.getResourceID(getResourceID(resource_name));
	}

	function shiftMapResources (arg0_value, arg1_options) {
		//Convert from parameters
		var shift_value = arg0_value;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.excluded_ids = (options.excluded_ids != undefined) ? getList(options.excluded_ids) : [0];

		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var all_resources = getAllResources();

		//Iterate over all_resources and invoke .setResourceID()
		for (var i = 0; i < all_provinces.length; i++) {
			var local_province_resource_id = all_provinces[i].getResourceID();

			if (!options.excluded_ids.includes(local_province_resource_id)) {
				var new_resource_id = local_province_resource_id + shift_value;

				//Normalise new_resource_id; set new_resource_id. This cycles resource IDs such that it is lossless.
				new_resource_id = ((new_resource_id % all_resources.length) + all_resources.length) % all_resources.length;
				if (!isSeaProvince(all_provinces[i]))
					all_provinces[i].setResourceID(new_resource_id);
			}
		}
	}
}