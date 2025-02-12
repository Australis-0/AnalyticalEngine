//Initialise functions
{
	function addEvent (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.id) options.id = generateRandomID(main.scopes.events);
		if (!options.type) options.type = "civ_event";

		if (options.name == undefined) options.name = "Event Name";
		if (options.description == undefined) options.description = "";

		if (options.trigger) options.limit = options.trigger;

		//Add options to main.scopes.events
		main.scopes.events[options.id] = options;
	}
}