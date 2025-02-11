//Initialise functions
{
	//Economy (Pops).
	{
		function provinceGrowthRateIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesGrowthRate(provinces);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function provinceGrowthRateIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesGrowthRate(provinces);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function provinceGrowthRateIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesGrowthRate(provinces);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function provinceGrowthRateIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesGrowthRate(provinces);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function provinceGrowthRateIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesGrowthRate(provinces);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function provincePopulationIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesPopulation(provinces);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function provincePopulationIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesPopulation(provinces);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function provincePopulationIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesPopulation(provinces);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function provincePopulationIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesPopulation(provinces);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function provincePopulationIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesPopulation(provinces);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function provinceReligionIs (arg0_provinces, arg1_religion_names) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var religion_names = getList(arg1_religion_names);

			//Iterate over religion_names
			for (var i = 0; i < religion_names.length; i++)
				religion_names[i] = religion_names[i].toLowerCase();

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++)
				if (!religion_names.includes(provinces[i]))
					//Return statement
					return false;
			return true;
		}

		function provinceReligionIsNot (arg0_provinces, arg1_religion_names) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var religion_names = getList(arg1_religion_names);

			//Iterate over religion_names
			for (var i = 0; i < religion_names.length; i++)
				religion_names[i] = religion_names[i].toLowerCase();

			//Iterate over provinces
			for (var i = 0; i < provinces.length; i++)
				if (religion_names.includes(provinces[i]))
					//Return statement
					return false;
			return true;
		}
	}
}

//Initialise internal helper functions
{
	function getProvincesGrowthRate (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceGrowthRate(provinces[i]);

		//Return statement
		return comparison_sum/provinces.length;
	}

	function getProvincesPopulation (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvincePopulation(provinces[i]);

		//Return statement
		return comparison_sum;
	}
}