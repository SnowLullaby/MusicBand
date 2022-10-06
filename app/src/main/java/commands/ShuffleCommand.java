package commands;

import models.MusicBandCollection;

public class ShuffleCommand implements Command {
    public void execute() {
        MusicBandCollection.getInstance().shuffle();
        System.out.println("Shuffled");
    }
}
