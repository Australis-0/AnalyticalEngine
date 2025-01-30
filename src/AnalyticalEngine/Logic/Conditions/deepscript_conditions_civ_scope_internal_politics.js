//Initialise functions
{
	//Politics (Internal).
	{
		function hasGovernmentType (arg0_civ_tags, arg1_government_types) { //[WIP] - Finish function body
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