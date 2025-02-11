//Initialise functions
{
	function fixSeaProvinces () { //[WIP] - Finish function
		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var sea_provinces = [];

		//Iterate over all_provinces and detect whether it is a sea province or not
		for (var i = 0; i < all_provinces.length; i++)
			if (isSeaProvince(all_provinces[i]))
				sea_provinces.push(all_provinces[i]);

		//Set sea_provinces to tag 'neu'
		setProvinceOwners(sea_provinces, "neu");
	}

	/**
	 * getAllProvinces() - Returns an array of all province objects.
	 *
	 * @returns {Array<Object>}
	 */
	function getAllProvinces () {
		//Declare local instance variables
		var all_provinces = [];
		var number_of_provinces = Game.getProvincesSize();

		//Iterate over all_provinces
		for (var i = 0; i < number_of_provinces; i++) {
			var local_province = Game.getProvince(i);

			if (local_province != null)
				all_provinces[i] = local_province;
		}

		//Return statement
		return all_provinces;
	}

	/**
	 * getProvince() - Returns a province object based upon its given province name/ID.
	 * @param {String} arg0_province_name - The province name/ID to return the province for.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.return_key=false] - Whether to return the Province ID instead of a Province object.
	 *
	 * @returns {Object|String}
	 */
	function getProvince (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Guard clause if province is already in province_name
		if (typeof province_name == "object") return province_name;

		//Declare local instance variables
		var province_id;
		var province_obj;

		//Try/catch to check for Province names as well
		if (!isNaN(province_name)) {
			//Integer handling
			province_id = parseInt(province_name);
			province_obj = Game.getProvince(province_id);
		} else {
			//String handling
			var all_provinces = getAllProvinces();

			//Iterate over all_provinces to find a province name match
			for (var i = 0; i < all_provinces.length; i++)
				if (all_provinces[i])
					if (all_provinces[i].getProvinceName().equalsIgnoreCase(province_name)) {
						province_id = all_provinces[i].getProvinceID();
						province_obj = all_provinces[i];
					}
		}

		//Return statement
		return (!options.return_key) ? province_obj : province_id;
	}

	function getProvinceArmies (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_armies = [];

		//Iterate over all armies in province_obj
		for (var i = 0; i < province_obj.getArmySize(); i++)
			province_armies.push(province_obj.getArmy(i));

		//Return statement
		return province_armies;
	}

	/**
	 * getProvinceCentre() - Returns the centrepoint of a province.
	 * @param {String} arg0_province_name - The province name to fetch the centrepoint for.
	 *
	 * @returns {Array<number, number>}
	 */
	function getProvinceCentre (arg0_province_name) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_name);

		//Return statement
		return [province_obj.getCenterX(), province_obj.getCenterY()];
	}

	function getProvinceController (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var province_obj = getProvince(province_name);
		var province_id = province_obj.getProvinceID();

		var occupying_civ_id = Game.getProvinceData(province_id).getOccupiedByCivID();

		if (occupying_civ_id == 0)
			occupying_civ_id = province_obj.getCivID();

		//Return statement
		return (!options.return_object) ?
			getCurrentTag(occupying_civ_id) : getCivilisation(occupying_civ_id);
	}

	/**
	 * getProvinceCores() - Fetches an array of all CivTags that have cores on a specific province.
	 * @param {String} arg0_province_name - The province name to fetch cores for.
	 *
	 * @returns {Array<String>}
	 */
	function getProvinceCores (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		var core_ids = [];
		var core_tags = [];

		//Iterate over all cores and push their Civilisation integer ids
		for (var i = 0; i < province_obj.iCoresSize; i++)
			core_ids.push(province_obj.getCore(i));
		for (var i = 0; i < core_ids.length; i++)
			core_tags.push(Game.getCiv(core_ids[i]).getCivTag());

		//Return statement
		return core_tags;
	}

	function getProvinceEconomy (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.getEconomy();
	}

	function getProvinceIncome (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.getProvinceIncome();
	}

	function getProvinceInfrastructure (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.getInfrastructure();
	}

	/**
	 * getProvinceOwner() - Returns the province owner's CivTag.
	 * @param {String} arg0_province_name - The province name to fetch the province owner for.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.return_object=false] - Whether to return the Civilisation object.
	 *
	 * @returns {String|Object}
	 */
	function getProvinceOwner (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var province_obj = getProvince(province_name);
		var province_owner = Game.getCiv(province_obj.getCivID());

		//Return statement
		return (!options.return_object) ?
			province_owner.getCivTag() : province_owner;
	}

	function getProvinceResource (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);
		var resource_type;

		if (province_obj) {
			var resource_id = province_obj.getResourceID();

			//Handle 'None' resource
			if (resource_id < 0) return "None";
			resource_type = getResource(resource_id).Name;
		}

		//Return statement
		if (resource_type != undefined)
			return resource_type;
	}

	function getProvinceSiegeOccupier (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var best_army_id = 0;
		var best_civ_id = 0;
		var province_obj = getProvince(province_name);

		//Iterate over all armies in province_obj
		for (var i = 0; i < province_obj.getArmySize(); i++)
			if (DiplomacyManager.isAtWar(province_obj.getCivID(), province_obj.getArmy(i).civID))
				if (province_obj.getArmy(i).iArmy > best_army_id) {
					best_army_id = province_obj.getArmy(i).iArmy;
					best_civ_id = province_obj.getArmy(i).civID;
				}

		//Return statement
		if (best_civ_id != 0)
			return (!options.return_object) ?
				getCurrentTag(best_civ_id) : getCivilisation(best_civ_id);
	}

	function getProvinceTaxEfficiency (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.getTaxEfficiency();
	}

	function getProvinceTerrain (arg0_province_name, arg1_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var province_obj = getProvince(province_name);
		var province_terrain_id = province_obj.getTerrainID();

		//Return statement
		return (!options.return_key) ?
			getTerrain(province_terrain_id) : province_terrain_id;
	}

	function isOccupied (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.isOccupied();
	}

	function isSeaProvince (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return province_obj.getSeaProvince();
	}

	function isUnderSiege (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Return statement
		return SiegeManager.isProvinceUnderSiege(province_obj.getProvinceID());
	}

	function setProvinceController (arg0_province_name, arg1_civ_name, arg2_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var civ_name = arg1_civ_name;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);
		var province_obj = getProvince(province_name);

		var from_civ_id = province_obj.getCivID();
		var province_id = province_obj.iProvinceID;
		var to_civ_id = civ_obj.iCivID;

		//Assign province controller
		province_obj.setOccupiedByCivID(to_civ_id);

		//Create UpdateTask to update the current map; deal with interfaces
		if (!options.do_not_display) {
			var UpdateTask = Java.extend(Game.SimpleTask, {
				update: function () {
					CivilizationRegionsManager.buildCivilizationsRegion(this.id);
					Game.updateProvincesInView = true;
					CivilizationRegionsManager.updateRegionsInView = true;
				}
			});
			var update_task_obj = new UpdateTask("buildCivilizationsRegion" + from_civ_id, from_civ_id);
			Game.gameThreadUpdate.addSimpleTask(update_task_obj);
		}
	}

	function setProvinceControllers (arg0_province_names, arg1_civ_name) {
		//Convert from parameters
		var province_names = getList(arg0_province_names);
		var civ_name = arg1_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Iterate over all province_names
		for (var i = 0; i < province_names.length; i++)
			setProvinceController(province_names[i], civ_name, {
				do_not_display: (i == province_names.length - 1)
			});
	}

	function setProvinceOwner (arg0_province_name, arg1_civ_name, arg2_options) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var civ_name = arg1_civ_name;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);
		var province_obj = getProvince(province_name);

		var from_civ_id = province_obj.getCivID();
		var from_civ_obj = getCivilisation(province_obj.getCivID());
		var province_id = province_obj.iProvinceID;
		var to_civ_id = civ_obj.iCivID;

		//Assign province owner
		province_obj.setCivID_RemoveOldAddNewToCiv(to_civ_id);
		Game.updateProvinceBorder(province_id);

		//Create UpdateTask to update the current map; deal with interfaces
		if (!options.do_not_display) {
			var UpdateTask = Java.extend(Game.SimpleTask, {
				update: function () {
					CivilizationRegionsManager.buildCivilizationsRegion(this.id);
					Game.updateProvincesInView = true;
					CivilizationRegionsManager.updateRegionsInView = true;
				}
			}); //(Civilization).addProvince_LoadScenario
			var update_task_obj = new UpdateTask("buildCivilizationsRegion" + from_civ_id, from_civ_id);
			Game.gameThreadUpdate.addSimpleTask(update_task_obj);
		}
	}

	/**
	 * setProvinceOwners() - Sets a new Civilisation owner for multiple provinces.
	 * @param {Array<number|Object<Province>|String>} arg0_province_names - The province names to transfer to a new owner.
	 * @param {number|Object<Civilisation>|String} arg1_civ_name - The civilisation to transfer provinces to.
	 *
	 * @returns {number}
	 */
	function setProvinceOwners (arg0_province_names, arg1_civ_name) {
		//Convert from parameters
		var province_names = getList(arg0_province_names);
		var civ_name = arg1_civ_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_name);

		//Iterate over all province_names
		for (var i = 0; i < province_names.length; i++)
			setProvinceOwner(province_names[i], civ_name, {
				do_not_display: (i == province_names.length - 1)
			});

		//Return statement
		return province_names.length;
	}

	function setProvincePopulationGrowthRate (arg0_province_name, arg1_value) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var value = Math.round(returnSafeNumber(arg1_value*100));

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		//Set growth rate
		province_obj.setGrowthRate(value);
		ProvinceDrawDetails.updateDrawProvinceDetails_GrowthRate(province_obj.getProvinceID());

		//Return statement
		return value;
	}
	/**
	 * updateProvinces() - Fixes all bugs related to province status changes (e.g. sea province assignment).
	 */
	function updateProvinces () {
		//Declare local instance variables
		var all_provinces = getAllProvinces();

		//
	}
}