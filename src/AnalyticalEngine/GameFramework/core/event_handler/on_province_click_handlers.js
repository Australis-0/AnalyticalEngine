//Initialise functions
{
	function initialiseOnProvinceClickHandler () {
		if (!global.on_province_click) global.on_province_click = {};
		global.on_province_click.debug = function (arg0_province_id) {
			//Convert from parameters
			var province_id = arg0_province_id;

			if (global.debug.log_province_info) {
				console.log("Clicked on Province: " + province_id);
				config.console.province_commands.print_province.special_function([province_id, "false"]);
			}
		};
		global.on_province_click.handler = function (arg0_province_id) {
			//Convert from parameters
			var province_id = arg0_province_id;

			//Pass to parseOnProvinceClick()
			parseOnProvinceClick(province_id);
		}
	}
}