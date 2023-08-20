package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class CreditsScreen implements Screen {
    @Override
    public void update(ScreenManager screenManager) {
        clearConsole();

        System.out.println("MEMORY GAME / CREDITS\n");

        System.out.println("""
                        Credits
                        -------
                    
                            * Rajiv Ramsing
                            * Girish Oemrawsingh
                            * Arvan Jagroep
                            * Aditya Dhanes
                            
                        Memory Game - SEM 4 - UNASAT 2023
                """);

        KeyboardInput.pressEnterForMenu();
        screenManager.switchScreen("menu");
    }
}
