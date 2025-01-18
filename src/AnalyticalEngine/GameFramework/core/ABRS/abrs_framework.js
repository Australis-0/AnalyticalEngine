//Import classes
{
	this.BufferedReader = Java.type("java.io.BufferedReader");
	this.File = Java.type("java.io.File");
	this.FileReader = Java.type("java.io.FileReader");
	this.FileWriter = Java.type("java.io.FileWriter");
	this.PrintWriter = Java.type("java.io.PrintWriter");
}

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

		//1. Lack of double inverted commas fix
		json_string = json_string.replace(/([{,])\s*([\w\-]+)\s*:/g, '$1"$2":');
		//2. Trailing comma fix
		json_string = json_string.replace(/,\s*(?=[}\]])/g, '');

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

		var fixed_json = fixInvalidJSON(json_string);

		//Return statement
		try {
			return JSON.parse(fixed_json);
		} catch (e) {
			console.error("Error reading the file: " + e.message);
		}
	}

	/**
	 * writeObjectToFile() - Writes a given object to a relative/absolute file path.
	 * @param {Object} arg0_object
	 * @param {String} arg1_file_path
	 */
	function writeObjectToFile (arg0_object, arg1_file_path) {
		//Convert from parameters
		var object = arg0_object;
		var file_path = arg1_file_path;

		//Declare local instance variables
		var json_string = JSON.stringify(object, null, 4)
			.replace(/"(\w+)":/g, '$1:');

		//Create a FileWriter and PrintWriter to write to a file
		var writer = new PrintWriter(new FileWriter(file_path));
		writer.println(json_string);
		writer.close();
	}
}