package producerConsumerExample;

public class MainTestWithLock {

    public static void main(String[] args) {

        // Producer-Consumer Example With Lock
        SharedBufferWithLock sharedBufferWithLock = new SharedBufferWithLock();

        // producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedBufferWithLock.produce(i);

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Producer Thread");

        // consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedBufferWithLock.consume();

                try {
                    Thread.sleep(800L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Consumer Thread");

        producer.start();
        consumer.start();
    }
}
