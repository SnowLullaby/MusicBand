package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class SaveCommand implements Command {
    public ExecutionResult execute() {
        MusicBandCollection.getInstance().save();
        return new ExecutionResult("File saved", true);
    }
}
