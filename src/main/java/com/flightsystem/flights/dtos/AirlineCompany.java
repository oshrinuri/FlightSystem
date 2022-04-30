package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.flightsystem.flights.dtos.DTOConstants.*;

/**
 * Data Transfer Object (DTO) class of AirlineCompany.
 * @version 1.3
 */
@EqualsAndHashCode
@Getter @Setter @ToString
public class AirlineCompany implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private long airlineCompanyId;
    private String airlineCompanyName;
    private int countryId;
    private long userId;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public AirlineCompany(long airlineCompanyId, String airlineCompanyName, int countryId, long userId) {
        if (airlineCompanyId <=0 || countryId <=0 || userId <=0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
        if (airlineCompanyName == null) throw new NullPointerException(NULL_NAME_EXCEPTION);
        if (!airlineCompanyName.matches(REGEX_LETTERS_SPACES_ONLY)) throw new IllegalArgumentException(INVALID_NAME_EXCEPTION);
        if (airlineCompanyName.trim().isEmpty()) throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION);
        this.airlineCompanyId = airlineCompanyId;
        this.airlineCompanyName = airlineCompanyName;
        this.countryId = countryId;
        this.userId = userId;
    }
}
