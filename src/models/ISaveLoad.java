package models;

import java.util.*;

/**
 * Parse collection and save it
 */

public interface ISaveLoad {
    public AbstractMap.SimpleEntry<Vector<MusicBand>, Long> parse();
    public void save (AbstractMap.SimpleEntry<Vector<MusicBand>, Long> collectionAndID);
}
