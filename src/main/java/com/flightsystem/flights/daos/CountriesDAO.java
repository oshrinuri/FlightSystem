package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Country;
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
 * Data Access Layer (DAO) for Countries table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.3
 */
@Component
public class CountriesDAO implements DAO<Country> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Countries\"";
    private static final String SQL_GET_ALL_COUNTRIES = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_COUNTRY_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_COUNTRY = "\"Name\" = ?, \"Flag_URL\" = ? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_COUNTRY = " (\"Name\",\"Flag_URL\") VALUES (?,?)";
    private static final String SQL_ADD_COUNTRY = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_COUNTRY;
    private static final String SQL_DEL_COUNTRY = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COUNTRY = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_COUNTRY;
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a country from database by ID.
     * @param id Country's id to be pulled.
     * @return Pulled Country object. Null if it doesn't exist.
     */
    @Override
    public Country get(int id) {
        Country country = null;
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_COUNTRY_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    country = fetchCountryObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Countries from DB.
     * @return a list of all Countries.
     */
    @Override
    public List<Country> getAll() {
        List<Country> countriesList = new ArrayList<>();
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_COUNTRIES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    countriesList.add(fetchCountryObject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new country to the DB.
     * @param country Country to be added.
     */
    @Override
    public void add(Country country) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_ADD_COUNTRY)) {
            fillStatement(statement, country);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing country from the DB.
     * @param country Country to be removed.
     */
    @Override
    public void remove(Country country) {
            try (Connection connection = PostgresConnectionUtil.getConnection();
                 PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_DEL_COUNTRY)) {
                statement.setLong(1, country.getCountryId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing country in the DB.
     * @param country Country to be updated with.
     */
    @Override
    public void update(Country country) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_UPDATE_COUNTRY)) {
            fillStatement(statement, country);
            statement.setInt(3, country.getCountryId());
            statement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new Country object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created Country object filled with database values. If no such exists - return null.
     */
    private static Country fetchCountryObject(ResultSet rs) {
        try {
            return new Country(
                    rs.getShort("ID"),
                    rs.getString("Name"),
                    rs.getString("Flag_URL")
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
     * @param country Country object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, Country country) {
        try {
            statement.setString(1, country.getName());
            statement.setString(2, country.getFlagURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
