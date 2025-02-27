//Initialise functions.
{
	function getArmyUnits (arg0_army_obj) {
		//Convert from parameters
		var army_obj = arg0_army_obj;

		//Declare local instance variables
		var return_obj = {};
		var swapped_units_obj = {};
		var units_obj = getUnitsObject();

		//Iterate over all units_keys
		var all_units_keys = Object.keys(units_obj);

		for (var i = 0; i < all_units_keys.length; i++)
			swapped_units_obj[units_obj[all_units_keys[i]].join("-")] = all_units_keys[i];

		//Iterate over all lArmyRegiment
		if (army_obj)
			for (var i = 0; i < army_obj.lArmyRegiment.length; i++) {
				var local_army_regiment = army_obj.lArmyRegiment.get(i);
				var local_unit_key = swapped_units_obj[local_army_regiment.aID + "-" + local_army_regiment.uID];

				modifyValue(return_obj, local_unit_key, 1);
			}

		//Return statement
		return return_obj;
	}

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

	function getRegimentSize () {
		//Return statement
		return Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
	}
}