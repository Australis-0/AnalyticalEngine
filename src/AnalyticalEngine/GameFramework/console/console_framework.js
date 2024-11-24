//Initialise parsing functions
{
    function getAllConsoleCommands (arg0_options) {
        //Convert from parameters
        var options = (arg0_options) ? arg0_options : {};

        //Declare local instance variables
        var flattened_console_commands = (config.flattened_console_commands) ?
            config.flattened_console_commands : dumbFlattenObject(config.console);
        var return_console_commands = [];
        var return_keys = [];

        //Iterate over all_flattened_console_commands
        var all_flattened_console_commands = Object.keys(flattened_console_commands);

        for (var i = 0; i < all_flattened_console_commands.length; i++)
            if (!config.console_reserved_keys.includes(all_flattened_console_commands[i])) {
                return_console_commands.push(flattened_console_commands[all_flattened_console_commands[i]]);
                return_keys.push(all_flattened_console_commands[i]);
            }

        //Return statement
        return (!options.return_keys) ? return_console_commands : return_keys;
    }

    function getConsoleCommand (arg0_name, arg1_options) {
        //Convert from parameters
        var name = arg0_name;
        var options = (arg1_options) ? arg1_options : {};

        //Guard clause for objects; direct keys
        if (typeof name == "object") return name;
        if (config.flattened_console_commands[name]) return (!options.return_key) ? config.flattened_console_commands[name] : name;

        //Declare local instance variables
        var console_command_exists = [false, ""]; //[console_command_exists, console_command_key];
        var search_name = name.toLowerCase().trim();

        //ID search - soft search 1st, hard search 2nd
        {
            //Iterate over config.all_console_commands
            for (var i = 0; i < config.all_console_commands.length; i++) {
                var local_value = config.all_console_commands[i];

                if (local_value.id.toLowerCase().includes(search_name))
                    console_command_exists = [true, local_value.key];
            }
            for (var i = 0; i < config.all_console_commands.length; i++) {
                var local_value = config.all_console_commands[i];

                if (local_value.id.toLowerCase() == search_name)
                    console_command_exists = [true, local_value.key];
            }
        }

        //Name search - soft search 1st, hard search 2nd
        {
            //Iterate over config.all_brush_actions
            for (var i = 0; i < config.all_console_commands.length; i++) {
                var local_value = config.all_console_commands[i];

                if (local_value.name)
                    if (local_value.name.toLowerCase().includes(search_name))
                        console_command_exists = [true, local_value.key];
            }
            for (var i = 0; i < config.all_console_commands.length; i++) {
                var local_value = config.all_console_commands[i];

                if (local_value.name)
                    if (local_value.name.toLowerCase() == search_name)
                        console_command_exists = [true, local_value.key];
            }
        }

        //Return statement
        if (console_command_exists[0])
            return (!options.return_key) ? config.flattened_console_commands[console_command_exists[1]] : console_command_exists[1];
    }

    function getConsoleCommandsAtOrder (arg0_options) {
        //Convert from parameters
        var options = (arg0_options) ? arg0_options : {};

        //Declare local instance variables
        var flattened_console_commands = config.flattened_console_commands;
        var order = (options.order != undefined) ? options.order : 1;
        var return_commands = [];
        var return_obj = {};
        var return_keys = [];

        //Iterate over all_flattened_console_commands
        var all_flattened_console_commands = Object.keys(flattened_console_commands);

        for (var i = 0; i < all_flattened_console_commands.length; i++) {
            var local_console_command = flattened_console_commands[all_flattened_console_commands[i]];

            if (local_console_command.order == options.order) {
                return_commands.push(local_console_command);
                return_keys.push(all_flattened_console_commands[i]);
            }
        }

        //options.return_object handler
        if (options.return_object) {
            for (var i = 0; i > return_commands.length; i++)
                return_obj[return_keys[i]] = return_commands[i];
            //Return statement
            return return_obj;
        }

        //Return statement
        return (!options.return_key) ? return_commands : return_keys;
    }

    function getConsoleCommandsCategory (arg0_name, arg1_options) {
        //Convert from parameters
        var name = arg0_name;
        var options = (arg1_options) ? arg1_options : {};

        //Guard clause for objects; direct keys
        if (typeof name == "object") return name;
        if (config.console[name]) return (!options.return_key) ? config.console[name] : name;

        //Declare local instance variables
        var all_console_commands = Object.keys(config.console);
        var console_commands_exists = [false, ""]; //[console_command_exists, console_command_key];
        var search_name = name.toLowerCase().trim();

        //ID search - soft search 1st, hard search 2nd
        {
            //Iterate over all_console_commands
            for (var i = 0; i < all_console_commands.length; i++)
                if (all_console_commands[i].toLowerCase().includes(search_name))
                    console_commands_exists = [true, all_console_commands[i]];
            for (var i = 0; i < all_console_commands.length; i++)
                if (all_console_commands[i].toLowerCase() == search_name)
                    console_commands_exists = [true, all_console_commands[i]];
        }

        //Name search - soft search 1st, hard search 2nd
        {
            //Iterate over all_console_commands
            for (var i = 0; i < all_console_commands.length; i++) {
                var local_value = config.console[all_console_commands[i]];

                if (local_value.name)
                    if (local_value.name.toLowerCase().includes(search_name))
                        console_commands_exists = [true, all_console_commands[i]];
            }
            for (var i = 0; i < all_console_commands.length; i++) {
                var local_value = config.console[all_console_commands[i]];

                if (local_value.name)
                    if (local_value.name.toLowerCase() == search_name)
                        console_commands_exists = [true, all_console_commands[i]];
            }
        }

        //Return statement
        if (console_commands_exists[0])
            return (!options.return_key) ? config.console[console_commands_exists[1]] : console_commands_exists[1];
    }

    function getConsoleCommandCategoriesInOrder () {
        //Declare local instance variables
        var sorted_commands = sortObjectByKey(config.console, { key: "order" });

        //Return statement
        return sorted_commands;
    }

    function getConsoleCommandsLowestOrder () {
        //Declare local instance variables
        var flattened_console_commands = config.flattened_console_commands;
        var min_order = Infinity;

        //Iterate over all_flattened_console_commands
        var all_flattened_console_commands = Object.keys(flattened_console_commands);

        for (var i = 0; i < all_flattened_console_commands.length; i++) {
            var local_console_command = flattened_console_commands[all_flattened_console_commands[i]];

            if (local_console_command.order != undefined)
                min_order = Math.min(min_order, local_console_command.order);
        }

        //Return statement
        return min_order;
    }

    function parseCommandString (arg0_string) {
        //Convert from parameters
        var string = arg0_string;

        //Declare local instance variables
        var regex = /"([^"]*)"|\\(.)|([^\s\\"]+)/g;

        var matches = string.match(regex) || [];

        //Return statement; clean up matched tokens: remove inverted commas and resolve escapes
        return matches.map(function (token) {
            if (token.startsWith('"') && token.endsWith('"')) {
                //Remove double quotes and resolve escaped characters within them
                return token.slice(1, -1).replace(/\\(["\\])/g, '$1');
            } else if (token.startsWith('\\')) {
                //Remove the backslash for escaped characters
                return token.slice(1);
            }
            return token; //Unquoted (including single quotes treated as normal characters)
        });
    }

    function parseConsoleCommand (arg0_arguments) {
        //Convert from parameters
        var arg = arg0_arguments;

        //Declare local instance variables
        var args = parseCommandString(arg);
        var return_value;

        //Iterate over config.all_console_commands
        for (var i = 0; i < config.all_console_commands.length; i++) {
            var local_command = config.all_console_commands[i];

            if (local_command.name == args[0])
                //Check for .special_function
                if (local_command.special_function) {
                    args.shift();

                    try {
                        return_value = local_command.special_function(args);
                    } catch (e) {
                        console.log(e);
                    }
                }
        }

        //Return statement
        return return_value;
    }

    function printHelp (arg0_page) {
        //Convert from parameters
        var page = (arg0_page) ? Math.max(parseInt(arg0_page), 0) : 0;

        //Declare local instance variables
        var help_string = [];

        //Iterate over config.console_commands by descending order
        var sorted_categories = getConsoleCommandCategoriesInOrder();

        var all_sorted_categories = Object.keys(sorted_categories);

        help_string.push("");
        for (var i = 0; i < all_sorted_categories.length; i++) {
            var local_command_category = config.console[all_sorted_categories[i]];

            //Add local_command_category header to help_string
            help_string.push("");
            help_string.push((local_command_category.name) ? local_command_category.name : all_sorted_categories[i]);
            help_string.push("---");
            help_string.push("");

            //Iterate over all_local_commands
            var all_local_commands = Object.keys(local_command_category);
            console.log("Local commands: " + all_local_commands)

            for (var x = 0; x < all_local_commands.length; x++)
                if (config.console_reserved_keys.indexOf(all_local_commands[x]) == -1) {
                    var display_local_command_arguments = [];
                    var local_command = local_command_category[all_local_commands[x]];
                    var local_command_arguments = [];

                    //Iterate over all_local_command_keys
                    var all_local_command_keys = Object.keys(local_command);

                    for (var y = 0; y < all_local_command_keys.length; y++)
                        if (all_local_command_keys[y].endsWith("_description")) {
                            var parameter_name = all_local_command_keys[y].replace("_description", "");
                            local_command_arguments.push(parameter_name);
                        }
                    for (var y = 0; y < local_command_arguments.length; y++)
                        display_local_command_arguments.push("[" + local_command_arguments[y] + "]");

                    //Push command
                    help_string.push(
                        ((local_command.name) ? local_command.name : all_local_commands[x]) +
                        ((local_command_arguments.length > 0) ? " " + display_local_command_arguments.join(" ") : "") +
                        ((local_command.description) ? " - " + local_command.description : ""));

                    //Push command argument descriptions
                    for (var y = 0; y < local_command_arguments.length; y++)
                        if (local_command[local_command_arguments[y] + "_description"])
                            help_string.push("- " + local_command_arguments[y] + " - " + local_command[local_command_arguments[y] + "_description"]);
                }
        }

        //Print help in console
        console.log(help_string.join("\n"));

        //Return statement
        return help_string;
    }
}