package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Ticket;
import com.flightsystem.flights.sqlconnection.PostgreSQLConnection;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Layer (DAO) for Tickets table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@Component
public class TicketsDAO implements DAO<Ticket> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Tickets\"";
    private static final String SQL_GET_ALL_TICKETS = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_TICKET_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_TICKET = "\"Flight_ID\" = ?, \"Customer_ID\" = ? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_TICKET = " (\"Flight_ID\",\"Customer_ID\") VALUES (?,?)";
    private static final String SQL_ADD_TICKET = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_TICKET;
    private static final String SQL_DEL_TICKET = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_TICKET = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_TICKET;
    private static final String SQL_GET_TICKETS_BY_CUSTOMER = "SELECT \"ID\",\"Flight_ID\",\"Customer_ID\" " +
            "FROM get_tickets_by_customer(?)";
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private final PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
    private ResultSet resultSet;
    private PreparedStatement statement;
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a ticket from database by ID.
     * @param id Ticket's id to be pulled.
     * @return Pulled Ticket object. Null if it doesn't exist.
     */
    @Override
    public Ticket get(int id) {
        Ticket ticket = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_TICKET_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                ticket = fetchTicketObject(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return ticket;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Tickets from DB.
     * @return a list of all Tickets.
     */
    @Override
    public List<Ticket> getAll() {
        List<Ticket> ticketList = new ArrayList<>();
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_TICKETS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ticketList.add(fetchTicketObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return ticketList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new ticket to the DB.
     * @param ticket Flight to be added.
     */
    @Override
    public void add(Ticket ticket) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_ADD_TICKET);
            fillStatement(statement, ticket);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing ticket from the DB.
     * @param ticket Ticket to be removed.
     */
    @Override
    public void remove(Ticket ticket) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_DEL_TICKET);
            statement.setLong(1, ticket.getTicketId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing ticket in the DB.
     * @param ticket Ticket to be updated with.
     */
    @Override
    public void update(Ticket ticket) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_UPDATE_TICKET);
            fillStatement(statement, ticket);
            statement.setLong(3, ticket.getTicketId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Tickets purchased by a Customer's id.
     * @param customerId Customer's ID.
     * @return A list of all tickets belonging to that customer.
     */
    public static List<Ticket> getTicketsByCustomerId(long customerId) {
        List<Ticket> ticketList = new ArrayList<>();
        PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_TICKETS_BY_CUSTOMER);
            statement.setLong(1,customerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ticketList.add(fetchTicketObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return ticketList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new Ticket object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created Ticket object filled with database values. If no such exists - return null.
     */
    private static Ticket fetchTicketObject(ResultSet rs) {
        try {
            return new Ticket(
                    rs.getLong("ID"),
                    rs.getLong("Flight_ID"),
                    rs.getLong("Customer_ID")
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
     * @param ticket Ticket object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, Ticket ticket) {
        try {
            statement.setLong(1, ticket.getFlightId());
            statement.setLong(2, ticket.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
