//Import classes
{

}

config.console.province_commands = {
	name: "Province Commands",
	order: 3,

	print_province: {
		name: "print-province",
		description: "Prints information on a province currently on the map.",

		arg0_province_id_description: "(Number) - The Province ID for whom to print information.",
		arg1_display_methods_description: "(Boolean) - Optional. Whether to display methods. True by default.",
		special_function: function (args) {
			//Declare local instance variables
			var display_methods = false;
				if (args.length >= 2)
					display_methods = (args[1] == "true") ? true : false;

			if (args.length >= 1) {
				var province_obj = getProvince(args[0]);

				if (province_obj) {
					//Format province_string
					var province_string = [];

					var province_owner = Game.getCiv(province_obj.getCivID());
						var display_province_cores = parseList(getProvinceCores(province_obj));
						var display_province_owner = province_owner.getCivName();
						var display_province_owner_tag = province_owner.getCivTag();
						var display_religion = Game.religionManager.getReligion(province_obj.getReligion()).Name;
						var display_resource = ResourcesManager.getResourceName(province_obj.getResourceID());

					province_string.push("Flags - (arg1) Display methods: " + display_methods);
					province_string.push(
						((display_methods) ? "(.iProvinceID) " : "") +
						"Province ID: " + province_obj.iProvinceID);
					province_string.push(
						((display_methods) ? "(.getProvinceName()) " : "") +
						"Province Name: " + province_obj.getProvinceName());
					province_string.push(
						((display_methods) ? "(Game.getCiv(province_obj.getCivID()).getCivName(); Game.getCiv(province_obj.getCivID()).getCivTag(); .getCivID()) " : "") +
						"Owner: " + display_province_owner + " - (Tag: " + display_province_owner_tag + ", ID: " + province_obj.getCivID() + ")");
					province_string.push(
						((display_methods) ? "(getProvinceCores(province_obj)) " : "") +
						"Cores: " + display_province_cores);
					province_string.push(
						((display_methods) ? "(Game.religionManager.getReligion(province_obj.getReligion()).Name; .getReligion()) " : "") +
						"- Religion: " + display_religion + " (ID: " + province_obj.getReligion() + ")");
					province_string.push(
						((display_methods) ? "(.getPopulationTotal()) " : "") +
						"- Population: " + province_obj.getPopulationTotal());

					console.log(province_string.join("\n"));
				} else {
					console.log("No Province with the ID/Name: '" + args[0] + "'.");
				}
			} else {
				console.log("No Province ID provided. Use syntax: print-province [arg0_province_id]");
			}
		}
	},
	toggle_province_info: {
		name: "toggle-province-info",
		description: "Prints on_province_click change information when the event fires.",
		special_function: function (args) {
			global.debug.log_province_info = (!global.debug.log_province_info);
			console.log("Set global.debug.log_province_info to " + global.debug.log_province_info);
		}
	}
};