package JVMAndThreads;

public class ThreadPriorityExampleMain {

    public static void main(String[] args) {

        Thread highPriorityThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("High-priority thread: " + i);
            }
        }, "High-priority thread");

        Thread mediumPriorityThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Medium-priority thread: " + i);
            }
        }, "Medium-priority thread");

        Thread lowPriorityThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Low-priority thread: " + i);
            }
        }, "Low-priority thread");

        highPriorityThread.setPriority((Thread.MAX_PRIORITY));
        mediumPriorityThread.setPriority((Thread.NORM_PRIORITY));
        lowPriorityThread.setPriority((Thread.MIN_PRIORITY));

        highPriorityThread.start();
        mediumPriorityThread.start();
        lowPriorityThread.start();
    }
}
