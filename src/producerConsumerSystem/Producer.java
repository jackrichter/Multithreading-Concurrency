package producerConsumerSystem;

import java.util.Random;

public class Producer implements Runnable{

    private final SharedBuffer sharedBuffer;
    private int orderId = 0;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        String[] products = {"Laptop", "Phone", "Headphone", "Monitor", "Keyboard"};
        Random random = new Random();

        while (true) {

            try {
                // Generate an Order
                String productName = products[random.nextInt(products.length)];
                int quantity = random.nextInt(1, 6);

                Order newOrder = new Order(++orderId, productName, quantity);
                sharedBuffer.produce(newOrder);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
