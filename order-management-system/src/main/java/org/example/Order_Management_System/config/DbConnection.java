package org.example.Order_Management_System.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DbConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static HikariDataSource dataSource;

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setUsername(USERNAME);
        hikariConfig.setPassword(PASSWORD);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(" Connection not Build , Process Was Fail ! ");
        }
        return null;
    }
}
