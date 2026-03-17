package threading;

public class MainTest {

    public static void main(String[] args) {

//        MyThread myThread = new MyThread();
//        myThread.start();
//        System.out.println(("Thread is started."));

        MyRunnable myRunnable = new MyRunnable();
        Thread myThread2 = new Thread(myRunnable);
        myThread2.start();
        System.out.println(("Thread is started."));
    }
}
