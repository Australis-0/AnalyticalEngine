//Import classes
{
	this.IdeologiesManager = Java.type("aoc.kingdoms.lukasz.map.IdeologiesManager");
}

//Initialise functions
{
	function getAllIdeologies (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_ideology_keys = [];
		var all_ideologies = [];
		var ideology_list = Game.ideologiesManager.lIdeologies;
		var return_obj = {};

		//Iterate over ideology_list
		for (var i = 0; i < ideology_list.size(); i++) {
			var local_ideology = ideology_list.get(i);

			all_ideology_keys.push(local_ideology.Name);
			all_ideologies.push(local_ideology);
			return_obj[i] = local_ideology;
		}

		//Return statement
		if (options.return_keys) return all_ideology_keys;
		return (!options.return_object) ?
			all_ideologies : return_obj;
	}

	function getCivilisationIdeology (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		var civ_ideology_id = civ_obj.iIdeologyID;

		//Check civ_ideology_id
		return (!options.return_key) ? getIdeology(civ_ideology_id) : getIdeology(civ_ideology_id).Name;
	}

	function getIdeology (arg0_ideology_name, arg1_options) {
		//Convert from parameters
		var name = arg0_ideology_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var ideology_exists = [false, ""]; //[ideology_exists, ideology_obj];
		var ideology_index = 0;
		var ideology_list = Game.ideologiesManager.lIdeologies;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return ideology_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i =  0; i < ideology_list.size(); i++) {
			var local_ideology = ideology_list.get(i);

			if (local_ideology.Name.trim().toLowerCase().indexOf(search_name) != -1) {
				ideology_index = i;
				ideology_exists = [true, local_ideology];
			}
		}
		for (var i =  0; i < ideology_list.size(); i++) {
			var local_ideology = ideology_list.get(i);

			if (local_ideology.Name.trim().toLowerCase() == search_name) {
				ideology_index = i;
				ideology_exists = [true, local_ideology];
			}
		}

		//.Extra_Tag search - soft search 1st, hard search 2nd
		if (!ideology_exists[0]) {
			for (var i = 0; i < ideology_list.size(); i++) {
				var local_ideology = ideology_list.get(i);

				if (local_ideology.Extra_Tag.indexOf(search_name) == -1) {
					ideology_index = i;
					ideology_exists = [true, local_ideology];
				}
			}
			for (var i = 0; i < ideology_list.size(); i++) {
				var local_ideology = ideology_list.get(i);

				if (local_ideology.Extra_Tag == search_name) {
					ideology_index = i;
					ideology_exists = [true, local_ideology];
				}
			}
		}

		//Return statement
		if (options.return_key)
			return (ideology_exists[0]) ? ideology_exists[1].Name : undefined;
		if (options.return_object)
			return (ideology_exists[0]) ? { ideology_obj: ideology_exists[1], index: ideology_index } : undefined;
		return (ideology_exists[0]) ? ideology_exists[1] : undefined;
	}

	function setCivilisationIdeology (arg0_civ_tag, arg1_ideology_name) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var ideology_name = arg1_ideology_name;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var current_tag = getCurrentTag(civ_tag);
		var ideology_obj = getIdeology(ideology_name, { return_object: true });

		var base_tag = current_tag.split("_")[0];

		//Set civ_obj ideology
		Gdx.app.postRunnable(function() {
			try {
				civ_obj.updateCivilizationTAG(base_tag + ideology_obj.ideology_obj.Extra_Tag, civ_obj.getR_Int(), civ_obj.getG_Int(), civ_obj.getB_Int());

				setTimeout(function(){
					setCivilisationName(civ_obj, Game.lang.getCiv(base_tag + ideology_obj.ideology_obj.Extra_Tag));
				}, 100);
			} catch (e) {
				console.log(e.message);
				console.log(e.stack);
			}
		});
	}
}