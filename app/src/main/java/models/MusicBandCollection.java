package models;

import validators.MusicBandValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MusicBandCollection {
    private static final MusicBandCollection instance = new MusicBandCollection();
    private final LocalDateTime creationTime = LocalDateTime.now();
    private ISaveLoad saveLoad;
    private Vector<MusicBand> collection;
    private Long maxID;

    public static MusicBandCollection getInstance() {
        if (instance.collection == null) throw new RuntimeException("Collection not initialized");
        return instance;
    }

    public static void initInstance(ISaveLoad saveLoad) {
        fillingInstance(saveLoad);
        checkID();
        checkUniqueID();
        checkCorrectMaxID();
    }

    private static void fillingInstance(ISaveLoad saveLoad) {
        instance.saveLoad = saveLoad;
        var collAndID = instance.saveLoad.parse();
        instance.collection = collAndID.getKey();
        instance.maxID = collAndID.getValue();
        instance.defaultSortByID();
    }

    private static void checkID() {
        if (instance.maxID == null) {
            System.out.println("В файле отсутствует максимальный ID");
            System.exit(0);
        }
    }

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

    private static void checkMusicBands(MusicBand band) {
        if (!MusicBandValidator.checkMusicBand(band) ) {
            System.out.println("Некорректный формат данных в исходном XML-файле");
            System.exit(0);
        }
    }

    private static void checkCorrectMaxID() {
        if (instance.findRealMaxID() != instance.maxID){
            instance.maxID = instance.findRealMaxID();
            System.out.printf("В файле неверно указан максимальный ID элементов. Рекомендуется исправить на %d или вызвать команду save!\n", instance.maxID);
        }
    }

    public long findRealMaxID() {
        long realMaxID = 0;
        for (MusicBand band: collection) {
            realMaxID = (band.id > realMaxID) ? band.id : realMaxID;
        }
        return realMaxID;
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
        var collAndID = new AbstractMap.SimpleEntry<Vector<MusicBand>, Long> (collection, maxID);
        saveLoad.save(collAndID);
    }

    public void addElement(MusicBand band) {
        collection.add(band);
        maxID = findRealMaxID();
    }

    public MusicBand getElementByID(Long id) {
        for (MusicBand band : collection){
            if (Objects.equals(band.id, id))
                return band;
        }
        return null;
    }

    public void removeAt(int index) throws ArrayIndexOutOfBoundsException{
        collection.remove(index);
    }

    public void removeByID(Long id) {
        collection.removeIf(band -> Objects.equals(band.id, id));
        maxID = findRealMaxID();
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


