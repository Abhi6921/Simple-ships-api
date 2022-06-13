package nl.inholland.ships.shipsapi.IT.steps.manufacturers;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.IT.steps.BaseStepDefinations;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class ManufacturerStepDefs extends BaseStepDefinations implements En {


    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTUxMzAyNTQsImV4cCI6MTY1NTEzMzg1NH0.61BxMZAktOqMsfpnEAiqlRseWSiCigKgJdYx-Pm-Xkg";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teTEzIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjU1MTMwMzE0LCJleHAiOjE2NTUxMzM5MTR9.N7AXrNiyjPIpAE0v1hoQGzrCkebXSZiTsY-3j9NhFpc";
    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTkwNSwiZXhwIjoxNjUzMzExOTA1fQ.mKFrXM15WCXVNbSFNpqYix_xsMjsH_M31hiFf-o7JXs";
    private static final String INVALID_TOKEN = "invalidtoken";
    
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;
    private Integer status;

    private Manufacturer manufacturer;

    private String token;


    public ManufacturerStepDefs() {

        Given("^I have a valid token for role \"([^\"]*)\"$", (String role) -> {
                switch(role) {
                    case "user" -> token = VALID_TOKEN_USER;
                    case "admin" -> token = VALID_TOKEN_ADMIN;
                }
        });
        // Senario 1
        When("I call get manufacturers endpoint", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization",  "Bearer " + token);
            request = new HttpEntity<>(null, httpHeaders);
            response = restTemplate.exchange(getBaseUrl() + "/manufacturers", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });

        Then("I receive status code of {int}", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });

        // Senario 2
        Given("^I have a valid manufacturer object with id (\\d+) and name \"([^\"]*)\" and affiliation \"([^\"]*)\" and ceo \"([^\"]*)\"$", (Long id, String name, String affiliation, String ceo) -> {
            manufacturer = new Manufacturer(id, name, affiliation, ceo);
        });

        When("^I make a post request to manufacturers endpoint$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", "Bearer " + token);
            httpHeaders.add("Content-Type", "application/json");
            request = new HttpEntity<>(mapper.writeValueAsString(manufacturer), httpHeaders);
            //response = restTemplate.exchange(getBaseUrl() + "/manufacturers", HttpMethod.POST, new HttpEntity<>(manufacturer, httpHeaders), String.class);
            response = restTemplate.postForEntity(getBaseUrl() + "/manufacturers", request, String.class);
            status = response.getStatusCodeValue();
        });

        Then("^I should receive a status code of (\\d+)$", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });


        Given("^I have an invalid token$", () -> {
            token = INVALID_TOKEN;
        });


    }
}
