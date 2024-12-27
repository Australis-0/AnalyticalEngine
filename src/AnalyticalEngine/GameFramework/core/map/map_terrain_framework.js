//Import classes
{
	//this.Game = Java.type("aoc.kingdoms.lukasz.jakowski.Game");
	this.Terrain = Java.type("aoc.kingdoms.lukasz.map.terrain.Terrain");
}

//Initialise functions
{
	/**
	 * getAllTerrains() - Returns all terrain types.
	 * @param {Object} [arg0_options]
	 * @param {boolean} [arg0_options.return_object=false]
	 *
	 * @returns {Array<Object>|Object}
	 */
	function getAllTerrains (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local isntance variables
		var all_terrains = [];
		var return_obj = {};
		var terrains_list = Game.terrainManager.terrains;

		//Iterate over terrains_list
		for (var i = 0; i < terrains_list.size(); i++) {
			var local_terrain = terrains_list.get(i);

			all_terrains.push(local_terrain);
			return_obj[i] = local_terrain;
		}

		//Return statement
		return (!options.return_object) ?
			all_terrains : return_obj;
	}

	function getTerrain (arg0_terrain_name) {
		//Convert from parameters
		var name = arg0_terrain_name;

		//Declare local instance variables
		var terrain_exists = [false, ""]; //[terrain_exists, terrain_obj];
		var terrains_list = Game.terrainManager.terrains;

		//Guard clause if terrains_list matches ID; or is object
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
}