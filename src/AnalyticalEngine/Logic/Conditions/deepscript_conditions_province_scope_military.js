//Initialise functions
{
	//Military (Defensive).
	{
		function provinceDefenceLevelIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesDefenceLevel(provinces);

			//Return statement
			return (comparison_value == value);
		}

		function provinceDefenceLevelIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesDefenceLevel(provinces);

			//Return statement
			return (comparison_value >= value);
		}

		function provinceDefenceLevelIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesDefenceLevel(provinces);

			//Return statement
			return (comparison_value > value);
		}

		function provinceDefenceLevelIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesDefenceLevel(provinces);

			//Return statement
			return (comparison_value <= value);
		}

		function provinceDefenceLevelIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesDefenceLevel(provinces);

			//Return statement
			return (comparison_value < value);
		}

		function provinceManpowerIs (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesManpower(provinces);

			//Return statement
			return (comparison_value == value);
		}

		function provinceManpowerIsGEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesManpower(provinces);

			//Return statement
			return (comparison_value >= value);
		}

		function provinceManpowerIsGreaterThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesManpower(provinces);

			//Return statement
			return (comparison_value > value);
		}

		function provinceManpowerIsLEQ (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesManpower(provinces);

			//Return statement
			return (comparison_value <= value);
		}

		function provinceManpowerIsLessThan (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var comparison_value = getProvincesManpower(provinces);

			//Return statement
			return (comparison_value < value);
		}
	}

	//Military (Offensive).
	{
		function provinceArmyIs (arg0_provinces, arg1_options) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.tag = (options.tag) ? getList(options.tag) : undefined;
			options.value = returnSafeNumber(options.value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var has_army = false;
				var local_province_armies = getProvinceArmies(provinces[i]);

				//Iterate over all local_province_armies
				for (var x = 0; x < local_province_armies.length; x++) {
					var is_general_valid = false;
					var is_strength_valid = false;
					var is_tag_valid = false;

					var is_valid_army = false;

					if (!options.tag) {
						is_tag_valid = true;
					} else if (options.tag.includes(getCurrentTag(local_province_armies[x].civID))) {
						is_tag_valid = true;
					}

					if (options.has_general) {
						is_general_valid = isGeneralEqualTo(local_province_armies[x].armyGeneral, options.has_general);
					} else {
						is_general_valid = true;
					}

					if (local_province_armies[x].iArmyRegimentSize > options.value)
						is_strength_valid = true;

					//Return statement
					if (is_tag_valid && is_general_valid && is_strength_valid)
						return true;
				}
			}

			//Return statement
			return false;
		}

		function provinceHasArmy (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_armies = getProvinceArmies(provinces[i])

				if (local_province_armies.length == 0)
					return false;
			}

			//Return statement
			return true;
		}

		function provinceHasArmyFrom (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_armies = getProvinceArmies(provinces[i]);

				//Iterate over all local_province_armies
				for (var x = 0; x < local_province_armies.length; x++) {
					var local_army = local_province_armies[x];
					var local_army_civ_tag = getCurrentTag(local_province_armies[x].civID);

					if (civ_tags.includes(local_army_civ_tag))
						return true;
				}
			}

			//Return statement
			return false;
		}
	}
}

//Initialise internal helper functions#
{
	function getProvincesDefenceLevel (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all provinces
		for (var i = 0; i < provinces.length; i++) {
			var local_province = getProvince(provinces[i]);

			comparison_sum += local_province.getFortLevel();
		}

		//Return statement
		return comparison_sum/provinces.length;
	}

	function getProvincesManpower (arg0_provinces) {
		//Convert from parameters
		var provinces = getList(arg0_provinces);

		//Declare local instance variables
		var comparison_sum = 0;

		//Iterate over all provinces
		for (var i = 0; i < provinces.length; i++) {
			var local_province = getProvince(provinces[i]);

			comparison_sum += local_province.getManpower();
		}

		//Return statement
		return comparison_sum;
	}
}