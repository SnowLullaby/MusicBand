import parsers.ParserXML;
import commandService.CommandService;
import models.*;

public class Main {
    private static String fileName;

    public static void main(String[] args) {
        initFileName(args[0]);
        ISaveLoad saveLoad = new ParserXML(fileName);
        MusicBandCollection.initInstance(saveLoad);

        var commandService = CommandService.getInstance();

        while (true) {
            commandService.execute();
        }
    }

    private static void initFileName (String fileName){
        try {
            Main.fileName = fileName;
        } catch (Exception e){
            System.out.println("Не указано имя файла");
            System.exit(0);
        }
    }

}
