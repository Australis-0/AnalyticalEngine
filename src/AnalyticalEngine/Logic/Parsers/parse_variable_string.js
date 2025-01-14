//Initialise functions
{
	function parseVariableString (arg0_string, arg1_options) { //[WIP] - Finish function body
		//Convert from parameters
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.regex_replace) options.regex_replace = {};

		//Declare local instance variables
		var all_option_keys = getAllObjectKeys(options, { include_parent_keys: true });

		//Destructure all keys in options such that they are locally available for eval to use
		for (var i = 0; i < all_option_keys.length; i++) {
			var local_split_key = all_option_keys[i].split(".");
			var local_value = getObjectKey(options, all_option_keys[i]);

			//Destructure local object first
			if (local_split_key.length > 1) {
				eval("if (!" + local_split_key[0] + ") var " + local_split_key[0] + " = {};"); //[WIP] - This needs to be moved to an iterative method
				eval(all_option_keys[i] = local_value);
			} else {

			}
		}
	}
}