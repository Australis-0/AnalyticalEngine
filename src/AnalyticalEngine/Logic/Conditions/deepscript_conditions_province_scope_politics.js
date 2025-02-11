//Initialise functions
{
	function provinceUnrestIs (arg0_provinces, arg1_value) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var comparison_value = getProvincesUnrest(provinces);

		//Return statement
		return (comparison_value == value);
	}

	function provinceUnrestIsGEQ (arg0_provinces, arg1_value) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var comparison_value = getProvincesUnrest(provinces);

		//Return statement
		return (comparison_value > value);
	}

	function provinceUnrestIsGreaterThan (arg0_provinces, arg1_value) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var comparison_value = getProvincesUnrest(provinces);

		//Return statement
		return (comparison_value >= value);
	}

	function provinceUnrestIsLEQ (arg0_provinces, arg1_value) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var comparison_value = getProvincesUnrest(provinces);

		//Return statement
		return (comparison_value <= value);
	}

	function provinceUnrestIsLessThan (arg0_provinces, arg1_value) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);
		var value = returnSafeNumber(arg1_value);

		//Declare local instance variables
		var comparison_value = getProvincesUnrest(provinces);

		//Return statement
		return (comparison_value < value);
	}
}

//Initialise internal helper functions
{
	function getProvincesUnrest (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all provinces
		for (var i = 0; i < provinces.length; i++) {
			var local_province = getProvince(provinces[i]);

			comparison_sum += local_province.getRevulutionaryRisk();
		}

		//Return statement
		return comparison_sum/provinces.length;
	}
}