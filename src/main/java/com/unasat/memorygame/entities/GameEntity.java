package com.unasat.memorygame.entities;

public class GameEntity {
    String playerName;
    int gameId, score;

    public GameEntity(int gameId, String playerName, int score) {
        setGameId(gameId);
        setPlayerName(playerName);
        setScore(score);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
