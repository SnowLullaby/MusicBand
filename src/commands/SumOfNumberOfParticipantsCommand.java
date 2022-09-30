package commands;

import models.MusicBandCollection;

public class SumOfNumberOfParticipantsCommand implements Command {
    public void execute() {
        System.out.printf("Суммарно в группах %d человек\n", MusicBandCollection.getInstance().getSumOfNumberOfParticipants());
    }
}
