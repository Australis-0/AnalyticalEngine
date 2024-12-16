//Import classes
{
	this.GameCivsEdit = Java.type("aoc.kingdoms.lukasz.menusEditor.GameCivsEdit");
}

//Initialise functions
{
	//[WIP] - Map context menu is closed with left-click on map
	function closeMapContextMenu () {
		if (main.interfaces.map_context_menu)
			main.interfaces.map_context_menu.menu_obj.setVisible(false);
	}

	function openMapContextMenu (arg0_province_id) { //[WIP] - This needs to be put into a Naissance style config
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var current_page = getCurrentPage();
		var mouse_coords = getMouseCoords();
		var province_obj = getProvince(province_id);

		var civilisation_obj = getProvinceOwner(province_id, { return_object: true });

		var civilisation_name = getCivilisationName(civilisation_obj);
		var civilisation_tag = civilisation_obj.getCivTag();

		//Open context menu if not already open
		if (!main.interfaces.map_context_menu) {
			var map_context_menu_obj = {
				id: "map_context_menu",
				no_title: true,
				persistent: ["SCENARIO_WASTELAND_CONTINENTS", "SCENARIO_WASTELAND", "SCENARIO_CIVILIZATIONS", "SCENARIO_ASSIGN", "SCENARIO_SETTINGS"],
				pinned: 2, //Pinned to 2 layers behind the Mod Editor.

				anchor: "top_left",
				height: 200,
				width: 3*CFG.BUTTON_WIDTH
			};

			//Add .x, .y according to mouse_coords; buttons based on province_obj
			map_context_menu_obj.x = mouse_coords[0];
			map_context_menu_obj.y = mouse_coords[1];

			//Edit Civilisation button
			map_context_menu_obj.edit_civilisation = {
				type: "button",
				name: "Edit " + truncateString(civilisation_name, 18),
				align: "left",
				height: 0.4,
				width: 3,
				x: 0,
				y: 0,

				special_function: function (e) {
					console.log("Attempting to edit civilisation: " + civilisation_tag);
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

			var editor_map_context_menu = createContextMenu(map_context_menu_obj);
		} else {
			var interface_obj = main.interfaces.map_context_menu;

			interface_obj.menu_obj.setPosX(mouse_coords[0]);
			interface_obj.menu_obj.setPosY(mouse_coords[1]);
			interface_obj.menu_obj.setVisible(true);

			var edit_civilisation_button = getElement("map_context_menu", "edit_civilisation");

			if (edit_civilisation_button) {
				edit_civilisation_button.element.setText("Edit " + truncateString(civilisation_name, 18));
				edit_civilisation_button.properties.onclick = function (e) {
					console.log("Attempting to edit civilisation: " + civilisation_tag);
					Gdx.app.postRunnable(function(){
						try {
							GameCivsEdit.nCiv = Game.loadCivilization(civilisation_tag);
							GameCivsEdit.goBackTo = View[current_page];
							Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
						} catch (e) {
							console.error(e);
						}
					});
				}; //console switchPage('EDITOR_GAMECIVS_EDIT');
			}
		}
	}
}