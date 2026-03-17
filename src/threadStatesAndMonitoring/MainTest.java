package threadStatesAndMonitoring;

public class MainTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("Before start: " + myThread.getState()); //New
        myThread.start();
        System.out.println("After start: " + myThread.getState());  // Runnable
    }
}
