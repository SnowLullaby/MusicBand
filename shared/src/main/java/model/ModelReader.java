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
        band.name = genericRead("название группы", String::new, MusicBandValidator::checkName);
        System.out.println("Заполнените данные о координатах группы");
        band.coordinates = new Coordinates();
        band.coordinates.x = genericRead("координату x", Double::parseDouble, CoordinatesValidator::checkX);
        band.coordinates.y = genericRead("координату y", Integer::parseInt, CoordinatesValidator::checkY);
        band.numberOfParticipants = genericRead("количество участников", Long::parseLong, MusicBandValidator::checkNumberOfParticipants);
        band.genre = genericRead("жанр музыки. Возможные варианты " + Arrays.toString(MusicGenre.values()), ModelReader::toGenre, (String) -> true);
        System.out.println("Введите yes если хотите ввесли данные о солисте");
        if (sc.nextLine().equals("yes")) {
            band.frontMan = new Person();
            band.frontMan.name = genericRead("имя солиста", String::new, FrontManValidator::checkName);
            band.frontMan.height = genericRead("рост солиста", Double::parseDouble, FrontManValidator::checkHeight);
            band.frontMan.weight = genericRead("веc солиста", Long::parseLong, FrontManValidator::checkWeight);
            band.frontMan.eyeColor  = genericRead("цвет глаз солиста. Возможные варианты " + Arrays.toString(Color.values()), ModelReader::toEyeColor, (String) -> true);
            if (sc.nextLine().equals("yes")) {
                band.frontMan.location = new Location();
                band.frontMan.location.x = genericRead("координату x", Float::parseFloat, LocationValidator::checkX);
                band.frontMan.location.y = genericRead("координату y", Float::parseFloat, LocationValidator::checkY);
                band.frontMan.location.name = genericRead("название локации", String::new, LocationValidator::checkName);
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
        System.out.println("Введите " + message);
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
            System.out.println("Введенное значение не удовлетворяет условию. Попробуйте еще раз");
        }
    }
}
