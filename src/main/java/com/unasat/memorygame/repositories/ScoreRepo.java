package com.unasat.memorygame.repositories;

import com.unasat.memorygame.entities.ScoreEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepo {
    Connection connection;

    public ScoreRepo(Connection connection) {
        this.connection = connection;
    }

    public List<ScoreEntity> getTop10Scores() {
        List<ScoreEntity> scores = new ArrayList<>(10);

        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    """
                            SELECT player_name, MAX(score) highscore
                            FROM scores
                            JOIN players p USING (player_id)
                            GROUP BY player_name
                            ORDER BY highscore DESC
                            LIMIT 10;
                        """);

            while (rs.next()) {
                ScoreEntity score = new ScoreEntity(rs.getString("player_name"), rs.getInt("highscore"));
                scores.add(score);
            }

            return scores;
        } catch (java.sql.SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }
    }
}
