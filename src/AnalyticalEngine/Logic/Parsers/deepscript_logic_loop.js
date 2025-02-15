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

	function onDateChangeLogicHandler (arg0_date_obj) {
		//Convert from parameters
		var new_date_obj = (arg0_date_obj) ? arg0_date_obj : getCurrentDate();

		//Declare local instance variables
		var date_change = false;

		if (!global.cache.old_date_obj) {
			global.cache.old_date_obj = new_date_obj;
			date_change = true;
		}
		if (JSON.stringify(global.cache.old_date_obj) != JSON.stringify(new_date_obj))
			date_change = true;


		if (date_change) {
			var old_date_obj = global.cache.old_date_obj;

			//Hour handler
			if (old_date_obj.hour != new_date_obj.hour)
				parseOnGameTick();

			//Day handler
			if (old_date_obj.day != new_date_obj.day) {
				//Parse events on daily tick
				parseOnGameDailyInterval();
				parseEvents();
			}

			//Month handler
			if (old_date_obj.month != new_date_obj.month)
				parseOnGameMonthlyInterval();

			//Year handler
			if (old_date_obj.year != new_date_obj.year)
				parseOnGameYearlyInterval();

			global.cache.old_date_obj = new_date_obj;
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
							fireCivilisationEvent(all_civilisations[x], local_event);
						} catch (e) {
							console.log(e.stack);
						}
			} else if (local_event.type == "province_event") {
				if (local_event.limit)
					for (var x = 0; x < all_provinces.length; x++)
						if (local_event.limit(all_provinces[x])) try {
							fireProvinceEvent(all_provinces[x], local_event);
						} catch (e) {
							console.log(e.stack);
						}
			}
		} catch (e) {
			console.log(e.stack);
		}
	}
}