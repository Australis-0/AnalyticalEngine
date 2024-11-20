//Initialise functions
{
	/**
	 * addRange()/modifyRange() - Adds a number to a range.
	 * @param {Array<number, number>} arg0_range - The range to add to.
	 * @param {number} arg1_number - The number to add to the range.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * addRanges() - Adds a range by another.
	 * @param {Array<number, number>} - The 1st range.
	 * @param {Array<number, number>} - The 2nd range.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * calculateNumberInRange() - Returns a given number within a range once standardised to its min, max potential.
	 * @param {Array<number, number>} arg0_range - The [min, max] bound range under which current values are valid.
	 * @param {number} arg1_number - The actual number for which to calculate between this range.
	 * @param {String} arg2_value_equation - The value equation which to use in these calculations for adjusted-iputs. Here VALUE represents arg1_number.
	 *
	 * @returns {number}
	 */
	function calculateNumberInRange(arg0_range, arg1_number, arg2_value_equation) {
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

	/**
	 * divideRange() - Divides a range by another.
	 * @param {Array<number, number>} arg0_range - The range to pass.
	 * @param {number} arg1_number - The number to divide by.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * divideRnages() - Divides a range by another.
	 * @param {Array<number, number>} arg0_range - The 1st range to pass.
	 * @param {Array<number, number>} arg1_range - The 2nd range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * exponentiateRange() - Exponentiates a range by another.
	 * @param {Array<number, number>} arg0_range - The range to pass.
	 * @param {number} arg1_power - The number to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * exponentiateRanges() - Exponentiates a range by another.
	 * @param {Array<number, number>} - The 1st range to pass.
	 * @param {Array<number, number>} - The 2nd range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * getMidpoint() - Fetches the midpoint of a range.
	 * @param {Array<number, number>} - The range to pass.
	 *
	 * @returns {number}
	 */
	function getMidpoint (arg0_range) {
		//Convert from parameters
		var range = getRange(arg0_range);

		//Return statement
		return (range[0] + range[1])/2;
	}

	/**
	 * getRange() - Gets a range from a given variable.
	 * @param {*} - The range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * multiplyRange() - Multiplies a range by a number.
	 * @param {Array<number, number>} arg0_range - The range to pass.
	 * @param {number} - The number to pass to the function.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * multiplyRanges() - Multiplies a range by another.
	 * @param {Array<number, number>} - The 1st range to pass.
	 * @param {Array<number, number>} - The 2nd range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * rootRange() - Roots a range by a given number.
	 * @param {Array<number, number>} arg0_range - The range to pass.
	 * @param {number} arg1_root - The number to root a range by.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * rootRanges() - Roots ranges by one another.
	 * @param {Array<number, number>} arg0_range - The 1st range to pass.
	 * @param {Array<number, number>} arg1_range - The 2nd range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * subtractRange() - Subtracts a number from a range.
	 * @param {Array<number, number>} arg0_range - The range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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

	/**
	 * subtractRanges() - Subtracts a range from another.
	 * @param {Array<number, number>} arg0_range - The 1st range to pass.
	 * @param {Array<number, number>} arg1_range - The 2nd range to pass.
	 *
	 * @returns {Array<number, number>}
	 */
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