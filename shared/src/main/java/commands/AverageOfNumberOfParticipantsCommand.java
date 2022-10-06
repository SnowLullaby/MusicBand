package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class AverageOfNumberOfParticipantsCommand implements Command {
    public ExecutionResult execute() {
        String res = String.format("Average of number of participants is %f\n", MusicBandCollection.getInstance().getAverageOfNumberOfParticipants());
        return new ExecutionResult(res, true);
    }
}
