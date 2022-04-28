package com.flightsystem.flights.daos;

import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.sqlconnection.PostgreSQLConnection;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer (DAO) for Users table in database, providing CRUD operations on database.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@Component
public class UsersDAO implements DAO<User> {
    /* Queries literals ----------------------------------------------------------------------------------------------*/
    private static final String SQL_TABLE_NAME = "\"Users\"";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE " +
            "\"Username\" = ?";
    private static final String SQL_GET_USER_BY_CREDENTIALS = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE " +
            "\"Username\" = ? AND \"Password\" = crypt(?,\"Password\")";
    private static final String SQL_UPDATE_COLUMNS_USER = "\"Username\" = ?, \"Password\" = ?," +
            "\"Email\" = ?, \"User_Role\" = ? , \"Thumbnail_URL\" = ? , \"Enabled\" = ? WHERE \"ID\" = ?";
    private static final String SQL_INSERT_VALUES_USER = " (\"Username\"," +
            "\"Password\",\"Email\",\"User_Role\",\"Thumbnail_URL\",\"Enabled\") VALUES (?,crypt(?,gen_salt('bf,10)),?,?," +
            "?,?)";
    private static final String SQL_ADD_USER = "INSERT INTO " + SQL_TABLE_NAME + SQL_INSERT_VALUES_USER;
    private static final String SQL_DEL_USER = "DELETE FROM " + SQL_TABLE_NAME + " WHERE \"ID\" = ?";
    private static final String SQL_UPDATE_USER = "UPDATE " + SQL_TABLE_NAME + " SET " + SQL_UPDATE_COLUMNS_USER;
    private static final String SQL_GET_CUSTOMER_BY_USERNAME = "SELECT * FROM get_customer_by_username(?)";
    private static final String SQL_GET_AIRLINE_BY_USERNAME = "SELECT * FROM get_airline_by_username(?)";
    private static final String SQL_GET_ADMIN_BY_USERNAME = "SELECT * FROM get_admin_by_username(?)";
    /* Class members -------------------------------------------------------------------------------------------------*/
    private final PostgreSQLConnection postgresJDBCConnection = new PostgreSQLConnection();
    private ResultSet resultSet;
    private PreparedStatement statement;
    /* Methods -------------------------------------------------------------------------------------------------------*/
    /***
     * Retrieves a User from database by ID.
     * @param id User's id to be pulled.
     * @return Pulled User object. Null if it doesn't exist.
     */
    @Override
    public User get(int id) {
        User user = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_USER_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                user = fetchUserObject(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return user;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Users from DB.
     * @return a list of all Users.
     */
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(fetchUserObject(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return userList;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    public User getByUsername(String username) {
        User user = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_USER_BY_USERNAME);
            statement.setString(1, username);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                user = fetchUserObject(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a User object by its Username and Password.
     * @param username Username of the User.
     * @param password Password of the User.
     * @return A User object corresponding credentials passed.
     */
    public User getByCredentials(String username, String password) {
        User user = null;
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_GET_USER_BY_CREDENTIALS);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                user = fetchUserObject(resultSet);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new user to the DB.
     * @param user User to be added.
     */
    @Override
    public void add(User user) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_ADD_USER);
            fillStatement(statement, user);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a User's related ID by his role: Admin's ID / Customer's ID / Airline's ID
     * @param user The user to retrieves his internal ID.
     * @return User's related internal ID.
     */
    public long getInternalId(User user) {
        long internalId = 0;
        String query;
        query = switch (user.getUserRole()) {
            case 1-> SQL_GET_CUSTOMER_BY_USERNAME;
            case 2-> SQL_GET_ADMIN_BY_USERNAME;
            case 3->  SQL_GET_AIRLINE_BY_USERNAME;
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserRole());
        };
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next())
                internalId = resultSet.getLong("ID");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
        return internalId;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an existing user from the DB.
     * @param user User to be removed.
     */
    @Override
    public void remove(User user) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_DEL_USER);
            statement.setLong(1, user.getUserId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing user in the DB.
     * @param user User to be updated with.
     */
    @Override
    public void update(User user) {
        try {
            postgresJDBCConnection.connect();
            statement = postgresJDBCConnection.prepareStatement(SQL_UPDATE_USER);
            fillStatement(statement, user);
            statement.setLong(7, user.getUserId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               PostgreSQLConnection.freeDatabaseResources(postgresJDBCConnection, statement, resultSet);
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Helper method:
     * Retrieves a new User object from a given ResultSet filled with database values.
     * @param rs A ResultSet filled with database values.
     * @return A newly created User object filled with database values. If no such exists - return null.
     */
    private static User fetchUserObject(ResultSet rs) {
        try {
            return new User(
                    rs.getLong("ID"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Email"),
                    rs.getInt("User_Role"),
                    rs.getString("Thumbnail_URL"),
                    rs.getBoolean("Enabled")
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
     * @param user User object to fill with.
     */
    private static void fillStatement(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getUserRole());
            statement.setString(5, user.getThumbnailURL());
            statement.setBoolean(6, user.isEnabled());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
