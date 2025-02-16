//Initialise functions
{
	function getAllDiseases (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_disease_keys = [];
		var all_diseases = [];
		var disease_list = PlagueManager.lPlagues;
		var return_obj = {};

		//Iterate over disease_list
		for (var i = 0; i < disease_list.size(); i++) {
			var local_disease = disease_list.get(i);

			all_disease_keys.push(local_disease.Name);
			all_diseases.push(local_disease);
			return_obj[i] = local_disease;
		}

		//Return statement
		if (options.return_keys) return all_disease_keys;
		return (!options.return_object) ?
			all_diseases : return_obj;
	}

	function getDisease (arg0_disease_name, arg1_options) {
		//Convert from parameters
		var name = arg0_disease_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var disease_exists = [false, ""]; //[disease_exists, disease_obj];
		var disease_index = 0;
		var disease_list = PlagueManager.lPlagues;

		//Guard clause if name matches ID; or is object
		if (typeof name == "object") return name;
		if (!isNaN(name)) return disease_list.get(name);

		//.Name search - soft search 1st, hard search 2nd
		var search_name = name.trim().toLowerCase();

		for (var i = 0; i < disease_list.size(); i++) {
			var local_disease = disease_list.get(i);

			if (local_disease.Name.trim().toLowerCase().indexOf(search_name) != -1) {
				disease_index = i;
				disease_exists = [true, local_disease];
			}
		}
		for (var i = 0; i < disease_list.size(); i++) {
			var local_disease = disease_list.get(i);

			if (local_disease.Name.trim().toLowerCase() == search_name) {
				disease_index = i;
				disease_exists = [true, local_disease];
			}
		}

		//Return statement
		if (options.return_key)
			return (disease_exists[0]) ? disease_exists[1].Name : undefined;
		if (options.return_object)
			return (disease_exists[0]) ? { index: disease_index, disease_obj: disease_exists[1] } : undefined;
		return (disease_exists[0]) ? disease_exists[1] : undefined;
	}
}