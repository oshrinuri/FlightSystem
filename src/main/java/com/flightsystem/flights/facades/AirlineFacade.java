package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.*;
import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.services.MyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.flightsystem.flights.facades.FacadeConstants.FLIGHT_NOT_OWNED_EXCEPTION;
import static com.flightsystem.flights.facades.FacadeConstants.FLIGHT_NULL_EXCEPTION;
/**
 * Facade class of Airline.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */
@Component
public class AirlineFacade extends AnonymousFacade {
    @Autowired public AirlineFacade(MyTokenService myTokenService, AdministratorDAO administratorDAO, FlightsDAO flightsDAO, AirlinesDAO airlinesDAO, CountriesDAO countriesDAO, UsersDAO usersDAO, CustomersDAO customersDAO, TicketsDAO ticketsDAO) {
        super(myTokenService, administratorDAO, flightsDAO, airlinesDAO, countriesDAO, usersDAO, customersDAO, ticketsDAO);
    }

    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights operated by the current Airline.
     * @return A list of all Flights operated by the current Airline.
     */
    public List<Flight> getMyFlights() {
        return FlightsDAO.getFlightsByAirlineId(getMyTokenService().getCurrentAuthedPrincipal().getInternalId());
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Adds a new Flight to the current Airline's flights list.
     * @param flight The flight to add into the Airline's flights.
     */
    public void addFlight(Flight flight) {
        if (flight == null) throw new NullPointerException(FLIGHT_NULL_EXCEPTION);
        if (flight.getAirlineCompanyId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId())
            throw new UnsupportedOperationException(FLIGHT_NOT_OWNED_EXCEPTION);
        flightsDAO.add(flight);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Update an existing Flight of the current Airline.
     * @param flight The flight to updated with.
     */
    public void updateFlight(Flight flight) {
        if (flight == null) throw new NullPointerException(FLIGHT_NULL_EXCEPTION);
        if (flight.getAirlineCompanyId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId())
            throw new UnsupportedOperationException(FLIGHT_NOT_OWNED_EXCEPTION);
        flightsDAO.update(flight);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Removes a Flight from current Airline's flights list.
     * @param flight The flight to be removed from the Airline's flights.
     */
    public void removeFlight(Flight flight) {
        if (flight == null) throw new NullPointerException(FLIGHT_NULL_EXCEPTION);
        if (flight.getAirlineCompanyId() != getMyTokenService().getCurrentAuthedPrincipal().getInternalId())
            throw new UnsupportedOperationException(FLIGHT_NOT_OWNED_EXCEPTION);
        flightsDAO.remove(flight);
    }
}
