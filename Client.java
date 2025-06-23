import java.io.*;
import java.net.*;

public class ChatClient {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println(" Connected to chat server.");

            // Prompt for name
            System.out.print("Enter your name: ");
            String name = input.readLine();
            out.println(name); // Send name to server

            // Thread to read messages from server
            Thread readThread = new Thread(() -> {
                String serverMsg;
                try {
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println(" Disconnected from server.");
                }
            });
            readThread.start();

            // Main thread to send messages
            String userMsg;
            while ((userMsg = input.readLine()) != null) {
                if (userMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chat...");
                    break;
                }
                out.println(userMsg);
            }

            socket.close(); // Close connection after exit

        } catch (IOException e) {
            System.out.println(" Error connecting to server: " + e.getMessage());
        }
    }
}
