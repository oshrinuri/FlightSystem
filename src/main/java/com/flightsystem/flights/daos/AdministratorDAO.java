package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Administrator;
import com.flightsystem.flights.sqlconnection.PostgresConnectionUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data Access Layer (DAO) for Administrators table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.3
 */
@Component
public class AdministratorDAO implements DAO<Administrator> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Administrators\"";
    private static final String SQL_GET_ALL_ADMINS = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_ADMIN_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_ADMINS = "\"First_Name\" = ?, \"Last_Name\" = ? , \"User_ID\" = " +
            "? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_ADMINS = " (\"First_Name\",\"Last_Name\",\"User_ID\") VALUES (?," +
            "?,?)";
    private static final String SQL_ADD_ADMIN = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_ADMINS;
    private static final String SQL_DEL_ADMIN = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_ADMIN = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_ADMINS;
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves an Administrator from database by ID.
     * @param id Administrator's id to be pulled.
     * @return Pulled Administrator object. Null if it doesn't exist.
     */
    @Override
    public Administrator get(int id) {
        Administrator administrator = null;
            try (Connection connection = PostgresConnectionUtil.getConnection();
                 PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ADMIN_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    administrator = fetchAdministratorObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;
    }
        /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Administrators from DB.
     * @return a list of all Administrators.
     */
    @Override
    public List<Administrator> getAll() {
        List<Administrator> administratorsList = new ArrayList<>();
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_ADMINS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    administratorsList.add(fetchAdministratorObject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administratorsList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new administrator to the DB.
     * @param administrator Administrator to be added.
     */
    @Override
    public void add(Administrator administrator) {
            try (Connection connection = PostgresConnectionUtil.getConnection();
                 PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_ADD_ADMIN)) {
                fillStatement(statement, administrator);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing administrator from the DB.
     * @param administrator Administrator to be removed.
     */
    @Override
    public void remove(Administrator administrator) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_DEL_ADMIN)) {
            statement.setLong(1, administrator.getAdminId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing administrator in the DB.
     * @param administrator Administrator to be updated with.
     */
    @Override
    public void update(Administrator administrator) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_UPDATE_ADMIN)) {
            fillStatement(statement, administrator);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new Administrator object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created Administrator object filled with database values. If no such exists - return null.
     */
    private static Administrator fetchAdministratorObject(ResultSet rs) {
        try {
            return new Administrator(
                    rs.getInt("ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getLong("User_ID")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Helper method:
     * Inserts a given object class attributes into the given statement query for later execution.
     * @param statement PreparedStatement to be filled.
     * @param administrator Administrator object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, Administrator administrator) {
        try {
            statement.setString(1, administrator.getFirstName());
            statement.setString(2, administrator.getLastName());
            statement.setLong(3, administrator.getUserId());
            statement.setLong(4, administrator.getAdminId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
