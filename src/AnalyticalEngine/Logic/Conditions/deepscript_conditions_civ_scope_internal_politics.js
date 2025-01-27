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
		}

		function is_not_government_type (arg0_civ_tags, arg1_government_types) {
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
	}
}

//Initialise internal helper functions
{

}