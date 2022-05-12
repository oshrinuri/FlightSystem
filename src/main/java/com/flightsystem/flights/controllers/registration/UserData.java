package com.flightsystem.flights.controllers.registration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class UserData implements Serializable {
    @NotEmpty(message = "Username can not be empty.")
    private String username;

    @NotEmpty(message = "Email can not be empty.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotEmpty(message = "Password can not be empty.")
    private String password;

}