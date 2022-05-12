package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.AirlineCompany;
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
 * Data Access Layer (DAO) for Airlines_Company table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */
@Component
public class AirlinesDAO implements DAO<AirlineCompany> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Airline_Companies\"";
    private static final String SQL_GET_ALL_AIRLINES = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_AIRLINE_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_GET_AIRLINE_BY_PARAMETERS = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE " +
            "\"Name\" = ? AND \"Country_ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_AIRLINE = "\"Name\" = ?, \"Country_ID\" = ? , \"User_ID\" = ? " +
            "WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_AIRLINES = " (\"Name\",\"Country_ID\",\"User_ID\") VALUES (?,?,?)";
    private static final String SQL_ADD_AIRLINE = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_AIRLINES;
    private static final String SQL_DEL_AIRLINE = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_AIRLINE = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_AIRLINE;
    private static final String SQL_GET_ALL_AIRLINES_BY_COUNTRY_ID = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
            "\"Country_ID\" = ?";
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves an airline from database by ID.
     * @param id Airline's id to be pulled.
     * @return Pulled Airline object. Null if it doesn't exist.
     */
    @Override
    public AirlineCompany get(int id) {
        AirlineCompany airlineCompany = null;
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_AIRLINE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    airlineCompany = fetchAirlineCompanyObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Airlines from DB.
     * @return a list of all Airlines.
     */
    @Override
    public List<AirlineCompany> getAll() {
        List<AirlineCompany> airlinesList = new ArrayList<>();
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_AIRLINES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    airlinesList.add(fetchAirlineCompanyObject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlinesList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new airline to the DB.
     * @param airlineCompany Airline to be added.
     */
    @Override
    public void add(AirlineCompany airlineCompany) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_ADD_AIRLINE)) {
            fillStatement(statement, airlineCompany);
            statement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing airline from the DB.
     * @param airlineCompany Airline to be removed.
     */
    @Override
    public void remove(AirlineCompany airlineCompany) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_DEL_AIRLINE)) {
            statement.setLong(1, airlineCompany.getAirlineCompanyId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing airline in the DB.
     * @param airlineCompany Airline to be updated with.
     */
    @Override
    public void update(AirlineCompany airlineCompany) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_UPDATE_AIRLINE)) {
            fillStatement(statement, airlineCompany);
            statement.setLong(4, airlineCompany.getAirlineCompanyId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves an Airline by Name and Country ID.
     * @param airlineName Airline's name.
     * @param countryId Country's ID.
     * @return An Airline matching the given Name and Country ID.
     */
    public AirlineCompany getAirlineByParameters(String airlineName, int countryId) {
        AirlineCompany airlineCompany = null;
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_AIRLINE_BY_PARAMETERS)) {
            statement.setString(1, airlineName);
            statement.setInt(2, countryId);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    airlineCompany = fetchAirlineCompanyObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Airlines by their country's ID.
     * @param countryId Airline's country ID.
     * @return A list of all Airlines which operate in country's ID.
     */
    private static List<AirlineCompany> getAirlinesByCountry(int countryId) {
        List<AirlineCompany> airlinesList = new ArrayList<>();
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_AIRLINES_BY_COUNTRY_ID)) {
            statement.setInt(1, countryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    airlinesList.add(fetchAirlineCompanyObject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlinesList;
    }
        /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new AirlineCompany object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created AirlineCompany object filled with database values. If no such exists - return null.
     */
    private static AirlineCompany fetchAirlineCompanyObject(ResultSet rs) {
        try {
            return new AirlineCompany(
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getInt("Country_ID"),
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
     * @param airlineCompany Airline object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, AirlineCompany airlineCompany) {
        try {
            statement.setString(1, airlineCompany.getAirlineCompanyName());
            statement.setInt(2, airlineCompany.getCountryId());
            statement.setLong(3, airlineCompany.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
