## Compulsory Component - all requirements

```bash
PS C:\Java\java-repo\Lab12\lab12\src\main\java> javac org\example\Main.java org\example\MyProgram.java
PS C:\Java\java-repo\Lab12\lab12\src\main\java> java -cp . org.example.Main org.example.MyProgram     
Test public static void org.example.MyProgram.m7() failed: java.lang.RuntimeException: Crash 
Test public static void org.example.MyProgram.m3() failed: java.lang.RuntimeException: Boom
Passed: 2, Failed: 2
```

## Homework Component - all requirements

```bash
PS C:\Java\java-repo\Lab12\lab12\src\main\java> java org.example.Main org\example\MyProgram.class       
Class: org.example.MyProgram
public void org.example.MyProgram.m11(int arg0, java.lang.String arg1)
public int org.example.MyProgram.m13(int arg0)
public static void org.example.MyProgram.m17(int[] arg0)
public static void org.example.MyProgram.m10(java.lang.String arg0)
public boolean org.example.MyProgram.m12()
public void org.example.MyProgram.m14()
public static void org.example.MyProgram.m15(double arg0)
public void org.example.MyProgram.m16(boolean arg0)
public static void org.example.MyProgram.m4()
public static void org.example.MyProgram.m9(int arg0)
public static void org.example.MyProgram.m6()
public static void org.example.MyProgram.m5()
public static void org.example.MyProgram.m7()
public static void org.example.MyProgram.m8()
public static void org.example.MyProgram.m2()
public static void org.example.MyProgram.m1()
public static void org.example.MyProgram.m3()
Invoking test method: m11
m11 executed with x = 42 and message = example
Test m11 passed
Invoking test method: m13
m13 executed with x = 42
Test m13 passed
Invoking test method: m17
m17 executed with array = 1 2 3 
Test m17 passed
Invoking test method: m10
m10 executed with message = example
Test m10 passed
Invoking test method: m12
m12 executed
Test m12 passed
Invoking test method: m14
m14 executed
Test m14 passed
Invoking test method: m15
m15 executed with y = 3.14159
Test m15 passed
Invoking test method: m16
m16 executed with flag = true
Test m16 passed
Invoking test method: m9
m9 executed with x = 42
Test m9 passed
Invoking test method: m5
m5 executed
Test m5 passed
Invoking test method: m7
Test m7 failed: java.lang.RuntimeException: Crash
Invoking test method: m1
m1 executed
Test m1 passed
Invoking test method: m3
Test m3 failed: java.lang.RuntimeException: Boom

Test Statistics:
Total tests: 13
Passed tests: 11
Failed tests: 2
Total execution time: 72 ms
PS C:\Java\java-repo\Lab12\lab12\src\main\java> java org.example.Main org\example\                              
Class: org.example.Main
public static void org.example.Main.main(java.lang.String[] arg0) throws java.lang.Exception
private static void org.example.Main.displayMethodPrototypes(java.lang.Class arg0)
private static java.util.List org.example.Main.loadClassesFromDirectory(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
private static java.lang.Object[] org.example.Main.generateMockArguments(java.lang.reflect.Parameter[] arg0)
private static java.util.List org.example.Main.loadClassesFromJar(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
private static java.lang.Class org.example.Main.loadClassFromFile(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
Class: org.example.MyProgram
public static void org.example.MyProgram.m2()
public static void org.example.MyProgram.m1()
public static void org.example.MyProgram.m3()
public void org.example.MyProgram.m11(int arg0, java.lang.String arg1)
public boolean org.example.MyProgram.m12()
public int org.example.MyProgram.m13(int arg0)
public static void org.example.MyProgram.m15(double arg0)
public static void org.example.MyProgram.m10(java.lang.String arg0)
public static void org.example.MyProgram.m17(int[] arg0)
public void org.example.MyProgram.m14()
public void org.example.MyProgram.m16(boolean arg0)
public static void org.example.MyProgram.m6()
public static void org.example.MyProgram.m9(int arg0)
public static void org.example.MyProgram.m5()
public static void org.example.MyProgram.m8()
public static void org.example.MyProgram.m4()
public static void org.example.MyProgram.m7()
Invoking test method: m1
m1 executed
Test m1 passed
Invoking test method: m3
Test m3 failed: java.lang.RuntimeException: Boom
Invoking test method: m11
m11 executed with x = 42 and message = example
Test m11 passed
Invoking test method: m12
m12 executed
Test m12 passed
Invoking test method: m13
m13 executed with x = 42
Test m13 passed
Invoking test method: m15
m15 executed with y = 3.14159
Test m15 passed
Invoking test method: m10
m10 executed with message = example
Test m10 passed
Invoking test method: m17
m17 executed with array = 1 2 3
Test m17 passed
Invoking test method: m14
m14 executed
Test m14 passed
Invoking test method: m16
m16 executed with flag = true
Test m16 passed
Invoking test method: m9
m9 executed with x = 42
Test m9 passed
Invoking test method: m5
m5 executed
Test m5 passed
Invoking test method: m7
Test m7 failed: java.lang.RuntimeException: Crash
Class: org.example.Test

Test Statistics:
Total tests: 13
Passed tests: 11
Failed tests: 2
Total execution time: 69 ms
PS C:\Java\java-repo\Lab12\lab12\src\main\java> java org.example.Main ..\..\..\build\libs\lab12-1.0-SNAPSHOT.jar
Class: org.example.Main
private static java.util.List org.example.Main.loadClassesFromDirectory(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
private static void org.example.Main.displayMethodPrototypes(java.lang.Class arg0)
private static java.lang.Object[] org.example.Main.generateMockArguments(java.lang.reflect.Parameter[] arg0)
private static java.util.List org.example.Main.loadClassesFromJar(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
private static java.lang.Class org.example.Main.loadClassFromFile(java.io.File arg0) throws java.io.IOException, java.lang.ClassNotFoundException
public static void org.example.Main.main(java.lang.String[] arg0) throws java.lang.Exception
Class: org.example.MyProgram
public int org.example.MyProgram.m13(int arg0)
public static void org.example.MyProgram.m17(int[] arg0)
public static void org.example.MyProgram.m10(java.lang.String arg0)
public boolean org.example.MyProgram.m12()
public void org.example.MyProgram.m14()
public static void org.example.MyProgram.m15(double arg0)
public void org.example.MyProgram.m11(int arg0, java.lang.String arg1)
public void org.example.MyProgram.m16(boolean arg0)
public static void org.example.MyProgram.m4()
public static void org.example.MyProgram.m6()
public static void org.example.MyProgram.m7()
public static void org.example.MyProgram.m5()
public static void org.example.MyProgram.m8()
public static void org.example.MyProgram.m9(int arg0)
public static void org.example.MyProgram.m2()
public static void org.example.MyProgram.m1()
public static void org.example.MyProgram.m3()
Invoking test method: m13
m13 executed with x = 42
Test m13 passed
Invoking test method: m17
m17 executed with array = 1 2 3
Test m17 passed
Invoking test method: m10
m10 executed with message = example
Test m10 passed
Invoking test method: m12
m12 executed
Test m12 passed
Invoking test method: m14
m14 executed
Test m14 passed
Invoking test method: m15
m15 executed with y = 3.14159
Test m15 passed
Invoking test method: m11
m11 executed with x = 42 and message = example
Test m11 passed
Invoking test method: m16
m16 executed with flag = true
Test m16 passed
Invoking test method: m7
Test m7 failed: java.lang.RuntimeException: Crash
Invoking test method: m5
m5 executed
Test m5 passed
Invoking test method: m9
m9 executed with x = 42
Test m9 passed
Invoking test method: m1
m1 executed
Test m1 passed
Invoking test method: m3
Test m3 failed: java.lang.RuntimeException: Boom
Class: org.example.Test

Test Statistics:
Total tests: 13
Passed tests: 11
Failed tests: 2
Total execution time: 61 ms

```
