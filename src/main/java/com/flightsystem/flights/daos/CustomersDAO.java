package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.Customer;
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
 * Data Access Layer (DAO) for Customers table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */

@Component
public class CustomersDAO implements DAO<Customer> {
    /* Queries literals ---------------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Customers\"";
    private static final String SQL_GET_ALL_CUSTOMERS = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_CUSTOMER_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_COLUMNS_CUSTOMER = "\"First_Name\" = ?, \"Last_Name\" = ?," +
            "\"Address\" = ?, \"Phone_Number\" = ?, \"Credit_Card_Number\" = ?, " +
            "\"User_ID\" = ? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_COLUMNS_CUSTOMER = " (\"First_Name\"," +
            "\"Last_Name\",\"Address\",\"Phone_Number\",\"Credit_Card_Number\"," +
            "\"User_ID\") VALUES (?,?,?,?,?,?)";
    private static final String SQL_ADD_CUSTOMER = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_COLUMNS_CUSTOMER;
    private static final String SQL_DEL_CUSTOMER = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_CUSTOMER = "UPDATE " + SQL_TABLE_NAME + " SET "
            + SQL_UPDATE_COLUMNS_CUSTOMER;
    /*  Methods -----------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a customer from database by ID.
     * @param id Customer's id to be pulled.
     * @return Pulled Customer object. Null if it doesn't exist.
     */
    @Override
    public Customer get(int id) {
        Customer customer = null;
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_CUSTOMER_BY_ID)) {
            statement.setLong(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next())
                    customer = fetchCustomerObject(resultSet);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return customer;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Customers from DB.
     * @return a list of all Customers.
     */
    @Override
    public List<Customer> getAll() {
        List<Customer> customersList = new ArrayList<>();
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement =  Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_CUSTOMERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customersList.add(fetchCustomerObject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new airline to the DB.
     * @param customer Customer to be added.
     */
    @Override
    public void add(Customer customer) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(SQL_ADD_CUSTOMER)) {
            fillStatement(statement, customer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing customer from the DB.
     * @param customer Customer to be removed.
     */
    @Override
    public void remove(Customer customer) {
        try (Connection connection = PostgresConnectionUtil.getConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(SQL_DEL_CUSTOMER)) {
                statement.setLong(1, customer.getCustomerId());
                statement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing customer in the DB.
     * @param customer Customer to be updated with.
     */
    @Override
    public void update(Customer customer) {
        try (Connection connection = PostgresConnectionUtil.getConnection()) {
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(SQL_UPDATE_CUSTOMER);
            fillStatement(statement,customer);
            statement.setLong(7, customer.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new Customer object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created Customer object filled with database values. If no such exists - return null.
     */
    private static Customer fetchCustomerObject(ResultSet rs) {
        try {
            return new Customer(
                    rs.getLong("ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getString("Address"),
                    rs.getString("Phone_Number"),
                    rs.getString("Credit_Card_Number"),
                    rs.getLong("User_ID"));
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
     * @param customer Customer object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, Customer customer) {
        try {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getCreditCardNumber());
            statement.setLong(6, customer.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
