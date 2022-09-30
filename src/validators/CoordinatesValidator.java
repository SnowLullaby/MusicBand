package validators;

import models.*;

public class CoordinatesValidator {
    public static boolean checkCoordinates(Coordinates coordinates) {
        return checkX(coordinates.x) && checkY(coordinates.y);
    }

    public static boolean checkX(double x) {
        return x <= 103;
    }

    public static boolean checkY(int y) {
        return true;
    }
}
