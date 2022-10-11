package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import models.MusicBand;

import java.time.LocalDate;

public class AddIfMinCommand implements Command {
    private final MusicBand musicBand;

    public AddIfMinCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public ExecutionResult execute() {
        var manager = MusicBandCollection.getInstance();
        musicBand.setCreationDateAsDate(LocalDate.now());
        if (manager.addElement(musicBand)) {
            manager.defaultSortByID();
            return new ExecutionResult("Successfully added element", true);
        }
        return new ExecutionResult("Failed to add element", false);
    }
}
