//Import classes
{
	this.BufferedReader = Java.type("java.io.BufferedReader");
	this.FileReader = Java.type("java.io.FileReader");
	this.FileWriter = Java.type("java.io.FileWriter");
	this.PrintWriter = Java.type("java.io.PrintWriter");
}

//Initialise functions
{
	function loadFileAsObject (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var json_string = "";
		var line;

		//Create a FileReader to read the file line by line
		var reader = new BufferedReader(new FileReader(file_path));

		//Iterate over all lines to load them into json_string
		while ((line = reader.readLine()) != null)
			json_string += line;
		reader.close();

		//Return statement
		return JSON.parse(json_string);
	}

	function writeObjectToFile (arg0_object, arg1_file_path) {
		//Convert from parameters
		var object = arg0_object;
		var file_path = arg1_file_path;

		//Declare local instance variables
		var json_string = JSON.stringify(object);

		//Create a FileWriter and PrintWriter to write to a file
		var writer = new PrintWriter(new FileWriter(json_string));
		writer.println(json_string);
		writer.close();
	}
}