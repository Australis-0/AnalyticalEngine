//Initialise functions
{
	//Politics (Internal).
	{
		function civilisationAdvisorSkillIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_advisor_skills = getCivilisationsAdvisorSkillValue(local_civ);

				if (local_civ_advisor_skills != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAdvisorSkillIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_advisor_skills = getCivilisationsAdvisorSkillValue(local_civ);

				if (local_civ_advisor_skills < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAdvisorSkillIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_advisor_skills = getCivilisationsAdvisorSkillValue(local_civ);

				if (local_civ_advisor_skills <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAdvisorSkillIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_advisor_skills = getCivilisationsAdvisorSkillValue(local_civ);

				if (local_civ_advisor_skills > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationAdvisorSkillIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_advisor_skills = getCivilisationsAdvisorSkillValue(local_civ);

				if (local_civ_advisor_skills >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy = getCivilisationLegacy(local_civ);

				if (local_civ_legacy != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy = getCivilisationLegacy(local_civ);

				if (local_civ_legacy < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy = getCivilisationLegacy(local_civ);

				if (local_civ_legacy <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy = getCivilisationLegacy(local_civ);

				if (local_civ_legacy > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy = getCivilisationLegacy(local_civ);

				if (local_civ_legacy >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyPerMonthIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy_per_month = getCivilisationLegacyPerMonth(local_civ);

				if (local_civ_legacy_per_month != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyPerMonthIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy_per_month = getCivilisationLegacyPerMonth(local_civ);

				if (local_civ_legacy_per_month < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyPerMonthIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy_per_month = getCivilisationLegacyPerMonth(local_civ);

				if (local_civ_legacy_per_month <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyPerMonthIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy_per_month = getCivilisationLegacyPerMonth(local_civ);

				if (local_civ_legacy_per_month > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationLegacyPerMonthIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_legacy_per_month = getCivilisationLegacyPerMonth(local_civ);

				if (local_civ_legacy_per_month >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationPrestigeIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_prestige = getCivilisationPrestige(local_civ);

				if (local_civ_prestige != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationPrestigeIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_prestige = getCivilisationPrestige(local_civ);

				if (local_civ_prestige < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationPrestigeIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_prestige = getCivilisationPrestige(local_civ);

				if (local_civ_prestige <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationPrestigeIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_prestige = getCivilisationPrestige(local_civ);

				if (local_civ_prestige > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationPrestigeIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_prestige = getCivilisationPrestige(local_civ);

				if (local_civ_prestige >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRecruitedAdvisorsIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationsRecruitedAdvisorsValue(local_civ);

				if (local_civ_recruited_advisors != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRecruitedAdvisorsIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationsRecruitedAdvisorsValue(local_civ);

				if (local_civ_recruited_advisors < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRecruitedAdvisorsIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationsRecruitedAdvisorsValue(local_civ);

				if (local_civ_recruited_advisors <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRecruitedAdvisorsIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationsRecruitedAdvisorsValue(local_civ);

				if (local_civ_recruited_advisors > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationRecruitedAdvisorsIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationsRecruitedAdvisorsValue(local_civ);

				if (local_civ_recruited_advisors >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedAdvantagesIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationUnlockedAdvantagesValue(local_civ);

				if (local_civ_recruited_advisors != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedAdvantagesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationUnlockedAdvantagesValue(local_civ);

				if (local_civ_recruited_advisors < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedAdvantagesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationUnlockedAdvantagesValue(local_civ);

				if (local_civ_recruited_advisors <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedAdvantagesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationUnlockedAdvantagesValue(local_civ);

				if (local_civ_recruited_advisors > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedAdvantagesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationUnlockedAdvantagesValue(local_civ);

				if (local_civ_recruited_advisors >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedLegaciesIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationLegaciesValue(local_civ);

				if (local_civ_recruited_advisors != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedLegaciesIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationLegaciesValue(local_civ);

				if (local_civ_recruited_advisors < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedLegaciesIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationLegaciesValue(local_civ);

				if (local_civ_recruited_advisors <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedLegaciesIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationLegaciesValue(local_civ);

				if (local_civ_recruited_advisors > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationUnlockedLegaciesIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags =  getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_recruited_advisors = getCivilisationLegaciesValue(local_civ);

				if (local_civ_recruited_advisors >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function hasGovernmentType (arg0_civ_tags, arg1_government_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var government_types = getList(arg1_government_types);

			//Declare local instance variables
			var comparison_government_keys = [];

			//Iterate over government_types
			for (var i = 0; i < government_types.length; i++)
				comparison_government_keys.push(getIdeology(government_types[i], { return_key: true }));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var local_government_type = getCivilisationIdeology(civ_obj, { return_key: true });

				if (!comparison_government_keys.includes(local_government_type))
					return false;
			}

			//Return statement
			return true;
		}

		function hasReligion (arg0_civ_tags, arg1_religion_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var religion_types = getList(arg1_religion_types);

			//Declare local instance variables
			var comparison_religion_keys = [];

			//Iterate over religion_types
			for (var i = 0; i < religion_types.length; i++)
				comparison_religion_keys.push(getReligion(religion_types[i], { return_key: true }));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var local_religion_type = getCivilisationReligion(civ_obj, { return_key: true });

				if (!comparison_religion_keys.includes(local_religion_type))
					return false;
			}

			//Return statement
			return true;
		}

		function isNotGovernmentType (arg0_civ_tags, arg1_government_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var government_types = getList(arg1_government_types);

			//Declare local instance variables
			var comparison_government_keys = [];

			//Iterate over government_types
			for (var i = 0; i < government_types.length; i++)
				comparison_government_keys.push(getIdeology(government_types[i], { return_key: true }));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var local_government_type = getCivilisationIdeology(civ_obj, { return_key: true });

				if (comparison_government_keys.includes(local_government_type))
					return false;
			}

			//Return statement
			return true;
		}

		function isReligion (arg0_civ_tags, arg1_religion_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var religion_types = getList(arg1_religion_types);

			//Declare local instance variables
			var comparison_religion_keys = [];

			//Iterate over religion_types
			for (var i = 0; i < religion_types.length; i++)
				comparison_religion_keys.push(getReligion(religion_types[i], { return_key: true }));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var local_religion_type = getCivilisationReligion(civ_obj, { return_key: true });

				if (comparison_religion_keys.includes(local_religion_type))
					return false;
			}

			//Return statement
			return true;
		}

		function isNotReligion (arg0_civ_tags, arg1_religion_types) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var religion_types = getList(arg1_religion_types);

			//Declare local instance variables
			var comparison_religion_keys = [];

			//Iterate over religion_types
			for (var i = 0; i < religion_types.length; i++)
				comparison_religion_keys.push(getReligion(religion_types[i], { return_key: true }));

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);
				var local_religion_type = getCivilisationReligion(civ_obj, { return_key: true });

				if (!comparison_religion_keys.includes(local_religion_type))
					return false;
			}

			//Return statement
			return true;
		}
	}
}

//Initialise internal helper functions
{
	function getCivilisationsAdvisorSkillValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var advisor_dictionary = getAdvisorDictionary();
		var comparison_sum = 0;

		var all_advisor_keys = Object.keys(advisor_dictionary);

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				var civ_advisors = getCivilisationAdvisors(civ_obj);
				var civ_advisor_skill_sum = 0;

				for (var x = 0; x < civ_advisors.length; x++)
					civ_advisor_skill_sum += civ_advisors[x].iLevel;
				comparison_sum += civ_advisor_skill_sum/civ_advisors.length;
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsLegacyValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationLegacy(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsLegacyPerMonthValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationLegacyPerMonth(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsPrestigeValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationPrestige(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsRecruitedAdvisorsValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				var civ_advisors = getCivilisationAdvisors(civ_obj);
				var civ_advisor_skill_sum = 0;

				for (var x = 0; x < civ_advisors.length; x++)
					if (civ_advisors[x].sName != null)
						comparison_sum++;
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsUnlockedAdvantagesValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++)
				comparison_sum += getCivilisationUnlockedAdvantagesValue(civ_tags[i]);
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsUnlockedLegaciesValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all civ_tags
		try {
			for (var i = 0; i < civ_tags.length; i++)
				comparison_sum += getCivilisationLegaciesValue(civ_tags[i]);
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
	}
}