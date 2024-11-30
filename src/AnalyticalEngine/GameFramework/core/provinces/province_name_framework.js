//Initialise functions
{
	/**
	 * createCity() - Creates a new city object for a given province.
	 * @param {String} arg0_province_id - The province ID for which to change the province name for.
	 * @param {String} arg1_city_name - The city name to specify.
	 * @param {number} arg2_x - The x coordinate.
	 * @param {number} arg3_y - The y coordinate.
	 */
	function createCity (arg0_province_id, arg1_city_name, arg2_x, arg3_y) {
		//Convert from parameters
		var province_id = parseInt(arg0_province_id);
		var city_name = arg1_city_name;
		var x = parseFloat(arg2_x);
		var y = parseFloat(arg3_y);

		//Declare local instance variables
		var new_city_obj = {
			Name: city_name,
			x: x/Game.mapBG.iMapScale,
			y: y/Game.mapBG.iMapScale,
			p: province_id
		};

		if (!getCity(province_id))
			main.map.cities.push(new_city_obj);
	}

	/**
	 * getCity() - Fetches the given city currently in main.map.cities.
	 * @param {String} arg0_province_id - The province ID to fetch the city name for.
	 * @param [arg1_options]
	 * @param [arg1_options.return_index=false] - Whether to return the index instead of city object.
	 *
	 * @returns {Object}
	 */
	function getCity (arg0_province_id, arg1_options) {
		//Convert from parameters
		var province_id = parseInt(arg0_province_id);
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var main_map_cities = main.map.cities;

		//Iterate over all main_map_cities
		for (var i = 0; i < main_map_cities.length; i++) {
			var local_city = main_map_cities[i];

			//Return statement
			if (local_city.p == province_id)
				return (!options.return_index) ? main_map_cities[i] : i;
		}
	}

	/**
	 * modifyCity() - Creates/modifies an extant city object to change the province name for.
	 * @param {String} arg0_province_id - The province ID for which to change the province name for.
	 * @param {String} arg1_city_name - The city name for the province.
	 * @param {number} arg2_x - The x coordinate for the city.
	 * @param {number} arg3_y - The y coordinate for the city.
	 */
	function modifyCity (arg0_province_id, arg1_city_name, arg2_x, arg3_y) {
		//Convert from parameters
		var province_id = arg0_province_id;
		var city_name = arg1_city_name;
		var x = parseFloat(arg2_x);
		var y = parseFloat(arg3_y);

		//Declare local instance variables
		var city_index = getCity(province_id, { return_index: true });

		//If city_obj exists, modify that. Otherwise, create a new city
		if (city_index != undefined) {
			main.map.cities[city_index] = {
				Name: city_name,
				x: x/Game.mapBG.iMapScale,
				y: y/Game.mapBG.iMapScale,
				p: province_id
			};
		} else {
			createCity(city_name, x, y, province_id);
		}
	}
}