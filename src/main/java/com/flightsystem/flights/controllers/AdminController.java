package com.flightsystem.flights.controllers;

import com.flightsystem.flights.dtos.Administrator;
import com.flightsystem.flights.dtos.AirlineCompany;
import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.facades.AdministratorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Admin REST Controller implementation
 * @author  Oshri Nuri
 * @version 1.3
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired private AdministratorFacade administratorFacade;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/customers")
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return administratorFacade.getAllCustomers();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/airlines")
    public void addAirline(@RequestBody AirlineCompany airlineCompany) {
        administratorFacade.addAirline(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/")
    public void addAdministrator(@RequestBody Administrator administrator) {
        administratorFacade.addAdministrator(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @DeleteMapping("/")
    public void removeAdministrator(@RequestBody Administrator administrator) {
        administratorFacade.removeAdministrator(administrator);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @DeleteMapping("/airlines")
    public void removeAirline(@RequestBody AirlineCompany airlineCompany) {
        administratorFacade.removeAirline(airlineCompany);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @DeleteMapping("/customers")
    public void removeCustomer(@RequestBody Customer customer) {
        administratorFacade.removeCustomer(customer);
    }
}
