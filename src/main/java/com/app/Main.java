package com.app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MyScanner.init();
        new App().run();
        MyScanner.close();
    }
}