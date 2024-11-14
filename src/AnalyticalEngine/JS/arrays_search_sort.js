//Initialise functions
{
	function getArrayElements (arg0_array, arg1_options) {
		//Convert from parameters
		var array = arg0_array;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		array = JSON.parse(JSON.stringify(array));
		var comparison_array;
		var return_array = [];

			//Initialise local instance variables
			if (options.in_array) comparison_array = options.in_array;
		if (options.in_set) comparison_array = options.in_set;

		for (var i = 0; i < array.length; i++) {
			//Check if element meets criteria
			var meets_criteria = true;

			//Array condition handling
			if (Array.isArray(array[i])) {
				if (!(
					(options.cardinality == undefined || array[i].length == options.cardinality) &&
					(options.cardinality_greater == undefined || array[i].length > options.cardinality_greater) &&
					(options.cardinality_geq == undefined || array[i].length >= options.cardinality_geq) &&
					(options.cardinality_leq == undefined || array[i].length <= options.cardinality_leq)
				))
					meets_criteria = false;

				//Subarray recursive handler
				if (meets_criteria)
					if (options.recursive)
						array[i] = getArrayElements(array[i], options);
			}
			//Numeric condition handling
			if (typeof array[i] == "number") {
				if (!(
					(options.eq == undefined || array[i] == options.eq) &&
					(options.geq == undefined || array[i] >= options.geq) &&
					(options.less == undefined || array[i] < options.less) &&
					(options.leq == undefined || array[i] <= options.leq) &&
					(options.range == undefined || (array[i] >= options.range[0] && array[i] <= options.range[1])) &&
					(options.not_range == undefined || (array[i] < options.range[0] && array[i] > options.range[1]))
				))
					meets_criteria = false;
			}
			//Generic element handling
			if (!(
				(options.indexes == undefined || indexes.includes(i)) &&
				(options.not_indexes == undefined || !not_indexes.includes(i))
			))
				meets_criteria = false;

			//Check if element is contained within in_array/in_set
			if (comparison_array) {
				var in_other_set = false;
				var stringified_local_element = JSON.stringify(array[i]);

				for (var x = 0; x < comparison_array.length; x++)
					if (stringified_local_element == JSON.stringify(comparison_array[x])) {
						in_other_set = true;
						break;
					}

				if (!in_other_set)
					meets_criteria = false;
			}

			//Push to array if meets_criteria
			if (meets_criteria)
				return_array.push(array[i]);
		}

		//Return statement
		return return_array;
	}

	function getArraySubstring (arg0_array, arg1_string, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var substring = arg1_string;
		var options = (arg2_options) ? arg2_options : {};

		//Initialise options
		if (!options.recursive)
			options.recursive = true;

		//Declare local instance variables
		var array_substring_elements = [];
		var string_substring = JSON.stringify(substring);

		//Iterate over array
		for (var i = 0; i < array.length; i++) {
			if (Array.isArray(array[i])) {
				//Recursively call getArraySubstring().
				if (options.recursive)
					array_substring_elements = appendArrays(array_substring_elements, getArraySubstring(array, substring, options));
			} else {
				if (JSON.stringify(array[i]).includes(substring))
					array_substring_elements.push(array[i]);
			}
		}

		//Return statement
		return array_substring_elements;
	}

	function indexesOf (arg0_array, arg1_index_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var index_array = getList(arg1_index_array);
		var options = (arg2_options) ? arg2_options : {};

		//Declare local instance variables
		var return_array = [];

		//Iterate through each element in index array
		for (var i = 0; i < index_array.length; i++)
			return_array.push((options.return_values) ? array[index_array[i]] : index_array[i]);

		//Return statement
		return return_array;
	}

	function sortArray (arg0_array, arg1_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.mode) options.mode = "descending";

		//Declare local instance variables; functions
		function comparisonFunction (a, b) {
			if (options.mode == "alphabetical") return a.localeCompare(b);
			if (options.mode == "ascending") return a - b;
			if (options.mode == "descending") return b - a;
		}
		function recursiveSort (array, key) {
			array.sort(function(a, b) {
				var a_value = (key) ? getObjectKey(a, key) : a;
				var b_value = (key) ? getObjectKey(b, key) : b;

				//Return statement
				return comparisonFunction(a_value, b_value);
			});
			if (options.recursive)
				array.forEach(function(item) {
					if (typeof item == "object") recursiveSort(item, key);
				});
		}

		//Perform sorting
		recursiveSort(array, options.sort_key);

		//Return statement
		return array;
	}
}