package AnalyticalEngine.Framework;
import java.lang.reflect.*;

import AnalyticalEngine.Debugger.*;

public class Methods {
    public static Object callMethod (String arg0_method_name, Object[] arg1_args)
            throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //Convert from parameters
        String method_name = arg0_method_name;
        Object[] args = (arg1_args != null) ? arg1_args : new Object[0]; //Ensure args isn't null

        //Declare local instance variables
        String[] method_parts = method_name.split("\\.");

        //Process method_name for invocation
        String processed_class_name = "";
            for (int i = 0; i < method_parts.length - 1; i++) {
                if (i > 0) processed_class_name += ".";
                processed_class_name += method_parts[i];
            }
        String processed_method_name = method_parts[method_parts.length - 1];

        //Try to load the target class, including nested classes internally represented with '$'.
        Class<?> current_class = loadNestedClass(processed_class_name);
        if (current_class == null) throw new ClassNotFoundException("Could not find class: " + processed_class_name); //Guard clause if class can't be found

        //Find target method with suitable parameters
        Method current_method = null;

        for (Method local_method : current_class.getDeclaredMethods())
            if (local_method.getName().equals(processed_method_name))
                //Match methods with zero parameters or non-zero parameters
                if (
                    (args.length == 0 && local_method.getParameterCount() == 0) ||
                    (args.length == local_method.getParameterCount())
                ) {
                    current_method = local_method;
                    break;
                }
        if (current_method == null) throw new NoSuchMethodException("No suitable method found with name " + method_name + " and " + args.length + " parameters."); //Guard clause if method can't be found
        current_method.setAccessible(true); //Set method to be publicly accessible

        //Convert arguments from Object to the required parameter types dynamically if needed
        Object[] parsed_args = new Object[args.length];
        Class<?>[] parameter_types = current_method.getParameterTypes();

        //Iterate over all args
        for (int i = 0; i < args.length; i++)
            parsed_args[i] = convertArgument(args[i], parameter_types[i]);

        //Return the result of the invoked method (assuming it's static, hence passing null)
        return current_method.invoke(null, parsed_args);
    }

    // Helper method to load a class, considering possible nested classes
    private static Class<?> loadNestedClass (String className) {
        try {
            //Return statement; try loading the class directly first
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //Handle nested classes by replacing the last dot with '$'
            String nestedClassName = replaceLastDotWithDollar(className);
            try {
                //Return statement if class is valid and not internally nested
                return Class.forName(nestedClassName);
            } catch (ClassNotFoundException nestedException) {}
        }
        return null;
    }

    // Helper method to replace the last dot in the class name with a dollar sign
    private static String replaceLastDotWithDollar (String className) {
        //Convert from parameters
        int lastDotIndex = className.lastIndexOf(".");

        if (lastDotIndex == -1) return className; //Guard clause; no dot to replace

        //Return statement
        return className.substring(0, lastDotIndex) + "$" + className.substring(lastDotIndex + 1);
    }

    // Helper method to convert arguments to appropriate types dynamically
    private static Object convertArgument(Object argument, Class<?> targetType) {
        if (argument == null) return null; //Guard clause; no conversion needed
        if (targetType.isInstance(argument)) return argument; //Guard clause; no conversion needed

        //Return statement
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(argument.toString());
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(argument.toString());
        } else if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(argument.toString());
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(argument.toString());
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(argument.toString());
        } else if (targetType == String.class) {
            return argument.toString();
        }

        //Throw error if argument type is not currently supported
        throw new IllegalArgumentException("Unsupported parameter type: " + targetType);
    }
}
