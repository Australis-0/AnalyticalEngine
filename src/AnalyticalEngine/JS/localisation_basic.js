//Initialise functions
{
	function parseLocalisation (arg0_string, arg1_options) {
		//Convert from parameters
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Iterate over all scopes if they exist
		if (options.scopes) {
			var all_scopes = Object.keys(options.scopes);

			for (var i = 0; i < all_scopes.length; i++) {
				var local_regex = new RegExp("{" + all_scopes[i] + "}", "gm");
				var local_value = options.scopes[all_scopes[i]];

				if (!options.is_html) {
					string = string.replace(local_regex, local_value);
				} else {
					string = string.replace(local_regex, "<span data-key = \"" + all_scopes[i] + "\">" + local_value + "</span>");
				}
			}
		}

		//Return statement
		return string;
	}

	function parseMilliseconds (arg0_millisecondds) {
		//Convert from parameters
		var duration = arg0_millisecondds;

		//Declare local instance variables
		var milliseconds = parseInt((duration % 1000)/100),
			seconds = Math.floor((duration/1000) % 60),
			minutes = Math.floor((duration/(1000*60)) % 60),
			hours = Math.floor((duration/(1000*60*60)) % 24);

		//Return statement
		return ((hours > 0) ? parseNumber(hours) + " hours" : "") + ((minutes > 0) ? ((hours > 0) ? ", " : "") + parseNumber(minutes) + " minutes" : "") + ((seconds > 0) ? ((minutes > 0) ? ", " : "") + parseNumber(seconds) + " seconds" : "");
	}

	function parseNumber (arg0_number, arg1_options) {
		//Convert from parameters
		var number = arg0_number;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var display_prefix = (options.display_prefix) ? ((number > 0) ? "+" : "") : "";

		//Declare local instance functions
		function internalHelperFormatNumber (arg0_number) {
			//Convert from parameters
			var parts = arg0_number.toString().split(".");

			//Return statement
			parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ".");
			return parts.join(",");
		}

		//Return statement
		return display_prefix + formatNumber(
			(typeof number == "number") ?
				(options.display_float) ?
					parseInt(number*100*100)/100/100 :
					parseInt(number) :
				parseInt(number)
		);
	}

	function parseString (arg0_string) {
		//Convert from parameters
		var processed_string = arg0_string;

		//Return statement
		return processed_string.split("_").join(" ")
			.replace(/(^| )(\w)/g, function (string) {
				return string.toUpperCase();
			});
	}

	function printPercentage (arg0_number, arg1_options) {
		//Convert from parameters
		var number = arg0_number;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (options.base_one) number--;

		//Declare local instance variables
		var number_string = (!options.display_float) ?
			Math.round(number*100).toString() :
			(Math.round(number*100*100)/100).toString();

		//Return statement
		return ((options.display_prefix) ?
			((number > 1 && !options.base_zero) || (number > 0 && options.base_zero) ? "+" : "")
		: "") + number_string + "%";
	}

	function printRange (arg0_array) {
		//Convert from parameters
		var array = arg0_array;

		//Return statement
		return (array[0] == array[1]) ?
			parseNumber(array[0]) :
			parseNumber(Math.min(array[0], array[1])) + " - " + parseNumber(Math.max(array[0], array[1]));
	}
}