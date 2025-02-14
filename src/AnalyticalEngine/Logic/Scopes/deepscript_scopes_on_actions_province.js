//Initialise functions
{
	//1. Province-wide Buttons
	{
		function onProvinceRename (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_rename[generateRandomID(main.scopes.on_province_rename)] = local_function;
		}
	}


	//2. Province Menu Buttons
	{
		function onProvinceBuildClick (arg0_function) {
			//Convert from parameters
			var local_function = arg0_function;

			//Declare local instance variables
			main.scopes.on_province_build_click[generateRandomID(main.scopes.on_province_build_click)] = local_function;
		}
	}
}

//Initialise internal helper functions
{

}