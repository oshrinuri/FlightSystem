package com.flightsystem.flights.services;

import com.flightsystem.flights.security.MyUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
/**
 * Custom Token Service.
 * @author  Oshri Nuri
 * @version 1.3
 */
@Service
public class MyTokenService {
        public MyUserPrincipal getCurrentAuthedPrincipal(){
            return (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }
