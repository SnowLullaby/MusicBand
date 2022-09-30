package commandService;

import communication.CommandInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandReader {
    private static final Scanner scanner = new Scanner(System.in);

    public CommandInfo read() {
        String s = scanner.nextLine();
        return parseCommandLine(s);
    }

    public static CommandInfo parseCommandLine(String line) {
        List<String> commandLine = List.of(line.trim().split(" "));
        if (commandLine.size() == 0) {
            return null;
        }
        return new CommandInfo(commandLine.get(0), new ArrayList<>(commandLine.subList(1, commandLine.size())), null);
    }
}
