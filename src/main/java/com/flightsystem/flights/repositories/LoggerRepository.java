package com.flightsystem.flights.repositories;

import com.flightsystem.flights.dtos.RequestLogger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoggerRepository extends MongoRepository<RequestLogger, String> {}