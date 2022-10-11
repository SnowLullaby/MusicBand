package collection;

import communication.User;
import models.MusicBand;

import java.util.List;
import java.util.Optional;

public interface PersistanceManager {
    Optional<User> getUser(String login);
    Optional<Long> addUser(User user);
    List<MusicBand> getBands();

    boolean removeBandById(long id);

    Optional<Long> addBand(MusicBand musicBand);
}
