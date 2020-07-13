package queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= size) {
                System.out.println("Queue size is " + size + ", and it already has " + size + " elements.");
                wait();
            }
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
