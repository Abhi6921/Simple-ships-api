package nl.inholland.ships.shipsapi.controller;

import nl.inholland.ships.shipsapi.model.dto.LoginDTO;
import nl.inholland.ships.shipsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
