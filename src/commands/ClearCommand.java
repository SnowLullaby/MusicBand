package commands;

import models.MusicBandCollection;

public class ClearCommand implements Command {
    public void execute() {
        MusicBandCollection.getInstance().removeAll();
        System.out.println("Очистка коллекции завершена");
    }
}
