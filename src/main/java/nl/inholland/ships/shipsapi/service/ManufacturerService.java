package nl.inholland.ships.shipsapi.service;


import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    public Manufacturer updateManufacturer(Long id, Manufacturer manufacturerDetails) {

        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        manufacturer.setId(manufacturerDetails.getId());
        manufacturer.setName(manufacturerDetails.getName());
        manufacturer.setAffiliation(manufacturerDetails.getAffiliation());
        manufacturer.setCeo(manufacturerDetails.getCeo());

        return manufacturerRepository.save(manufacturer);
    }
}
