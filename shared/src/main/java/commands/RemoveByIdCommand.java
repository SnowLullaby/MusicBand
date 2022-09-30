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
        return new ExecutionResult("Если элемент с таким ID существовал - он удален", true);
    }
}
