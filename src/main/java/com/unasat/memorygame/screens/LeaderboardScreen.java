package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.entities.ScoreEntity;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.ScoreRepo;

public class LeaderboardScreen implements Screen {
    ScoreRepo scoreRepo;

    public LeaderboardScreen(ScoreRepo scoreRepo) {
        this.scoreRepo = scoreRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        System.out.println("MEMORY GAME / LEADERBOARD\n");

        System.out.println("""
                PLAYER\t\t\t\t\tSCORE
                ------\t\t\t\t\t-----""");

        for (ScoreEntity score : this.scoreRepo.getTop10Scores()) {
            System.out.println(score.getPlayerName() + "\t\t\t\t\t" + score.getScore());
        }

        KeyboardInput.pressEnterForMenu();
        screenManager.switchScreen("menu");
    }
}
