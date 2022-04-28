package com.flightsystem.flights.services;

import com.flightsystem.flights.security.MyUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyTokenService {
        public MyUserPrincipal getCurrentAuthedPrincipal(){
            return (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }
