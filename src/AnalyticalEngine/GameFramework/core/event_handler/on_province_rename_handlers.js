//Initialise functions
{
	function initialiseOnProvinceRenameHandler () {
		if (!global.on_province_rename) global.on_province_rename = {};
		global.on_province_rename.handler = function (arg0_options) {
			//Convert from parameters
			var options = (arg0_options) ? arg0_options : {};

			//Declare local instance variables
			var old_name = options.old_name;
			var new_name = options.new_name;
			var province_id = options.province_id;

			//Pass to parseOnProvinceRename()
			parseOnProvinceRename(province_id, old_name, new_name);
		}
	}

	function getSelectedProvince () {
		//Declare local instance variables
		var current_province_id = Game.iActiveProvince;
		var current_province_obj = getProvince(current_province_id);

		//Return statement
		return current_province_obj;
	}
}