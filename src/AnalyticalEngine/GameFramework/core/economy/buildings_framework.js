//Import classes
{
	this.BuildingsManager = Java.type("aoc.kingdoms.lukasz.map.BuildingsManager");
	this.ProvinceConstructedBuilding = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceConstructedBuilding");
}

//Initialise functions
{
	function addBuildingConstruction (arg0_province_name, arg1_building_name) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var building_name = arg1_building_name;

		//Declare local instance variables
		var building_obj = getBuilding(building_name, { return_object: true });
		var province_obj = getProvince(province_name);

		//Make sure province_obj exists before adding construction
		if (province_obj)
			if (building_obj)
				//Return statement
				return province_obj.addBuildingConstruction(building_obj.building_category_index, building_obj.index);
	}

	function addBuilding (arg0_province_name, arg1_building_name) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var building_name = arg1_building_name;

		//Declare local instance variables
		var building_obj = getBuilding(building_name, { return_object: true });
		var province_obj = getProvince(province_name);

		//Make sure province_obj exists before adding building
		if (province_obj)
			if (building_obj)
				province_obj.addNewBuilding(
					new ProvinceConstructedBuilding(building_obj.building_category_index, building_obj.index)
				);
	}

	function getAllBuildings (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_building_keys = [];
		var all_buildings = [];
		var buildings_list = BuildingsManager.buildings;
		var return_obj = {};

		//Iterate over buildings_list
		for (var i = 0; i < buildings_list.size(); i++) {
			var local_building = buildings_list.get(i);

			all_building_keys.push(local_building.Name);
			all_buildings.push(local_building);
			return_obj[i] = local_building;
		}

		//Return statement
		if (options.return_keys) return all_building_keys;
		return (!options.return_object) ?
			all_buildings : return_obj;
	}

	/**
	 * getBuilding() - Fetches the relevant building category object and/or index for it.
	 * @param {String} arg0_building_name
	 * @param {Object} [arg1_options]
	 *  @param {boolean} [arg1_options.return_object=false]
	 *
	 * @returns {Object|{building_category_index: number, building_obj: Object, index: number}}
	 */
	function getBuilding (arg0_building_name, arg1_options) {
		//Convert from parameters
		var name = arg0_building_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var building_category_index = 0;
		var building_exists = [false, ""]; //[building_exists, building_obj];
		var building_index = 0;
		var buildings_list = BuildingsManager.buildings;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return buildings_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < buildings_list.size(); i++) {
			var local_building = buildings_list.get(i);

			if (local_building.Name)
				for (var x = 0; x < local_building.Name.length; x++)
					if (local_building.Name[x].trim().toLowerCase().indexOf(search_name) != -1) {
						building_category_index = i;
						building_index = x;
						building_exists = [true, local_building];
					}
		}
		for (var i = 0; i < buildings_list.size(); i++) {
			var local_building = buildings_list.get(i);

			if (local_building.Name)
				for (var x = 0; x < local_building.Name.length; x++)
					if (local_building.Name[x].trim().toLowerCase() == search_name) {
						building_category_index = i;
						building_exists = [true, local_building];
						building_index = x;
					}
		}

		//Return statement
		if (options.return_object)
			return (building_exists[0]) ? { building_category_index: building_category_index, building_obj: building_exists[1], index: building_index } : undefined;
		return (building_exists[0]) ? building_exists[1] : undefined;
	}

	function getBuildingKey (arg0_building_name) {
		//Convert from parameters
		var building_name = arg0_building_name;

		//Declare local instance variables
		var building_obj = getBuilding(building_name, { return_object: true });

		//Return statement
		if (building_obj)
			return building_obj.building_obj.Name[building_obj.index];
	}

	/**
	 * getProvinceBuildings() - Returns an object of all buildings built in a province.
	 * @param {String} arg0_province_name
	 * @param {Object} arg1_options
	 *  @param {Object} [arg1_options.all_buildings=getAllBuildings()] - Optimisation parameter.
	 *  @param {Object} [arg1_options.return_max_value=false]
	 *
	 * @returns {Object}
	 */
	function getProvinceBuildings (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_buildings = (!options.all_buildings) ? getAllBuildings() : options.all_buildings;
		var province_buildings_obj = {};
		var province_obj = getProvince(province_name);

		//Iterate over province_obj.iBuildingsSize
		if (province_obj)
			for (var i = 0; i < province_obj.iBuildingsSize; i++) {
				var local_building = province_obj.buildings.get(i);
				var local_building_category_index = local_building.getBuilding();
				var local_index = local_building.getBuildingID();

				var local_building_category_name = all_buildings[local_building_category_index].Name[0];
				var local_building_name = all_buildings[local_building_category_index].Name[local_index];

				if (!options.return_max_value) {
					province_buildings_obj[local_building_name] = 1;
				} else {
					province_buildings_obj[local_building_category_name] = Math.max(province_buildings_obj[local_building_category_name], local_index);
				}
			}

		//Return statement
		return province_buildings_obj;
	}

	/**
	 * getTotalBuildings() - Fetches the total number of buildings a Civilisation owns.
	 * @param {String} arg0_civ_name
	 * @param {Object} arg1_options
	 *  @param {Object} [arg1_options.all_buildings=getAllBuildings()] - Optimisation parameter.
	 *  @param {boolean} [arg1_options.exclude_occupied_provinces=false]
	 *  @param {boolean} [arg1_options.return_max_value=false]
	 * @returns {Object}
	 */
	function getTotalBuildings (arg0_civ_name, arg1_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var options = (arg1_options) ? arg1_options : {};

		if (!options.all_buildings) options.all_buildings = getAllBuildings();

		//Declare local instance variables
		var all_civ_provinces = getCivilisationProvinces(civ_name, options);
		var civ_buildings_obj = {};

		//Iterate over all_civ_provinces and merge civ_buildings_obj
		for (var i = 0; i < all_civ_provinces.length; i++)
			civ_buildings_obj = mergeObjects(civ_buildings_obj,
				getProvinceBuildings(all_civ_provinces[i], options));

		//Return statement
		return civ_buildings_obj;
	}

	/**
	 * getUniqueCapitalBuildingsConstructed() - Returns the number of unique capital buildings constructed
	 * @param {String} arg0_civ_name
	 * @param {Object} [arg1_options]
	 *  @param {boolean} [arg1_options.capital_city_only=false]
	 *  @param {boolean} [arg1_options.military_academy_only=false]
	 *  @param {boolean} [arg1_options.military_academy_for_generals_only=false]
	 *  @param {boolean} [arg1_options.supreme_court_only=false]
	 *
	 * @returns {number}
	 */
	function getUniqueCapitalBuildingsConstructed (arg0_civ_name, arg1_options) {
		//Convert from parameters
		var civ_name = arg0_civ_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var capital_buildings_constructed = 0;
		var civ_obj = getCivilisation(civ_name);

		if (civ_obj) {
			if (options.capital_city_only)
				return civ_obj.getCapitalLevel();
			capital_buildings_constructed += civ_obj.getCapitalLevel();
			if (options.military_academy_only)
				return civ_obj.getMilitaryAcademyLevel();
			capital_buildings_constructed += civ_obj.getMilitaryAcademyLevel();
			if (options.military_academy_for_generals_only)
				return civ_obj.getMilitaryAcademyForGeneralsLevel();
			capital_buildings_constructed += civ_obj.getMilitaryAcademyForGeneralsLevel();
			if (options.supreme_court_only)
				return civ_obj.getSupremeCourtLevel();
			capital_buildings_constructed += civ_obj.getSupremeCourtLevel();
		}

		//Return statement
		return capital_buildings_constructed;
	}

	/**
	 * removeBuilding() - Removes a building from a given province.
	 * @param {String} arg0_province_name
	 * @param {String} arg1_building_name
	 */
	function removeBuilding (arg0_province_name, arg1_building_name) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var building_name = arg1_building_name;

		//Declare local instance variables
		var building_obj = getBuilding(building_name, { return_object: true });
		var province_obj = getProvince(province_name);

		//Make sure province_obj exists before destroying building
		if (province_obj)
			if (building_obj)
				province_obj.destroyBuilding(building_obj.building_category_index, building_obj.index);
	}

	/**
	 * removeBuildingConstruction() - Removes an ongoing building construction from a given province.
	 * @param {String} arg0_province_name
	 * @param {String} arg1_building_name
	 */
	function removeBuildingConstruction (arg0_province_name, arg1_building_name) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var building_name = arg1_building_name;

		//Declare local instance variables
		var building_obj = getBuilding(building_name, { return_object: true });
		var civ_obj = getProvinceOwner(province_name, { return_object: true });
		var province_obj = getProvince(province_name);

		//Attempt to cancel building construction
		if (province_obj)
			//Return statement
			province_obj.cancelBuildingConstruction(civ_obj.getCivID(), building_obj.building_category_index, building_obj.index);
	}
}