package com.unasat.memorygame.interfaces;

import com.unasat.memorygame.entities.PlayerEntity;

public interface Player {
    PlayerEntity findByPlayerName(String playerName) throws java.sql.SQLException;
}
