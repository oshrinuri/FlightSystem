package com.flightsystem.flights.controllers;

import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.facades.AirlineFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("airline")
public class AirlineController {
    private final AirlineFacade airlineFacade;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/flights")
    public List<Flight> getAirlineFlights() {
        return airlineFacade.getMyFlights();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/flights")
    public void addFlight(@RequestBody Flight flight) {
        airlineFacade.addFlight(flight);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PutMapping("/flights")
    public void updateFlight(@RequestBody Flight flight) {
        airlineFacade.updateFlight(flight);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @DeleteMapping("/flights")
    public void removeFlight(@RequestBody Flight flight) {
        airlineFacade.removeFlight(flight);
    }
}
