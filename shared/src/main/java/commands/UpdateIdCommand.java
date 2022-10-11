package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import models.MusicBand;

import java.time.LocalDate;

public class UpdateIdCommand implements Command {
    private final long id;
    private MusicBand musicBand;

    public UpdateIdCommand(int id, MusicBand musicBand) {
        this.id = id;
        this.musicBand = musicBand;
    }

    @Override
    public ExecutionResult execute() {
        var bandManager = MusicBandCollection.getInstance();
        musicBand.setCreationDateAsDate(LocalDate.now());
        if (bandManager.removeByID(id) && bandManager.addElement(musicBand)) {
            bandManager.defaultSortByID();
            return new ExecutionResult("Successfully updated collection", true);
        }
        return new ExecutionResult("Failed to update collection", false);
    }
}
