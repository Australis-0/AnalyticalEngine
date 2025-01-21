//Initialise functions
{
	function parseOnGameDailyInterval () {
		//Declare local instance variables
		var all_mod_keys = Object.keys(config.deepscript);

		//Iterate over all_mod_keys
		for (var i = 0; i < all_mod_keys.length; i++) {
			var local_mod = config.deepscript[all_mod_keys[i]];

			//Iterate over all_mod_files
			var all_mod_files = Object.keys(local_mod);

			for (var x = 0; x < all_mod_files.length; x++) {
				var local_scope = local_mod[all_mod_files[x]];

				//Call parseEffect() for local_scope upon finding on_game_daily_interval
				var all_scope_keys = Object.keys(local_scope);

				for (var y = 0; y < all_scope_keys.length; y++)
					if (all_scope_keys[i] == "on_game_daily_interval" || all_scope_keys[i].startsWith("on_game_daily_interval_")) {

					}
			}
		}
	}
}