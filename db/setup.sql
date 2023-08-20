CREATE DATABASE memorygame;

USE memorygame;

CREATE TABLE players (
    player_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(72) NOT NULL,
    date_of_birth DATE NOT NULL
);

CREATE TABLE scores (
    score_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    score INTEGER NOT NULL,
    player_id INTEGER,
    CONSTRAINT FOREIGN KEY(player_id) REFERENCES players(player_id)
);

CREATE TABLE games (
    game_id INT PRIMARY KEY AUTO_INCREMENT,
    player_id int,
    score_id int,
    CONSTRAINT FOREIGN KEY (player_id) REFERENCES players (player_id),
    CONSTRAINT FOREIGN KEY (score_id) REFERENCES scores (score_id)
);
