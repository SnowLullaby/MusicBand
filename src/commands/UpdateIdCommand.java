package commands;

import models.MusicBandCollection;

public class UpdateIdCommand extends Add {
    private final long id;
    public UpdateIdCommand (int id) { this.id = id; }

    @Override
    protected boolean addCondition() {
        return MusicBandCollection.getInstance().getElementByID(id) != null;
    }

    @Override
    protected Long calculateId() {
        return id;
    }
}
