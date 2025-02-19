//Set on_page_change EventHandler
{
	function initialiseOnPageChangeHandler () {
		if (!global.on_page_change) global.on_page_change = {};
		global.on_page_change.debug = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			if (global.debug.log_page_change_info)
				console.log("toggle-page-change-info: Page changed to: " + getCurrentPage());
		};
		global.on_page_change.menu_handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Declare local instance variables
			var all_interfaces = Object.keys(main.interfaces);
			var current_view_id = Game.menuManager.viewID;

			//Iterate over all_interfaces
			for (var i = 0; i < all_interfaces.length; i++) {
				var local_interface = main.interfaces[all_interfaces[i]];

				if (isCurrentlyPersistent(local_interface)) {
					Game.menuManager.addNextMenuToView(current_view_id, local_interface.menu_obj);
					Game.menuManager.setOrderOfMenu(current_view_id); //This is needed to refresh the menu order
				}
			}
		}

		global.on_page_change.editor_handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Declare local instance variables
			var mod_editor_pages = ["SCENARIO_WASTELAND_CONTINENTS", "SCENARIO_WASTELAND", "SCENARIO_CIVILIZATIONS", "SCENARIO_ASSIGN", "SCENARIO_SETTINGS"];

			//Load Mapdata on initialisation
			if (current_page == "MAINMENU" && !main.map_loaded) {
				console.log("Loaded Nashorn Mapdata; post-loading event handlers.");
				main.game_loaded = true;

				initialiseSaveLoad();
				initialisePostLoadingEventHandlers();
			}

			//Load Map Editors
			//Map Editors Menu
			if (current_page == "EDITOR_MAPS_EDIT") {
				//Hide colour picker
				hideColourPicker();
			}
			//Regions
			if (current_page == "EDITOR_MAPS_EDIT_GEO_REGION") {
				var interface_obj = main.interfaces.province_region_editor;

				if (interface_obj)
					if (interface_obj.colour_picker_open)
						openColourPicker(interface_obj.current_region_colour);
			}
			//Terrain
			if (current_page == "EDITOR_MAPS_EDIT_TERRAIN")
				initialiseEditorViewMapmodes();

			//Load Mod Editor
			main.in_mod_editor = (mod_editor_pages.includes(current_page));
			if (main.in_mod_editor)
				initialiseModEditor();
		};
		global.on_page_change.handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Declare local instance variables
			var mod_editor_pages = ["SCENARIO_WASTELAND_CONTINENTS", "SCENARIO_WASTELAND", "SCENARIO_CIVILIZATIONS", "SCENARIO_ASSIGN", "SCENARIO_SETTINGS"];

			//Page handling OnAction scopes
			if (current_page == "EDITOR") {
				parseOnEditorClick();
			} else if (current_page == "IN_GAME") {
				global.cache.in_game = true;
			} else if (current_page == "MAINMENU") {
				if (global.cache.in_game)
					parseOnGameExit();
				delete global.cache.in_game;
			} else if (mod_editor_pages.includes(current_page)) {
				parseOnNewScenario();
			}
		}
		global.on_page_change.scenario_handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Load scenario data on NEW_GAME
			if (current_page == "NEW_GAME") {
				console.log("Scenario loaded: " + getCurrentScenario());
				loadScenario(getCurrentScenario());
			} else if (current_page == "IN_GAME") {
				loadScenarioStart(getCurrentScenario());
			}
		}
	}
}