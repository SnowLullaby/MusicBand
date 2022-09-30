package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "coordinates")
public class Coordinates implements Serializable {
    @XmlElement(name = "x")
    public double x;
    @XmlElement(name = "y")
    public int y;

    @Override
    public String toString(){
        return ("[" + x + "; " + y + "]");
    }
}
