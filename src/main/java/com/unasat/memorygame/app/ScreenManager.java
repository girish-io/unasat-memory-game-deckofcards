package com.unasat.memorygame.app;

import com.unasat.memorygame.entities.PlayerEntity;
import com.unasat.memorygame.interfaces.Screen;

import java.util.HashMap;
import java.util.Map;

/*
 * The ScreenManager is used for managing and making switching between different screens easier.
 * It is also able to keep a player context for the currently logged in player.
 */
public class ScreenManager {
    Screen defaultScreen;
    Screen currentScreen;

    // Keeps track of all registered screens
    HashMap<String, Screen> screens = new HashMap<>();

    PlayerEntity player;

    /*
     * Registers a new screen
     */
    public void register(String screenName, Screen screen) {
        this.screens.put(screenName, screen);
    }

    /*
     * Sets the default screen
     */
    public void setDefaultScreen(String screenName) {
        if (screens.get(screenName) != null) {
            this.defaultScreen = screens.get(screenName);
            this.currentScreen = this.defaultScreen;
        } else {
            throw new RuntimeException("No screen registered with key \"" + screenName + "\"");
        }
    }

    public void update() throws Exception {
        if (this.currentScreen == null) {
            throw new Exception("No default screen was specified.");
        }

        this.currentScreen.update(this);
    }

    /*
     * Switch to a certain screen
     */
    public void switchScreen(String screenName) {
        this.currentScreen = this.screens.get(screenName);
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Map<String, Screen> getRegisteredScreens() {
        return screens;
    }
    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public Screen getDefaultScreen() {
        return defaultScreen;
    }
}
