//Initialise functions
{
	/**
	 * getList() - Returns a list/array from a variable.
	 * @param arg0_variable {*} - The variable to return a list/array from.
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