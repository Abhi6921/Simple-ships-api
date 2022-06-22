package nl.inholland.ships.shipsapi.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.service.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
class ManufacturerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private ManufacturerService manufacturerService;
    private Manufacturer manufacturer;
    private Manufacturer manufacturer1;

    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmhpIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTU5MzM3OTQsImV4cCI6MTY1NTkzNzM5NH0.S_6WS7o1YrD3DbiQPT4wILvsp1yIt-89PtPAlDINlOo";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY1NTkzMzYxMywiZXhwIjoxNjU1OTM3MjEzfQ.jZczzF4iVwgB42Pgv1CoP7HVZWEhtT550AGd6hdyyPE";
    @BeforeEach
    public void setup() {
        manufacturer = new Manufacturer(10005L, "xyz corporation", "random alliance", "Abhishek Narvekar");
        manufacturer1 = new Manufacturer(10006L, "abc corporation", "random 2 corporation", "Patrick Jane");
    }

    @Test
    public void getAllManufacturersShouldReturnJsonArray() throws Exception {

        given(manufacturerService.getAllManufacturers()).willReturn(Arrays.asList(manufacturer, manufacturer1));

        this.mvc.perform(get("/manufacturers").header("Authorization", "Bearer " + VALID_TOKEN_USER)).andExpect(
                status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(manufacturer.getName()));
    }

    @Test
    public void postingAManufacturerShouldReturn201Created() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Manufacturer manufacturer = new Manufacturer(10006L, "xyz corporation", "my alliance", "Thresa Lisbon");

        this.mvc.perform(post("/manufacturers").header("Authorization", "Bearer " + VALID_TOKEN_ADMIN)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(manufacturer)))
                .andExpect(status().isCreated());
    }
}