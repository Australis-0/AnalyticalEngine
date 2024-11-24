package AnalyticalEngine.Debugger;

import java.io.*;
import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.tools.*;

import AnalyticalEngine.AnalyticalEngine;
import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;
import AnalyticalEngine.Debugger.*;
import AnalyticalEngine.Framework.*;
import AnalyticalEngine.Framework.Java;

import static java.util.Collections.*;

public class console {
    public static HashMap<String, Map<String, Object>> commands = new HashMap<String, Map<String, Object>>();
    public static boolean debugger_enabled = true;

    static class ConsoleFramework {
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

    public static void log (Object arg0_object) {
        //Convert from parameters
        Object object = arg0_object;

        if (debugger_enabled)
            System.out.println("[JAVA] [AnalyticalEngine] " + object);
    }
}
