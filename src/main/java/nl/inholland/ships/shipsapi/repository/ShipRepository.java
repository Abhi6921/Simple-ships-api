package nl.inholland.ships.shipsapi.repository;

import nl.inholland.ships.shipsapi.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
