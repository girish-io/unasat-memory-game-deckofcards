package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.entities.GameEntity;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;

public class GameHistoryScreen implements Screen {
    PlayerRepo playerRepo;

    public GameHistoryScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        System.out.println("MEMORY GAME / GAME HISTORY\n");

        System.out.println("""
                GAME ID\t\t\t\t\tPLAYER\t\t\t\t\tSCORE
                -------\t\t\t\t\t------\t\t\t\t\t-----""");

        for (GameEntity game : this.playerRepo.getPastGames(screenManager.getPlayer())) {
            System.out.println(
                    game.getGameId() + "\t\t\t\t\t" +
                    game.getPlayerName() + "\t\t\t\t\t" +
                    game.getScore());
        }

        KeyboardInput.pressEnterForMenu();
        screenManager.switchScreen("menu");
    }
}
