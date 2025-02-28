//Initialise functions
{
	function getAllFilesInFolder (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var folder = new File(file_path);

		//Return statement
		return (folder.isDirectory()) ?
			Java.from(folder.list()) : undefined;
	}

	/**
	 * fixInvalidJSON() - Internal helper function. Fixes AOC3's default invalid JSON as it would be loaded in, i.e. JSON without quotes.
	 * @param {String} arg0_json_string
	 *
	 * @returns {String}
	 */
	function fixInvalidJSON (arg0_json_string) {
		//Convert from parameters
		var json_string = arg0_json_string;

		//1. Fix missing quotes aroundd object keys
		var key_pattern = Pattern.compile("(?m)(\\s*)([A-Za-z_][A-Za-z_0-9]*)\\s*:");
		json_string = key_pattern.matcher(json_string).replaceAll("$1\"$2\":");

		//2. Fix unquoted string values (optional, if necessary)
		var value_pattern = Pattern.compile(":\\s*([A-Za-z_][A-Za-z_0-9]*)(?=,|\\s*\\})");
		json_string = value_pattern.matcher(json_string).replaceAll(": \"$1\"");

		//3. Fix trailing commas
		var trailing_comma_pattern = Pattern.compile(",(\\s*[}\\]])");
		json_string = trailing_comma_pattern.matcher(json_string).replaceAll("$1");

		//Return statement
		return json_string;
	}

	function isFile (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Return statement
		return (!isFolder(file_path));
	}

	function isFolder (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var folder = new File(file_path);

		//Return statement
		return (folder.isDirectory());
	}

	/**
	 * loadFileAsObject() - Loads a relative/absolute file path as a given object.
	 * @param {String} arg0_file_path
	 *
	 * @returns {Object}
	 */
	function loadFileAsObject (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var json_string = "";
		var line;

		//Create a FileReader to read the file line by line
		var reader = new BufferedReader(new FileReader(file_path));

		//Iterate over all lines to load them into json_string
		try {
			var reader = new BufferedReader(new FileReader(file_path));
			while ((line = reader.readLine()) != null)
				json_string += line;
			reader.close();
		} catch (e) {
			console.error("Error reading the file: " + e.message);
		}

		try {
			var parsed_json = JSON.parse(json_string);

			//Return statement
			return parsed_json;
		} catch (e) {
			var fixed_json = fixInvalidJSON(json_string);

			//Return statement
			try {
				return JSON.parse(fixed_json);
			} catch (e) {
				console.error("Error reading the file: " + e.message);
			}
		}
	}

	/**
	 * writeObjectToFile() - Writes a given object to a relative/absolute file path.
	 * @param {Object} arg0_object
	 * @param {String} arg1_file_path
	 * @param {Object} [arg2_options]
	 *  @param {boolean} [arg2_options.return_string=false] - Whether to return the string instead of immediately writing to file.
	 *
	 * @return {String|undefined}
	 */
	function writeObjectToFile (arg0_object, arg1_file_path, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var file_path = arg1_file_path;
		var options = (arg2_options) ? arg2_options : {};

		try {
			//Declare local instance variables
			var json_string = JSON.stringify(object, null, 4)
			.replace(/"(\w+)":/g, '$1:');

			//Guard clause if options.return_string is enabled
			if (options.return_string)
				return json_string;

			//Create a FileWriter and PrintWriter to write to a file
			var writer = new PrintWriter(new FileWriter(file_path));
			writer.println(json_string);
			writer.close();
		} catch (e) {
			console.error(e.stack);
		}
	}
}