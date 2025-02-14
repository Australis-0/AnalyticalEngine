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
			var civ_names = [];

			//Iterate over all_civ_tags to fill in civ_names
			for (var i = 0; i < all_civ_tags.length; i++)
				civ_names.push(getCivilisationName(all_civ_tags[i], { display_debug: true }));

			//Print current civ tags to console
			console.log("Current Civilisations (" + civ_names.length + "):\n- " + civ_names.join("\n- "));
		}
	},
	print_map_civilisations_with_no_government: {
		name: "print-map-civilisations-with-no-government",
		description: "Prints all civilisations on map currently without a government.",
		special_function: function (args) {
			var all_civ_tags = getAllCurrentCivTags();
			var civ_names = [];

			//Iterate over all_civ_tags to fetch civs without government
			for (var i = 0; i < all_civ_tags.length; i++)
				if (all_civ_tags[i].split("_").length == 1)
					civ_names.push(getCivilisationName(all_civ_tags[i], { display_debug: true }));

			//Print current civ tags to console
			console.log("Civilisations with No Government (" + civ_names.length + "):\n- " + civ_names.join("\n- "));
		}
	},
	print_map_civilisations_with_overridden_names: {
		name: "print-map-civilisations-with-overridden-names",
		description: "Prints all civilisations that have their base names overridden.",
		special_function: function (args) {
			var all_civ_tags = getAllCurrentCivTags();
			var civ_names_with_overridden_names = [];
			var civ_tags_with_overridden_names = [];

			//Iterate over all_civ_tags to fetch civs with overridden names
			for (var i = 0; i < all_civ_tags.length; i++) {
				var local_split_tag = all_civ_tags[i].split("_");
				var localisation_value;

				//Fetch localisation_value name for civ
				localisation_value = getCivilisationActualName(all_civ_tags[i]);

				//Check to see if localisation_value matches current name
				if (localisation_value) {
					var local_civ_name = getCivilisationName(all_civ_tags[i]);

					if (local_civ_name != localisation_value) {
						civ_names_with_overridden_names.push(getCivilisationName(all_civ_tags[i], { display_debug: true }));
						civ_tags_with_overridden_names.push(all_civ_tags[i]);
					}
				}
			}

			//Print current civ tags to console
			console.log("Current Civilisations With Overridden Names (" + civ_names_with_overridden_names.length + "):\n- " + civ_names_with_overridden_names.join("\n- "));
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
	toggle_civilisation_info: {
		name: "toggle-civilisation-info",
		description: "Prints on_civilisation_click change information when the event fires.",
		special_function: function (args) {
			global.debug.log_civilisation_info = (!global.debug.log_civilisation_info);
			console.log("Set global.debug.log_civilisation_info to " + global.debug.log_civilisation_info);
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