package commands;

import models.MusicBandCollection;

public class SumOfNumberOfParticipantsCommand implements Command {
    public void execute() {
        System.out.printf("Sum of number of participants is %d\n", MusicBandCollection.getInstance().getSumOfNumberOfParticipants());
    }
}
