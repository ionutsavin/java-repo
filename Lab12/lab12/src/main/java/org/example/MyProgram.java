package org.example;

public class MyProgram {
    @Test
    public static void m1() {
        System.out.println("m1 executed");
    }

    public static void m2() {
        System.out.println("m2 executed");
    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
        System.out.println("m4 executed");
    }

    @Test
    public static void m5() {
        System.out.println("m5 executed");
    }

    public static void m6() {
        System.out.println("m6 executed");
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

    public static void m8() {
        System.out.println("m8 executed");
    }

    @Test
    public static void m9(int x) {
        System.out.println("m9 executed with x = " + x);
    }

    @Test
    public static void m10(String message) {
        System.out.println("m10 executed with message = " + message);
    }

    @Test
    public void m11(int x, String message) {
        System.out.println("m11 executed with x = " + x + " and message = " + message);
    }

    @Test
    public boolean m12() {
        System.out.println("m12 executed");
        return true;
    }

    @Test
    public int m13(int x) {
        System.out.println("m13 executed with x = " + x);
        return x * 2;
    }

    @Test
    public void m14() {
        System.out.println("m14 executed");
    }

    @Test
    public static void m15(double y) {
        System.out.println("m15 executed with y = " + y);
    }

    @Test
    public void m16(boolean flag) {
        System.out.println("m16 executed with flag = " + flag);
    }

    @Test
    public static void m17(int[] array) {
        System.out.print("m17 executed with array = ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
