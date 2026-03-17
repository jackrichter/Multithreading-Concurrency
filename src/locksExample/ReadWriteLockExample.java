package locksExample;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private int data = 0;

    // write method
    public  void writeData(int value) {

        writeLock.lock();
        try {
            data = value;
            System.out.println(Thread.currentThread().getName() + " Wrote: " + data);
        } finally {
            writeLock.unlock();
        }
    }

    // read method
    public void readData() {

        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Read: " + data);
        } finally {
            readLock.unlock();
        }
    }
}
