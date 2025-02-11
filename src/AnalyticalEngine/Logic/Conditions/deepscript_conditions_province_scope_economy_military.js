//Initialise functions
{
	//Military.
	{
		function provinceArmyIs (arg0_provinces, arg1_options) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var options = (arg1_options) ? arg1_options : {};

			//Initialise options
			options.tag = (options.tag) ? getList(options.tag) : undefined;

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var has_army = false;
				var local_province_armies = getProvinceArmies(provinces[i]);

				//Iterate over all local_province_armies
				for (var x = 0; x < local_province_armies.length; x++) {
					var is_tag_valid = false;

					if (!options.tag) {
						is_tag_valid = true;
					} else if (options.tag.includes(getCurrentTag(local_province_armies[x].civID))) {
						is_tag_valid = true;
					}
				}
			}
		}
	}
}