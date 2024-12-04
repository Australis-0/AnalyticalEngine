config.mapmodes.undefined_province_names = {
	name: "Undefined Province Names",

	live_update: true,
	separate_mapmode: true,
	special_function: function (arg0_province_obj) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_obj);

		//Declare local instance variables
		var province_colour = undefined;
		var province_id = province_obj.getProvinceID();

		var has_city = getCity(province_id);

		//Check has_province_name
		//Set the province colour to red if a corresponding city object doesn't exist.
		if (["Unnamed", ""].includes(province_obj.getProvinceName())) { //[WIP] - Account for all random province names in future.
			province_colour = (!has_city) ?
				[255, 0, 0, 0.5] : //Set to red if city doesn't exist
				[255, 255, 0, 0.5]; //Set to yellow if city exists but is still Unnamed
		}

		//Return statement
		return province_colour;
	}
};