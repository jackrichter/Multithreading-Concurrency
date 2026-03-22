package JVMAndThreads;

public class DaemonThreadExampleMain {

    public static void main(String[] args) {

        Thread userThread = new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                System.out.println("User Thread: " + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "UserThread");

        Thread daemonThread = new Thread(()->{
            while (true) {
                System.out.println("Deamon thread is running...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DaemonThread");

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();
    }
}
