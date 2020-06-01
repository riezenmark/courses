package generic;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private final int length;
    private final T[] array;
    private final Iterator<T> iterator = new Iterator<>() {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return length > cursor;
        }

        @Override
        public T next() throws NullPointerException {
            if (hasNext()) {
                return array[cursor++];
            } else {
                throw (new NoSuchElementException());
            }
        }

    };

    @SuppressWarnings("unchecked")
    public SimpleArray(Class<T> c, final int length) {
        this.length = length;
        this.array = ((T[]) Array.newInstance(c, length));
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator;
    }

    public void add(T model) throws ArrayIndexOutOfBoundsException {
        boolean success = false;
        for (int i = 0; i < this.length; i++) {
            if (this.array[i] == null) {
                this.array[i] = model;
                success = true;
                break;
            }
        }
        if (!success) {
            throw (new ArrayIndexOutOfBoundsException());
        }
    }

    public void set(int index, T model) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index < this.length) {
            this.array[index] = model;
        } else {
            throw (new ArrayIndexOutOfBoundsException());
        }
    }

    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index < this.length) {
            this.array[index] = null;
            for (int i = index; i < this.length - 1; i++) {
                this.array[index] = this.array[index + 1];
            }
            this.array[length - 1] = null;
        } else {
            throw (new ArrayIndexOutOfBoundsException());
        }
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index < this.length) {
            return this.array[index];
        } else {
            throw (new ArrayIndexOutOfBoundsException());
        }
    }

}
