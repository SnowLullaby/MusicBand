package commands;

import models.*;

public class SaveCommand implements Command {
    public void execute() {
        MusicBandCollection.getInstance().save();
        System.out.println("Файл сохранен");
    }
}
