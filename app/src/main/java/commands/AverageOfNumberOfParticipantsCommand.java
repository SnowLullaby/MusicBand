package commands;

import models.MusicBandCollection;

public class AverageOfNumberOfParticipantsCommand implements Command {
    public void execute() {
        System.out.printf("Average of number of participants is %f\n", MusicBandCollection.getInstance().getAverageOfNumberOfParticipants());
    }
}
