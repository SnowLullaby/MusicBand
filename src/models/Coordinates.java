package models;

import javax.xml.bind.annotation.*;

/**
 * Store x and y Coordinates of Music Band
 */
@XmlRootElement(name = "coordinates")
public class Coordinates {
    @XmlElement(name = "x") // not null <= 103
    public double x;
    @XmlElement(name = "y") //not null
    public int y;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "]");
    }
}
