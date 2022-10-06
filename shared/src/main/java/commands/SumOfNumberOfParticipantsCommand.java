package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class SumOfNumberOfParticipantsCommand implements Command {
    public ExecutionResult execute() {
        String res = String.format("Sum of number of participants is %d\n", MusicBandCollection.getInstance().getSumOfNumberOfParticipants());
        return new ExecutionResult(res, true);
    }
}
