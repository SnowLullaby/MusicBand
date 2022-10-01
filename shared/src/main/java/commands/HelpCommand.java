package commands;

import commandService.ExecutionResult;

import java.util.Vector;

public class HelpCommand implements Command {
    private final Vector<CommandsDesc.FullCommandDesc> commandList;

    public HelpCommand (Vector<CommandsDesc.FullCommandDesc> commandList) {
        this.commandList = commandList;
    }

    public ExecutionResult execute(){
        StringBuilder info = new StringBuilder();
        for (CommandsDesc.FullCommandDesc co: commandList) {
            info.append(String.format("%s: %s\n", co.name, co.desc));
        }
        return new ExecutionResult(info.toString(), true);
    }
}
