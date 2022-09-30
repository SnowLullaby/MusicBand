package commands;

public class NoMoreFreeIDError extends Exception {
    public NoMoreFreeIDError() {
        super("Нет индексов, удовлетворяющих условию");
    }
}
