package com.unasat.memorygame.input;

import java.util.Scanner;

public class KeyboardInput {
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        userInput = userInput.trim();

        return userInput;
    }

    public static void pressEnterForMenu() {
        System.out.print("\n\n(press <enter> to return to menu)");
        KeyboardInput.getInput();
    }
}
