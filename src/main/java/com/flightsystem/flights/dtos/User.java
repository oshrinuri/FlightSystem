package com.flightsystem.flights.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.flightsystem.flights.dtos.DTOConstants.*;
/**
 * Data Transfer Object (DTO) class of User.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@EqualsAndHashCode
@Getter @Setter @ToString
public class User implements DTO {
    /* Constants --------------------------------------------------------------------------------------------------------- */
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String INVALID_PASSWORD_EXCEPTION = "Password is too short. " + MIN_PASSWORD_LENGTH +
            " characters minimum.";
    /* Class members ------------------------------------------------------------------------------------------------------*/
    @Autowired private PasswordEncoder passwordEncoder; // Database password encoder - BCrypt
    /* ------------------------------------------------------------------------------------------------------------------- */
    private long userId;
    private String username;
    private String password;
    private String email;
    private int userRole;
    private String thumbnailURL;
    private boolean isEnabled;
    /* Constructor ------------------------------------------------------------------------------------------------------- */
    public User(long userId, String username, String password, String email, int userRole, String thumbnailURL,
                boolean isEnabled) {
        if (username == null) throw new NullPointerException(NULL_USERNAME_EXCEPTION);
        if (password == null) throw new NullPointerException(NULL_PASSWORD_EXCEPTION);
        if (email == null) throw new NullPointerException(NULL_EMAIL_EXCEPTION);
        if (password.length() < MIN_PASSWORD_LENGTH) throw new IllegalArgumentException(INVALID_PASSWORD_EXCEPTION);
        if (!email.matches(REGEX_EMAIL_VALIDATION)) throw new IllegalArgumentException(INVALID_EMAIL_EXCEPTION);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.thumbnailURL = thumbnailURL;
        this.isEnabled = true;
    }
}
