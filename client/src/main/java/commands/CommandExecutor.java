package commands;

import commandService.CommandService;
import communication.ClientCommunicator;
import communication.CommandInfo;
import communication.RequestMessage;
import communication.ResponseMessage;
import model.ModelReader;
import models.MusicBand;

import java.io.IOException;
import java.util.List;

public class CommandExecutor {
    private static final List<String> extendedCommands = List.of(new String[]{"add", "addIdMin", "addIfMax"});
    private static final ModelReader modelReader = new ModelReader();
    private final ClientCommunicator clientCommunicator;

    public CommandExecutor(ClientCommunicator clientCommunicator) {
        this.clientCommunicator = clientCommunicator;
    }

    public void execute(String commandName, List<String> args) throws IOException {
        MusicBand data = null;
        if (extendedCommands.contains(commandName)) {
            data = modelReader.readModel();
        }
        processRemoteCommand(new CommandInfo(commandName, args, data));
    }

    private void processRemoteCommand(CommandInfo commandInfo) throws IOException {
        this.clientCommunicator.send(new RequestMessage(commandInfo));
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ResponseMessage response = this.clientCommunicator.receive();
        processResponse(response);
    }

    private void processResponse(ResponseMessage responseMessage) {
        if (!responseMessage.result().isOk()){
            System.out.println("Error: ");
        }
        System.out.println(responseMessage.result().message());
    }
}
