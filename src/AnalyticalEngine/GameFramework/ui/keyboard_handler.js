//Import classes
{
	this.classLoader = classLoader;
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
	this.Gdx = Java.type("com.badlogic.gdx.Gdx");
	this.OnscreenKeyboardType = Java.type("com.badlogic.gdx.Input.OnscreenKeyboardType");
	this.Keyboard = Java.type("aoc.kingdoms.lukasz.jakowski.Keyboard");
}

//Initialise functions
{
	function openKeyboardPrompt (arg0_variable_key, arg1_string) {
		//Convert from parameters
		var variable_key = arg0_variable_key;
		var string = arg1_string;

		//Set local constants
		Keyboard.keyboardMode = true;
		Keyboard.keyboardMessage = string;
		Keyboard.verticalLineTime = CFG.currentTimeMillis;
		Keyboard.keyboardVerticalLine = true;
		Keyboard.keyboardActionType = Keyboard.KeyboardActionType.SEARCH_GAMECIVS;

		//Set keyboard to current UI
		if (!CFG.isDesktop())
			try {
				Gdx.input.setOnscreenKeyboardVisible(true, OnscreenKeyboardType.Default);
			} catch (e) {
				CFG.exceptionStack(e);
			}

		//Handle keyboard functionality
		Keyboard.keyboardAction = new Keyboard.Keyboard_Action({
			actionType: function (arg0_char) {
				//Convert from parameters
				var char = arg0_char;

				//Set variables
				Keyboard.keyboardMessage += char;
			},

			delete: function () {
				if (Keyboard.keyboardMessage.length() > 1) {
					Keyboard.keyboardMessage = Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1);
				} else {
					Keyboard.keyboardMessage = "";
				}
			},

			save: function () {
				global[variable_key] = JSON.parse(JSON.stringify(Keyboard.keyboardMessage));

				//Close keyboard
				Keyboard.keyboardMode = false;
				Keyboard.keyboardMessage = "";
				Keyboard.keyboardVerticalLine = false;
				Keyboard.keyboardActionType = Keyboard.KeyboardActionType.NONE;

				Keyboard.keyboardAction = new Keyboard.Keyboard_Action({
					actionType: function (arg0_char) {},
					delete: function () {},
					save: function () {}
				});

				if (!CFG.isDesktop())
					try {
						Gdx.input.setOnscreenKeyboardVisible(false);
					} catch (e) {
						console.log(e);
					}
			}
		});
	}
}