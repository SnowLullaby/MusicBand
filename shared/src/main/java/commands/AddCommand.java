package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import models.MusicBand;

import java.time.LocalDate;

public class AddCommand implements Command {
    private final MusicBand musicBand;

    public AddCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public ExecutionResult execute() {
        musicBand.setCreationDateAsDate(LocalDate.now());
        if (MusicBandCollection.getInstance().addElement(musicBand)) {
            MusicBandCollection.getInstance().defaultSortByID();
            return new ExecutionResult("Successfully add element", true);
        }
        return new ExecutionResult("Failed to add element", false);
    }
}
