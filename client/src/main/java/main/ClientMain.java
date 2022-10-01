package main;

import commands.CommandExecutor;
import commandService.CommandReader;
import communication.ClientCommunicator;
import communication.CommandInfo;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        var reader = new CommandReader();
        ClientCommunicator clientCommunicator = new ClientCommunicator("127.0.0.1", 12345);
        var commandExecutor = new CommandExecutor(clientCommunicator);

        while (true) {
            try {
                CommandInfo commandInfo = reader.read();
                if (commandInfo != null) {
                    commandExecutor.execute(commandInfo.name(), commandInfo.args());
                }
            } catch (IOException e) {
                clientCommunicator.connectToServer();
                throw new RuntimeException(e);
//                System.out.print("Ошибка при взаимодействии с сервером: " + e.getMessage());
            }
        }
    }
}
