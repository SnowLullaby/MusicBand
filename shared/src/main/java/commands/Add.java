package commands;

import collection.MusicBandCollection;
import commandService.ExecutionResult;
import models.*;

import java.time.LocalDate;

abstract class Add implements Command {
    private final MusicBand musicBand;

    public Add(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    public ExecutionResult execute() {
        try {
            setIDAndDate(musicBand);
            MusicBandCollection.getInstance().addElement(musicBand);
            MusicBandCollection.getInstance().defaultSortByID();
            return new ExecutionResult("Element added", true);
        } catch (NoMoreFreeIDError e) {
            return new ExecutionResult(e.getMessage(), false);
        }
    }

    protected abstract boolean addCondition();

    protected abstract Long calculateId();

    protected void setIDAndDate(MusicBand musicBand) throws NoMoreFreeIDError {
        if (addCondition()) {
            musicBand.id = calculateId();
            musicBand.setCreationDateAsDate(LocalDate.now());
        } else throw new NoMoreFreeIDError();
    }
}