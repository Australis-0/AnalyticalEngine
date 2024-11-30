//Declare imports
{
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
}

//Initialise functions
{
	/**
	 * getAllProvinces() - Returns an array of all province objects.
	 *
	 * @returns {Array<Object>}
	 */
	function getAllProvinces () {
		//Declare local instance variables
		var all_provinces = [];
		var number_of_provinces = Game.getProvincesSize();

		//Iterate over all_provinces
		for (var i = 0; i < number_of_provinces; i++) {
			var local_province = Game.getProvince(i);

			if (local_province != null)
				all_provinces[i] = local_province;
		}

		//Return statement
		return all_provinces;
	}

	/**
	 * getProvince() - Returns a province object based upon its given province name/ID.
	 * @param {String} arg0_province_name - The province name/ID to return the province for.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.return_key=false] - Whether to return the Province ID instead of a Province object.
	 *
	 * @returns {Object|String}
	 */
	function getProvince (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Guard clause if province is already in province_name
		if (typeof province_name == "object") return province_name;

		//Declare local instance variables
		var province_id;
		var province_obj;

		//Try/catch to check for Province names as well
		if (!isNaN(province_name)) {
			//Integer handling
			province_id = parseInt(province_name);
			province_obj = Game.getProvince(province_id);
		} else {
			//String handling
			var all_provinces = getAllProvinces();

			//Iterate over all_provinces to find a province name match
			for (var i = 0; i < all_provinces.length; i++)
				if (all_provinces[i])
					if (all_provinces[i].getProvinceName().equalsIgnoreCase(province_name)) {
						province_id = all_provinces[i].getProvinceID();
						province_obj = all_provinces[i];
					}
		}

		//Return statement
		return (!options.return_key) ? province_obj : province_id;
	}

	/**
	 * getProvinceCentre() - Returns the centrepoint of a province.
	 * @param {String} arg0_province_name - The province name to fetch the centrepoint for.
	 *
	 * @returns {Array<number, number>}
	 */
	function getProvinceCentre (arg0_province_name) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_name);

		//Return statement
		return [province_obj.getCenterX(), province_obj.getCenterY()];
	}

	/**
	 * getProvinceCores() - Fetches an array of all CivTags that have cores on a specific province.
	 * @param {String} arg0_province_name - The province name to fetch cores for.
	 *
	 * @returns {Array<String>}
	 */
	function getProvinceCores (arg0_province_name) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_name);

		//DDeclare local instance variables
		var core_ids = [];
		var core_tags = [];

		//Iterate over all cores and push their Civilisation integer ids
		for (var i = 0; i < province_obj.iCoresSize; i++)
			core_ids.push(province_obj.getCore(i));
		for (var i = 0; i < core_ids.length; i++)
			core_tags.push(Game.getCiv(core_ids[i]).getCivTag());

		//Return statement
		return core_tags;
	}
}