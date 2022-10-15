package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import communication.User;
import models.MusicBand;

import java.time.LocalDate;

public class UpdateIdCommand implements Command {
    private final long id;
    private MusicBand musicBand;
    private User user;

    public UpdateIdCommand(int id, MusicBand musicBand, User user) {
        this.id = id;
        this.musicBand = musicBand;
        this.user = user;
    }

    @Override
    public ExecutionResult execute() {
        var bandManager = MusicBandCollection.getInstance();
        musicBand.setCreationDateAsDate(LocalDate.now());
        if (bandManager.removeByID(id, user) && bandManager.addElement(musicBand)) {
            bandManager.defaultSortByID();
            return new ExecutionResult("Successfully updated collection", true);
        }
        return new ExecutionResult("Failed to update collection", false);
    }
}
