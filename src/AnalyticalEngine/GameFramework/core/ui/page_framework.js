//Initialise functions
{
	function getAllPageKeys () {
		//Declare local instance variables
		var all_view_keys = [];
		var current_page_id = Game.menuManager.getViewID();
		var view_array = View.values();

		//Iterate over view_array
		for (var i = 0; i < view_array.length; i++)
			all_view_keys.push(view_array[i].name());
		config.all_page_keys = all_view_keys; //Dynamically set optimisation

		//Return statement
		return all_view_keys;
	}

	function getCurrentPage () {
		//Declare local instance variables
		var all_page_keys = (config.all_page_keys) ? config.all_page_keys : getAllPageKeys();
		var current_page_id = Game.menuManager.getViewID();

		//Iterate over all_page_keys
		for (var i = 0; i < all_page_keys.length; i++)
			if (Game.menuManager[all_page_keys[i]] != undefined) {
				var local_id = Game.menuManager[all_page_keys[i]];

				if (local_id == current_page_id)
					//Return statement
					return all_page_keys[i];
			}
	}

	function switchPage (arg0_page) {
		//Convert from parameters
		var page = arg0_page;

		//Declare local instance variables
		var current_page_id = Game.menuManager.getViewID();

		//Change View
		try {
			if (View[page])
				if (getCurrentPage() != page)
					if (page == "EDITOR_GAMECIVS_EDIT") {
						Gdx.app.postRunnable(function(){
							var civilisation_tag = "sau_m";
							GameCivsEdit.nCiv = Game.loadCivilization(civilisation_tag);
							GameCivsEdit.goBackTo = View[getCurrentPage()];
							Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
						});
					} else if (page == "SCENARIO_ASSIGN") {
						//SCENARIO_ASSIGN HANDLING
						CFG.iCreateScenario_AssignProvinces_Civ = 0;
						Game.keyboard.hideKeyboard();
						Game.mapBG.requestToDisposeMinimap = true;

						Game.menuManager.setViewIDWithoutAnimation(View[page]);
					} else if (page == "SCENARIO_CIVILIZATIONS") {
						//SCENARIO_CIVILIZATIONS HANDLING
						Gdx.app.postRunnable(function() {
							GameCivs.sSearch = "";
							ScenarioWasteland.lUndo.clear();
							Game.iActiveProvince = -1;
							CFG.brushTool = false;
							Game.menuManager.setViewIDWithoutAnimation(View.SCENARIO_CIVILIZATIONS);
						});
					} else {
						Game.menuManager.setViewIDWithoutAnimation(View[page]);
					}
		} catch (e) {
			console.error(e);
		}
	}
}