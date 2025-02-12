//Initialise functions
{
	function getCurrentDate () {
		//Return statement
		return {
			year: Game_Calendar.currentYear,
			month: Game_Calendar.currentMonth,
			day: Game_Calendar.currentDay,
			hour: Game_Calendar.HOUR
		};
	}

	function initialiseOnDateChangeHandler () {
		if (!global.on_date_change) global.on_date_change = {};
		global.on_date_change.logic_loop = function (arg0_date_obj) {
			//Convert from parameters
			var date_obj = arg0_date_obj;

			//Declare local instance variables
			var old_date_obj = date_obj.old_date_obj;
			var new_date_obj = date_obj.new_date_obj;

			//Hour handler
			if (old_date_obj.hour != new_date_obj.hour) {
				//console.log("New hour: ", new_date_obj.hour);
			}

			//Day handler
			if (old_date_obj.day != new_date_obj.day) {
				//console.log("New day: ", new_date_obj.day);
				parseEvents();
			}

			//Month handler
			if (old_date_obj.month != new_date_obj.month) {
				//console.log("New month: ", new_date_obj.month);
			}

			//Year handler
			if (old_date_obj.year != new_date_obj.year) {
				//console.log("New year: ", new_date_obj.year);
			}
		}
	}

	function parseEventAIChances (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var ai_chances = {};
		var all_options_keys = Object.keys(options);
		var total_buttons = 0;

		//Iterate over all_options_keys
		for (var i = 0; i < all_options_keys.length; i++)
			if (all_options_keys.startsWith("btn_"))
				total_buttons++;

		//Iterate over all_options_keys
		for (var i = 0; i < all_options_keys.length; i++)
			if (all_options_keys[i].startsWith("btn_")) {
				var local_ai_chance = 0;
				var local_btn = options[all_options_keys[i]];
				var limit_fulfilled = false;

				if (local_btn.limit)
					limit_fulfilled = local_btn.limit.apply(null, Array.prototype.slice.call(arguments, 1));

				if (limit_fulfilled) {
					local_ai_chance = (local_btn.ai_chance != undefined) ?
						local_btn.ai_chance : 100/total_buttons;
					if (typeof local_ai_chance == "function")
						local_ai_chance = returnSafeNumber(
							local_ai_chance.apply(null, Array.prototype.slice.call(arguments, 1))
						);

					ai_chances[all_options_keys[i]] = local_ai_chance;
				}
			}

		//Return statement
		return standardisePercentage(ai_chances);
	}

	function parseEvents () {
		//Declare local instance variables
		var all_civilisations = getAllCivilisations();
		var all_events = Object.keys(main.scopes.events);
		var all_provinces = getAllProvinces();

		//Iterate over all_events
		for (var i = 0; i < all_events.length; i++) try {
			var local_event = main.scopes.events[all_events[i]];

			if (local_event.type == "civ_event" || local_event.type == "mission_event") {
				if (local_event.limit)
					for (var x = 0; x < all_civilisations.length; x++)
						if (local_event.limit(all_civilisations[x])) try {
							var localisation_variables = {
								FROM: all_civilisations[x],
								FROM_TAG: getCurrentTag(all_civilisations[x])
							};

							//Fire event - Player handler
							if (civilisationIsPlayer(all_civilisations[x])) {
								//Format event for player display
								var all_local_event_keys = Object.keys(local_event);
								var actual_options = {};

								actual_options.event_type = local_event.type;
								actual_options.province_id = getCivilisationCapital(all_civilisations[x]).getProvinceID();

								if (local_event.effect)
									local_event.effect(localisation_variables);
								if (local_event.immediate)
									local_event.immediate(localisation_variables);
								if (local_event.name)
									actual_options.name = parseDisplayLocalisationString(local_event.name, localisation_variables);
								if (local_event.description)
									actual_options.description = parseDisplayLocalisationString(local_event.description, localisation_variables);
								actual_options.options = {};

								//Iterate over all_local_event_keys
								for (var y = 0; y < all_local_event_keys.length; y++)
									if (all_local_event_keys[y].startsWith("btn_")) try {
										var local_option = local_event[all_local_event_keys[y]];
										var limit_fulfilled = true;

										if (local_option.limit)
											limit_fulfilled = local_option.limit(localisation_variables);

										if (limit_fulfilled) {
											if (local_option.name)
												local_option.name = parseDisplayLocalisationString(local_option.name, localisation_variables);

											actual_options.options[all_local_event_keys[y]] = local_option;
										}
									} catch (e) {
										console.log(e.stack);
									}

								//Display event to player
								createEvent(actual_options);
							}
							//Fire event - AI handler; sort through all btn_<key>s and distribute AI chances
							else {
								var local_ai_chances = parseEventAIChances(local_event, localisation_variables);
								var local_current_chance = 0;
								var local_seed = Math.random();

								var all_local_ai_chances = Object.keys(local_ai_chances);

								for (var y = 0; y < all_local_ai_chances.length; y++) {
									var local_ai_chance = local_ai_chances[all_local_ai_chances[y]];

									if (
										local_seed > local_current_chance &&
										local_seed <= (local_current_chance + local_ai_chance)
									) {
										if (local_event[all_local_ai_chances[y]].effect)
											local_event[all_local_ai_chances[y]].effect(all_civilisations[x]);
										break;
									}

									local_current_chance += local_ai_chance;
								}
							}
						} catch (e) {
							console.log(e.stack);
						}
			} else if (local_event.type == "province_event") {
				if (local_event.limit)
					for (var x = 0; x < all_provinces.length; x++)
						if (local_event.limit(all_provinces[x])) {
							var province_civilisation = getProvinceOwner(all_provinces[x], { return_object: true });

							var localisation_variables = {
								FROM: all_provinces[x],
								CIV_OBJ: province_civilisation,
								CIV_TAG: getCurrentTag(province_civilisation),
								PROVINCE_ID: all_provinces[x].getProvinceID(),
								PROVINCE_NAME: all_provinces[x].getProvinceName()
							};

							//Fire event - Player handler
							if (civilisationIsPlayer(province_civilisation)) {
								//Format event for player display
								var all_local_event_keys = Object.keys(local_event);
								var actual_options = {};

								actual_options.event_type = local_event.type;
								actual_options.province_id = all_provinces[x].getProvinceID();

								if (local_event.effect)
									local_event.effect(localisation_variables);
								if (local_event.immediate)
									local_event.immediate(localisation_variables);
								if (local_event.name)
									actual_options.name = parseDisplayLocalisationString(local_event.name, localisation_variables);
								if (local_event.description)
									actual_options.description = parseDisplayLocalisationString(local_event.description, localisation_variables);
								actual_options.options = {};

								//Iterate over all_local_event_keys
								for (var y = 0; y < all_local_event_keys.length; y++)
									if (all_local_event_keys[y].startsWith("btn_")) try {
										var local_option = local_event[all_local_event_keys[y]];
										var limit_fulfilled = true;

										if (local_option.limit)
											limit_fulfilled = local_option.limit(localisation_variables);

										if (limit_fulfilled) {
											if (local_option.name)
												local_option.name = parseDisplayLocalisationString(local_option.name, localisation_variables);
											actual_options.options[all_local_event_keys[y]] = local_option;
										}
									} catch (e) {
										console.log(e.stack);
									}

								//Display event to player
								createEvent(actual_options);
							}
							//Fire event - AI handler; sort through all btn_<key>s and distribute AI chances
							else {
								var local_ai_chances = parseEventAIChances(local_event, localisation_variables);
								var local_current_chance = 0;
								var local_seed = Math.random();

								var all_local_ai_chances = Object.keys(local_ai_chances);

								for (var y = 0; y < all_local_ai_chances.length; y++) {
									var local_ai_chance = local_ai_chances[all_local_ai_chances[y]];

									if (
										local_seed > local_current_chance &&
										local_seed <= (local_current_chance + local_ai_chance)
									) {
										if (local_event[all_local_ai_chances[y]].effect)
											local_event[all_local_ai_chances[y]].effect(all_provinces[x]);
										break;
									}

									local_current_chance += local_ai_chance;
								}
							}
						}
			}
		} catch (e) {
			console.log(e.stack);
		}
	}
}