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
	 *  @param {number} [arg0_options.province_id] - The province ID to use.
	 *
	 *	@param {Object} [arg0_options.options]
	 *	 @param {Object} [arg0_options.options."option_key"]
	 *	  @param {String} [arg0_options.options."option_key".name]
	 *	  @param {Function} [arg0_options.options."option_key".special_function]
	 *	  @param {Array<{ colour: Array<number, number, number>, font_weight: number, image: String, name: String, raw_string: boolean }>} [arg0_options.options."option_key".tooltip]
	 *
	 * @returns {Object<InGame_DeepscriptEvent>}
	 */
	function createEvent (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		options.event_id = (options.event_id) ?
			options.event_id + "-" + generateRandomID(main.events) :
			"default_event-" + generateRandomID(main.events);
		if (!options.event_type) options.event_type = "civ_event";
		if (!options.options) options.options = {};
		if (options.province_id != undefined) {
			var province_obj = getProvince(options.province_id);

			if (province_obj)
				options.province_id = province_obj.getProvinceID();
		}

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
				if (options.province_id != undefined) event_obj.setProvinceID(options.province_id);

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

								for (var x = 0; x < local_tooltip.length; x++) {
									var colour_key = "`WHITE";
									var font_weight_key = "`NORMAL";
									var image_key = "";

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
									if (local_tooltip[x].image)
										image_key = "`IMAGE<" + local_tooltip[x].image + ">";
									if (local_tooltip[x].raw_string)
										processed_tooltip_array.push(local_tooltip[x].raw_string);

									//Push to processed_tooltip_array
									if (local_tooltip[x].name)
										processed_tooltip_array.push(colour_key + font_weight_key + image_key + "`" + local_tooltip[x].name);
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

				//3. Render; fire, and display event
				var menus_list = Game.menuManager.menus;
				event_obj.render();

				menus_list.get(Game.menuManager.IN_GAME)
				.set(Game.menuManager.IN_GAME_EVENT, event_obj);
				menus_list.get(Game.menuManager.IN_GAME)
				.get(Game.menuManager.IN_GAME_EVENT)
				.setVisible(true);

				//Set menu order
				Game.menuManager.setOrderOfMenu(Game.menuManager.IN_GAME_EVENT);

				//4. Set main.events object
				main.events[options.event_id] = {
					id: options.event_id,
					type: options.event_type,

					display_options: options,
					menu_obj: event_obj,
					options: options.options
				};

				//Return statement
				return main.events[options.event_id];
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
			province_id: "Detroit",

			options: {
				option1: {
					name: "Option 1", // Option display name
					tooltip: [
						{ raw_string: "This is a tooltip for Option 1." }, // Tooltip text
						{ colour: [255, 0, 0], font_weight: 700, name: "This option is bold and red!" }
					],
					special_function: function (e) {
						console.log("You clicked Option 1!");
					}
				},
				option2: {
					name: "Option 2",
					tooltip: [
						{ raw_string: "This is a tooltip for Option 2." },
						{ colour: [0, 255, 0], font_weight: 400, image: "ui/interface/H/icons/plunder.png", name: "This option is green." }
					],
					special_function: function (e) {
						console.log("You clicked Option 2!");
					}
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

	function eventOptionHandler (arg0_event_id, arg1_option_id) {
		//Convert from parameters
		var event_id = arg0_event_id;
		var option_id = arg1_option_id;

		//Declare local instance variables
		var event_obj = main.events[event_id];
		var event_option = event_obj.options[option_id];

		//If event_option is defined, attempt to invoke event_option.special_function
		if (event_option)
			if (event_option.special_function)
				event_option.special_function(event_obj);

		//Clear event_obj
		delete main.events[event_id];
	}
}