import collection.MusicBandCollection;
import communication.RequestMessage;
import communication.ResponseMessage;
import collection.DbPersistenceManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final MessageProcessor messageProcessor;
    private Socket clientSocket; //сокет для общения
    private ServerSocket server; // серверсокет

    private MusicBandCollection collectionManager;

    private final int port;

    public Server(int port) {
        this.port = port;
        this.collectionManager = MusicBandCollection.getInstance();
        messageProcessor = new MessageProcessor(DbPersistenceManager.INSTANCE);
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
                    ResponseMessage result = messageProcessor.process(requestMessage);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(result);
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
