//Initialise functions
{
	function getAggressiveExpansion (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.getAggressiveExpansion();
	}

	function getAllies (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var ally_tags = [];
		var civ_obj = getCivilisation(civ_tag);

		//Iterate over civ_obj.diplomacy.alliance
		civ_obj.diplomacy.alliance.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			ally_tags.push(local_civ_tag);
		});

		//Return statement
		return ally_tags;
	}

	function getDaysAtWar (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var maximum_days_at_war = 0;

		//Iterate over civ_obj.diplomacy.iWarsSize
		for (var i = 0; i < civ_obj.diplomacy.iWarsSize; i++)
			maximum_days_at_war = Math.max(
				maximum_days_at_war,
				Game_Calendar.TURN_ID - WarManager.lWars.get(civ_obj.diplomacy.lWars.get(i)).iWarTurnID
			);

		//Return statement
		return maximum_days_at_war;
	}

	function getDefensivePacts (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var defensive_pact_tags = [];

		//Iterate over civ_obj.diplomacy.defensivePact
		civ_obj.diplomacy.defensivePact.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			defensive_pact_tags.push(local_civ_tag);
		});

		//Return statement
		return defensive_pact_tags;
	}

	function getEnemies (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var enemies_array = [];

		//Iterate over civ_obj.diplomacy.atWar
		for (var i = 0; i < civ_obj.diplomacy.atWar.size(); i++)
			enemies_array.push(getCurrentTag(civ_obj.diplomacy.atWar.get(i)));

		//Return statement
		return enemies_array;
	}

	function getGuarantees (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var guarantees_array = [];

		//Iterate over civ_obj.diplomacy.guarantee
		civ_obj.diplomacy.guarantee.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			guarantees_array.push(local_civ_tag);
		})

		//Return statement
		return guarantees_array;
	}

	function getGuarantors (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var guarantors_array = [];

		//Iterate over civ_obj.diplomacy.guarantee
		civ_obj.diplomacy.guaranteeByCivID.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			guarantors_array.push(local_civ_tag);
		})

		//Return statement
		return guarantors_array;
	}

	function getOpinion (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_obj = getCivilisation(ot_civ_tag);

		var civ_id = getCivilisationID(civ_obj);
		var ot_civ_id = getCivilisationID(ot_civ_obj);

		//Construct return_obj
		var ot_value = civ_obj.diplomacy.getRelation(ot_civ_id);
		var value = ot_civ_obj.diplomacy.getRelation(civ_id);

		//Return statement
		return {
			civ_tag: getCurrentTag(civ_obj),
			ot_civ_tag: getCurrentTag(ot_civ_obj),

			value: value,
			ot_value: ot_value,
		};
	}

	function getOverlord (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_civ_tags = getAllCurrentCivTags();
		var civ_obj = getCivilisation(civ_tag);

		civ_tag = getCurrentTag(civ_obj);

		//Iterate over all_civ_tags
		for (var i = 0; i < all_civ_tags.length; i++) {
			var local_civ = getCivilisation(all_civ_tags[i]);
			var local_civ_vassals = getVassals(local_civ);

			if (local_civ_vassals.includes(civ_tag))
				//Return statement
				return (options.return_tag) ? getCurrentTag(local_civ) : local_civ;
		}
	}

	function getMilitaryAccess (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var military_access_array = [];

		//Iterate over civ_obj.diplomacy.guarantee
		civ_obj.diplomacy.militaryAccess.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			military_access_array.push(local_civ_tag);
		})

		//Return statement
		return military_access_array;
	}

	function getNeighbours (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var neighbour_tags = [];

		//Iterate over civ_obj.civNeighbors.civs
		for (var i = 0; i < civ_obj.civNeighbors.civs.size(); i++) try {
			neighbour_tags.push(getCurrentTag(civ_obj.civNeighbors.civs.get(i).civID));
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return neighbour_tags;
	}

	function getNonAggressionPacts (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var non_aggression_pact_tags = [];

		//Iterate over civ_obj.diplomacy.nonAggressionPact
		civ_obj.diplomacy.nonAggressionPact.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			non_aggression_pact_tags.push(local_civ_tag);
		});

		//Return statement
		return non_aggression_pact_tags;
	}

	function getRivals (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var rivals_array = [];

		//Iterate over civ_obj.diplomacy.guarantee
		civ_obj.diplomacy.rivals.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			rivals_array.push(local_civ_tag);
		})

		//Return statement
		return rivals_array;
	}

	function getTotalWars (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.diplomacy.iWarsSize;
	}

	function getTruces (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var truces_array = [];

		//Iterate over civ_obj.diplomacy.guarantee
		civ_obj.diplomacy.truce.forEach(function (key, value) {
			var local_civ_id = key;
			var local_civ_tag = getCurrentTag(local_civ_id);

			truces_array.push(local_civ_tag);
		})

		//Return statement
		return truces_array;
	}

	function getVassals (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var vassal_list = civ_obj.diplomacy.lVassals;
		var vassal_tags = [];

		//Iterate over civ_obj.diplomacy.lVassals
		for (var i = 0; i < vassal_list.size(); i++) {
			var local_vassal = vassal_list.get(i);
			var local_vassal_civ_tag = getCurrentTag(local_vassal.c);

			vassal_tags.push(local_vassal_civ_tag);
		}

		//Return statement
		return vassal_tags;
	}

	function getWars (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var wars_array = [];

		//Iterate over all civ_obj.diplomacy.lWars
		for (var i = 0; i < civ_obj.diplomacy.lWars.size(); i++) {
			var local_war = WarManager.lWars.get(civ_obj.diplomacy.lWars.get(i));

			wars_array.push(local_war);
		}

		//Return statement
		return wars_array;
	}

	function hasAlliance (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveAlliance(ot_civ_id);
	}

	function hasDefensivePact (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveDefensivePact(ot_civ_id);
	}

	function hasGuarantee (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.diplomacy.guaranteeByCivID.size();
	}

	function hasGuaranteeFrom (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
 		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveGuaranteeByCivID(ot_civ_id);
	}

	function hasMilitaryAccess (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveMilitaryAccess(ot_civ_id);
	}

	function hasNoAllies (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.alliance.size() == 0);
	}

	function hasNoDefensivePact (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.defensivePact.size() == 0);
	}

	function hasNoGuarantors (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.guaranteeByCivID.size() == 0);
	}

	function hasNoMilitaryAccess (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.militaryAccess.size() == 0);
	}

	function hasNoNeighbours (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.civNeighbors.civsSize == 0);
	}

	function hasNoNonAggressionPacts (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.nonAggressionPact.size() == 0);
	}

	function hasNoTruce (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.truce.size() == 0);
	}

	function hasNonAggressionPact (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveNonAggressionPact(ot_civ_id);
	}

	function hasTruce (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveTruce(ot_civ_id);
	}

	function isAtWar (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.iWarsSize > 0);
	}

	function isAtWarWith (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_id = getCivilisationID(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return DiplomacyManager.isAtWar(civ_id, ot_civ_id);
	}

	function isGuaranteeing (arg0_civ_tag, arg1_civ_tags) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tags = getList(arg1_civ_tags);

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Iterate over ot_civ_tags
		for (var i = 0; i < ot_civ_tags.length; i++)
			if (!civ_obj.diplomacy.haveGuarantee(getCivilisationID(ot_civ_tags[i])))
				return false;

		//Return statement
		return true;
	}

	function isNotAllied (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return civ_obj.diplomacy.haveAlliance(ot_civ_id);
	}

	function isNotAtWar (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return (civ_obj.diplomacy.iWarsSize == 0);
	}

	function isNotRival (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return (!civ_obj.diplomacy.isRival(ot_civ_id));
	}

	function isRival (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_id = getCivilisationID(ot_civ_tag);

		//Return statement
		return (civ_obj.diplomacy.isRival(ot_civ_id));
	}

	function isVassal (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var all_civ_tags = getAllCurrentCivTags();
		var civ_id = getCivilisationID(civ_tag);

		//Iterate over all_civ_tags
		for (var i = 0; i < all_civ_tags.length; i++) {
			var local_civ = getCivilisation(all_civ_tags[i]);

			for (var x = 0; x < local_civ.lVassals.size(); x++)
				if (local_civ.lVassals.get(x).c == civ_id)
					//Return statement
					return true;
		}
	}

	function isVassalOf (arg0_civ_tag, arg1_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ot_civ_tag = arg1_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var ot_civ_obj = getCivilisation(ot_civ_tag);

		//Check if ot_civ_obj vassals contains civ_obj ID
		civ_tag = getCurrentTag(civ_obj);
		var ot_vassals = getVassals(ot_civ_obj);

		//Return statement
		if (ot_vassals.includes(civ_tag))
			return true;
	}

	function modifyAggressiveExpansion (arg0_civ_tag, arg1_value) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.addAggressiveExpansion(value);
	}
}