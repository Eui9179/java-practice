package com.app;

import java.util.Scanner;

public class MyScanner {
    private static Scanner sc;

    public static void init() {
        sc = new Scanner(System.in);
    }

    public static void close() {
        sc.close();
    }

    public static Scanner getInstance () {
        return sc;
    }
}
