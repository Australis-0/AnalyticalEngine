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
			var modifier_dictionary = {
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
		
	}
}