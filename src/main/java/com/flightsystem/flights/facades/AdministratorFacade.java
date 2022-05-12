package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.*;
import com.flightsystem.flights.dtos.Administrator;
import com.flightsystem.flights.dtos.AirlineCompany;
import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.services.MyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.flightsystem.flights.facades.FacadeConstants.*;
/**
 * Facade class of Administrator.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */
@Component
public class AdministratorFacade extends AnonymousFacade {
    @Autowired public AdministratorFacade(MyTokenService myTokenService, AdministratorDAO administratorDAO, FlightsDAO flightsDAO, AirlinesDAO airlinesDAO, CountriesDAO countriesDAO, UsersDAO usersDAO, CustomersDAO customersDAO, TicketsDAO ticketsDAO) {
        super(myTokenService, administratorDAO, flightsDAO, airlinesDAO, countriesDAO, usersDAO, customersDAO, ticketsDAO);
    }

    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Customers from database.
     * @return A list of all Customers.
     */
    public List<Customer> getAllCustomers() {
        return customersDAO.getAll();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds an Airline to the database.
     * @param airlineCompany The airline to be added.
     */
    public void addAirline(AirlineCompany airlineCompany) {
        if (airlineCompany == null) throw new NullPointerException(AIRLINE_NULL_EXCEPTION);
        airlinesDAO.add(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an Airline from the database.
     * @param airlineCompany The airline to be removed.
     */
    public void removeAirline(AirlineCompany airlineCompany) {
        if (airlineCompany == null) throw new NullPointerException(AIRLINE_NULL_EXCEPTION);
        airlinesDAO.remove(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Remove a Customer from the database.
     * @param customer The customer to be removed.
     */
    public void removeCustomer(Customer customer) {
        if (customer == null) throw new NullPointerException(CUSTOMER_NULL_EXCEPTION);
        customersDAO.remove(customer);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds an Administrator to the database.
     * @param administrator The administrator to be added.
     */
    public void addAdministrator(Administrator administrator) {
        if (administrator == null) throw new NullPointerException(ADMIN_NULL_EXCEPTION);
        administratorDAO.add(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes an Administrator from the database.
     * @param administrator The administrator to be removed.
     */
    public void removeAdministrator(Administrator administrator) {
        if (administrator == null) throw new NullPointerException(ADMIN_NULL_EXCEPTION);
        administratorDAO.remove(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
}
