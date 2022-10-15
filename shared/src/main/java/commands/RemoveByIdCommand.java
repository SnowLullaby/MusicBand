package commands;


import collection.MusicBandCollection;
import commandService.ExecutionResult;
import communication.User;

public class RemoveByIdCommand implements Command {
    long id;
    User user;

    public RemoveByIdCommand(long id, User user) {
        this.id = id;
        this.user = user;
    }

    public ExecutionResult execute() {
        if (MusicBandCollection.getInstance().removeByID(id, user)) {
            return new ExecutionResult("Element is removed", true);
        }
        return new ExecutionResult("Failed to remmove an element with id: " + id, false);
    }
}
