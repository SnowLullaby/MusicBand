package models;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "coordinates")
public class Coordinates {
    @XmlElement(name = "x")
    public double x;
    @XmlElement(name = "y")
    public int y;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "]");
    }
}
