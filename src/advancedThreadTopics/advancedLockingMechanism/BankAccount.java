package advancedThreadTopics.advancedLockingMechanism;

import java.util.concurrent.locks.StampedLock;

public class BankAccount {

    // The shared resource for thread-safe access
    private double balance;

    private final StampedLock lock = new StampedLock();

    public void deposit(double amount) {
        long stamp = lock.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + " depositing: " + amount);
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
        }finally {
            lock.unlockWrite(stamp);
        }
    }

    public void withdraw(double amount) {
        long stamp = lock.writeLock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + " new balance: " + balance);
            } else
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", Insufficient balance");
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public double getBalanceOptimistic() {
        long stamp = lock.tryOptimisticRead();

        // Try reading optimistically
        double currentBalance = balance;

        // Check if the optimistic lock is still valid
        if (!lock.validate(stamp)) {

            // Require the lock again to read the balance and ensure consistency
            stamp = lock.readLock();

            try {
                currentBalance = balance;
            } finally {
                // Release the Read lock if it was acquired
                lock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " reading current balance: " + currentBalance);

        return currentBalance;
    }

    public double getBalance() {
        long stamp = lock.readLock();

        try {
            return balance;
        }finally {
            lock.unlockRead(stamp);
        }
    }
}
