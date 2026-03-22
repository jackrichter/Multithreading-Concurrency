package advancedThreadTopics.concurrencyDesignPatterns.producerconsumerdisignpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            for (int i = 1; i <= 10; i++) {
                int value = (int) (Math.random() * 100);
                System.out.println("Produced: " + value);
                queue.put(value);
                TimeUnit.MICROSECONDS.sleep(500L);      // <- OBS! TimeUnit
            }
            queue.put(-1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
