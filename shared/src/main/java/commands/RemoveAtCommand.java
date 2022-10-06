package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class RemoveAtCommand implements Command {
    private int index;
    public RemoveAtCommand(int index) {
        this.index = index;
    }

    public ExecutionResult execute() {
        MusicBandCollection.getInstance().removeAt(index);
        return new ExecutionResult("Removed", true);
    }
}
