package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.sqlconnection.PostgreSQLConnection;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Layer (DAO) for Flights table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@Component
public class FlightsDAO implements DAO<Flight> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Flights\"";
    private static final String SQL_GET_ALL_FLIGHTS = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_FLIGHT_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_FLIGHT = "\"Airline_Company_ID\" = ?, \"Origin_Country_ID\" = ?," +
            "\"Destination_Country_ID\" = ?, \"Departure_Time\" = ?, \"Landing_Time\" = ?, " +
            "\"Remaining_Tickets\" = ? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_FLIGHT = " (\"Airline_Company_ID\"," +
            "\"Origin_Country_ID\",\"Destination_Country_ID\",\"Departure_Time\",\"Landing_Time\"," +
            "\"Remaining_Tickets\") VALUES (?,?,?,?,?,?)";
    private static final String SQL_ADD_FLIGHT = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_FLIGHT;
    private static final String SQL_DEL_FLIGHT = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ? AND " +
            "\"Airline_Company_ID\" = ?";
    private static final String SQL_UPDATE_FLIGHT = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_FLIGHT;
    private static final String SQL_GET_ALL_BY_ORIGIN_COUNTRY_ID = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
            "\"Origin_Country_ID\" = ?";
    private static final String SQL_GET_ALL_BY_DEST_COUNTRY_ID = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
            "\"Destination_Country_ID\" = ?";
    private static final String SQL_GET_ALL_BY_DEP_DATE = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
            "\"Departure_Time\"::DATE = ?";
    private static final String SQL_GET_ALL_BY_LAND_DATE = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
            "\"Landing_Time\"::DATE = ?";
    private static final String SQL_GET_ALL_BY_PARAMS = "SELECT * FROM " + SQL_TABLE_NAME + "WHERE " +
        "\"Origin_Country_ID\" = ? AND \"Destination_Country_ID\" = ? AND \"Departure_Time\"::DATE = ?";
    private static final String SQL_GET_ALL_FLIGHTS_BY_CUSTOMER = "SELECT \"Flight_ID\",\"Airline_Company_ID\"," +
            "\"Origin_Country_ID\",\"Destination_Country_ID\",\"Departure_Time\",\"Landing_Time\",\"Remaining_Tickets\""
            + " FROM get_tickets_by_customer(?) JOIN \"Flights\" ON \"Flight_ID\" = \"Flights\".\"ID\"";
    private static final String SQL_GET_ALL_FLIGHTS_BY_AIRLINE_ID = "SELECT * FROM get_flights_by_airline_id(?)";
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private final PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
    private ResultSet resultSet;
    private PreparedStatement statement;
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a list of Flights belonging to a given Customer.
     * @param customer Customer to retrieve flights from.
     * @return A list of Flights belonging to the customer.
     */
    public static List<Flight> getFlightsByCustomer(Customer customer) {
        List<Flight> flightList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_FLIGHTS_BY_CUSTOMER);
            statement.setLong(1,customer.getCustomerId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of Flights departing from origin country and arriving at destination country on a given date.
     * @param originCountryId Origin country ID.
     * @param destinationCountryId Destination country ID.
     * @param departureDate Flight's departure date.
     * @return A list of Flights matching the given parameters.
     */
    public static List<Flight> getFlightsByParams(int originCountryId, int destinationCountryId,
                                                  Timestamp departureDate) {
        List<Flight> flightList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_BY_PARAMS);
            statement.setInt(1,originCountryId);
            statement.setInt(2,destinationCountryId);
            statement.setTimestamp(3,departureDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights belonging to an airline by its ID.
     * @param airlineId Airline's ID.
     * @return A list of all Flights operated by the given Airline's id.
     */
    public static List<Flight> getFlightsByAirlineId(long airlineId) {
        List<Flight> flightList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_FLIGHTS_BY_AIRLINE_ID);
            statement.setLong(1,airlineId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights planned to depart on a given date.
     * @param departureDate Departure date.
     * @return A list of all Flights planned to depart at the given date.
     */
    public static List<Flight> getFlightsByDepartureDate(Date departureDate) {
        return pullFlightsByDate(departureDate,SQL_GET_ALL_BY_DEP_DATE);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights planned to land on a given date.
     * @param landingDate Arrival date.
     * @return A list of all Flights planned to arrive at the given date.
     */
    public static List<Flight> getFlightsByLandingDate(Date landingDate) {
        return pullFlightsByDate(landingDate,SQL_GET_ALL_BY_LAND_DATE);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights by their origin country ID.
     * @param countryId Origin's country ID.
     * @return A list of all Flights which leaves from the given country's ID.
     */
    public static List<Flight> getFlightsByOriginCountryId(int countryId) {
        return pullFlightsByCountryId(countryId,SQL_GET_ALL_BY_ORIGIN_COUNTRY_ID);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights by their destination country ID.
     * @param countryId Destination's country ID.
     * @return A list of all Flights which arrive at the given country's ID.
     */
    public static List<Flight> getFlightsByDestinationCountryId(int countryId) {
        return pullFlightsByCountryId(countryId,SQL_GET_ALL_BY_DEST_COUNTRY_ID);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a list of all Flights planned on a given date by a certain criteria (arrival/departure date).
     * @param date Departure/landing date.
     * @param query Query to execute according to departure/landing option.
     * @return A list of all Flights planned for the given date.
     */
    private static List<Flight> pullFlightsByDate(Date date,String query) {
        List<Flight> flightList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(query);
            statement.setDate(1,date);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Helper method:
     * Retrieves a list of all Flights occur in a given country (origin/destination).
     * @param countryId ID of the country.
     * @param query Query to execute according to origin/destination option.
     * @return A list of all Flights which occur in the given country by origin/destination.
     */
    private static List<Flight> pullFlightsByCountryId(int countryId,String query) {
        List<Flight> flightList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(query);
            statement.setInt(1,countryId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------ */
    /***
     * Helper method:
     * Retrieves a new Flight object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created Flight object filled with database values. If no such exists - return null.
     */
    private static Flight fetchFlightObject(ResultSet rs) {
        try {
            return new Flight(
                    rs.getLong("ID"),
                    rs.getLong("Airline_Company_ID"),
                    rs.getInt("Origin_Country_ID"),
                    rs.getInt("Destination_Country_ID"),
                    rs.getTimestamp("Departure_Time"),
                    rs.getTimestamp("Landing_Time"),
                    rs.getInt("Remaining_Tickets"));
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
     * @param flight Flight object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, Flight flight) {
        try {
            statement.setLong(1, flight.getAirlineCompanyId());
            statement.setInt(2, flight.getOriginCountryId());
            statement.setInt(3, flight.getDestinationCountryId());
            statement.setTimestamp(4, flight.getDepartureTime());
            statement.setTimestamp(5, flight.getLandingTime());
            statement.setInt(6, flight.getRemainingTickets());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a flight from database by ID.
     * @param id Flight's id to be pulled.
     * @return Pulled Flight object. Null if it doesn't exist.
     */
    @Override
    public Flight get(int id) {
        Flight flight = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_FLIGHT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                flight = fetchFlightObject(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flight;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights from DB.
     * @return a list of all Flights.
     */
    @Override
    public List<Flight> getAll() {
        List<Flight> flightList = new ArrayList<>();
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_FLIGHTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightList.add(fetchFlightObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return flightList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new flight to the DB.
     * @param flight Flight to be added.
     */
    @Override
    public void add(Flight flight) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_ADD_FLIGHT);
            fillStatement(statement, flight);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing flight from the DB.
     * @param flight Flight to be removed.
     */
    @Override
    public void remove(Flight flight) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_DEL_FLIGHT);
            statement.setLong(1, flight.getFlightId());
            statement.setLong(2, flight.getAirlineCompanyId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing flight in the DB.
     * @param flight Flight to be updated with.
     */
    @Override
    public void update(Flight flight) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_UPDATE_FLIGHT);
            fillStatement(statement, flight);
            statement.setLong(7,flight.getFlightId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
}
