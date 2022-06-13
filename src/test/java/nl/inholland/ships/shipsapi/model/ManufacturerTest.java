package nl.inholland.ships.shipsapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerTest {
    private Manufacturer manufacturer;

    @BeforeEach
    void setup() {
        manufacturer = new Manufacturer();
    }

    @Test
    void newManufacturerShouldNotBeNull() {
        Assertions.assertNotNull(manufacturer);
    }

    @Test
    void newManufacturerAffiliationShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> manufacturer.setAffiliation(null));
    }


}