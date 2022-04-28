package com.flightsystem.flights.facades.exceptions;

/***
 * Custom exception to be thrown when login() procedure fails (i.e: invalid password / user doesn't exist).
 */
public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
