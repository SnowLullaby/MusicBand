package commands;

import commandService.ExecutionResult;

public class ExitCommand implements Command {
    public ExecutionResult execute() {
        System.out.println("See you later!");
        System.exit(0);
        return new ExecutionResult("See you later!", true);
    }
}
