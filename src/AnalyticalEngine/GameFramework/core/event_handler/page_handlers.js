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

			//Load mapdata on initialisation
			if (current_page == "MAINMENU" && !main.map_loaded) {
				console.log("Loaded Nashorn Mapdata; post-loading event handlers.");

				initialiseSaveLoad();
				initialisePostLoadingEventHandlers();
			}
			//Load Mod Editor
			if (current_page == "SCENARIO_CIVILIZATIONS") {
				initialiseModEditor();
			}
		};

		global.on_page_change.scenario_handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Load scenario data on NEW_GAME
			if (current_page == "NEW_GAME") {
				console.log("Scenario loaded: " + getCurrentScenario());
				loadScenario(getCurrentScenario());
			}
		}
	}
}