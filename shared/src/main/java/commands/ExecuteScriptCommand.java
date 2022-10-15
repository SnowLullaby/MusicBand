package commands;

import commandService.CommandReader;
import commandService.CommandService;
import commandService.ExecutionResult;
import communication.CommandInfo;
import communication.User;
import parsers.ScriptParser;

public class ExecuteScriptCommand implements Command {
    private final String fileName;
    private User user;

    public ExecuteScriptCommand(String fileName, User user) {
        this.fileName = fileName;
        this.user = user;
    }

    public ExecutionResult execute() {
        try {
            var commands = ScriptParser.parse(fileName);
            for (String commandLine : commands) {
                CommandInfo commandInfo = CommandReader.parseCommandLine(commandLine);
                if (commandInfo != null) {
                    Command command = CommandService.INSTANCE.getCommand(commandInfo.name(), commandInfo.args(), null, user);
                    command.execute();
                }
            }
            return new ExecutionResult("Script's execution finished", true);
        } catch (Exception e) {
            return new ExecutionResult("Access to file denied", false);
        }
    }
}
