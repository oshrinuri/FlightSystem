package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.FlightsDAO;
import com.flightsystem.flights.dtos.Flight;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.flightsystem.flights.facades.FacadeConstants.FLIGHT_NOT_OWNED_EXCEPTION;
import static com.flightsystem.flights.facades.FacadeConstants.FLIGHT_NULL_EXCEPTION;
/**
 * Facade class of Airline.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@NoArgsConstructor
@Component
public class AirlineFacade extends AnonymousFacade {
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
        FlightsDAO flightsDAO = new FlightsDAO();
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
        FlightsDAO flightsDAO = new FlightsDAO();
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
        FlightsDAO flightsDAO = new FlightsDAO();
        flightsDAO.remove(flight);
    }
}
