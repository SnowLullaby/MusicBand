package models;

import java.util.Vector;

public class MusicBandCollection {
    Vector collection;
    private static MusicBandCollection instance = null;

    public static MusicBandCollection getInstance(String fileName){
        if (instance == null){
            parseXML(fileName);
            instance = new MusicBandCollection();
        }
        return instance;
    }

    private static void parseXML(String fileName){}

}
