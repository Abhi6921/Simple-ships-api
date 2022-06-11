package nl.inholland.ships.shipsapi.service;

import nl.inholland.ships.shipsapi.model.MyUser;
import nl.inholland.ships.shipsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ShipsAPIUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByUsername(username);

        if (myUser == null) {
            throw new UsernameNotFoundException("username " + username + " not found!");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(myUser.getPassword())
                .authorities(myUser.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
