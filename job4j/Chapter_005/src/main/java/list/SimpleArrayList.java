package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<E> implements Iterable<E> {

    private int position = 0;
    private int length = 10;
    private Object[] container;
    private int modCount = 0;

    public SimpleArrayList() {
        container = new Object[length];
    }

    public SimpleArrayList(int length) {
        this.length = length;
        container = new Object[length];
    }

    public SimpleArrayList(Object[] container) {
        this.container = container;
        this.length = container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;

            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return length > cursor;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() throws NoSuchElementException {
                checkForModification();
                if (hasNext()) {
                    return (E) container[cursor++];
                } else {
                    throw (new NoSuchElementException());
                }
            }

            private void checkForModification() throws ConcurrentModificationException {
                if (modCount != expectedModCount) {
                    throw (new ConcurrentModificationException());
                }
            }
        };
    }

    public int size() {
        return this.length;
    }

    public void add(E value) {
        modCount++;
        if (position >= length) {
            Object[] newContainer = new Object[length * 2];
            if (length >= 0) {
                System.arraycopy(this.container, 0, newContainer, 0, length);
                this.container = newContainer;
                length *= 2;
            }
        }
        this.container[position++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index < this.length) {
            return (E) this.container[index];
        }
        throw (new ArrayIndexOutOfBoundsException());
    }
}
