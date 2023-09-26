package com.formacionbdi.springboot.app.config;

public class UtilMath {
    public static int suma(int a, int b) {
        return a + b;
    }

    // a method that recive a string and return a string in capital letters
    public static String capitalizar(String str) {
        return str.toUpperCase();
    }
// a method that recive a string and return a string with the first letter in capital
    public static String capitalizar2(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
