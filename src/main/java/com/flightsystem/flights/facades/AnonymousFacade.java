package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.CustomersDAO;
import com.flightsystem.flights.daos.exceptions.EmailAlreadyExistException;
import com.flightsystem.flights.daos.exceptions.UsernameAlreadyExistException;
import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.dtos.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Facade class of Anonymous User.
 * @author  Oshri Nuri
 * @version 1.3
 */
@NoArgsConstructor
@Component
public class AnonymousFacade extends FacadeBase {
    @Autowired CustomersDAO customersDAO;
    /* ------------------------------------------------------------------------------------------------------------------- */

    /***
     * Creates a new Customer entity in database, based on a User object.
     * @param user User to be linked with customer.
     * @throws EmailAlreadyExistException Email address already exists in database.
     * @throws UsernameAlreadyExistException Username already exists in database.
     */
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
