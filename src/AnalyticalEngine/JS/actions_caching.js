//Initialise function
{
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

	function deleteAction (arg0_action_key) {
		//Convert from parameters
		var action_key = arg0_action_key;

		//global.actions should exist
		initialiseUndoRedo();

		//Remove actions
		delete this.actions[action_key];
	}

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