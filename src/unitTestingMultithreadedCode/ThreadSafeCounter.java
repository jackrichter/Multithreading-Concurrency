package unitTestingMultithreadedCode;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCounter {

    // The shared resource
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
