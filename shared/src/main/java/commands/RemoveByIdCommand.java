package commands;


import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class RemoveByIdCommand implements Command {
    long id;

    public RemoveByIdCommand(long id) {
        this.id = id;
    }

    public ExecutionResult execute() {
        MusicBandCollection.getInstance().removeByID(id);
        return new ExecutionResult("If the elements existed, it is removed", true);
    }
}
