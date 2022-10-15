package models;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Store information about Music band (id, name, coordinates, creation date, number of participants, genre and Frontman)
 */
@XmlRootElement(name = "band")
public class MusicBand implements Serializable {
    public Long id; //not null > 0, auto-generated
    public String name; //not null, not ""
    public Coordinates coordinates;
    public LocalDate creationDate = LocalDate.now();
    public Long numberOfParticipants; //may be null or > 0
    public MusicGenre genre; //not null
    public Person frontMan;
    public String userName;

    public String getCreationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return formatter.format(creationDate);
    }

    @Override
    public String toString(){
        return ("[" + id + "; " + name + "; " + coordinates  + "; " + getCreationDate() + "; " +
        numberOfParticipants + "; " + genre + "; " + frontMan + "; " + userName +"]");
    }

    public void setCreationDateAsDate(LocalDate now) {
        creationDate = now;
    }
}
