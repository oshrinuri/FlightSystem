package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Ticket;
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
 * Data Access Layer (DAO) for Tickets table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.3
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
    /* Methods ------------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a ticket from database by ID.
     * @param id Ticket's id to be pulled.
     * @return Pulled Ticket object. Null if it doesn't exist.
     */
    @Override
    public Ticket get(int id) {
        Ticket ticket = null;
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_TICKET_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    ticket = fetchTicketObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_TICKETS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    ticketList.add(fetchTicketObject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_ADD_TICKET)) {
            fillStatement(statement, ticket);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing ticket from the DB.
     * @param ticket Ticket to be removed.
     */
    @Override
    public void remove(Ticket ticket) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_DEL_TICKET)) {
            statement.setLong(1, ticket.getTicketId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing ticket in the DB.
     * @param ticket Ticket to be updated with.
     */
    @Override
    public void update(Ticket ticket) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_UPDATE_TICKET)) {
            fillStatement(statement, ticket);
            statement.setLong(3, ticket.getTicketId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(SQL_GET_TICKETS_BY_CUSTOMER)) {
            statement.setLong(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    ticketList.add(fetchTicketObject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
