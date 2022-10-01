package validators;

import models.*;

public class MusicBandValidator {
    public static boolean checkMusicBand(MusicBand musicBand){
        return chekID(musicBand.id)
                && checkName(musicBand.name)
                && checkCoordinates(musicBand.coordinates)
                && checkCreationDate(musicBand.getCreationDate())
                && checkNumberOfParticipants(musicBand.numberOfParticipants)
                && checkFrontMan(musicBand.frontMan);
    }

    public static boolean checkFrontMan(Person frontMan) {
        return frontMan == null || FrontManValidator.checkFrontMan(frontMan);
    }

    public static boolean checkGenre(String genre) {
        try {
            Enum.valueOf(MusicGenre.class, genre);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean checkNumberOfParticipants(Long numberOfParticipants) {
        return numberOfParticipants > 0;
    }

    public static boolean checkCreationDate(String creationDate) {
        return creationDate != null;
    }

    public static boolean checkCoordinates(Coordinates coordinates) {
        return coordinates != null && CoordinatesValidator.checkCoordinates(coordinates);
    }

    public static boolean checkName(String name) {
        return name != null && !name.equals("");
    }

    private static boolean chekID(Long id) {
        return id != 0 && id > 0;
    }
}
