package com.unasat.memorygame.entities;

import java.util.Date;

public class PlayerEntity {
    int playerId;
    String playerName;
    Date dateOfBirth;
    String password;

    public PlayerEntity(int playerId, String playerName, String password, Date dateOfBirth) {
        setPlayerId(playerId);
        setPlayerName(playerName);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
