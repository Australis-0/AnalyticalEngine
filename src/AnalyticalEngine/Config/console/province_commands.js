config.console.province_commands = {
	name: "Province Commands",
	order: 3,

	print_map_cities: {
		name: "print-province",
		description: "Prints information on a province currently on the map.",
		invoke_function: "printProvince",

		arg0_province_id_description: "(Number) - The Province ID for whom to print information.",
		arg1_display_methods_description: "(Boolean) - Optional. Whether to display methods. True by default."
	}
};