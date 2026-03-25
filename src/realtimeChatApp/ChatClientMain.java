package realtimeChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The client-side entry point. Handles the client-side logic.
 * The program the user runs to join the chat.
 * It connects to the server and sends /receives messages in real-time.
 */
public class ChatClientMain {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {

        // Connect to the server using a (client) Socket
        try(Socket socket = new Socket(SERVER_ADDRESS, PORT);
            // BufferReader to read messages from the server and PrintWriter to send messages to the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // BufferedReader to read user input from the console
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to chat server.");

            // Thread for continuously receiving messages from the server without blocking
            new Thread(() -> {
                String message;
                try {
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }).start();

            // In the main thread, read user input from the console and send it to the server
            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
            }

        } catch (UnknownHostException e) {
            System.out.println("Cannot determine IP address of the server. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
