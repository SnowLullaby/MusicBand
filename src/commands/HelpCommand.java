package commands;

import java.util.Vector;

public class HelpCommand implements Command {
    private final Vector<CommandsDesc.FullCommandDesc> commandList;

    public HelpCommand (Vector<CommandsDesc.FullCommandDesc> commandList) {
        this.commandList = commandList;
    }

    public void execute(){
        for (CommandsDesc.FullCommandDesc co: commandList) {
            System.out.printf("%s: %s\n", co.name, co.desc);
        }
    }
}
