package commandService;

public class NoCommandError extends Exception {
    public NoCommandError() {
        super("Command don't exist");
    }
}
