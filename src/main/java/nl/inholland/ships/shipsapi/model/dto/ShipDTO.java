package nl.inholland.ships.shipsapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipDTO {

    private String name;
    private String manufacturerName;
    private String type;
    private double length;
    private long cost;

}
