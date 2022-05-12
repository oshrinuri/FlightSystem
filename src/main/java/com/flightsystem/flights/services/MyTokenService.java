package com.flightsystem.flights.services;

import com.flightsystem.flights.security.MyUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyTokenService {
        public MyUserPrincipal getCurrentAuthedPrincipal(){
            return (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }
