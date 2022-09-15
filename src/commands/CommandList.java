package commands;

import java.util.Vector;

public class CommandList {
    private static final Vector<CommandList.CommandStructure> commandList = new Vector<>();

    private static class CommandStructure {
        String commandName;
        Command command;
    }

    public static Command getCommand(String commandName) {
        for (var currentCommand : commandList) {
            if (currentCommand.commandName.equals(commandName)) {
                return currentCommand.command;
            }
        }
        return null;
    }

    public static void add(String commandName, Command command) {
        var currentCommand = new CommandStructure();
        currentCommand.commandName = commandName;
        currentCommand.command = command;
        commandList.add(currentCommand);
    }

    public static class CommandOutput {
        public String name;
        public String desc;
    }

    public static Vector<CommandOutput> getCommandsOutput() {
        var resultVector = new Vector<CommandOutput>();
        for (var currentCommand : commandList) {
            var co = new CommandOutput();
            co.name = currentCommand.commandName;
            co.desc = currentCommand.command.getDesc();
            resultVector.add(co);
        }
        return resultVector;
    }
}
