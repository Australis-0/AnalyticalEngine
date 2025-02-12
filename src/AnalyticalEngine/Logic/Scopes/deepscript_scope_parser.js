//Initialise functions
{
	/**
	 * addEvent() - [WIP] - Documentation.
	 * @param {Object} [arg0_options]
	 *  @param {String} [arg0_options.id=generateRandomID(main.scopes.events)] - The ID of the event.
	 *  @param {String} [arg0_options.image] - The filepath to the event image to display.
	 *  @param {String} [arg0_options.type] - Either 'civ_event'/'mission_event'/'province_event'.
	 *
	 *  @param {String} [arg0_options.name=""] - The name of the event.
	 *  @param {String} [arg0_options.description=""] - The description for the event to display.
	 *
	 *  @param {boolean} [arg0_options.display_in_missions=false]
	 *  @param {number} [arg0_options.mission_id=undefined] - If defined, this will display in missions unless told not to explicitly.
	 *  @param {number} [arg0_options.only_once=false] - Whether to fire the event only once.
	 *
	 *  @param {Function} [arg0_options.effect]
	 *  @param {Function} [arg0_options.immediate]
	 *  @param {Function} [arg0_options.limit] - Event triggers if this function returns true.
	 *  @param {Function} [arg0_options.trigger] - Event triggers if this function returns true. Alias for limit.
	 *
	 *  @param {Object} [arg0_options."btn_<key>"]
	 *   @param {String} [arg0_options."btn_<key>".name]
	 *   @param {String} [arg0_options."<btn_key>".description]
	 *
	 *   @param {number|Function} [arg0_options."<btn_key>".ai_chance=1] - Proportional to other options by default.
	 *   @param {boolean} [arg0_options."<btn_key>".disabled=false] - Whether the button is disabled for clicking. Useful for tooltips.
	 *   @param {Function} [arg0_options."<btn_key>".effect]
	 *   @param {Function} [arg0_options."<btn_key>".limit] - Whether the option displays to begin with.
	 */
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