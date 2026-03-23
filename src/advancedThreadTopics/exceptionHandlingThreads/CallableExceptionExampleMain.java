package advancedThreadTopics.exceptionHandlingThreads;

import java.util.concurrent.*;

public class CallableExceptionExampleMain {

    public static void main(String[] args) {

        while (true) {

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Callable<String> task = () -> {

                System.out.println(Thread.currentThread().getName() + ": Task started.");
                double value = Math.random();
                System.out.println("Generated number for thread: " + Thread.currentThread().getName() + " is " + value);
                if (value > 0.5) {
                    throw new RuntimeException("Simulated error!");
                }
                Thread.sleep(1000L);

                return (Thread.currentThread().getName() + ": Task completed successfully.");
            };

            // Submit the task to the Callable service and store the returned Future
            Future<String> future = executor.submit(task);

            // Retrieve the result from the Future, handle exceptions and shutdown the executor service
            try {
                String result = future.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task interrupted");
            } catch (ExecutionException e) {
                System.out.println("Task failed with exception: " + e.getCause().getMessage());
                break;
            } finally {
                executor.shutdown();
            }
        }
    }
}
