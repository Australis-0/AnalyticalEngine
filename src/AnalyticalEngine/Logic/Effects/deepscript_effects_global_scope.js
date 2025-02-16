//Initialise functions
{
	//1. Meta.
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

		function runFunction (arg0_function_key) {
			//Convert from parameters
			var function_key = arg0_function_key;

			//Attempt function call
			try {
				this[function_key].apply(null, Array.prototype.slice.call(arguments, 1));
			} catch (e) {
				console.error("Attempting to call " + function_key + "(). Error:");
				console.error(e.stack);
			}
		}
	}

	//2. AI
	{
		function setAIAggression (arg0_value) {
			//Convert from parameters
			var value = returnSafeNumber(arg0_value);

			//Set AI Aggressiveness
			Game.aiAggressivnes = value;
		}
	}

	//3. Diplomacy.
	{
		function createWar (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Initialise options
			options.attackers = (options.attackers) ? getList(options.attackers) : [];
			options.defenders = (options.defenders) ? getList(options.defenders) : [];

			options.conquer_vassal = (options.conquer_vassal) ? true : false;
			options.is_coalition = (options.is_coalition) ? true : false;

			//Declare local instance variables
			var attacker_id = getCivilisationID(options.attackers[0]);
			var defender_id = getCivilisationID(options.defenders[0]);
			var war_key = WarManager.addWar(attacker_id, defender_id, options.conquer_vassal, options.is_coalition, null);
			var war_obj = WarManager.lWars.get(war_key);

			if (options.name)
				war_obj.name = options.name;
			if (options.attackers.length > 1)
				for (var i = 1; i < options.attackers.length; i++) {
					var local_attacker_id = getCivilisationID(options.attackers[i]);

					if (local_attacker_id)
						war_obj.addAggressor(local_attacker_id);
				}
			if (options.defenders.length > 1)
				for (var i = 1; i < options.defenders.length; i++) {
					var local_defender_id = getCivilisationID(options.defenders[i]);

					if (local_defender_id)
						war_obj.addDefender(local_defender_id);
				}

			war_obj.loadSave_AddInWar();
		}
	}

	//4. Player.
	{
		function switchTag (arg0_civ_tag) { //[WIP] - Needs to be adapted to multiplayer by synchronising tag switch states across players. Test in MP. Doesn't work immediately after game start.
			//Convert from parameters
			var civ_tag = arg0_civ_tag;

			//Declare local instance variables
			var actual_civ_id = getCivilisationID(civ_tag);

			if (actual_civ_id) {
				for (var i = 0; i < M_Players.players.size(); i++)
					if (M_Players.players.get(i).civID == Game.player.iCivID)
						M_Players.players.get(i).civID = actual_civ_id;

				Game.player = new Player();
				Game.player.iCivID = actual_civ_id;
				Game.player.fog.initFogOfWar();
				Game.player.loadFormableCivs();

				if (SteamMultiManager.isHost()) {
					M_Players.updateCivToHost();
				} else {
					M_Players.updateHostCiv();
				}
				NewGame.play();
			}
		}
	}

	//5. Pops.
	{
		function createDisease (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Declare local instance variables
			
		}
	}

	//6. International Organisation Scope Effects.

	//7. Resource Scope Effects.
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