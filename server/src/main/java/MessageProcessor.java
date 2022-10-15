import commandService.CommandService;
import commandService.ExecutionResult;
import commands.Command;
import communication.CommandInfo;
import communication.RequestMessage;
import communication.ResponseMessage;
import communication.User;
import collection.DbPersistenceManager;
import models.MusicBand;

import java.util.List;
import java.util.Objects;

public class MessageProcessor {
    private final DbPersistenceManager persistenceManager;

    public MessageProcessor(DbPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public ResponseMessage process(RequestMessage message) {
        System.out.println("Got message: " + message);
        ExecutionResult result;

        var commandInfo = message.commandInfo();
        if (Objects.equals(commandInfo.name(), ("login")) || Objects.equals(commandInfo.name(), "register")) {
            result = processAuth(commandInfo);
        } else if (!isAuthenticated(message.user())) {
            result = new ExecutionResult("Unauthorised. Enter login or register", false);
        } else {
            result = executeCommand(commandInfo, message.user());
        }
        System.out.println("Result: " + result);
        return new ResponseMessage(result);
    }

    private boolean isAuthenticated(User user) {
        if (user == null) return false;
        String encryptedPass = Encryption.encrypt(user.password());
        return persistenceManager.getUser(user.username()).filter((dbUser) -> Objects.equals(dbUser.password(), encryptedPass)).isPresent();
    }

    private ExecutionResult processAuth(CommandInfo commandInfo) {
        String fail = "Failed to authorize, try again.";
        boolean isOk;
        List<String> args = commandInfo.args();
        try {
            String username = args.get(0);
            String password = args.get(1);
            var user = new User(username, password, -1);
            boolean isAuth = isAuthenticated(user);
            if (isAuth && (Objects.equals(commandInfo.name(), "login"))) {
                isOk = true;
            } else if (!isAuth && Objects.equals(commandInfo.name(), "register")) {
                String encryptedPass = Encryption.encrypt(password);
                persistenceManager.addUser(new User(username, encryptedPass, -1));
                isOk = true;
            } else {
                isOk = false;
            }
        } catch (IndexOutOfBoundsException e) {
            isOk = false;
        }
        return isOk ? new ExecutionResult("Authenticated successful!", true): new ExecutionResult(fail, false);
    }

    private ExecutionResult executeCommand(CommandInfo commandInfo, User user) {
        Command command = CommandService.INSTANCE.getCommand(commandInfo.name(), commandInfo.args(), (MusicBand) commandInfo.extendedData(), user);
        if (command == null) {
            return new ExecutionResult("Undefined command", false);
        } else {
            return command.execute();
        }
    }
}
