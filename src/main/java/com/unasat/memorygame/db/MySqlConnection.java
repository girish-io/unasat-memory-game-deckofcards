package com.unasat.memorygame.db;

import java.sql.DriverManager;
import java.sql.Connection;

public class MySqlConnection {
    String dbName;
    String dbHost;
    String dbUser;
    String dbPass;

    private final Connection connection;

    public MySqlConnection(String user, String pass, String host, String dbName) throws java.sql.SQLException {
        this.dbUser = user;
        this.dbPass = pass;
        this.dbHost = host;
        this.dbName = dbName;
        this.connection = createConnection();
    }

    public Connection createConnection() throws java.sql.SQLException {
        String connectionUrl = "jdbc:mysql://" + this.dbHost + "/" + this.dbName;
        return DriverManager.getConnection(connectionUrl, this.dbUser, this.dbPass);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
