//Initialise functions
{
	function initialiseGameViewMapmodes () {
		//Declare local instance variables
		var all_mapmodes = Object.keys(config.mapmodes);
		if (!main.interfaces.game_view_mapmodes)
			main.interfaces.game_view_mapmodes = {};
		var interface_obj = main.interfaces.game_view_mapmodes;

		//Display InGame mapmodes
		if (!interface_obj.menu_obj) try {
			var game_view_mapmodes_menu_obj = {
				id: "game_view_mapmodes",
				name: "Mapmodes:",
				no_title: false,

				anchor: "top_left",
				can_close: false,
				height: 400,
				width: 3*CFG.BUTTON_WIDTH,
				x: 200,
				y: 300,

				default: {
					type: "button",
					name: "Default",
					width: 3,
					x: 0,
					y: 0,

					special_function: function (e) {
						Gdx.app.postRunnable(function() {
							Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
						});
					}
				}
			};
			var mapmode_button_y = 1;

			//Iterate over all_mapmodes
			for (var i = 0; i < all_mapmodes.length; i++)
				(function(local_mapmode_id) {
					var local_mapmode = config.mapmodes[all_mapmodes[i]];

					if (local_mapmode.is_game_mapmode) {
						game_view_mapmodes_menu_obj[local_mapmode_id] = {
							type: "button",
							name: (local_mapmode.name) ? local_mapmode.name : all_mapmodes[i],
							height: 0.4,
							width: 3,
							x: 0,
							y: mapmode_button_y,

							special_function: function (e) {
								Gdx.app.postRunnable(function(){
									clearMap();
									clearMapmode();
								});
								setTimeout(function(){
									switchMapmode(local_mapmode_id);
								}, 100);
							}
						};

						mapmode_button_y++;
					}
				})(all_mapmodes[i]);

			//Create context menu
			var game_view_mapmodes_menu = createContextMenu(game_view_mapmodes_menu_obj);
		} catch (e) {
			console.log(e.stack);
		}
	}

	function initialiseGameViewMapmodesUI () {
		onMapmodeListClick(function (arg0_display_game_view_mapmodes) {
			try {
				//Convert from parameters
				var display_game_view_mapmodes = arg0_display_game_view_mapmodes;

				console.log("onMapmodeListClick() clicked!", display_game_view_mapmodes);

				//Declare local instance variables
				var interface_obj = main.interfaces.game_view_mapmodes;

				if (display_game_view_mapmodes) {
					initialiseGameViewMapmodes();
				} else {
					if (interface_obj)
						deleteInterface("game_view_mapmodes");
				}
			} catch (e) {
				console.error(e.stack);
			}
		});
	}
}