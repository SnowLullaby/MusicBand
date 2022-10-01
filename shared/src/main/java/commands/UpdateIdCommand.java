package commands;

import collection.MusicBandCollection;
import models.MusicBand;

public class UpdateIdCommand extends Add {
    private final long id;

    public UpdateIdCommand(int id, MusicBand musicBand) {
        super(musicBand);
        this.id = id;
    }

    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().getElementByID(id) != null;
    }

    @Override
    protected Long calculateId() {
        return id;
    }
}
