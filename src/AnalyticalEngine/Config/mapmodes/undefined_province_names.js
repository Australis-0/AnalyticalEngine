config.mapmodes.undefined_province_names = {
	name: "Undefined Province Names",

	live_update: true,
	separate_mapmode: true,
	special_function: function (arg0_province_obj) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_obj);

		//Declare local instance variables
		var has_province_name = false;
		var province_colour = undefined;
		var province_id = province_obj.getProvinceID();

		//Check has_province_name
		//Set the province colour to red if a corresponding city object doesn't exist.
		if (province_obj.getProvinceName() == "Unnamed")
			province_colour = [255, 0, 0, 0.5];

		//[WIP] - Deprecated code as it doesn't account for all Unnamed province names.
		/*
		if (main.map.cities)
			has_province_name = (getCity(province_id)) ? true : false;
		if (!has_province_name)
			province_colour = [255, 0, 0, 0.5];
		//Set the province colour to yellow if the province is named 'Unnamed'.
		if (province_obj.getProvinceName() == "Unnamed" && has_province_name)
			province_colour = [255, 255, 0, 0.5];
		 */

		//Return statement
		return province_colour;
	}
};