package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.flightsystem.flights.dtos.DTOConstants.NEGATIVE_VALUE_EXCEPTION;
/**
 * Data Transfer Object (DTO) class of Ticket.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@EqualsAndHashCode
@Getter @Setter @ToString
public class Ticket implements DTO {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private long ticketId;
    private long flightId;
    private long customerId;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public Ticket(long ticketId, long flightId, long customerId) {
        if (ticketId <= 0 || flightId <=0 || customerId <=0) throw new IllegalArgumentException(NEGATIVE_VALUE_EXCEPTION);
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.customerId = customerId;
    }
}
