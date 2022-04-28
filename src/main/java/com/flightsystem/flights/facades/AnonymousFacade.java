package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.UsersDAO;
import com.flightsystem.flights.dtos.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.flightsystem.flights.facades.FacadeConstants.USER_NULL_EXCEPTION;

/**
 * Facade class of Anonymous User.
 * @author  Oshri Nuri
 * @version 1.0
 * @since   17/03/2022
 */
@NoArgsConstructor
@Component
public class AnonymousFacade extends FacadeBase {
    // TODO :: Yet to be finished!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void addCustomer(User user) {
        if (user == null) throw new NullPointerException(USER_NULL_EXCEPTION);
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.add(user);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Generates a random String with the (TESTING PURPOSE ONLY - HELPER METHOD)
     * @param length Desired String length.
     * @return A random String with English alphabet letters.
     */
    private static String randomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
