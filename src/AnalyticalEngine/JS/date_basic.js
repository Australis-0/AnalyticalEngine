//Initialise global variables
{
	var bc_leap_years = [-45, -42, -39, -36, -33, -30, -27, -24, -21, -18, -15, -12, -9];
	var days_in_months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 30, 31];
	var lowercase_months = ["january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"];
	var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
}

//Initialise functions
{
	function daysInMonths (arg0_date_object) {
		//Convert from parameters
		var date = convertTimestampToDate(arg0_date_object);

		//Declare local instance variables
		var days = 0;

		//Iterate over elapsed months
		for (var i = 0; i < date.month - 1; i++)
			days += days_in_months[i];
		if (isLeapYear(date.year) && date.month >= 2)
			days++;

		//Return statement
		return days;
	}

	function getBlankDate () {
		//Return statement
		return { year: 0, month: 0, day: 0, hour: 0, minute: 0 };
	}

	function getDateString (arg0_date_object, arg1_format) {
		//Convert from parameters
		var date = arg0_date_object;
		var format = (arg1_format) ? arg1_format : "DD Month YYYY";

		//Declare local instance variables
		var day = padZero(date.day);
		var hour = padZero(date.hour);
		var minute = padZero(date.minute);
		var month = months[getMonth(date.month)];
		var year = date.year;

		//Generate the formatted date string based on the requested format.
		if (format == "DD MM YYYY") return day + " " + padZero(date.month) + " " + year;
		if (format == "DD-MM-YYYY") return day + "-" + padZero(date.month) + "-" + year;
		if (format == "DD/MM/YYYY") return day + "/" + padZero(date.month) + "/" + year;
		if (format == "DD Month YYYY") return day + " " + month + " " + year;
		if (format == "DD Month, YYYY") return day + " " + month + ", " + year;
		if (format == "HH:MM") return hour + ":" + minute;
		if (format == "MM DD YYYY") return month + " " + day + " " + year;
		if (format == "MM-DD-YYYY") return month + "-" + day + "-" + year;
		if (format == "MM/DD/YYYY") return month + "/" + day + "/" + year;
		if (format == "Month DD YYYY") return month + " " + day + " " + year;
		if (format == "Month DD, YYYY") return month + " " + day + ", " + year;
		if (format == "YYYY-MM-DD") return year + "-" + padZero(date.month) + "-" + day;
	}

	function getMonth (arg0_string) {
		//Convert from parameters
		var string = arg0_string.toLowerCase();

		//Declare local instance variables
		var lowercase_months = ["january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"];

		var local_month = lowercase_months.indexOf(string) + 1;

		if (!local_month)
			for (var i = 0; i < lowercase_months.length; i++)
				if (lowercase_months[i].includes(string)) {
					local_month = i + 1;
					break;
				}
		if (!local_month) local_month = 1;

		//Return statement
		return local_month;
	}

	function getStandardYear (arg0_date_object) {
		//Convert from parameters
		var date = convertTimestampToDate(arg0_date_object);

		//Return statement
		return date.year;
	}

	function getTimestamp (arg0_date_object) {
		//Convert from parameters
		var date = convertTimestampToDate(arg0_date_object);

		//Guard clause
		if (typeof date == "string") {
			if (date.startsWith("t")) return date;
			date = parseInt(date);
		}
		if (!isNaN(parseInt(date))) return date;

		//Declare local instance variables
		var is_leap_year = isLeapYear(date.year);
		var leap_years = leapYearsBefore(date.year);
		var year_minutes = (leap_years*366 + (date.year - leap_years)*365)*24*60;

		var timestamp_number = Math.floor(returnSafeNumber(year_minutes) +
			returnSafeNumber(daysInMonths(date)*24*60) +
			returnSafeNumber(date.day*24*60) +
			returnSafeNumber(date.hour*60) +
			returnSafeNumber(date.minute));
		timestamp_number = alphabetiseNumber(timestamp_number);

		//Return statement
		return ((timestamp_number >= 0) ? "tz" : "t") + "_" + timestamp_number;
	}

	function isLeapYear (arg0_year, arg1_hanseceltican_standard) {
		//Convert from parameters
		var year = parseInt(arg0_year);
		var hanseceltican_standard = arg1_hanseceltican_standard;

		//BC guard clause
		if (global.bc_leap_years && hanseceltican_standard)
			if (global.bc_leap_years.indexOf(year) != -1)
				return true;

		//Return statement
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0) && year != 4);
	}

	function leapYearsBefore (arg0_year) {
		//Convert from parameters
		var year = parseInt(arg0_year);

		//Decrement year
		year--;

		//Return statement
		return (year/4) - (year/100) + (year/400) - 1; //4AD was not a leap year
	}

	function leapYearsBetween (arg0_start_year, arg1_end_year) {
		//Convert from parameters
		var start_year = arg0_start_year;
		var end_year = arg1_end_year;

		//Return statement
		return leapYearsBefore(end_year) - leapYearsBefore(start_year + 1);
	}

	function monthsFromDays (arg0_date_object) {
		//Convert from parameters
		var date = convertTimestampToDate(arg0_date_object);

		//Declare local variables
		var local_days_in_months = JSON.parse(JSON.stringify(days_in_months));
		var months = 0;

		//Leap year handling
		if (isLeapYear(date.year)) local_days_in_months[1] = 29;

		//Count number of months
		for (var i = 0; i < local_days_in_months.length; i++) {
			date.day -= local_days_in_months[i];

			if (date.day >= 0) months++;
		}

		//Return statement
		return months + 1;
	}

	function padZero (arg0_value) {
		return (arg0_value < 10) ? "0" + arg0_value : arg0_value;
	}

	function parseYears (arg0_number, arg1_current_year) {
		//Convert from parameters
		var years_elapsed = arg0_number;
		var current_year = arg1_current_year;

		//Declare local instance variables
		var is_leap_year = (current_year) ? isLeapYear(current_year) : false;
		var time_elapsed = {
			hour: 0,
			day: 0,
			month: 0,
			year: 0
		};

		var days_in_months = (!is_leap_year) ?
			[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] :
			[31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		//Take care of the year field first
		time_elapsed.year = Math.floor(years_elapsed);
		years_elapsed -= Math.floor(years_elapsed);

		//How many days has it been?
		time_elapsed.day = Math.floor(
			years_elapsed*((!is_leap_year) ? 365 : 366)
		);
		years_elapsed -= time_elapsed.day/((!is_leap_year) ? 365 : 366);

		//How many months has it been?
		for (var i = 0; i < days_in_months.length; i++)
			if (time_elapsed.day >= days_in_months[i]) {
				time_elapsed.day -= days_in_months[i];
				time_elapsed.month++;
			}

		//How many hours has it been?
		time_elapsed.hour = years_elapsed*((!is_leap_year) ? 365*24 : 366*24);

		//Return statement
		time_elapsed.days_in_months = days_in_months;

		//Return statement
		return time_elapsed;
	}
}

//KEEP AT BOTTOM! - Initialise aliases
{
	uppercase_months = months;
}