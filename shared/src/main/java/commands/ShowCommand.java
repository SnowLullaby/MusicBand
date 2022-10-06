package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class ShowCommand implements Command {
    public ExecutionResult execute() {
        String res = "";
        res += "There are " + MusicBandCollection.getInstance().getSize() + " elements in collection";
        res += "\n";
        res += MusicBandCollection.getInstance().toString();
        return new ExecutionResult(res, true);
    }
}
