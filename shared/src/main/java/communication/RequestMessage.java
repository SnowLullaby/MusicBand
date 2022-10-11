package communication;

import java.io.Serializable;

public record RequestMessage(CommandInfo commandInfo, User user) implements Serializable {
}
