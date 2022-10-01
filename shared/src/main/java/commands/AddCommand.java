package commands;

import collection.MusicBandCollection;
import models.MusicBand;

public class AddCommand extends Add {
    public AddCommand(MusicBand musicBand) {
        super(musicBand);
    }

    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().findRealMaxID() < Integer.MAX_VALUE;
    }

    @Override
    protected Long calculateId() {
        return MusicBandCollection.getInstance().findRealMaxID() + 1;
    }
}
