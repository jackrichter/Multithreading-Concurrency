package advancedThreadTopics.concurrencyDesignPatterns.threadpermessagedesignpattern;

public class ThreadPerMessagePatternExampleMain {

    public static void main(String[] args) {

        String[] messages = {"Message 1", "Message 2", "Message 3", "Message 4", "Message 5"};

        for (String message : messages) {
            Thread thread = new Thread(new PrintRequestHandler(message));
            thread.start();
        }
    }
}
