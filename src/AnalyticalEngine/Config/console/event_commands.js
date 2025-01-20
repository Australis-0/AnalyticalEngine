config.console.event_commands = {
	name: "Event Commands",

	create_dummy_event: {
		name: "create-dummy-event",
		description: "Spawns in a sample dummy event for testing purposes.",
		special_function: function (args) {
			createDummyEvent();
			console.log("Dummy event spawned on-screen.");
		}
	}
};