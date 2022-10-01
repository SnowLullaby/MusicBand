package commandService;

public class NoCommandError extends Exception {
    public NoCommandError() {
        super("Такой команды не существует!");
    }
}
