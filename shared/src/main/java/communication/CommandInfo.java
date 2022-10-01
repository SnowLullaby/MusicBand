package communication;

import java.io.Serializable;
import java.util.List;

public record CommandInfo(String name, List<String> args, Object extendedData) implements Serializable {
}
