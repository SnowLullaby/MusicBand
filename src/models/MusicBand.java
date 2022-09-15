package models;

public class MusicBand {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Long numberOfParticipants = null;
    private MusicGenre genre;
    private Person frontMan = null;
}
