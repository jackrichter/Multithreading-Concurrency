package JVMAndThreads;

public class CustomTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread Name" + Thread.currentThread().getName() + ", State: " + Thread.currentThread().getState());
    }
}
