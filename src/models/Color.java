package models;

import javax.xml.bind.annotation.*;

@XmlEnum(String.class)
public enum Color {
    @XmlEnumValue("Зеленые")
    GREEN,
    @XmlEnumValue("Алые")
    RED,
    @XmlEnumValue("Голубые")
    BLUE,
    @XmlEnumValue("Карие")
    BROWN;
}
