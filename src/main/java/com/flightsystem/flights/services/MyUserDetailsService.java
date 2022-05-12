package com.flightsystem.flights.services;

import com.flightsystem.flights.daos.UsersDAO;
import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.security.MyUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UsersDAO usersDAO;
    private static final int ROLE_ADMIN = 2;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = usersDAO.getByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        long internalUserId = 0;
        if (user.getUserRole() != ROLE_ADMIN) internalUserId = usersDAO.getInternalId(user);
        return new MyUserPrincipal(user, internalUserId);
    }
}