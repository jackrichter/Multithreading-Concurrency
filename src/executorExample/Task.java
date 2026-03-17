package executorExample;

public class Task implements Runnable{

    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        System.out.println("Executing task " + taskName + " by thread: " + Thread.currentThread().getName());

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(taskName + " is completed.");
    }
}
