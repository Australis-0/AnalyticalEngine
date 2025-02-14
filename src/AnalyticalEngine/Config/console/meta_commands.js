config.console.meta_commands = {
    name: "Meta Commands",
    order: 1,

    console: {
        name: "console",
        description: "Runs Javascript code directly in-console.",
        special_function: function (args) {
            var return_value = eval(args.join(" "));

            if (return_value != undefined)
                console.log(return_value);
        }
    },
    exit: {
        name: "exit",
        description: "Exits the current program.",

        arg0_exit_code_description: "(Number) - Optional. The exit code to send when quitting the game. 0 by default.",
        special_function: function (args) {
            var exit_code = (args[0]) ? parseInt(args[0]) : 0;

            console.log("Process closed with exit code " + exit_code);
            java.lang.System.exit(exit_code);
        }
    },
    help: {
        name: "help",
        description: "Displays all commands.",
        special_function: function (args) {
            printHelp();
        }
    },
    print_commands_state: {
        name: "print-commands-variable",
        description: "Prints all current commands.",
        special_function: function (args) {
            console.log(Object.keys(config.all_console_commands));
        }
    },
    print_main: {
        name: "print-main",
        description: "Logs the current main variable.",
        special_function: function (args) {
            console.log(Object.keys(main));
        }
    },
    toggle_scopes_info: {
        name: "toggle-scopes-info",
        description: "Prints scopes trigger information when different scope parsing handlers are fired.",
        special_function: function (args) {
            global.debug.log_scopes_info = (!global.debug.log_scopes_info);
            console.log("Set global.debug.log_scopes_info to " + global.debug.log_scopes_info);
        }
    }
};