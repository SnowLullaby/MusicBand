package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import communication.User;
import models.MusicBand;

import java.time.LocalDate;

public class AddCommand implements Command {
    private final MusicBand musicBand;
    private final User user;

    public AddCommand(MusicBand musicBand, User user) {
        this.musicBand = musicBand;
        this.user = user;
    }

    @Override
    public ExecutionResult execute() {
        musicBand.userName = user.username();
        musicBand.setCreationDateAsDate(LocalDate.now());
        if (MusicBandCollection.getInstance().addElement(musicBand)) {
            MusicBandCollection.getInstance().defaultSortByID();
            return new ExecutionResult("Successfully add element", true);
        }
        return new ExecutionResult("Failed to add element", false);
    }
}
