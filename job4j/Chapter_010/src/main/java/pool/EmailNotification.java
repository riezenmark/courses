package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = String.format("Notification to %s on email %s", user.getName(), user.getEmail());
        String body = String.format("Add a new event to %s.", user.getName());
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    @SuppressWarnings("BusyWait")
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Pool has been shut down.");
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
