//Initialise functions
{
	/**
	 * printAlert() - Prints a regular alert given a string.
	 * @param {String} arg0_string
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.display_prefix=false] - Optional. Whether to display the default [AnalyticalEngine] prefix. False by default.
	 */
	function printAlert (arg0_string, arg1_options) {
		//Convert from parameters
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var prefix_string = (options.display_prefix) ? "[AnalyticalEngine] " : "";

		//Print alert
		Game.menuManager.addToast(prefix_string + string);
	}

	function printEditorAlert (arg0_string, arg1_options) {
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Set printAlert; console.log() string
		options.display_prefix = true;
		printAlert(string, options);
		console.log("[Editor] " + string);
	}
}