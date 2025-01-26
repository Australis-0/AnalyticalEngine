//Initialise functions
{
	function getCivilisationDevelopedInfrastructure (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.eventsData.getDevelopedInfrastructure();
	}

	function getCivilisationIncreasedManpower (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.eventsData.getIncreasedManpower();
	}

	function getCivilisationIncreasedTaxEfficiency (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.eventsData.getIncreasedTaxEfficiency();
	}

	function getCivilisationInvestedInEconomy (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.eventsData.getInvestedInEconomy();
	}
}