//Initialise functions
{
	function playMusic (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Format file_path
		if (file_path.endsWith(".ogg"))
			file_path = file_path.replace(".ogg", "");

		//Execute Game.soundsManager.loadNextMusic()
		try {
			Game.soundsManager.loadNextMusic(file_path);
		} catch (e) {
			console.log(e);
		}
	}

	function runEvent (arg0_event_id, arg1_options) {
		//Convert from parameters
		var event_id = arg0_event_id;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
	}
}