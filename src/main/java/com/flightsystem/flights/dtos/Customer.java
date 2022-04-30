package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.flightsystem.flights.dtos.DTOConstants.*;
/**
 * Data Transfer Object (DTO) class of Customer
 * @author  Oshri Nuri
 * @version 1.3
 */
@Getter @Setter @ToString
@EqualsAndHashCode
public class Customer implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String creditCardNumber;
    private long userId;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public Customer(long customerId, String firstName, String lastName, String address, String phoneNumber,
                    String creditCardNumber, long userId) {
        checkValidity(customerId,firstName,lastName,address,phoneNumber,creditCardNumber,userId);
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.userId = userId;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    private static void checkValidity(long customerId, String firstName, String lastName, String address, String phoneNumber,
                                      String creditCardNumber, long userId) {
        if (firstName == null || lastName == null) throw new NullPointerException(NULL_NAME_EXCEPTION);
        if (address == null) throw new NullPointerException(NULL_ADDRESS_EXCEPTION);
        if (phoneNumber == null) throw new NullPointerException(NULL_PHONE_EXCEPTION);
        if (creditCardNumber == null) throw new NullPointerException(NULL_CREDIT_CARD_EXCEPTION);
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty())
            throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION);
        if (!firstName.matches(REGEX_LETTERS_SPACES_ONLY) || !lastName.matches(REGEX_LETTERS_SPACES_ONLY))
            throw new IllegalArgumentException(INVALID_NAME_EXCEPTION);
        if (address.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_ADDRESS_EXCEPTION);
        if (phoneNumber.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_PHONE_EXCEPTION);
        if (creditCardNumber.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_CREDIT_CARD_EXCEPTION);
        if (customerId <= 0 || userId <= 0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
    }
}
