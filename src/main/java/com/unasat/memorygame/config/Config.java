package com.unasat.memorygame.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    public static String MYSQL_HOST;
    public static String MYSQL_USER;
    public static String MYSQL_PASS;
    public static String MYSQL_DB_NAME;
    public static boolean USE_DECK_OF_CARDS_API = true;

    public static void initConfig(String configFilePath) {
        Path p = Paths.get(configFilePath);
        String configFileParentDir = p.getParent().toString();
        String configFileName = p.getFileName().toString();

        Dotenv dotenv = Dotenv.configure()
                .directory(configFileParentDir)
                .filename(configFileName)
                .load();

        MYSQL_HOST = dotenv.get("MYSQL_HOST");
        MYSQL_USER = dotenv.get("MYSQL_USER");
        MYSQL_PASS = dotenv.get("MYSQL_PASS");
        MYSQL_DB_NAME = dotenv.get("MYSQL_DB_NAME");
    }
}
