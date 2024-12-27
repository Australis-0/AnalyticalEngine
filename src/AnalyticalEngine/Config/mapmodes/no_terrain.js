config.mapmodes.no_terrain = {
	name: "No Terrain",

	is_editor_mapmode: true,
	live_update: true,
	separate_mapmode: true,
	special_function: function (arg0_province_obj) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_obj);

		//Declare local instance variables
		var province_colour = undefined;
		var province_id = province_obj.getProvinceID();
		var province_terrain_id = getProvinceTerrain(province_obj, { return_key: true });
		var province_terrain_obj = getProvinceTerrain(province_obj);

		var province_terrain_colour = getTerrainColour(province_terrain_obj);

		//Check to see if province_terrain_colour is of the first type
		if (province_terrain_id == 1) {
			province_colour = [0, 0, 0, 0]; //Set to transparent if province is of the first terrain type.
		} else {
			province_colour = [province_terrain_colour[0], province_terrain_colour[1], province_terrain_colour[2], 0.85];
		}

		//Return statement
		return province_colour;
	}
}