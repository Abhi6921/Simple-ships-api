package nl.inholland.ships.shipsapi.controller;

import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    @PreAuthorize("hasRole({'USER'})")
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer) {

        Manufacturer manufacturer1 = manufacturerService.getByName(manufacturer.getName());

        if (manufacturer1 != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "manufacturer name already exists");
        }
        manufacturer1 = manufacturerService.addManufacturer(manufacturer);

        return new ResponseEntity<>(manufacturer1, HttpStatus.CREATED);
    }

    @GetMapping(value = "", params = {"affiliation"})
    public ResponseEntity<List<Manufacturer>> findByAffiliation(@RequestParam(value = "affiliation", required = true) String affiliation) {
        List<Manufacturer> manufacturers = manufacturerService.getAllByAffiliation(affiliation);
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }
}
