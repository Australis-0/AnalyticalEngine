//Initialise functions
{
	function initialiseSaveLoad () {
		main.map_loaded = true;

		loadCities();
		loadRegions();
	}

	function loadCities () {
		//Declare local instance variables
		var main_cities_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json";

		//Load file as main.map.cities
		main.map.cities = loadFileAsObject(main_cities_file_path);
	}

	function loadRegions () {
		//Declare local instance variables
		var main_regions_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "GeographicalRegions.json";

		//Load file as main.map.regions
		main.map.regions = loadFileAsObject(main_regions_file_path);
	}

	function saveCities () {
		//Declare local instance variables
		var main_cities_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json";

		//Write as file
		writeObjectToFile(main.map.cities, main_cities_file_path);
	}

	function saveRegions () {
		//Declare local instance variables
		var main_regions_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "GeographicalRegions.json";

		//Write as file
		writeObjectToFile(main.map.regions, main_regions_file_path);
	}
}