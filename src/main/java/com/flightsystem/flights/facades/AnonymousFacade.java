package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.*;
import com.flightsystem.flights.daos.exceptions.EmailAlreadyExistException;
import com.flightsystem.flights.daos.exceptions.UsernameAlreadyExistException;
import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.services.MyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Facade class of Anonymous User.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */

@Component
public class AnonymousFacade extends FacadeBase {

    @Autowired public AnonymousFacade(MyTokenService myTokenService, AdministratorDAO administratorDAO, FlightsDAO flightsDAO, AirlinesDAO airlinesDAO, CountriesDAO countriesDAO, UsersDAO usersDAO, CustomersDAO customersDAO, TicketsDAO ticketsDAO) {
        super(myTokenService, administratorDAO, flightsDAO, airlinesDAO, countriesDAO, usersDAO, customersDAO, ticketsDAO);
    }

    public void addCustomer(User user) throws EmailAlreadyExistException, UsernameAlreadyExistException {
        long userId = createNewUser(user);
        String suffix = UUID.randomUUID().toString();
        Customer customer = new Customer(1, // Temporary values only - will be overridden as soon as Customer
                "temp",                              // logs in
                "temp",
                "temp",
                "temp_" + suffix,
                "temp_" + suffix,userId);
        customersDAO.add(customer);
    }
}
