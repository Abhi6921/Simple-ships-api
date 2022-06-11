package nl.inholland.ships.shipsapi.repository;

import nl.inholland.ships.shipsapi.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);

}
