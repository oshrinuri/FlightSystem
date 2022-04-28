package com.flightsystem.flights.security;

import com.flightsystem.flights.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import static com.flightsystem.flights.enums.UserRole.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /* Class members ------------------------------------------------------------------------------------------------------*/
    @Autowired private MyUserDetailsService myUserDetailsService;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Bean
    public static BCryptPasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Authentication configuration - only Users from the Database are permitted to authenticate with their credentials.
     * @param auth Auth parameter to config.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    /***
     * Authorization configuration - only allowed UserRoles are permitted to access certain URLs.
     * @param http HttpSecurity parameter to config.
     */
    @Override
    protected void configure(HttpSecurity http) {
        try {
                    http.authorizeRequests()
                    .antMatchers("/","/login","/airlines/**","flights/**","/countries/**","/flightsByParams/**","/user")
                    .permitAll()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/airline/**").hasAnyRole(AIRLINE.toString(), ADMIN.toString())
                    .antMatchers("/admin/customers").hasRole(ADMIN.toString())
                    .antMatchers("/customer/**").hasAnyRole(CUSTOMER.toString(), ADMIN.toString())
                    .antMatchers("/contact-us/").permitAll()
                    .antMatchers("/contact-us/messages/**").hasRole(ADMIN.toString())
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login/error")
                    .defaultSuccessUrl("/home",true)
                    .usernameParameter("username").passwordParameter("password")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout=true")
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                    .and().cors()
                    .and()
                    .csrf()
                    .disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
