//Import classes
{
	this.InGame_DeepscriptEvent = Java.type("AnalyticalEngine.Java.menus.InGame_DeepscriptEvent");
}

//Initialise functions
{
	/**
	 * createEvent() - Spawns a new event and displays it to the player. UI only.
	 * @param {Object} [arg0_options]
	 *  @param {String} [arg0_options.event_type="civ_event"] - Either 'civ_event', 'mission_event', or 'province_event'.
	 *
	 *  @param {Object} [arg0_options.name=""] - Same as .title.
	 *  @param {Object} [arg0_options.description=""] - Same as .desc.
	 *  @param {String} [arg0_options.image=""] - The file path to the image.
	 *  @param {number} [arg0_options.mission_id=0] - The mission ID to provide if .event_type is of 'mission_event'.
	 *
	 *	@param {Object} [arg0_options.options]
	 *	 @param {Object} [arg0_options.options."option_key"]
	 *	  @param {String} [arg0_options.options."option_key".name]
	 *	  @param {Function} [arg0_options.options."option_key".special_function]
	 *	  @param {Array<{ colour: Array<number, number, number>, font_weight: number, name: String, raw_string: boolean }>} [arg0_options.options."option_key".tooltip]
	 *
	 * @returns {Object<InGame_DeepscriptEvent>}
	 */
	function createEvent (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		options.event_id = (options.event_id) ?
			"default_event_" + generateRandomID(main.events) : options.event_id + "_" + generateRandomID(main.events);
		if (!options.event_type) options.event_type = "civ_event";
		if (!options.options) options.options = {};

		//Declare local instance variables
		Gdx.app.postRunnable(function(){
			try {
				var event_obj = new InGame_DeepscriptEvent(options.event_id, options.event_type);

				//1. .name, .description, .image, .mission_id handling
				if (options.name) event_obj.setName(options.name);
				if (options.description) event_obj.setDescription(options.description);
				(options.image) ?
					event_obj.loadEventImage(options.image) :
					event_obj.loadDefaultEventImage();
				if (options.mission_id) event_obj.setMissionID(options.mission_id);

				//2. Add options.options
				var all_option_keys = Object.keys(options.options);

				for (var i = 0; i < all_option_keys.length; i++)
					try {
						var local_option = options.options[all_option_keys[i]];

						var local_option_id = all_option_keys[i];
						var local_option_name = (local_option.name) ? local_option.name : "MISSING_LOC";
						var local_tooltip_strings = [];
						var processed_tooltip_array = [];

						//Iterate over local_option.tooltip if available to format local_tooltip_strings
						if (local_option.tooltip)
							try {
								var local_tooltip = getList(local_option.tooltip);

								for (var x = 0; x < local_tooltip.length; x++)
									if (local_tooltip[x].raw_string) {
										processed_tooltip_array.push(local_tooltip[x]);
									} else {
										var colour_key = "`RESET";
										var font_weight_key = "`NORMAL";

										if (local_tooltip[x].colour)
											if (typeof local_tooltip[x].colour == "string") {
												colour_key = "`" + local_tooltip[x].colour;
											} else if (Array.isArray(local_tooltip[x].colour)) {
												colour_key = "`COLOUR<" + local_tooltip[x].colour.join(", ") + ">";
											}
										if (local_tooltip[x].font_weight)
											if (local_tooltip[x].font_weight == 700) {
												font_weight_key = "`BOLD";
											} else {
												font_weight_key = "`NORMAL";
											}
									}
							} catch (e) {
								console.error("Error parsing event tooltip: " + e.message);
							}

						//Convert processed_tooltip_array to list
						var processed_tooltip_array_list = new ArrayList();

						for (var x = 0; x < processed_tooltip_array.length; x++)
							processed_tooltip_array_list.add(processed_tooltip_array[x]);

						//Call event_obj.addOption()
						try {
							event_obj.addOption(local_option_id, local_option_name, processed_tooltip_array_list);
						} catch (e) {
							console.error("Invoking event_obj.addOption() caused an error: " + e.message);
						}
					} catch (e) {
						console.error("Error parsing event option: " + e.message);
					}

				//3. Fire event
				var menus_list = Game.menuManager.menus;
				event_obj.render();

				/*var current_view_id = Game.menuManager.viewID;
				var raw_menu_id = Game.menuManager.addNextMenuToView(current_view_id, event_obj);
				Game.menuManager.setOrderOfMenu(current_view_id);

				event_obj.updateScrollable();*/

				//DEPRECATED CODE!
				menus_list.get(Game.menuManager.IN_GAME)
					.set(Game.menuManager.IN_GAME_EVENT, event_obj);
				menus_list.get(Game.menuManager.IN_GAME)
					.get(Game.menuManager.IN_GAME_EVENT)
					.setVisible(true);

				//Set menu order
				Game.menuManager.setOrderOfMenu(Game.menuManager.IN_GAME_EVENT);
			} catch (e) {
				console.error(e.stack);
			}
		});

	}

	/**
	 * createDummyEvent() - Used for internal testing to create a dummy event.
	 */
	function createDummyEvent () {
		// Define dummy options for the event
		var dummyEventOptions = {
			event_id: "test_event", // Custom event ID
			event_type: "civ_event", // Event type
			name: "Test Event", // Event name
			description: "Hello! I'm an Event Description. You know basically nothing about me except that I'm really long. Lipsum Lorem Ipsum Dolor Sit Amet! Blah blah blah blah blah blah blah blah blah LOC TEST blah blah blah. By the way, here's a bunch of flavour.", // Event description
			mission_id: 101, // Example mission ID

			options: {
				option1: {
					name: "Option 1", // Option display name
					tooltip: [
						{ raw_string: "This is a tooltip for Option 1." }, // Tooltip text
						{ colour: [255, 0, 0], font_weight: 700, name: "This option is bold and red!" }
					]
				},
				option2: {
					name: "Option 2",
					tooltip: [
						{ raw_string: "This is a tooltip for Option 2." },
						{ colour: [0, 255, 0], font_weight: 400, name: "This option is green." }
					]
				}
			}
		};

		// Invoke the createEvent() function with the dummy event options
		try {
			createEvent(dummyEventOptions);
		} catch (e) {
			console.error(e.stack);
		}
	}
}