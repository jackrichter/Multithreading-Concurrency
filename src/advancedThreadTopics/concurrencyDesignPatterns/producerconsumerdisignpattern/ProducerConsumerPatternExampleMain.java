package advancedThreadTopics.concurrencyDesignPatterns.producerconsumerdisignpattern;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPatternExampleMain {

    public static void main(String[] args) {

        // The shared thread-safe buffer. The communication means between the Producer and the Consumer
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        System.out.println("Start!");

        producer.start();
        consumer.start();

        // Join makes the main thread wait until both threads are done, preventing the program from exiting too early
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Done!");
    }
}
