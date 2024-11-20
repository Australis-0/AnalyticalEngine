//Initialise function
{
	/**
	 * createAction() - Sets the action performed in the current timeline.
	 * @param {String} arg0_action_key - The key to assign to the action in question.
	 * @param {Object} [arg1_options]
	 * @param {String} [arg1_options.function] - The function key which performs the action
	 * @param {String} [arg1_options.reverse_function] - The function key which reverses the action
	 * @param {*} [arg1_options.'variable_name'] - Other flags to transfer over to the action object.
	 *
	 * @returns {Object<Action>}
	 */
	function createAction (arg0_action_key, arg1_options) {
		//Convert from parameters
		var action_key = arg0_action_key;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables; set action
		var all_options = Object.keys(options);

		initialiseUndoRedo();
		this.actions[action_key] = {
			id: action_key
		};

		//Iterate over all_options
		for (var i = 0; i < all_options.length; i++)
			this.actions[action_key][all_options[i]] = options[all_options[i]];

		//Return statement
		return this.actions[action_key];
	}

	/**
	 * deleteAction() - Deletes an action from the action config.
	 * @param {String} arg0_action_key - The key of the action to delete.
	 *
	 * @returns {Object<Action>}
	 */
	function deleteAction (arg0_action_key) {
		//Convert from parameters
		var action_key = arg0_action_key;

		//global.actions should exist
		initialiseUndoRedo();

		//Remove actions
		delete this.actions[action_key];
	}

	/**
	 * initialiseUndoRedo() - Initialises action config/caching if necessary.
	 */
	function initialiseUndoRedo () {
		if (!this.actions) this.actions = {};
		if (!this.timelines) this.timelines = {};

		if (!this.actions.current_timeline) {
			var current_timeline_id = generateRandomID(this.timelines);

			this.actions.current_index = 0;
			this.actions.current_timeline = current_timeline_id;
			this.actions.initial_timeline = current_timeline_id;
			this.actions.timelines[current_timeline_id] = [];
		}
	}
}