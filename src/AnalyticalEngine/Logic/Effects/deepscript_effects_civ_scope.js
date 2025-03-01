//Initialise functions
{
	//Economy - (Civilisation).
	{
		function civilisationAddMoney (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				local_civ.fGold += value;
			}
		}

		function civilisationAddMonthlyIncome (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				local_civ.civBonuses.MonthlyIncome += value;
			}
		}
	}

	//Economy - (Technology).
	{
		function civilisationAddResearch (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				local_civ.addResearchProgress(value);
			}
		}

		function civilisationInstantResearch (arg0_civ_tags, arg1_technology_name) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var technology_name = returnSafeNumber(arg1_technology_name);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				instantResearch(local_civ, technology_name);
			}
		}
	}

	//Meta.
	{
		function civilisationAddTemporaryModifier (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.duration = returnSafeNumber(options.duration, 1);
			options.name = (options.name) ? options.name : "Temporary Modifier";

			//Declare local instance variables
			var current_date_obj = getCurrentDate();
			var modifier_dictionary = getModifierDictionary();

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var temp_modifiers = [];

				if (!getCivilisationVariable(local_civ, "temporary_modifiers"))
					setCivilisationVariable(local_civ, []);
				temp_modifiers = getCivilisationVariable(local_civ, "temporary_modifiers");

				if (!options.id) options.id = generateRandomID(temp_modifiers);

				//Create CivilizationBonuses
				var civ_bonus_obj = new CivilizationBonuses();
					civ_bonus_obj.id = options.id;
					civ_bonus_obj.name = options.name;

				//Set duration after adding
				civ_bonus_obj.tempTurnID = Game_Calendar.TURN_ID + daysInTotalMonths(current_date_obj, options.duration);

				//Iterate over all options and check for modifier_dictionary listings
				var all_options_keys = Object.keys(options);

				for (var x = 0; x < all_options_keys.length; x++)
					if (modifier_dictionary[all_options_keys[x]]) {
						var local_key = modifier_dictionary[all_options_keys[x]];
						var local_value = returnSafeNumber([all_options_keys[x]]);

						civ_bonus_obj[local_key] += local_value;
					}

				//Add constructed civ_bonus_obj to local_civ
				local_civ.addCivilizationBonus_Temporary(civ_bonus_obj);
			}
		}

		function civilisationRemoveTemporaryModifier (arg0_civ_tags, arg1_modifier_id) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var modifier_id = arg1_modifier_id;

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				//Iterate over local_civ.civBonusesTemporary
				for (var i = 0; i < local_civ.civBonusesTemporary.size(); i++) {
					var local_civ_bonus = local_civ.civBonusesTemporary.get(i);

					if (local_civ_bonus.id != null)
						if (local_civ_bonus.id == modifier_id) {
							civ_obj.updateCivilizationBonuses_Temporary(local_civ_bonus, -1);
							civ_obj.civBonusesTemporary.remove(i);
							civ_obj.iCivBonusesTemporarySize = civ_obj.civBonusesTemporary.size();
						}
				}
			}
		}

		function civilisationScaleTemporaryModifier (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.duration = returnSafeNumber(options.duration);
			options.value = returnSafeNumber(options.value, 1);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				//Iterate over local_civ.civBonusesTemporary
				for (var i = 0; i < local_civ.civBonusesTemporary.size(); i++) {
					var local_civ_bonus = local_civ.civBonusesTemporary.get(i);

					if (local_civ_bonus.id != null)
						if (local_civ_bonus.id == modifier_id) {
							local_civ_bonus.tempTurnID += (local_civ_bonus.tempTurnID - Game_Calendar.TURN_ID)*options.duration;
							civ_obj.updateCivilizationBonuses_Temporary(local_civ_bonus, options.value);
						}
				}
			}
		}
	}

	//Military.
	{
		function civilisationAddGeneral (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.bonus_attack = returnSafeNumber(options.bonus_attack);
			options.bonus_defence = returnSafeNumber(options.bonus_defence);

			//Load general from file
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);
				var local_file = options.file.replace(".json", "").replace(".JSON", "");

				CharactersManager.loadGeneral(local_civ_id, local_file, new Integer(options.bonus_attack), new Integer(options.bonus_defence));
			}
		}

		function civilisationAddGenerals (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < value; x++)
					local_civ.addGeneral(CivilizationGeneralsPool.getGeneral_Random(local_civ.getCivID()));
			}
		}

		function civilisationCreateArmy (arg0_civ_tags, arg1_options) { //[WIP] - Finish function body
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Declare local instance variables
			var all_armies = [];
			var all_options_keys = Object.keys(options);
			var keywords_dictionary = ["general", "name", "provinces"];
			var units_obj = getUnitsObject();

			//Instantiate armies belonging to civ_tags
			if (options.provinces)
				for (var i = 0; i < options.provinces.length; i++) {
					var local_province = getProvince(options.provinces[i]);
					var local_province_id = local_province.getProvinceID();

					for (var x = 0; x < civ_tags.length; x++) {
						//Create new ArmyDivision
						var local_civ_id = getCivilisationID(civ_tags[x]);
						var local_general;
						var local_regiments = new ArrayList();

						//Populate local_general
						if (typeof options.general == "string") {
							local_general = getGeneral(local_general);
						} else if (typeof options.general == "object") {
							try {
								if (!options.general.name) options.general.name = "";
								options.general.attack = returnSafeNumber(options.general.attack);
								options.general.defence = returnSafeNumber(options.general.defence);
								if (!options.general.year_of_birth) options.general.year_of_birth = getCurrentDate().year;
								options.general.image_file = "";
								options.general.image_id = returnSafeNumber(options.general.image_id);

								local_general = new ArmyGeneral(
									options.general.name,
									options.general.attack,
									options.general.defence,
									options.general.year_of_birth,

									options.general.image_file,
									options.general.image_id
								);
							} catch (e) {}
						}

						//Iterate over all_options_keys to fetch unit types
						for (var y = 0; y < all_options_keys.length; y++)
							if (!keywords_dictionary.includes(all_options_keys[y])) {
								var local_unit_key = getUnit(all_options_keys[y], { return_key: true });
								var local_unit_index = units_obj[local_unit_key];

								var local_regiment = new ArmyRegiment(local_unit_index[1], local_unit_index[0]);

								//Add local_regiment
								local_regiments.add(local_regiment);
							}

						//Create new ArmyDivision
						var local_army;

						try {
							if (local_general) {
								local_army = new ArmyDivision(
									local_civ_id, //(nCivID)
									local_province_id, //(nProvinceID)
									local_regiments, //(nArmyRegiment)
									local_general //(armyGeneral)
								);
							} else {
								local_army = new ArmyDivision(
									local_civ_id, //(nCivID)
									local_province_id, //(nProvinceID)
									local_regiments //(nArmyRegiment)
								);
							}
						} catch (e) {
							console.log(e.stack);
						}

						//Add local_army
						if (local_army) {
							local_province.addArmy(local_army);
							all_armies.push(local_army);
						}
					}
				}

			//Return statement
			return all_armies;
		}

		function civilisationDisbandArmy (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.provinces) {
				options.provinces = getAllProvinces();
			} else {
				options.provinces = getList(options.provinces);

				for (var i = 0; i < options.provinces.length; i++)
					options.provinces[i] = getProvince(options.provinces[i]);
			}
			options.value = returnSafeNumber(options.value);

			//Declare local instance variables
			var civ_ids = [];

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_ids.push(getCivilisationID(civ_tags[i]));

			//Iterate over options.provinces
			for (var i = 0; i < options.provinces.length; i++) {
				var local_province = getProvince(options.provinces[i]);
				var province_armies = getProvinceArmies(local_province);

				//Iterate over all province_armies
				for (var x = 0; x < province_armies.length; x++)
					if (civ_ids.includes(province_armies[x].civID))
					if (options.value < 1) {
						var regiments_to_disband = Math.floor(province_armies[x].iArmyRegimentSize*options.value);

						for (var y = 0; y < regiments_to_disband; y++)
							province_armies[x].removeRegiment(regiments_to_disband[y]);
					} else {
						local_province.removeArmy_UnassignGeneral(x);
						local_province.removeArmy(x);
					}
			}
		}

		function civilisationExpandArmy (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.provinces) {
				options.provinces = getAllProvinces();
			} else {
				options.provinces = getList(options.provinces);

				for (var i = 0; i < options.provinces.length; i++)
					options.provinces[i] = getProvince(options.provinces[i]);
			}
			options.value = returnSafeNumber(options.value, 1);

			//Declare local instance variables
			var civ_ids = [];

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_ids.push(getCivilisationID(civ_tags[i]));

			//Iterate over options.provinces
			for (var i = 0; i < options.provinces.length; i++) {
				var local_province = getProvince(options.provinces[i]);
				var province_armies = getProvinceArmies(local_province);

				//Iterate over all province_armies
				for (var x = 0; x < province_armies.length; x++)
					if (civ_ids.includes(province_armies[x].civID))
						for (var y = 0; y <  province_armies[x].lArmyRegiment.size(); y++) {
							var local_army_regiment = province_armies[x].lArmyRegiment.get(y);

							local_army_regiment.num = new Integer(local_army_regiment.num*options.value);
						}
			}
		}
	}

	//Politics (Internal).
	{
		function civilisationAddAdvisor (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.value = returnSafeNumber(options.value);

			//Declare local instance variables
			var civ_ids = [];

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_ids.push(getCivilisationID(civ_tags[i]));

			//Iterate over all civ_ids
			for (var i = 0; i < civ_ids.length; i++)
				if (options.file) {
					var local_file = options.file.replace(".json", "").replace(".JSON", "");

					CharactersManager.loadAdvisor(civ_ids[i], local_file, options.value);
				} else {
					var local_outcome = new EventOutcome_AddAdvisor(new Integer(options.value));

					local_outcome.updateCiv(civ_ids[i], 1);
				}
		}

		function civilisationChangeGovernment (arg0_civ_tags, arg1_government_name) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var government_name = arg1_government_name;

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				setCivilisationIdeology(civ_tags[i], government_name);
		}

		function civilisationChangeReligion (arg0_civ_tags, arg1_religion_name) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var religion_name = arg1_religion_name;

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				setCivilisationIdeology(civ_tags[i], religion_name);
		}

		function civilisationSetRuler (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.name) options.name = "";
			if (!options.birth_date) options.birth_date = getCurrentDate();
			if (!options.bonus) options.bonus = {};
			if (!options.image) options.image = "";

			//Declare local instance variables
			var all_bonus_keys = Object.keys(options.bonus);
			var modifier_dictionary = getModifierDictionary();

			var event_outcome_obj = EventOutcome_AddRuler(
				options.name, //(sName)
				"", //(sSurname)
				options.image, //(imageID)
				options.birth_date.day, //(BornDay)
				options.birth_date.month, //(BornMonth)
				options.birth_date.year //(BornYear)
			);

			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_id = local_civ.getCivID();

				event_outcome_obj.updateCiv(civ_tags[i]);

				//Apply bonuses; iterative over all_bonus_keys
				for (var x = 0; x < all_bonus_keys.length; x++) {
					var local_bonus_key = modifier_dictionary[all_bonus_keys[x]];
					var local_value = options.bonus[all_bonus_keys[x]];

					local_civ.ruler.rulerBonuses[local_bonus_key] += local_value;
				}
				local_civ.ruler.updateCivBonuses(local_civ_id, 1);
			}
		}

		function civilisationSetTag (arg0_civ_tag, arg1_civ_tag) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var ot_civ_tag = arg1_civ_tag;

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			Gdx.app.postRunnable(function(){
				civ_obj.setCivTag(ot_civ_tag);
				civ_obj.updateCivilizationTAG();
				civ_obj.loadFlag();
			});
		}
	}

	//Provinces.
	{
		function civilisationAddCapitalBuilding (arg0_civ_tags, arg1_capital_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_key = arg1_capital_building_key;
			var value = returnSafeNumber(arg2_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				var current_building_level = getCapitalBuildingLevel(local_civ, capital_building_key, value);

				//Set new capital building level
				setCapitalBuildingLevel(local_civ, capital_building_key, current_building_level + value);
			}
		}

		function civilisationAddCores (arg0_civ_tags, arg1_provinces) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var provinces = getList(arg1_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				provinces[i] = getProvince(provinces[i]);

			//Iterate over all civ_tags to assign cores
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);

				//Iterate over all provinces
				for (var x = 0; x < provinces.length; x++)
					provinces[x].addCore_Just(local_civ_id);
			}
		}

		function civilisationAnnex (arg0_civ_tag, arg1_civ_tags) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < local_civ.getNumOfProvinces(); x++)
					setProvinceOwner(local_civ.getProvinceID(x), local_civ);
			}
		}

		function civilisationAnnexCores (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Declare local instance variables
			var all_provinces = getAllProvinces();
			var civ_ids = [];

			//Iterate over all civ_ids
			for (var i = 0; i < civ_tags.length; i++)
				civ_ids.push(getCivilisationID(civ_tags[i]));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_ids.length; i++)
				for (var x = 0; x < all_provinces.length; x++)
					if (all_provinces[x].haveACore(civ_ids[i]))
						setProvinceOwner(all_provinces[x], civ_ids[i]);
		}

		function civilisationAnnexCoresFromCivilisation (arg0_civ_tags, arg1_options) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.tag) options.tag = [];
			if (!options.provinces) {
				options.provinces = [];

				//Iterate over all options.tag
				for (var i = 0; i < options.tag.length; i++) {
					var local_civ = getCivilisation(civ_tags[i]);
					var local_civ_id = local_civ.getCivID();

					for (var x = 0; x < local_civ.getNumOfProvinces(); x++)
						options.provinces.push(getProvince(local_civ.getProvinceID(x)));
				}
			}

			//Declare local instance variables
			var all_provinces = getAllProvinces();
			var civ_ids = [];

			//Iterate over all civ_ids
			for (var i = 0; i < civ_tags.length; i++)
				civ_ids.push(getCivilisationID(civ_tags[i]));

			//Annex province; Iterate over all civ_tags
			for (var i = 0; i < civ_ids.length; i++)
				for (var x = 0; x < options.provinces.length; x++)
					if (options.provinces[x].haveACore(civ_ids[i]))
						setProvinceOwner(all_provinces[x], civ_ids[i]);
		}

		function civilisationAnnexProvinces (arg0_civ_tag, arg1_provinces) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var provinces = getList(arg1_provinces);

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			//Iterate over all civ_tags
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				setProvinceOwner(local_province, civ_obj);
			}
		}

		function civilisationAnnexProvincesFromCivilisation (arg0_civ_tag, arg1_options) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.tag) options.tag = [];
			if (!options.provinces) options.provinces = [];

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			//Iterate over all options.tag
			for (var i = 0; i < options.tag.length; i++)
				options.tag[i] = getCurrentTag(options.tag[i]);

			//Iterate over all options.provinces
			for (var i = 0; i < options.provinces.length; i++) {
				var local_province = getProvince(options.provinces[i]);

				if (options.tag.includes(getProvinceOwner(local_province)))
					setProvinceOwner(local_province, civ_obj);
			}
		}

		function civilisationExplode (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);
				var local_civ_tag = getCurrentTag(civ_tags[i]);
				var local_explode_obj = new EventOutcome_Explode(local_civ_tag);

				local_explode_obj.updateCiv(local_civ_id, 0);
			}
		}

		function civilisationMoveCapital (arg0_civ_tags, arg1_provinces) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var provinces  = getList(arg1_provinces);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_new_capital_province_id = getProvince(provinces[i]).getProvinceID();

				//Set new capital
				local_civ.moveCapital(local_new_capital_province_id);
			}
		}

		function civilisationOccupyProvinces (arg0_civ_tag, arg1_provinces) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var provinces = getList(arg1_provinces);

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			//Iterate over all civ_tags
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				setProvinceController(local_province, civ_obj);
			}
		}

		function civilisationOccupyProvincesFromCivilisation (arg0_civ_tag, arg1_provinces) {
			//Convert from parameters
			var civ_tag = arg0_civ_tag;
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			if (!options.tag) options.tag = [];
			if (!options.provinces) options.provinces = [];

			//Declare local instance variables
			var civ_obj = getCivilisation(civ_tag);

			//Iterate over all options.tag
			for (var i = 0; i < options.tag.length; i++)
				options.tag[i] = getCurrentTag(options.tag[i]);

			//Iterate over all options.provinces
			for (var i = 0; i < options.provinces.length; i++) {
				var local_province = getProvince(options.provinces[i]);

				if (options.tag.includes(getProvinceOwner(local_province)))
					setProvinceController(local_province, civ_obj);
			}
		}

		function civilisationRemoveCapitalBuilding (arg0_civ_tags, arg1_capital_building_key, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var capital_building_key = arg1_capital_building_key;
			var value = returnSafeNumber(arg2_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				var current_building_level = getCapitalBuildingLevel(local_civ, capital_building_key, value);

				//Set new capital building level
				setCapitalBuildingLevel(local_civ, capital_building_key, current_building_level - value);
			}
		}

		function civilisationRemoveCores (arg0_civ_tags, arg1_provinces) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var provinces = getList(arg1_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				provinces[i] = getProvince(provinces[i]);

			//Iterate over all civ_tags to assign cores
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);

				//Iterate over all provinces
				for (var x = 0; x < provinces.length; x++)
					provinces[x].removeCore(local_civ_id);
			}
		}
	}
}

//Internal helper functions
{
	function getCapitalBuildingLevel (arg0_civ_tag, arg1_capital_building_key, arg2_value) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var capital_building_key = arg1_capital_building_key;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		if (capital_building_key == "capital_city") {
			return civ_obj.getCapitalLevel();
		} else if (capital_building_key == "military_academy") {
			return civ_obj.getMilitaryAcademyLevel();
		} else if (capital_building_key == "military_academy_for_generals") {
			return civ_obj.getMilitaryAcademyForGeneralsLevel();
		} else if (capital_building_key == "nuclear_reactor") {
			return civ_obj.getNuclearReactorLevel();
		} else if (capital_building_key == "supreme_court") {
			return civ_obj.getSupremeCourtLevel();
		}
	}

	function getModifierDictionary () {
		//Return statement
		return {
			advisor_cost: "AdvisorCost",
			advisor_max_level: "AdvisorMaxLevel",
			aggressive_expansion_modifier: "AggressiveExpansion",
			all_characters_life_expectancy: "AllCharactersLifeExpectancy",
			army_maintenance: "ArmyMaintenance",
			army_morale_recovery: "ArmyMoraleRecovery",
			army_movement_speed: "ArmyMovementSpeed",
			battle_width: "BattleWidth",
			building_maintenance_cost: "BuildingsMaintenanceCost",
			construction_cost: "ConstructionCost",
			construction_time: "ConstructionTime",
			core_cost: "CoreCost",
			corruption: "Corruption",
			devastation: "Devastation",
			develop_infrastructure_cost: "DevelopInfrastructureCost",
			diplomacy_points: "DiplomacyPoints",
			discipline: "Discipline",
			disease_death_rate: "DiseaseDeathRate",
			economy_income: "IncomeEconomy",
			generals_attack: "GeneralAttack",
			generals_cost: "GeneralCost",
			generals_defence: "GeneralDefence",
			growth_rate: "GrowthRate",
			improve_relations_modifier: "ImproveRelationsModifier",
			income_from_vassals: "IncomeFromVassals",
			increase_growth_rate_cost: "IncreaseGrowthRateCost",
			increase_manpower_cost: "IncreaseManpowerCost",
			increase_tax_efficiency_cost: "IncreaseTaxEfficiencyCost",
			inflation: "Inflation",
			invest_in_economy_cost: "InvestInEconomyCost",
			loan_interest: "LoanInterest",
			loan_limit: "MaxNumberOfLoans",
			loot: "Loot",
			manpower_recovery_from_disbanded_armies: "ManpowerRecoveryFromADisbandedArmy",
			manpower_recovery_speed: "ManpowerRecoverySpeed",
			max_alliances: "MaxNumOfAlliances",
			max_amount_of_money: "MaximumAmountOfGold",
			max_building_slots: "BuildingSlot",
			max_infrastructure: "MaxInfrastructure",
			max_level_capital_city: "MaximumLevelOfCapitalCity",
			max_level_military_academy:  "MaximumLevelOfTheMilitaryAcademy",
			max_level_military_academy_for_generals: "MaximumLevelOfTheMilitaryAcademyForGenerals",
			max_level_nuclear_reactor: "MaximumLevelOfNuclearReactor",
			max_level_supreme_court: "MaximumLevelOfTheSupremeCourt",
			max_manpower: "MaxManpower",
			max_morale: "MaxMorale",
			max_percentage_of_manpower: "MaxManpower_Percentage",
			max_percentage_of_money: "MaximumAmountOfGold_Percentage",
			military_maintenance_cost: "MaintenanceCost",
			monthly_income: "MonthlyIncome",
			monthly_legacy: "MonthlyLegacy",
			monthly_legacy_percentage: "MonthlyLegacy_Percentage",
			production_efficiency: "ProductionEfficiency",
			production_income: "ProductionIncome",
			province_maintenance: "ProvinceMaintenance",
			recruit_army_cost: "RecruitArmyCost",
			recruit_backline_cost: "RecruitArmySecondLineCost",
			recruit_frontline_cost: "RecruitArmyFirstLineCost",
			recruitment_time: "RecruitmentTime",
			regiments_limit: "RegimentsLimit",
			reinforcement_speed: "ReinforcementSpeed",
			religious_conversion_cost: "ReligionCost",
			research: "Research",
			research_points: "ResearchPoints",
			revolutionary_risk: "RevolutionaryRisk",
			siege_effectiveness: "SiegeEffectiveness",
			tax_efficiency: "TaxEfficiency",
			tax_income: "IncomeTaxation",
			technology_cost: "TechnologyCost",
			unit_attack: "UnitsAttack",
			unit_defence: "UnitsDefense",
			warscore_cost: "WarScoreCost",

			administrative_buildings_cost: "AdministrationBuildingsCost",
			economy_buildings_cost: "EconomyBuildingsCost",
			military_buildings_cost: "MilitaryBuildingsCost",
			wonder_buildings_cost: "WonderConstructionCost"
		};
	}

	function setCapitalBuildingLevel (arg0_civ_tag, arg1_capital_building_key, arg2_value) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var capital_building_key = arg1_capital_building_key;
		var value = returnSafeNumber(arg2_value);

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		var civ_id = civ_obj.getCivID();

		if (capital_building_key == "capital_city") {
			//Reset trackers
			civ_obj.civBonuses.MonthlyIncome -= Game.getCapital_Income(civ_id);
			civ_obj.civBonuses.ProvinceMaintenance -= Game.getCapital_ProvincesMaintenance(civ_id)*100;

			//Set capital_city level
			civ_obj.setCapitalLevel(value);

			//Set new trackers
			civ_obj.buildCapitalCity_Bonuses();
			Game.gameThread.addCivUpdateTotalIncomePerMonth(civ_id);
		} else if (capital_building_key == "military_academy") {
			//Reset trackers
			civ_obj.civBonuses.UnitsAttack -= Game.getMilitaryAcademy_Attack(civ_id);
			civ_obj.civBonuses.UnitsDefense -= Game.getMilitaryAcademy_Defense(civ_id);
			civ_obj.civBonuses.MaintenanceCost -= Game.getMilitaryAcademy_MaintenanceCost(civ_id);
			civ_obj.civBonuses.RegimentsLimit -= Game.getMilitaryAcademy_RegimentsLimit(civ_id);

			//Set military_academy level
			civ_obj.setMilitaryAcademyLevel(value);

			//Set new trackers
			civ_obj.buildMilitaryAcademy_Bonuses();
			Game.gameThread.addCivUpdateTotalIncomePerMonth(civ_id);
			civ_obj.updateRegimentsLimit();
		} else if (capital_building_key == "military_academy_for_generals") {
			//Reset trackers
			civ_obj.civBonuses.GeneralAttack -= Game.getMilitaryAcademyForGenerals_GeneralAttack(civ_id);
			civ_obj.civBonuses.GeneralDefense -= Game.getMilitaryAcademyForGenerals_GeneralDefense(civ_id);
			civ_obj.civBonuses.MaintenanceCost -= Game.getMilitaryAcademyForGenerals_MaintenanceCost(civ_id);

			//Set military_academy_for_generals level
			civ_obj.setMilitaryAcademyForGeneralsLevel(value);

			//Set new trackers
			civ_obj.buildMilitaryAcademyForGenerals_Bonuses();
			Game.gameThread.addCivUpdateTotalIncomePerMonth(civ_id);
		} else if (capital_building_key == "nuclear_reactor") {
			//Reset trackers
			civ_obj.civBonuses.ProductionEfficiency -= Game.getNuclearReactor_ProductionEfficiency(civ_id);

			//Set nuclear_reactor level
			civ_obj.setNuclearReactorLevel(value);

			//Set new trackers
			civ_obj.buildNuclearReactor_Bonuses();
			Game.gameThread.addCivUpdateTotalIncomePerMonth(civ_id);
		} else if (capital_building_key == "supreme_court") {
			//Set supreme_court level
			civ_obj.addCivUpdateTotalIncomePerMonth(value);

			//Set new trackers
			civ_obj.buildSupremeCourt_Bonuses();

			//Iterate over all provinces owned by the civ and update their income
			for (var i = 0; i < civ_obj.getNumOfProvinces(); i++)
				Game.getProvince(civ_obj.getProvinceID(i)).updateProvinceIncome();
			Game.gameThread.addCivUpdateTotalIncomePerMonth(civ_id);
		}
	}
}