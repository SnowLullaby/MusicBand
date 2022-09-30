package commands;

import models.MusicBandCollection;
import models.Person;

import java.util.Vector;

public class PrintFieldDescendingFrontManCommand implements Command {
    public void execute() {
        MusicBandCollection.getInstance().descendingSortByID();
        Vector<Person> frontManCollection = MusicBandCollection.getInstance().getFrontManCollection();
        for (Person frontMan: frontManCollection) {
            System.out.println(frontMan.toString());
        }
        MusicBandCollection.getInstance().defaultSortByID();

    }
}
