//Initialise functions
{
	function addRange (arg0_range, arg1_number) {
		//Convert from parameters
		var range = arg0_range;
		var number = arg1_number;

		//Guard clause for number
		if (isNaN(number)) return range;

		//Add number to range
		range[0] += number;
		range[1] += number;

		//Return statement
		return range;
	}

	function addRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		return_range[0] += ot_range[0];
		return_range[1] += ot_range[1];

		//Return statement
		return return_range.sort();
	}

	function calculateNumberInRange (arg0_range, arg1_number, arg2_value_equation) {
		//Convert from parameters
		var range = getList(arg0_range);
		var number = arg1_number;
		var value_equation = (arg2_value_equation) ? arg2_value_equation : "VALUE";

		//Guard clause; range must at least have min and max
		if (range.length < 2) return 0;

		//Declare local instance variables
		var actual_max_value = parseVariableString(value_equation, { VALUE: range[1] });
		var actual_min_value = parseVariableString(value_equation, { VALUE: range[0] });

		var actual_difference = actual_max_value - actual_min_value;
		var nominal_difference = range[1] - range[0];

		//Return statement
		return range[0] + number*(nominal_difference/actual_difference);
	}

	function divideRange (arg0_range, arg1_number) {
		//Convert from parameters
		var range = arg0_range;
		var number = arg1_number;

		//Guard clause for number
		if (isNaN(number)) return range;

		//Divide range by number
		range[0] /= number;
		range[1] /= number;

		//Return statement
		return range;
	}

	function divideRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		range[0] /= ot_range[0];
		range[1] /= ot_range[1];

		//Return statement
		return range.sort();
	}

	function exponentiateRange (arg0_range, arg1_power) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var power = arg1_power;

		//Guard clause for power
		if (isNaN(power)) return power;

		//Exponentiate range by power
		range[0] = Math.pow(range[0], power);
		range[1] = Math.pow(range[1], power);

		//Return statement
		return range.sort();
	}

	function exponentiateRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		range[0] = Math.pow(range[0], ot_range[0]);
		range[1] = Math.pow(range[1], ot_range[1]);

		//Return statement
		return range.sort();
	}

	function getMidpoint (arg0_range) {
		//Convert from parameters
		var range = getRange(arg0_range);

		//Return statement
		return (range[0] + range[1])/2;
	}

	function getRange (arg0_range) {
		//Convert from parameters
		var range = arg0_range;

		//Declare local instance variables
		var range_array = [];

		//Check if range is Array
		if (Array.isArray(range)) {
			if (range.length >= 2) {
				range_array = [range[0], range[1]];
			} else if (range.length == 1) {
				range_array = [range[0], range[0]];
			} else {
				range_array = [0, 0];
			}
		} else if (typeof range == "number") {
			range_array = [range, range];
		}

		//Return statement
		return JSON.parse(JSON.stringify(range_array.sort()));
	}

	function multiplyRange (arg0_range, arg1_number) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var number = arg1_number;

		//Apply operator
		range[0] *= number;
		range[1] *= number;

		//Return statement
		return range.sort();
	}

	function multiplyRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		range[0] *= ot_range[0];
		range[1] *= ot_range[1];

		//Return statement
		return range.sort();
	}

	function rootRange (arg0_range, arg1_root) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var root = returnSafeNumber(arg1_root);

		//Apply operator
		range[0] = root(range[0], root);
		range[1] = root(range[1], root);

		//Return statement
		return range.sort();
	}

	function rootRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		range[0] = root(range[0], ot_range[0]);
		range[1] = root(range[1], ot_range[1]);

		//Return statement
		return range.sort();
	}

	function subtractRange (arg0_range, arg1_number) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var number = arg1_number;

		//Apply operator
		range[0] -= number;
		range[1] -= number;

		//Return statement
		return range.sort();
	}

	function subtractRanges (arg0_range, arg1_range) {
		//Convert from parameters
		var range = getRange(arg0_range);
		var ot_range = getRange(arg1_range);

		//Apply operator
		range[0] -= ot_range[0];
		range[1] -= ot_range[1];

		//Return statement
		return range.sort();
	}
}

//KEEP AT BOTTOM! Initialise function aliases
{
	modifyRange = addRange;
}