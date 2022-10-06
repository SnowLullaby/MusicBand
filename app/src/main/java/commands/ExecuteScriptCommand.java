package commands;

import commandService.CommandService;
import parsers.ScriptParser;

public class ExecuteScriptCommand implements Command {
    private String fileName;
    public ExecuteScriptCommand (String fileName) {
        this.fileName = fileName;
    }

    public void execute() {
        try {
            var commands = ScriptParser.parse(fileName);
            for (String command: commands) {
                CommandService.getInstance().executeFromLine(command);
            }
            System.out.println("Script's execution finished");
        } catch (Exception e) {
            System.out.println("Access to file denied");
        }

    }
}
