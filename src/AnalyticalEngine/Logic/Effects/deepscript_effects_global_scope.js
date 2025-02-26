//Initialise functions
{
	//0.1. Variables - (Civilisation).
	{
		function getCivilisationFlag (arg0_civ_tag, arg1_flag_key, arg2_options) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var flag_key = arg1_flag_key;
			var options = (arg2_options) ? arg2_options : {};

			//Declare local instance variables
			var current_tag = getCurrentTag(civ_tag);
			var return_index = [];
			var return_value;

			if (main.gamestate.civilisations[current_tag])
				if (main.gamestate.civilisations[current_tag][flag_key]) {
					return_index = [current_tag, flag_key];
					return_value = main.gamestate.civilisations[current_tag][flag_key];
				}
			if (return_value == undefined)
				if (typeof civ_tag == "string")
					if (!civ_tag.includes("_")) {
						var base_tag = current_tag.split("_")[0];

						if (main.gamestate.civilisations[base_tag])
							if (main.gamestate.civilisations[base_tag][flag_key]) {
								return_index = [base_tag, flag_key];
								return_value = main.gamestate.civilisations[base_tag][flag_key];
							}
					}

			//Return statement
			return (!options.return_index) ? return_value : return_index;
		}

		function getCivilisationFlags (arg0_civ_tags, arg1_flag_key, arg2_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var flag_key = arg1_flag_key;
			var options = (arg2_options) ? arg2_options : {};

			//Declare local instance variables
			var return_array = [];

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Populate return_array; iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				return_array.push(getCivilisationFlag(civ_tags[i], flag_key, options));

			//Return statement
			return return_array;
		}

		function getCivilisationVariable (arg0_civ_tag, arg1_variable_key, arg2_options) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var variable_key = arg1_variable_key;
			var options = (arg2_options) ? arg2_options : {};

			//Declare local instance variables
			var current_tag = getCurrentTag(civ_tag);
			var return_index = [];
			var return_value;

			if (main.gamestate.civilisations[current_tag])
				if (main.gamestate.civilisations[current_tag][variable_key]) {
					return_index = [current_tag, variable_key];
					return_value = main.gamestate.civilisations[current_tag][variable_key];
				}
			if (return_value == undefined)
				if (typeof civ_tag == "string")
					if (!civ_tag.includes("_")) {
						var base_tag = current_tag.split("_")[0];

						if (main.gamestate.civilisations[base_tag])
							if (main.gamestate.civilisations[base_tag][variable_key]) {
								return_index = [base_tag, variable_key];
								return_value = main.gamestate.civilisations[base_tag][variable_key];
							}
					}

			//Return statement
			return (!options.return_index) ? return_value : return_index;
		}

		function getCivilisationVariables (arg0_civ_tags, arg1_variable_key, arg2_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var variable_key = arg1_variable_key;
			var options = (arg2_options) ? arg2_options : {};

			//Declare local instance variables
			var return_array = [];

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Populate return_array; iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				return_array.push(getCivilisationVariable(civ_tags[i], variable_key, options));

			//Return statement
			return return_array;
		}

		function setCivilisationFlag (arg0_civ_tag, arg1_flag_key) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var flag_index = getCivilisationFlag(civ_tag, { return_index: true });

			//Set flag index
			if (flag_index) {
				main.gamestate.civilisations[flag_index[0]][flag_index[1]] = true;
			} else {
				if (typeof civ_tag == "string")
					if (civ_tag.includes("_")) {
						initCivilisation(civ_tag);
						main.gamestate.civilisations[civ_tag][flag_key] = true;
					} else {
						var current_tag = getCurrentTag(civ_tag);

						initCivilisation(current_tag);
						main.gamestate.civilisations[current_tag][flag_key] = true;
					}
			}

			//Return statement
			return true;
		}

		function setCivilisationFlags (arg0_civ_tags, arg1_flag_key) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var flag_key = arg1_flag_key;

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Set flag
			for (var i = 0; i < civ_tags.length; i++)
				setCivilisationFlag(civ_tags[i], flag_key);

			//Return statement
			return true;
		}

		function setCivilisationVariable (arg0_civ_tag, arg1_variable_key, arg2_value) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var variable_key = arg1_variable_key;
			var value = arg2_value;

			//Declare local instance variables
			var variable_index = getCivilisationVariable(civ_tag, { return_index: true });

			//Set civilisation variable
			if (variable_index) {
				main.gamestate.civilisations[variable_index[0]][variable_index[1]] = value;
			} else {
				if (typeof civ_tag == "string")
					if (civ_tag.includes("_")) {
						initCivilisation(civ_tag);
						main.gamestate.civilisations[civ_tag][variable_key] = value;
					} else {
						var current_tag = getCurrentTag(civ_tag);

						initCivilisation(current_tag);
						main.gamestate.civilisations[civ_tag][variable_key] = value;
					}
			}

			//Return statement
			return value;
		}

		function setCivilisationVariables (arg0_civ_tags, arg1_variable_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var variable_key = arg1_variable_key;
			var value = getList(arg2_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Set variables
			for (var i = 0; i < civ_tags.length; i++)
				setCivilisationVariable(civ_tags[i], variable_key, value);

			//Return statement
			return value;
		}

		function removeCivilisationFlag (arg0_civ_tag, arg1_flag_key) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var flag_index = getCivilisationFlag(civ_tag, { return_index: true });

			//Delete flag index if it exists
			if (flag_index)
				if (main.gamestate.civilisations[flag_index[0]])
					delete main.gamestate.civilisations[flag_index[0]][flag_index[1]];

			//Return statement
			return false;
		}

		function removeCivilisationFlags (arg0_civ_tags, arg1_flag_key) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var flag_key = arg1_flag_key;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Remove flags
			for (var i = 0; i < civ_tags.length; i++)
				removeCivilisationFlag(civ_tags[i], flag_key);

			//Return statement
			return false;
		}

		function removeCivilisationVariable (arg0_civ_tag, arg1_variable_key) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var variable_key = arg1_variable_key;

			//Declare local instance variables
			var variable_index = getCivilisationVariable(civ_tag, { return_index: true });

			//Delete variable index if it exists
			if (variable_index)
				if (main.gamestate.civilisations[variable_index[0]])
					delete main.gamestate.civilisations[variable_index[0]][variable_index[1]];
		}

		function removeCivilisationVariables (arg0_civ_tags, arg1_variable_key) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var variable_key = arg1_variable_key;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Remove variables
			for (var i = 0; i < civ_tags.length; i++)
				removeCivilisationVariable(civ_tags[i], variable_key);

			//Return statement
			return false;
		}
	}

	//0.2. Variables - (Global).
	{
		function getGlobalFlag (arg0_flag_key) {
			//Convert from parameters
			var flag_key = arg0_flag_key;

			//Return statement
			return main.gamestate.global[flag_key];
		}

		function getGlobalFlags (arg0_flag_keys) {
			//Convert from parameters
			var flag_keys = getList(arg0_flag_keys);

			//Declare local instance variables
			var return_array = [];

			//Iterate over all flag_keys
			for (var i = 0; i < flag_keys.length; i++)
				return_array.push(getGlobalFlag(flag_keys[i]));

			//Return statement
			return return_array;
		}

		function getGlobalVariable (arg0_variable_key) {
			//Convert from parameters
			var variable_key = arg0_variable_key;

			//Return statement
			return main.gamestate.global[variable_key];
		}

		function getGlobalVariables (arg0_variable_keys) {
			//Convert from variables
			var variable_keys = getList(arg0_variable_keys);

			//Declare local instance variables
			var return_array = [];

			//Iterate over all variable_keys
			for (var i = 0; i < variable_keys.length; i++)
				return_array.push(getGlobalVariable(variable_keys[i]));

			//Return statement
			return return_array;
		}

		function setGlobalFlag (arg0_flag_key) {
			//Convert from parameters
			var flag_key = arg0_flag_key;

			//Set flag
			main.gamestate.global[flag_key] = true;

			//Return statement
			return true;
		}

		function setGlobalFlags (arg0_flag_keys) {
			//Convert from parameters
			var flag_keys = getList(arg0_flag_keys);

			//Set flags
			for (var i = 0; i < flag_keys.length; i++)
				setGlobalFlag(flag_keys[i]);

			//Return statement
			return true;
		}

		function setGlobalVariable (arg0_variable_key, arg1_value) {
			//Convert from parameters
			var variable_key = arg0_variable_key;
			var value = arg1_value;

			//Set variable
			main.gamestate.global[variable_key] = value;

			//Return statement
			return value;
		}

		function setGlobalVariables (arg0_variable_keys, arg1_value) {
			//Convert from parameters
			var variable_keys = getList(arg0_variable_keys);
			var value = arg1_value;

			//Set global variables
			for (var i = 0; i < variable_keys.length; i++)
				setGlobalVariable(variable_keys[i]);

			//Return statement
			return true;
		}

		function removeGlobalFlag (arg0_flag_key) {
			//Convert from parameters
			var flag_key = arg0_flag_key;

			//Delete flag
			delete main.gamestate.global[flag_key];
		}

		function removeGlobalFlags (arg0_flag_keys) {
			//Convert from parameters
			var flag_keys = getList(arg0_flag_keys);

			//Delete flags
			for (var i = 0; i < flag_keys.length; i++)
				removeGlobalFlag(flag_keys[i]);
		}

		function removeGlobalVariable (arg0_variable_key) {
			//Convert from parameters
			var variable_key = arg0_variable_key;

			//Delete flag
			delete main.gamestate.global[variable_key];
		}

		function removeGlobalVariables (arg0_variable_keys) {
			//Convert from parameters
			var variable_keys = getList(arg0_variable_keys);

			//Delete flags
			for (var i = 0; i < variable_keys.length; i++)
				removeGlobalVariable(variable_keys[i]);
		}
	}

	//0.3. Variables - (Province).
	{
		function getProvinceFlag (arg0_province, arg1_flag_key) {
			//Convert from parameters
			var province = arg0_province;
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Return statement
			return province_obj[flag_key];
		}

		function getProvinceFlags (arg0_provinces, arg1_flag_key) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var return_array = [];

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++)
				return_array.push(getProvinceFlag(provinces[i], flag_key));

			//Return statement
			return return_array;
		}

		function getProvinceVariable (arg0_province, arg1_variable_key) {
			//Convert from parameters
			var province = arg0_province;
			var variable_key = arg1_variable_key;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Return statement
			return province_obj[variable_key];
		}

		function getProvinceVariables (arg0_provinces, arg1_variable_key) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var variable_key = arg1_variable_key;

			//Declare local instance variables
			var return_array = [];

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++)
				return_array.push(getProvinceVariable(provinces[i], variable_key));

			//Return statement
			return return_array;
		}

		function removeProvinceFlag (arg0_province, arg1_flag_key) {
			//Convert from parameters
			var province = arg0_province;
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Delete flag
			delete province_obj[flag_key];
		}

		function removeProvinceFlags (arg0_provinces, arg1_flag_key) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var flag_key = arg1_flag_key;

			//Delete flags
			for (var i = 0; i < provinces.length; i++)
				removeProvinceFlag(provinces[i], flag_key);
		}

		function removeProvinceVariable (arg0_province, arg1_variable_key) {
			//Convert from parameters
			var province = arg0_province;
			var variable_key = arg1_variable_key;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Delete variable
			delete province_obj[variable_key];
		}

		function removeProvinceVariables (arg0_provinces, arg1_variable_key) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var variable_key = arg1_variable_key;

			//Delete variables
			for (var i = 0; i < provinces.length; i++)
				removeProvinceVariable(provinces[i], variable_key);
		}

		function setProvinceFlag (arg0_province, arg1_flag_key) {
			//Convert from parameters
			var province = arg0_province;
			var flag_key = arg1_flag_key;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Set flag
			province_obj[flag_key] = true;

			//Return statement
			return true;
		}

		function setProvinceFlags (arg0_provinces, arg1_flag_key) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var flag_key = arg1_flag_key;

			//Set province flags
			for (var i = 0; i < provinces.length; i++)
				setProvinceFlag(provinces[i], flag_key);
		}

		function setProvinceVariable (arg0_province, arg1_variable_key, arg2_value) {
			//Convert from parameters
			var province = arg0_province;
			var variable_key = arg1_variable_key;
			var value = arg2_value;

			//Declare local instance variables
			var province_id = getProvince(province).getProvinceID();
			var province_obj = main.gamestate.provinces[province_id];

			//Set variable
			province_obj[variable_key] = value;

			//Return statement
			return value;
		}

		function setProvinceVariables (arg0_provinces, arg1_variable_key, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var variable_key = arg1_variable_key;
			var value = arg2_value;

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				setProvinceVariable(provinces[i], variable_key, value);
		}
	}

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
		function switchTag (arg0_civ_tag) { //[WIP] - Needs to be adapted to multiplayer by synchronising tag switch states across players. Test in MP.
			//Convert from parameters
			var civ_tag = arg0_civ_tag;

			//Declare local instance variables
			var actual_civ_id = getCivilisationID(civ_tag);

			if (actual_civ_id) {
				setTimeout(function(){
					console.log("Switched player to " + civ_tag);
					NewGame.play();
				}, 50);

				for (var i = 0; i < M_Players.players.size(); i++)
					if (M_Players.players.get(i).civID == Game.player.iCivID)
						M_Players.players.get(i).civID = actual_civ_id;

				Game.player = new Player();
				Game.player.iCivID = actual_civ_id;

				ProvinceBorderManager.clearProvinceBorder();
				Game.player.currSituation.buildPlayerLegaciesLVL();
				Game.player.currSituation.updateCurrentSituation();
				ProvinceDrawArmy.updateArmyImgID();
				Game.menuManager.rebuildInGame();

				for (var i = 0; i < Game.getCivsSize(); i++)
					Game.getCiv(i).updateArmyRegimentSize();

				Game.player.loadFormableCivs();
				InGame_Court_Government.reloadFlags = true;

				if (SteamMultiManager.isHost()) {
					M_Players.updateCivToHost();
				} else {
					M_Players.updateHostCiv();
				}

				NewGame.updateArmiesText();
			}
		}
	}

	//5. Pops.
	{
		/**
		 * createDisease() - Spawns in a new disease in the targets given.
		 *
		 * @param {Object} [arg0_options]
		 *  @param {String} [arg0_options.disease_type]
		 *  @param {Array<String>} [arg0_options.provinces=[]]
		 */
		function createDisease (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Initialise options
			if (options.disease_type == undefined) options.disease_type = 0;
			if (!options.provinces) options.provinces = [getRandomProvince()];

			//Declare local instance variables
			var actual_outbreak_province_id = getProvince(options.provinces[0]).getProvinceID();
			var disease_obj = getDisease(options.disease_type);
			var new_disease_obj = new Plague(
				actual_outbreak_province_id, //(outbreakProvince)
				disease_obj.Name, //(sName)
				disease_obj.R, //(fR)
				disease_obj.G, //(fG)
				disease_obj.B, //(fB)
				PlagueManager.activePlagues.size(), //(nPlagueID_InGame)
				disease_obj.DEATH_RATE_MIN + disease_obj.DEATH_RATE_EXTRA*Math.random(), //(fDdeathRate)
				new Integer(disease_obj.DURATION_TURNS_MIN + randomNumber(0, disease_obj.DURATION_TURNS_EXTRA)), //(iDurationTurnsLeft)
				disease_obj.EXPANSION_MODIFIER + disease_obj.EXPANSION_MODIFIER_EXTRA*Math.random(), //(EXPANSION_MODIFIER)
				disease_obj.ImageID, //(iImageID)
				disease_obj.DEVASTATION //(fDevastation)
			);

			if (options.provinces.length > 1)
				for (var i = 1; i < options.provinces.length; i++) {
					var local_province_id = getProvince(options.provinces[i]).getProvinceID();

					new_disease_obj.addProvince(local_province_id);
				}

			//Add new disease and run addProvince() for each extra province not yet added
			PlagueManager.activePlagues.add(new_disease_obj);
		}
	}

	//6. International Organisation Scope Effects.

	//7. Resource Scope Effects.
	{
		function resourceChangePrice (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Iterate over options.resource_type
			if (!options.resource_type) options.resource_type = [];
			for (var i = 0; i < options.resource_type.length; i++)
				options.resource_type[i] = getResourceID(options.resource_type[i]);

			options.duration = returnSafeNumber(options.duration, 1);
			options.value = returnSafeNumber(options.value, 1);

			//Execute effect from ResourcesManager
			for (var i = 0; i < options.resource_type.length; i++)
				ResourcesManager.setPriceChangePerc(options.resource_type[i], options.value, Game_Calendar.TURN_ID + 31*options.duration);
		}

		function resourceChangePriceGroup (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Initialise options
			if (!options.resource_group) options.resource_group = [];

			//Declare local instance variables
			var resource_types = [];

			//Iterate over options.resource_group
			for (var i = 0; i < options.resource_group.length; i++)
				resource_types = resource_types.concat(getResourceGroup(options.resource_group[i], { return_indexes: true }));

			resourceChangePrice({
				resource_type: resource_types,

				duration: options.duration,
				value: options.value
			});
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