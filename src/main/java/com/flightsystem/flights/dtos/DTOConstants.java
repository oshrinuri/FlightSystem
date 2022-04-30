package com.flightsystem.flights.dtos;
/**
 * Constants for usage in DTO constructors (Input validation and Exceptions).
 * @author  Oshri Nuri
 * @version 1.3
 */
final class DTOConstants {
    /* Regex literals -----------------------------------------------------------------------------------------------------*/
    static final String REGEX_LETTERS_SPACES_ONLY = "^[a-zA-Z\\s_-]*$";
    static final String REGEX_NUMBERS_ONLY = "^[0-9]*$";
    static final String REGEX_EMAIL_VALIDATION = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /* Exceptions literals ------------------------------------------------------------------------------------------------*/
    static final String NEGATIVE_VALUE_EXCEPTION = "ID number must be positive.";
    static final String INVALID_NAME_EXCEPTION = "Name must contain only alphabetic letters.";
    static final String INVALID_PHONE_EXCEPTION = "Phone number must contain only digits.";
    static final String INVALID_CREDIT_CARD_EXCEPTION = "Credit Card number must contain only digits.";
    static final String INVALID_ROUTE_EXCEPTION = "Flight's origin country cannot be the same as destination.";
    static final String INVALID_SCHEDULE_EXCEPTION = "Flight's landing time cannot be before Departure time.";
    static final String INVALID_EMAIL_EXCEPTION = "Email address is invalid.";
    static final String NULL_NAME_EXCEPTION = "Name cannot be null.";
    static final String NULL_PHONE_EXCEPTION = "Phone number cannot be null.";
    static final String NULL_ADDRESS_EXCEPTION = "Address cannot be null.";
    static final String NULL_CREDIT_CARD_EXCEPTION = "Credit Card number cannot be null.";
    static final String NULL_TIMESTAMP_EXCEPTION = "Departure/Landing time cannot be null.";
    static final String NULL_EMAIL_EXCEPTION = "Email cannot be null.";
    static final String NULL_USERNAME_EXCEPTION = "Username cannot be null.";
    static final String NULL_PASSWORD_EXCEPTION = "Password cannot be null.";
    static final String EMPTY_NAME_EXCEPTION = "Name cannot be empty.";
    static final String EMPTY_PHONE_EXCEPTION = "Phone number cannot be empty.";
    static final String EMPTY_ADDRESS_EXCEPTION = "Address cannot be empty.";
    static final String EMPTY_CREDIT_CARD_EXCEPTION = "Credit Card number cannot be empty.";
    static final String TICKET_COUNT_EXCEPTION = "Remaining tickets must be non-negative.";
    private DTOConstants(){} // private constructor to disable instantiating this class
}
