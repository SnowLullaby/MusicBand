import communication.RequestMessage;
import communication.ResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestRunner implements Runnable {
    private final MessageProcessor messageProcessor;
    private final Socket clientSocket;

    public RequestRunner(Socket clientSocket, MessageProcessor messageProcessor)  {
        this.clientSocket = clientSocket;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                var requestMessage = (RequestMessage) in.readObject();
                ResponseMessage result = messageProcessor.process(requestMessage);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(result);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
