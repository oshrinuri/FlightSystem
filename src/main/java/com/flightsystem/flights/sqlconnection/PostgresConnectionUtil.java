package com.flightsystem.flights.sqlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * PostgreSQL Database connection and resources utility class.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */
public final class PostgresConnectionUtil {
    //-----------------------------------------------------------------------------------------------------------------------
    private PostgresConnectionUtil() {
        throw new IllegalStateException("Utility class for database connection");
    }
    //-----------------------------------------------------------------------------------------------------------------------
    public static Connection getConnection() {
        Properties config = new Properties();
        try (InputStream input = PostgresConnectionUtil.class.getClassLoader().
                getResourceAsStream("postgres.properties")) {
            config.load(input);
            String driver = config.getProperty("db.driver");
            String address = config.getProperty("db.address");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");
            Class.forName(driver);
            return DriverManager.getConnection(address, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
