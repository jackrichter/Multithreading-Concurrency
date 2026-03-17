package locksExample;

public class MainTestReadWriteLock {

    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockExample readWriteLock = new ReadWriteLockExample();

        Thread writerThread = new Thread(() -> readWriteLock.writeData(49), "Writer Thread");

        Thread reader1 = new Thread(readWriteLock::readData, "Reader 1");
        Thread reader2 = new Thread(readWriteLock::readData, "Reader 2");

        writerThread.start();
        reader1.start();
        reader2.start();

        writerThread.join();
        reader1.join();
        reader2.join();
    }
}
