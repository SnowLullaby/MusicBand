package commands;

public class ExitCommand implements Command {
    public void execute() {
        System.out.println("Благодарю за использование!");
        System.exit(0);
    }
}
