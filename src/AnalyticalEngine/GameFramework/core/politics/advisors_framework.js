//Initialise functions
{
	/**
	 * getAdvisor() - Returns the Advisor object for a given CivTag.
	 * @param {String} arg0_civ_tag
	 * @param {String} arg1_advisor_type - Either 'administrative', 'economic', 'technology', or 'military'.
	 *
	 * @returns {Object}
	 */
	function getAdvisor (arg0_civ_tag, arg1_advisor_type) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var advisor_type = arg1_advisor_type;

		//Declare local instance variables
		var advisor_dictionary = getAdvisorDictionary();
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return Game.advisorManager.getAdvisor(getCivilisationID(civ_obj), advisor_dictionary[advisor_type]);
	}

	function getAdvisorDictionary () {
		//Return statement
		return {
			administrative: "advisorAdministration",
			economic: "advisorEconomy",
			technology: "advisorTechnology",
			military: "advisorMilitary"
		};
	}

	function getCivilisationAdvisors (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variable
		var advisor_dictionary = getAdvisorDictionary();
		var civ_obj = getCivilisation(civ_tag);

		//Iterate over all_advisor_keys
		var advisor_array = [];
		var all_advisor_keys = Object.keys(advisor_dictionary);

		for (var i = 0; i < all_advisor_keys.length; i++) {
			var local_advisor_id = advisor_dictionary[all_advisor_keys[i]];

			advisor_array.push(civ_obj[local_advisor_id]);
		}

		//Return statement
		return advisor_array;
	}
}