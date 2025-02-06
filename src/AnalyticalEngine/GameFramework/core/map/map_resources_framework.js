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

	/**
	 * getCivilisationLargestProducedResource() - Returns the largest produced dresource of a given Civilisation.
	 * @param {String} arg0_civ_tag
	 * @param {Object} [arg1_options]
	 *  @param {boolean} [arg1_options.return_key=false]
	 *
	 * @returns {Object|String}
	 */
	function getCivilisationLargestProducedResource (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var resource_id = ResourcesManager.getLargestGoodsProducedByCiv(civ_obj.getCivID());

		//Return statement
		return (!options.return_key) ? getResource(resource_id) : resource_id;
	}

	/**
	 * getCivilisationProvincesResources() - Fetches Civilisation resources based on a subset of Provinces.
	 * @param {String} arg0_civ_tag
	 * @param {Array<String>} arg1_provinces
	 * @param {Object} [arg2_options]
	 *  @param {boolean} [arg2_options.exclude_occupied_provinces=false]
	 *
	 * @returns {Object}
	 */
	function getCivilisationProvincesResources (arg0_civ_tag, arg1_provinces, arg2_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var provinces = getList(arg1_proviunces);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var return_obj = {};

		//Iterate over provinces and make sure province owner is correct
		for (var i = 0; i < provinces.length; i++) {
			var local_province = getProvince(provinces[i]);

			if (getProvinceOwner(local_province) == civ_tag) {
				var include_province = true;

				if (options.exclude_occupied_provinces)
					if (all_provinces[i].isOccupied())
						include_province = false;
				if (include_province)
					modifyValue(return_obj, getProvinceResource(local_province), 1);
			}
		}

		//Return statement
		return return_obj;
	}

	/**
	 * getCivilisationResources() - Returns the number of Resource provinces a Civilisation currently has.
	 * @param {String} arg0_civ_tag
	 * @param {Object} [arg1_options]
	 *  @param {Array<Object>} [arg1_options.all_provinces=getAllProvinces()] - Optimisation parameter.
	 *
	 * @returns {Object}
	 */
	function getCivilisationResources (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.all_provinces) options.all_provinces = getAllProvinces();

		//Declare local instance variables
		var civ_provinces = getCivilisationProvinces(civ_tag, options);
		var return_obj = {};

		//Iterate over all_provinces and fetch provinces owned by the civ
		for (var i = 0; i < civ_provinces.length; i++)
			modifyValue(return_obj, getProvinceResource(civ_provinces[i]), 1);

		//Return statement
		return return_obj;
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

	function getGlobalResourceProductionObject (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_civ_tags = (options.all_civ_tags) ? options.all_civ_tags : getAllCurrentCivTags();
		var return_obj = {};

		//Iterate over all_civ_tags; call getCivilisationResources() and add to return_obj
		for (var i = 0; i < all_civ_tags.length; i++)
			return_obj[all_civ_tags[i]] = getCivilisationResources(all_civ_tags[i]);

		//Return statement
		return return_obj;
	}

	function getLargestProducersObject (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_civ_tags = (options.all_civ_tags) ? options.all_civ_tags : getAllCurrentCivTags();
		var all_resource_types = getAllResources({ return_keys: true });
		var global_resource_production_obj = getGlobalResourceProductionObject({ all_civ_tags: all_civ_tags });
		var largest_producers_obj = {};
		var return_obj = {};

		//Populate largest_producers_obj
		for (var i = 0; i < all_resource_types.length; i++)
			largest_producers_obj[all_resource_types[i]] = ["", 0]; //[civ_tag, largest_value];

		//Iterate over all_civ_tags; process largest_producers_obj
		for (var i = 0; i < all_civ_tags.length; i++)
			for (var x = 0; x < all_resource_types.length; x++)
				if (global_resource_production_obj[all_civ_tags[i]][all_resource_types[x]] > largest_producers_obj[all_resource_types[x]][1])
					largest_producers_obj[all_resource_types[x]] = [all_civ_tags[i], all_resource_types[x]];

		//Iterate over largest_producers_obj; finish return_obj
		var all_largest_producers_keys = Object.keys(largest_producers_obj);

		for (var i = 0; i < all_largest_producers_keys.length; i++)
			modifyValue(return_obj, largest_producers_obj[all_largest_producers_keys[i]][0], 1);

		//Return statement
		return return_obj;
	}

	function getProvincesResources (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var return_obj = {};

		//Iterate over provinces and fetch provinces owned by the civ
		for (var i = 0; i < provinces.length; i++) {
			var local_province_obj = getProvince(provinces[i]);

			modifyValue(return_obj, getProvinceResource(local_province_obj), 1);
		}

		//Return statement
		return return_obj;
	}

	function getResource (arg0_resource_name, arg1_options) {
		//Convert from parameters
		var name = arg0_resource_name;
		var options = (arg1_options) ? arg1_options : {};

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
		if (options.return_key)
			if (resource_exists[0])
				return resource_exists[1].Name;
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