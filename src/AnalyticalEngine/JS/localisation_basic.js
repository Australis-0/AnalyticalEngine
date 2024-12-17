//Initialise functions
{
	/**
	 * parseLocalisation() - Parses a localisation into a given string.
	 * @param {String} arg0_string - The string to parse into a given localisation, with {} representing scope variables.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.is_html=false] - Whether the localisation to be parsed is HTML.
	 * @param {Object} [arg1_options.scopes]
	 * @param {*} [arg1_options.scopes."variable_key"] - The value to replace {<variable>} string keys with.
	 *
	 * @returns {String}
	 */
	function parseLocalisation (arg0_string, arg1_options) {
		//Convert from parameters
		var string = arg0_string;
		var options = (arg1_options) ? arg1_options : {};

		//Iterate over all scopes if they exist
		if (options.scopes) {
			var placeholder_regex = /\{([^{}]+)\}/gm;

			string = string.replace(placeholder_regex, function (match, expression) {
				try {
					var scope_keys = [];
					var scope_values = [];

					for (var key in options.scopes)
						if (options.scopes.hasOwnProperty(key)) {
							scope_keys.push(key);
							scope_values.push(options.scopes[key]);
						}

					var local_function = new Function(scope_keys.join(","), "return " + expression + ";");
					var local_result = local_function.apply(null, scope_values);

					//Return result, optionally wrapped in HTML
					if (options.is_html)
						return "<span data-key = '" + expression + "'>" + local_result + "</span>";
					return local_result;
				} catch (e) {
					console.error("Error parsing localisation: ", expression, " ", e);
					return match;
				}
			});
		}

		//Return statement
		return string;
	}

	/**
	 * parseMilliseconds() - Parses milliseconds into a human-readable time uration.
	 * @param {arg0_milliseconds} - The number of milliseconds to parse.
	 *
	 * @returns {String}
	 */
	function parseMilliseconds (arg0_milliseconds) {
		//Convert from parameters
		var duration = arg0_milliseconds;

		//Declare local instance variables
		var milliseconds = parseInt((duration % 1000)/100),
			seconds = Math.floor((duration/1000) % 60),
			minutes = Math.floor((duration/(1000*60)) % 60),
			hours = Math.floor((duration/(1000*60*60)) % 24);

		//Return statement
		return ((hours > 0) ? parseNumber(hours) + " hours" : "") + ((minutes > 0) ? ((hours > 0) ? ", " : "") + parseNumber(minutes) + " minutes" : "") + ((seconds > 0) ? ((minutes > 0) ? ", " : "") + parseNumber(seconds) + " seconds" : "");
	}

	/**
	 * parseNumber() - Formats a number to a string whilst displaying decimal separators (e.g. 1.567,23 instead of 1567.23).
	 * @param {number} arg0_number - The number to parse.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.display_float=false] - Whether to display a number to the hundreths place.
	 * @param {boolean} [arg1_options.display_prefix=false] - Whether to display a starting prefix.
	 *
	 * @returns {String}
	 */
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

	/**
	 * parseString() - Parses a debug string into human-readable text.
	 * @param {String} arg0_string - The string to parse.
	 *
	 * @returns {String}
	 */
	function parseString (arg0_string) {
		//Convert from parameters
		var processed_string = arg0_string;

		//Return statement
		return processed_string.split("_").join(" ")
			.replace(/(^| )(\w)/g, function (string) {
				return string.toUpperCase();
			});
	}

	/**
	 * printPercentage() - Formats a string to fit a certain percentage (e.g. 23%), instead of a default decimal number.
	 * @param {number} - The number to format into a percentage.
	 * @param [arg1_options]
	 * @param {boolean} [arg1_options.base_zero] - Whether to start at a base zero instead of one.
	 * @param {boolean} [arg1_options.display_float] - Whether to display a number to the hundreths place.
	 * @param {boolean} [arg1_options.is_modifier] - Used for parsing negative modifiers.
	 *
	 * @returns {String}
	 */
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

	/**
	 * printRange() - Returns a given range of numbers as a string using [min, max] format.
	 * @param {Array<number, number>} - The range to input.
	 *
	 * @returns {String}
	 */
	function printRange (arg0_array) {
		//Convert from parameters
		var array = arg0_array;

		//Return statement
		return (array[0] == array[1]) ?
			parseNumber(array[0]) :
			parseNumber(Math.min(array[0], array[1])) + " - " + parseNumber(Math.max(array[0], array[1]));
	}
}