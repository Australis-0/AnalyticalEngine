//Initialise functions
{
	function stripMarkdown (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Declare local instance variables
		var processed_string = input_string.toString();

		//Return statement
		return processed_string.replace(/(__)|(\*\*)/gm, "");
	}

	function stripNonNumerics (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return input_string.toString().replace(/(__)|(\*\*)/gm, "");
	}

}