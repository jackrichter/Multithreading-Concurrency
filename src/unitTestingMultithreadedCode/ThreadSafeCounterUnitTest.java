package unitTestingMultithreadedCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * To add junit-jupiter capabilities:
 * File->Project Structure->Modules->Dependencies->+>JARs or Directories,
 * Navigate to C:\Users\Acer\.m2\repository\org\junit\jupiter\junit-jupiter-api\6.0.3
 * Add JAR file junit-jupiter-api-6.0.3
 * Navigate to C:\Users\Acer\.m2\repository\org\junit\platform\junit-platform-engine\6.0.3
 * Add JAR file junit-platform-engine-6.0.3
 * Navigate to C:\Users\Acer\.m2\repository\org\junit\platform\junit-platform-commons\6.0.3
 * Add JAR file junit-platform-commons-6.0.3
 */
public class ThreadSafeCounterUnitTest {

    @Test
    public void testCounterWithMultipleThreads() throws InterruptedException {

        ThreadSafeCounter counter = new ThreadSafeCounter();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            // Increment the counter concurrently, creating a 1000 of threads that increment counter concurrently
            executorService.submit(counter::increment);
        }

        executorService.shutdown();

        // Wait for all threads to complete
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        Assertions.assertEquals(1000, counter.getCounter(), "Counter value is incorrect");
    }
}
