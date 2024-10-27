package AnalyticalEngine.Framework;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class Java {
    public static class JavaSourceFromString extends SimpleJavaFileObject {
        //Declare local instance variables
        final String code;

        public JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    public static class MultipleClassLoader extends ClassLoader {
        public MultipleClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                //Load the compiled bytecode from in-memory file manager.
                byte[] class_data = java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(name + ".class")
                );

                //Return statement
                return defineClass(name, class_data, 0, class_data.length);
            } catch (Exception e) {
                return super.findClass(name); //Delegate to parent if current level doesn't work
            }
        }
    }
}