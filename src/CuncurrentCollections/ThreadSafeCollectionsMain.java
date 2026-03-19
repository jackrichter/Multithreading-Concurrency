package CuncurrentCollections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeCollectionsMain {

    public static void main(String[] args) {

        List<String> orderList = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(() -> addOrder(orderList, "Order 1"));
        Thread t2 = new Thread(() -> addOrder(orderList, "Order 2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final order list: " + orderList);
    }

    public static void addOrder(List<String> orderList, String order) {
        orderList.add(order);
        System.out.println(Thread.currentThread().getName() + " added: " + order);
    }
}
