package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;

public class ShowCommand implements Command {
    public ExecutionResult execute() {
        String res = "";
        res += "Вызван вывод в окллекции. В коллекции " + MusicBandCollection.getInstance().getSize() + " элементов";
        res += "\n";
        res += MusicBandCollection.getInstance().toString();
        return new ExecutionResult(res, true);
    }
}
