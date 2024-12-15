//Initialise functions
{
	function initialiseViewMapmodes () {

	}

	/**
	 * initialiseModEditor() - Initialises the main Mod Editor, mainly with UI/Window management.
	 */
	function initialiseModEditor () {
		//Declare local instance variables
		if (!main.interfaces.mod_editor)
			main.interfaces.mod_editor = {};
		var interface_obj = main.interfaces.mod_editor;
		interface_obj.open_windows = [];

		if (!interface_obj.menu_obj)
			var mod_editor_menu = createContextMenu({
				id: "mod_editor",
				name: "AnalyticalEngine\nMod Editor:",
				no_title: false,
				persistent: ["SCENARIO_WASTELAND_CONTINENTS", "SCENARIO_WASTELAND", "SCENARIO_CIVILIZATIONS", "SCENARIO_ASSIGN", "SCENARIO_SETTINGS"],

				anchor: "top_left",
				can_close: false,
				height: 400,
				width: 6*CFG.BUTTON_WIDTH,
				x: 100,
				y: 100,

				view_wasteland_continents_button: {
					type: "button",
					name: "Edit Wasteland Continents",
					align: "left",
					width: 3,
					x: 0,
					y: 0,

					special_function: function (e) {
						switchPage("SCENARIO_WASTELAND_CONTINENTS");
					}
				},
				view_wasteland_button: {
					type: "button",
					name: "Edit Wasteland",
					align: "left",
					width: 3,
					x: 0,
					y: 1,

					special_function: function (e) {
						switchPage("SCENARIO_WASTELAND");
					}
				},
				view_scenario_civilisations: {
					type: "button",
					name: "Add/Remove Civillisations",
					align: "left",
					width: 3,
					x: 0,
					y: 2,

					special_function: function (e) {
						switchPage("SCENARIO_CIVILIZATIONS");
					}
				},
				view_scenario_assign: {
					type: "button",
					name: "Edit Scenario Map",
					align: "left",
					width: 3,
					x: 0,
					y: 3,

					special_function: function (e) {
						switchPage("SCENARIO_ASSIGN");
					}
				},
				view_scenario_settings: {
					type: "button",
					name: "Edit Scenario Config",
					align: "left",
					width: 3,
					x: 0,
					y: 4,

					special_function: function (e) {
						switchPage("SCENARIO_SETTINGS");
					}
				},
				view_mapmodes_button: {
					type: "button",
					name: "View Mapmodes",
					width: 3,
					x: 1,
					y: 0,

					special_function: function (e) {
						//Convert from parameters
						var button_el = e.element;
						var interface_obj = e.interface_obj;

						if (!interface_obj.open_windows.includes("mod_editor_mapmodes"))
							interface_obj.open_windows.push("mod_editor_mapmodes");
					}
				},
				view_mod_editor_settings: {
					type: "button",
					name: "View Mod Editor Settings",
					width: 3,
					x: 1,
					y: 1,

					special_function: function (e) {
						//Convert from parameters
						var button_el = e.element;
						var interface_obj = e.interface_obj;

						if (!interface_obj.open_windows.includes("mod_editor_settings"))
							interface_obj.open_windows.push("mod_editor_settings");
					}
				}
			});


		if (!interface_obj.logic_loop)
			interface_obj.logic_loop = updateModEditor();

		//Return statement
		return main.interfaces.mod_editor;
	}

	/**
	 * updateModEditor() - Updates Mod Editor UI logic.
	 */
	function updateModEditor () {
		//Return statement
		return setInterval(function(){
			//Declare local instance variables
			var current_view_id = Game.menuManager.viewID;
			var interface_obj = main.interfaces.mod_editor;

			//Toggle tab view
		}, 100);
	}
}