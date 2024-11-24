config.console.map_commands = {
	name: "Map Commands",
	order: 2,

	print_map_cities: {
		name: "print-map-cities",
		description: "Prints all currently named cities on the map.",
		special_function: function (args) {
			var main_map_cities = main.map.cities;

			//Print Main
			console.log("Cities: " + main_map_cities.length + "/" + Game.getProvincesSize() + " Provinces");
		}
	}
};