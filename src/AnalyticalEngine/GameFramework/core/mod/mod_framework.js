//Initialise functions
{
	/**
	 * getAllModDirectories() - Reads all mod directories.
	 *
	 * @returns {Array<String>}
	 */
	function getAllModDirectories () {
		//Declare local instance variables
		var all_mod_directories = [];

		//Iterate over all mod folders
		for (var i = 0; i < SteamManager.modsFoldersSize; i++)
			all_mod_directories.push(SteamManager.modsFolders.get(i));

		//Return statement
		return all_mod_directories;
	}

	/**
	 * getAllModFiles() - Fetches all mod files upon initialisation and their mod.txt file data.
	 * @param {Object} [arg0_options]
	 * @param {boolean} [arg0_options.return_object=false]
	 *
	 * @returns {Array<Object>|Object}
	 */
	function getAllModFiles (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Declare local instance variables
		var all_mod_directories = getAllModDirectories();
		var all_sorted_directories = [];
		var sorted_mod_directories = {};

		//Iterate over all_mod_directories and check for .mod in the base directory
		for (var i = 0; i < all_mod_directories.length; i++) {
			var all_local_files = getAllFilesInFolder(all_mod_directories[i]);
			var has_mod_file = false;

			for (var x = 0; x < all_local_files.length; x++)
				if (all_local_files[x].endsWith("mod.txt"))
					try {
						var local_mod_file_path = all_mod_directories[i] + all_local_files[x];

						var local_mod_file_obj = loadFileAsObject(local_mod_file_path);
						var local_mod_order = (local_mod_file_obj.order) ? local_mod_file_obj.order : 1;

						sorted_mod_directories[all_mod_directories[i]] = {
							file_path: all_mod_directories[i],
							options: local_mod_file_obj,
							order: local_mod_order
						};
						has_mod_file = true;
					} catch (e) {
						console.error(e);
						console.error(e.stack);
					}
			if (!has_mod_file)
				sorted_mod_directories[all_mod_directories[i]] = {
					file_path: all_mod_directories[i],
					options: {},
					order: Infinity
				};
		}

		//Sort sorted_mod_directories
		sorted_mod_directories = sortObjectByKey(sorted_mod_directories, { key: "order", mode: "ascending" });

		//1. Return statement; options.return_object handler
		if (options.return_object) return sorted_mod_directories;

		var all_sorted_mod_directories_keys = Object.keys(sorted_mod_directories);
		var sorted_mod_directories_array = [];

		for (var i = 0; i < all_sorted_mod_directories_keys.length; i++)
			sorted_mod_directories_array.push(sorted_mod_directories[all_sorted_mod_directories_keys[i]]);

		//2. Return statement; !options.return_object handler
		return sorted_mod_directories_array;
	}

	/**
	 * getModLoadOrder() - Returns the current mod load order as an Array<String>
	 * @param {Object} [arg0_options]
	 * @param {Array<Object>} [arg0_options.all_mod_files=getAllModFiles()]
	 *
	 * @returns {Array<String>}
	 */
	function getModLoadOrder (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.all_mod_files) options.all_mod_files = getAllModFiles();

		//Declare local instance variables
		var mod_load_order = [];

		//Iterate over options.all_mod_files
		for (var i = 0; i < options.all_mod_files.length; i++)
			mod_load_order.push(options.all_mod_files[i].file_path);

		//Return statement
		return mod_load_order;
	}

	/**
	 * loadModDeepscript() - Loads any extent DeepScript files in a given folder recursively and pushes them to config.deepscript.
	 * @param {String} arg0_file_path - The file path from which to load .ds files.
	 * @param {Object} [arg1_options] - Optional. Optimisation parameter.
	 *  @param {number} [arg1_options.depth=0]
	 *  @param {Array<String>} [arg1_options.deepscript_file_extensions=[".ds"]]
	 *  @param {String} [arg1_options.mod_folder]
	 */
	function loadModDeepscript (arg0_file_path, arg1_options) {
		//Convert from parameters
		var file_path = arg0_file_path;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		if (!options.depth) options.depth = 0;
		if (!options.deepscript_file_extensions) options.deepscript_file_extensions = [".ds", ".js"];

		//Check if the file_path given is a folder
		try {
			if (isFolder(file_path)) {
				var all_files = getAllFilesInFolder(file_path);

				if (options.depth == 0)
					options.mod_folder = file_path;

				//Iterate over all_files
				for (var i = 0; i < all_files.length; i++) {
					var local_file_path = file_path + all_files[i];

					if (isFolder(local_file_path)) {
						var new_options = JSON.parse(JSON.stringify(options));
						new_options.depth++;

						loadModDeepscript(local_file_path + "/", new_options);
					} else {
						//Check if file ends in one of the file_extensions
						var is_deepscript_file = false;

						for (var x = 0; x < options.deepscript_file_extensions.length; x++)
							if (local_file_path.endsWith(options.deepscript_file_extensions[x]))
								is_deepscript_file = true;

						if (is_deepscript_file)
							try {
								loadModJavascript(local_file_path);
							} catch (e) {
								console.error(e.message);
							}
					}
				}
			} else {
				if (options.depth == 0)
					console.error(file_path + " is not a valid folder.");
			}
		} catch (e) {
			console.error(e.stack);
		}
	}

	function loadModJavascript (arg0_file_path) {
		//Convert from parameters
		var file_path = arg0_file_path;

		//Declare local instance variables
		var script_content = new java.lang.String(Files.readAllBytes(Paths.get(file_path)));

		//Evaluate any content found in script_content
		if (script_content)
			eval(script_content);
	}

	/**
	 * loadMods() - Loads current mod data into config.all_mods[<mod_key>] and config.deepscript[<mod_key>].
	 */
	function loadMods () {
		//Declare local instance variables
		config.all_mods = getAllModFiles();

		//Iterate over config.all_mods
		for (var i = 0; i < config.all_mods.length; i++)
			try {
				//Initialise mod metadata
				var local_deepscript_file_extensions;
					if (config.all_mods[i])
						if (config.all_mods[i].options)
							if (config.all_mods[i].options.deepscript_file_extensions)
								local_deepscript_file_extensions = config.all_mods[i].options.deepscript_file_extensions;
				var local_mod_folder = config.all_mods[i].file_path;

				//Load Deepscript
				config.deepscript[local_mod_folder] = {};
					loadModDeepscript(local_mod_folder, {
						deepscript_file_extensions: local_deepscript_file_extensions,
						mod_folder: local_mod_folder
					});
			} catch (e) {
				console.error(e.stack);
			}
	}
}