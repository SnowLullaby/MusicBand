package validators;

import models.*;

public class MusicBandValidatore {
    public static boolean checkMusicBand(MusicBand musicBand){
        return chekID(musicBand.id) && checkName(musicBand.name) && checkCoordinates(musicBand.coordinates) &&
                checkCreationDate(musicBand.getCreationDate()) && checkNumberOfParticipants(musicBand.numberOfParticipants)
                && chekGenre(musicBand.genre) && checkFrontMan(musicBand.frontMan);
    }

    private static boolean checkFrontMan(Person frontMan) {
        return frontMan == null || FrontManValidator.checkFrontMan(frontMan);
    }

    private static boolean chekGenre(MusicGenre genre) {
        try {
            Enum.valueOf(MusicGenre.class, genre.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean checkNumberOfParticipants(Long numberOfParticipants) {
        return numberOfParticipants > 0;
    }

    private static boolean checkCreationDate(String creationDate) {
        return creationDate != null;
    }

    private static boolean checkCoordinates(Coordinates coordinates) {
        return coordinates != null && CoordinatesValidator.checkCoordinates(coordinates);
    }

    private static boolean checkName(String name) {
        return name != null && !name.equals("");
    }

    private static boolean chekID(Long id) {
        return id != 0 && id > 0;
    }
}
