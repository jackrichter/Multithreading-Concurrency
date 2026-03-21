package JVMAndThreads;

public class JVMThreadManagementExampleMain {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new CustomTask(), "Thread-1");
        Thread thread2 = new Thread(new CustomTask(), "Thread-2");
        Thread thread3 = new Thread(new CustomTask(), "Thread-3");

        System.out.println("Thread-1 State Before Start: " + thread1.getState());
        System.out.println("Thread-2 State Before Start: " + thread2.getState());
        System.out.println("Thread-3 State Before Start: " + thread3.getState());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread-1 State After Start: " + thread1.getState());
        System.out.println("Thread-2 State After Start: " + thread2.getState());
        System.out.println("Thread-3 State After Start: " + thread3.getState());
    }
}
