package models;

import java.util.Vector;

public class MusicBandCollection {
    Vector collection;
    private static MusicBandCollection instance = null;

    public static MusicBandCollection getInstance(String fileName){
        if (instance == null){
            instance = new MusicBandCollection();
            instance.parseXML(fileName);
        }
        return instance;
    }

    private void parseXML(String fileName){

    }

}
