package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.CustomersDAO;
import com.flightsystem.flights.daos.FlightsDAO;
import com.flightsystem.flights.daos.TicketsDAO;
import com.flightsystem.flights.dtos.Customer;
import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.dtos.Ticket;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.flightsystem.flights.facades.FacadeConstants.*;

/**
 * Facade class of Customer.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@NoArgsConstructor
@Component
public class CustomerFacade extends AnonymousFacade {
    /***
     * Update the current existing Customer (Must be with the same ID of LoginToken).
     * @param customer The customer to be updated with.
     */
    public void updateCustomer(Customer customer) {
        if (customer == null) throw new NullPointerException(CUSTOMER_NULL_EXCEPTION);
        if (customer.getCustomerId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId()) throw new
                UnsupportedOperationException(NOT_ELIGIBLE_EXCEPTION);
        CustomersDAO customersDAO = new CustomersDAO();
        customersDAO.update(customer);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a ticket to the current Customer's flights.
     * @param ticket The ticket to be added.
     */
    public void addTicket(Ticket ticket) {
        if (ticket == null) throw new NullPointerException(TICKET_NULL_EXCEPTION);
        if (ticket.getCustomerId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId()) throw new
                UnsupportedOperationException(TICKET_NOT_OWNED_EXCEPTION);
        TicketsDAO ticketsDAO = new TicketsDAO();
        Flight flight = new FlightsDAO().get((int)ticket.getFlightId());
        flight.setRemainingTickets(flight.getRemainingTickets()-1); // Purchasing a ticket -> Less remaining available
        ticketsDAO.add(ticket);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes a ticket from the current Customer's flights.
     * @param ticket The ticket to be removed.
     */
    public void removeTicket(Ticket ticket) {
        if (ticket == null) throw new NullPointerException(TICKET_NULL_EXCEPTION);
        if (ticket.getCustomerId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId()) throw new
                UnsupportedOperationException(TICKET_NOT_OWNED_EXCEPTION);
        TicketsDAO ticketsDAO = new TicketsDAO();
        Flight flight = new FlightsDAO().get((int)ticket.getFlightId());
        flight.setRemainingTickets(flight.getRemainingTickets()+1); // Removing a ticket -> More remaining available
        ticketsDAO.remove(ticket);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of current Customer's tickets.
     * @return A list of current Customer's tickets.
     */
    public List<Ticket> getMyTickets() {
        return TicketsDAO.getTicketsByCustomerId(getMyTokenService().getCurrentAuthedPrincipal().getInternalId());
    }
}
