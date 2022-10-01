package commands;

import models.MusicBandCollection;

public class AverageOfNumberOfParticipantsCommand implements Command {
    public void execute() {
        System.out.printf("В среднем в группе %f человек\n", MusicBandCollection.getInstance().getAverageOfNumberOfParticipants());
    }
}
