//Initialise functions
{
	function initialiseProvinceNamesEditorEventHandlers () {
		if (!global.on_page_change) global.on_page_change = {};
		if (!global.on_province_click) global.on_province_click = {};

		global.on_page_change.EDITOR_MAPS_EDIT_PROVINCE_NAMES = function (arg0_current_page) {
			//Convert from parameters
			var current_page = arg0_current_page;

			//Declare local instance variables

		};
		global.on_province_click.EDITOR_MAPS_EDIT_PROVINCE_NAMES = function (arg0_province_id) {
			//Convert from parameters
			var province_id = arg0_province_id;

			//Declare local instance variables
			var current_page = getCurrentPage();

			//Check to see if the current page is EDITOR_MAPS_EDIT_PROVINCE_NAMES
			if (current_page == "EDITOR_MAPS_EDIT_PROVINCE_NAMES") {
				var province_obj = getProvince(province_id);

				//Load the current province_obj into the current editing tab

			}
		}
	}
}