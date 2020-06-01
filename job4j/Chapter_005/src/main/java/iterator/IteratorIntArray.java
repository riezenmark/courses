package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorIntArray implements Iterator {

    private final int[] values;
    private int index = 0;

    public IteratorIntArray(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > index;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return values[index++];
        } else {
            throw (new NoSuchElementException());
        }
    }
}
