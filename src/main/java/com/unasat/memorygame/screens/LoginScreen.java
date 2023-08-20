package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.entities.PlayerEntity;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class LoginScreen implements Screen {
    PlayerRepo playerRepo;

    public LoginScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        clearConsole();

        System.out.println("MEMORY GAME / LOGIN\n");

        while (true) {
            String username, password;

            System.out.print("Username: ");
            username = KeyboardInput.getInput();

            System.out.print("Password: ");
            password = KeyboardInput.getInput();

            PlayerEntity player = playerRepo.authenticatePlayer(username, password);

            if (player == null) {
                System.out.println("Username or password is incorrect! Please try again.\n");
                continue;
            }

            screenManager.setPlayer(player);
            screenManager.switchScreen("menu");
            break;
        }
    }
}
