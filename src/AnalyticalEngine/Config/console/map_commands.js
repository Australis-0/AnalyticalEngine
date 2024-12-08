config.console.map_commands = {
	name: "Map Commands",
	order: 2,

	change_mapmode: {
		name: "change-mapmode",
		description: "Changes the current mapmode.",
		arg0_mapmode_description: "The mapmode to change to.",
		special_function: function (args) {
			var new_mapmode = args[0];

			switchMapmode(new_mapmode);
			console.log("Switched mapmode to: " + new_mapmode);
		}
	},
	print_map_civilisations: {
		name: "print-map-civilisations",
		description: "Prints all civilisations on map.",
		special_function: function (args) {
			var all_civ_tags = getAllCurrentCivTags();

			console.log("Current Civs (" + all_civ_tags.length + "):\n");
			console.log("- " + all_civ_tags.join("\n- "));
		}
	},
	print_map_cities: {
		name: "print-map-cities",
		description: "Prints all currently named cities on the map.",
		special_function: function (args) {
			var main_map_cities = main.map.cities;

			//Print Main
			console.log("Cities: " + main_map_cities.length + "/" + Game.getProvincesSize() + " Provinces");
		}
	},
	toggle_mapmode_info: {
		name: "toggle-mapmode-info",
		description: "Prints information on different mapmodes when the event fires.",
		special_function: function (args) {
			global.debug.log_mapmode_info = (!global.debug.log_mapmode_info);
			console.log("Set global.debug.log_mapmode_info to " + global.debug.log_mapmode_info);
		}
	}
};