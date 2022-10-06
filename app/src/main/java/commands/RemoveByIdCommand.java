package commands;

import models.MusicBand;
import models.MusicBandCollection;

public class RemoveByIdCommand implements Command {
    long id;
    public RemoveByIdCommand(long id) { this.id = id; }

    public void execute() {
        MusicBandCollection.getInstance().removeByID(id);
        System.out.println("If the elements existed, it is removed");
    }
}
