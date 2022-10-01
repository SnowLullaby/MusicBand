package commands;

import models.MusicBandCollection;

public class ShowCommand implements Command {
    public void execute() {
        System.out.println("Вызван вывод в окллекции. В коллекции " + MusicBandCollection.getInstance().getSize() + " элементов");
        System.out.print(MusicBandCollection.getInstance().toString());
    }
}
