package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;

public class AuthScreen implements Screen {

    @Override
    public void update(ScreenManager screenManager) {
        while (true) {
            System.out.println("""
            MEMORY GAME : WELCOME
            ---------------------
            
                1) I am an existing player
                2) I am a new player
        """);

            System.out.print("(Choose a number) > ");
            String userInput = KeyboardInput.getInput();

            if (userInput.equals("1")) {
                screenManager.switchScreen("login");
                break;
            } else if (userInput.equals("2")) {
                screenManager.switchScreen("signup");
                break;
            }
        }
    }
}
