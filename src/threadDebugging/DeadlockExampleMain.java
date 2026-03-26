package threadDebugging;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The tool to help debug this deadlock it is jstack!
 * 1. Open a Terminal
 * 2. Find the process id of this program with the "jps" command
 * 3. Run jstack + process id. It gives as the thread dump with stack trace and info on the deadlock
 * 4. Scroll down to the section: Found one Java-level deadlock
 */
public class DeadlockExampleMain {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        Runnable task1 = () -> {
            try {
                lock1.lock();
                System.out.println("Thread 1 acquired lock 1");
                Thread.sleep(500L);
                lock2.lock();
                System.out.println("Thread 1 acquired lock 2");
                Thread.sleep(500L);
                lock2.unlock();
                Thread.sleep(500L);
                lock1.unlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Trouble section causing the deadlock: Aquiring the lock in different order
//        Runnable task2 = () -> {
//            try {
//                lock2.lock();
//                System.out.println("Thread 2 acquired lock 2");
//                Thread.sleep(500L);
//                lock1.lock();
//                System.out.println("Thread 2 acquired lock 1");
//                Thread.sleep(500L);
//                lock1.unlock();
//                Thread.sleep(500L);
//                lock2.unlock();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        };

        // Solution: Aquire the locks in the same order
        Runnable task2 = () -> {
            try {
                lock1.lock();
                System.out.println("Thread 2 acquired lock 1");
                Thread.sleep(500L);
                lock2.lock();
                System.out.println("Thread 2 acquired lock 2");
                Thread.sleep(500L);
                lock2.unlock();
                Thread.sleep(500L);
                lock1.unlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread thread1 = new Thread(task1, "Thread 1");
        Thread thread2 = new Thread(task2, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
