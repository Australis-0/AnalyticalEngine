//Import classes
{
	this.Game_Calendar = Java.type("aoc.kingdoms.lukasz.jakowski.Game_Calendar");
}

//Initialise functions
{
	function getCurrentDate () {
		//Return statement
		return {
			year: Game_Calendar.currentYear,
			month: Game_Calendar.currentMonth,
			day: Game_Calendar.currentDay,
			hour: Game_Calendar.HOUR
		};
	}

	function initialiseOnDateChangeHandler () {
		if (!global.on_date_change) global.on_date_change = {};
		global.on_date_change.logic_loop = function (arg0_date_obj) {
			//Convert from parameters
			var date_obj = arg0_date_obj;

			//Declare local instance variables
			var old_date_obj = date_obj.old_date_obj;
			var new_date_obj = date_obj.new_date_obj;

			//Hour handler
			if (old_date_obj.hour != new_date_obj.hour) {
				//console.log("New hour: ", new_date_obj.hour);
			}

			//Day handler
			if (old_date_obj.day != new_date_obj.day) {
				//console.log("New day: ", new_date_obj.day);
				parseOnGameDailyInterval();
			}

			//Month handler
			if (old_date_obj.month != new_date_obj.month) {
				//console.log("New month: ", new_date_obj.month);
			}

			//Year handler
			if (old_date_obj.year != new_date_obj.year) {
				//console.log("New year: ", new_date_obj.year);
			}
		}

	}
}