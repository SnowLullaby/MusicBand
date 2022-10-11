package commands;


import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class RemoveByIdCommand implements Command {
    long id;

    public RemoveByIdCommand(long id) {
        this.id = id;
    }

    public ExecutionResult execute() {
        if (MusicBandCollection.getInstance().removeByID(id)) {
            return new ExecutionResult("Element is removed", true);
        }
        return new ExecutionResult("Failed to remmove an element with id: " + id, false);
    }
}
