package PerformanceAndScalability;

public class SafeCounterMain {

    public static void main(String[] args) {

        ThreadSafeCounter threadSafeCounter = new ThreadSafeCounter();
        Runnable task = threadSafeCounter::increment;

        Thread t1 = new Thread(task, "Theread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
