package realtimeChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The server-side worker.
 * A list or Set of active clients connections to broadcast messages effectively.
 * It is the Set of Shared Resources. Each instance represents a single client.
 * Implementing Runnable means that it can be passed to a Thread and run concurrently.
 */
public class ClientHandler implements Runnable{

    // The Socket is the direct communication link between the client and the server
    private final Socket clientSocket;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        // Listen to messages from the client using an InputStream wrapped in a BufferReader to effectively handle input
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                ChatServerMain.broadcast(message, this);
            }

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        } finally {
            ChatServerMain.removeClient(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}
