package communication;

import java.io.Serializable;

public record User(String username, String password, long id) implements Serializable {
}
