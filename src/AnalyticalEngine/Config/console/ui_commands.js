config.console.ui_commands = {
	name: "UI Commands",
	order: 4,

	create_dummy_menu: {
		name: "create-dummy-menu",
		description: "Spawns in a sample dummy menu for testing purposes.",
		arg0_title_description: "The title of the Window to spawn.",
		special_function: function (args) {
			if (args.length >= 1) {
				createDummyMenu({ name: args.join(" ") });
			} else {
				createDummyMenu();
			}

			console.log("Dummy menu spawned on-screen.");
		}
	},

	print_page: {
		name: "print-page",
		description: "Prints current information related to the in-game MenuManager.",
		special_function: function (args) {
			console.log("- Current Page: '" + getCurrentPage());
			console.log("- Current Page ID: " + Game.menuManager.getViewID());
		}
	},

	print_page_ids: {
		name: "print-page-ids",
		description: "Prints all page IDs that are associated with the in-game MenuManager.",
		arg0_hide_depopulated_pages_description: "(Boolean) - Whether to hide depopulated pages. False by default.",
		special_function: function (args) {
			//Convert from parameters
			var hide_depopulated_pages = false;

			//Initialise parameters
			if (args[0])
				if (args[0] == "true")
					hide_depopulated_pages = true;

			//Declare local instance variables
			var all_page_keys = (config.all_page_keys) ? config.all_page_keys : getAllPageKeys();
			var page_string = [];

			//Format page_string
			page_string.push("Note: Some pages may not be populated until clicked upon in-game.");
			page_string.push("");

			//Iterate over all_page_keys and print out key-value pairs
			for (var i = 0; i < all_page_keys.length; i++)
				if (Game.menuManager[all_page_keys[i]] != undefined) {
					var local_id = Game.menuManager[all_page_keys[i]];

					if (local_id >= 0 || (local_id <= -1 && !hide_depopulated_pages))
						page_string.push(all_page_keys[i] + ": " + local_id);
				}

			console.log(page_string.join("\n"));
		}
	},

	toggle_page_change_info: {
		name: "toggle-page-change-info",
		description: "Prints on_page_change information when the event fires.",
		special_function: function (args) {
			global.debug.log_page_change_info = (!global.debug.log_page_change_info);
			console.log("Set global.debug.log_page_change_info to " + global.debug.log_page_change_info);
		}
	}
}