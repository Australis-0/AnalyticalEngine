//Extend String type
{
	String.prototype.includes = function (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Return statement
		return (this.indexOf(string) != -1);
	}
}

//Initialise functions
{
	/**
	 * capitaliseWords() - Capitalise all the words in a string.
	 * @param {String} arg0_input_string - The string to pass to the function.
	 *
	 * @returns {String}
	 */
	function capitaliseWords (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Declare local instance variables
		var separate_words = input_string.split(" ");

		//Iterate over separate_words to capitalise them
		for (var i = 0; i < separate_words.length; i++) {
			separate_words[i] = separate_words[i].charAt(0).toUpperCase();
			separate_words[i].substring(1);
		}

		//Return statement
		return separate_words.join(" ");
	}

	/**
	 * cleanStringify() - Cleans an input object to be compatible with JSON.stringify().
	 * @param {String} arg0_input_object - The object to pass to the function.
	 *
	 * @returns {Object}
	 */
	function cleanStringify (arg0_input_object) {
		//Convert from parameters
		var input_object = arg0_input_object;

		//Declare local instance functions
		function internalHelperCopyWithoutCircularReferences (arg0_references, arg1_object) {
			//Convert from parameters
			var references = arg0_references;
			var object = arg1_object;

			//Declare local instance variables
			var clean_object = {};

			Object.keys(object).forEach(function (key) {
				var value = object[key];

				if (value && typeof value === 'object') {
					if (references.indexOf(value) < 0) {
						references.push(value);
						clean_object[key] = copyWithoutCircularReferences(references, value);
						references.pop();
					} else {
						clean_object[key] = '###_Circular_###';
					}
				} else if (typeof value !== 'function') {
					clean_object[key] = value;
				}
			});

			//Return statement; internal helper function
			return clean_object;
		}

		//Copy without circular references
		if (input_object && typeof input_object == "object")
			input_object = copyWithoutCircularReferences([input_object], input_object);

		//Return statement
		return JSON.stringify(input_object);
	}

	/**
	 * equalsIgnoreCase() - Compares two strings, ignoring their case. Returns a boolean.
	 * @param {String} arg0_string - The 1st string to compare.
	 * @param {String} arg1_string - The 2nd string to compare.
	 *
	 * @returns {boolean}
	 */
	function equalsIgnoreCase (arg0_string, arg1_string) {
		//Convert from parameters
		var string = arg0_string.toString();
		var ot_string = arg1_string.toString();

		//Return statement
		return (string.toLowerCase().trim() == ot_string.toLowerCase().trim());
	}

	/**
	 * formaliseString() - Formalises a debug string into human-readable text.
	 * @param {String} arg0_input_string - The string to pass to the function.
	 *
	 * @returns {String}
	 */
	function formaliseString (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return input_string.replace(/_/g, " ")
			.replace(/(^\w{1})|(\s{1}\w{1})/g, function (match) { // Use standard function instead of arrow function
				return match.toUpperCase();
			});
	}

	/**
	 * getDateFromString() - Fetches the date from an input string.
	 * @param {String} arg0_input_string - The string to pass to the function.
	 *
	 * @returns {Date}
	 */
	function getDateFromString (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return Date.parse(input_string);
	}

	/**
	 * getNesting() - Fetches the amount of nesting embedded within the current string.
	 * @param {String} arg0_input_string - The string to pass to the function.
	 *
	 * @returns {number}
	 */
	function getNesting (arg0_input_string) {
		//Convert from parameters
		var string = arg0_input_string;

		//Declare local instance variables
		var first_character = "";
		var nesting = 0;
		var spaces_until_first_character = 0;

		//Iterate over current string to count the number of spaces to the next character
		for (var i = 0; i < string.length; i++) {
			if (string[i] == " ") {
				spaces_until_first_character++;
			} else {
				if (first_character == "")
					first_character = string[i];
			}

			//Break once non-space is found
			if (first_character != "") break;
		}

		if (first_character == "-")
			nesting = Math.ceil(spaces_until_first_character/2);

		//Return statement
		return nesting;
	}

	/**
	 * isImage() - Checks if the given link is that of a compatible image.
	 * @param {String} arg0_input_string - The input link to pass to the function.
	 *
	 * @returns {boolean}
	 */
	function isImage (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return /^https?:\/\/.+\.(jpg|jpeg|png|webp|avif|gif|svg)$/.test(input_string);
	}

	/**
	 * padZeroes() - Pads a string with zeroes to be fixed width.
	 * @param {String} [arg0_input_string=""] - The string to pass to the function.
	 * @param {number} [arg1_length=1] - The length to pad the string out to.
	 *
	 * @returns {String}
	 */
	function padZeroes (arg0_input_string, arg1_length) {
		//Convert from parameters
		var input_string = (arg0_input_string) ? arg0_input_string : "";
		var length = (arg1_length) ? arg1_length : 1;

		//Pad zeroes
		for (var i = input_string.length; i < length; i++)
			input_string = "0" + input_string;

		//Return statement
		return input_string;
	}

	/**
	 * parseBoolean() - Returns a human-readable version of a boolean.
	 * @param {boolean} arg0_input_boolean - The boolean to pass to the function.
	 *
	 * @returns {String}
	 */
	function parseBoolean (arg0_input_boolean) {
		//Convert from parameters
		var input_boolean = arg0_input_boolean;

		//Return statement
		return (input_boolean) ? "Yes" : "No";
	}

	/**
	 * parseDate() - Returns a string timestamp of a contemporary date.
	 * @param {String} arg0_timestamp - The Date timestamp to pass to the function.
	 *
	 * @returns {String}
	 */
	function parseDate (arg0_timestamp) {
		//Convert from parameters
		var a = new Date(arg0_timestamp);

		//Declare local instance variables
		var year = a.getFullYear();
		var month = months[a.getMonth()];
		var date = a.getDate();
		var hour = a.getHours() < 10 ? "0" + a.getHours() : a.getHours();
		var min = a.getMinutes() < 10 ? "0" + a.getMinutes() : a.getMinutes();
		var sec = a.getSeconds() < 10 ? "0" + a.getSeconds() : a.getSeconds();

		//Return statement
		return date + " " + month + " " + year + " " + hour + ":" + min + ":" + sec;
	}

	/**
	 * parseList() - Parses a list into human-readable form.
	 * @param {Array<String>} arg0_input_list - The array to pass to the function.
	 *
	 * @returns {String}
	 */
	function parseList (arg0_input_list) {
		//Convert from parameters
		var name_array = getList(arg0_input_list);

		//Declare local instance variables
		var name_string = "";

		//Modify ending
		if (name_array.length > 2) {
			name_array[name_array.length - 1] = "and " + name_array[name_array.length - 1];
			name_string = name_array.join(", ");
		} else if (name_array.length == 2) {
			name_array[name_array.length - 1] = "and " + name_array[name_array.length - 1];
			name_string = name_array.join(" ");
		} else {
			name_string = name_array[0];
		}

		//Return statement
		return name_string;
	}

	/**
	 * parseNonMarkdownNesting() - Returns a string with the correct padding (2-space per nesting).
	 *
	 * @param {number} arg0_nesting - The amount of nesting to convert.
	 *
	 * @returns {String}
	 */
	function parseNonMarkdownNesting (arg0_nesting) {
		//Convert from parameters
		var nesting = parseInt(arg0_nesting);

		//Declare local instance variables
		var nesting_string = "";

		//Populate nesting_string
		for (var i = 0; i < nesting; i++)
			nesting_string += "  ";

		//Return statement
		return nesting_string;
	}

	/**
	 * processOrdinalString() - Fetches the current ordinal present in a numeric string.
	 * @param {String} arg0_input_string - The ordinal string to pass to the function.
	 *
	 * @returns {String}
	 */
	function processOrdinalString (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Declare local instance variables
		var current_string = input_string.toString().trim();
		var trim_patterns = [
			[/  /gm, " "],
			[" . ", ". "],
			[/^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}) [a-z]*/gm]
		];
		var alphabet = "abcdefghijklmnopqrstuvwxyz";
		for (var i = 0; i < alphabet.split("").length; i++)
			trim_patterns.push([" " + alphabet.split("")[i] + " ", alpahbet.split("")[i] + " "]);

		//Trim out, well, trim patterns
		for (var i = 0; i < trim_patterns.length; i++) {
			if (trim_patterns[i].length > 1) {
				current_string = current_string.replace(trim_patterns[i][0], trim_patterns[i][1]);
			} else {
				var current_roman_array = current_string.match(trim_patterns[i][0]);
				if (current_roman_array != null) {
					current_string = current_string.replace(current_roman_array[0], current_roman_array[0].split(" ").join(" "));
				}
			}
		}

		//Return statement
		return current_string;
	}
}