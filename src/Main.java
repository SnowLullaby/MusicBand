import commands.*;
import models.MusicBandCollection;

import java.util.Scanner;

public class Main {
    private static MusicBandCollection musicBandCollection;

    private static String fileName;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initFileName(args[0]);
        System.out.println(fileName);
        musicBandCollection = MusicBandCollection.getInstance(fileName);

        initCommands();

        while (true) {
            var currentCommandLine = scanner.nextLine().trim().split(" ");
            try {
                Router.execute(currentCommandLine[0]);
            } catch (Exception e) {
                System.out.printf("Команды %s не существует!\n", currentCommandLine[0]);
            }
        }
    }

    private static void initCommands() {
        Router.describe("help", new HelpCommand());
        Router.describe("exit", new ExitCommand());
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
