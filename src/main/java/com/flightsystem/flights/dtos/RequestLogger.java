package com.flightsystem.flights.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document("RequestLogger")
public class RequestLogger {

    @Id
    private String requestId;
    private String requestTime;

    public RequestLogger(String requestId, String requestTime) {
        this.requestId = requestId;
        this.requestTime = requestTime;
    }
}
