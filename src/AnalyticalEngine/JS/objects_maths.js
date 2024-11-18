//Initialise function
{
	function addObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n + " + value);
	}

	function addObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i + x", options);
	}

	function changeObjectRange (arg0_object, arg1_key, arg2_min_max_argument, arg3_value) {
		//Convert from parameters
		var object = arg0_object;
		var key = arg1_key;
		var min_max_argument = arg2_min_max_argument;
		var value = Math.round(returnSafeNumber(arg3_value));

		//Add to object
		if (object[key]) {
			if (min_max_argument == "minimum") {
				object[key][0] += value;
			} else if (min_max_argument == "maximum") {
				object[key][1] += value;
			} else {
				object[key][0] += value;
				object[key][1] += value;
			}
		} else {
			if (min_max_argument == "minimum") {
				object[key] = [value, 0];
			} else if (min_max_argument == "maximum") {
				object[key] = [0, value];
			} else {
				object[key] = [value, value];
			}
		}

		//Return statement
		return object;
	}

	function divideObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n/" + value);
	}

	function divideObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i/x", options);
	}

	function getObjectMaximum (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (options.recursive != false) options.recursive = true;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var local_maximum;

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (typeof local_value == "number")
				local_maximum = Math.max(local_value, local_maximum);

			//Ranges handler
			if (options.include_ranges && Array.isArray(local_value))
				if (local_value.length == 2 && arrayIsOfType(local_value, "number"))
					for (var x = 0; x < local_value.length; x++)
						local_maximum = Math.max(local_value[x], local_maximum);

			//Object handler
			if (options.recursive)
				if (typeof local_value == "object") {
					var subobject_maximum = getObjectMaximum(local_value, options);

					local_maximum = Math.max(local_maximum, subobject_maximum);
				}
		}

		//Return statement
		return local_maximum;
	}

	function getObjectMinimum (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var local_minimum;

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (typeof local_value == "number")
				local_minimum = Math.min(local_value, local_minimum);

			//Ranges handler
			if (options.include_ranges && Array.isArray(local_value))
				if (local_value.length == 2 && arrayIsOfType(local_value, "number"))
					for (var x = 0; x < local_value.length; x++)
						local_minimum = Math.min(local_value[x], local_minimum);

			//Object handler
			if (options.recursive)
				if (typeof local_value == "object") {
					var subobject_minimum = getObjectMinimum(local_value, options);

					local_minimum = Math.min(local_minimum, subobject_minimum);
				}
		}

		//Return statement
		return local_minimum;
	}

	function getObjectSum (arg0_object, arg1_options) {
		//Convert from parameters
		var object = arg0_object;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (options.recursive != false) options.recursive = true;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var total_sum = 0;

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (typeof local_value == "number") {
				total_sum += local_value;
			} else if (typeof local_value == "object") {
				//Recursively call function
				total_sum += getObjectSum(local_value, options);
			}
		}

		//Return statement
		return total_sum;
	}

	function invertFractionObject (arg0_object) {
		//Convert from parameters
		var object = JSON.parse(JSON.stringify(arg0_object));

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			object[all_object_keys[i]] = 1 - local_value;
		}

		//Return statement
		return object;
	}

	function modifyObjectRange (arg0_object, arg1_value, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var value = returnSafeNumber(arg1_value);
		var options = (arg2_options) ? arg2_options : {};

		//Initialise options
		options.include_numbers = (options.include_numbers != false) ? true : false;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);

		//Iterate over all_object
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			//Check if local_value is a range
			if (Array.isArray(local_value))
				if (local_value == 2)
					if (arrayIsOfType(local_value, "number")) {
						local_value[0] += value;
						local_value[1] += value;
					}
			//Check if local_value is a number
			if (options.include_numbers && typeof local_value == "number")
				local_value += value;
		}

		//Return statement
		return object;
	}

	function multiplyObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n*" + value + "");
	}

	function multiplyObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i*x", options);
	}

	function operateObject (arg0_object, arg1_equation, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var equation = arg1_equation;
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var log_error_string = (options.log_errors) ? "console.log(e);" : "";
		var only_numbers = (options.only_numbers);

		var equation_expression = "try { return " + equation + "; } catch (e) {" + log_error_string + "};";
		var equation_function = new Function("n", equation_expression);
		var processed_object = {};

		//Iterate over all_object_keys; calculate the operation recursively
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (typeof local_value == "object") {
				if (options.recursive != false)
					//Set processed_object
					processed_object[all_object_keys[i]] = operateObject(local_value, equation, options);
			} else {
				//Set local value
				if ((only_numbers && typeof local_value == "number") || !only_numbers)
					processed_object[all_object_keys[i]] = equation_function(local_value);
			}
		}

		//Return statement
		return processed_object;
	}

	function operateObjects (arg0_object, arg1_object, arg2_equation, arg3_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var equation = arg2_equation;
		var options = (arg3_options) ? arg3_options : {};

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var all_ot_object_keys = Object.keys(ot_object);
		var log_error_string = (options.log_errors) ? "console.log(e);" : "";
		var only_numbers = (options.only_numbers);

		var equation_expression = "try { " + equation + " } catch (e) {};" +
			"return { object: i, ot_object: x };";
		var equation_function = new Function("i", "x", equation_expression);
		var processed_object = {};
		var processed_ot_object = {};

		//Iterate over all_object_keys; calculate the operation of each two objects for i
		for (var i = 0; i < all_object_keys.length; i++) {
			var i_value = object[all_object_keys[i]];
			var x_value = ot_object[all_object_keys[i]];

			//Set x_value
			if (x_value == undefined) x_value = 0;

			if (typeof i_value == "object") {
				//Recursively call operateObjects() on subobjects
				var local_return_values = operateObjects(i_value, x_value, equation, options);

				//Set processed_object; processed_ot_object
				processed_object[all_object_keys[i]] = local_return_values.object;
				processed_ot_object[all_object_keys[i]] = local_return_values.ot_object;
			} else {
				var local_return_values = equation_function(i_value, x_value);

				//Check if only numbers condition is met
				if ((only_numbers && typeof i_value == "number" && typeof x_value == "number") || !only_numbers) {
					if (i_value) processed_object[all_object_keys[i]] = object[all_object_keys[i]];
					if (x_value) processed_ot_object[all_object_keys[i]] = ot_object[all_object_keys[i]];
				} else {
					//Set processed_object; processed_ot_object
					processed_object[all_object_keys[i]] = local_return_values.object;
					processed_ot_object[all_object_keys[i]] = local_return_values.ot_object;
				}
			}
		}

		//Iterate over all_ot_object_keys; calculate the operation of each two objects for x
		for (var i = 0; i < all_ot_object_keys.length; i++) {
			var i_value = object[all_ot_object_keys[i]];
			var x_value = ot_object[all_ot_object_keys[i]];

			//Set i_value to default if undefined
			if (i_value == undefined) i_value = 0;

			if (typeof i_value == "object")
				//Recursively call operateObjects() on subobjects
				if (options.recursive != false) {
					var local_return_values = operateObjects(i_value, x_value, equation, options);

					//Set processed_object; processed_ot_object
					processed_object[all_ot_object_keys[i]] = local_return_values.object;
					processed_ot_object[all_ot_object_keys[i]] = local_return_values.ot_object;
				} else {
					var local_return_values = equation_function(i_value, x_value);

					//Check if only_numbers condition is met
					if ((only_numbers &&
							typeof i_value == "number" && typeof x_value == "number")
						|| !only_numbers) {
						if (i_value)
							processed_object[all_ot_object_keys[i]] = object[all_ot_object_keys[i]];
						if (x_value)
							processed_ot_object[all_ot_object_keys[i]] = ot_object[all_ot_object_keys[i]];
					} else {
						//Set processed_object; processed_ot_object
						processed_object[all_ot_object_keys[i]] = local_return_values.object;
						processed_ot_object[all_ot_object_keys[i]] = local_return_values.ot_object;
					}
				}
		}

		//Return statement
		return {
			object: processed_object,
			ot_object: processed_ot_object
		};
	}

	function standardiseFraction (arg0_object) {
		//Convert from parameters
		var object = JSON.parse(JSON.stringify(arg0_object));

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var object_maximum = getObjectMaximum(object);

		//Iterate over all_object_keys
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			if (object_maximum == 0) {
				object[all_object_keys[i]] = 0;
			} else {
				object[all_object_keys[i]] = local_value/object_maximum;
			}
		}

		//Return statement
		return object;
	}

	function standardisePercentage (arg0_object, arg1_total, arg2_round) {
		//Convert from parameters
		var object = JSON.parse(JSON.stringify(arg0_object));
		var total = (arg1_total) ? arg1_total : 1;
		var round = arg2_round;

		//Declare local instance variables
		var all_object_keys = Object.keys(object);
		var object_total = 0;

		//Fetch object_total
		for (var i = 0; i < all_object_keys.length; i++)
			object_total += returnSafeNumber(object[all_object_keys[i]]);

		//Standardise to object_total
		for (var i = 0; i < all_object_keys.length; i++) {
			var local_value = object[all_object_keys[i]];

			//Set local_value to % value
			local_value = local_value/object_total;

			//Multiply % value by total
			object[all_object_keys[i]] = (round) ?
				Math.ceil(local_value*total) :
				local_value*total;
		}

		//Return statement
		return object;
	}

	function subtractObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n - " + value);
	}

	function subtractObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i - x", options);
	}
}