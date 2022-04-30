package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.flightsystem.flights.dtos.DTOConstants.*;
/**
 * Data Transfer Object (DTO) class of Country.
 * @author  Oshri Nuri
 * @version 1.3
 */
@EqualsAndHashCode
@Getter @Setter @ToString
public class Country implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private int countryId;
    private String name;
    private String flagURL;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public Country(int countryId, String name, String flagURL) {
        if (countryId <=0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
        if (name == null) throw new NullPointerException(NULL_NAME_EXCEPTION);
        if (name.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION);
        if (!name.matches(REGEX_LETTERS_SPACES_ONLY)) throw new IllegalArgumentException(INVALID_NAME_EXCEPTION);
        this.countryId = countryId;
        this.name = name;
        this.flagURL = flagURL;
    }
}
