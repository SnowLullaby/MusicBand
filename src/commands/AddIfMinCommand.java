package commands;

import models.*;

public class AddIfMinCommand extends Add {
    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().getFirstID() > 1;
    }

    @Override
    protected Long calculateId() {
        return MusicBandCollection.getInstance().getFirstID() - 1;
    }
}
