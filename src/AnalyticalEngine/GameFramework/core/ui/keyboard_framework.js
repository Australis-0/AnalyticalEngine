//Initialise functions
{
	/**
	 * openKeyboardPrompt() - Opens a given keyboard prompt and writes the return variable to global[<key>].
	 * @param {String} arg0_variable_key - The global.<key> name to write the input string to.
	 * @param {Object} [arg1_options]
	 * @param {String} [arg1_options.placeholder=""] - The placeholder string to use.
	 * @param {Function} [arg1_options.special_function] - The special function to invoke upon saving the keyboard prompt. (e) feeds in string. Undefined by default.
	 * @param {Function} [arg1_options.update_function] - The function to run upon key events.
	 */
	function openKeyboardPrompt (arg0_variable_key, arg1_options) {
		//Convert from parameters
		var variable_key = arg0_variable_key;
		var options = (arg1_options) ? arg1_options : {};

		//Set local constants
		Keyboard.keyboardMode = true;
		Keyboard.keyboardMessage = (options.placeholder) ? options.placeholder : "";
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

				if (options.update_function)
					options.update_function(JSON.parse(JSON.stringify(Keyboard.keyboardMessage)));
			},

			delete: function () {
				if (Keyboard.keyboardMessage.length() > 1) {
					Keyboard.keyboardMessage = Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1);
				} else {
					Keyboard.keyboardMessage = "";
				}

				if (options.update_function)
					options.update_function(JSON.parse(JSON.stringify(Keyboard.keyboardMessage)));
			},

			save: function () {
				global[variable_key] = JSON.parse(JSON.stringify(Keyboard.keyboardMessage));
				if (options.special_function)
					options.special_function(global[variable_key]);

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