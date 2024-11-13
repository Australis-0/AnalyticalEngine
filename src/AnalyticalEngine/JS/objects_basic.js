//Initialise Basic Objects functions
{
	/**
	 * cleanObject() - Removes both zero values and undefined/null values from an object by default.
	 * @param {Object} arg0_object - The input object to pass.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.remove_falsey=false] - Whether to remove falsey values.
	 * @param {boolean} [arg1_options.remove_zeroes=false] - Whether to remove zero values from the cleaned object.
	 *
	 * @returns {Object}
	 */
	function cleanObject (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Clean stringify object first before parsing remove_zeroes
		var cleaned_object = cleanStringify(object);

		var all_cleaned_keys = Object.keys(cleaned_object);

		//Iterate over all_cleaned_keys
		for (var i = 0; i < all_cleaned_keys.length; i++) {
			var local_value = cleaned_object[all_cleaned_keys[i]];

			if (local_value == undefined || local_value == null)
				delete cleaned_object[all_cleaned_keys[i]];
			if (options.remove_falsey) {
				if (!local_value)
					delete cleaned_object[all_cleaned_keys[i]];
			} else if (options.remove_zeroes) {
				if (local_value == 0)
					delete cleaned_object[all_cleaned_keys[i]];
			}

			//Recursively call function
			if (typeof local_value == "object")
				cleaned_object[all_cleaned_keys[i]] = cleanObject(local_value, options);
		}

		//Return statement
		return cleaned_object;
	}

	/**
	 * dumbFlattenObject() - Non-recursively flattens a given input object, removing only 1 layer at a time.
	 * @param {Object} arg0_object - The input object to process.
	 *
	 * @rreturns {Object}
	 */
	function dumbFlattenObject (arg0_object) {
		//Convert from parameters
		var object = arg0_object;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var new_obj = {};

		//Iterate over all_object_keys and move their subobject values to new_obj
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (!Array.isArray(local_value) && typeof local_value == "object") {
				var all_subkeys = Object.keys(local_value);

				//Iterate over all all_subkeys
				for (var x = 0; x < all_subkeys.length; x++)
					new_obj[all_subkeys[x]] = local_value[all_subkeys[x]];
			} else {
				new_obj[all_object_keys[i]] = local_value;
			}
		}

		//Return statement
		return new_obj;
	}

	/**
	 * dumbMergeObjects() - Merges two objects non-recursively. Overrides all previous values.
	 * @param {Object} arg0_object - The object to merge into.
	 * @param {Object} arg1_object - The object that takes precedence.
	 *
	 * @returns {Object}
	 */
	function dumbMergeObjects (arg0_object, arg1_object) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;

		//Declare local instance variables
		var all_ot_object_keys = Object.keys(ot_object);

		//Iterate over all_ot_object_keys
		for (var i = 0; i < all_ot_object_keys.length; i++) {
			var local_value = ot_object[all_ot_object_keys[i]];

			object[all_ot_object_keys[i]] = local_value;
		}

		//Return statement
		return object;
	}

	/**
	 * equateObject() - Equates two objects over an interval.
	 * @param {Object} arg0_object - The 1st object to equate.
	 * @param {string} arg1_key - The key of the 1st object to equate.
	 * @param {Object} arg2_object - The 2nd object to equate.
	 * @param {string} arg3_key - The key of the 2nd object to equate.
	 * @param {number} [arg4_interval=100] - The interval over which to ensure that both objects are equal
	 */
	function equateObject (arg0_object, arg1_key, arg2_object, arg3_key, arg4_interval) {
		//Convert from parameters
		var object = arg0_object;
		var key = arg1_key;
		var ot_object = arg2_object;
		var ot_key = arg3_key;
		var interval = (arg4_interval) ? arg4_interval : 100;

		//Return statement; set interval
		return setInterval(function(object, key, ot_object, ot_key){
			object[key] = ot_object[ot_key];
		}, interval, object, key, ot_object, ot_key, interval);
	}

	/**
	 * flattenObject() - Moves all keys into the 1st nesting layer.
	 * @param {Object} arg0_object - The object to pass.
	 *
	 * @returns {Object}
	 */
	function flattenObject (arg0_object) {
		//Convert from parameters
		var object = arg0_object;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Iterate over all_object_keys to move keys into current object
		for (var i = 0; i < all_object_keys.length; i++) {
			var flattened_subobj = {};
			var local_subobj = object[all_object_keys[i]];

			if (typeof local_subobj == "object") {
				flattened_subobj = flattenObject(local_subobj);

				var all_flattened_keys = Object.keys(flattened_subobj);

				for (var x = 0; x < all_flattened_keys.length; x++)
					if (!object[all_flattened_keys[x]]) {
						object[all_flattened_keys[x]] = flattened_subobj[all_flattened_keys[x]];
					} else {
						object[all_flattened_keys[x]] += flattened_subobj[all_flattened_keys[x]];
					}
			} else if (typeof local_subobj == "number") {
				if (!object[all_object_keys[i]])
					object[all_object_keys[i]] = local_subobj;
				//Do not implement an else object here because that would add 1n per depth
			} else {
				object[all_object_keys[i]] = local_subobj;
			}
		}

		//Delete any remanent typeof object in the current object
		all_object_keys = Object.keys(object);

		for (var i = 0; i < all_object_keys.length; i++)
			if (typeof object[all_object_keys[i]] == "object")
				delete object[all_object_keys[i]];

		//Return statement
		return object;
	}

	/**
	 * getDepth() - Returns the maximal object depth as a number.
	 * @param {Object} arg0_object - The object to fetch depth for.
	 * @param {number} [arg1_depth=1] - Optimisation parameter used as an internal helper.
	 *
	 * @returns {number}
	 */
	function getDepth (arg0_object, arg1_depth) {
		//Convert from parameters
		var object = arg0_object;
		var depth = (arg1_depth) ? arg1_depth : 1;

		//Iterate over object
		for (var key in object) {
			if (!object.hasOwnProperty(key)) continue;

			if (typeof object[key] == "object") {
				var level = getDepth(object[key]) + 1;
				depth = Math.max(depth, level);
			}
		}

		//Return statement
		return depth;
	}

	/**
	 * getObjectKey() - Fetches an object value from a string (e.g. 'test.one.two').
	 * @param {Object} arg0_object - The object to fetch the key from.
	 * @param {string} arg1_key - The string of the key to fetch from the object.
	 *
	 * @returns {*}
	 */
	function getObjectKey (arg0_object, arg1_key) {
		//Convert from parameters
		var object = arg0_object;
		var key = arg1_key;

		//Declare local instance variables
		var split_key = (Array.isArray(key)) ? key : key.split(".");
		var return_value;

		if (split_key.length <= 1 && object[split_key[0]]) {
			return_value = object[split_key[0]];
		} else {
			if (object[split_key[0]]) {
				//Preserve old index; pop from front before calling recursion
				var old_index = JSON.parse(JSON.stringify(split_key[0]));
				split_key.shift();
				var found_return_value = getObjectKey(object[old_index], split_key);

				//If value was found, return that
				if (found_return_value)
					return_value = found_return_value;
			}
		}

		//Return statement
		return return_value;
	}

	/**
	 * getObjectList() - Converts an Object to a List.
	 * @param {Object} arg0_object_list - The objectified list to pass.
	 *
	 * @returns {Array}
	 */
	function getObjectList (arg0_object_list) {
		//Convert from parameters
		var list_obj = arg0_object_list;

		//Declare local instance variables
		if (list_obj) {
			var all_list_keys = Object.keys(list_obj);
			var object_array = [];

			//Append everything in object as object_array
			for (var i = 0; i < all_list_keys.length; i++)
				object_array.push(list_obj[all_list_keys[i]]);

			//Return statement
			return object_array;
		} else {
			return [];
		}
	}

	/**
	 * getSubobject() - Fetches a subobject.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {string} arg1_key - The key to recursively look for to fetch the local subobject.
	 * @param {boolean} [arg2_restrict_search=false] - Whether to restrict the search to the 1st layer.
	 *
	 * @returns {boolean}
	 */
	function getSubobject (arg0_object, arg1_key, arg2_restrict_search) {
		//Convert from parameters
		var object = arg0_object;
		var key = arg1_key;
		var restrict_search = arg2_restrict_search;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Process key
		if (!Array.isArray(key))
			key = getList(key.split("."));

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_subobj = object[all_object_keys[i]];

			if (all_object_keys[i] == key[key.length - 1]) {
				//Guard clause
				return local_subobj;
				break;
			} else if (typeof local_subobj == "object") {
				var explore_object = false;
				var new_key = JSON.parse(JSON.stringify(key));
				if (key.length > 1)
					restrict_search = true;

				if (restrict_search && all_object_keys[i] == key[0]) {
					new_key.splice(0, 1);
					explore_object = true;
				}
				if (!restrict_search) explore_object = true;

				//Restrict search for certain arguments
				if (explore_object) {
					var has_subobj = getSubobject(local_subobj, new_key, restrict_search);

					if (has_subobj) {
						//Return statement
						return has_subobj;
						break;
					}
				}
			}
		}
	}

	/**
	 * getSubobjectKeys() - Fetches the keys in a subobject that matches the given criteria.
	 * @param {Object} arg0_object - The object to pass to the function.
	 * @param {Object} [arg1_options]
	 * @param {Array.<string>} [arg1_options.exclude_keys] - A list of keys to exclude.
	 * @param {boolean} [arg1_options.include_objects=false] - Whether to include object keys.
	 * @param {boolean} [arg1_options.only_objects=false] - Whether to only include objects.
	 *
	 * @returns {Array.<string>}
	 */
	function getSubobjectKeys (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.exclude_keys) options.exclude_keys = [];

		//Declare local instance variables
		var all_keys = [];
		var all_object_keys = Object.keys(object);

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_subobj = object[all_object_keys[i]];

			if (typeof local_subobj == "object") {
				//Push key itself first
				if (!options.exclude_keys.includes(all_object_keys[i]))
					all_keys.push(all_object_keys[i]);

				var all_subkeys = getSubobjectKeys(local_subobj, options);

				if (options.include_objects || options.only_objects)
					if (!options.exclude_keys.includes(all_object_keys[i]))
						all_keys.push(all_object_keys[i]);

				for (var x = 0; x < all_subkeys.length; x++)
					if (!options.exclude_keys.includes(all_subkeys[x]))
						all_keys.push(all_subkeys[x]);
			} else {
				if (!options.only_objects)
					if (!options.exclude_keys.includes(all_object_keys[i]))
						all_keys.push(all_object_keys[i]);
			}
		}

		//Return statement
		return all_keys;
	}

	/**
	 * mergeObjects() - Merges two objects together.
	 * @param {Object} arg0_object - The 1st object to merge into.
	 * @param {Object} arg1_object - The 2nd object to concatenate/add.
	 * @param {Object} [arg2_options]
	 * @param {Boolean} [arg2_options.must_have_difference=false] - Whether values must be different before they can be added/subtracted from one another.
	 * @param {Boolean} [arg2_options.overwrite=false] - Whether to overwrite objects when merging.
	 * @param {Boolean} [arg2_options.recursive=false] - Whether merging is recursive.
	 *
	 * @returns {Object}
	 */
	function mergeObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters - merge_obj overwrites onto merged_obj
		var merged_obj = JSON.parse(JSON.stringify(arg0_object));
		var merge_obj = JSON.parse(JSON.stringify(arg1_object));
		var options = (arg2_options) ? arg2_options : {};

		//Initialise options
		if (options.recursive == undefined) options.recursive = true;

		//Declare local instance variables
		var all_merge_keys = Object.keys(merge_obj);

		//Iterate over all_merge_keys
		for (var i = 0; i < all_merge_keys.length; i++) {
			var current_value = merged_obj[all_merge_keys[i]];
			var local_value = merge_obj[all_merge_keys[i]];

			if (typeof local_value == "number") {
				if (merged_obj[all_merge_keys[i]]) {
					//Check if variable should be overwritten
					var to_overwrite = (options.overwrite || (options.must_have_difference && current_value == local_value));

					merged_obj[all_merge_keys[i]] = (!to_overwrite) ?
						merged_obj[all_merge_keys[i]] + local_value :
						local_value; //Add numbers together
				} else {
					merged_obj[all_merge_keys[i]] = local_value;
				}
			} else if (typeof local_value == "object" && current_value && local_value) {
				if (options.recursive)
					merged_obj[all_merge_keys[i]] = mergeObjects(current_value, local_value, options); //Recursively merge objects if possible
			} else {
				merged_obj[all_merge_keys[i]] = local_value;
			}
		}

		//Return statement
		return merged_obj;
	}

	/**
	 * replaceKeys() - Replaces keys in an object with alternative keys. Used for casting between object types.
	 * @param {Object} arg0_object - The original object to change.
	 * @param {Object.<string, string>} [arg1_options]
	 * @param {string} [arg1_options."(original_key)"] - Replaces (original_key) with its equivalent string.
	 *
	 * @returns {Object}
	 */
	function replaceKeys (arg0_object, arg1_options) {
		//Convert from parameters
		var object = (arg0_object) ? arg0_object : {};
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Iterate over all_option_keys
		for (var i = 0; i < all_object_keys.length; i++)
			//Replace key if parallel found in options
			if (options[all_object_keys[i]]) {
				var replacement_key = options[all_object_keys[i]];

				object[replacement_key] = object[all_object_keys[i]];
				delete object[all_object_keys[i]];
			}

		//Return statement
		return object;
	}

	/**
	 * removeZeroes() - Removes all zero value Numbers in an Object.
	 * @param arg0_object {Object} - The object to pass to the function.
	 *
	 * @returns {Object}
	 */
	function removeZeroes (arg0_object) {
		//Convert from parameters
		var object = JSON.parse(JSON.stringify(arg0_object));

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_subobj = object[all_object_keys[i]];

			if (typeof local_subobj == "number")
				if (local_subobj == 0)
					delete object[all_object_keys[i]];
			if (typeof local_subobj == "object")
				object[all_object_keys[i]] = removeZeroes(local_subobj);
		}

		//Return statement
		return object;
	}

	/**
	 * sortObject() - Sorts an object based on its values.
	 * @param arg0_object {Object} - The object to sort.
	 * @param arg1_options {Object}
	 *  @param {string} [arg1_options.type="descending"] - Either 'ascending' or 'descending'.
	 */
	function sortObject (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var entries = [];
		var mode = (options.type) ? options.type : "descending";

		//Iterate over all_object_keys; convert object to array of [key, value] pairs.
		for (var i = 0; i < all_object_keys.length; i++)
			entries.push([all_object_keys[i], object[all_object_keys[i]]]);

		//Sort entries based on value
		entries.sort(function (a, b) {
			var value_a = a[1];
			var value_b = b[1];

			//If value_a or value_b are of type Array, sum them to fetch value to sort by
			if (Array.isArray(value_a)) value_a = getSum(value_a);
			if (Array.isArray(value_b)) value_b = getSum(value_b);

			//Sub-return statement
			return (mode == "descending") ? value_b - value_a : value_a - value_b;
		});

		//Convert back to object
		var sorted_obj = {};

		//Iterate over entries
		for (var i = 0; i < entries.length; i++)
			sorted_obj[entries[i][0]] = entries[i][1];

		//Convert array back to object
		return sorted_obj;
	}

	/**
	 * sortObjectByKey() - Sorts an object by an immediate key.
	 * @param {Object} arg0_object - The object to sort.
	 * @param {Object} [arg1_options] - Options.
	 * @param {string} [arg1_options.key] - The sub-key to sort by. Non-recursive.
	 * @param {string} [arg1_options.type="descending"] - Either 'ascending' or 'descending'.
	 *
	 * @returns {Object}
	 */
	function sortObjectByKey (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.mode) options.mode = "descending";
		if (!options.key) throw new Error("options.key must be defined.");

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var entries = [];

		//Iterate over all_object_keys; convert object to array of objects
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_subobj = object[all_object_keys[i]];
			local_subobj.RESERVED_KEY = all_object_keys[i]; //Add temporary key for tracking.
			entries.push(local_subobj);
		}

		//Sort entries based on the provided key
		entries.sort(function (a, b) {
			var value_a = a[options.key];
			var value_b = b[options.key];

			//Sub-return statement
			return (options.mode == "descending") ? value_b - value_a : value_a - value_b;
		});

		//Convert sorted entries back to object
		var sorted_obj = {};

		//Iterate over entries
		for (var i = 0; i < entries.length; i++) {
			var local_key = entries[i].RESERVED_KEY;
			sorted_obj[local_key] = entries[i];
			delete entries[i].RESERVED_KEY;
			delete sorted_obj[local_key].RESERVED_KEY;
		}

		//Return statement
		return sorted_obj;
	}
}