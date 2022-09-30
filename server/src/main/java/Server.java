import collection.MusicBandCollection;
import commandService.CommandService;
import commandService.ExecutionResult;
import commands.Command;
import communication.RequestMessage;
import communication.ResponseMessage;
import models.MusicBand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket clientSocket; //сокет для общения
    private ServerSocket server; // серверсокет

    private MusicBandCollection collectionManager;

    private final int port;

    public Server(int port) {
        this.port = port;
        this.collectionManager = MusicBandCollection.getInstance();
    }

    public void run() throws IOException {
        server = new ServerSocket(port);
        serverLoop();
        this.close();
    }

    private void serverLoop() throws IOException {
        while (true) {
            clientSocket = server.accept();
            try {
                while (true) {
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    var requestMessage = (RequestMessage) in.readObject();
                    System.out.println("Got message: " + requestMessage);
                    var commandInfo = requestMessage.commandInfo();
                    Command command = CommandService.INSTANCE.getCommand(commandInfo.name(), commandInfo.args(), (MusicBand) commandInfo.extendedData());
                    ExecutionResult result = command.execute();

                    System.out.println("Result: " + result);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(new ResponseMessage(result));
                }
            } catch (IOException | ClassNotFoundException e){
                clientSocket.close();
            }
        }
    }

    private void close() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
        server.close();
    }
}
