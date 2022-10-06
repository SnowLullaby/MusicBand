package commands;

public class ExitCommand implements Command {
    public void execute() {
        System.out.println("See you later!");
        System.exit(0);
    }
}
