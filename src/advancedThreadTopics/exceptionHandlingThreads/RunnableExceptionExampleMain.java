package advancedThreadTopics.exceptionHandlingThreads;

public class RunnableExceptionExampleMain {

    public static void main(String[] args) {

        // Call the task's run method via a Lambda expression
        Runnable task = () -> {

            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": Task started.");
                    double value = Math.random();
                    System.out.println("Generated number for thread: " + Thread.currentThread().getName() + " is " + value);
                    if (value > 0.5) {
                        throw new RuntimeException("Simulated error!");
                    }
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + ": Task completed successfully.");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + ": Caught exception - " + e.getMessage());
                    break;
                }
            }
        };

        // Create and start two threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
