//Initialise functions
{
	//Economy (Building).
	{
		function provinceAddBuilding (arg0_provinces, arg1_building_name, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_name = arg1_building_name;
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var building_obj = getBuilding(building_name, { return_object: true });

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				addBuilding(provinces[i], building_obj);
		}

		function provinceRemoveBuilding (arg0_provinces, arg1_building_name, arg2_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var building_name = arg1_building_name;
			var value = returnSafeNumber(arg2_value);

			//Declare local instance variables
			var building_obj = getBuilding(building_name, { return_object: true });

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				removeBuilding(provinces[i], building_obj);
		}
	}

	//Economy (Development).
	{
		function provinceModifyDevastation (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setDevastation(local_province.getDevastation() + value);
			}
		}

		function provinceModifyEconomy (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setEconomy(local_province.getEconomy() + value);
			}
		}

		function provinceModifyGrowthRate (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				Game.getProvinceData7(local_province.getProvinceID()).setIncreasedGrowthRate(
					Game.getProvinceData7(local_province.getProvinceID()).getIncreasedGrowthRate() + value
				);
				local_province.updateProvinceIncome();
				local_province.updateInfrastructureMax();

				local_province.updateCityScale();

				//Research handling
				if (local_province.haveResearchBuilding()) {
					var local_civ_id = local_province.getCivID();

					getCivilisation(local_civ_id).updateResearchPerMonth();
					Game.gameThread.addCivUpdateTotalIncomePerMonth(local_civ_id);
				}
			}
		}

		function provinceModifyInfrastructure (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setInfrastructure(local_province.getInfrastructure() + value);
			}
		}

		function provinceModifyManpower (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setManpower(local_province.getManpower() + value);
			}
		}

		function provinceModifyTaxEfficiency (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setTaxEfficiency(local_province.getTaxEfficiency() + value);

				//Income handling
				var local_civ_id = local_province.getCivID();

				local_province.updateProvinceIncome();
				Game.gameThread.addCivUpdateTotalIncomePerMonth(local_civ_id);
			}
		}

		function provinceSetDevastation (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setDevastation(value);
			}
		}

		function provinceSetEconomy (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setEconomy(value);
			}
		}

		function provinceSetGrowthRate (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				Game.getProvinceData7(local_province.getProvinceID()).setIncreasedGrowthRate(value);
				local_province.updateProvinceIncome();
				local_province.updateInfrastructureMax();

				local_province.updateCityScale();

				//Research handling
				if (local_province.haveResearchBuilding()) {
					var local_civ_id = local_province.getCivID();

					getCivilisation(local_civ_id).updateResearchPerMonth();
					Game.gameThread.addCivUpdateTotalIncomePerMonth(local_civ_id);
				}
			}
		}

		function provinceSetInfrastructure (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setInfrastructure(value);
			}
		}

		function provinceSetManpower (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setManpower(value);
			}
		}

		function provinceSetTaxEfficiency (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setTaxEfficiency(value);

				//Income handling
				var local_civ_id = local_province.getCivID();

				local_province.updateProvinceIncome();
				Game.gameThread.addCivUpdateTotalIncomePerMonth(local_civ_id);
			}
		}
	}

	//Economy (Pops).
	{
		function provinceModifyNationality (arg0_provinces, arg1_options) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var options = (arg1_options) ? arg1_options : {};

			//Declare local instance variables
			var civ_ids = [];

			//Iterate over all options.tag
			for (var i = 0; i < options.tag.length; i++)
				civ_ids.push(getCivilisationID(options.tag[i]));

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				for (var x = 0; x < civ_ids.length; x++) {
					var actual_value = (options.number) ?
						options.number : (local_province.getPopulationOfCivID(civ_ids[x])/local_province.getPopulationTotal()*options.value);

					local_province.updateIncreasePopulationOfCivID(civ_ids[x], actual_value);
				}
			}
		}

		function provinceModifyPopulation (arg0_provinces, arg1_value) { //[WIP] - Refactor to use provinceSetPopulation()
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var province_population_obj = new EventOutcome_Province_Population(value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i])
				var local_value = local_province.getPopulationTotal() + value;

				provinceSetPopulation(local_province, local_value);
			}
		}

		function provinceModifyPopulationPercentage (arg0_provinces, arg1_value) { //[WIP] - Refactor to use provinceSetPopulation()
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var province_population_obj = new EventOutcome_Province_Population(value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_population = getProvincePopulation(provinces[i]);

				var local_new_population = new Integer(Math.ceil(local_province_population*value));

				provinceSetPopulation(local_province, local_new_population);
			}
		}

		function provinceSetNationality (arg0_provinces, arg1_options) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var options = (arg1_options) ? arg1_options : {};

			//Declare local instance variables
			var civ_ids = [];

			//Iterate over all options.tag
			for (var i = 0; i < options.tag.length; i++)
				civ_ids.push(getCivilisationID(options.tag[i]));

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				for (var x = 0; x < civ_ids.length; x++) {
					var actual_value = (options.number) ?
						options.number : (local_province.getPopulationOfCivID(civ_ids[x])/local_province.getPopulationTotal()*options.value);

					local_province.setPopulationOfCivID(civ_ids[x], actual_value);
				}
			}
		}

		function provinceSetPopulation (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Declare local instance variables
			var province_population_obj = new EventOutcome_Province_Population(value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_population = local_province.getPopulationTotal()

				//Different logic for positive vs. negative growth due to internal structuring
				if (value < local_province_population) {
					var remaining_population_percentage = value/local_province_population;

					for (var x = local_province.getPopulationSize() - 1; x >= 0; x--)
						local_province.setPopulationOfCivID(
							local_province.getPopulationCivID(x), //(nCivID)
							new Integer(local_province.getPopulationID(x) - Math.floor(local_province.getPopulationID(x)*remaining_population_percentage)) //(nPopulation)
						);
				} else {
					province_population_obj.value = value - local_province_population;
					province_population_obj.updateProvince(local_province.getProvinceID());
				}
			}
		}

		function provinceSetReligion (arg0_provinces, arg1_religion_name) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var religion_name = arg1_religion_name;

			//Declare local instance variables
			var religion_obj = getReligion(religion_name);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				setProvinceReligion(provinces[i], religion_obj);
		}
	}

	//Meta.
	{
		function provinceRenameProvince (arg0_provinces, arg1_province_names) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var province_names = getList(arg1_province_names);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.getCity(0).setCityName(local_province.getProvinceID(), province_names[i]);
			}
		}
	}

	//Politics.
	{
		function provinceModifyUnrest (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setRevulutionaryRisk(local_province.getRevulutionaryRisk() + value);
			}
		}

		function provinceSetUnrest (arg0_provinces, arg1_value) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var value = returnSafeNumber(arg1_value);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				local_province.setRevulutionaryRisk(value);
			}
		}
	}
}