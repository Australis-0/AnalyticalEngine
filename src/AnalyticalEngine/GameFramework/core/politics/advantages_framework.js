//Import classes
{
	this.AdvantagesManager = Java.type("aoc.kingdoms.lukasz.map.AdvantagesManager");
}

//Initialise functions
{
	function getAllAdvantages (arg0_options) {
		//Declare local instance variables
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_advantage_keys = [];
		var all_advantages = [];
		var return_obj = [];
		var advantages_list = AdvantagesManager.advantages;

		//Iterate over advantages_list
		for (var i = 0; i < advantages_list.size(); i++) {
			var local_advantage = advantages_list.get(i);

			all_advantage_keys.push(i);
			all_advantages.push(local_advantage);
			return_obj[i] = local_advantage;
		}

		//Return statement
		if (options.return_keys) return all_advantage_keys;
		return (!options.return_object) ?
			all_advantages : return_obj;
	}

	function getAdvantage (arg0_advantage_id, arg1_options) {
		//Convert from parameters
		var advantage_id = parseInt(arg0_advantage_id);
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var advantages_list = AdvantagesManager.advantages;

		//Return statement
		if (advantage_id >= advantages_list.size()) advantage_id = advantages_list.size() - 1;
		if (advantage_id < 0) advantage_id = 0;

		return (!options.return_key) ? advantages_list.get(advantage_id) : advantage_id;
	}

	function getCivilisationUnlockedAdvantages (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_advantage_keys = [];
		var civ_advantages = [];
		var civ_obj = getCivilisation(civ_tag);

		//Iterate over civ_obj.lAdvantages
		for (var i = 0; i < civ_obj.lAdvantages.size(); i++) {
			var local_advantage = civ_obj.lAdvantages.get(i);

			civ_advantage_keys.push(local_advantage.id);
			civ_advantages.push(local_advantage);
		}

		//Return statement
		return (!options.return_keys) ? civ_advantages : civ_advantage_keys;
	}

	function getCivilisationUnlockedAdvantagesValue (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		var civ_advantage_keys = getCivilisationUnlockedAdvantages(civ_obj, { return_keys: true });
		var civ_advantages_value = 0;

		//Iterate over civ_advantage_keys
		for (var i = 0; i < civ_advantage_keys.length; i++)
			civ_advantages_value += civ_obj.getAdvantageLvl(civ_advantage_keys[i]) + 1;

		//Return statement
		return civ_advantages_value;
	}
}