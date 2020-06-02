package set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void defaultConstructorTest() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertThat(set.size(), is(0));
        assertThat(set.isEmpty(), is(true));
    }

    @Test
    public void addAndIteratorTest() {
        SimpleSet<Integer> set = new SimpleSet<>();

        assertThat(set.isEmpty(), is(true));
        assertThat(set.size(), is(0));

        set.add(3);
        assertThat(set.isEmpty(), is(false));
        assertThat(set.size(), is(1));
        set.add(7);
        assertThat(set.size(), is(2));
        set.add(5);
        assertThat(set.size(), is(3));

        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(false));

        set.add(5);
        assertThat(set.size(), is(3));

        iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(false));

        set.add(4);
        assertThat(set.size(), is(4));

        iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(false));

        set.add(1);
        assertThat(set.size(), is(5));

        iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextExceptionTest() {
        SimpleSet<Integer> set = new SimpleSet<>();

        set.add(5);
        set.add(7);
        set.add(3);

        Iterator<Integer> it = set.iterator();

        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastTest() {
        SimpleSet<Integer> set = new SimpleSet<>();

        Iterator<Integer> it = set.iterator();
        set.add(8);
        it.next();
    }
}