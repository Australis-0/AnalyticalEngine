//Initialise functions
{
	/**
	 * getAllTerrains() - Returns all terrain types.
	 * @param {Object} [arg0_options]
	 *  @param {boolean} [arg0_options.return_keys=false]
	 *  @param {boolean} [arg0_options.return_object=false]
	 *
	 * @returns {Array<Object>|Object}
	 */
	function getAllTerrains (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local isntance variables
		var all_terrain_keys = [];
		var all_terrains = [];
		var return_obj = {};
		var terrains_list = Game.terrainManager.terrains;

		//Iterate over terrains_list
		for (var i = 0; i < terrains_list.size(); i++) {
			var local_terrain = terrains_list.get(i);

			all_terrain_keys.push(local_terrain.Name);
			all_terrains.push(local_terrain);
			return_obj[i] = local_terrain;
		}

		//Return statement
		if (options.return_keys) return all_terrain_keys;
		return (!options.return_object) ?
			all_terrains : return_obj;
	}

	function getTerrain (arg0_terrain_name) {
		//Convert from parameters
		var name = arg0_terrain_name;

		//Declare local instance variables
		var terrain_exists = [false, ""]; //[terrain_exists, terrain_obj];
		var terrains_list = Game.terrainManager.terrains;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return terrains_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < terrains_list.size(); i++) {
			var local_terrain = terrains_list.get(i);

			if (local_terrain.Name)
				if (local_terrain.Name.trim().toLowerCase().indexOf(search_name) != -1)
					terrain_exists = [true, local_terrain];
		}
		for (var i = 0; i < terrains_list.size(); i++) {
			var local_terrain = terrains_list.get(i);

			if (local_terrain.Name)
				if (local_terrain.Name.trim().toLowerCase() == search_name)
					terrain_exists = [true, local_terrain];
		}

		//Return statement
		return (terrain_exists[0]) ? terrain_exists[1] : undefined;
	}

	function getTerrainColour (arg0_terrain_name) {
		//Convert from parameters
		var terrain_name = arg0_terrain_name;

		//Declare local instance variables
		var terrain_obj = getTerrain(terrain_name);

		//Return statement
		return [Math.round(terrain_obj.Color[0]*255),
			Math.round(terrain_obj.Color[1]*255),
			Math.round(terrain_obj.Color[2]*255)];
	}

	function getTerrainID (arg0_terrain_name) {
		//Convert from parameters
		var terrain_name = arg0_terrain_name;

		//Declare local instance variables
		var all_terrains = getAllTerrains();
		var terrain_obj = getTerrain(terrain_name);

		//Iterate over all_terrains
		for (var i = 0; i < all_terrains.length; i++)
			if (all_terrains[i].Name == terrain_obj.Name)
				//Return statement
				return i;
	}

	function setProvinceTerrain (arg0_province_id, arg1_terrain_name) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var terrain_name = arg1_terrain_name;

		//Declare local instance variables
		var all_terrains = getAllTerrains();
		var province_obj = getProvince(province_id);
		var terrain_obj = getTerrain(terrain_name);

		if (terrain_obj)
			province_obj.setTerrainID(getTerrainID(terrain_obj));
	}

	/**
	 * shiftMapTerrain() - Shifts all terrain for current map provinces by a specific TerrainID value.
	 * @param {number} arg0_value - The value to shift all terrain by.
	 * @param {Object} [arg1_options]
	 * @param {Array<String>} [arg1_options.excluded_ids=[0, 1]] - The IDs to exclude from value shifts. [0, 1] by default due to game reservation.
	 */
	function shiftMapTerrain (arg0_value, arg1_options) {
		//Convert from parameters
		var shift_value = arg0_value;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.excluded_ids = (options.excluded_ids != undefined) ? getList(options.excluded_ids) : [0, 1];

		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var all_terrains = getAllTerrains();

		//Iterate over all_provinces and invoke .setTerrainID()
		for (var i = 0; i < all_provinces.length; i++) {
			var local_province_terrain_id = all_provinces[i].getTerrainID();

			if (!options.excluded_ids.includes(local_province_terrain_id)) {
				var new_terrain_id = local_province_terrain_id + shift_value;

				//Normalise new_terrain_id; set new_terrain_id. This cycles terrain IDs such that it is lossless.
				new_terrain_id = ((new_terrain_id % all_terrains.length) + all_terrains.length) % all_terrains.length;
				all_provinces[i].setTerrainID(new_terrain_id);
			}
		}
	}
}