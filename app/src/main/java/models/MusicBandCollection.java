package models;

import validators.MusicBandValidator;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Manages collection contains music bands
 */
public class MusicBandCollection {
    private static final MusicBandCollection instance = new MusicBandCollection();
    private final LocalDateTime creationTime = LocalDateTime.now();
    private ISaveLoad saveLoad;
    private Vector<MusicBand> collection;
    private Long maxID;

    /**
     * Return instance of MusicBandCollection
     * @return instance
     */
    public static MusicBandCollection getInstance() {
        if (instance.collection == null) throw new RuntimeException("Collection not initialized");
        return instance;
    }

    /**
     * Create instance of MusicBandCollection
     * @param saveLoad - instance of ISAveLoad class
     * @see ISaveLoad
     */
    public static void initInstance(ISaveLoad saveLoad) {
        fillingInstance(saveLoad);
        checkID();
        checkUniqueID();
        checkCorrectMaxID();
    }

    /**
     * Filling field of instance
     * @param saveLoad - instance of ISAveLoad class
     */
    private static void fillingInstance(ISaveLoad saveLoad) {
        instance.saveLoad = saveLoad;
        var collAndID = instance.saveLoad.parse();
        instance.collection = collAndID.getKey();
        instance.maxID = collAndID.getValue();
        instance.defaultSortByID();
    }

    /**
     * Checking maxId existence ib file
     */
    private static void checkID() {
        if (instance.maxID == null) {
            System.out.println("В файле отсутствует максимальный ID");
            System.exit(0);
        }
    }

    /**
     * Checking that all ID's in collection are unique
     */
    private static void checkUniqueID() {
        var differentIDs = new Vector<Long>();
        for (MusicBand band: instance.collection) {
            checkMusicBands(band);
            if (differentIDs.contains(band.id)) {
                System.out.println("Проверьте уникальность ID в коллекции!");
                System.exit(0);

            }
            differentIDs.add(band.id);
        }
    }

    /**
     *
     * @param band -
     */
    private static void checkMusicBands(MusicBand band) {
        if (!MusicBandValidator.checkMusicBand(band) ) {
            System.out.println("Некорректный формат данных в исходном XML-файле");
            System.exit(0);
        }
    }

    /**
     *
     */
    private static void checkCorrectMaxID() {
        if (instance.findRealMaxID() != instance.maxID){
            instance.maxID = instance.findRealMaxID();
            System.out.printf("В файле неверно указан максимальный ID элементов. Рекомендуется исправить на %d или вызвать команду save!\n", instance.maxID);
        }
    }

    /**
     *
     * @return
     */
    public long findRealMaxID() {
        long realMaxID = 0;
        for (MusicBand band: collection) {
            realMaxID = (band.id > realMaxID) ? band.id : realMaxID;
        }
        return realMaxID;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return collection.size();
    }

    /**
     *
     * @return
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     *
     * @return
     */
    public Long getFirstID() {
        return collection.get(0).id;
    }

    /**
     *
     */
    public void save() {
        var collAndID = new AbstractMap.SimpleEntry<Vector<MusicBand>, Long> (collection, maxID);
        saveLoad.save(collAndID);
    }

    /**
     *
     * @param band
     */
    public void addElement(MusicBand band) {
        collection.add(band);
        maxID = findRealMaxID();
    }

    /**
     *
     * @param id
     * @return
     */
    public MusicBand getElementByID(Long id) {
        for (MusicBand band : collection){
            if (Objects.equals(band.id, id))
                return band;
        }
        return null;
    }

    /**
     *
     * @param index
     * @throws ArrayIndexOutOfBoundsException
     */
    public void removeAt(int index) throws ArrayIndexOutOfBoundsException{
        collection.remove(index);
    }

    /**
     *
     * @param id
     */
    public void removeByID(Long id) {
        collection.removeIf(band -> Objects.equals(band.id, id));
        maxID = findRealMaxID();
    }

    /**
     *
     */
    public void removeAll() {
        collection.clear();
    }

    /**
     *
     */
    public void shuffle() {
        Collections.shuffle(collection);
    }

    /**
     *
     * @return
     */
    public long getSumOfNumberOfParticipants() {
        long sum = 0;
        for (MusicBand band: collection) {
            sum += band.numberOfParticipants;
        }
        return sum;
    }

    /**
     *
     * @return
     */
    public double getAverageOfNumberOfParticipants() {
        var average = (double) getSumOfNumberOfParticipants();
        average /= collection.size();
        return average;
    }

    /**
     *
     */
    public void defaultSortByID() {
        Comparator<MusicBand> comparator = new ItemLocationComparatorAscending();
        collection.sort(comparator);
    }

    /**
     *
     */
    static class ItemLocationComparatorAscending implements Comparator<MusicBand> {
        public int compare(MusicBand band1, MusicBand band2) {
            return band1.id.compareTo(band2.id);
        }
    }

    /**
     *
     */
    public void descendingSortByID() {
        Comparator<MusicBand> comparator = new ItemLocationComparatorDescending();
        collection.sort(comparator);
    }

    /**
     *
     */
    static class ItemLocationComparatorDescending implements Comparator<MusicBand> {
        public int compare(MusicBand band1, MusicBand band2) {
            return band2.id.compareTo(band1.id);
        }
    }

    /**
     *
     * @return
     */
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


