package PerformanceAndScalability;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExampleMain {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        Runnable task1 = () -> {
            lock1.lock();
            try {
                System.out.println("Thread-1 acquired lock1. Waiting for lock2 ...");
                Thread.sleep(100L);
                lock2.lock();
                try {
                    System.out.println("Thread-1 acquired lock2.");
                } finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock1.unlock();
            }
        };

        Runnable task2 = () -> {
            lock2.lock();
            try {
                System.out.println("Thread-2 acquired lock2. Waiting for lock1 ...");
                Thread.sleep(100L);
                lock1.lock();
                try {
                    System.out.println("Thread-2 acquired lock1.");
                } finally {
                    lock1.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock2.unlock();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
