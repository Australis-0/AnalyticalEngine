//Import classes
{
	this.Color = Java.type("com.badlogic.gdx.graphics.Color");
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
	this.ProvinceDraw = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceDraw");
}

//Initialise functions
{
	function initialiseOnMapmodeChangeHandler () {
		if (!global.on_mapmode_change) global.on_mapmode_change = {};
		global.on_mapmode_change.debug = function (arg0_mapmode) {
			//Convert from parameters
			var mapmode = arg0_mapmode;

			if (global.debug.log_mapmode_info)
				console.log("Changed mapmode to: '" + mapmode + "', with ID: " + getMapmodeID(mapmode));
		};

		global.on_mapmode_change.custom_mapmodes = function (arg0_mapmode) {
			//Convert from parameters
			var mapmode = arg0_mapmode;

			//Declare local instance variables
			var mapmodes = config.mapmodes;

			//Clear custom mapmodes if the mapmode is manually changed, i.e. in-game.
			if (mapmode != main.mapmodes.custom_mapmode)
				delete main.mapmodes.custom_mapmode;

			//Only render custom_mapmode if the current mapmode is set to a custom mapmode
			if (main.mapmodes.custom_mapmode) {
				var custom_mapmode_obj = config.mapmodes[main.mapmodes.custom_mapmode];

				//1. Apply the current main.mapmodes.custom_mapmode; and override if .separate_mapmode is true
				loadCustomMapmode(custom_mapmode_obj);
				//.live_update handler
				if (!main.mapmodes.live_update_logic_loop)
					main.mapmodes.live_update_logic_loop = setInterval(function(){
						//Make sure custom_mapmode still exists within a setInterval context
						if (main.mapmodes.custom_mapmode) {
							var all_provinces = getAllProvinces();
							var local_custom_mapmode_obj = config.mapmodes[main.mapmodes.custom_mapmode];

							if (local_custom_mapmode_obj.live_update)
								loadCustomMapmode(local_custom_mapmode_obj, {
									all_provinces: all_provinces,
									live_update: true
								});
						}
					}, 100);

				//2. Parse the current main.mapmodes.province_colours
				try {
					ProvinceDraw.drawProvinces = new ProvinceDraw.DrawProvinces({
						draw: function (oSB) {
							//Render updates
							for (var i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; i++) {
								var local_province = getProvince(Game.getProvinceInViewID(i));
								var local_province_id = local_province.getProvinceID();

								var local_colour = main.mapmodes.province_colours[local_province_id];

								if (local_colour != undefined) {
									if (local_colour.length == 3) {
										local_colour = convertRGB(local_colour);
									} else if (local_colour.length >= 4) {
										local_colour = convertRGBA(local_colour);
									}
									oSB.setColor(local_colour[0], local_colour[1], local_colour[2], local_colour[3]);
								} else {
									oSB.setColor(0, 0, 0, 0);
								}

								local_province.drawLandProvince(oSB);
							}
							for (var i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; i++) {
								var local_province = getProvince(Game.getProvinceInViewID(i));
								var local_province_id = local_province.getProvinceID();

								var local_colour = main.mapmodes.province_colours[local_province_id];

								if (local_colour != undefined) {
									if (local_colour.length == 3) {
										local_colour = convertRGB(local_colour);
									} else if (local_colour.length >= 4) {
										local_colour = convertRGBA(local_colour);
									}
									oSB.setColor(local_colour[0], local_colour[1], local_colour[2], local_colour[3]);
								} else {
									oSB.setColor(0, 0, 0, 0);
								}

								local_province.drawLandProvince(oSB);
							}
						}
					});
				} catch (e) {
					console.log(e);
				}
			}
		};
	}
}