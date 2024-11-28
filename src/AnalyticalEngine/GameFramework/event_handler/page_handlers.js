//Set on_page_change EventHandler
{
	function initialiseOnPageChangeHandler () {
		global.on_page_change = (global.on_page_change) ? global.on_page_change : {};
		global.on_page_change.debug = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			if (global.debug.log_page_change_info)
				console.log("toggle-page-change-info: Page changed to: " + getCurrentPage());
		};

		global.on_page_change.editor_handler = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Load mapdata on initialisation
			if (current_page == "MAINMENU" && !main.map_loaded) {
				console.log("Loaded Nashorn Mapdata.");

				initialiseSaveLoad();
			}
		};
	}
}