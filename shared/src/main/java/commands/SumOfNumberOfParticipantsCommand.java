package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class SumOfNumberOfParticipantsCommand implements Command {
    public ExecutionResult execute() {
        String res = String.format("Суммарно в группах %d человек\n", MusicBandCollection.getInstance().getSumOfNumberOfParticipants());
        return new ExecutionResult(res, true);
    }
}
