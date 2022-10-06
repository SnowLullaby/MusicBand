package models;

import jakarta.xml.bind.annotation.*;

@XmlEnum(String.class)
public enum Color {
    @XmlEnumValue("GREEN")
    GREEN,
    @XmlEnumValue("RED")
    RED,
    @XmlEnumValue("BLUE")
    BLUE,
    @XmlEnumValue("BROWN")
    BROWN;
}
