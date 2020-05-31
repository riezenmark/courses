package iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorIntArrayTest {

    private static final class ForEachArray implements Iterable {

        private final int[] values;

        public ForEachArray(final int[] values) {
            this.values = values;
        }

        @Override
        public Iterator iterator() {
            return new IteratorIntArray(this.values);
        }
    }

    @Test
    public void whenCallNextThenGetValueAndGoForward() {
        IteratorIntArray it = new IteratorIntArray(new int[] {1, 3});

        it.next();
        var result = it.next();

        assertThat(result, is(3));
    }

    @Test
    public void whenHasNext() {
        IteratorIntArray it = new IteratorIntArray(new int[] {1, 3});

        it.next();
        boolean hasNext = it.hasNext();

        assertThat(hasNext, is(true));
    }

    @Test
    public void whenNoNext() {
        IteratorIntArray it = new IteratorIntArray(new int[] {1, 3});

        it.next();
        it.next();
        boolean hasNext = it.hasNext();

        assertThat(hasNext, is(false));
    }

    @Test
    public void whenForEach() {
        ForEachArray f = new ForEachArray(new int[] {1, 2, 4, 5, 10, 12});

        for (var value : f) {
            System.out.println(value);
        }
    }

}