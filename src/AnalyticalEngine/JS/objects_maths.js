//Initialise function
{
	/**
	 * addObject() - Adds a value to an object, recursively.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_value - The value to add to each variable in the object.
	 *
	 * @returns {Object}
	 */
	function addObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n + " + value);
	}

	/**
	 * addObjects() - Adds values between two objects, recursively.
	 * @param {Object} arg0_object - The 1st object to pass.
	 * @param {Object} arg1_object - The 2nd object to pass.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {{object: Object, ot_object: Object}}
	 */
	function addObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i + x", options);
	}

	/**
	 * changeObjectRange() - Changes object ranges, non-recursively, for a given key.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {String} arg1_key - The key corresponding to the range to modify.
	 * @param {String} arg2_min_max_argument - Either 'minimum'/'maximum'. 'both' by default.
	 * @param {number} arg3_value - The number to change these ranges by.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * divideObject()  - Divides an object by a value, recursively.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_value - The value to divide each variable in the object by.
	 *
	 * @returns {Object}
	 */
	function divideObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n/" + value);
	}

	/**
	 * divideObjects() - Divides two objects, recursively.
	 * @param {Object} arg0_object - The 1st object to pass.
	 * @param {Object} arg1_object - The 2nd object to pass.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {{object: Object, ot_object: Object}}
	 */
	function divideObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i/x", options);
	}

	/**
	 * getObjectMaximum() - Fetches the maximum value within an object.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.include_ranges=false] - Whether to include ranges.
	 * @param {boolean} [arg1_options.recursive=true] - Whether the function should be recursive.
	 *
	 * @returns {number}
	 */
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

	/**
	 * getObjectMinimum() - Fetches the minimum value within an object.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.include_ranges=false] - Whether to include ranges.
	 * @param {boolean} [arg1_options.recursive=true] - Whether the function is recursive.
	 */
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

	/**
	 * getObjectSum() - Fetches the object sum, recursively.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {Object} [arg1_options]
	 * @param {boolean} [arg1_options.recursive=true] - Whether to sum recursively.
	 *
	 * @returns {number}
	 */
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

	/**
	 * invertFractionObject() - Inverts a fraction object, fetching the reciprocal of percentage values.
	 * @param {Object} arg0_object - The object to pass.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * modifyObjectRange() - Modifies ranges in an object recursively, by operating on objects.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_value - The value to modify ranges by.
	 * @param {Object} [arg2_options]
	 * @param {Object} [arg2_options.include_numbers=true] - Whether to include single numbers.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * multiplyObject() - Multiplies an object by a value, recursively.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_value - The value to add to each variable in the object.
	 *
	 * @returns {Object}
	 */
	function multiplyObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n*" + value + "");
	}

	/**
	 * multiplyObjects() - Multiplies an object recursively.
	 * @param {Object} arg0_object - The 1st object to pass.
	 * @param {Object} arg1_object - The 2nd object to pass.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {{object: Object, ot_object: Object}}
	 */
	function multiplyObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i*x", options);
	}

	/**
	 * operateObject() - Performs an operation on a single object, recursively.
	 * @param {Object} arg0_object - The object to pass to operateObject().
	 * @param {String} arg1_equation - The string literal to use as an equation.<br>- 'n' represents the corresponding element of the 1st object.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.log_errors=false] - Whether to log errors.
	 * @param {boolean} [arg2_options.only_numbers=true] - Whether only numbers can be operated on.
	 * @param {boolean} [arg2_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * operateObjects() - Performs an operation on two objects together, recursively.
	 * @param {Object} arg0_object - The 1st object to pass to operateObjects().
	 * @param {Object} arg1_object - The 2nd object to pass to operateObjects().
	 * @param {String} arg2_equation - The string literal to use as an equation (e.g. i = i + x).<br>- 'i' represents the corresponding element of the 1st object,<br>- 'x' represents the corresponding element of the 2nd object;<br>- undefined values are represented as zero.
	 * @param {Object} [arg3_options]
	 * @param {boolean} [arg3_options.log_errors=false] - Whether to log errors.
	 * @param {boolean} [arg3_options.only_numbers=true] - Whether only numbers can be operated on.
	 * @param {boolean} [arg3_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {{object: Object, ot_object: Object}}
	 */
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

	/**
	 * standardiseFraction() - Standardises the object to maximum = 1, with each other value being adjusted to a value.
	 * @param {Object} arg0_object - The object to pass.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * standardisePercentage() - Standardises the object to a given total.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_total - The total figure to adjust the object to.
	 * @param {boolean} [arg2_round=false] - Whether to force rounding when standardising.
	 *
	 * @returns {Object}
	 */
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

	/**
	 * subtractObject() - Subtracts a value from an object, recursively.
	 * @param {Object} arg0_object - The object to pass.
	 * @param {number} arg1_value - The value to add to each variable in the object.
	 *
	 * @returns {Object}
	 */
	function subtractObject (arg0_object, arg1_value) {
		//Convert from parameters
		var object = arg0_object;
		var value = arg1_value;

		//Return statement
		return operateObject(object, "n - " + value);
	}

	/**
	 * subtractObjects() - Subtracts one object from another, recursively.
	 * @param {Object} arg0_object - The 1st object to pass.
	 * @param {Object} arg1_object - The 2nd object to pass.
	 * @param {Object} [arg2_options]
	 * @param {boolean} [arg2_options.recursive=true] - Whether the operation is recursive.
	 *
	 * @returns {{object: Object, ot_object: Object}}
	 */
	function subtractObjects (arg0_object, arg1_object, arg2_options) {
		//Convert from parameters
		var object = arg0_object;
		var ot_object = arg1_object;
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateObjects(object, ot_object, "i = i - x", options);
	}
}