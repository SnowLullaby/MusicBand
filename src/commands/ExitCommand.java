package commands;

public class ExitCommand implements Command {
    public void execute() {
        System.out.println("Благодарю за использование!");
        System.exit(0);
    }

    public String getDesc() {
        return "завершает выполнеие программы (без сохранения в файл)";
    }
}
