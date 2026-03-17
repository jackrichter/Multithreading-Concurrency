package counterExampleinThread;

public class MainTestVolatile {

    static void main(String[] args) {

        VolatileExample volatileExample = new VolatileExample();

        Thread thread = new Thread(volatileExample::run);
        thread.start();
        System.out.println("Thread is started.");

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        volatileExample.stopRunning();
    }
}
