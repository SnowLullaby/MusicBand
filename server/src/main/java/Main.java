import collection.MusicBandCollection;
import parsers.ParserXML;
import models.*;

import java.io.IOException;

public class Main {
    /** path to file */
    private static String fileName;

    /**
     * Initialize collection and call reader command
     */
    public static void main(String[] args) {
        initFileName(args[0]);
        ISaveLoad saveLoad = new ParserXML(fileName);
        MusicBandCollection.initInstance(saveLoad);
        Server server = new Server(12345);

        try {
            server.run();
        } catch (IOException e) {
            System.out.println("Server stopped with error: " + e.getMessage());
        }
    }

    private static void initFileName (String fileName){
        try {
            Main.fileName = fileName;
        } catch (Exception e){
            System.out.println("File name not specified");
            System.exit(0);
        }
    }

}
