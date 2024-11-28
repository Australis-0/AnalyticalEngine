//Initialise functions
{
	function initialiseOnclickHandler () {
		global.onclick = (global.onclick) ? global.onclick : {};

		global.onclick.menu_handler = function (e) {
			menuHandler(e);
		};
	}
}