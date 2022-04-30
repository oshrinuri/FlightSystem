package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.flightsystem.flights.dtos.DTOConstants.*;

/**
 * Data Transfer Object (DTO) class of Administrator.
 * @author  Oshri Nuri
 * @version 1.3
 */
@EqualsAndHashCode
@Getter @Setter @ToString
public class Administrator implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private long adminId;
    private String firstName;
    private String lastName;
    private long userId;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public Administrator(long adminId, String firstName, String lastName, long userId) {
        if (firstName == null || lastName == null) throw new NullPointerException(NULL_NAME_EXCEPTION);
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION);
        if (!firstName.matches(REGEX_LETTERS_SPACES_ONLY) || !lastName.matches(REGEX_LETTERS_SPACES_ONLY))
            throw new IllegalArgumentException(INVALID_NAME_EXCEPTION);
        if (adminId < 0 || userId < 0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }
}
