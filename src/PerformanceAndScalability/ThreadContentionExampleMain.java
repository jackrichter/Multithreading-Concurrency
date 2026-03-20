package PerformanceAndScalability;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadContentionExampleMain {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int counter = 0;     // The shared resource

    public static void main(String[] args) {

        Runnable task = () -> {

            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " is incrementing the counter");
                    counter++;
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " is releasing the lock");
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
