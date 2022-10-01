package validators;

import models.Color;
import models.Location;
import models.Person;

public class FrontManValidator {
    public static boolean checkFrontMan(Person frontMan) {
        return checkName(frontMan.name)
                && checkHeight(frontMan.height)
                && checkWeight(frontMan.weight)
                && checkLocation(frontMan.location);
     }

    public static boolean checkLocation(Location location) {
        return location == null || LocationValidator.checkLocation(location);
    }

    public static boolean checkWeight(Long weight) {
        return weight > 0;
    }

    public static boolean checkHeight(Double height) {
        return height != null && height > 0;
    }

    public static boolean checkName(String name) {
        return name != null && !name.equals("");
    }
}
