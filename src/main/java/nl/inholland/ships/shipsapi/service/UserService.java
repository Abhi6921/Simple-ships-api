package nl.inholland.ships.shipsapi.service;

import nl.inholland.ships.shipsapi.jwt.JwtTokenProvider;
import nl.inholland.ships.shipsapi.model.MyUser;
import nl.inholland.ships.shipsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {

        String token = "";

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            MyUser myUser = userRepository.findByUsername(username);
            token = jwtTokenProvider.createToken(username, myUser.getRoles());

        }catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Invalid username/password");
        }

        return token;
    }

    public MyUser addUser(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        userRepository.save(myUser);
        return myUser;
    }
}
