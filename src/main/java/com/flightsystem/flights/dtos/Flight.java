package com.flightsystem.flights.dtos;

import lombok.*;

import java.sql.Timestamp;

import static com.flightsystem.flights.dtos.DTOConstants.*;
/**
 * Data Transfer Object (DTO) class of Flight.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter @ToString
public class Flight implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private long flightId;
    private long airlineCompanyId;
    private int originCountryId;
    private int destinationCountryId;
    private Timestamp departureTime;
    private Timestamp landingTime;
    private int remainingTickets;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public Flight(long flightId, long airlineCompanyId, int originCountryId, int destinationCountryId,
                  Timestamp departureTime, Timestamp landingTime, int remainingTickets) {
        if (departureTime == null || landingTime == null) throw new NullPointerException(NULL_TIMESTAMP_EXCEPTION);
        if (remainingTickets < 0) throw new IllegalArgumentException(TICKET_COUNT_EXCEPTION);
        if (flightId <= 0 || airlineCompanyId <=0 || originCountryId <= 0 || destinationCountryId <=0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
        if (landingTime.before(departureTime)) throw new IllegalArgumentException(INVALID_SCHEDULE_EXCEPTION);
        if (originCountryId == destinationCountryId) throw new IllegalArgumentException(INVALID_ROUTE_EXCEPTION);
        this.flightId = flightId;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }
}
