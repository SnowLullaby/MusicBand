package commands;

import commandService.ExecutionResult;

public class ExitCommand implements Command {
    public ExecutionResult execute() {
        System.out.println("Благодарю за использование!");
        System.exit(0);
        return new ExecutionResult("Благодарю за использование!", true);
    }
}
