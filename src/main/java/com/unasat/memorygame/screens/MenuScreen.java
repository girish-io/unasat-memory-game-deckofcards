package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class MenuScreen implements Screen {
    @Override
    public void update(ScreenManager screenManager) {
        clearConsole();

        showMenu();

        System.out.print("(Choose an option)> ");
        String menuOption = KeyboardInput.getInput();

        switch (menuOption) {
            case "1", "play" -> screenManager.switchScreen("game");

            case "2" -> screenManager.switchScreen("leaderboard");

            case "3" -> screenManager.switchScreen("gameHistory");

            case "4" -> screenManager.switchScreen("credits");

            case "5" -> screenManager.switchScreen("editProfileScreen");

            case "99", "exit" -> System.exit(0);
        }

        clearConsole();
    }
    public static void showMenu() {
        System.out.println("""
                    Memory Game
                    -----------
                    
                        1) Start game
                        2) Leaderboard
                        3) Game history
                        4) Credits
                        5) View / Edit profile
                        
                        99) Exit
                """);
    }
}
