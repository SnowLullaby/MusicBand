package commands;

import models.*;
import validators.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class Add implements Command {
    protected MusicBand band = new MusicBand();
    private final Scanner sc = new Scanner(System.in);

    public void execute() {
        try {
            setIDAndDate();
            readAll();
            MusicBandCollection.getInstance().addElement(band);
            MusicBandCollection.getInstance().defaultSortByID();
            System.out.println("Element added");
        } catch (NoMoreFreeIDError e) {
            System.out.println(e.getMessage());
        }
    }

    private void readAll() {
        band.name = genericRead("group name", String::new, MusicBandValidator::checkName);
        System.out.println("fill data about coordinates");
        band.coordinates = new Coordinates();
        band.coordinates.x = genericRead("x", Double::parseDouble, CoordinatesValidator::checkX);
        band.coordinates.y = genericRead("y", Integer::parseInt, CoordinatesValidator::checkY);
        band.numberOfParticipants = genericRead("number of participants", Long::parseLong, MusicBandValidator::checkNumberOfParticipants);
        band.genre = genericRead("music genre. Options: " + Arrays.toString(MusicGenre.values()), Add::toGenre, (String) -> true);
        System.out.println("Enter yes if you want to fill date about front man");
        if (sc.nextLine().equals("yes")) {
            band.frontMan = new Person();
            band.frontMan.name = genericRead("name", String::new, FrontManValidator::checkName);
            band.frontMan.height = genericRead("height", Double::parseDouble, FrontManValidator::checkHeight);
            band.frontMan.weight = genericRead("weight", Long::parseLong, FrontManValidator::checkWeight);
            band.frontMan.eyeColor = genericRead("eye color. Options " + Arrays.toString(Color.values()), Add::toEyeColor, (String) -> true);
            System.out.println("Enter yes if you want to fill date about location");
            if (sc.nextLine().equals("yes")) {
                band.frontMan.location = new Location();
                band.frontMan.location.x = genericRead("x", Float::parseFloat, LocationValidator::checkX);
                band.frontMan.location.y = genericRead("y", Float::parseFloat, LocationValidator::checkY);
                band.frontMan.location.name = genericRead("name", String::new, LocationValidator::checkName);
            }
        }
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

    protected abstract boolean addCondition();

    protected abstract Long calculateId();

    protected void setIDAndDate() throws NoMoreFreeIDError {
        if (addCondition()) {
            band = new MusicBand();
            band.id = calculateId();
            band.setCreationDateAsDate(LocalDate.now());
        } else throw new NoMoreFreeIDError();
    }
}