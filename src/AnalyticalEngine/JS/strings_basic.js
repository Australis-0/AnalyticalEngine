//Initialise function
{
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

	function equalsIgnoreCase (arg0_string, arg1_string) {
		//Convert from parameters
		var string = arg0_string.toString();
		var ot_string = arg1_string.toString();

		//Return statement
		return (string.toLowerCase().trim() == ot_string.toLowerCase().trim());
	}

	function formaliseString (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return input_string.replace(/_/g, " ")
			.replace(/(^\w{1})|(\s{1}\w{1})/g, function (match) { // Use standard function instead of arrow function
				return match.toUpperCase();
			});
	}

	function getDateFromString (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return Date.parse(input_string);
	}

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

	function isImage (arg0_input_string) {
		//Convert from parameters
		var input_string = arg0_input_string;

		//Return statement
		return /^https?:\/\/.+\.(jpg|jpeg|png|webp|avif|gif|svg)$/.test(input_string);
	}

	function parseBoolean (arg0_input_boolean) {
		//Convert from parameters
		var input_boolean = arg0_input_boolean;

		//Return statement
		return (input_boolean) ? "Yes" : "No";
	}

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