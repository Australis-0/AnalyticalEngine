//Initialise functions
{
	function getCivilisationAverageGrowthRate (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.getAverageGrowthRate();
	}

	function getCivilisationIncreasedGrowthRate (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.civBonuses.GrowthRate;
	}

	function getCivilisationPopulation (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.getPopulationTotal();
	}

	function getProvinceGrowthRate (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var province_obj = getProvince(province_id);

		//Return statement
		return province_obj.getGrowthRate();
	}

	function getProvincePopulation (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Declare local instance variables
		var province_obj = getProvince(province_id);

		//Return statement
		return province_obj.getPopulationTotal();
	}
}