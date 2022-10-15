import collection.DbPersistenceManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private final MessageProcessor messageProcessor;
    private Socket clientSocket; //сокет для общения
    private ServerSocket server; // серверсокет

    private final ForkJoinPool pool;

    private final int port;

    public Server(int port) {
        this.port = port;
        this.pool = new ForkJoinPool(4);
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
            RequestRunner requestRunner = new RequestRunner(clientSocket, messageProcessor);
            pool.execute(requestRunner);
        }
    }

    private void close() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
        server.close();
    }
}
