package commands;

import models.MusicBandCollection;
import java.time.LocalDateTime;

public class InfoCommand implements Command {
    public void execute() {
        LocalDateTime creationTime = MusicBandCollection.getInstance().getCreationTime();
        int size = MusicBandCollection.getInstance().getSize();
        System.out.printf("Creation time: %s\nSize: %s\n", creationTime, size);
    }
}
