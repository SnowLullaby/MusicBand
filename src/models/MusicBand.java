package models;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "band")
public class MusicBand {
    @XmlElement(name = "id")
    public Long id;
    @XmlElement(name = "name")
    public String name;
    @XmlElement(name = "coordinates")
    public Coordinates coordinates;
    private LocalDate creationDate;
    @XmlElement(name = "numberOfParticipants")
    public Long numberOfParticipants = null;
    @XmlElement(name = "musicGenre")
    public MusicGenre genre;
    @XmlElement(name = "frontMan")
    public Person frontMan = null;

    @XmlElement(name = "creationDate")
    public void setCreationDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        creationDate = LocalDate.parse(date, formatter);
    }

    public String getCreationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return formatter.format(creationDate);
    }

    @Override
    public String toString(){
        return ("[" + id + "; " + name + "; " + coordinates  + "; " + getCreationDate() + "; " +
        numberOfParticipants + "; " + genre + "; " + frontMan + "]");
    }

    public void setCreationDateAsDate(LocalDate now) {
        creationDate = now;
    }
}
