config.console.province_commands = {
	name: "Province Commands",
	order: 3,

	print_province: {
		name: "print-province",
		description: "Prints information on a province currently on the map.",

		arg0_province_id_description: "(Number) - The Province ID for whom to print information.",
		arg1_display_methods_description: "(Boolean) - Optional. Whether to display methods. True by default.",
		special_function: function (args) {

		}
	}
};