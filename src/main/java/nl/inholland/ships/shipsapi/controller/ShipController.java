package nl.inholland.ships.shipsapi.controller;

import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.model.Ship;
import nl.inholland.ships.shipsapi.model.dto.ShipDTO;
import nl.inholland.ships.shipsapi.service.ManufacturerService;
import nl.inholland.ships.shipsapi.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = shipService.getAllShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ship> addShip(@RequestBody ShipDTO shipDTO) {
        Ship ship = convertShipDTOToShip(shipDTO);
        Ship newship = shipService.addShip(ship);
        return new ResponseEntity<>(newship, HttpStatus.CREATED);
    }

    public Ship convertShipDTOToShip(ShipDTO shipDTO) {
        Ship ship = new Ship();
        ship.setName(shipDTO.getName());
        Manufacturer manufacturer = manufacturerService.getByName(shipDTO.getManufacturerName());
        if (manufacturer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no manufacturer with this name");
        }
        ship.setManufacturer(manufacturer);
        ship.setType(shipDTO.getType());
        ship.setLength(shipDTO.getLength());
        ship.setCost(shipDTO.getCost());
        return ship;
    }

}
