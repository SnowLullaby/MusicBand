package validators;

import models.Location;

public class LocationValidator {
    public static boolean checkLocation(Location location) {
        return checkX(location.x)
                && checkY(location.y)
                && checkName(location.name);
    }

    public static boolean checkName(String name) {
        return true;
    }

    public static boolean checkY(Float y) {
        return y != null;
    }

    public static boolean checkX(Float x) {
        return x != null;
    }
}
