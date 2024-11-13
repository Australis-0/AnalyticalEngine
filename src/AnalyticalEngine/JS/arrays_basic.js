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

	function dimensionality (arg0_input_array, arg1_dimension_array) {

	}

	function flattenArray (arg0_input_array) {

	}

	function getCardinality (arg0_variable) {

	}

	function getRecursiveCardinality (arg0_input_array) {

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
}