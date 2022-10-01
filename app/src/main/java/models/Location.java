package models;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "location")
public class Location {
    @XmlElement(name = "x")
    public Float x;
    @XmlElement(name = "y")
    public Float y;
    @XmlElement(name = "name")
    public String name = null;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "; " + name + "]");
    }
}

