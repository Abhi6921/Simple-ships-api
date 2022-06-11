package nl.inholland.ships.shipsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    @Id
    private long id;
    private String name;
    private String affiliation;
    private String ceo;
    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnoreProperties({"manufacturer"})
    List<Ship> ships;


    public Manufacturer(long id, String name, String affiliation, String ceo) {
        this.id = id;
        this.name = name;
        this.affiliation = affiliation;
        this.ceo = ceo;
    }
}
