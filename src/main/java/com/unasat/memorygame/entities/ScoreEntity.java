package com.unasat.memorygame.entities;

public class ScoreEntity {
    String playerName;
    int score;

    public ScoreEntity(String playerName, int score) {
        setPlayerName(playerName);
        setScore(score);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
