package com.flightsystem.flights.controllers;

import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.dtos.Ticket;
import com.flightsystem.flights.facades.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Customer REST Controller implementation
 * @author  Oshri Nuri
 * @version 1.3
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired private CustomerFacade customerFacade;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/tickets")
    public void addTicket(@RequestBody Ticket ticket) {
        customerFacade.addTicket(ticket);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @DeleteMapping("/tickets")
    public void removeTicket(@RequestBody Ticket ticket) {
        customerFacade.removeTicket(ticket);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/tickets")
    public List<Ticket> getMyTickets() {
       return customerFacade.getMyTickets();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PutMapping("/")
    public void updateCustomer(@RequestBody Customer customer) {
        customerFacade.updateCustomer(customer);
    }
}
