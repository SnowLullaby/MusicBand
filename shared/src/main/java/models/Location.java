package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

/**
 * Store x, y and name for Frontman's location
 */
@XmlRootElement(name = "location")
public class Location implements Serializable {
    @XmlElement(name = "x") //not null
    public Float x;
    @XmlElement(name = "y") //not null
    public Float y;
    @XmlElement(name = "name") //may be null
    public String name = null;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "; " + name + "]");
    }
}

