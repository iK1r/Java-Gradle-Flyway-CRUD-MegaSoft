package com.example.megasoft;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private final Connection connection;

    private Database() {
        try {
            Files.createDirectories(Path.of("data"));
            connection = DriverManager.getConnection("jdbc:h2:./data/megasoft", "sa", "");
        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Cannot connect to database", e);
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
