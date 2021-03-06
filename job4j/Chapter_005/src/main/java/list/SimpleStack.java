package list;

import java.util.Iterator;

public class SimpleStack<T> implements Iterable<T> {

    private final SimpleLinkedList<T> container = new SimpleLinkedList<>();

    public T pop() {
        return container.delete();
    }

    public void push(T data) {
        container.add(data);
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public T get(int index) {
        return container.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
