package profilingMultithreadedApp;

public class BankAccount {

    // Shared resource
    private int balance = 0;

    public synchronized void deposit(int amount){

        balance += amount;
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public synchronized void withdraw(int amount){

        if (balance >= amount){
            balance -= amount;
            try {
                Thread.sleep(100L);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }

    public synchronized int getBalance(){
        return balance;
    }
}
