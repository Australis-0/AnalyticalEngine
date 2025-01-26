//Import classes
{
	this.TechnologyTree = Java.type("aoc.kingdoms.lukasz.map.technology.TechnologyTree");
}

//Initialise functions
{
	function getAllTechnologies (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_technologies = [];
		var all_technology_keys = [];
		var technology_list = TechnologyTree.lTechnology;
		var return_obj = {};

		//Iterate over technology_list
		for (var i = 0; i < technology_list.size(); i++) {
			var local_technology = technology_list.get(i);

			all_technology_keys.push(local_technology.Name);
			all_technologies.push(local_technology);
			return_obj[i] = local_technology;
		}

		//Return statement
		if (options.return_keys) return all_technology_keys;
		return (!options.return_object) ?
			all_technologies : return_obj;
	}

	function getCivilisationResearchedTechnologies (arg0_civ_tag, arg1_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.all_technologies) options.all_technologies = getAllTechnologies();

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var researched_technologies = [];

		if (civ_obj)
			for (var i = 0; i < options.all_technologies.length; i++) {
				var local_technology = options.all_technologies[i];
				var local_technology_id = local_technology.ID;

				if (civ_obj.getTechResearched(local_technology_id))
					researched_technologies.push(local_technology.Name);
			}

		//Return statement
		return researched_technologies;
	}

	function getCivilisationResearchPerMonth (arg0_civ_tag) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);

		//Return statement
		return civ_obj.fResearchPerMonth;
	}

	function getTechnology (arg0_technology_name) {
		//Convert from parameters
		var name = arg0_technology_name;

		//Declare local instance variables
		var technology_exists = [false, ""]; //[technology_exists, technology_obj];
		var technology_list = TechnologyTree.lTechnology;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return technology_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < technology_list.size(); i++) {
			var local_technology = technology_list.get(i);

			if (local_technology.Name)
				if (local_technology.Name.trim().toLowerCase().indexOf(search_name) != -1)
					technology_exists = [true, local_technology];
		}
		for (var i = 0; i < technology_list.size(); i++) {
			var local_technology = technology_list.get(i);

			if (local_technology.Name)
				if (local_technology.Name.trim().toLowerCase() == search_name)
					technology_exists = [true, local_technology];
		}

		//Return statement
		return (technology_exists[0]) ? technology_exists[1] : undefined;
	}

	function instantResearch (arg0_civ_tag, arg1_technology_name, arg2_options) {
		//Convert from parameters
		var civ_tag = arg0_civ_tag;
		var technology_name = arg1_technology_name;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var civ_obj = getCivilisation(civ_tag);
		var technology_obj = getTechnology(technology_name);

		//Call .addTechnology()
		civ_obj.addTechnology(technology_obj.ID, false);
	}
}