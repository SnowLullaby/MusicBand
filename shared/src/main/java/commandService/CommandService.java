package commandService;

import commands.*;
import communication.User;
import models.MusicBand;

import java.util.List;

public class CommandService {
    public static final CommandService INSTANCE = new CommandService();
    private CommandService() {};

    public Command getCommand(String name, List<String> args, MusicBand musicBand, User user) {
        try {
            return searchCommand(name, args, musicBand, user);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {System.out.println("Incorrect arguments");
            return null;
        } catch (NoCommandError e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Command searchCommand(String name, List<String> args, MusicBand musicBand, User user) throws NoCommandError, NumberFormatException {
        if (CommandsDesc.HELP.name.equals(name)) return new HelpCommand(CommandsDesc.getAllCommands());
        if (CommandsDesc.INFO.name.equals(name)) return new InfoCommand();
        if (CommandsDesc.SHOW.name.equals(name)) return new ShowCommand();
        if (CommandsDesc.ADD.name.equals(name)) return new AddCommand(musicBand, user);
        if (CommandsDesc.UPDATE.name.equals(name)) return new UpdateIdCommand(Integer.parseInt(args.get(0)), musicBand, user);
        if (CommandsDesc.REMOVE_BY_ID.name.equals(name)) return new RemoveByIdCommand(Integer.parseInt(args.get(0)), user);
        if (CommandsDesc.CLEAR.name.equals(name)) return new ClearCommand();
        if (CommandsDesc.SAVE.name.equals(name)) return new SaveCommand();
        if (CommandsDesc.EXECUTE_SCRIPT.name.equals(name)) return new ExecuteScriptCommand(args.get(0), user);
        if (CommandsDesc.EXIT.name.equals(name)) return new ExitCommand();
        if (CommandsDesc.REMOVE_AT.name.equals(name)) return new RemoveAtCommand(Integer.parseInt(args.get(0)));
        if (CommandsDesc.ADD_IF_MIN.name.equals(name)) return new AddIfMinCommand(musicBand);
        if (CommandsDesc.SHUFFLE.name.equals(name)) return new ShuffleCommand();
        if (CommandsDesc.SUM_OF_NUMBER_OF_PARTICIPANTS.name.equals(name)) return new SumOfNumberOfParticipantsCommand();
        if (CommandsDesc.AVERAGE_OF_NUMBER_OF_PARTICIPANTS.name.equals(name)) return new AverageOfNumberOfParticipantsCommand();
        if (CommandsDesc.PRINT_FIELD_DESCENDING_FRONT_MAN.name.equals(name)) return new PrintFieldDescendingFrontManCommand();
        throw new NoCommandError();
    }
}
