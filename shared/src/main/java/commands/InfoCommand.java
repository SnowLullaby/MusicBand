package commands;


import collection.MusicBandCollection;
import commandService.ExecutionResult;

import java.time.LocalDateTime;

public class InfoCommand implements Command {
    public ExecutionResult execute() {
        LocalDateTime creationTime = MusicBandCollection.getInstance().getCreationTime();
        int size = MusicBandCollection.getInstance().getSize();
        String res = String.format("Creation time: %s\nSize: %s\n", creationTime, size);
        return new ExecutionResult(res, true);
    }
}
