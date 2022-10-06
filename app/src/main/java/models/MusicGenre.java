package models;

import jakarta.xml.bind.annotation.*;

@XmlEnum(String.class)
public enum MusicGenre {
    @XmlEnumValue("ROCK")
    ROCK,
    @XmlEnumValue("PSYCHEDELIC POCK")
    PSYCHEDELIC_ROCK,
    @XmlEnumValue("PSYCHEDELIC CLOUD RAP")
    PSYCHEDELIC_CLOUD_RAP,
    @XmlEnumValue("JAZZ")
    JAZZ,
    @XmlEnumValue("POST ROCK")
    POST_ROCK;
}
