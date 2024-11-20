//Initialise functions
{
	/**
	 * appendDataframe() - Appends two dataframes to one another.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to input into the function.
	 * @param {Array<Array>} arg1_dataframe - The dataframe to append.
	 * @param {Object} [arg2_options]
	 * @param {*} [arg2_options.default_value] - What the default variable should be.
	 */
	function appendDataframes (arg0_dataframe, arg1_dataframe, arg2_options) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var ot_dataframe = arg1_dataframe;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var headers = [];
		var header_set = {};

		//Iterate over dataframe[0]; or the dataframe header
		for (var i= 0; i < dataframe[0].length; i++)
			if (!header_set[dataframe[0][i]]) {
				headers.push(dataframe[0][i]);
				header_set[dataframe[0][i]] = true;
			}
		//Iterate over ot_dataframe[0]; or the dataframe header
		for (var i = 0; i < ot_dataframe[0].length; i++)
			if (!header_set[ot_dataframe[0][i]]) {
				headers.push(ot_dataframe[0][i]);
				header_set[ot_dataframe[0][i]] = true;
			}

		//Instantiate new dataframe; remove headers from original dataframes
		var dataframe_one = dataframe.slice(1);
		var dataframe_two = ot_dataframe.slice(1);
		var new_dataframe = [headers];

		//Append data from the first daatframe
		for (var i = 0; i < dataframe_one.length; i++)
			new_dataframe.push(dataframe_one[i]);

		//Append data from the second dataframe
		for (var i = 0; i < dataframe_two.length; i++) {
			var local_row = dataframe_two[i];
			var new_row = new Array(headers.length);

			for (var x = 0; x < new_row.length; x++)
				new_row[x] = options.default_value;
			for (var x = 0; x < row.length; x++) {
				var column_index = headers.indexOf(ot_dataframe[0][x]);
				new_row[column_index] = row[x];
			}

			new_dataframe.push(new_row);
		}

		//Return statement
		return new_dataframe;
	}

	/**
	 * convertDataframeToObject() - Converts a dataframe to an object.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to convert to an object.
	 *
	 * @returns {Object}
	 */
	function convertDataframeToObject (arg0_dataframe) {
		//Convert from parameters
		var dataframe = arg0_dataframe;

		//Guard clause
		if (!Array.isArray(dataframe)) return dataframe;

		//Declare local instance variables
		var dataframe_header = dataframe[0];
		var dataframe_obj = {};

		//Guard clause if dataframe has no header, or is not 2D array
		if (!dataframe_header || !Array.isArray(dataframe_header)) return dataframe;

		//Initialise dataframe_obj subobjects; then dataframe rows
		for (var i = 0; i < dataframe_header.length; i++)
			dataframe_obj[dataframe_header[i]] = {};
		for (var i = 1; i < dataframe.length; i++)
			for (var x = 0; x < dataframe[i].length; x++)
				if (dataframe_header[x])
					dataframe_obj[dataframe_header[x]][i] = dataframe[i][x];

		//Return statement
		return dataframe_obj;
	}

	/**
	 * convertObjectToDataframe() - Converts a given object to a dataframe.
	 * @param {Object} arg0_dataframe_obj - The object to convert into a dataframe.
	 *
	 * @returns {Array<Array>}
	 */
	function convertObjectToDataframe (arg0_dataframe_obj) {
		//Convert from parameters
		var dataframe_obj = arg0_dataframe_obj;

		//Guard clause
		if (typeof dataframe_obj != "object") return dataframe_obj;

		//Declare local instance variables
		var all_variables = Object.keys(dataframe_obj);
		var return_dataframe = [];

		//Set header
		return_dataframe.push(all_variables);

		//Iterate over all_variables
		for (var i = 0; i < all_variables.length; i++) {
			var local_subobj = dataframe_obj[all_variables[i]];

			var all_local_keys = Object.keys(local_subobj);

			for (var x = 0; x < all_local_keys.length; x++) {
				var local_value = local_subobj[all_local_keys[x]];

				//Initialise array if nonexistent
				if (!return_dataframe[all_local_keys[x]])
					return_dataframe[all_local_keys[x]] = [];
				return_dataframe[all_local_keys[x]][i] = local_value;
			}
		}

		//Return statement
		return return_dataframe;
	}

	/**
	 * getColumns() - Fetches the number of columns in a dataframe.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 *
	 * @returns {number}
	 */
	function getColumns (arg0_dataframe) {
		//Convert from parameters
		var dataframe = arg0_dataframe;

		//Guard clause
		if (dataframe.length == 0) return 0;

		//Declare local instance variables
		var max_columns = 0;

		//Iterate over dataframe
		for (var i = 0; i < dataframe.length; i++)
			if (Array.isArray(dataframe[i]))
				max_columns = Math.max(dataframe[i].length, max_columns);

		//Return statement
		return max_columns;
	}

	/**
	 * getDimensions() - Returns the number of columns and rows.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 *
	 * @returns {Array<number, number>}
	 */
	function getDimensions (arg0_dataframe) {
		//Convert from parameters
		var dataframe = arg0_dataframe;

		//Return statement
		return [getColumns(dataframe), getRows(dataframe)];
	}

	/**
	 * getRows() - Fetches the number of rows in a dataframe.
	 * @param {Array<Array>} - The dataframe to pass to the function.
	 *
	 * @returns {number}
	 */
	function getRows (arg0_dataframe) {
		//Convert from parameters
		var dataframe = arg0_dataframe;

		//Return statement
		return dataframe.length;
	}

	/**
	 * hasHeaer() - Checks whether a dataframe has a true header.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 *
	 * @returns {boolean}
	 */
	function hasHeader (arg0_dataframe) {
		//Convert from parameters
		var dataframe = getList(arg0_dataframe);

		//Declare local instance variables
		var has_header = false;

		//Check if dataframe has_header
		if (Array.isArray(dataframe[0])) {
			var all_strings = true;

			//Iterate over dataframe[0], or dataframe header
			for (var i = 0; i < dataframe[0].length; i++)
				if (typeof dataframe[0][i] != "string") {
					all_strings = false;
					break;
				}
			if (all_strings)
				has_header = true;
		}

		//Return statement
		return has_header;
	}

	/**
	 * mergeDataframes() - Merges two dataframes; with the second dataframe's columns being appended to the first dataframe post-operation. Mathematical operations can be applied here as a system of equations. Dataframes may have different dimensions, non-corresponding values are assumed to be zero or undefined.
	 *
	 * Dataframes are a 2D array, typically with a header row.
	 *
	 * @param {Array<Array>} arg0_dataframe - The 1st dataframe to pass to the function.
	 * @param {Array<Array>} arg1_dataframe - The 2nd dataframe to pass to the function.
	 * @param {Object} [arg2_options]
	 * @param {String} [arg2_options.equation] - The string literal to use as an equation (e.g. 'i + x*5'). If no equal sign is provided, this applies to every cell, regardless of column. Equations are split by semicolons.<br><br>As an example, x$D = i$B replaces the D column of the 2nd dataframe with the B column of the 1st.<br>- 'i' represents the corresponding element of the first dataframe,<br>  - 'i$Column' represents the selection of a 1st dataframe column named 'Column'.<br>- 'x' represents the corresponding element of the second dataframe,<br>  - 'x$Column' represents the selection of a 2nd dataframe column named 'Column'.
	 *
	 * @returns {Array<Array>}
	 */
	function mergeDataframes (arg0_dataframe, arg1_dataframe, arg2_options) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var ot_dataframe = arg1_dataframe;
		var options = (arg2_options) ? arg2_options : {};

		//Process options.equation
		if (options.equation) {
			var operate_dataframes = operateDataframes(dataframe, ot_dataframe, options);

			dataframe = operate_dataframes.dataframe;
			ot_dataframe = operate_dataframes.ot_dataframe;
		}

		//Return statement
		return appendDataframes(dataframe, ot_dataframe);
	}

	/**
	 * operateDataframes() - Operates on two dataframes by applying an equation string.
	 *
	 * @param {Array<Array>} arg0_dataframe - The 1st dataframe to pass to the function.
	 * @param {Array<Array>} arg1_dataframe - The 2nd dataframe to pass to the function.
	 * @param {Object} [arg2_options]
	 * @param {String} [arg2_options.equation] - The string literal to use as an equation (e.g. 'i + x*5'). If no equal sign is provided, this applies to every cell, regardless of column. Equations are split by semicolons.<br><br>As an example, x$D = i$B replaces the D column of the 2nd dataframe with the B column of the 1st.<br>- 'i' represents the corresponding element of the first dataframe,<br>  - 'i$Column' represents the selection of a 1st dataframe column named 'Column'.<br>- 'x' represents the corresponding element of the second dataframe,<br>  - 'x$Column' represents the selection of a 2nd dataframe column named 'Column'.
	 * @param {boolean} [arg2_options.return_safe_number=true] - Whether to use returnSafeNumber().
	 *
	 * @returns {{dataframe: Array<Array>, ot_dataframe: Array<Array>}}
	 */
	function operateDataframes (arg0_dataframe, arg1_dataframe, arg2_options) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var ot_dataframe = arg1_dataframe;
		var options = (arg2_options) ? arg2_options : {};

		//Initialise options
		if (options.return_safe_number != false) options.return_safe_number = true;

		//Parse options.equation
		if (options.equation) {
			//Convert dataframes to objects for easier corresponding manipulation
			var dataframe_length = dataframe.length;
			var ot_dataframe_length = ot_dataframe.length;

			//Formatting variables
			var f_0 = (options.return_safe_number) ? "returnSafeNumber(" : "";
			var f_1 = (options.return_safe_number) ? ")" : "";

			dataframe = convertDataframeToObject(dataframe);
			ot_dataframe = convertDataframeToObject(ot_dataframe);

			var split_equation = options.equation.split(";");

			//Iterate over split_equation to apply them to dataframe and ot_dataframe
			for (var i = 0; i < split_equation.length; i++) {
				var local_regex = /\$(\w+)/g;

				var converted_string = split_equation[i].replace(local_regex, '["$1"]');
				converted_string = converted_string.replace(/\//g, "/");
				var max_length = (dataframe_length > ot_dataframe_length) ? dataframe_length : ot_dataframe_length;

				//Iterate over max_length
				for (var x = 1; x < max_length; x++) {
					var processed_equation = converted_string.replace(/"]/g, '"][' + x + ']');
					var regex_i = /i\["(\w+)"\]\[(\d+)\]/g;
					var regex_x = /x\["(\w+)"\]\[(\d+)\]/g;

					//Split processed_equation
					processed_equation = processed_equation.split("=");
					processed_equation[1] = processed_equation[1].replace(regex_i, f_0 + 'i["$1"][' + x + ']' + f_1);
					processed_equation[1] = processed_equation[1].replace(regex_x, f_0 + 'x["$1"][' + x + ']' + f_1);

					var equation_expression = processed_equation[0] + " = " + processed_equation[1] + ";";
					var equation_function = new Function("i", "x", equation_expression);

					//Process function
					equation_function(dataframe, ot_dataframe);
				}
			}

			//Reconvert back to dataframe arrays
			dataframe = convertObjectToDataframe(dataframe);
			ot_dataframe = convertObjectToDataframe(ot_dataframe);
		}

		//Return statement
		return {
			dataframe: dataframe,
			ot_dataframe: ot_dataframe
		};
	}

	/**
	 * setHeader() - Sets the upper header variables.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 * @param {Array<String>} arg1_header_array - The names of variables to set on the 0th row.
	 *
	 * @returns {Array<Array>}
	 */
	function setHeader (arg0_dataframe, arg1_header_array) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var headers = getList(arg1_header_array);

		//Set header
		dataframe[0] = headers;

		//Return statement
		return dataframe;
	}

	/**
	 * selectColumn() - Selects a 2D array column (by header name).
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 * @param {string} arg1_column_name - The name of the variable/column to select.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.return_index=false] - Whether to return an index.
	 * @returns {Array}
	 */
	function selectColumn (arg0_dataframe, arg1_column_name, arg2_options) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var column_name = arg1_column_name.toString().toLowerCase();
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var column_exists = [false, -1];
		var return_array = [];

		//Iterate over dataframe header
		if (Array.isArray(dataframe))
			if (dataframe[0]) {
				//Soft-match first
				for (var i = 0; i < dataframe[0].length; i++)
					//Check against local string
					if (dataframe[0][i].toString().toLowerCase().indexOf(column_name) != -1)
						column_exists = [true, i];

				//Hard-match second
				for (var i = 0; i < dataframe[0].length; i++)
					//Check against local string
					if (dataframe[0][i].toString().toLowerCase() == column_name)
						column_exists = [true, i];
			}

		//Return statement; options.return_index guard clause
		if (options.return_index) return column_exists[1];

		//If column_exists[0], process return_array
		if (column_exists[0])
			for (var i = 0; i < dataframe.length; i++)
				return_array.push(dataframe[i][column_exists[1]]);

		//Return statement
		return return_array;
	}

	/**
	 * selectRow() - Selects a 2D array row (by header name or index).
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 * @param {number} arg1_row_index - The row index to pass to the function.
	 * @param {Object} [arg2_options]
	 * @param {Object} [arg2_options.exclude_header=false] - Whether to exclude the header.
	 * @returns {Array}
	 */
	function selectRow (arg0_dataframe, arg1_row_index, arg2_options) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var row_index = arg1_row_index;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return (!options.exclude_header) ? dataframe[row_index] : dataframe[row_index + 1];
	}

	/**
	 * setColumn() - Sets a 2D array column.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 * @param {string} arg1_column_name - The name of the variable/column to set.
	 * @param {Array} arg2_values - The list of values to set for this column.
	 *
	 * @returns {Array}
	 */
	function setColumn (arg0_dataframe, arg1_column_name, arg2_values) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var column_name = arg1_column_name;
		var values = getList(arg2_values);

		//Declare local instance variables
		var column_index = (isNaN(column_name)) ? selectColumn(dataframe, column_name, { return_index: true }) : column_name;

		//Set new values; delete rest
		for (var i = 0; i < dataframe.length; i++)
			dataframe[i][column_index] = values[i];

		//Return statement
		return dataframe.filter(function(row) {
			return row.some(function(element) {
				return (element !== undefined && element !== null)
			});
		});
	}

	/**
	 * setRow() - Sets a 2D array row.
	 * @param {Array<Array>} arg0_dataframe - The dataframe to pass to the function.
	 * @param {number} arg1_row_index - The row index to pass to the function.
	 * @param {Array} arg2_values - The list of values to set for this row.
	 *
	 * @returns {Array}
	 */
	function setRow (arg0_dataframe, arg1_row_index, arg2_values) {
		//Convert from parameters
		var dataframe = arg0_dataframe;
		var row_index = arg1_row_index;
		var values = getList(arg2_values);

		//Set local row_index to values
		if (isArrayEmpty(dataframe)) {
			dataframe[row_index] = values;
		} else {
			dataframe.splice(row_index, 1);
		}

		//Return statement
		return dataframe;
	}
}