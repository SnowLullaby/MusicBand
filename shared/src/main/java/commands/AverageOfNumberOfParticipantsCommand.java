package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class AverageOfNumberOfParticipantsCommand implements Command {
    public ExecutionResult execute() {
        String res = String.format("В среднем в группе %f человек\n", MusicBandCollection.getInstance().getAverageOfNumberOfParticipants());
        return new ExecutionResult(res, true);
    }
}
