//Initialise functions
{
	//Economy (Technology).
	{
		function civilisationResearchedTechnologiesIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies <- value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchedTechnologiesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_researched_technologies = getCivilisationResearchedTechnologies(local_civ).length;

				if (local_civ_researched_technologies > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationResearchPerMonthIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = getList(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_research_per_month = getCivilisationResearchPerMonth(local_civ);

				if (local_civ_research_per_month >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationHasTechnology (arg0_civ_tags, arg1_technology_names) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var technology_names = getList(arg1_technology_names);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_technologies_researched = getCivilisationResearchedTechnologies(local_civ);

				for (var x = 0; x < technology_names.length; x++) {
					var local_technology = getTechnology(technology_names[x]);

					if (!local_technologies_researched.includes(local_technology.Name))
						//Return statement
						return false;
				}
			}

			//Return statement
			return true;
		}
	}
}