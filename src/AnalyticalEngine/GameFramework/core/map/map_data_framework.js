//Initialise functions
{
	/**
	 * getNumberOfProvinces() - Fetches the current number of provinces from a map editor's ProvincePoints.json file.
	 * @param {String} arg0_file_path - The file path to that map editor's ProvincePoints.json
	 *
	 * @returns {number}
	 */
	function getNumberOfProvinces (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Guard clause if file path does not exist
		if (!fileExists(file_path)) {
			console.error("getNumberOfProvinces() - File path does not exist at: " + file_path);
			return 0;
		}

		//Declare local instance variables
		var raw_data = readFile(file_path);

		//Fix raw_data; parse JSON
		raw_data = raw_data.replace(/(\w+):/g, '"$1":')
		.replace(/,(\s*\])/g, '$1')
		.replace(/"Age_of_History":\s*Data,/, '"Age_of_History": "Data",');
		var map_obj = JSON.parse(raw_data);

		//Return statement
		return map_obj.Data.length;
	}

	function loadEconomyNumberData (arg0_provinces_file_path, arg1_numbers_file_path, arg2_max_value) {
		//Convert from parameters
		var provinces_file_path = arg0_provinces_file_path;
		var numbers_file_path = arg1_numbers_file_path;
		var limit_value = returnSafeNumber(arg2_max_value, 1);

		try {
			//Declare local instance variables
			var max_value = 1;
			var provinces_number_obj = loadNumberDataFromImage(provinces_file_path, numbers_file_path);

			//Iterate over all_province_numbers; fetch max_value
			var all_province_numbers = Object.keys(provinces_number_obj);

			for (var i = 0; i < all_province_numbers.length; i++) {
				var local_province_id = all_province_numbers[i];
				var local_value = provinces_number_obj[all_province_numbers[i]];

				max_value = Math.max(max_value, local_value);
			}

			//Iterate over all_province_numbers; set province population growth rate
			for (var i = 0; i < all_province_numbers.length; i++) {
				var local_province_id = all_province_numbers[i];
				var local_value = provinces_number_obj[all_province_numbers[i]];

				//Set population growth rate
				setProvinceEconomy(local_province_id, (local_value/max_value)*limit_value);
			}

			//Return statement
			return provinces_number_obj;
		} catch (e) {
			console.error(e.stack);
		}
	}

	function loadNumberDataFromImage (arg0_provinces_file_path, arg1_numbers_file_path) {
		//Convert from parameters
		var provinces_file_path = arg0_provinces_file_path;
		var numbers_file_path = arg1_numbers_file_path;

		//Guard clause; check to make sure both provinces_file_path and numbers_file_path exist
		if (!fileExists(provinces_file_path)) {
			console.error("loadNumberDataFromImage() - Provinces File doesn't exist at path " + provinces_file_path);
			return undefined;
		}
		if (!fileExists(numbers_file_path)) {
			console.error("loadNumberDataFromImage() - Numbers File doesn't exist at path " + numbers_file_path);
		}

		//Declare local instance variables
		var province_values = {}; //Stores sum_number per province

		//Read numbers
		var numbers_image = ImageIO.read(new File(numbers_file_path));
		var provinces_image = ImageIO.read(new File(provinces_file_path));
		var provinces_image_height = provinces_image.getHeight();
		var provinces_image_width = provinces_image.getWidth();

		//Iterate over provinces_image; assign percentage
		for (var i = 0; i < provinces_image_width; i++)
			for (var x = 0; x < provinces_image_height; x++) {
				var local_number_colour = convertIntToRGBA(numbers_image.getRGB(i, x));
				var local_province_colour = convertIntToRGBA(provinces_image.getRGB(i, x));
				var local_province_id = decodeRGBAsNumber(local_province_colour);
				var local_province_value = decodeRGBAAsNumber(local_number_colour);

				//Assign number
				if (local_province_id > 0)
					modifyValue(province_values, local_province_id, local_province_value);
			}

		//Return statement
		return province_values;
	}

	function loadPercentageDataFromImage (arg0_provinces_file_path, arg1_percentages_file_path, arg2_max_value) {
		//Convert from parameters
		var provinces_file_path = arg0_provinces_file_path;
		var percentages_file_path = arg1_percentages_file_path;
		var max_value = returnSafeNumber(arg2_max_value, 1);

		//Guard clause; check to make sure both provinces_file_path and percentages_file_path exist
		if (!fileExists(provinces_file_path)) {
			console.error("loadPercentageDataFromImage() - Provinces File doesn't exist at path " + provinces_file_path);
			return undefined;
		}
		if (!fileExists(percentages_file_path)) {
			console.error("loadPercentageDataFromImage() - Percentages File doesn't exist at path " + percentages_file_path);
		}

		//Declare local instance variable
		var province_values = {}; //Stores sum_percentage per province

		//Read provinces_file_path and percentages_file_path
		var percentages_image = ImageIO.read(new File(percentages_file_path));
		var provinces_image = ImageIO.read(new File(provinces_file_path));
		var provinces_image_height = provinces_image.getHeight();
		var provinces_image_width = provinces_image.getWidth();

		//Iterate over provinces_image; assign percentage
		for (var i = 0; i < provinces_image_width; i++)
			for (var x = 0; x < provinces_image_height; x++) {
				var local_percentage_colour = convertIntToRGBA(percentages_image.getRGB(i, x));
				var local_province_colour = convertIntToRGBA(provinces_image.getRGB(i, x));
				var local_province_id = decodeRGBAsNumber(local_province_colour);

				//Assign percentage
				if (local_province_id > 0)
					modifyValue(province_values, local_province_id, (local_percentage_colour[1]/200)*max_value);
			}

		//console.log("Province values: " + Object.keys(province_values));
		//Return statement
		return province_values;
	}

	function loadPopulationNumberData (arg0_provinces_file_path, arg1_numbers_file_path) {
		//Convert from parameters
		var provinces_file_path = arg0_provinces_file_path;
		var numbers_file_path = arg1_numbers_file_path;

		try {
			//Declare local instance variables
			var max_value = 1;
			var provinces_number_obj = loadNumberDataFromImage(provinces_file_path, numbers_file_path);

			//Iterate over all_province_numbers; fetch max_value
			var all_province_numbers = Object.keys(provinces_number_obj);

			for (var i = 0; i < all_province_numbers.length; i++) {
				var local_province_id = all_province_numbers[i];
				var local_value = provinces_number_obj[all_province_numbers[i]];

				max_value = Math.max(max_value, local_value);
			}

			//Iterate over all_province_numbers; set province population growth rate
			for (var i = 0; i < all_province_numbers.length; i++) {
				var local_province_id = all_province_numbers[i];
				var local_value = provinces_number_obj[all_province_numbers[i]];

				//Set population growth rate
				setProvincePopulationGrowthRate(local_province_id, local_value/max_value);
			}

			//Return statement
			return provinces_number_obj;
		} catch (e) {
			console.error(e.stack);
		}
	}

	function loadPopulationPercentageData (arg0_provinces_file_path, arg1_percentages_file_path) {
		//Convert from parameters
		var provinces_file_path = arg0_provinces_file_path;
		var percentages_file_path = arg1_percentages_file_path;

		//Declare local instance variables
		var max_value = 1;
		var provinces_percentage_obj = standardiseFraction(loadPercentageDataFromImage(provinces_file_path, percentages_file_path));

		//Iterate over all_provinces_population; Math.ceil() percentages and set in main.
		var all_province_percentages = Object.keys(provinces_percentage_obj);

		for (var i = 0; i < all_province_percentages.length; i++) {
			var local_province_id = all_province_percentages[i];
			var local_value = provinces_percentage_obj[all_province_percentages[i]];

			//Set population growth rate
			setProvincePopulationGrowthRate(local_province_id, local_value);
		}

		//Return statement
		return provinces_percentage_obj;
	}

	/**
	 * saveProvinceFileAsImage() - Saves a province file as an image in the base folder.
	 * @param {number|Object<Province>|String} arg0_province_name - The province name/ID to save as an image.
	 *
	 * @returns {Array<{province_id: String, x: number, y: number, file_path: String}>}
	 */
	function saveProvinceFileAsImage (arg0_province_name) {
		//Convert from parameters
		var province_name = arg0_province_name;

		//Declare local instance variables
		var province_obj = getProvince(province_name);

		if (province_obj) {
			var province_id = province_obj.getProvinceID();

			var input_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "data/scales/1/" + province_id;
			var output_file_path = province_id + ".png";

			//Guard clause if file doesn't exist
			var input_file = new File(input_file_path);
			if (!input_file.exists()) return;

			//1. Convert the CIM file to a PNG file and save it in cache.
			{
				var cim_file = new FileHandle(input_file_path); //Input file

				var pixmap = PixmapIO.readCIM(cim_file);
				var png_file = new FileHandle(output_file_path); //Output file

				PixmapIO.writePNG(png_file, pixmap);
				/*console.log("Saved Province " + province_id + " as " + province_id + ".png, located at X: " +
					province_obj.getMinX()/Game.mapBG.iMapScale +
					", Y: " + province_obj.getMinY()/Game.mapBG.iMapScale);*/

				//Dispose of the pixmap to prevent memory leaks.
				pixmap.dispose();
			}

			//2. Randomise the colour on the PNG
			{
				var new_colour_array = splitStringByNumber(province_id.toString(), 2);
				var new_colour = encodeNumberAsRGB(parseInt(province_id));
				var new_colour_int = convertRGBAToInt(new_colour);
				//console.log("New colour array: " + new_colour_array.join(", "));
				//console.log("New colour: " + new_colour.join(", "));
				//console.log("New colour int: " + new_colour_int);

				var raw_png_file = new File(output_file_path);

				var province_image = ImageIO.read(raw_png_file);

				var province_height = province_image.getHeight();
				var province_width = province_image.getWidth();

				//Iterate over pixels
				//console.log("Province image dimensions: " + province_width + ", " + province_height);
				for (var i = 0; i < province_width; i++)
					for (var x = 0; x < province_height; x++) {
						var local_colour = convertIntToRGBA(province_image.getRGB(i, x));

						//If the pixel has an alpha >= 255; set it to new_colour_int
						//console.log("Colour at " + i + ", " + x + " is " + local_colour.join(", "));
						if (local_colour[3] >= 255) {
							province_image.setRGB(i, x, new_colour_int);
							//console.log("Setting " + i + ", " + x + " to " + new_colour_int);
						}
					}

				//Save the modified image
				var output_file = new File(output_file_path);
				ImageIO.write(province_image, "png", output_file);
			}

			//3. Return statement
			return {
				province_id: province_id,
				x: province_obj.getMinX()/Game.mapBG.iMapScale,
				y: province_obj.getMinY()/Game.mapBG.iMapScale,

				file_path: province_id + ".png"
			};
		}
	}

	/**
	 * saveProvinceFilesAsImage() - Saves multiple province files as a single image in the base folder.
	 * @param {Array<number|Object<Province>|String>} arg0_province_names - The province names to save as an image.
	 * @param {Object} [arg1_options]
	 * @param {Object} [arg1_options.output_file_path="province_map.png"] - The file path to output to in the base folder.
	 *
	 * @returns {Array<{province_id: String, x: number, y: number, file_path: String}>}
	 */
	function saveProvinceFilesAsImage (arg0_province_names, arg1_options) {
		//Convert from parameters
		var province_names = getList(arg0_province_names);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.output_file_path) options.output_file_path = "province_map.png";

		//Declare local instance variables
		var max_x = 0;
		var max_y = 0;
		var province_images = [];

		//1. Iterate over all province_names; save them as images in cache.
		for (var i = 0; i < province_names.length; i++) {
			var local_province_data = saveProvinceFileAsImage(province_names[i]);

			if (local_province_data)
				province_images.push(local_province_data);
		}

		//2. Iterate over all province_images to determine canvas height, width
		for (var i = 0; i < province_images.length; i++) {
			var local_data = province_images[i];
			var local_image = ImageIO.read(new File(local_data.file_path));
			var local_image_height = local_image.getHeight();
			var local_image_width = local_image.getWidth();

			max_x = Math.max(local_data.x + local_image_width, max_x);
			max_y = Math.max(local_data.y + local_image_height, max_y);
		}
		console.log("Created province map with image dimensions: " + max_x + ", " + max_y);

		//3. Create a blank canvas with dimensions [max_x, max_y]
		var collated_image = new BufferedImage(max_x, max_y, BufferedImage.TYPE_INT_ARGB);
		var graphics = collated_image.createGraphics();

		//4. Iterate over all province_images; draw each image onto graphics (canvas)
		for (var i = 0; i < province_images.length; i++) {
			var local_data = province_images[i];
			var local_image = ImageIO.read(new File(local_data.file_path));

			graphics.drawImage(local_image, local_data.x, local_data.y, null);
		}

		//5. Save the collated image; dispose graphics
		graphics.dispose();
		var output_file = new File(options.output_file_path);
		ImageIO.write(collated_image, "png", output_file);

		//Return statement
		return province_images;
	}

	/**
	 * saveProvinceMap() - Saves the currently loaded map as an output province map.
	 * @param {String} arg0_file_path - The file path to output the province map to.
	 *
	 * @returns {Array<{province_id: String, x: number, y: number, file_path: String}>}
	 */
	function saveProvinceMap (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var province_images = saveProvinceFilesAsImage(all_provinces, { output_file_path: file_path });

		//Iterate over all province_images to delete cache files from the base folder
		for (var i = 0; i < province_images.length; i++) {
			var local_data = province_images[i];
			var local_file = new File(local_data.file_path);

			//Check to make sure file exists before attempting to delete it
			if (local_file.exists())
				try {
					local_file.delete();
				} catch (e) { console.error(e); }
		}

		//Return statement
		return province_images;
	}

	/**
	 * writeASCFileToImage() - Used to write a .asc file to a valid image.
	 * @param {String} arg0_input_file_path - The file path of the ASC file to translate to a PNG.
	 * @param {options} [arg1_options]
	 * @param {String} [arg1_options.mode="percentage"] - What the mode should be. Either 'absolute'/'percentage'.
	 * @param {String} [arg1_options.output_file_path="asc_output.png"] - The output file path in the base directory.
	 * @param {String} [arg1_options.scalar=2] - The scalar to divide the max_value by.
	 *
	 * @returns {{dataframe: Array<Array<number>>, max_value: number}}
	 */
	function writeASCFileToImage (arg0_input_file_path, arg1_options) {
		//Convert from parameters
		var input_file_path = arg0_input_file_path;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.mode) options.mode = "percentage";
		if (!options.output_file_path) options.output_file_path = "asc_output.png";
		options.scalar = returnSafeNumber(options.scalar, 2);

		//Guard clause; check if input_file_path exists
		if (!fileExists(input_file_path)) {
			console.error("writeASCFileToImage() - Input file path doesn't exist at " + input_file_path);
			return;
		}

		//Declare local instance variables
		var asc_dataframe = readASCFile(input_file_path);
		var image_columns = asc_dataframe[0].length;
		var image_rows = asc_dataframe.length;

		var image = new BufferedImage(image_columns, image_rows, BufferedImage.TYPE_INT_ARGB);
		var max_value = getMaximumInArray(asc_dataframe)/options.scalar;

		//Iterate over all asc_dataframe rows
		for (var i = 0; i < image_columns; i++)
			for (var x = 0; x < image_rows; x++) {
				var local_colour;
				var local_value = asc_dataframe[x][i];

				if (local_value != undefined) {
					if (options.mode == "absolute") {
						local_colour = convertIntToRGBA(local_value);
					} else if (options.mode == "percentage") {
						var local_g = Math.min(Math.round((local_value/max_value)*100), 200);

						local_colour = convertRGBAToInt([0, local_g, 0, 255]);
					}
				} else {
					local_colour = convertRGBAToInt([0, 0, 0, 0]);
				}

				//Set image pixel
				image.setRGB(i, x, local_colour);
			}

		console.log("ASC output file written to: " + options.output_file_path);

		//Write the image to file
		var output_file = new File(options.output_file_path);
		ImageIO.write(image, "png", output_file);

		//Return statement
		return {
			dataframe: asc_dataframe,
			max_value: max_value*options.scalar
		};
	}

	/**
	 * writeProvincesToFile() - Writes a map editor's ProvincePoints.json to an AOC3-compatible ProvincePoints.json
	 * @param {String} arg0_input_file_path - The input file path leading to the map editor's ProvincePoints.json.
	 * @param {String} [arg1_output_file_path="ProvincePoints.json"] - The output file path.
	 *
	 * @returns {String}
	 */
	function writeProvincesToFile (arg0_input_file_path, arg1_output_file_path) {
		//Convert from parameters
		var input_file_path = arg0_input_file_path;
		var output_file_path = (arg1_output_file_path) ? arg1_output_file_path : "ProvincePoints.json";

		//Guard clause if input_file_path does not exist
		if (!fileExists(input_file_path)) {
			console.error("writeProvincesToFile() - Input file path " + input_file_path + " does not exist. Returned undefined.");
			return undefined;
		}

		//Declare local instance variables
		var raw_data = readFile(input_file_path);

		//Fix raw_data; parse JSON
		raw_data = raw_data.replace(/(\w+):/g, '"$1":')
		.replace(/,(\s*\])/g, '$1')
		.replace(/"Age_of_History":\s*Data,/, '"Age_of_History": "Data",');
		var map_obj = JSON.parse(raw_data);

		//Create new_map_obj
		var new_map_obj = {
			Data: []
		};

		//Iterate over map_obj.Data.length; fix points to compatible format
		for (var i = 0; i < map_obj.Data.length; i++)
			new_map_obj.Data.push({
				pX: map_obj.Data[i].lPointsX,
				pY: map_obj.Data[i].lPointsY
			});

		//Prepare processed_data; write to file
		var processed_data = "{\nAge_of_History: Data,\nData: " + JSON.stringify(new_map_obj.Data).replace(/"(\w+)":/g, '$1:') + "\n}";

		writeFile(output_file_path, processed_data);

		//Return statement
		return processed_data;
	}
}