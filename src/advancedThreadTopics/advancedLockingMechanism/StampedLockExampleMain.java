package advancedThreadTopics.advancedLockingMechanism;

public class StampedLockExampleMain {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        // Define tasks
        Runnable depositTask = () -> {
            for (int i = 0; i < 3; i++) {
                account.deposit(100);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable withdrawTask = () -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(50);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable readTask = () -> {
            for (int i = 0; i < 3; i++) {
                account.getBalanceOptimistic();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Create and Start threads to execute their tasks concurrently
        Thread depositThread = new Thread(depositTask, "Depositor");
        Thread withdrawThread = new Thread(withdrawTask, "Withdrawer");
        Thread readThread = new Thread(readTask, "Reader");

        depositThread.start();
        withdrawThread.start();
        readThread.start();

        // The main thread waits for all three worker threads to finish
        try {
            depositThread.join();
            withdrawThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
