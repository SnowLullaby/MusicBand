import java.util.Scanner;

public class CommandService {
    private static CommandService instance = null;
    private CommandService() {};
    static Scanner scanner = new Scanner(System.in);
    private String[] currentCommandLine = null;

    public static CommandService getInstance() {
        if(instance == null){
            instance = new CommandService();
        }
        return instance;
    }

    public void execute() {
        var currentCommandLine = scanner.nextLine().trim().split(" ");

    }

    private void searchCommand() {
        switch (currentCommandLine[0]){
            case ("help"):
                System.out.println("gdfgdg");
                break;
            default:
                break;
        }
    }
}
