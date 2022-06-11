package nl.inholland.ships.shipsapi.steps.manufacturers;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.service.ManufacturerService;
import nl.inholland.ships.shipsapi.steps.BaseStepDefinations;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class ManufacturerStepDefs extends BaseStepDefinations implements En {

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;
    private Integer status;

    private Manufacturer manufacturer;

    private ManufacturerService manufacturerService;


    public ManufacturerStepDefs() {

        // Senario 1
        When("the user call get endpoint", () -> {
            response = restTemplate.exchange(getBaseUrl() + "/manufacturers", HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });

        Then("the user receives status code of {int}", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });

        // Senario 2
        Given("^I have a valid manufacturer object with id (\\d+) and name \"([^\"]*)\" and affiliation \"([^\"]*)\" and ceo \"([^\"]*)\"$", (Long id, String name, String affiliation, String ceo) -> {
            manufacturer = new Manufacturer(id, name, affiliation, ceo);
        });

        When("^I make a post request to manufacturer endpoint$", () -> {
            response = restTemplate.exchange(getBaseUrl() + "/manufacturers", HttpMethod.POST, new HttpEntity<>(manufacturer, httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });

        Then("^I should receive a status code of (\\d+)$", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });

    }
}
