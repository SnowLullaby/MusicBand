package collection;

import communication.User;
import models.MusicBand;
import models.Person;
import org.glassfish.jaxb.runtime.v2.runtime.output.StAXExStreamWriterOutput;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages collection contains music bands
 */
public class MusicBandCollection {
    private static final MusicBandCollection instance = new MusicBandCollection();
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final CopyOnWriteArrayList<MusicBand> collection;
    private final PersistanceManager persistanceManager;

    private MusicBandCollection() {
        this.persistanceManager = DbPersistenceManager.INSTANCE;
        this.collection = new CopyOnWriteArrayList<>(persistanceManager.getBands());
    }

    /**
     * Return instance of MusicBandCollection
     * @return instance
     */
    public static MusicBandCollection getInstance() {
        return instance;
    }

    public int getSize() {
        return collection.size();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Long getFirstID() {
        return collection.get(0).id;
    }

    public void save() {
        // all bands are already saved to db
    }

    public boolean addElement(MusicBand band) {
        Optional<Long> maybeId = persistanceManager.addBand(band);
        if (maybeId.isPresent()) {
            band.id = maybeId.get();
            collection.add(band);
            return true;
        } else {
            return false;
        }
    }

    public void removeAt(int index) throws ArrayIndexOutOfBoundsException{
        collection.remove(index);
    }

    public boolean removeByID(Long id, User user) {
        if (collection.stream().filter(musicBand -> Objects.equals(musicBand.id, id)).anyMatch(band -> Objects.equals(band.userName, user.username()))) {
            collection.removeIf(band_ -> Objects.equals(band_.id, id));
            return persistanceManager.removeBandById(id);
        }
        return false;
    }

    public void removeAll() {
        collection.clear();
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }

    public long getSumOfNumberOfParticipants() {
        long sum = 0;
        for (MusicBand band: collection) {
            sum += band.numberOfParticipants;
        }
        return sum;
    }

    public double getAverageOfNumberOfParticipants() {
        var average = (double) getSumOfNumberOfParticipants();
        average /= collection.size();
        return average;
    }

    public void defaultSortByID() {
        Comparator<MusicBand> comparator = new ItemLocationComparatorAscending();
        collection.sort(comparator);
    }

    static class ItemLocationComparatorAscending implements Comparator<MusicBand> {
        public int compare(MusicBand band1, MusicBand band2) {
            return band1.id.compareTo(band2.id);
        }
    }

    public void descendingSortByID() {
        Comparator<MusicBand> comparator = new ItemLocationComparatorDescending();
        collection.sort(comparator);
    }

    static class ItemLocationComparatorDescending implements Comparator<MusicBand> {
        public int compare(MusicBand band1, MusicBand band2) {
            return band2.id.compareTo(band1.id);
        }
    }

    public Vector<Person> getFrontManCollection() {
        var frontManCollection = new Vector<Person>();
        for (MusicBand band: collection) {
            frontManCollection.add(band.frontMan);
        }
        return frontManCollection;
    }

    @Override
    public String toString(){
        String str = "";
        for (MusicBand band: collection) {
            str += band.toString() + "\n";
        }
        return str;
    }
}
