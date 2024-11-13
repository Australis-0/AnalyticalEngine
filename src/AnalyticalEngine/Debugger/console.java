package AnalyticalEngine.Debugger;

import java.io.*;
import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;
import javax.tools.*;

import AnalyticalEngine.AnalyticalEngine;
import AnalyticalEngine.Debugger.*;
import AnalyticalEngine.Framework.*;
import AnalyticalEngine.Framework.Java;

import static java.util.Collections.*;

public class console {
    public static HashMap<String, Map<String, Object>> commands = new HashMap<String, Map<String, Object>>();
    public static boolean debugger_enabled = true;

    static class ConsoleFramework {
        /**
            addCommand() - Adds a command to the current AnalyticalEngine debugger.
            @param arg0_command_name (String) - The command name to define.
            @param arg1_args (String[]) - Any further variable keys to assign based on the String[] args passed to the command.
            @param arg2_options (Map<String, Object>)
                <br>- description: (String) - Describes what the command does.
                <br>- function: (String) - The name of the function to call.
                <br>- <key>_description: (String) - Describes an argument for the command, nested below it in the help menu.
        */
        public static void addCommand (String arg0_command_name, String[] arg1_args, Map<String, Object> arg2_options) {
            //Convert from parameters
            String command_name = arg0_command_name;
            String[] args = (arg1_args != null) ? arg1_args : new String[]{};
            Map<String, Object> options = (arg2_options != null) ? arg2_options : new HashMap<>();

            //Declare local instance variables
            options.put("id", command_name);

            options.put("args", args);
            commands.put(command_name, options);
        }

        public static ArrayList<String> getHelpString () {
            //Declare local instance variables
            ArrayList<String> help_string = new ArrayList<String>();

            //Format help_string
            help_string.add("HELP MENU:");
            help_string.add("Console Commands:");
            help_string.add("");

            //Iterate over all commands
            for (Map local_command : commands.values()) {
                String local_args_string = "";
                String local_description_string = (local_command.containsKey("description")) ? " - " + local_command.get("description").toString() : "";

                ArrayList<String> local_args_descriptions = new ArrayList<String>();

                //Initialise local_args_string
                if (local_command.containsKey("args")) {
                    String[] local_args = (String[]) local_command.get("args");
                    for (int i = 0; i < local_args.length; i++) {
                        local_args_string += " <" + local_args[i] + ">";
                        if (local_command.containsKey(local_args[i] + "_description"))
                            local_args_descriptions.add(" - " + local_args[i] + ": " + local_command.get(local_args[i] + "_description"));
                    }
                }

                help_string.add("- " + local_command.get("id") + local_args_string + local_description_string);
                help_string.addAll(local_args_descriptions);
            }

            //Return help_string
            return help_string;
        }

        public static void initialiseCommands () {
            String debugger_prefix = "AnalyticalEngine.Debugger.";

            //console <code>
            Map<String, Object> console_options = new HashMap<>();
                console_options.put("description", "Runs Java code directly in-console.");
                console_options.put("function", debugger_prefix + "console.ConsoleFramework.parseJavaCode");
            addCommand("console", new String[]{"arg0_code"}, console_options);

            //exit <exit_code>
            Map<String, Object> exit_options = new HashMap<>();
                exit_options.put("description", "Exits the current program.");
                exit_options.put("function", debugger_prefix + "Game.exit");
                exit_options.put("arg0_exit_code_description", "(Number) - Optional. The exit code to send when quitting the game. 0 by default.");
            addCommand("exit", new String[]{"arg0_exit_code"}, exit_options);

            //help
            Map<String, Object> help_options = new HashMap<>();
                help_options.put("description", "Displays all commands.");
                help_options.put("function", debugger_prefix + "console.ConsoleFramework.printHelp");
            addCommand("help", null, help_options);

            //print-command-state
            Map<String, Object> print_command_state_options = new HashMap<>();
                print_command_state_options.put("description", "Prints the current commands' HashMap.");
                print_command_state_options.put("function", debugger_prefix + "console.ConsoleFramework.printCommandsVariable");
            addCommand("print-commands-variable", null, print_command_state_options);

            //print-main
            Map<String, Object> print_main_options = new HashMap<>();
            print_main_options.put("description", "Prints the current main object for AnalytiaclEngine.");
            print_main_options.put("function", debugger_prefix + "Main.printMain");
            addCommand("print-main", null, print_main_options);
                //print-main-cities
                Map<String, Object> print_main_cities_options = new HashMap<>();
                    print_main_cities_options.put("description", "Prints the number of currently loaded cities.");
                    print_main_cities_options.put("function", debugger_prefix + "Main.printMainCities");
                addCommand("print-main-cities", null, print_main_cities_options);

            //print-province
            Map<String, Object> print_province_options  = new HashMap<>();
                print_province_options.put("description", "Prints Province information from the current GameState.");
                print_province_options.put("function", debugger_prefix + "GameProvinces.printProvince");
                print_province_options.put("arg0_province_id_description", "(Number) - The Province ID which to get Province information for.");
                print_province_options.put("arg1_display_methods_description", "(Boolean) - Optional. Whether to display methods. True by default.");
            addCommand("print-province", new String[]{"arg0_province_id", "arg1_display_methods"}, print_province_options);

            //set-province-colour
            Map<String, Object> set_province_colour_options = new HashMap<>();
                set_province_colour_options.put("description", "Sets the colour of a province.");
                set_province_colour_options.put("function", debugger_prefix + "GameProvinces.setProvinceColour");
                set_province_colour_options.put("arg0_province_id_description", "(Number) - The Province ID to reference.");
            addCommand("set-province-colour", new String[]{"arg0_province_id", "arg1_r", "arg2_g", "arg3_b", "arg4_a"}, set_province_colour_options);
        }

        public static void printCommandsVariable () {
            commands.forEach((key, value) -> console.log(key + ": " + value));
        }

        public static void parseConsoleCommand (String[] arg0_args) {
            //Convert from parameters
            String[] args = arg0_args;

            //Iterate over all commands
            for (Map local_command : commands.values()) {
                ArrayList<String> local_flags = new ArrayList<String>();
                if (args.length > 1)
                    for (int i = 1; i < args.length; i++)
                        local_flags.add(args[i]);

                if (args[0].equalsIgnoreCase((String) local_command.get("id"))) {
                    if (local_command.containsKey("function")) {
                        try {
                            String local_function = (String) local_command.get("function");
                            Methods.callMethod(local_function, local_flags.toArray());
                        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public static void parseJavaCode (String arg0_code) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
            //Convert from parameters
            String code = arg0_code;

            //Declare local instance variables
            String processed_code = "public class EvalClass { public void main () { " + code + " } }";

            //Set class_options
            String classpath = System.getProperty("java.class.path");
            String classpath_option = "-classpath";
            String classpath_value = classpath.replace(";", File.pathSeparator);

            Iterable<String> class_options = Arrays.asList(classpath_option, classpath_value);

            //Define EvalClass
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            String class_name = "EvalClass";
            JavaFileObject file = new Java.JavaSourceFromString(class_name, processed_code);
            StringWriter writer = new StringWriter();

            StandardJavaFileManager file_manager = compiler.getStandardFileManager(null, null, null);
            List<JavaFileObject> files = Collections.singletonList(file);
            JavaCompiler.CompilationTask task = compiler.getTask(writer, file_manager, null, class_options, null, files);
            boolean is_parsed = task.call();

            if (is_parsed) {
                //Load and execute compiled EvalClass
                Java.MultipleClassLoader custom_class_loader = new Java.MultipleClassLoader(ClassLoader.getSystemClassLoader()); //Capable of handling multiple classes.

                Class<?> eval_class = custom_class_loader.findClass(class_name);
                Object local_instance = eval_class.getDeclaredConstructor().newInstance();
                Method local_method = eval_class.getMethod("main");

                //Invoke main method described
                local_method.invoke(local_instance);
            } else {
                console.log("Compilation failed: " + writer.toString());
            }

            file_manager.close();
        }

        public static void printHelp () {
            //Declare local instance variables
            ArrayList<String> help_string = getHelpString();

            console.log(String.join("\n", help_string));
        }
    }
    static class ToggleDebugger {
        public static void disableDebug () {
            debugger_enabled = false;
        }
        public static void enableDebug () {
            debugger_enabled = true;
        }
        public static void toggleDebug () {
            debugger_enabled = (!debugger_enabled);
        }
    }

    public static void initialise () {
        //Initialise console commands
        ConsoleFramework.initialiseCommands();

        //Initialise separate console thread
        Thread console_thread = new Thread (() -> {
            Scanner input = new Scanner(System.in);

            console.log("Analytical Engine (Console) initialised.");
            while (true) {
                System.out.print("\n[Analytical Engine] > ");
                String input_string = input.nextLine();

                //Regexp pattern to delimit either spaces or quoted arguments.
                Pattern local_delimiter = Pattern.compile("\"([^\"]*)\"|(\\S+)");
                Matcher local_matcher = local_delimiter.matcher(input_string);

                List<String> local_arguments = new ArrayList<>();
                while (local_matcher.find())
                    local_arguments.add((local_matcher.group(1) != null) ? local_matcher.group(1) : local_matcher.group(2));
                String[] processed_args = local_arguments.toArray(new String[0]);

                ConsoleFramework.parseConsoleCommand(processed_args);
            }
        });
        console_thread.start();
    }

    public static void log (Object arg0_object) {
        //Convert from parameters
        Object object = arg0_object;

        if (debugger_enabled)
            System.out.println("[Analytical Engine] " + object);
    }
}
