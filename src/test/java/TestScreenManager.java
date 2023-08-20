import com.unasat.memorygame.app.ScreenManager;
import com.unasat.memorygame.interfaces.Screen;
import com.unasat.memorygame.screens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestScreenManager {
    static ScreenManager screenManager;

    @BeforeAll
    public static void init() {
        Screen authScreen = new AuthScreen();
        Screen loginScreen = new LoginScreen(null);
        Screen signupScreen = new SignupScreen(null);
        Screen menuScreen = new MenuScreen();
        Screen gameScreen = new GameScreen(null);
        Screen gameWinScreen = new GameWinScreen(null);
        Screen gameOverScreen = new GameOverScreen(null);
        Screen gameHistoryScreen = new GameHistoryScreen(null);
        Screen leaderboardScreen = new LeaderboardScreen(null);
        Screen editProfileScreen = new EditProfileScreen(null);
        Screen creditsScreen = new CreditsScreen();

        screenManager = new ScreenManager();
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
    }

    @Test
    @DisplayName("Screens should be created")
    public void screensShouldBeCreated() {
        Map<String, Screen> registeredScreens = screenManager.getRegisteredScreens();

        Assertions.assertNotNull(registeredScreens.get("auth"));
        Assertions.assertNotNull(registeredScreens.get("login"));
        Assertions.assertNotNull(registeredScreens.get("signup"));
        Assertions.assertNotNull(registeredScreens.get("menu"));
        Assertions.assertNotNull(registeredScreens.get("game"));
        Assertions.assertNotNull(registeredScreens.get("gameWin"));
        Assertions.assertNotNull(registeredScreens.get("gameOver"));
        Assertions.assertNotNull(registeredScreens.get("gameHistory"));
        Assertions.assertNotNull(registeredScreens.get("leaderboard"));
        Assertions.assertNotNull(registeredScreens.get("editProfileScreen"));
        Assertions.assertNotNull(registeredScreens.get("credits"));
    }

    @Test
    @DisplayName("Screen should be switched")
    public void screensShouldSwitch() {
        String screenToSwitchTo = "game";

        Screen prevScreen = screenManager.getCurrentScreen();
        screenManager.switchScreen(screenToSwitchTo);
        Screen currentScreen = screenManager.getCurrentScreen();

        Assertions.assertNotEquals(prevScreen, currentScreen);
        Assertions.assertEquals(currentScreen, screenManager.getRegisteredScreens().get(screenToSwitchTo));
    }

    @Test
    @DisplayName("Screen should be set to default")
    public void screenShouldBeSetToDefault() {
        String screenToSetAsDefault = "login";
        screenManager.setDefaultScreen(screenToSetAsDefault);

        Assertions.assertEquals(screenManager.getDefaultScreen(), screenManager.getRegisteredScreens().get(screenToSetAsDefault));
    }
}
