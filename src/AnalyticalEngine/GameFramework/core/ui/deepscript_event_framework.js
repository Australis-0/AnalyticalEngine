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
		if (!options.options) options.options = {
			okay: {
				name: "Okay"
			}
		};
		if (options.province_id != undefined) {
			var province_obj = getProvince(options.province_id);

			if (province_obj)
				options.province_id = province_obj.getProvinceID();
		}

		//Declare local instance variables
		Gdx.app.postRunnable(function(){
			try {
				var current_view_id = Game.menuManager.viewID;
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
							(!local_option.disable_click) ?
								event_obj.addOption(local_option_id, local_option_name, processed_tooltip_array_list) :
								event_obj.addOption(local_option_id, local_option_name, processed_tooltip_array_list, true);
						} catch (e) {
							console.error("Invoking event_obj.addOption() caused an error: " + e.message);
						}
					} catch (e) {
						console.error("Error parsing event option: " + e.message);
					}

				//3. Render; fire, and display event
				var menus_list = Game.menuManager.menus;
				event_obj.render();

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
		//Declare local instance variables
		var dummy_event_options = {
			event_id: "test_event", // Custom event ID
			event_type: "civ_event", // Event type
			image: "game/events/images/H/0.png",
			name: "Test Event", // Event name
			description: "The Charge in the Soviet Union (Kennan) to the Secretary of State\nSECRET\nMoscow, February 22, 1946--9 p.m. [Received February 22--3: 52 p.m.]\n511. Answer to Dept's 284, Feb 3 [13] involves questions so intricate, so delicate, so strange to our form of thought, and so important to analysis of our international environment that I cannot compress answers into single brief message without yielding to what I feel would be dangerous degree of over-simplification. I hope, therefore, Dept will bear with me if I submit in answer to this question five parts, subjects of which will be roughly as follows: \n(1) Basic features of post-war Soviet outlook.\n(2) Background of this outlook\n(3) Its projection in practical policy on official level.\n(4) Its projection on unofficial level.\n(5) Practical deductions from standpoint of US policy.\nI apologize in advance for this burdening of telegraphic channel; but questions involved are of such urgent importance, particularly in view of recent events, that our answers to them, if they deserve attention at all, seem to me to deserve it at once. There follows:\nPart 1: Basic Features of Post War Soviet Outlook, as Put Forward by Official Propaganda Machine\nAre as Follows:\n(a) USSR still lives in antagonistic \"capitalist encirclement\" with which in the long run there can be no permanent peaceful coexistence. As stated by Stalin in 1927 to a delegation of American workers:\n\"In course of further development of international revolution there will emerge two centers of world significance: a socialist center, drawing to itself the countries which tend toward socialism, and a capitalist center, drawing to itself the countries that incline toward capitalism. Battle between these two centers for command of world economy will decide fate of capitalism and of communism in entire world.\"\n(b) Capitalist world is beset with internal conflicts, inherent in nature of capitalist society. These conflicts are insoluble by means of peaceful compromise. Greatest of them is that between England and US.\n(c) Internal conflicts of capitalism inevitably generate wars. Wars thus generated may be of two kinds: intra-capitalist wars between two capitalist states, and wars of intervention against socialist world. Smart capitalists, vainly seeking escape from inner conflicts of capitalism, incline toward latter.\n(d) Intervention against USSR, while it would be disastrous to those who undertook it, would cause renewed delay in progress of Soviet socialism and must therefore be forestalled at all costs.\n(e) Conflicts between capitalist states, though likewise fraught with danger for USSR, nevertheless hold out great possibilities for advancement of socialist cause, particularly if USSR remains militarily powerful, ideologically monolithic and faithful to its present brilliant leadership.\n(f) It must be borne in mind that capitalist world is not all bad. In addition to hopelessly reactionary and bourgeois elements, it includes (1) certain wholly enlightened and positive elements united in acceptable communistic parties and (2) certain other elements (now described for tactical reasons as progressive or democratic) whose reactions, aspirations and activities happen to be \"objectively\" favorable to interests of USSR These last must be encouraged and utilized for Soviet purposes.\n(g) Among negative elements of bourgeois-capitalist society, most dangerous of all are those whom Lenin called false friends of the people, namely moderate-socialist or social-democratic leaders (in other words, non-Communist left-wing). These are more dangerous than out-and-out reactionaries, for latter at least march under their true colors, whereas moderate left-wing leaders confuse people by employing devices of socialism to seine interests of reactionary capital.\nSo much for premises. To what deductions do they lead from standpoint of Soviet policy? To following:\n(a) Everything must be done to advance relative strength of USSR as factor in international society. Conversely, no opportunity most be missed to reduce strength and influence, collectively as well as individually, of capitalist powers.\n(b) Soviet efforts, and those of Russia's friends abroad, must be directed toward deepening and exploiting of differences and conflicts between capitalist powers. If these eventually deepen into an \"imperialist\" war, this war must be turned into revolutionary upheavals within the various capitalist countries.\n(c) \"Democratic-progressive\" elements abroad are to be utilized to maximum to bring pressure to bear on capitalist governments along lines agreeable to Soviet interests.\n(d) Relentless battle must be waged against socialist and social-democratic leaders abroad.", // Event description
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
					disable_click: true,
					tooltip: [
						{ raw_string: "Custom tooltips have been added to AnalyticalEngine!" },
						{ raw_string: "`YELLOW`This`RESET` is a `GREEN`multicoloured`RESET` raw string `YELLOW`test`RESET`."},
						{ raw_string: "- You can even do inline `BOLD`bold`RESET`." },
						{ colour: [240, 60, 60], font_weight: 400, image: "ui/interface/H/icons/plunder.png", name: "This option is salmon with a custom image." },
						{ raw_string: " " },
						{ raw_string: "- After a short line break, try having an inline flag: `IMAGE<gfx/flagsXH/uni_m.png>` United Kingdom" }
					],
					special_function: function (e) {
						console.log("You clicked Option 2!");
					}
				}
			}
		};

		//Invoke createEvent() function with dummy event options
		try {
			createEvent(dummy_event_options);
		} catch (e) {
			console.error(e.stack);
		}
	}

	/**
	 * eventOptionHandler() - Handles any events on option click.
	 * @param {String} arg0_event_id - The event ID that is passed to the handler. The original event can be found by truncating characters after the '-'.
	 * @param {String} arg1_option_id - The option ID passed to the handler.
	 */
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
		if (!event_option.disable_click)
			delete main.events[event_id];
	}
}