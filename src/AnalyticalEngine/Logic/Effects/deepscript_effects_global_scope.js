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

	/**
	 * runEvent() - Fires an event given its ID.
	 * @param {String} arg0_event_id
	 * @param {Object} [arg1_options]
	 *  @param {Array<String>} [arg1_options.civ_tags=[]]
	 *  @param {Array<String>} [arg1_options.provinces=[]]
	 */
	function runEvent (arg0_event_id, arg1_options) {
		//Convert from parameters
		var event_id = arg0_event_id;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
	}
}

//Initialise internal helper functions
{
	function getEvent (arg0_event_name) {
		//Convert from parameters
		var event_name = arg0_event_name;

		//Guard clause if main.scopes.events[event_name] is valid
		if (main.scopes.events[event_name]) return main.scopes.events[event_name];

		//Declare local instance variables
		var all_events_keys = Object.keys(main.scopes.events);

		//Iterate over all_events_keys
		for (var i = 0; i < all_events_keys.length; i++) {
			var local_event = main.scopes.events[all_events_keys[i]];


		}
	}
}