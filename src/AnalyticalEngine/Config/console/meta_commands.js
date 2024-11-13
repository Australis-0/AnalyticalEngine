config.console.meta_commands = {
    name: "Meta Commands",
    order: 1,

    console: {
        name: "console",
        description: "Runs Javascript code directly in-console.",
        invoke_function: "parseJavascript"
    },
    exit: {
        name: "exit",
        description: "Exits the current program.",
        invoke_function: "exitGame",

        arg0_exit_code_description: "(Number) - Optional. The exit code to send when quitting the game. 0 by default.",
    },
    help: {
        name: "help",
        description: "Displays all commands.",
        invoke_function: "printHelp"
    },
    print_commands_state: {
        name: "print-commands-variable",
        description: "prints all current commands.",
        invoke_function: "printCommands"
    }
};