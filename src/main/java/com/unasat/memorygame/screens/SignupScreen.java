package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.entities.PlayerEntity;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class SignupScreen implements Screen {
    PlayerRepo playerRepo;

    public SignupScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        clearConsole();

        System.out.println("MEMORY GAME / SIGN UP\n");

        String username = null, password = null, dateOfBirth = null;

        while (true) {
            if (username == null) {
                System.out.print("Username (max 15 char.): ");
                username = KeyboardInput.getInput();

                if (username.length() > 15) {
                    System.out.println("Username cannot exceed 15 characters!\n");
                    username = null;
                    continue;
                }
            }

            if (password == null) {
                System.out.print("Password: ");
                password = KeyboardInput.getInput();

                if (password.length() > 72) {
                    System.out.println("Password cannot exceed 72 characters!\n");
                    password = null;
                    continue;
                }
            }

            if (dateOfBirth == null) {
                System.out.print("Birthday (yyyy-mm-dd): ");
                dateOfBirth = KeyboardInput.getInput();
            }

            PlayerEntity player = playerRepo.findByPlayerName(username);

            if (player == null) {
                player = playerRepo.createPlayer(username, password, dateOfBirth);
            } else {
                System.out.println("A player with this name already exists. Choose a different name.");
                continue;
            }

            screenManager.setPlayer(player);
            screenManager.switchScreen("login");
            break;
        }
    }
}
