package commands;

import collection.MusicBandCollection;
import models.MusicBand;

public class AddIfMinCommand extends Add {
    public AddIfMinCommand(MusicBand musicBand) {
        super(musicBand);
    }

    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().getFirstID() > 1;
    }

    @Override
    protected Long calculateId() {
        return MusicBandCollection.getInstance().getFirstID() - 1;
    }
}
