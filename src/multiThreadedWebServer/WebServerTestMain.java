package multiThreadedWebServer;

public class WebServerTestMain {

    static void main(String[] args) {

        MultiThreadedWebServer server = new MultiThreadedWebServer(8080);

        Thread serverThread = new Thread(server::startServer);
        serverThread.start();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
