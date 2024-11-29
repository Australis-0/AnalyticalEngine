//Import classes
{
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
	this.View = Java.type("aoc.kingdoms.lukasz.menu.View");
}

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
}