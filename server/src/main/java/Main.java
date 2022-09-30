import collection.MusicBandCollection;
import parsers.ParserXML;
import models.*;

import java.io.IOException;

public class Main {
    private static String fileName;

    public static void main(String[] args) {
        initFileName(args[0]);
        ISaveLoad saveLoad = new ParserXML(fileName);
        MusicBandCollection.initInstance(saveLoad);
        Server server = new Server(12345);

        try {
            server.run();
        } catch (IOException e) {
            System.out.println("Сервер остановлен с ошибкой: " + e.getMessage());
        }
    }

    private static void initFileName(String fileName) {
        try {
            Main.fileName = fileName;
        } catch (Exception e) {
            System.out.println("Не указано имя файла");
            System.exit(0);
        }
    }

}
