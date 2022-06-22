package nl.inholland.ships.shipsapi.controller;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmhpIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTU5MzM3OTQsImV4cCI6MTY1NTkzNzM5NH0.S_6WS7o1YrD3DbiQPT4wILvsp1yIt-89PtPAlDINlOo";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY1NTkzMzYxMywiZXhwIjoxNjU1OTM3MjEzfQ.jZczzF4iVwgB42Pgv1CoP7HVZWEhtT550AGd6hdyyPE";

    @Autowired
    private ShipController shipController;

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
    public void postingAShipShouldReturn201Created() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ShipDTO shipDTO = new ShipDTO();
        shipDTO.setName("abc name");
        shipDTO.setManufacturerName("Kuat Drive Yards");
        shipDTO.setType("some type");
        shipDTO.setLength(300.00);
        shipDTO.setCost(3000000);

        //Ship ship = shipController.convertShipDTOToShipToInsertShip(shipDTO);

        this.mvc.perform(post("/ships").header("Authorization", "Bearer " + VALID_TOKEN_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(shipDTO)))
                .andExpect(status().isCreated());
    }




}