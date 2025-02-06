//Initialise functions
{
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

		function civilisationAtWar (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = arg1_value;

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_at_war = isAtWar(local_civ);

				if (value)
					if (!local_civ_at_war)
						return false;
				if (!value)
					if (local_civ_at_war)
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

		function hasAlliance (arg0_civ_tags, arg1_civ_tags) {
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

		function hasAlliesEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasAlliesGEQ (arg0_civ_tags, arg1_value) {
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

		function hasAlliesGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasAlliesLEQ (arg0_civ_tags, arg1_value) {
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

		function hasAlliesLessThan (arg0_civ_tags, arg1_value) {
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

		function hasDefensivePact (arg0_civ_tags, arg1_civ_tags) {
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

		function hasDefensivePactsEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasDefensivePactsGEQ (arg0_civ_tags, arg1_value) {
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

		function hasDefensivePactsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasDefensivePactsLEQ (arg0_civ_tags, arg1_value) {
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

		function hasDefensivePactsLessThan (arg0_civ_tags, arg1_value) {
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

		function hasGuarantee (arg0_civ_tags, arg1_civ_tags) {
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

		function hasMilitaryAccess (arg0_civ_tags, arg1_civ_tags) {
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

		function hasNeighboursEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasNeighboursGEQ (arg0_civ_tags, arg1_value) {
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

		function hasNeighboursGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasNeighboursLEQ (arg0_civ_tags, arg1_value) {
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

		function hasNeighboursLessThan (arg0_civ_tags, arg1_value) {
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

		function hasNoAllies (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

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

		function hasNoDefensivePact (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_defensive_pacts = getDefensivePacts(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_defensive_pacts.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function hasNoGuarantors (arg0_civ_tags) {
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

		function hasNoMilitaryAccess (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_military_access = getMilitaryAccess(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_military_access.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function hasNonAggressionPactsEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasNonAggressionPactsGEQ (arg0_civ_tags, arg1_value) {
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

		function hasNonAggressionPactsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasNonAggressionPactsLEQ (arg0_civ_tags, arg1_value) {
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

		function hasNonAggressionPactsLessThan (arg0_civ_tags, arg1_value) {
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

		function hasNoNeighbours (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_neighbours = getNeighbours(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_neighbours.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function hasNoNonAggressionPacts (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_non_aggression_pacts = getNonAggressionPacts(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_non_aggression_pacts.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function hasNoTruces (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_truces = getTruces(local_civ);

				//Iterate over ot_civ_tags
				for (var x = 0; x < ot_civ_tags.length; x++)
					if (local_civ_truces.includes(ot_civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function hasNonAggressionPacts (arg0_civ_tags, arg1_civ_tags) {
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

		function hasTotalWarsEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasTotalWarsGEQ (arg0_civ_tags, arg1_value) {
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

		function hasTotalWarsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasTotalWarsLEQ (arg0_civ_tags, arg1_value) {
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

		function hasTotalWarsLessThan (arg0_civ_tags, arg1_value) {
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

		function hasTruce (arg0_civ_tags, arg1_civ_tags) {
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

		function hasVassalsEqualTo (arg0_civ_tags, arg1_value) {
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

		function hasVassalsGEQ (arg0_civ_tags, arg1_value) {
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

		function hasVassalsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function hasVassalsLEQ (arg0_civ_tags, arg1_value) {
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

		function hasVassalsLessThan (arg0_civ_tags, arg1_value) {
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

		function isAI (arg0_civ_tags) {
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

		function isAtWarWith (arg0_civ_tags, arg1_civ_tags) {
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

		function isCiv (arg0_civ_tags) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);

			//Declare local instance variables
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);

				if (local_civ) {
					if (local_civ.getProvinces().size() < 1)
						return false;
				} else {
					return false;
				}
			}

			//Return statement
			return true;
		}

		function isGuaranteeing (arg0_civ_tags, arg1_civ_tags) {
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

		function isNotAllied (arg0_civ_tags) {
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

		function isNotAtWar (arg0_civ_tags) {
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

		function isNotRival (arg0_civ_tags) {
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

		function isPlayer (arg0_civ_tags) {
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

		function isRival (arg0_civ_tags, arg1_civ_tags) {
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

		function isVassal (arg0_civ_tags) {
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

		function isVassalOf (arg0_civ_tags, arg1_civ_tags) {
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

		function opinionIs (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function opinionIs (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function opinionIsGEQ (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function opinionIsGreaterThan (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function opinionIsLEQ (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function opinionIsLessThan (arg0_civ_tags, arg1_civ_tags, arg2_value) {
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

		function rankingIs (arg0_civ_tags, arg1_value) {
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

		function rankingIsGEQ (arg0_civ_tags, arg1_value) {
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

		function rankingIsGreaterThan (arg0_civ_tags, arg1_value) {
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

		function rankingIsLEQ (arg0_civ_tags, arg1_value) {
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

		function rankingIsLessThan (arg0_civ_tags, arg1_value) {
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