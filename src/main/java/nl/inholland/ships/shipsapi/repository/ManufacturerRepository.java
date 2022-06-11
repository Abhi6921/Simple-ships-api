package nl.inholland.ships.shipsapi.repository;

import nl.inholland.ships.shipsapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findManufacturerByName(String name);
    List<Manufacturer> findAllByAffiliation(String affiliation);
}
