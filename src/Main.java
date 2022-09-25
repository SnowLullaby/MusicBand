import commandService.CommandService;
import commands.*;
import models.MusicBandCollection;

public class Main {
    private static MusicBandCollection musicBandCollection;

    private static String fileName;

    public static void main(String[] args) {
        initFileName(args[0]);
        System.out.println(fileName);
        musicBandCollection = MusicBandCollection.getInstance(fileName);

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
