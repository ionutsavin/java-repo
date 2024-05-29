package org.example;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java org.example.Main <class-name>");
            return;
        }

        String className = args[0];
        int passed = 0, failed = 0;

        for (Method m : Class.forName(className).getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, failed);
    }
}