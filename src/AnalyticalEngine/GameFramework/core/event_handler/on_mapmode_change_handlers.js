//Initialise functions
{
	function initialiseOnMapmodeChangeHandler () {
		if (!global.on_mapmode_change) global.on_mapmode_change = {};
		global.on_mapmode_change.debug = function (arg0_mapmode) {
			//Convert from parameters
			var mapmode = arg0_mapmode;

			if (global.debug.log_mapmode_info)
				console.log("Changed mapmode to: '" + mapmode + "', with ID: " + getMapmodeID(mapmode));
		}
	}
}