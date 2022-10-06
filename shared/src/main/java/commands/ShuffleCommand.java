package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class ShuffleCommand implements Command {
    public ExecutionResult execute() {
        MusicBandCollection.getInstance().shuffle();
        return new ExecutionResult("Shuffled", true);
    }
}
