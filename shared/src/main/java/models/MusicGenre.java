package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlEnum(String.class)
public enum MusicGenre implements Serializable {
    @XmlEnumValue("Роцк")
    ROCK,
    @XmlEnumValue("Психоделический роцк")
    PSYCHEDELIC_ROCK,
    @XmlEnumValue("Психоделический клубный рэп")
    PSYCHEDELIC_CLOUD_RAP,
    @XmlEnumValue("Джаз")
    JAZZ,
    @XmlEnumValue("Пост роцк")
    POST_ROCK;
}
