package ConditionVariableExample;

public class MainTestCondition {

    public static void main(String[] args) throws InterruptedException {

        // The shared resource
        SharedQueue sharedQueue = new SharedQueue();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedQueue.produce(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();     // The Producer might be interrupted while waiting
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            // The consumer consumes 10 items
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedQueue.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();     // The Consumer might be interrupted while waiting
                }
            }
        });

        // Start the threads
        producer.start();   // generate numbers adding the to the que
        consumer.start();   // remove numbers from the queue

        // I added the joins just so that the last printout could be executed, after producer and consumer finished
        // (Join demonstration). It is unnecessary for the example itself.
        producer.join();
        consumer.join();
        System.out.println("Both threads have finished.");
    }
}
