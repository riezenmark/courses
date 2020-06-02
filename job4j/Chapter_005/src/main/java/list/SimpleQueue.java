package list;

import java.util.Iterator;

public class SimpleQueue<T> implements Iterable<T> {

    private final SimpleLinkedList<T> container = new SimpleLinkedList<>();

    public void add(T data) {
        container.add(data);
    }

    public T poll() {
        return container.delete(0);
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
