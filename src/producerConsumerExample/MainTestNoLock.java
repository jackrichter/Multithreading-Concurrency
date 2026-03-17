package producerConsumerExample;

public class MainTestNoLock {

    public static void main(String[] args) {

        // Producer-Consumer Example
        SharedBuffer sharedBuffer = new SharedBuffer();

        // producer thread
        Thread producerThread = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                sharedBuffer.produce(i);

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Producer Thread");

        // consumer thread
        Thread consumerThread = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                sharedBuffer.consume();

                try {
                    Thread.sleep(800L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Consumer Thread");

        producerThread.start();
        consumerThread.start();
    }
}
