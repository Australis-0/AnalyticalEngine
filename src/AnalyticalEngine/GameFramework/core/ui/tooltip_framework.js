//Import classes
{
	this.ArrayList = Java.type("java.util.ArrayList");
	this.MenuElement_Hover = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover");
	this.MenuElement_HoverElement_Type_Text = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text");
	this.MenuElement_HoverElement_Type_FlagCiv_Title = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title");
}

//Initialise functions
{
	/**
	 * createTooltip() - Creates a tooltip as a MenuElement_Hover.
	 * @param {Object} [arg0_options]
	 * @param {String} [arg0_options.id] - Random ID by default.
	 *
	 * @param {arg0_options."input_key"}
	 *  @param {String} [arg0_options."input_key".name]
	 *  @param {String} [arg0_options."input_key".type] - 'text'.
	 *  @param {Array<number, number, number>} [arg0_options.colour=[255, 255, 255]]
	 *  @param {number] [arg0_options.font_weight=400]} - Either 400/700.
	 *
	 * @returns {{id: String, menu_elements: Array<MenuElement_HoverElement>, menu_obj: MenuElement_Hover}}
	 */
	function createTooltip (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_options = Object.keys(options);
		var menu_elements_array_list = new ArrayList();
		var tooltip_obj = {
			id: (options.id) ? options.id : generateRandomID(),
			menu_elements: []
		};

		//Iterate over all_options, and if it is of type object, push it to tooltip_obj.menu_elements
		for (var i = 0; i < all_options.length; i++) {
			var local_element;
			var local_value = options[all_options[i]];

			if (typeof local_value == "object")
				if (local_value.type == "text") {
					local_element = createTooltipText(local_value);
				}

			//Push local_element to tooltip_obj.menu_elements, as well as menu_elements_array_list
			if (local_element) {
				tooltip_obj.menu_elements.push(local_element);
				menu_elements_array_list.add(local_element);
			}
		}

		//Invoke MenuElement_Hover
		tooltip_obj.menu_obj = new MenuElement_Hover(
			menu_elements_array_list //(nElements)
		);

		//Return statement
		return tooltip_obj;
	}

	/**
	 * createTooltipText() - Creates tooltip text as a MenuElement_HoverElement and returns it.
	 * @param {Object} [arg0_options]
	 * @param {String} [arg0_options.name=""]
	 * @param {String} [arg0_options.civilisation=""] - The civilisation flag to have if necessary
	 * @param {Array<number, number, number>} [arg0_options.colour=[255, 255, 255]]
	 * @param {number} [arg0_options.font_weight=400] - Either 400/700.
	 *
	 * @returns {Object<MenuElement_HoverElement>}
	 */
	function createTooltipText (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.name) options.name = "";
		if (!options.colour) options.colour = [255, 255, 255];
			if (options.colour.length == 3)
				options.colour.push(1);
		if (!options.font_weight) options.font_weight = 400;

		//Declare local instance variables
		var actual_colour = convertRGBA(options.colour);
		var actual_font_id = (options.font_weight == 400) ? 0 : 1;
		var tooltip_text_obj;

		if (!options.civilisation) {
			tooltip_text_obj = new MenuElement_HoverElement_Type_Text(
				options.name, //(sText)
				actual_font_id, //(actual_font_id)
				actual_colour //(nColor)
			);
		} else {
			var civilisation_id = getCivilisationID(options.civilisation);

			tooltip_text_obj = new MenuElement_HoverElement_Type_FlagCiv_Title(
				civilisation_id, //(iCivID)
				options.name //(nText2)
			);
		}

		//Return statement
		return tooltip_text_obj;
	}
}