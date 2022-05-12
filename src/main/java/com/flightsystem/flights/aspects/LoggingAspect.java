package com.flightsystem.flights.aspects;

import com.flightsystem.flights.dtos.RequestLogger;
import com.flightsystem.flights.repositories.LoggerRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {
    LoggerRepository loggerRepository;

    @Before("execution(* com.flightsystem.flights.controllers.*.add*(..))")
    public void logRequestTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM//yyyy HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);
        RequestLogger log = new RequestLogger(UUID.randomUUID().toString(), time);
        loggerRepository.insert(log);
    }
}
