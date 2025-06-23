import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 1234;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(" Chat server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(" New client connected.");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println(" Server error: " + e.getMessage());
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add this client's writer to the set
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Read client's name
                name = in.readLine();
                broadcast("[Server]: Welcome " + name + "!");

                String msg;
                while ((msg = in.readLine()) != null) {
                    broadcast("[" + name + "]: " + msg);
                }
            } catch (IOException e) {
                System.out.println(" Client disconnected: " + name);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(" Error closing socket.");
                }
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                broadcast("[Server]: " + name + " has left the chat.");
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
