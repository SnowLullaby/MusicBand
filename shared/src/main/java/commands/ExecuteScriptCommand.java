package commands;

import commandService.CommandReader;
import commandService.CommandService;
import commandService.ExecutionResult;
import communication.CommandInfo;
import parsers.ScriptParser;

public class ExecuteScriptCommand implements Command {
    private final String fileName;

    public ExecuteScriptCommand(String fileName) {
        this.fileName = fileName;
    }

    public ExecutionResult execute() {
        try {
            var commands = ScriptParser.parse(fileName);
            for (String commandLine : commands) {
                CommandInfo commandInfo = CommandReader.parseCommandLine(commandLine);
                if (commandInfo != null) {
                    Command command = CommandService.INSTANCE.getCommand(commandInfo.name(), commandInfo.args(), null);
                    command.execute();
                }
            }
            return new ExecutionResult("Скрипт выполнен", true);
        } catch (Exception e) {
            return new ExecutionResult("Ошибка при доступе к скрипту", false);
        }
    }
}
