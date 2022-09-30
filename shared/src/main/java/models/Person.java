package models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "frontMan")
public class Person {
    @XmlElement(name = "name")
    public String name;
    @XmlElement(name = "height")
    public Double height;
    @XmlElement(name = "weight")
    public Long weight = null;
    @XmlElement(name = "eyeColor")
    public Color eyeColor;
    @XmlElement(name = "location")
    public Location location = null;

    @Override
    public String toString(){
        return ("[" + name + "; " + height + "; " + weight + "; " + eyeColor + "; " + location.toString() + "]");
    }
}
