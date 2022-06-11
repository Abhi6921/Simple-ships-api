package nl.inholland.ships.shipsapi.service;

import nl.inholland.ships.shipsapi.model.Ship;
import nl.inholland.ships.shipsapi.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Ship addShip(Ship ship) {
        return shipRepository.save(ship);
    }
}
