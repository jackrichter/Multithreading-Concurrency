package ConditionVariableExample;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {

    // The Buffer
    Queue<Integer> queue = new LinkedList<>();
    private  final int capacity = 5;

    private  final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();    // thread acquires the lock

        try {
            // while the queue is full, wait.
            while (queue.size() == capacity) {
                System.out.println("Queue is full. Producer is waiting...");
                notFull.await();    // wait until the queue is not full
            }
            queue.add(value);   // add an item to the queue
            System.out.println("Produced: " + value);
            notEmpty.signal();      // Notify the Consumer that the queue is not empty

        } finally {
            lock.unlock();      // thread lock is released
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();    // thread acquires the lock

        try {
            // while the queue is empty, wait.
            while (queue.isEmpty()) {
                System.out.println("Queue is empty. Consumer is waiting...");
                notEmpty.await();   // wait until the queue is not empty
            }
            int value = queue.poll();   // remove an item from the queue
            System.out.println("Consumed: " + value);
            notFull.signal();       // Notify the Producer that the queue is not full

        } finally {
            lock.unlock();      // thread lock is released
        }
    }
}
