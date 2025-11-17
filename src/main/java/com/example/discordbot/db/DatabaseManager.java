package com.example.discordbot.db;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/revetisbot?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "StrongPassword123!";

    private static Connection connection;

    public static void connect() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("DB Hatasi olustu: "+e);
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("MySQL connection succesfull");
            createTable();
        } catch (SQLException e) {
            System.out.println("MySQL connection fail: " + e.getMessage());
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS guild_settings ("
                + "guild_id VARCHAR(30) NOT NULL PRIMARY KEY, "
                + "log_channel_id VARCHAR(30), "
                + "language VARCHAR(5) DEFAULT 'en', "
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ");";


        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            ResultSet rs = connection.getMetaData().getColumns(null, null, "guild_settings", "language");
            if (!rs.next()) {
                stmt.execute("ALTER TABLE guild_settings ADD COLUMN language VARCHAR(5) DEFAULT 'en'");
            }
        } catch (SQLException e) {
            System.out.println("Tablo oluşturulamadı: " + e.getMessage());
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
