package advancedThreadTopics.concurrencyDesignPatterns.workerthreaddesignpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThreadPatternExampleMain {

    public static void main(String[] args) {

        // Hold the Tasks
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // A fixed number for the pool of working threads that will process the Tasks concurrently
        int numberOfWorkers = 3;    // pool size
        Worker[] workers = new Worker[numberOfWorkers]; // The pool

        // Start workers
        for (int i = 0; i < numberOfWorkers; i++) {
            workers[i] = new Worker(taskQueue);
            workers[i].start();
        }

        // Add tasks to the queue
        for (int i = 1; i <= 10; i++) {
            taskQueue.add(new Task(i));
        }

        // Define some delay for the tasks to be completed
        try {
            Thread.sleep(12000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop the workers after processing all tasks by interruptimg them
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }
}
