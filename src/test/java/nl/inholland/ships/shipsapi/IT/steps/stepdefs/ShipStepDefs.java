package nl.inholland.ships.shipsapi.IT.steps.stepdefs;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import nl.inholland.ships.shipsapi.IT.steps.BaseStepDefinations;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.model.dto.ShipDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class ShipStepDefs extends BaseStepDefinations implements En {

    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTU0ODkwNjEsImV4cCI6MTY1NTQ5MjY2MX0.05h_vPjJen1rHWY0admelOGFjDY53TEXGIKs8thhca8";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teTEzIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjU1NDg4Nzc4LCJleHAiOjE2NTU0OTIzNzh9.qN6IytkIvMnVg4qFOysxkzl7zV3mqDhacMQldt7-JG4";
    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTkwNSwiZXhwIjoxNjUzMzExOTA1fQ.mKFrXM15WCXVNbSFNpqYix_xsMjsH_M31hiFf-o7JXs";
    private static final String INVALID_TOKEN = "invalidtoken";

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;
    private Integer status;

    private ShipDTO shipDTO;

    private String token;

    public ShipStepDefs() {

        Given("^I have a valid token for role \"([^\"]*)\" for ships$", (String role) -> {
            switch(role) {
                case "user" -> token = VALID_TOKEN_USER;
                case "admin" -> token = VALID_TOKEN_ADMIN;
            }
        });

        When("^I call get ships endpoint$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization",  "Bearer " + token);
            request = new HttpEntity<>(null, httpHeaders);
            response = restTemplate.exchange(getBaseUrl() + "/ships", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });
        Then("^I receive http status code of (\\d+)$", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });

        And("^I have a valid ship object with name \"([^\"]*)\" and manufacturerName \"([^\"]*)\" and type \"([^\"]*)\" and length (\\d+)\\.(\\d+) and cost (\\d+)$", (String arg0, String arg1, String arg2, Integer arg3, Integer arg4, Integer arg5) -> {
            shipDTO = new ShipDTO();
            shipDTO.setName(arg0);
            shipDTO.setManufacturerName(arg1);
            shipDTO.setType(arg2);
            shipDTO.setLength(arg3);
            shipDTO.setCost(arg4);
        });
        When("^I make a post request to ships$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", "Bearer " + token);
            httpHeaders.add("Content-Type", "application/json");
            request = new HttpEntity<>(mapper.writeValueAsString(shipDTO), httpHeaders);
            //response = restTemplate.exchange(getBaseUrl() + "/manufacturers", HttpMethod.POST, new HttpEntity<>(manufacturer, httpHeaders), String.class);
            response = restTemplate.postForEntity(getBaseUrl() + "/manufacturers", request, String.class);
            status = response.getStatusCodeValue();
        });


    }
}
