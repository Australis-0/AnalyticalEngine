//Initialise functions.
{
	function getCivilisationBattleWidth (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.civBonuses.BattleWidth;
	}

	function getCivilisationManpower (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.fManpower;
	}

	function getCivilisationManpowerPercentage (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return getCivilisationManpower(civ_obj)/getCivilisationMaximumManpower(civ_obj);
	}

	function getCivilisationMaximumManpower (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.fManpowerMax;
	}

	function getCivilisationRegimentsAmount (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.iRegiments;
	}

	function getCivilisationRegimentsOverLimit (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return getCivilisationRegimentsAmount(civ_tag) - getCivilisationRegimentsLimit(civ_tag);
	}

	function getCivilisationRegimentsLimit (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.iRegimentsLimit;
	}
}