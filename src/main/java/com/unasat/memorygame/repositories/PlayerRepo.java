package com.unasat.memorygame.repositories;

import com.unasat.memorygame.entities.GameEntity;
import com.unasat.memorygame.entities.PlayerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerRepo {
    Connection connection;

    public PlayerRepo(Connection connection) {
        this.connection = connection;
    }

    public PlayerEntity findByPlayerName(String pName) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM players WHERE player_name=?");

            statement.setString(1, pName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int playerId = rs.getInt("player_id");
                String playerName = rs.getString("player_name");
                String password = rs.getString("password");
                Date dateOfBirth = rs.getDate("date_of_birth");
                return new PlayerEntity(playerId, playerName, password, dateOfBirth);
            } else {
                return null;
            }
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }
    }

    public PlayerEntity authenticatePlayer(String playerName, String password) {
        PlayerEntity player = findByPlayerName(playerName);

        if (player == null) {
            return null;
        } else {
            if (password.equals(player.getPassword())) {
                return player;
            }
        }

        return null;
    }

    public PlayerEntity createPlayer(String playerName, String password, String dateOfBirth) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO players (player_name, password, date_of_birth) VALUES (?, ?, ?)");

            statement.setString(1, playerName);
            statement.setString(2, password);
            statement.setString(3, dateOfBirth);

            boolean createdPlayer = statement.execute();

            if (createdPlayer) {
                return findByPlayerName(playerName);
            }
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }

        return null;
    }

    public void createScore(PlayerEntity player, int score) {
        try {
            PreparedStatement scoreStatement = this.connection.prepareStatement(
                    "INSERT INTO scores (score, player_id) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            scoreStatement.setInt(1, score);
            scoreStatement.setInt(2, player.getPlayerId());

            scoreStatement.execute();
            ResultSet scoreRs = scoreStatement.getGeneratedKeys();

            if (scoreRs.next()) {
                int scoreId = scoreRs.getInt(1);

                PreparedStatement gameStatement = this.connection.prepareStatement(
                        "INSERT INTO games (player_id, score_id) VALUES (?, ?)");
                gameStatement.setInt(1, player.getPlayerId());
                gameStatement.setInt(2, scoreId);
                gameStatement.execute();
            }

        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
    }

    public List<GameEntity> getPastGames(PlayerEntity player) {
        List<GameEntity> games = new ArrayList<>();

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT game_id, player_name, score
                            FROM games
                            JOIN players p USING (player_id)
                            JOIN scores USING (score_id)
                            WHERE p.player_id=?;
                        """);

            statement.setInt(1, player.getPlayerId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                GameEntity game = new GameEntity(
                        rs.getInt("game_id"),
                        rs.getString("player_name"),
                        rs.getInt("score"));
                games.add(game);
            }

            return games;
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }
    }

    public Integer getTotalGames(PlayerEntity player) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT COUNT(*) total_games FROM games WHERE player_id=?");

            statement.setInt(1, player.getPlayerId());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_games");
            }
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }

        return null;
    }

    public Integer getLastScore(PlayerEntity player) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT score FROM scores WHERE player_id=? ORDER BY score_id DESC LIMIT 1");

            statement.setInt(1, player.getPlayerId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("score");
            }
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        return null;
    }

    public Integer getHighScore(PlayerEntity player) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT MAX(score) highscore FROM scores WHERE player_id=? ORDER BY score_id DESC LIMIT 1");

            statement.setInt(1, player.getPlayerId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("highscore");
            }
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
        return null;
    }

    public void setPlayerName(PlayerEntity player, String playerName) throws Exception {
        if (playerName.length() > 15) {
            throw new Exception("Username cannot exceed 15 characters.");
        }

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET player_name=? WHERE player_id=?");

            statement.setString(1, playerName);
            statement.setInt(2, player.getPlayerId());
            statement.execute();
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
    }

    public void setDateOfBirth(PlayerEntity player, String dateOfBirth) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET date_of_birth=? WHERE player_id=?");

            statement.setString(1, dateOfBirth);
            statement.setInt(2, player.getPlayerId());
            statement.execute();
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
    }

    public void setPassword(PlayerEntity player, String newPassword) throws Exception {
        if (newPassword.length() > 72) {
            throw new Exception("Password cannot exceed 72 characters.");
        }

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET password=? WHERE player_id=?");

            statement.setString(1, newPassword);
            statement.setInt(2, player.getPlayerId());
            statement.execute();
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
    }
}
