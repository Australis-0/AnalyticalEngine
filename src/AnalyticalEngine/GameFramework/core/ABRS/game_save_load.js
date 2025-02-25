//Initialise functions
{
	function initialiseGameSaveLoad () {
		//Game Save handler
		onGameSave(function (arg0_save_key) {
			//Convert from parameters
			var save_key = arg0_save_key;

			//Declare local instance variables
			var save_file_path = "saves/" + Game.map.getFile_ActiveMap_Path() + save_key + "/AnalyticalEngine.js";

			//Save main.gamestate
			try {
				writeObjectToFile(main.gamestate, save_file_path);
			} catch (e) {
				console.warn(e.stack);
			}
		});

		//Game Load handler
		onGameLoad(function (arg0_load_key) {
			//Convert from parameters
			var load_key = arg0_load_key;

			//Declare local instance variables
			var load_file_path = "saves/" + Game.map.getFile_ActiveMap_Path() + load_key + "/AnalyticalEngine.js";

			//Load main.gamestate
			try {
				main.gamestate = loadFileAsObject(load_file_path);
			} catch (e) {
				console.warn(e.stack);
			}
		});
	}
}