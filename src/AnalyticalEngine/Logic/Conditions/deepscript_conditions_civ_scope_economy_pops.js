//Initialise functions
{
	//Economy (Pops).
	{
		function civilisationAverageGrowthRateIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) == value)
				return true;
		}

		function civilisationAverageGrowthRateIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) >= value)
				return true;
		}

		function civilisationAverageGrowthRateIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) > value)
				return true;
		}

		function civilisationAverageGrowthRateIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) <= value)
				return true;
		}

		function civilisationAverageGrowthRateIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsAverageGrowthRateValue(civ_tags) < value)
				return true;
		}

		function civilisationIncreasedGrowthRateIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) == value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) >= value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) > value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) <= value)
				return true;
		}

		function civilisationIncreasedGrowthRateIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsIncreasedGrowthRateValue(civ_tags) < value)
				return true;
		}

		function civilisationPopulationIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) == value)
				return true;
		}

		function civilisationPopulationIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) >= value)
				return true;
		}

		function civilisationPopulationIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) > value)
				return true;
		}

		function civilisationPopulationIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) <= value)
				return true;
		}

		function civilisationPopulationIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Return statement
			if (getCivilisationsPopulationValue(civ_tags) < value)
				return true;
		}
	}
}