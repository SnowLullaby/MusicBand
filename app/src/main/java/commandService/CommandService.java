package commandService;

import commands.*;

import java.util.Scanner;

public class CommandService {
    private static CommandService instance = null;
    private CommandService() {}
    private String[] currentCommandLine = null;
    static Scanner scanner = new Scanner(System.in);

    public static CommandService getInstance() {
        if(instance == null){
            instance = new CommandService();
        }
        return instance;
    }

    public void execute() {
        currentCommandLine = scanner.nextLine().trim().split(" ");
        try {
            searchCommand(getParam(0)).execute();
        } catch (NumberFormatException e) {
            System.out.println("Incorrect argument's tip");
        } catch (NoCommandError | NoParamsError e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeFromLine(String currentCommand) {
        currentCommandLine = currentCommand.trim().split(" ");
        try {
            searchCommand(getParam(0)).execute();
        } catch (NumberFormatException e) {
            System.out.println("Incorrect argument's tip");
        } catch (NoCommandError | NoParamsError e) {
            System.out.println(e.getMessage());
        }
    }

    private Command searchCommand(String name) throws NoCommandError, NoParamsError, NumberFormatException {
        if (CommandsDesc.HELP.name.equals(name)) return new HelpCommand(CommandsDesc.getAllCommands());
        if (CommandsDesc.INFO.name.equals(name)) return new InfoCommand();
        if (CommandsDesc.SHOW.name.equals(name)) return new ShowCommand();
        if (CommandsDesc.ADD.name.equals(name)) return new AddCommand();
        if (CommandsDesc.UPDATE.name.equals(name)) return new UpdateIdCommand(Integer.parseInt(getParam(1)));
        if (CommandsDesc.REMOVE_BY_ID.name.equals(name)) return new RemoveByIdCommand(Integer.parseInt(getParam(1)));
        if (CommandsDesc.CLEAR.name.equals(name)) return new ClearCommand();
        if (CommandsDesc.SAVE.name.equals(name)) return new SaveCommand();
        if (CommandsDesc.EXECUTE_SCRIPT.name.equals(name)) return new ExecuteScriptCommand(getParam(1));
        if (CommandsDesc.EXIT.name.equals(name)) return new ExitCommand();
        if (CommandsDesc.REMOVE_AT.name.equals(name)) return new RemoveAtCommand(Integer.parseInt(getParam(1)));
        if (CommandsDesc.ADD_IF_MIN.name.equals(name)) return new AddIfMinCommand();
        if (CommandsDesc.SHUFFLE.name.equals(name)) return new ShuffleCommand();
        if (CommandsDesc.SUM_OF_NUMBER_OF_PARTICIPANTS.name.equals(name)) return new SumOfNumberOfParticipantsCommand();
        if (CommandsDesc.AVERAGE_OF_NUMBER_OF_PARTICIPANTS.name.equals(name)) return new AverageOfNumberOfParticipantsCommand();
        if (CommandsDesc.PRINT_FIELD_DESCENDING_FRONT_MAN.name.equals(name)) return new PrintFieldDescendingFrontManCommand();
        throw new NoCommandError();
    }

    private String getParam(int index) throws NoParamsError {
        try {
            return currentCommandLine[index];
        } catch (Exception e) {
            throw new NoParamsError();
        }
    }
}
