package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.*;
import com.flightsystem.flights.daos.exceptions.EmailAlreadyExistException;
import com.flightsystem.flights.daos.exceptions.UsernameAlreadyExistException;
import com.flightsystem.flights.dtos.AirlineCompany;
import com.flightsystem.flights.dtos.Country;
import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.services.MyTokenService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

import static com.flightsystem.flights.facades.FacadeConstants.*;
/**
 * Abstract base class for all Facade types.
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */

@RequiredArgsConstructor
@Component
public abstract class FacadeBase {
    @Getter protected final  MyTokenService myTokenService;
    protected final AdministratorDAO administratorDAO;
    protected final FlightsDAO flightsDAO;
    protected final AirlinesDAO airlinesDAO;
    protected final CountriesDAO countriesDAO;
    protected final UsersDAO usersDAO;
    protected final CustomersDAO customersDAO;
    protected final TicketsDAO ticketsDAO;
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Flights from DB.
     * @return a list of all Flights.
     */
    public List<Flight> getAllFlights() {
        return flightsDAO.getAll();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Retrieves a flight from database by ID.
     * @param id Flight's id to be pulled.
     * @return Pulled Flight object.
     */
    public Flight getFlightById(int id) {
        if (id <= 0) throw new IllegalArgumentException(ID_NEGATIVE_EXCEPTION);
        Flight flight = flightsDAO.get(id);
        if (flight == null) throw new NoSuchElementException(String.format(NOT_FOUND_BY_ID_EXCEPTION,"Flight",id));
        return flight;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of Flights departing origin country and landing at destination country on a certain date.
     * @param originCountryId Origin country ID.
     * @param destinationCountryId Destination country ID.
     * @param departureDate Date of departure.
     * @return A list of Flights which fulfill above requirements.
     */
    public List<Flight> getFlightsByParameters(int originCountryId, int destinationCountryId,
                                               Timestamp departureDate) {
        if (originCountryId <= 0 || destinationCountryId <= 0) throw new IllegalArgumentException(ID_NEGATIVE_EXCEPTION);
        if (departureDate == null) throw new NullPointerException(DATE_NULL_EXCEPTION);
        return FlightsDAO.getFlightsByParams(originCountryId,destinationCountryId,departureDate);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Airlines from DB.
     * @return a list of all Airlines.
     */
    public List<AirlineCompany> getAllAirlines() {
        return airlinesDAO.getAll();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Retrieves an airline from database by ID.
     * @param id Airline's id to be pulled.
     * @return Pulled AirlineCompany object.
     */
    public AirlineCompany getAirlineById(int id) {
        if (id <= 0) throw new IllegalArgumentException(ID_NEGATIVE_EXCEPTION);
        AirlineCompany airline = airlinesDAO.get(id);
        if (airline == null) throw new NoSuchElementException(String.format(NOT_FOUND_BY_ID_EXCEPTION,"Airline",id));
        return airline;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Retrieves an airline from database by Name and Country ID.
     * @param airlineName Airline's name
     * @param countryId Country's ID.
     * @return Pulled AirlineCompany object.
     */
    public AirlineCompany getAirlineByParameters(String airlineName, int countryId) {
        if (airlineName == null) throw new NullPointerException(AIRLINE_NULL_EXCEPTION);
        if (airlineName.trim().length() == 0) throw new IllegalArgumentException(AIRLINE_EMPTY_EXCEPTION);
        if (countryId <= 0) throw new IllegalArgumentException(ID_NEGATIVE_EXCEPTION);
        AirlineCompany airline = airlinesDAO.getAirlineByParameters(airlineName,countryId);
        if (airline == null) throw new NoSuchElementException(String.format(NOT_FOUND_BY_PARAMS_EXCEPTION,airlineName,
                countryId));
        return airline;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Retrieves a list of all Countries from DB.
     * @return a list of all Countries.
     */
    public List<Country> getAllCountries() {
       return countriesDAO.getAll();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * Retrieves a country from database by ID.
     * @param id Country's id to be pulled.
     * @return Pulled Country object.
     */
    public Country getCountryById(int id) {
        if (id <= 0) throw new IllegalArgumentException(ID_NEGATIVE_EXCEPTION);
        Country country = countriesDAO.get(id);
        if (country == null) throw new NoSuchElementException(String.format(NOT_FOUND_BY_ID_EXCEPTION,"Country",id));
        return country;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     *
     * @param user User object create in database.
     * @return Created User ID from database.
     */
    public long createNewUser(User user) throws EmailAlreadyExistException, UsernameAlreadyExistException {
        if (user == null) throw new NullPointerException(USER_NULL_EXCEPTION);
        if (usersDAO.getAll().stream().anyMatch(u -> u.getEmail().equals(user.getEmail())))
            throw new EmailAlreadyExistException();
        if (usersDAO.getAll().stream().anyMatch(u -> u.getUsername().equals(user.getUsername())))
            throw new UsernameAlreadyExistException();
        usersDAO.add(user);
        return usersDAO.getByUsername(user.getUsername()).getUserId();
    }
}
