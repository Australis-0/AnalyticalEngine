//Initialise functions
{
	//Military.
	{
		//[WIP] - Finish limit functions.
		function civilisationBattleWidthIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_battle_width = getCivilisationBattleWidth(local_civ);

				if (local_civ_battle_width != value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationBattleWidthIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_battle_width = getCivilisationBattleWidth(local_civ);

				if (local_civ_battle_width < value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationBattleWidthIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_battle_width = getCivilisationBattleWidth(local_civ);

				if (local_civ_battle_width <= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationBattleWidthIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_battle_width = getCivilisationBattleWidth(local_civ);

				if (local_civ_battle_width > value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationBattleWidthIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++) {
				var local_civ = getCivilisation(civ_tags[i]);
				var local_civ_battle_width = getCivilisationBattleWidth(local_civ);

				if (local_civ_battle_width >= value)
					return false;
			}

			//Return statement
			return true;
		}

		function civilisationManpowerIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsManpowerValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationManpowerIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsManpowerValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationManpowerIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsManpowerValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationManpowerIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsManpowerValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationManpowerIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsManpowerValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationManpowerPercentageIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				if (getCivilisationManpowerPercentage(civ_tags[i]) != value)
					//Return statement
					return false;
			return true;
		}

		function civilisationManpowerPercentageIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				if (getCivilisationManpowerPercentage(civ_tags[i]) >= value)
					//Return statement
					return false;
			return true;
		}

		function civilisationManpowerPercentageIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				if (getCivilisationManpowerPercentage(civ_tags[i]) > value)
					//Return statement
					return false;
			return true;
		}

		function civilisationManpowerPercentageIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				if (getCivilisationManpowerPercentage(civ_tags[i]) <= value)
					//Return statement
					return false;
			return true;
		}

		function civilisationManpowerPercentageIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				if (getCivilisationManpowerPercentage(civ_tags[i]) < value)
					//Return statement
					return false;
			return true;
		}

		function civilisationMaxManpowerIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsMaximumManpowerValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationMaxManpowerIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsMaximumManpowerValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationMaxManpowerIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsMaximumManpowerValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationMaxManpowerIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsMaximumManpowerValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationMaxManpowerIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsMaximumManpowerValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationRegimentsIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationRegimentsIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationRegimentsIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationRegimentsIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationRegimentsIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationRegimentsOverLimitIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsOverLimitValue(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationRegimentsOverLimitIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsOverLimitValue(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationRegimentsOverLimitIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsOverLimitValue(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationRegimentsOverLimitIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsOverLimitValue(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationRegimentsOverLimitIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationsRegimentsOverLimitValue(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}

		function civilisationRegimentsLimitIs (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationRegimentsLimit(civ_tags);

			//Return statement
			if (comparison_value == value)
				return true;
		}

		function civilisationRegimentsLimitIsGEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationRegimentsLimit(civ_tags);

			//Return statement
			if (comparison_value >= value)
				return true;
		}

		function civilisationRegimentsLimitIsGreaterThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationRegimentsLimit(civ_tags);

			//Return statement
			if (comparison_value > value)
				return true;
		}

		function civilisationRegimentsLimitIsLEQ (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationRegimentsLimit(civ_tags);

			//Return statement
			if (comparison_value <= value)
				return true;
		}

		function civilisationRegimentsLimitIsLessThan (arg0_civ_tags, arg1_value) {
			//Convert from parameters
			var civ_tags = getList(arg0_civ_tags);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variable
			var comparison_value = getCivilisationRegimentsLimit(civ_tags);

			//Return statement
			if (comparison_value < value)
				return true;
		}
	}
}

//Initialise internal helper functions
{
	function getCivilisationsBattleWidthValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_value = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_value = Math.max(comparison_value, getCivilisationBattleWidth(civ_tags[i]));
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_value;
	}

	function getCivilisationsManpowerValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationManpower(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsManpowerPercentageValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationManpowerPercentage(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum/civ_tags.length;
	}

	function getCivilisationsMaximumManpowerValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationMaximumManpower(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsRegimentsValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationRegimentsAmount(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsRegimentsOverLimitValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationRegimentsOverLimit(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}

	function getCivilisationsRegimentsLimitValue (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Declare local instance variables
		var comparison_sum = 0;

		try {
			for (var i = 0; i < civ_tags.length; i++) {
				var civ_obj = getCivilisation(civ_tags[i]);

				comparison_sum += getCivilisationRegimentsLimit(civ_obj);
			}
		} catch (e) {
			console.log(e.message);
			console.log(e.stack);
		}

		//Return statement
		return comparison_sum;
	}
}