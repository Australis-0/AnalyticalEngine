//Initialise functions
{
	function initialiseSaveLoad () {
		main.map_loaded = true;

		loadCities();
	}

	function loadCities () {
		//Declare local instance variables
		var main_cities_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json";

		//Load file as main.map.cities
		main.map.cities = loadFileAsObject(main_cities_file_path);
	}

	function saveCities () {
		//Declare local instance variables
		var main_cities_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json";

		//Write as file
		writeObjectToFile(main.map.cities, main_cities_file_path);
	}
}