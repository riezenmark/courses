package list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeArrayList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final SimpleArrayList<E> list;

    public ThreadSafeArrayList() {
        this.list = new SimpleArrayList<>();
    }

    public ThreadSafeArrayList(SimpleArrayList<E> list) {
        this.list = list;
    }

    public ThreadSafeArrayList(Object[] container) {
        this.list = new SimpleArrayList<>(container);
    }

    public int size() {
        synchronized (this) {
            return this.list.size();
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return this.list.isEmpty();
        }
    }

    public void add(int index, E value) {
        synchronized (this) {
            this.list.add(index, value);
        }
    }

    public void add(E value) {
        synchronized (this) {
            this.list.add(value);
        }
    }

    public E get(int index) {
        synchronized (this) {
            return this.list.get(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            return copy(this.list).iterator();
        }
    }

    private SimpleArrayList<E> copy(SimpleArrayList<E> origin) {
        SimpleArrayList<E> copy = new SimpleArrayList<>(origin.size());
        for (var element : origin) {
            copy.add(element);
        }
        return copy;
    }
}
