package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

/**
 * Store information about FrontMan (name, height, weight, eyeColor, location)
 */
@XmlRootElement(name = "frontMan")
public class Person implements Serializable {
    @XmlElement(name = "name")
    public String name; // not null, not ""
    @XmlElement(name = "height")
    public Double height; //not null, > 0
    @XmlElement(name = "weight")
    public Long weight = null; // may be null or > 0
    @XmlElement(name = "eyeColor")
    public Color eyeColor; //not null
    @XmlElement(name = "location")
    public Location location = null; //may be null

    @Override
    public String toString(){
        return ("[" + name + "; " + height + "; " + weight + "; " + eyeColor + "; " + location + "]");
    }
}
