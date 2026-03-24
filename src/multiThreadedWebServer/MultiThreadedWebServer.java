package multiThreadedWebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedWebServer {

    private  final int port;

    public MultiThreadedWebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        // Pool of threads
        ExecutorService threadPoll = Executors.newFixedThreadPool(10);

        // Using Try-With-Resource, we create the socket and start listening to the specified port
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is running on port: " + port);

            // Continuously refreshing the web page will still show the response because we are in an infinite loop
            while (true) {

                // Accept client connection and get the Socket Object to be sent to the client
                Socket clientSocket = serverSocket.accept();

                // Create a thread for each client from the pool, thus creating a multithreaded server
                threadPoll.submit(new ClientHandler(clientSocket));

                // Alternatively, we can
//                new Thread (new ClientHandler(clientSocket)).start();

            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
