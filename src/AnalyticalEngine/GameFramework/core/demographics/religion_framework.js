//Import classes
{
	this.ReligionManager = Java.type("aoc.kingdoms.lukasz.map.ReligionManager");
}

//Initialise functions
{
	function getAllReligions (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_religion_keys = [];
		var all_religions = [];
		var religion_list = Game.religionManager.lReligions;
		var return_obj = {};

		//Iterate over religion_list
		for (var i = 0; i < religion_list.size(); i++) {
			var local_religion = religion_list.get(i);

			all_religion_keys.push(local_religion.Name);
			all_religions.push(local_religion);
			return_obj[i] = local_religion;
		}

		//Return statement
		if (options.return_keys) return all_religion_keys;
		return (!options.return_object) ?
			all_religions : return_obj;
	}

	function getCivilisationReligion (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var civ_religion_id = civ_obj.getReligionID();

		//Return statement
		return (!options.return_key) ? getReligion(civ_religion_id) : getReligion(civ_religion_id).Name;
	}

	function getReligion (arg0_religion_name, arg1_options) {
		//Convert from parameters
		var name = arg0_religion_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var religion_exists = [false, ""]; //[religion_exists, religion_obj];
		var religion_index = 0;
		var religion_list = Game.religionManager.lReligions;

		//Guard clauses if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return religion_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < religion_list.size(); i++) {
			var local_religion = religion_list.get(i);

			if (local_religion.Name.trim().toLowerCase().indexOf(search_name) != -1) {
				religion_index = i;
				religion_exists = [true, local_religion];
			}
		}
		for (var i = 0; i < religion_list.size(); i++) {
			var local_religion = religion_list.get(i);

			if (local_religion.Name.trim() == search_name) {
				religion_index = i;
				religion_exists = [true, local_religion];
			}
		}

		//Return statement
		if (options.return_key)
			return (religion_exists[0]) ? religion_exists[1].Name : undefined;
		if (options.return_object)
			return (religion_exists[0]) ? { index: religion_index, religion_obj: religion_exists[1] } : undefined;
		return (religion_exists[0]) ? religion_exists[1] : undefined;
	}

	function setCivilisationReligion (arg0_civ_tag, arg1_religion_name) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var religion_name = arg1_religion_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var religion_obj = getReligion(religion_name, { return_object: true });

		//Set civ_obj religion
		civ_obj.setReligionID_UpdateBonuses(religion_obj.index);
	}

	function setProvinceReligion (arg0_province_name, arg1_religion_name) {
		//Convert from parameters
		var province_name = arg0_province_name;
		var religion_name = arg1_religion_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);
		var religion_obj = getReligion(religion_name, { return_object: true });

		//Set province_obj religion
		province_obj.setReligion(religion_obj.index);
	}
}