config.console.meta_commands = {
    name: "Meta Commands",
    order: 1,

    console: {
        name: "console",
        description: "Runs Javascript code directly in-console.",
        special_function: function (args) {
            var return_value = eval(args.join(" "));
            console.log(return_value);
        }
    },
    exit: {
        name: "exit",
        description: "Exits the current program.",
        invoke_function: "exitGame",

        arg0_exit_code_description: "(Number) - Optional. The exit code to send when quitting the game. 0 by default.",
        special_function: function (args) {
            var exit_code = (args[0]) ? parseInt(args[0]) : 0;
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
        description: "prints all current commands.",
        special_function: function (args) {
            console.log(Object.keys(config.all_console_commands));
        }
    }
};