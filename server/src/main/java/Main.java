import java.io.IOException;

public class Main {

    /**
     * Initialize collection and call reader command
     */
    public static void main(String[] args) {
        Server server = new Server(12345);

        try {
            server.run();
        } catch (IOException e) {
            System.out.println("Server stopped with error: " + e.getMessage());
        }
    }
}
