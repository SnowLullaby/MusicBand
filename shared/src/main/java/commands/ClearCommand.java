package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class ClearCommand implements Command {
    public ExecutionResult execute() {
        MusicBandCollection.getInstance().removeAll();
        return new ExecutionResult("Collection clearing finished", true);
    }
}
