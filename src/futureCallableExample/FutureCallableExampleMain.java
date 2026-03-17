package futureCallableExample;

import java.util.concurrent.*;

public class FutureCallableExampleMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> callableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is executing callable task");

            Thread.sleep(2000L);

            return "Result from " + threadName;
        };

        Future<String> futureResult = executorService.submit(callableTask);
        String result = futureResult.get();

        System.out.println("Result from future: " + result);
        executorService.shutdown();
    }
}
