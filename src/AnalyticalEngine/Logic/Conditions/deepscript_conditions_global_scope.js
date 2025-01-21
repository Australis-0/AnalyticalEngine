//Initialise functions
{
	function anyCivilisationDoesNotExist (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Iterate over civ tags to see which ones do not exist
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_tag = getCivilisation(civ_tags[i]);

			if (local_civ_tag.getNumOfProvinces() <= 0)
				//Return statement
				return true;
		}
	}

	function anyCivilisationExists (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Iterate over civ tags to see which ones do exist
		for (var i = 0; i < civ_tags.length; i++) {
			var local_civ_tag = getCivilisation(civ_tags[i]);

			if (local_civ_tag.getNumOfProvinces() > 0)
				//Return statement
				return true;
		}
	}

	function civilisationDoesNotExist (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Return statement
		return (!anyCivilisationExists(civ_tags));
	}

	function civilisationExists (arg0_civ_tags) {
		//Convert from parameters
		var civ_tags = getList(arg0_civ_tags);

		//Return statement
		return (!anyCivilisationDoesNotExist(civ_tags));
	}

	function isDate (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Declare local instance variables
		var current_date = getCurrentDate();
		var date_string = string.split("."); //DD.MM.YYYY format

		//Return statement
		if (
			parseInt(date_string[0]) == current_date.day &&
			parseInt(date_string[1]) == current_date.month &&
			parseInt(date_string[2]) == current_date.year
		)
			return true;
	}

	function isDateGreaterThan (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Ddeclare local instance variables
		var current_date = getCurrentDate();
		var current_date_timestamp_int = convertTimestampToInt(getTimestamp(current_date));
		var date_string = string.split(".");
		var date_timestamp_int = convertTimestampToInt(getTimestamp({
			year: parseInt(date_string[2]),
			month: parseInt(date_string[1]),
			day: parseInt(date_string[0])
		}));

		//Return statement
		if (current_date_timestamp_int > date_timestamp_int)
			return true;
	}

	function isDateGEQ (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Ddeclare local instance variables
		var current_date = getCurrentDate();
		var current_date_timestamp_int = convertTimestampToInt(getTimestamp(current_date));
		var date_string = string.split(".");
		var date_timestamp_int = convertTimestampToInt(getTimestamp({
			year: parseInt(date_string[2]),
			month: parseInt(date_string[1]),
			day: parseInt(date_string[0])
		}));

		//Return statement
		if (current_date_timestamp_int >= date_timestamp_int)
			return true;
	}

	function isDateLEQ (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Ddeclare local instance variables
		var current_date = getCurrentDate();
		var current_date_timestamp_int = convertTimestampToInt(getTimestamp(current_date));
		var date_string = string.split(".");
		var date_timestamp_int = convertTimestampToInt(getTimestamp({
			year: parseInt(date_string[2]),
			month: parseInt(date_string[1]),
			day: parseInt(date_string[0])
		}));

		//Return statement
		if (current_date_timestamp_int <= date_timestamp_int)
			return true;
	}

	function isDateLessThan (arg0_string) {
		//Convert from parameters
		var string = arg0_string;

		//Ddeclare local instance variables
		var current_date = getCurrentDate();
		var current_date_timestamp_int = convertTimestampToInt(getTimestamp(current_date));
		var date_string = string.split(".");
		var date_timestamp_int = convertTimestampToInt(getTimestamp({
			year: parseInt(date_string[2]),
			month: parseInt(date_string[1]),
			day: parseInt(date_string[0])
		}));

		//Return statement
		if (current_date_timestamp_int < date_timestamp_int)
			return true;
	}

	function isHour (arg0_number) {
		//Convert from parameters
		var number = arg0_number;

		//Declare local instance variables
		var current_date = getCurrentDate();

		//Return statement
		if (current_date.hour == number)
			return true;
	}

	function isHourGreaterThan (arg0_number) {
		//Convert from parameters
		var number = arg0_number;

		//Declare local instance variables
		var current_date = getCurrentDate();

		//Return statement
		if (current_date.hour > number)
			return true;
	}

	function isHourGEQ (arg0_number) {
		//Convert from parameters
		var number = arg0_number;

		//Declare local instance variables
		var current_date = getCurrentDate();

		//Return statement
		if (current_date.hour >= number)
			return true;
	}

	function isHourLEQ (arg0_number) {
		//Convert from parameters
		var number = arg0_number;

		//Declare local instance variables
		var current_date = getCurrentDate();

		//Return statement
		if (current_date.hour <= number)
			return true;
	}

	function isHourLessThan (arg0_number) {
		//Convert from parameters
		var number = arg0_number;

		//Declare local instance variables
		var current_date = getCurrentDate();

		//Return statement
		if (current_date.hour < number)
			return true;
	}
}