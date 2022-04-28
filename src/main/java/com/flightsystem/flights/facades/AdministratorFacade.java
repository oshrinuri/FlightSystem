package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.AdministratorDAO;
import com.flightsystem.flights.daos.AirlinesDAO;
import com.flightsystem.flights.daos.CustomersDAO;
import com.flightsystem.flights.dtos.Administrator;
import com.flightsystem.flights.dtos.AirlineCompany;
import com.flightsystem.flights.dtos.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.flightsystem.flights.facades.FacadeConstants.*;
/**
 * Facade class of Administrator.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@NoArgsConstructor
@Component
public class AdministratorFacade extends AnonymousFacade {
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Customers from database.
     * @return A list of all Customers.
     */
    public List<Customer> getAllCustomers() {
        return new CustomersDAO().getAll();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds an Airline to the database.
     * @param airlineCompany The airline to be added.
     */
    public void addAirline(AirlineCompany airlineCompany) {
        if (airlineCompany == null) throw new NullPointerException(AIRLINE_NULL_EXCEPTION);
        AirlinesDAO airlinesDAO = new AirlinesDAO();
        airlinesDAO.add(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an Airline from the database.
     * @param airlineCompany The airline to be removed.
     */
    public void removeAirline(AirlineCompany airlineCompany) {
        if (airlineCompany == null) throw new NullPointerException(AIRLINE_NULL_EXCEPTION);
        AirlinesDAO airlinesDAO = new AirlinesDAO();
        airlinesDAO.remove(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Remove a Customer from the database.
     * @param customer The customer to be removed.
     */
    public void removeCustomer(Customer customer) {
        if (customer == null) throw new NullPointerException(CUSTOMER_NULL_EXCEPTION);
        CustomersDAO customersDAO = new CustomersDAO();
        customersDAO.remove(customer);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds an Administrator to the database.
     * @param administrator The administrator to be added.
     */
    public void addAdministrator(Administrator administrator) {
        if (administrator == null) throw new NullPointerException(ADMIN_NULL_EXCEPTION);
        AdministratorDAO administratorDAO = new AdministratorDAO();
        administratorDAO.add(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an Administrator from the database.
     * @param administrator The administrator to be removed.
     */
    public void removeAdministrator(Administrator administrator) {
        if (administrator == null) throw new NullPointerException(ADMIN_NULL_EXCEPTION);
        AdministratorDAO administratorDAO = new AdministratorDAO();
        administratorDAO.remove(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
}
