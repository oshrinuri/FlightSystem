package com.flightsystem.flights.services;

import com.flightsystem.flights.daos.UsersDAO;
import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired private UsersDAO usersDAO;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = usersDAO.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        long internalUserId;
        if (user.getUserRole() != 2) {
            internalUserId = usersDAO.getInternalId(user);
        } else {
            internalUserId = 0;
        }
        return new MyUserPrincipal(user, internalUserId);
    }
}