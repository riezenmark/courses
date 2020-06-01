package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] numbers;
    private int position = 0;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return nextEvenPosition() != -1;
    }

    @Override
    public Object next() {
        int i = nextEvenPosition();
        if (i != -1) {
            position = i + 1;
            return numbers[i];
        }
        throw (new NoSuchElementException());
    }

    private int nextEvenPosition() {
        for (int i = position; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}
