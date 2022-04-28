package com.flightsystem.flights.facades;

import com.flightsystem.flights.enums.UserRole;
import lombok.Getter;
@Getter
class LoginToken {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    private final long id;
    private final String userName;
    private final UserRole role;
    /* Constructor --------------------------------------------------------------------------------------------------------*/
    public LoginToken(long id, String userName, UserRole role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }
}
