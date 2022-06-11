package nl.inholland.ships.shipsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String type;
    private Double length;
    private long cost;

    @ManyToOne
    @JsonIgnoreProperties({"ships"})
    private Manufacturer manufacturer;

    public Ship(Manufacturer manufacturer, String name, String type, Double length, long cost) {

        this.name = name;
        this.type = type;
        this.length = length;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }
}
