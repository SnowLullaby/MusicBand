package commands;

public class NoMoreFreeIDError extends Exception {
    public NoMoreFreeIDError() {
        super("This index doesn't exist");
    }
}
