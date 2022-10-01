package communication;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientCommunicator {
    private final String host;
    private final int port;
    private static final int MAX_RETRY_COUNT = 10;

    private SocketChannel socketChannel;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientCommunicator(String host, int port) {
        this.host = host;
        this.port = port;
        connectToServer();
    }

    public void send(RequestMessage message) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();

        oos.writeObject(message);
        byte[] data = bos.toByteArray();
        socketChannel.write(ByteBuffer.wrap(data));
    }

    public ResponseMessage receive() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
        socketChannel.read(byteBuffer);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        ResponseMessage responseMessage;
        try {
            responseMessage = (ResponseMessage) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Received invalid class");
            throw new IOException(e.getMessage());
        }
        return responseMessage;
    }

    public void connectToServer() {
        if (!connect()) {
            System.out.println("Не удалось подключиться");
            System.exit(1);
        }
    }

    private boolean connect() {
        for (int i = 0; i < MAX_RETRY_COUNT; i++) {
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
                socketChannel = SocketChannel.open(inetSocketAddress);
                socketChannel.configureBlocking(false);
                System.out.println("Подключение успешно");
                return true;
            } catch (IOException ignored) {}
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return false;
    }
}
