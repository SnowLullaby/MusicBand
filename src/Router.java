import commands.*;


public class Router {

    public static void execute(String commandName) throws Exception {
        var command = CommandList.getCommand(commandName);
        if (command == null) throw new Exception();
        command.execute();
    }

    public static void describe(String commandName, Command command) {
        CommandList.add(commandName, command);
    }
}

