//Initialise functions
{
	//Politics (External [Diplomacy] - AI).
	{
		function civilisationIsAI (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);

				if (M_Players.isPlayer(local_civ_id))
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsPlayer (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ_id = getCivilisationID(civ_tags[i]);

				if (!M_Players.isPlayer(local_civ_id))
					return false;
			}

			//Return statement
			return true;
		}
	}

	//Politics (External [Diplomacy]).
	{
		function civilisationAggressiveExpansionIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tag);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_aggressive_expansion = getAggressiveExpansion(local_civ);

				if (local_civ_aggressive_expansion != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAggressiveExpansionIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tag);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_aggressive_expansion = getAggressiveExpansion(local_civ);

				if (local_civ_aggressive_expansion < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAggressiveExpansionIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tag);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_aggressive_expansion = getAggressiveExpansion(local_civ);

				if (local_civ_aggressive_expansion <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAggressiveExpansionIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tag);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_aggressive_expansion = getAggressiveExpansion(local_civ);

				if (local_civ_aggressive_expansion > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAggressiveExpansionIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tag);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_aggressive_expansion = getAggressiveExpansion(local_civ);

				if (local_civ_aggressive_expansion >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAtWarDaysIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war_days = getDaysAtWar(local_civ);

				if (local_civ_at_war_days != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAtWarDaysIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war_days = getDaysAtWar(local_civ);

				if (local_civ_at_war_days < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAtWarDaysIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war_days = getDaysAtWar(local_civ);

				if (local_civ_at_war_days <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAtWarDaysIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war_days = getDaysAtWar(local_civ);

				if (local_civ_at_war_days > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAtWarDaysIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war_days = getDaysAtWar(local_civ);

				if (local_civ_at_war_days >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationGuarantorsAre (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_guarantors = getGuarantors(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_guarantors.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationGuarantorsAreNot (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_guarantors = getGuarantors(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_guarantors.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasAlliancesWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_allies = getAllies(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_allies.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_defensive_pacts = getDefensivePacts(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_defensive_pacts.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasMilitaryAccessWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_military_access = getMilitaryAccess(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_military_access.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoAllies (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_allies = getAllies(local_civ);

				//Return statement
				if (local_civ_allies.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoDefensivePacts (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_defensive_pacts = getDefensivePacts(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_defensive_pacts.length > 0)
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoGuarantees (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_guarantees = getGuarantees(local_civ);

				//Iterate over ot_civ_tags
				if (local_civ_guarantees.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoGuarantors (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_guarantors = getGuarantors(local_civ);

				if (local_civ_guarantors.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoMilitaryAccess (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_military_access = getMilitaryAccess(local_civ);

				if (local_civ_military_access.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPacts (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_non_aggression_pacts = getNonAggressionPacts(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_non_aggression_pacts.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoNeighbours (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoNonAggressionPacts (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_non_aggression_pacts = getNonAggressionPacts(local_civ);

				if (local_civ_non_aggression_pacts.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoRivals (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_rivals = getRivals(local_civ);

				if (local_civ_rivals.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNoTruces (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_truces = getTruces(local_civ);

				if (local_civ_truces.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTruces (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_truces = getTruces(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_truces.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsAtWar (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war = isAtWar(local_civ);

				if (!local_civ_at_war)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsAtWarWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_enemies = getEnemies(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_enemies.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsGuaranteeing (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_guarantees = getGuarantees(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_guarantees.includes(ot_civ_tags[x]))
						return false;
			}
		}

		function civilisationIsNeighboursWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_neighbours = getNeighbours(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (!local_civ_neighbours.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsNotAlliedWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_allies = getAllies(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_allies.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsNotAtWar (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war = getEnemies(local_civ);

				//Return statement
				if (local_civ_at_war.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsNotAtWarWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_enemies = getEnemies(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_enemies.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsNotRivalsWith (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_rivals = getRivals(local_civ);

				//Return statement
				if (local_civ_rivals.length > 0)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsRivalWith (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Declare local instance variables
			var all_rivals = [];

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_rivals = getRivals(local_civ);

				for (var x = 0; x < local_civ_rivals.length; x++)
					all_rivals.push(local_civ_rivals[x]);
			}

			//Iterate over ot_civ_tags
			for (var i = 0; i < ot_civ_tags.length; i++) {
				var local_ot_civ_tag = getCurrentTag(ot_civ_tags[i]);

				if (!all_rivals.includes(local_ot_civ_tag))
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsVassal (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				if (!isVassal(civ_obj))
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationIsVassalOf (arg0_civ_tags, arg1_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var civ_overlord_tag = getOverlord(civ_obj, { return_tag: true });
				var meets_check = false;

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_overlord_tag = getCurrentTag(ot_civ_tags[x]);

					if (local_overlord_tag == civ_overlord_tag)
						meets_check = true;
				}
				if (!meets_check)
					return false;
			}

			//Return statement
			return true;
		}
	}

	//Politics (External [Diplomacy] - Relation Comparisons).
	{
		function civilisationHasAlliesEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getAllies(local_civ).length != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasAlliesGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getAllies(local_civ).length < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasAlliesGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getAllies(local_civ).length <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasAlliesLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getAllies(local_civ).length > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasAlliesLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getAllies(local_civ).length >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getDefensivePacts(local_civ).length != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getDefensivePacts(local_civ).length < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getDefensivePacts(local_civ).length <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getDefensivePacts(local_civ).length > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasDefensivePactsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getDefensivePacts(local_civ).length >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNeighboursEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNeighboursGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNeighboursGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNeighboursLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNeighboursLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNeighbours(local_civ).length >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPactsEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNonAggressionPacts(local_civ).length != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPactsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNonAggressionPacts(local_civ).length < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPactsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNonAggressionPacts(local_civ).length <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPactsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNonAggressionPacts(local_civ).length > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasNonAggressionPactsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getNonAggressionPacts(local_civ).length >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTotalWarsEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getTotalWars(local_civ) != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTotalWarsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getTotalWars(local_civ) < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTotalWarsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getTotalWars(local_civ) <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTotalWarsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getTotalWars(local_civ) > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTotalWarsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getTotalWars(local_civ) >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasVassalsEqualTo (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getVassals(local_civ).length != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasVassalsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getVassals(local_civ).length < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasVassalsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getVassals(local_civ).length <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasVassalsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getVassals(local_civ).length > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasVassalsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(artg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getVassals(local_civ).length >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRankingIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getCivilisationRank(local_civ) != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRankingIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getCivilisationRank(local_civ) < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRankingIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getCivilisationRank(local_civ) <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRankingIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getCivilisationRank(local_civ) > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRankingIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (getCivilisationRank(local_civ) >= value)
					return false;
			}

			//Return statement
			return true;
		}
	}

	//Politics (External [Diplomacy] - Opinion).
	{
		function civilisationOpinionIs (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value != value)
						return false;
				}
			}

			//Return statement
			return true;
		}

		function civilisationOpinionIs (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value != value)
						return false;
				}
			}

			//Return statement
			return true;
		}

		function civilisationOpinionIsGEQ (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value < value)
						return false;
				}
			}

			//Return statement
			return true;
		}

		function civilisationOpinionIsGreaterThan (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value <= value)
						return false;
				}
			}

			//Return statement
			return true;
		}

		function civilisationOpinionIsLEQ (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value > value)
						return false;
				}
			}

			//Return statement
			return true;
		}

		function civilisationOpinionIsLessThan (arg0_civ_tags, arg1_civ_tags, arg2_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var ot_civ_tags = getList(arg1_civ_tags);
			var value = returnSafeNumber(arg2_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				for (var x = 0; x < ot_civ_tags.length; x++) {
					var local_ot_civ = getCivilisation(ot_civ_tags[x]);

					var local_opinion_obj = getOpinion(local_civ, local_ot_civ);

					if (local_opinion_obj.value >= value)
						return false;
				}
			}

			//Return statement
			return true;
		}
	}
}

//Initialise internal helper functions
{
	function getCivilisationsAggressiveExpansionValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_value = Math.max(comparison_value, getAggressiveExpansion(civ_tags[i]));

		//Return statement
		return comparison_value;
	}

	function getCivilisationsAtWarDaysValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++)
			comparison_sum += getDaysAtWar(civ_tags[i]);

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsAtWarWithValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var at_war_with_array = [];

		//Iterate over all civ_tags to fetch what tags they are at war with
		for (var i = 0; i < civ_tags.length; i++) {
			var local_enemies = getEnemies(civ_tags[i]);

			for (var x = 0; x < local_enemies.length; x++)
				if (!at_war_with_array.includes(local_enemies[x]))
					at_war_with_array.push(local_enemies[x]);
		}

		//Return statement
		return at_war_with_array;
	}

	function getCivilisationsHasAlliesValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var allies_array = [];

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_allies = getAllies(civ_tags[i]);

			for (var x = 0; x < local_civ_allies.length; x++)
				if (!allies_array.includes(local_civ_allies[x]))
					allies_array.push(local_civ_allies[x]);
		}

		//Return statement
		return allies_array;
	}

	function getCivilisationsHasDefensivePactsValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var defensive_pacts_array = [];

		//Iterate over all civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_defensive_pacts = getDefensivePacts(civ_tags[i]);

			for (var x = 0; x < local_civ_defensive_pacts.length; x++)
				if (!defensive_pacts_array.includes(local_civ_defensive_pacts[x]))
					defensive_pacts_array.push(local_civ_defensive_pacts[x]);
		}

		//Return statement
		return defensive_pacts_array;
	}

	function getCivilisationsHasNeighboursValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var neighbours_array = [];

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_neighbours = getNeighbours(civ_tags[i]);

			for (var x = 0; x < local_civ_neighbours.length; x++)
				if (!neighbours_array.includes(local_civ_neighbours[x]))
					neighbours_array.push(local_civ_neighbours[x]);
		}

		//Return statement
		return neighbours_array;
	}

	function getCivilisationsHasNonAggressionPactsValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var non_aggression_pacts_array = [];

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_non_aggression_pacts = getNonAggressionPacts(civ_tags[i]);

			for (var x = 0; x < local_civ_non_aggression_pacts.length; x++)
				if (!non_aggression_pacts_array.includes(local_civ_non_aggression_pacts[x]))
					non_aggression_pacts_array.push(local_civ_non_aggression_pacts[x]);
		}

		//Return statement
		return non_aggression_pacts_array;
	}

	function getCivilisationsHasTotalWars (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_total_wars = getTotalWars(civ_tags[i]);

			comparison_value = Math.max(comparison_value, local_civ_total_wars);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsHasVassals (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var vassals_array = [];

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_total_vassals = getVassals(civ_tags[i]);

			for (var x = 0; x < local_civ_total_vassals.length; x++)
				vassals_array.push(local_civ_total_vassals[x]);
		}

		//Return statement
		return local_civ_total_vassals;
	}

	function getCivilisationsHasWars (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var wars_array = [];

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);
			var local_civ_wars = getWars(civ_tags[i]);

			//Iterate over local_civ_wars
			for (var x = 0; x < local_civ_wars.length; x++)
				if (!wars_array.includes(local_civ_wars[x]))
					wars_array.push(local_civ_wars[x]);
		}

		//Return statement
		return wars_array;
	}

	function getCivilisationsRanking (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		//Iterate over civ_tags
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ = getCivilisation(civ_tags[i]);

			comparison_value = Math.max(comparison_value, getCivilisationRank(local_civ));
		}

		//Return statement
		return comparison_value;
	}
}