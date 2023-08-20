package com.unasat.memorygame.app;

public class Card {
    public String value;
    public String displayName;
    public boolean opened = false;
    public int position = -1;

    public Card(String value) {
        setValue(value);
    }

    /*
     * Sets the card at a specific position on the board
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /*
     * Gets the card's current position on the board
     */
    public int getPosition() {
        return position;
    }

    /*
     * Gets the value of the card
     */
    public String getValue() {
        return value;
    }

    /*
     * Sets a value for the card
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * Check whether the card was opened by the player
     */
    public boolean isOpened() {
        return opened;
    }

    /*
     * Sets the card's open status
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
