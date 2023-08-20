package com.unasat.memorygame.app;

import java.util.concurrent.TimeUnit;

/*
 * Helper class and functions
 */
public class Helpers {

    /*
     * Clears the terminal output
     */
    public static void clearConsole() {
        final String os = System.getProperty("os.name");

        ProcessBuilder process;

        if (os.contains("Windows")) {
            process = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
        } else {
            process = new ProcessBuilder("clear").inheritIO();
        }

        try {
            process.start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Waits <x> seconds before continuing execution
     */
    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
