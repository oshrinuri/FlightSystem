package com.flightsystem.flights.facades;
/**
 * For usage in Facades (Constants and Exceptions literals).
 * @author  Oshri Nuri
 * @version 1.2
 * @since   17/03/2022
 */
final class FacadeConstants {
    /* Exceptions literals ------------------------------------------------------------------------------------------------*/
    static final String AIRLINE_NULL_EXCEPTION = "Airline object cannot be null.";
    static final String AIRLINE_EMPTY_EXCEPTION = "Airline name cannot be empty.";
    static final String CUSTOMER_NULL_EXCEPTION = "Customer object cannot be null.";
    static final String ADMIN_NULL_EXCEPTION = "Admin object cannot be null.";
    static final String NOT_ELIGIBLE_EXCEPTION = "You are not eligible to perform this action.";
    static final String FLIGHT_NOT_OWNED_EXCEPTION = "Flight isn't operated by your Airline.";
    static final String FLIGHT_NULL_EXCEPTION = "Flight object cannot be null.";
    static final String TICKET_NULL_EXCEPTION = "Ticket object cannot be null.";
    static final String TICKET_NOT_OWNED_EXCEPTION = "Ticket doesn't belong to you.";
    static final String NOT_FOUND_BY_ID_EXCEPTION = "%s %d doesn't exist.";
    static final String NOT_FOUND_BY_PARAMS_EXCEPTION = "%s doesn't exist in countryId = %d.";
    static final String ID_NEGATIVE_EXCEPTION = "ID cannot be negative.";
    static final String DATE_NULL_EXCEPTION = "Date cannot be null.";
    static final String USER_NULL_EXCEPTION = "User cannot be null.";
    private FacadeConstants(){}
}
