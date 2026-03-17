package locksExample;

public class MainTestReentrantLock {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();

        Thread t1 = new Thread(reentrantLockExample::increment, "Thread 1");
        Thread t2 = new Thread(reentrantLockExample::increment, "Thread 2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Both threads have finished.");
    }
}
