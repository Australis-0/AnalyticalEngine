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

		global.on_mapmode_change.undefined_province_names = function (arg0_mapmode) { //[WIP] - Move this so that it is more permanent, in coordination with OPERATION LÄNGEFRAT.
			//Convert from parameters
			var mapmode = arg0_mapmode;

			//Render all undefined provinces in red
			if (mapmode == "undefined_province_names") {
				ProvinceDraw.drawProvinces = new ProvinceDraw.DrawProvinces({
					draw: function (oSB) {
						for (var i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; i++) {
							var local_province = getProvince(Game.getProvinceInViewID(i));

							if (local_province.getProvinceName() == "Unnamed") {
								oSB.setColor(Color.RED);
							} else {
								oSB.setColor(0, 0, 0, 0);
							}
							//oSB.setColor(0, 0, 0, 0);
							local_province.drawLandProvince(oSB);
						}
						for (var i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; i++) {
							var local_province = getProvince(Game.getProvinceInViewID(i));

							if (local_province.getProvinceName() == "Unnamed") {
								oSB.setColor(Color.RED);
							} else {
								oSB.setColor(0, 0, 0, 0);
							}
							//oSB.setColor(0, 0, 0, 0);
							local_province.drawLandProvince(oSB);
						}
					}
				});
			}
		};
	}
}