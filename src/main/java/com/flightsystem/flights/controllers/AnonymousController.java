package com.flightsystem.flights.controllers;

import com.flightsystem.flights.controllers.registration.UserData;
import com.flightsystem.flights.daos.exceptions.EmailAlreadyExistException;
import com.flightsystem.flights.daos.exceptions.UsernameAlreadyExistException;
import com.flightsystem.flights.dtos.AirlineCompany;
import com.flightsystem.flights.dtos.Country;
import com.flightsystem.flights.dtos.Flight;
import com.flightsystem.flights.dtos.User;
import com.flightsystem.flights.facades.AnonymousFacade;
import com.flightsystem.flights.security.MyUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
@Controller
public class AnonymousController {
    private final AnonymousFacade anonymousFacade;
    private static final int ROLE_CUSTOMER = 1;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping({"/","login"})
    public String login(final Model model) {
        model.addAttribute("userData", new UserData());
        return "login";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/home")
    public String home() {
        return "homepage";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @RequestMapping("/login/error")
    public String loginError() {
        return "error/login-error";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @RequestMapping("/getmyprincipal")
    @ResponseBody
    public String currentUserName(@AuthenticationPrincipal MyUserPrincipal principal) {
        return principal.getUsername() + " (" + getParsedRole(principal.getAuthorities()) + ")";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/flights")
    @ResponseBody
    public List<Flight> getAllFlights() {
        return anonymousFacade.getAllFlights();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/flights/{flightId}")
    @ResponseBody
    public Flight getFlightById(@PathVariable("flightId") int flightId) {
        return anonymousFacade.getFlightById(flightId);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/flightsByParams")
    @ResponseBody
    public List<Flight> getFlightsByParameters(@RequestParam("originCountryId") int originCountryId,
                               @RequestParam("destinationCountryId") int destinationCountryId,
                               @RequestParam("departureDate")  @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate) {
        return anonymousFacade.getFlightsByParameters(originCountryId,destinationCountryId,
                new Timestamp(departureDate.getTime()));
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/airlines")
    @ResponseBody
    public List<AirlineCompany> getAllAirlines() {
        return anonymousFacade.getAllAirlines();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/airlines/{airlineId}")
    @ResponseBody
    public AirlineCompany getAirlineById(@PathVariable("airlineId") int airlineId) {
        return anonymousFacade.getAirlineById(airlineId);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/airlines/{airlineName}/{countryId}")
    @ResponseBody
    public AirlineCompany getAirlineByParameters(@PathVariable("airlineName") String airlineName,
                                                 @PathVariable("countryId") int countryId) {
        return anonymousFacade.getAirlineByParameters(airlineName,countryId);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/countries")
    @ResponseBody
    public List<Country> getAllCountries() {
        return anonymousFacade.getAllCountries();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/countries/{countryId}")
    @ResponseBody
    public Country getCountryById(@PathVariable("countryId") int countryId) {
        return anonymousFacade.getCountryById(countryId);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/registerSuccess")
    public String postRegister() {
        return "registerSuccess";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/register")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model){
      if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "login";
        }
        try {
            anonymousFacade.addCustomer(new User(0,userData.getUsername(),userData.getPassword(),userData.getEmail(),
                    ROLE_CUSTOMER,"",true));
        }catch (EmailAlreadyExistException e) {
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "/login";
        } catch (UsernameAlreadyExistException e) {
            bindingResult.rejectValue("username", "userData.username","Username already exists.");
            model.addAttribute("registrationForm", userData);
            return "/login";
        }
        return "/registerSuccess";
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    private String getParsedRole(Collection<? extends GrantedAuthority> authorities) {
        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            sb.append(switch (authority.getAuthority()) {
                case "ROLE_ADMIN" -> "Admin";
                case "ROLE_CUSTOMER" -> "Customer";
                case "ROLE_AIRLINE" -> "Airline";
                default -> throw new IllegalArgumentException("Invalid role");
            });
        }
        return sb.toString();
    }
}
