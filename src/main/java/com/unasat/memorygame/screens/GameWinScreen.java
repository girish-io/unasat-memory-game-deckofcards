package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class GameWinScreen implements Screen {
    PlayerRepo playerRepo;

    public GameWinScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        clearConsole();
        int lastScore = playerRepo.getLastScore(screenManager.getPlayer());
        int highScore = playerRepo.getHighScore(screenManager.getPlayer());
        System.out.println("YOU WIN!");
        System.out.println("YOUR SCORE: " + lastScore);
        System.out.println("HIGH SCORE: " + highScore);
        System.out.println("\n");

        KeyboardInput.pressEnterForMenu();
        screenManager.switchScreen("menu");
    }
}
