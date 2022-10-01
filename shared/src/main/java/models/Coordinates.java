package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

/**
 * Store x and y Coordinates of Music Band
 */
@XmlRootElement(name = "coordinates")
public class Coordinates implements Serializable {
    @XmlElement(name = "x")
    public double x;
    @XmlElement(name = "y") //not null
    public int y;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "]");
    }
}
