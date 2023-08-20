package com.unasat.memorygame.app;

import com.unasat.memorygame.config.Config;
import com.unasat.memorygame.db.MySqlConnection;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.repositories.PlayerRepo;
import com.unasat.memorygame.repositories.ScoreRepo;
import com.unasat.memorygame.screens.*;


public class Application {
    public static void main(String[] args) {
        Config.initConfig("./memory_game.conf");

        Helpers.clearConsole();

        ScreenManager screenManager = initScreenManager();

        // Game loop
        loop(screenManager);
    }

    public static ScreenManager initScreenManager() {
        MySqlConnection mySqlConnection = null;

        try {
            mySqlConnection = new MySqlConnection(
                    Config.MYSQL_USER,
                    Config.MYSQL_PASS,
                    Config.MYSQL_HOST,
                    Config.MYSQL_DB_NAME
            );
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            System.exit(1);
        }

        PlayerRepo playerRepo = new PlayerRepo(mySqlConnection.getConnection());
        ScoreRepo scoreRepo = new ScoreRepo(mySqlConnection.getConnection());

        Screen authScreen = new AuthScreen();
        Screen loginScreen = new LoginScreen(playerRepo);
        Screen signupScreen = new SignupScreen(playerRepo);
        Screen menuScreen = new MenuScreen();
        Screen gameScreen = new GameScreen(playerRepo);
        Screen gameWinScreen = new GameWinScreen(playerRepo);
        Screen gameOverScreen = new GameOverScreen(playerRepo);
        Screen gameHistoryScreen = new GameHistoryScreen(playerRepo);
        Screen leaderboardScreen = new LeaderboardScreen(scoreRepo);
        Screen editProfileScreen = new EditProfileScreen(playerRepo);
        Screen creditsScreen = new CreditsScreen();

        ScreenManager screenManager = new ScreenManager();
        screenManager.register("auth", authScreen);
        screenManager.register("login", loginScreen);
        screenManager.register("signup", signupScreen);
        screenManager.register("menu", menuScreen);
        screenManager.register("game", gameScreen);
        screenManager.register("gameWin", gameWinScreen);
        screenManager.register("gameOver", gameOverScreen);
        screenManager.register("gameHistory", gameHistoryScreen);
        screenManager.register("leaderboard", leaderboardScreen);
        screenManager.register("editProfileScreen", editProfileScreen);
        screenManager.register("credits", creditsScreen);

        screenManager.setDefaultScreen("auth");

        return screenManager;
    }

    public static void loop(ScreenManager screenManager) {
        while (true) {
            try {
                screenManager.update();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
