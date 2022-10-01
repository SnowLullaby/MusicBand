import parsers.ParserXML;
import commandService.CommandService;
import models.*;

/**
 * launching class
 */
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

        var commandService = CommandService.getInstance();

        while (true) {
            commandService.execute();
        }
    }

    /**
     * Check path to file
     * @param fileName path to file
     */
    private static void initFileName (String fileName){
        try {
            Main.fileName = fileName;
        } catch (Exception e){
            System.out.println("Не указано имя файла");
            System.exit(0);
        }
    }

}
