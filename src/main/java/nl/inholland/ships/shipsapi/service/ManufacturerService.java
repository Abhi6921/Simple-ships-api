package nl.inholland.ships.shipsapi.service;


import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer getByName(String name) {
        return manufacturerRepository.findManufacturerByName(name);
    }

    public List<Manufacturer> getAllByAffiliation(String affiliation) {
        return manufacturerRepository.findAllByAffiliation(affiliation);
    }
}
