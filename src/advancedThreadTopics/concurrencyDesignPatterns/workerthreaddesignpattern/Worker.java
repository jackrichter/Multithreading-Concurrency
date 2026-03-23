package advancedThreadTopics.concurrencyDesignPatterns.workerthreaddesignpattern;

import java.util.concurrent.BlockingQueue;

/**
 * This class is responsible for running the Tasks assigned to it.
 */
public class Worker extends Thread{

    // The shared Task-queue for thread-sase process management of the Tasks by the Workers-pool
    private final BlockingQueue<Runnable> taskQueue;

    public Worker(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {

        // Continuously pick tasks from the queue and execute them. Infinite loop to keep the worker thread alive
        while (true) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Terminate the loop
                break;
            }
        }
    }
}
