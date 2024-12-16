config.mapmodes.no_government = {
	name: "No Government",

	is_editor_mapmode: true,
	live_update: true,
	separate_mapmode: true,
	special_function: function (arg0_province_obj) {
		//Convert from parameters
		var province_obj = getProvince(arg0_province_obj);

		//Declare local instance variables
		var province_colour = undefined;
		var province_id = province_obj.getProvinceID();
		var province_owner = getProvinceOwner(province_id);

		//Check to see if province_owner has a defined government tag
		if (province_owner) {
			var split_tag = province_owner.split("_");

			if (province_owner) {
				if (split_tag.length == 1) {
					province_colour = [255, 0, 0, 0.5]; //Set to red if no government suffix tag is present.
				} else {
					province_colour = [0, 0, 0, 0]; //Set to transparent if province has a valid government.
				}
			} else {
				province_colour = [255, 255, 0, 0.5]; //Set to yellow if province is wasteland.
			}
		}

		//Return statement
		return province_colour;
	}
};