package nl.inholland.ships.shipsapi.controller;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.model.Ship;
import nl.inholland.ships.shipsapi.model.dto.ShipDTO;
import nl.inholland.ships.shipsapi.service.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
class ShipControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private ShipService shipService;

    private Manufacturer manufacturer;
    private Manufacturer manufacturer1;

    private Ship ship1;
    private Ship ship2;

    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTU4NTQzMDIsImV4cCI6MTY1NTg1NzkwMn0.cSl0vi2NHzt2jANQzmiiHgVmvzO7Qt8G_KoqOsZNpZU";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teTEzIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjU1ODU0OTk0LCJleHAiOjE2NTU4NTg1OTR9.pGa1P7Cedzzqvd28j3kndGj9BPF0_oIk32z3-3jiQHI";

    @BeforeEach
    public void setup() {
        manufacturer = new Manufacturer(10005L, "xyz corporation", "random alliance", "Abhishek Narvekar");
        manufacturer1 = new Manufacturer(10006L, "abc corporation", "random 2 corporation", "Patrick Jane");
        // Manufacturer manufacturer, String name, String type, Double length, long cost
        ship1 = new Ship(manufacturer, "The Revenge Swashbucklers", "Bulk Carrier", 230.00, 3400000);
        ship2 = new Ship(manufacturer1, "The Barbaric Grail", "Offshore Ships", 500.00, 900000);
    }

    @Test
    public void getAllShipsShouldReturnJsonArray() throws Exception {

        given(shipService.getAllShips()).willReturn(Arrays.asList(ship1, ship2));

        this.mvc.perform(get("/ships").header("Authorization", "Bearer " + VALID_TOKEN_USER)).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(ship1.getName()));
    }

    // TODO: finish this test by today
    @Test
    public void postingAShipShouldReturn201Created() {
        ObjectMapper mapper = new ObjectMapper();

        ShipDTO shipDTO = new ShipDTO();
        shipDTO.setName("abc name");
        shipDTO.setManufacturerName("xyz corporation");
        shipDTO.setType("some type");
        shipDTO.setLength(300.00);
        shipDTO.setCost(3000000);

        //this.mvc.perform(post("/ships"))
    }


}