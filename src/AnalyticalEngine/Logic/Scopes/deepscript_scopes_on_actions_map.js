//Initialise functions
{
	function onCivilisationClick (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_civilisation_click[generateRandomID(main.scopes.on_civilisation_click)] = local_function;
	}

	function onProvinceClick (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_province_click[generateRandomID(main.scopes.on_province_click)] = local_function;
	}
}

//Initialise internal helper functions
{
	function parseOnCivilisationClick (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var all_on_civilisation_click_keys = Object.keys(main.scopes.on_civilisation_click);
		var civ_obj = getCivilisation(civ_tag);

		var player_id = Game.player.iCivID;
		var player_obj = getCivilisation(player_id);
		var player_tag = getCurrentTag(player_obj);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onCivilisationClick() fired.");

		//Iterate over all_on_civilisation_click_keys
		for (var i = 0; i < all_on_civilisation_click_keys.length; i++) {
			var local_function = main.scopes.on_civilisation_click[all_on_civilisation_click_keys[i]];

			local_function({
				civ_obj: civ_obj,
				civ_tag: civ_tag,

				player_obj: player_obj,
				player_tag: player_tag
			});
		}
	}

	function parseOnProvinceClick (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Guard clause if diplomacy menu is currently open
		if (isDiplomacyMenuOpen()) return;

		//Declare local instance variables
		var all_on_province_click_keys = Object.keys(main.scopes.on_province_click);
		var province_obj = getProvince(province_id);

		//Debug statement
		if (global.debug.log_scopes_info)
			console.log("onProvinceClick() fired.");

		//Iterate over all_on_province_click_keys
		for (var i = 0; i < all_on_province_click_keys.length; i++) {
			var local_function = main.scopes.on_province_click[all_on_province_click_keys[i]];

			local_function({
				province_id: province_id,
				province_obj: province_obj
			});
		}
	}
}