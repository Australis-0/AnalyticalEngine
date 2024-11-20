//Initialise functions
{
	/**
	 * createObjectSearch() - Creates a function to search an object regularly using a substring. Similar to createSmartSearch(), but creates a 'relevancy index' for each potential entry and offices them to a return object.
	 *
	 * @param {Object} [arg0_options]
	 * @param {boolean} [arg0_options.exclude_zero_relevance=true] - Whether to exclude entries with zero or less relevance from the end result.
	 * @param {String} [arg0_options.function_name] - The function name to define this search as in the global namespace.
	 * @param {String} [arg0_options.priority_compounding="multiplicative"] - Either 'linear' or 'multiplicative'. 'multiplicative' by default.
	 * @param {Array<String>} [arg0_options.priority_order] - The order of keys whose value should be searhced, both soft/hard. First is most important, last is least important. Note that these values compound on top of each other.
	 *
	 * @returns {Function}
	 */
	function createObjectSearch (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (options.exclude_zero_relevance != false) options.exclude_zero_relevance = true;
		if (!options.priority_compounding) options.priority_compounding = "multiplicative";
		if (!options.priority_order) options.priority_order = ["key"];

		//Declare local instance variables
		var function_expression = []; //Here 'object' is the variable to search for

		//Function expression syntax: (arg0_object, arg1_input)
		//Index all keys first
		function_expression.push("/*Convert from parameters*/");
		function_expression.push("var object = arg0_object;");
		function_expression.push("var input = arg1_input;");
		function_expression.push("");
		function_expression.push("//Declare local instance variables");
		function_expression.push("var all_object_keys = Object.keys(object);");
		function_expression.push("var lowercase_input = lowercase_input;");
		function_expression.push("var processed_object = {};");
		function_expression.push("");
		function_expression.push("/*Guard clause for object*/");
		function_expression.push("if (typeof input == \"object\") return input;");

		//Iterate over options.priority_order
		for (var i = 0; i < options.priority_order.length; i++) {
			var local_search = options.priority_order[i];

			//<key> handler
			if (local_search == "key") {
				// Check for substring relevancy
				function_expression.push("for (var i = 0; i < all_object_keys.length; i++) {");
				function_expression.push("  if (all_object_keys[i].toLowerCase().indexOf(lowercase_input) != -1) {");
				function_expression.push("    var local_relevancy = input.length/all_object_keys[i].length;");
				function_expression.push("    var local_score = processed_object[all_object_keys[i]];");
				function_expression.push("");
				function_expression.push("    if (!local_score) {");
				function_expression.push("      processed_object[all_object_keys[i]] = {");
				function_expression.push("        id: \"" + all_object_keys[i] + "\",");
				function_expression.push("        value: " + local_relevancy);
				function_expression.push("      };");
				function_expression.push("    } else {");
				function_expression.push("      local_score.value = local_score.value " +
					(options.priority_compounding == "multiplicative" ? "*" : "+") + " local_relevancy;");
				function_expression.push("    }");
				function_expression.push("  }");
				function_expression.push("}");
			} else {
				// Check for local value relevancy
				function_expression.push("for (var i = 0; i < all_object_keys.length; i++) {");
				function_expression.push("  var local_value = object[all_object_keys[i]];");
				function_expression.push("");
				function_expression.push("  try {");
				function_expression.push("    if (local_value." + local_search + ")");
				function_expression.push("      if (local_value." + local_search + ".toLowerCase().indexOf(lowercase_input) != -1) {");
				function_expression.push("        var local_relevancy = input.length / local_value." + local_search + ".length;");
				function_expression.push("        var local_score = processed_object[all_object_keys[i]];");
				function_expression.push("");
				function_expression.push("        if (!local_score) {");
				function_expression.push("          processed_object[all_object_keys[i]] = {");
				function_expression.push("            id: \"" + all_object_keys[i] + "\",");
				function_expression.push("            value: " + local_relevancy);
				function_expression.push("          };");
				function_expression.push("        } else {");
				function_expression.push("          local_score.value = local_score.value " +
					(options.priority_compounding == "multiplicative" ? "*" : "+") + " local_relevancy;");
				function_expression.push("        }");
				function_expression.push("      }");
				function_expression.push("  } catch (e) {}");
				function_expression.push("}");
			}
		}

		//Sort object
		function_expression.push("processed_object = sortObject(processed_object);");
		function_expression.push("");
		function_expression.push("/*Return statement*/");
		function_expression.push("return processed_object");

		//Declare function
		var equation_function = new Function("arg0_object", "arg1_input", function_expression.join(""));

		this[function_name] = equation_function;

		//Return statement
		return this[function_name];
	}

	/**
	 * createSmartSearch() - Defines a smart search function off of which various attributes are checked in a specific order, both soft and hard.
	 *
	 * @param {Object} [arg0_options]
	 * @param {String} [arg0_options.function_name] - The function name to define this search as in the global namespace.
	 * @param {Array<String>} [arg0_options.priority_order] - The order in which to search, both soft/hard. First is most important, last is least important. 'key' defines base key.
	 * @param {boolean} [arg0_options.hard_search=true] - Whether hard search is enabled.
	 * @param {boolean} [arg0_options.soft_search=true] - Whether soft search is enabled.
	 *
	 * @returns {Function}
	 */
	function createSmartSearch (arg0_options) {
		//Convert options
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.priority_order) options.priority_order = ["key"];

		if (options.hard_search != false) options.hard_search = true;
		if (options.soft_search != false) options.soft_search = true;

		//Declare local instance variables
		var function_expression = []; //Here 'object' is the variable to search for

		//Function expression syntax: (arg0_object, arg1_input, arg2_options)
		//Index all keys first
		function_expression.push("/*Convert from parameters*/");
		function_expression.push("var object = arg0_object;");
		function_expression.push("var input = arg1_input;");
		function_expression.push("");
		function_expression.push("//Declare local instance variables");
		function_expression.push("var all_object_keys = Object.keys(object);");
		function_expression.push("var lowercase_input = input.toLowerCase();");
		function_expression.push("var variable_exists = [false, undefined]; /*[<variable_exists>, <variable_value>];*/");
		function_expression.push("");
		function_expression.push("/*Object/key guard clause*/");
		function_expression.push("if (typeof input == \"object\") return input;");
		function_expression.push("if (object[input]) return (!options.return_key) ? object[input] : input;");

		//Iterate over options.priority_order
		for (var i = 0; i < options.priority_order.length; i++) {
			var local_search = options.priority_order[i];

			//Post 0-index encapsulation for priority
			if (i > 0)
				function_expression.push("if (!variable_exists[0]) {");

			//<key> handler
			if (local_search == "key") {
				//Soft search handler
				if (options.soft_search) {
					function_expression.push("for (var i = 0; i < all_object_keys.length; i++)");
					function_expression.push("  if (all_object_keys[i].toLowerCase().indexOf(lowercase_input) != -1)");
					function_expression.push("    variable_exists = [true, (!options.return_key) ? object[all_object_keys[i]] : all_object_keys[i]];");
				}

				//Hard search handler
				if (options.hard_search) {
					function_expression.push("for (var i = 0; i < all_object_keys.length; i++)");
					function_expression.push("  if (all_object_keys[i].toLowerCase() == lowercase_input)");
					function_expression.push("    variable_exists = [true, (!options.return_key) ? object[all_object_keys[i]] : all_object_keys[i]];");
				}
			} else {
				//Soft search handler
				if (options.soft_search) {
					function_expression.push("for (var i = 0; i < all_object_keys.length; i++) try {");
					function_expression.push("  var local_value = object[all_object_keys[i]]." + local_search + ";");
					function_expression.push("");
					function_expression.push("  if (local_value.toLowerCase().indexOf(lowercase_input) != -1)");
					function_expression.push("    variable_exists = [true, (!options.return_key) ? object[all_object_keys[i]] : all_object_keys[i]];");
					function_expression.push("} catch {}");
				}

				//Hard search handler
				if (options.hard_search) {
					function_expression.push("for (var i = 0; i < all_object_keys.length; i++) try {");
					function_expression.push("  var local_value = object[all_object_keys[i]]." + local_search + ";");
					function_expression.push("");
					function_expression.push("  if (local_value.toLowerCase() == lowercase_input)");
					function_expression.push("    variable_exists = [true, (!options.return_key) ? object[all_object_keys[i]] : all_object_keys[i]];");
					function_expression.push("} catch {}");
				}
			}

			//Post 0-index encapsulation for priority
			if (i > 0)
				function_expression.push("}");
		}

		//Append return statement
		function_expression.push("return (variable_exists[0]) ? variable_exists[1] : undefined;");

		//Declare function
		var equation_function = new Function("arg0_object", "arg1_input", "arg2_options", function_expression.join(""));
		this[options.function_name] = equation_function;

		//Return statement
		return this[options.function_name];
	}

	/**
	 * deleteSmartSearch() - Deletes a smart search function.
	 * @param {String} arg0_name - The .function_name of the smart search to delkete.
	 */
	function deleteSmartSearch (arg0_function_name) {
		//Convert from parameters
		var function_name = arg0_function_name;

		//Delete from global
		delete this[function_name];
	}
}