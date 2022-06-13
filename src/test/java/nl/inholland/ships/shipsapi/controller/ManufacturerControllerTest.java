package nl.inholland.ships.shipsapi.controller;
import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.service.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {ManufacturerController.class})
@AutoConfigureMockMvc*/
@SpringBootTest
class ManufacturerControllerTest {

   /* @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManufacturerService manufacturerService;*/

    @Autowired
    private ManufacturerService manufacturerService;

    Manufacturer manufacturer1;
    Manufacturer test;

    @BeforeEach
    public void setup() {
        test = new Manufacturer(1000010, "xyz corporation", "my alliance", "skdnvsdn");
        manufacturer1 = new Manufacturer(100009, "vskdncksd", "this affiliation", "vwkjdnkcnd");
    }

    @Test
    void getAllManufacturers() throws Exception {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        assertNotEquals(manufacturers.size(), 0);
    }

    @Test
    void createManufacturer() {
        manufacturerService.addManufacturer(test);
        Manufacturer manufacturer = manufacturerService.getByName("xyz corporation");
        assertEquals(manufacturer.getName(), test.getName());
    }

    @Test
    void findManufacturersByAffiliation() {
        assertNotEquals(manufacturerService.getAllByAffiliation(manufacturer1.getAffiliation()), 0);
    }
}