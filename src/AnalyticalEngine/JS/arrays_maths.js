//Initialise functions
{
	/**
	 * getSum() - Fetches the sum of a List.
	 * @param arg0_array {Array} - The array to fetch the sum of.
	 *
	 * @returns {number}
	 */
	function getSum (arg0_array) {
		//Convert from parameters
		var array = arg0_array;

		//Declare local instance variables
		var sum = 0;

		for (var i = 0; i < array.length; i++)
			if (!isNaN(array[i]))
				sum += parseFloat(array[i]);

		//Return statement
		return sum;
	}
}