package com.flightsystem.flights.repositories;

import com.flightsystem.flights.dtos.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer> {}
