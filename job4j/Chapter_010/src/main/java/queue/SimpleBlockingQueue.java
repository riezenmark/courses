package queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (this) {
            queue.offer(value);
            if (queue.size() == 1) {
                notify();
            }
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty. Waiting for new data...");
                wait();
            }
            T t = queue.poll();
            notify();
            return t;
        }
    }

}
