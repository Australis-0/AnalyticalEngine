//Initialise functions
{
	function initialiseOnCivilisationClickHandler () {
		if (!global.on_civilisation_click) global.on_civilisation_click = {};
		global.on_civilisation_click.debug = function (arg0_civ_tag) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			if (global.debug.log_civilisation_info)
				console.log("Clicked on Civilisation: (" + civ_tag + ") - " + civ_obj.getCivName());
		};
		global.on_civilisation_click.handler = function (arg0_civ_tag) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;

			//Pass to parseOnCivilisationClick()
			parseOnCivilisationClick(civ_tag);
		}
	}

	function isDiplomacyMenuOpen () {
		try {
			//Return statement
			return (Game.menuManager.menus.get(Game.menuManager.IN_GAME).get(Game.menuManager.IN_GAME_CIV).getVisible());
		} catch (e) {}
	}
}