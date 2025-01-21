//Initialise functions
{
	/**
	 * parseEffect() - Parses an effect scope.
	 * @param {Object} arg0_scope
	 * @param {Object} [arg1_options]
	 *  @param {number} [arg1_options.depth=0] - Optimisation parameter. The current recursive depth.
	 *  @param {Array<Object>} [arg1_options.scopes]
	 */
	function parseEffect (arg0_scope, arg1_options) { //[WIP] - Finish function body
		//Convert from parameters
		var scope = (arg0_scope) ? arg0_scope : {};
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (options.depth == undefined) options.depth = 0;
			options.depth++;
		if (!options.options) options.options = {}; //Stores all local variables
			if (!options.options.date) options.options.date = getCurrentDate();
		if (!options.scopes) options.scopes = [{ scope_type: "global" }]; //options.scopes is a CascadingScope array, with the last element being the most recent scope and containing variables related to it.

		//Declare local instance variables
		var all_recursive_scope_keys = getAllObjectKeys(scope, { include_parent_keys: true });
		var last_scope_obj = options.scopes[options.scopes.length - 1];

		for (var i = 0; i < all_recursive_scope_keys.length; i++) {
			var local_value = getList(getObjectKey(scope, all_recursive_scope_keys[i]));

			//Test to make sure that local_value[0] is indeed a string; and that local_value is 1 long
			if (local_value.length == 1)
				if (typeof local_value[0] == "string") {
					//Direct variable substitution if detected as valid
					if (getObjectKey(options.options, local_value[0]) != undefined)
						setObjectKey(scope, all_recursive_scope_keys[i], getObjectKey(options.options, local_value[0]));
					for (var x = 0; x < options.scopes.length; x++)
						if (getObjectKey(options.scopes[x], local_value[0]) != undefined)
							setObjectKey(scope, all_recursive_scope_keys[i], getObjectKey(options.scopes[x], local_value[0]));

					//If the string is more complex; attempt to use parseVariableString() on it
					try {
						options.options.ignore_errors = true;
						setObjectKey(scope, all_recursive_scope_keys[i], parseVariableString(local_value[0], options.options));
						delete options.options.ignore_errors;
					} catch (e) {
						console.log(scope, all_recursive_scope_keys[i], local_value[0], options.options);
						console.log(e.message);
					}
				}
		}
		var suboptions = options.options;

		//.limit parser
		if (scope.limit) {
			var new_options = JSON.parse(JSON.stringify(options));
			limit_fulfilled = parseLimit(scope.limit, new_options);
		}

		//.effect parser
		if (limit_fulfilled) {
			var all_scope_keys = Object.keys(scope);

			//Iterate over all_scope_keys
			for (var i = 0; i < all_scope_keys.length; i++) {
				var local_value = getList(scope[all_scope_keys[i]]);

				//1. Gamestate Scopes Handler
				{
					if (all_scope_keys[i] == "global" || all_scope_keys[i].startsWith("global_"))
						if (last_scope_obj.type != "global") {
							var new_options = JSON.parse(JSON.stringify(options));
								new_options.scopes.push({ scope_type: "global" });
						}
				}

			}
		}
	}
}