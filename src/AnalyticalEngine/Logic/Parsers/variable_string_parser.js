//Initialise functions
{
	/**
	 * parseDisplayLocalisationString() - Parses a LocalisationString directy.
	 * @param {String} arg0_string - The string to process.
	 * @param {Object} [arg1_options]
	 *  @param {Object} [arg1_options."key_name"] - Key/value pairs to feed into the local environment for resolving.
	 *  @param {Object} [arg1_options.regex_replace={}] - Strings to replace in terms of localisation.
	 *
	 * @returns {String}
	 */
	function parseDisplayLocalisationString (arg0_string, arg1_options) {
		//Convert from parameters
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var result = ""; //Stores the processed string
		var stack = []; //Stack for tracking nested {}
		var start_index = -1; //Start index of the current outermost block

		//Iterate over all string characters
		for (var i = 0; i < string.length; i++)
			if (string[i] == "{") {
				stack.push(i);
				if (stack.length == 1)
					start_index = i; //Mark the start of an outermost block
			} else if (string[i] == "}") {
				stack.pop();
				if (stack.length == 0 && start_index != -1) {
					//Found an outermost block
					var local_expression = string.slice(start_index + 1, i);
					var local_value = parseVariableString(local_expression, options);

					result += string.slice(0, start_index) + local_value;
					string = string.slice(i + 1);
					i = -1;
					start_index = -1;
				}
			}

		//Append any remaining part of the string
		result += string;

		//Return statement
		return result;
	}

	/**
	 * parseVariableString() - Parses a variable string and returns its resolved value.
	 * @param {String} arg0_string - The string which to resolve.
	 * @param {Object} [arg1_options]
	 *  @param {Object} [arg1_options."key_name"] - Key/value pairs to feed into the local environment for resolving.
	 *  @param {Object} [arg1_options.regex_replace={}] - Strings to replace in terms of localisation.
	 *
	 * @returns {*}
	 */
	function parseVariableString (arg0_string, arg1_options) {
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
				var define_string = [];

				for (var x = 0; x < local_split_key.length - 1; x++)
					if (x == 0) {
						define_string.push("if (" + local_split_key[0] + " == undefined) var " + local_split_key[0] + " = {};");
					} else {
						var local_join_key = [];

						for (var y = 0; y < x + 1; y++)
							local_join_key.push(local_split_key[y]);
						local_join_key = local_join_key.join(".");

						define_string.push("if (" + local_join_key + " == undefined) " + local_join_key + " = {};");
					}
				eval(define_string.join("."));
			} else {
				eval("var " + all_option_keys[i] + " = local_value;");
			}
		}

		var evaluated_string;
		try {
			try {
				//Iterate over options.regex_replace
				var all_regex_replace_keys = Object.keys(options.regex_replace);

				for (var i = 0; i < all_regex_replace_keys.length; i++) {
					var local_value = options.regex_replace[all_regex_replace_keys[i]];
					var local_regexp = new RegExp(all_regex_replace_keys[i], "gm");

					try {
						string = string.replace(local_regexp, local_value);
					} catch (e) {}
				}
			} catch (e) {}

			evaluated_string = eval(string);
		} catch (e) {
			if (!options.ignore_errors) try {
				console.log("Options:", options);
				console.log("String:", string);
				console.log(e);
			} catch (e) {}
			evaluated_string = string;
		}

		//Return statement
		return evaluated_string;
	}
}