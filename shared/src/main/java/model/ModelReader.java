package model;

import models.*;
import validators.CoordinatesValidator;
import validators.FrontManValidator;
import validators.LocationValidator;
import validators.MusicBandValidator;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class ModelReader {
    private final Scanner sc = new Scanner(System.in);

    public MusicBand readModel() {
        MusicBand band = new MusicBand();
        band.name = genericRead("group name", String::new, MusicBandValidator::checkName);
        System.out.println("Fill data about coordinates");
        band.coordinates = new Coordinates();
        band.coordinates.x = genericRead("x", Double::parseDouble, CoordinatesValidator::checkX);
        band.coordinates.y = genericRead("y", Integer::parseInt, CoordinatesValidator::checkY);
        band.numberOfParticipants = genericRead("number of participants", Long::parseLong, MusicBandValidator::checkNumberOfParticipants);
        band.genre = genericRead("music genre. Options: " + Arrays.toString(MusicGenre.values()), ModelReader::toGenre, (String) -> true);
        System.out.println("Enter yes if you want to fill date about front man");
        if (sc.nextLine().equals("yes")) {
            band.frontMan = new Person();
            band.frontMan.name = genericRead("name", String::new, FrontManValidator::checkName);
            band.frontMan.height = genericRead("height", Double::parseDouble, FrontManValidator::checkHeight);
            band.frontMan.weight = genericRead("weight", Long::parseLong, FrontManValidator::checkWeight);
            band.frontMan.eyeColor  = genericRead("eye color. Options: " + Arrays.toString(Color.values()), ModelReader::toEyeColor, (String) -> true);
            System.out.println("Enter yes if you want to fill date about location");
            if (sc.nextLine().equals("yes")) {
                band.frontMan.location = new Location();
                band.frontMan.location.x = genericRead("x", Float::parseFloat, LocationValidator::checkX);
                band.frontMan.location.y = genericRead("y", Float::parseFloat, LocationValidator::checkY);
                band.frontMan.location.name = genericRead("name", String::new, LocationValidator::checkName);
            }
        }
        return band;
    }

    private static MusicGenre toGenre(String value) {
        return Enum.valueOf(MusicGenre.class, value);
    }

    private static Color toEyeColor(String value) {
        return Enum.valueOf(Color.class, value);
    }

    private <T> T genericRead(String message, Function<String, T> parser, Predicate<T> constraint) {
        System.out.println("Enter " + message);
        while (true) {
            String inputValue = sc.nextLine();
            try {
                T parsed;
                if (Objects.equals(inputValue, "") || Objects.equals(inputValue, "null")) {
                    parsed = null;
                } else {
                    parsed = parser.apply(inputValue);
                }
                if (constraint.test(parsed)) {
                    return parsed;
                }
            } catch (NullPointerException | IllegalArgumentException ignored) {
            }
            System.out.println("Incorrect value. Try again");
        }
    }
}
