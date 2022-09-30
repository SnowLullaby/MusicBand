package commands;

import models.MusicBandCollection;

public class AddCommand extends Add {
    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().findRealMaxID() < Integer.MAX_VALUE;
    }

    @Override
    protected Long calculateId() {
        return MusicBandCollection.getInstance().findRealMaxID() + 1;
    }
}
