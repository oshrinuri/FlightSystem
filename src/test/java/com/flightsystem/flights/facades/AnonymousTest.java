package com.flightsystem.flights.facades;

import com.flightsystem.flights.dtos.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AnonymousTest {
    private static HttpResponse<String> response = null;
    private static final GsonBuilder builder = new GsonBuilder();
    private static Gson gson;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @BeforeAll
    public static void setup() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://localhost:8080/countries"))
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        builder.setPrettyPrinting();
        gson = builder.create();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Test
    @DisplayName("(GET) Get a list of all countries [check only first one]")
    void testGetAllCountries() {
        Country[] countries = gson.fromJson(response.body(), Country[].class);
        Country expected = new Country(1,"Andorra","https://flagcdn.com/48x36/ad.png");
        assertEquals(countries[0],expected);
    }
}