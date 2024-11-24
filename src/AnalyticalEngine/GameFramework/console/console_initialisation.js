//Initialise functions
{
	function initialiseConsole () {
		//Import classes
		this.BufferedReader = Java.type("java.io.BufferedReader");
		this.InputStreamReader = Java.type("java.io.InputStreamReader");
		this.Runnable = Java.type("java.lang.Runnable");
		this.System = Java.type("java.lang.System");
		this.Thread = Java.type("java.lang.Thread");

		//Create a thread to handle stdin reading
		this.console_thread = new Thread(new Runnable({
			run: function () {
				var reader = new BufferedReader(new InputStreamReader(System.in));

				console.log("Console initialised.");
				try {
					var line;
					while ((line = reader.readLine()) !== null) {
						console.log("> " + line);
						parseConsoleCommand(line);
					}
				} catch (e) {
					console.log("Error reading input: " + e);
				}
			}
		}));

		console_thread.start();
	}
}