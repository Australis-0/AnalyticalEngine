//Extend Array type
{
	if (!Array.prototype.includes)
		Array.prototype.includes = function (arg0_element, arg1_from_index) {
			//Convert from parameters
			var element = arg0_element;
			var from_index = arg1_from_index;

			//Use from_index if provided
			var start = (from_index || 0);
			start = Math.max((start >= 0) ? start : this.length + start, 0);

			for (var i = start; i < this.length; i++)
				if (this[i] == element || Number.isNaN(this[i]) && Number.isNaN(element))
					//Return statement
					return true;
			//Return statement
			return false;
		}
}

//Initialise functions
{
	/**
	 * appendArrays() - Appends an array to another.
	 * @param {Array} arg0_array - The 1st array to concatenate.
	 * @param {Array} arg1_array - The 2nd array to concatenate.
	 *
	 * @returns {Array}
	 */
	function appendArrays (arg0_array, arg1_array) {
		//Convert from parameters
		var array = arg0_array;
		var ot_array = arg1_array;

		//Return statement
		return array.concat(array, ot_array);
	}

	/**
	 * createArray() - Creates an array from the following options.
	 * @param {Object} [arg0_options]
	 * @param {Array.<number, number>} [arg0_options.domain] - Creates an integer array between [min, max].
	 * @param {Array.<number, number, number>} [arg0_options.linear_sequence] - Generates a linear sequence from linear_sequence[0] to linear_sequence[1] in steps of linear_sequence[2].
	 * @param {Array.<string, number>} [arg0_options.sequence] - Generates a sequenced array according to a mathematical equation.
	 * @param {string} [arg0_options.sequence.0] - Mathematical equation as a string literal. The current iteration when generating the sequence is referred to as 'n'.
	 * @param {number} [arg0_options.sequence.1] - The total number of iterations to repeat the sequence for.
	 * @param {Array.<Array, number>} [arg0_options.repeat] - Repeats an array x times.
	 * @param {Array.<Array, number>} [arg0_options.repeat_each] - Repeats each element of an array x times.
	 *
	 * @returns {Array}
	 */
	function createArray (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var return_array = [];

		//Process array
		if (options.domain) {
			var domain_range = getRange(options.domain);

			for (var i = domain_range[0]; i <= domain_range[1]; i++)
				return_array.push(i);
		}
		if (options.linear_sequence) {
			var linear_sequence = getRange(options.linear_sequence);
			var step = (options.linear_sequence[2]) ? options.linear_sequence[2] : 1;

			for (var i = linear_sequence[0]; i <= linear_sequence[1]; i+= step)
				return_array.push(i);
		}
		if (options.sequence) {
			var sequence_literal = options.sequence[0];

			for (var i = 0; i < options.sequence[1]; i++) {
				var local_expression = "var n = " + i + "; return " + sequence_literal + ";";
				var local_result = eval("(function(){ " + local_expression + " })();");

				return_array.push(local_result);
			}
		}
		if (options.repeat)
			for (var i = 0; i < options.repeat[1]; i++)
				for (var x = 0; x < options.repeat[0].length; x++)
					return_array.push(options.repeat[0][x]);
		if (options.repeat_each)
			for (var i = 0; i < options.repeat_each[0].length; i++)
				for (var x = 0; x < options.repeat_each[1]; x++)
					return_array.push(options.repeat_each[0][i]);

		//Return statement
		return return_array;
	}

	/**
	 * dimensionality() - Formats an array with n dimensions with zero-indexed dimensionality.
	 * @param {Array} arg0_input_array - The array to input.
	 * @param {Array<number>} arg1_dimension_array - An array providing the dimensions of the current array in nD format (what to break it down into), starting with the Y dimension.
	 *
	 * @returns {Array<Array>}
	 */
	function dimensionality (arg0_input_array, arg1_dimension_array) {
		//Convert from parameters
		var input_array = getList(arg0_input_array);
		var dimension_array = getList(arg1_dimension_array);

		//Deep copy to avoid modifying original array
		input_array = JSON.parse(JSON.stringify(input_array));

		//Guard clause for recursion
		if (dimension_array.length == 0) return input_array;

		//Declare local instance variables
		var current_dimension = dimension_array.shift();
		var return_array = [];

		//Continually format the dimensions of the given array
		while (input_array.length > 0) {
			var sub_array = input_array.splice(0, current_dimension);
			return_array.push(dimensionality(sub_array, dimension_array.slice()));
		}

		//Return statement
		return return_array;
	}

	/**
	 * flattenArray() - Flattens a nested array to be 1-deep.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {Array}
	 */
	function flattenArray (arg0_input_array) {
		//Convert from parameters
		var input_array = arg0_input_array;

		//Declare local instance variables/functions
		function internalHelperFlatten (arg0_array) {
			//Convert from parameters
			var array = [];

			//Declare local instance variables
			var return_array = [];

			//Iterate over array
			for (var i = 0; i < array.length; i++)
				if (Array.isArray(array[i])) {
					result = result.concat(flatten(array[i]));
				} else {
					result.push(array[i]);
				}

			//Return statement
			return result;
		}

		//Return statement
		return internalHelperFlatten(input_array);
	}

	/**
	 * getCardinality() - Fetches the cardinality of an array/object/variable.
	 * @param {*} arg0_variable - The variable to input.
	 *
	 * @returns {number}
	 */
	function getCardinality (arg0_variable) {
		//Convert from parameters
		var input_variable = arg0_variable;

		//Return statement
		if (Array.isArray(input_variable)) return input_variable.length;
		if (typeof input_variable == "object" && input_variable !== null) return Object.keys(input_variable).length;
		return 1;
	}

	/**
	 * getRecursiveCardinality() - Fetches the cardinality of an array recursively.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {number}
	 */
	function getRecursiveCardinality (arg0_input_array) {
		//Convert from parameters
		var input_array = arg0_input_array;

		//Return statement
		return (Array.isArray(input_array)) ?
			flattenArray(input_array).length : 0;
	}

	/**
	 * getList() - Returns a list/array from a variable.
	 * @param {*} arg0_variable - The variable to return a list/array from.
	 *
	 * @returns {Array}
	 */
	function getList (arg0_variable) {
		//Convert from parameters
		var input_variable = arg0_variable;

		//Return statement
		return (Array.isArray(input_variable)) ? input_variable : [input_variable];
	}

	/**
	 * isArrayEmpty() - Checks whether an array is empty.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {boolean}
	 */
	function isArrayEmpty (arg0_input_array) {
		//Convert from parameters
		var input_array = getList(arg0_input_array);

		//Return statement
		return (input_array.length == 0 || input_array.every(function(element) { return (element == undefined); }));
	}

	/**
	 * moveElement() - Moves an element from one index to another.
	 * @param {Array} arg0_input_array - The array to input.
	 * @param {number} [arg1_old_index=0] - The old index of the element.
	 * @param {number} [arg2_new_index=0] - The new index the element should be moved to.
	 *
	 * @returns {Array}
	 */
	function moveElement (arg0_input_array, arg1_old_index, arg2_new_index) {
		//Convert from parameters
		var input_array = getList(arg0_input_array);
		var old_index = returnSafeNumber(arg1_old_index);
		var new_index = returnSafeNumber(arg2_new_index);

		//Move element in array
		if (new_index >= input_array.length) {
			var local_index = new_index - input_array.length + 1;
			while (local_index--) input_array.push(undefined);
		}
		input_array.splice(new_index, 0, input_array.splice(old_index, 1)[0]);

		//Return statement
		return input_array;
	}

	/**
	 * truncateArray() - Truncates an array to a given length.
	 * @param {Array} arg0_input_array - The array to input.
	 * @param {number} [arg1_length=0] - The length to truncate the array to.
	 *
	 * @returns {Array}
	 */
	function truncateArray (arg0_input_array, arg1_length) {
		//Convert from parameters
		var input_array = getList(arg0_input_array);
		var length = returnSafeNumber(arg1_length);

		//Set length
		input_array.length = length;

		//Return statement
		return input_array;
	}

	/**
	 * reverseArray() - Reverses an input array.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {Array}
	 */
	function reverseArray (arg0_input_array) {
		//Convert from parameters
		var input_array = arg0_input_array;

		//Return statement
		return (Array.isArray(input_array)) ?
			JSON.parse(JSON.stringify(input_array)).reverse() : input_array;
	}

	/**
	 * uniqueArray() - Removes any duplicate items from an input array.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {Array}
	 */
	function uniqueArray (arg0_input_array) {
		//Convert from parameters
		var input_array = arg0_input_array;

		//Declare local instance variables
		var unique_array = input_array.filter(function(item, index, array) {
			//Convert sub-arrays to strings for comparison
			if (Array.isArray(item)) {
				item = item.map(function(sub_item) {
					if (typeof sub_item == "object") return JSON.stringify(sub_item);
					return sub_item;
				}).join(",");
				array[index] = item;
			}

			//Local filter return statement
			return (array.indexOf(item) == index);
		});

		//Return statement; convert sub-arrays back to arrays
		return unique_array.map(function(item) {
			if (typeof item == "string")
				return item.split(",").map(function(sub_item) {
					try {
						return JSON.parse(sub_item);
					} catch (e) {
						return sub_item;
					}
				});

			//Local map return statement
			return item;
		});
	}
}

//KEEP AT BOTTOM! Initialise function aliases
{
	getArray = getList;
	unique = uniqueArray;
}