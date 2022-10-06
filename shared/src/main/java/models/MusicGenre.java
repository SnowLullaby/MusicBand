package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlEnum(String.class)
public enum MusicGenre implements Serializable {
    @XmlEnumValue("ROCK")
    ROCK,
    @XmlEnumValue("PSYCHEDELIC ROCK")
    PSYCHEDELIC_ROCK,
    @XmlEnumValue("PSYCHEDELIC CLOUD RAP")
    PSYCHEDELIC_CLOUD_RAP,
    @XmlEnumValue("JAZZ")
    JAZZ,
    @XmlEnumValue("POST ROCK")
    POST_ROCK;
}
