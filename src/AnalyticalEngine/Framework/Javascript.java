package AnalyticalEngine.Framework;

import AnalyticalEngine.AnalyticalEngine;
import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;
import static AnalyticalEngine.AnalyticalEngine.application;
import static aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher.main_thread_tasks;

import AnalyticalEngine.Debugger.console;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javascript {
    public static void executeJS (ScriptEngine arg0_nashorn, String arg1_file_path, String arg2_code) {
        //Convert from parameters
        ScriptEngine engine = arg0_nashorn;
        String file_path = arg1_file_path;
        String script_content = arg2_code;

        //Declare local instance variables
        String script_wrapper = "try { " + script_content + " } catch (e) {" +
                "e.fileName = '" + file_path + "'; " +
                "e.fileStack = e.fileStack || []; " +
                "e.fileStack.push({ fileName: '" + file_path + "', message: e.message, lineNumber: e.lineNumber }); " +
                "throw e; }";

        //Evaluate script with detailed stack tracing
        try {
            engine.eval(script_wrapper);
        } catch (ScriptException e) {
            System.err.println("Javascript error in file: " + file_path);
            System.err.println("Message: " + e.getMessage());

            //Extract line and column if available
            int column_number = e.getColumnNumber();
            int line_number = e.getLineNumber();

            //Enhanced stack trace with JS file context
            System.err.println("Error occurred at line: " + line_number + ", column " + column_number);
            System.err.println("Full JS stack trace:");
            e.printStackTrace();
        }
    }

    public static void executeJSFile (String arg0_file) {
        executeJSFile(arg0_file, AnalyticalEngine().nashorn); }
    public static void executeJSFile(String arg0_file, ScriptEngine arg1_nashorn) {
        //Convert from parameters
        String file_path = arg0_file;
        ScriptEngine engine = arg1_nashorn;

        //Ensure file_path does not start with a leading
        if (file_path.startsWith("/"))
            file_path = file_path.substring(1);

        // Declare local instance variables
        try {
            URL resourceUrl = Javascript.class.getClassLoader().getResource(file_path);

            if (resourceUrl == null)
                throw new FileNotFoundException("ERROR: Resource not found -> " + file_path);

            //Print debugging info
            //Open the input stream correctly based on JAR or FileSystem
            try (InputStream input_stream = resourceUrl.openStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input_stream))) {

                String script_content = reader.lines().collect(Collectors.joining("\n"));
                executeJS(engine, file_path, script_content);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[JAVA] [AnalyticalEngine] " + file_path + " could not be loaded.");
        }
    }

    public static List<String> getAllFiles (String arg0_folder) {
        //Convert from parameters
        String folder = arg0_folder;

        //Declare local instance variables
        List<String> file_paths = new ArrayList<>();
        URL resource = Javascript.class.getResource(folder);

        try {
            if (resource != null) {
                URI uri = resource.toURI();

                if ("file".equals(uri.getScheme())) { //Unpackaged file handling
                    Path path = Paths.get(uri);

                    try (Stream<Path> walk = Files.walk(path)) {
                        file_paths = walk.filter(Files::isRegularFile)
                            .filter(p -> p.toString().endsWith(".js"))
                            .map(p -> folder + "/" + path.relativize(p).toString().replace("\\", "/"))
                            .collect(Collectors.toList());
                    }
                } else if ("jar".equals(uri.getScheme())) { //Internal JAR handling
                    String jar_path = uri.getSchemeSpecificPart().split("!")[0];

                    try (JarFile jar_file = new JarFile(jar_path.substring(jar_path.indexOf("/")))) {
                        file_paths = jar_file.stream().map(JarEntry::getName)
                            .filter(name -> name.startsWith(folder.substring(1)) && name.endsWith(".js"))
                            .collect(Collectors.toList());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Return statement
        return file_paths;
    }

    public static ScriptEngine initialiseJavascript () {
        //Declare local instance variables
        List<String> load_directories = new ArrayList<>();
        List<String> load_files = new ArrayList<>();
        ScriptEngineManager nashorn_manager = new ScriptEngineManager();
        ScriptEngine nashorn = nashorn_manager.getEngineByName("nashorn");

        ClassLoader class_loader = Thread.currentThread().getContextClassLoader();

        //Error handling
        if (nashorn == null) {
            console.log("[ERROR] [FATAL] Nashorn is null! This implies that the wrong JDK has been selected. This has to be selected at runtime to be the bundled JDK that ships with AOC3 by default at src/lib/.");
            console.log("- Java Version: " + System.getProperty("java.version"));
            console.log("- Java Vendor: " + System.getProperty("java.vendor"));
            console.log("- Java Home: " + System.getProperty("java.home"));
        }

        if (nashorn == null) console.log("Nashorn is null.");

        //Imports for nashorn
        new Thread(() -> {
            try {
                console.log("[DEBUG] Waiting for application to be initialised ...");
                while (application == null)
                    Thread.sleep(100);
                nashorn.put("application", application);
                console.log("[DEBUG] 'application' initialised ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        nashorn.put("classLoader", class_loader);
        Consumer<Runnable> run_on_main_thread = (command) -> {
            if (command != null) {
                main_thread_tasks.add(command);
            } else {
                System.err.println("[ERROR] Invalid argument: Expected a Runnable command.");
            }
        };
        nashorn.put("runOnMainThread", run_on_main_thread);

        //Load files as stated in js_file_structure.json
        String file_path = "/AnalyticalEngine/js_file_structure.json";
        Json file_structure_json = new Json();
        HashMap<String, Object> file_structure_obj = null;

        //Use class loader to load file from resources
        try (InputStream input_stream = Javascript.class.getResourceAsStream(file_path)) {
            if (input_stream == null)
                System.err.println("JSON File for loading JS not found: " + file_path);

            //Wrap input_stream with InputStreamReader
            Reader input_reader = new InputStreamReader(input_stream);
            file_structure_obj = file_structure_json.fromJson(HashMap.class, input_reader);
            input_reader.close();

            //Load discrete nashorn_files. These are prioritised over individual directories
            Array<String> nashorn_files = (Array<String>) file_structure_obj.get("files");
            for (String local_file : nashorn_files)
                executeJSFile(local_file, nashorn);

            //Now that directories are loaded into nashorn_directories, iterate over the List and execute .js files inside of them
            Array<String> nashorn_directories = (Array<String>) file_structure_obj.get("directories");
            for (String local_directory : nashorn_directories) {
                List<String> all_local_files = getAllFiles(local_directory);
                for (String local_file : all_local_files)
                    executeJSFile(local_file, nashorn);

                console.log("Loaded " + all_local_files.size() + " files present in " + local_directory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Return statement
        return nashorn;
    }


}
