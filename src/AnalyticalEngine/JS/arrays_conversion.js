//Initialise functions
{
	/**
	 * arrayToObject() - Casts an array to object.
	 * @param {Array} arg0_input_array - The array to input.
	 *
	 * @returns {Object}
	 */
	function arrayToObject (arg0_input_array) {
		//Convert from parameters
		var input_array = arg0_input_array;

		//Declare local instance variables
		var return_obj = {};

		//Iterate over input_array
		for (var i = 0; i < input_array.length; i++)
			return_obj[i] = input_array[i];

		//Return statement
		return return_obj;
	}

	/**
	 * objectToArray() - Casts an object to array.
	 * @param {Object} arg0_input_object - The object to input.
	 *
	 * @returns {Array}
	 */
	function objectToArray (arg0_input_object) {
		//Convert from parameters
		var input_obj = arg0_input_object;

		//Declare local instance variables
		var all_object_keys = Object.keys(input_obj);
		var return_array = [];

		//Iterate over input_obj
		for (var i = 0; i < all_object_keys.length; i++)
			return_array.push(input_obj[all_object_keys[i]]);

		//Return statement
		return return_array;
	}
}