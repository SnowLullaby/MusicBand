package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import models.Person;

import java.util.Vector;

public class PrintFieldDescendingFrontManCommand implements Command {
    public ExecutionResult execute() {
        MusicBandCollection.getInstance().descendingSortByID();
        Vector<Person> frontManCollection = MusicBandCollection.getInstance().getFrontManCollection();
        StringBuilder res = new StringBuilder();
        for (Person frontMan: frontManCollection) {
            res.append(frontMan.toString()).append("\n");
        }
        MusicBandCollection.getInstance().defaultSortByID();
        return new ExecutionResult(res.toString(), true);
    }
}
