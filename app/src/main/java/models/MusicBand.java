package models;

import jakarta.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Store information about Music band (id, name, coordinates, creation date, number of participants, genre and Frontman)
 */
@XmlRootElement(name = "band")
public class MusicBand {
    @XmlElement(name = "id")
    public Long id; //not null > 0, auto-generated
    @XmlElement(name = "name")
    public String name; //not null, not ""
    @XmlElement(name = "coordinates")
    public Coordinates coordinates; //not null
    private LocalDate creationDate; //not null, > 0, auto-generated
    @XmlElement(name = "numberOfParticipants")
    public Long numberOfParticipants = null; //may be null or > 0
    @XmlElement(name = "musicGenre")
    public MusicGenre genre; //not null
    @XmlElement(name = "frontMan")
    public Person frontMan = null; //may be null

    /**
     * Set creation date from string, used in AddCommand, AddIfMinCommand and UpdateCommand
     * @param date - creation date as String
     */
    @XmlElement(name = "creationDate")
    public void setCreationDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        creationDate = LocalDate.parse(date, formatter);
    }

    /**
     * Convert creation date to string
     * @return creation date as String
     */
    public String getCreationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return formatter.format(creationDate);
    }

    /**
     * Set current date as creation date, used in AddCommand, AddIfMinCommand and UpdateCommand
     * @param now - current date
     */
    public void setCreationDateAsDate(LocalDate now) {
        creationDate = now;
    }

    @Override
    public String toString(){
        return ("[" + id + "; " + name + "; " + coordinates  + "; " + getCreationDate() + "; " +
        numberOfParticipants + "; " + genre + "; " + frontMan + "]");
    }
}
