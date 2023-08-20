package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.Helpers;
import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.entities.PlayerEntity;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;


import static com.unasat.memorygame.app.Helpers.clearConsole;

public class EditProfileScreen implements Screen {
    PlayerRepo playerRepo;

    public EditProfileScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        while (true) {
            clearConsole();

            System.out.println("MEMORY GAME / EDIT PROFILE\n");

            PlayerEntity player = screenManager.getPlayer();

            System.out.println("MY PROFILE\n");
            System.out.printf("""
                (1) Player name: %s
                (2) Birthday:    %s
                
                (3) Change password
                
                Total games: %d%n""", player.getPlayerName(), player.getDateOfBirth(), playerRepo.getTotalGames(player));

            System.out.println("\n\nPress <enter> to return to the main menu or enter the number for the field you wish to edit.\n");

            System.out.print("(Edit Profile)> ");

            String userInput = KeyboardInput.getInput();

            switch (userInput) {
                case "1" -> {
                    System.out.print("New username: ");
                    String usernameInput = KeyboardInput.getInput();

                    try {
                        playerRepo.setPlayerName(player, usernameInput);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Helpers.sleep(2);
                        continue;
                    }

                    player = playerRepo.findByPlayerName(usernameInput);
                    screenManager.setPlayer(player);
                    continue;
                }

                case "2" -> {
                    System.out.print("New date of birth (yyyy-mm-dd): ");
                    String birthdayInput = KeyboardInput.getInput();

                    playerRepo.setDateOfBirth(player, birthdayInput);

                    player = playerRepo.findByPlayerName(player.getPlayerName());
                    screenManager.setPlayer(player);
                    continue;
                }

                case "3" -> {
                    System.out.print("Current password: ");
                    String currentPassword = KeyboardInput.getInput();

                    if (currentPassword.equals(player.getPassword())) {
                        System.out.print("New password: ");
                        String newPassword = KeyboardInput.getInput();

                        System.out.print("Re-enter password: ");
                        String retypePassword = KeyboardInput.getInput();

                        if (newPassword.equals(retypePassword)) {
                            try {
                                playerRepo.setPassword(player, newPassword);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                Helpers.sleep(2);
                                continue;
                            }

                            player = playerRepo.findByPlayerName(player.getPlayerName());
                            screenManager.setPlayer(player);
                        } else {
                            System.out.println("Passwords don't match!");
                            Helpers.sleep(2);
                        }
                    } else {
                        System.out.println("Incorrect password.");
                        Helpers.sleep(2);
                    }

                    continue;
                }
            }

            screenManager.switchScreen("menu");
            break;
        }
    }
}
