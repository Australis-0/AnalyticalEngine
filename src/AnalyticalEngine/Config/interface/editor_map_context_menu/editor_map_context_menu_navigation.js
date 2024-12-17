//Import classes
{
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
}

config.editor_map_context_menu.navigation = {
	name: "Editor Map Context Menu",
	scope_type: ["mod_editor"],

	navigation_ui: {
		id: "map_context_menu",
		no_title: true,
		persistent: ["SCENARIO_WASTELAND_CONTINENTS", "SCENARIO_WASTELAND", "SCENARIO_CIVILIZATIONS", "SCENARIO_ASSIGN", "SCENARIO_SETTINGS"],
		order: 0,

		anchor: "top_left",
		height: 200,
		width: 3*CFG.BUTTON_WIDTH,

		edit_civilisation: {
			type: "button",
			name: "Edit {truncateString(interface_obj.civilisation_name, 18)}",
			align: "left",
			height: 0.4,
			width: 3,
			x: 0,
			y: 0,

			special_function: function (e) {
				//Convert from parameters
				var interface_obj = e.interface_obj;

				//Declare local isntance variables
				var civilisation_tag = interface_obj.civilisation_tag;
				var current_page = getCurrentPage();

				//Switch to Civilisation Editor
				Gdx.app.postRunnable(function(){
					try {
						GameCivsEdit.nCiv = Game.loadCivilization(civilisation_tag);
						GameCivsEdit.goBackTo = View[current_page];
						Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
					} catch (e) {
						console.error(e);
					}
				});
			}
		}
	}
};