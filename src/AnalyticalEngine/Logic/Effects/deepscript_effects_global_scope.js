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

		//Initialise options
		options.civ_tags = getList(options.civ_tags);
		options.provinces = getList(options.provinces);

		//Declare local instance variables
		var event_obj = getEvent(event_id);

		if (event_obj) {
			if (event_obj.type == "civ_event" || event_obj.type == "mission_event") {
				for (var i = 0; i < options.civ_tags.length; i++)
					fireCivilisationEvent(options.civ_tags[i], event_obj);
			} else if (event_obj.type == "province_event") {
				for (var i = 0; i < options.provinces.length; i++)
					fireProvinceEvent(options.provinces[i], event_obj);
			}
		} else {
			console.error("Event ID: " + event_id + " could not be found.");
		}
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
		var event_exists = [false, ""]; //[event_exists, event_obj];
		var search_name = event_name.trim().toLowerCase();

		//.id search - soft search 1st, hard search 2nd
		for (var i = 0; i < all_events_keys.length; i++) {
			var local_event = main.scopes.events[all_events_keys[i]];

			if (all_events_keys[i].indexOf(search_name) != -1)
				event_exists = [true, local_event];
		}
		for (var i = 0; i < all_events_keys.length; i++) {
			var local_event = main.scopes.events[all_events_keys[i]];

			if (all_events_keys[i] == search_name)
				event_exists = [true, local_event];
		}

		//.name search - soft search 1st, hard search 2nd
		if (!event_exists[0]) {
			for (var i = 0; i < all_events_keys.length; i++) {
				var local_event = main.scopes.events[all_events_keys[i]];

				if (local_event.name)
					if (local_event.name.trim().toLowerCase().indexOf(search_name) != -1)
						event_exists = [true, local_event];
			}
			for (var i = 0; i < all_events_keys.length; i++) {
				var local_event = main.scopes.events[all_events_keys[i]];

				if (local_event.name)
					if (local_event.name.trim().toLowerCase() == search_name)
						event_exists = [true, local_event];
			}
		}

		//Return statement
		return (event_exists[0]) ? event_exists[1] : undefined;
	}
}