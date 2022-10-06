package commandService;

public class NoParamsError extends Exception {
    public NoParamsError() {
        super("Not enough arguments");
    }
}
