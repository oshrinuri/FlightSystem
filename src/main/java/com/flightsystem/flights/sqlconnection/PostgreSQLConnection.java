package com.flightsystem.flights.sqlconnection;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * PostgreSQL Database connection and resources management class.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
public class PostgreSQLConnection {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    @Getter private Connection connection;
    private PreparedStatement preparedStatement;
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Creates a database connection. Database url, user and password are taken from /resources folder (postgres.properties) file.
     * @return The established connection to the database.
     */
    public Connection connect() {
        Properties config = new Properties();
        try (InputStream input = PostgreSQLConnection.class.getClassLoader().
                getResourceAsStream("postgres.properties")) {
            config.load(input);
            String driver = config.getProperty("db.driver");
            String address = config.getProperty("db.address");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");
            Class.forName(driver);
            connection = DriverManager.getConnection(address, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Creates a PreparedStatement object for sending parameterized SQL statements to the database.
     * @param query The query to send to the database.
     * @return a PreparedStatement filled with the query given.
     */
    public PreparedStatement prepareStatement(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Releases given ResultSet, PreparedStatement, Connection and JDBC resources
     * immediately instead of waiting for them to be automatically released.
     * @param cn A database Connection object.
     * @param st A Statement object.
     * @param rs A ResultSet object.
     */
    public static void freeDatabaseResources(PostgreSQLConnection cn, PreparedStatement st, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (st != null) st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (cn != null) cn.getConnection().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
