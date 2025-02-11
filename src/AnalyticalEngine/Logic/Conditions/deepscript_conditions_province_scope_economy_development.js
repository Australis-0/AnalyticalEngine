//Initialise functions
{
	//Economy (Development).
	{
		function provinceEconomyIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesEconomyValue(provinces) == value)
				return true;
		}

		function provinceEconomyIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesEconomyValue(provinces) >= value)
				return true;
		}

		function provinceEconomyIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesEconomyValue(provinces) > value)
				return true;
		}

		function provinceEconomyIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesEconomyValue(provinces) <= value)
				return true;
		}

		function provinceEconomyIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesEconomyValue(provinces) < value)
				return true;
		}

		function provinceIncomeIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesIncomeValue(provinces) == value)
				return true;
		}

		function provinceIncomeIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesIncomeValue(provinces) >= value)
				return true;
		}

		function provinceIncomeIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesIncomeValue(provinces) > value)
				return true;
		}

		function provinceIncomeIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesIncomeValue(provinces) <= value)
				return true;
		}

		function provinceIncomeIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesIncomeValue(provinces) < value)
				return true;
		}

		function provinceInfrastructureIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesInfrastructureValue(provinces) == value)
				return true;
		}

		function provinceInfrastructureIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesInfrastructureValue(provinces) >= value)
				return true;
		}

		function provinceInfrastructureIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesInfrastructureValue(provinces) > value)
				return true;
		}

		function provinceInfrastructureIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesInfrastructureValue(provinces) <= value)
				return true;
		}

		function provinceInfrastructureIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesInfrastructureValue(provinces) < value)
				return true;
		}

		function provinceTaxEfficiencyIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesTaxEfficiencyValue(provinces) == value)
				return true;
		}

		function provinceTaxEfficiencyIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesTaxEfficiencyValue(provinces) >= value)
				return true;
		}

		function provinceTaxEfficiencyIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesTaxEfficiencyValue(provinces) > value)
				return true;
		}

		function provinceTaxEfficiencyIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesTaxEfficiencyValue(provinces) <= value)
				return true;
		}

		function provinceTaxEfficiencyIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getProvincesTaxEfficiencyValue(provinces) < value)
				return true;
		}
	}

}

//Initialise internal helper functions
{
	function getProvincesEconomyValue (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceEconomy(provinces[i]);

		//Return statement
		return comparison_sum;
	}

	function getProvincesIncomeValue (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceIncome(provinces[i]);

		//Return statement
		return comparison_sum;
	}

	function getProvincesInfrastructureValue (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceInfrastructure(provinces[i]);

		//Return statement
		return comparison_sum;
	}

	function getProvincesTaxEfficiencyValue (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over provinces
		for (var i = 0; i < provinces.length; i++)
			comparison_sum += getProvinceTaxEfficiency(provinces[i]);

		//Return statement
		return comparison_sum;
	}
}