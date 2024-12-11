//Import classes
{
	this.BufferedReader = Java.type("java.io.BufferedReader");
	this.BufferedWriter = Java.type("java.io.BufferedWriter");
	this.File = Java.type("java.io.File");
	this.FileReader = Java.type("java.io.FileReader");
	this.FileWriter = Java.type("java.io.FileWriter");
	this.StringBuilder = Java.type("java.lang.StringBuilder");
}

//Initialise functions
{
	/**
	 * fileExists() - Checks whether a file path exists.
	 * @param {String} arg0_file_path - The file path to check.
	 *
	 * @returns {boolean}
	 */
	function fileExists (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var file = new File(file_path);

		//Return statement
		return file.exists();
	}

	/**
	 * readASCFile() - Reads an ASC file into a 2D dataframe; with NODATA_values replaced by undefined.
	 * @param {String} arg0_file_path - The input file path to convert to an array.
	 *
	 * @returns {Array<Array<number>>}
	 */
	function readASCFile(arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Guard clause to ensure that file_path exists
		if (!fileExists(file_path)) {
			console.error("readASCFile() - Invalid input file path: " + file_path);
			return [[undefined]];
		}

		//Declare local instance variables
		var checks_passed = 0;
		var data_columns = 0;
		var data_frame = [];
		var data_rows = 0;
		var is_header = true;
		var no_data_value = undefined;
		var raw_data = readFile(file_path).split("\n");
		var row_index = 0;

		//Iterate over all raw_data lines
		for (var i = 0; i < raw_data.length; i++) {
			var line = raw_data[i].trim();

			//Internal guard clause; skip empty lines
			if (line === "") continue;

			//Header parsing
			if (is_header) {
				if (checks_passed < 3) {
					if (line.startsWith("ncols")) {
						data_columns = parseInt(line.split(" ")[1]);
						checks_passed++;
					} else if (line.startsWith("nrows")) {
						data_rows = parseInt(line.split(" ")[1]);
						checks_passed++;
					} else if (line.startsWith("NODATA_value")) {
						no_data_value = line.split(/\s+/)[1]; //Recognise it as a string to avoid floating-point issues
						checks_passed++;
					}
				} else {
					//End of header; initialize data_frame
					is_header = false;
					data_frame = new Array(data_rows);

					//Fill the frame with empty arrays for direct indexing
					for (var x = 0; x < data_rows; x++)
						data_frame[x] = new Array(data_columns);
				}
				continue;
			}

			//Iterate over all data_columns; data parsing
			var values = line.split(" ");

			for (var x = 0; x < data_columns; x++) {
				data_frame[row_index][x] = (values[x] == no_data_value) ? undefined : parseFloat(values[x]);
			}

			row_index++;
			if (row_index >= data_rows) break; //Stop processing once all rows are read
		}

		//Return statement
		return data_frame;
	}

	/**
	 * readFile() - Reads a file and returns it in string form, with newlines denoted by \n.
	 * @param {String} arg0_file_path - The input file to read out.
	 *
	 * @returns {String}
	 */
	function readFile (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var file = new File(file_path);
		var raw_data = new StringBuilder();
		var reader = new BufferedReader(new FileReader(file));

		var line;

		//Iterate over all lines in file
		while ((line = reader.readLine()) != null)
			raw_data.append(line).append("\n");
		reader.close();

		//Return statement
		return raw_data.toString();
	}

	/**
	 * writeFile() - Writes text to a given file.
	 * @param {String} arg0_file_path - The output file path.
	 * @param {String} arg1_string - The string to write to file.
	 *
	 * @returns {String}
	 */
	function writeFile (arg0_file_path, arg1_string) {
		//Convert from parameters
		var file_path = arg0_file_path;
		var string = arg1_string;

		//Declare local instance variables
		var file = new File(file_path);
		var parent_directory = file.getParentFile();

		//Make sure parent_directory exists before proceeding
		if (parent_directory != null && !parent_directory.exists())
			if (!parent_directory.mkdirs()) {
				console.error("writeFile() - Failed to create necessary directories at: " + parent_directory.getAbsolutePath() + ". Returning empty string.");
				return "";
			}

		var writer = new BufferedWriter(new FileWriter(file));

		//Write content to file
		writer.write(string);
		writer.close();

		//Return statement
		return string;
	}
}