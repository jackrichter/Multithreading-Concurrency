package multiThreadedWebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{

    // Needs a Socket for each client connection
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        // Reader and writer using Try-With-Resource
        try(OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Read client request
            String line;
            while (!(line = reader.readLine()).isEmpty()) {
                System.out.println(line);
            }

            // Create the response and send it to the client
            String httpResponse = "HTTP/1.1 200 OK\nContent-Type: text/html\nContent-Length: 11\n\nHello World";
            outputStream.write(httpResponse.getBytes());

        }catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }
}
