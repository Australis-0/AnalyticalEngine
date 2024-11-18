//Initialise function
{
	function convertTimestampToDate (arg0_timestamp) {
		//Convert from parameters
		var timestamp = arg0_timestamp;

		//Guard clause
		if (typeof timestamp == "object") return timestamp;
		if (typeof timestamp == "string") {
			timestamp = timestamp.toString().replace("t_", "").replace("tz_", "");
			timestamp = parseInt(numeriseAlphabet(timestamp));
		}

		//Declare local instance variables
		var local_date = {};

		//Guard clause
		if (typeof timestamp == "object") return timestamp;

		//Calculate years
		local_date.year = Math.floor(timestamp/(365.25*24*60));
		timestamp -= convertTimestampToInt(getTimestamp({ year: local_date.year, month: 0, day: 0, hour: 0, minute: 0 }));

		var leap_years = leapYearsBefore(local_date.year);

		//Calculate months
		var number_of_days = timestamp/(24*60);

		local_date.month = monthsFromDays({ year: local_date.year, day: number_of_days });
		timestamp -= daysInMonths({ year: local_date.year, month: local_date.month })*24*60;

		//Calculate months
		if (local_date.month > 12) {
			local_date.year++;
			local_date.month = 1;
		}

		//Calculate days
		local_date.day = Math.floor(timestamp/(24*60));
		timestamp -= local_date.day*24*60;

		//Calculate hours
		local_date.hour = Math.floor(timestamp/60);
		timestamp -= local_date.hour*60;

		//Remainder as minutes
		local_date.minute = timestamp;

		//Return statement
		return local_date;
	}

	function convertTimestampToInt (arg0_timestamp) {
		//Convert from parameters
		var timestamp = arg0_timestamp;

		//Return statement
		return parseInt(
			numeriseAlphabet(timestamp.toString().replace("t_", "").replace("tz_", ""))
		);
	}
}