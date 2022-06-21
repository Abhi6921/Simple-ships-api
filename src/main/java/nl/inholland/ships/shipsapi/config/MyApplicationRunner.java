package nl.inholland.ships.shipsapi.config;

import nl.inholland.ships.shipsapi.model.Manufacturer;
import nl.inholland.ships.shipsapi.model.MyUser;
import nl.inholland.ships.shipsapi.model.Role;
import nl.inholland.ships.shipsapi.model.Ship;
import nl.inholland.ships.shipsapi.repository.ManufacturerRepository;
import nl.inholland.ships.shipsapi.repository.ShipRepository;
import nl.inholland.ships.shipsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Manufacturer manufacturer1 = new Manufacturer(1000001,"Corellian Engineering Corporation","Rebel Alliance","Corran Destt");
        Manufacturer manufacturer2 = new Manufacturer(1000002,"Kuat Drive Yards","Galactic Republic","Kuat of Kuat");
        Manufacturer manufacturer3 = new Manufacturer(1000003,"Sienar Fleet Systems","Galactic Republic","Raith Sienar");

        List<Manufacturer> manufacturers = new ArrayList<>(Arrays.asList(
                manufacturer1, manufacturer2, manufacturer3
        ));

        manufacturerRepository.saveAll(manufacturers);
        List<Ship> ships = List.of(
                new Ship(manufacturer1, "CR90", "Corvette", 150.00, 2700000),
                new Ship(manufacturer2,"Executor", "Dreadnought",19000.00,325000000),
                new Ship(manufacturer3, "TIE","Fighter",6.3,60000),
                new Ship(manufacturer3, "Lambda","Shuttle",20.00,140000)
        );

        shipRepository.saveAll(ships);

        MyUser testuser = new MyUser();
        testuser.setFullName("Abhi king");
        testuser.setEmail("abi@gmail.com");
        testuser.setUsername("test");
        testuser.setPassword("secret");
        testuser.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_USER)));

        userService.addUser(testuser);

        MyUser testuser2 = new MyUser();
        testuser2.setFullName("tommy king");
        testuser2.setEmail("tommy@gmail.com");
        testuser2.setUsername("tommy13");
        testuser2.setPassword("abc123");
        testuser2.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.addUser(testuser2);
    }
}
