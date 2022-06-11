package nl.inholland.ships.shipsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class MyUser {

    @Id
    @SequenceGenerator(name = "users_seq", initialValue = 1)
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    private long id;
    private String fullName;
    private String email;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;



}
