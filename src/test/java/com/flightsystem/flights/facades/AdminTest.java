package com.flightsystem.flights.facades;

import com.flightsystem.flights.daos.CustomersDAO;
import com.flightsystem.flights.dtos.Customer;
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
class AdminTest {
    private static HttpClient client = null;
    private static HttpRequest request = null;
    private static HttpResponse<String> response = null;
    private static CustomersDAO customersDAO = new CustomersDAO();
    private static final GsonBuilder builder = new GsonBuilder();
    private static Gson gson;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @BeforeAll
    public static void setup() {
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create("http://localhost:8080/admin/customers"))
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        gson = builder.create();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Test
    @DisplayName("(GET) Get a list of all customers [check only the first one]")
    void testGetCustomers() {
        Customer[] customers = gson.fromJson(response.body(), Customer[].class);
        Customer expected = customersDAO.getAll().get(0);
        assertEquals(expected, customers[0]);
    }
}