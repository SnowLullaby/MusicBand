package commands;

import models.MusicBandCollection;

public class RemoveAtCommand implements Command {
    private int index;
    public RemoveAtCommand(int index) {
        this.index = index;
    }

    public void execute() {
        MusicBandCollection.getInstance().removeAt(index);
        System.out.println("Removed");
    }
}
