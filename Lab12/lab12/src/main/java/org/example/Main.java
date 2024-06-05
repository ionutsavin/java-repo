package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java org.example.Main <path-to-class-or-folder-or-jar>");
            return;
        }

        File file = new File(args[0]);
        List<Class<?>> classes = new ArrayList<>();

        if (file.isFile()) {
            if (file.getName().endsWith(".class")) {
                classes.add(loadClassFromFile(file));
            } else if (file.getName().endsWith(".jar")) {
                classes.addAll(loadClassesFromJar(file));
            }
        } else if (file.isDirectory()) {
            classes.addAll(loadClassesFromDirectory(file));
        } else {
            System.out.println("Invalid input. Please provide a .class file, a directory, or a .jar file.");
            return;
        }

        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;
        long startTime = System.currentTimeMillis();

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz.getName());
            displayMethodPrototypes(clazz);

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Test.class)) {
                    totalTests++;
                    System.out.println("Invoking test method: " + m.getName());
                    try {
                        Object instance = null;
                        if (!Modifier.isStatic(m.getModifiers())) {
                            instance = clazz.getConstructor().newInstance();
                        }
                        Object[] mockArgs = generateMockArguments(m.getParameters());
                        m.invoke(instance, mockArgs);
                        passedTests++;
                        System.out.printf("Test %s passed%n", m.getName());
                    } catch (InvocationTargetException ex) {
                        System.out.printf("Test %s failed: %s %n", m.getName(), ex.getCause());
                        failedTests++;
                    } catch (Exception ex) {
                        System.out.printf("Test %s failed: %s %n", m.getName(), ex);
                        failedTests++;
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("\nTest Statistics:");
        System.out.printf("Total tests: %d%n", totalTests);
        System.out.printf("Passed tests: %d%n", passedTests);
        System.out.printf("Failed tests: %d%n", failedTests);
        System.out.printf("Total execution time: %d ms%n", totalTime);
    }

    private static void displayMethodPrototypes(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType().getTypeName()).append(" ");
            sb.append(clazz.getName()).append(".").append(method.getName()).append("(");

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                sb.append(parameters[i].getType().getTypeName()).append(" ").append(parameters[i].getName());
                if (i < parameters.length - 1) {
                    sb.append(", ");
                }
            }

            sb.append(")");

            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                sb.append(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    sb.append(exceptionTypes[i].getTypeName());
                    if (i < exceptionTypes.length - 1) {
                        sb.append(", ");
                    }
                }
            }

            System.out.println(sb);
        }
    }

    private static Class<?> loadClassFromFile(File file) throws IOException, ClassNotFoundException {
        String absolutePath = file.getAbsolutePath();
        String classPath = absolutePath.substring(absolutePath.indexOf("org"), absolutePath.length() - 6);
        String className = classPath.replace(File.separatorChar, '.');
        URLClassLoader classLoader = new URLClassLoader(new URL[]{file.getParentFile().toURI().toURL()});
        return Class.forName(className, true, classLoader);
    }

    private static List<Class<?>> loadClassesFromDirectory(File directory) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                classes.add(loadClassFromFile(file));
            } else if (file.isDirectory()) {
                classes.addAll(loadClassesFromDirectory(file));
            }
        }
        return classes;
    }

    private static List<Class<?>> loadClassesFromJar(File jarFile) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            URL[] urls = {new URL("jar:file:" + jarFile.getAbsolutePath() + "!/")};
            URLClassLoader classLoader = URLClassLoader.newInstance(urls);

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.').replace(".class", "");
                    classes.add(Class.forName(className, true, classLoader));
                }
            }
        }
        return classes;
    }

    private static Object[] generateMockArguments(Parameter[] parameters) {
        Object[] mockArgs = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> paramType = parameters[i].getType();
            if (paramType == int.class) {
                mockArgs[i] = 42;
            } else if (paramType == boolean.class) {
                mockArgs[i] = true;
            } else if (paramType == byte.class) {
                mockArgs[i] = (byte) 1;
            } else if (paramType == short.class) {
                mockArgs[i] = (short) 2;
            } else if (paramType == long.class) {
                mockArgs[i] = 123456789L;
            } else if (paramType == float.class) {
                mockArgs[i] = 3.14f;
            } else if (paramType == double.class) {
                mockArgs[i] = 3.14159;
            } else if (paramType == char.class) {
                mockArgs[i] = 'A';
            } else if (paramType == String.class) {
                mockArgs[i] = "example";
            } else if (paramType == int[].class) {
                mockArgs[i] = new int[]{1, 2, 3};
            } else {
                mockArgs[i] = null;
            }
        }
        return mockArgs;
    }
}
