//Set on_page_change EventHandler
{
	setTimeout(function(){
		global.on_page_change = (global.on_page_change) ? global.on_page_change : {};
		global.on_page_change.debug = function () {
			if (global.debug.log_page_change_info)
				console.log("toggle-page-change-info: Page changed to: " + getCurrentPage());
		}
	}, 100);
}