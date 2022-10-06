package commands;

import models.MusicBandCollection;

public class ShowCommand implements Command {
    public void execute() {
        System.out.println("There are " + MusicBandCollection.getInstance().getSize() + " elements in collection");
        System.out.print(MusicBandCollection.getInstance().toString());
    }
}
