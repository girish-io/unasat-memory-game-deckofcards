package com.unasat.memorygame.screens;

import com.unasat.memorygame.app.Board;
import com.unasat.memorygame.app.Card;
import com.unasat.memorygame.app.Helpers;
import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.input.KeyboardInput;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;

import java.util.ArrayList;
import java.util.List;

import static com.unasat.memorygame.app.Helpers.clearConsole;

public class GameScreen implements Screen {
    Board board;
    List<Card> cardPairs = new ArrayList<>();

    private ScreenManager screenManager;

    int SCORE_MATCHED_PAIR;
    int SCORE_BONUS_MULTIPLIER;
    int MAX_FAILED_ATTEMPTS;

    int score;
    int failedAttempts;

    PlayerRepo playerRepo;

    public GameScreen(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void update(ScreenManager screenManager) {
        this.screenManager = screenManager;

        initializeGame();

        while (true) {
            clearConsole();
            showScore();
            showUsedAttempts();

            System.out.println();

            showBoard();

            System.out.print("\n\n(Choose a card number)> ");
            String userInput = KeyboardInput.getInput();

            if (userInput.equals("quit") || userInput.equals("exit")) {
                screenManager.switchScreen("menu");
                break;
            }

            int cardPosInput;

            try {
                cardPosInput = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                continue;
            }

            if (cardPosInput < 1 || cardPosInput > 20) {
                continue;
            }

            if (this.board.isCardAlreadyOpened(cardPosInput)) {
                System.out.println("Card already opened!");
                continue;
            }

            // Open card
            Card openedCard = this.board.openCard(cardPosInput);

            cardPairs.add(openedCard);

            if (cardPairs.size() == 2) {
                if (cardPairs.get(0).getValue().equals(cardPairs.get(1).getValue())) {
                    clearConsole();
                    System.out.println("Matched card!\n\n");
                    showBoard();
                    Helpers.sleep(2);
                    increaseScore();
                } else {
                    clearConsole();
                    System.out.println("No match!\n\n");

                    showBoard();
                    Helpers.sleep(5);

                    closeUnmatchedCards();
                    failAttempt();
                }

                // Player has reached attempt limit
                if (getFailedAttempts() == this.MAX_FAILED_ATTEMPTS) {
                    endGame();
                    break;
                }

                // All cards are open, the game has been won
                if (this.board.allCardsOpen()) {
                    setScore(clampScore(getScore() + calculateBonusScore()));
                    endGame();
                    break;
                }

                this.cardPairs.clear();
            }
        }
    }

    private void initializeGame() {
        this.SCORE_MATCHED_PAIR = 2;
        this.SCORE_BONUS_MULTIPLIER = 3;

        this.MAX_FAILED_ATTEMPTS = 10;

        this.cardPairs.clear();

        setScore(0);
        setFailedAttempts(0);

        createBoard();
    }

    private void showScore() {
        System.out.println("SCORE: " + this.getScore());
    }

    private void showUsedAttempts() {
        System.out.println("ATTEMPTS USED: " + getFailedAttempts() + "/" + this.MAX_FAILED_ATTEMPTS);
    }

    private void showBoard() {
        System.out.println(this.board);
    }

    private void createBoard() {
        String[] cards = {
                "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J"
        };

        this.board = new Board(cards, 5);
        this.board.shuffle();
    }

    private int getScore() {
        return this.score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    private int getFailedAttempts() {
        return this.failedAttempts;
    }

    private void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    private void increaseScore() {
        setScore(getScore() + this.SCORE_MATCHED_PAIR);
    }

    private int calculateBonusScore() {
        return (getScore() - getFailedAttempts()) * this.SCORE_BONUS_MULTIPLIER;
    }

    private void failAttempt() {
        setFailedAttempts(getFailedAttempts() + 1);
    }

    private int clampScore(int score) {
        return Math.max(score, 0);
    }

    private void endGame() {
        playerRepo.createScore(this.screenManager.getPlayer(), this.getScore());

        if (this.board.allCardsOpen()) {
            // Game has been won
            System.out.println("YOU WON!");
            this.screenManager.switchScreen("gameWin");
        } else {
            // Game has been lost
            System.out.println("YOU LOST!");
            this.screenManager.switchScreen("gameOver");
        }
    }

    public void closeUnmatchedCards() {
        for (Card card : this.cardPairs) {
            this.board.closeCard(card.getPosition());
        }
    }
}
