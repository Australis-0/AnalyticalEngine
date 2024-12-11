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
	find_civilisation: {
		name: "find-civilisation",
		description: "Pans to a civilisation's capital.",
		arg0_civilisation_description: "The civilisation to pan to.",
		special_function: function (args) {
			//Convert from parameters
			var input_civ_name = args.join(" ");

			//Declare local instance variables
			var civ_obj = getCivilisation(input_civ_name);

			//Fetch capital_obj if possible; pan to it if it exists. Otherwise log that the civilisation does not presently exist
			if (civ_obj) {
				var capital_obj = getCivilisationCapital(civ_obj);
				var capital_name = getProvinceName(capital_obj, { display_province_id: true });
				var civ_name = getCivilisationName(civ_obj);

				if (capital_obj) {
					panToProvince(capital_obj);
					console.log("Scrolled to " + capital_name);
				} else {
					console.log(civ_name + " currently isn't on the map.");
				}
			} else {
				console.log("'" + input_civ_name + "' is not a defined civilisation.");
			}
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