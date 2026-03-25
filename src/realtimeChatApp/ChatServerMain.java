package realtimeChatApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Connects the user's machine to the chat server.
 * Listens for incoming connections and manages active clients.
 * It broadcasts messages to all connected clients.
 * The details of reading or writing individual client messages
 * are delegated to the ClientHandler class.
 */
public class ChatServerMain {

    private static final int PORT = 12345;

    // Shared Set insures that each client is unique. No duplicates
    private static final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {

        System.out.println("Chat server started...");

        // Open a network port and wait for clients to connect
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {

            // Listens for incoming broadcast
            while (true) {
                // Accept a client and create a handler for it
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                // Keep track of active connected clients by adding to the shared client collection, thus making the server concurrent
                clients.add(clientHandler);

                // Spawn a new Thread for each client to handle communication without clients blocking each other
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    // Broadcast to all other connected clients allowing group communication
    public static void broadcast(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        // Remove disconnected clients from the list of actives
        clients.remove(clientHandler);
    }
}
